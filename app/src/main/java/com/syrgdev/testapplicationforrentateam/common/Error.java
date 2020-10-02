package com.syrgdev.testapplicationforrentateam.common;

public class Error {

    String mMessage;
    Throwable mThrowable;

    public Error(Throwable throwable, String message) {
        mMessage = message;
        mThrowable = throwable;
    }

    public String getMessage() {
        return mMessage;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

}
