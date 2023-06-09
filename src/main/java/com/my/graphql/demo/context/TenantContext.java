package com.my.graphql.demo.context;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class TenantContext {
  private static final InheritableThreadLocal<TenantInfo> currentTenantContext =
      new InheritableThreadLocal<>();

  private TenantContext() {}

  public static void setCurrentTenantContext(String dataPartitionId) {
    log.info("Setting tenant context with dataPartitionId: {}", dataPartitionId);
    TenantInfo tenantInfo = new TenantInfo(dataPartitionId);
    currentTenantContext.set(tenantInfo);
  }

  public static TenantInfo getCurrentTenantContext() {
    return currentTenantContext.get();
  }

  public static String getCurrentDataPartitionId() {
    return currentTenantContext.get() != null
        ? currentTenantContext.get().getDataPartitionId()
        : null;
  }

  public static void clear() {
    log.info("Clearing tenant context, current context: {}", currentTenantContext.get());
    currentTenantContext.remove();
  }
}
