package com.task.databaseinspector.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.databaseinspector.busobj.response.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ConnectionIdParamInterceptor implements HandlerInterceptor {

    @Autowired
    private ContextHolder contextHolder;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    @Lazy
    private MessageSource messageSource;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        final String connectionId = request.getParameter("connectionId");
        if (StringUtils.isEmpty(connectionId)) {
            final GeneralResponse generalResponse = new GeneralResponse(false, messageSource.getMessage("connection.id.null", null, LocaleContextHolder.getLocale()));
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(objectMapper.writeValueAsString(generalResponse));
            return false;
        }
        contextHolder.setConnectionId(Long.valueOf(connectionId));
        return true;
    }
}
