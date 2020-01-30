package com.task.databaseinspector.exception;

import lombok.Getter;

@Getter
public class ConnectionNotFoundException extends RuntimeException {
    private final long connectionId;

    public ConnectionNotFoundException(long connectionId) {
        this.connectionId = connectionId;
    }
}
