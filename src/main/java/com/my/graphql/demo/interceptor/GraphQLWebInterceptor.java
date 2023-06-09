package com.my.graphql.demo.interceptor;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.util.Collections;

@Component
public class GraphQLWebInterceptor implements  WebGraphQlInterceptor {


    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        String dataPartitionId = request.getHeaders().getFirst("data-partition-id");
        final String tenantId = dataPartitionId !=null ? dataPartitionId : "tenant1";
        request.configureExecutionInput((executionInput, builder) ->
                builder.graphQLContext(Collections.singletonMap("dataPartitionId", tenantId)).build());
        return chain.next(request);
    }
}
