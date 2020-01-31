package com.task.databaseinspector.interceptor;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="request", proxyMode= ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter(value = AccessLevel.PACKAGE)
public class ContextHolder {
    private Long connectionId;

}
