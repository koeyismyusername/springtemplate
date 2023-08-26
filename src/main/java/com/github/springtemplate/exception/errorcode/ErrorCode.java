package com.github.springtemplate.exception.errorcode;

import com.github.springtemplate.exception.ApiException;

public interface ErrorCode {
    ApiException exception();
    String getMessage();
    int getStatus();
}
