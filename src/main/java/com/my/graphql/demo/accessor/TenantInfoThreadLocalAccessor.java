package com.my.graphql.demo.accessor;

import com.my.graphql.demo.context.TenantContext;
import com.my.graphql.demo.context.TenantInfo;
import io.micrometer.context.ThreadLocalAccessor;

/**
 * {@link ThreadLocalAccessor} to expose a thread-bound TenantInfo object to data
 * fetchers in Spring GraphQL.
 */
public class TenantInfoThreadLocalAccessor implements ThreadLocalAccessor<TenantInfo> {

	public final String KEY = "tenantInfo";

	@Override
	public Object key() {
		return KEY;
	}

	@Override
	public TenantInfo getValue() {
		return TenantContext.getCurrentTenantContext();
	}

	@Override
	public void setValue(TenantInfo tenantInfo) {
		TenantContext.setCurrentTenantContext(tenantInfo);
	}

	@Override
	public void reset() {
		TenantContext.clear();
	}

}

