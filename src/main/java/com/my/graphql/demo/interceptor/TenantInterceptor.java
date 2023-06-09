package com.my.graphql.demo.interceptor;

import com.my.graphql.demo.context.TenantContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
        HttpServletRequest request, HttpServletResponse response, Object handler) {
        // TODO: to be updated with dynamic data partition id based on the request
        final String dataPartitionId = request.getHeader("data-partition-id");
        TenantContext.setCurrentTenantContext(dataPartitionId != null ? dataPartitionId : "tenant1");
        return true;
    }

    @Override
    public void afterCompletion(
        HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        request.removeAttribute("dataPartitionId");
        TenantContext.clear();
    }
}
