package com.github.springtemplate.service;

import com.github.springtemplate.dto.request.SignupRequest;
import com.github.springtemplate.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 나이 증가 스레드 안전성 테스트")
    public void testUserAgingAsync() throws InterruptedException {
        final int executeNumber = 20;

        final ExecutorService executorService = Executors.newFixedThreadPool(executeNumber);
        final CountDownLatch countDownLatch = new CountDownLatch(executeNumber);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        for (int i = 0; i < executeNumber; i++) {
            int index = i / 5;
            try {
                executorService.execute(()->{
                    try {
                        userService.signup(SignupRequest.builder()
                                .age(10)
                                .name("name" + index)
                                .email("koey" + index + "@gmail.com")
                                .password("password"+index)
                                .build()
                        );
                        successCount.getAndIncrement();
                    } catch (Exception e) {
                        failCount.getAndIncrement();
                    } finally {
                        countDownLatch.countDown();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        countDownLatch.await();
        System.out.printf("success = %d, fail = %d\n", successCount.get(), failCount.get());
        assertEquals(executeNumber, successCount.get() + failCount.get());
    }
}
