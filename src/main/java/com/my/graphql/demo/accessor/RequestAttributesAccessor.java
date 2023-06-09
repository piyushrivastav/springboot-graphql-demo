package com.my.graphql.demo.accessor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import io.micrometer.context.ThreadLocalAccessor;

/**
 * {@link ThreadLocalAccessor} to expose a thread-bound RequestAttributes object to data
 * fetchers in Spring GraphQL.
 */
@Component
public class RequestAttributesAccessor implements ThreadLocalAccessor<RequestAttributes> {


	@Override
	public Object key() {
		return "dataPartitionId";
	}

	@Override
	public RequestAttributes getValue() {
		return RequestContextHolder.getRequestAttributes();
	}

	@Override
	public void setValue(RequestAttributes attributes) {
		RequestContextHolder.setRequestAttributes(attributes);
	}

	@Override
	public void reset() {
		RequestContextHolder.resetRequestAttributes();
	}

}

