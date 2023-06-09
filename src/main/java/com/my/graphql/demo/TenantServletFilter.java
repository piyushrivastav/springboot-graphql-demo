package com.my.graphql.demo;

import com.my.graphql.demo.context.TenantContext;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class TenantServletFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpServletRequest) {
            String dataPartitionId = httpServletRequest.getHeader("data-partition-id");
            TenantContext.setCurrentTenantContext(dataPartitionId != null ? dataPartitionId : "tenant1");
        }
        chain.doFilter(request, response);
        TenantContext.clear();
    }
}
