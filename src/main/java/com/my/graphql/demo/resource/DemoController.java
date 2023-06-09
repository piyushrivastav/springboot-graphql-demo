package com.my.graphql.demo.resource;

import com.my.graphql.demo.context.TenantContext;
import com.my.graphql.demo.model.PropertyConfigDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class DemoController {

  @QueryMapping
  public PropertyConfigDetails propertyConfig() {
    String dataPartitionId = TenantContext.getCurrentDataPartitionId();
    log.info(dataPartitionId);
    PropertyConfigDetails propertyConfigDetails = new PropertyConfigDetails();
    propertyConfigDetails.setProperty("dataPartitionId");
    propertyConfigDetails.setValue(dataPartitionId);
    return propertyConfigDetails;
  }
}
