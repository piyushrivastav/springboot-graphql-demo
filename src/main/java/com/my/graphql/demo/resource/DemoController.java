package com.my.graphql.demo.resource;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

import com.my.graphql.demo.model.PropertyConfigDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Slf4j
@Controller
public class DemoController {

  @QueryMapping(name = "propertyConfig")
  public PropertyConfigDetails propertyConfig() {
    RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
    String attribute = (String) attributes.getAttribute("dataPartitionId", SCOPE_REQUEST);
    log.info(attribute);

    PropertyConfigDetails propertyConfigDetails = new PropertyConfigDetails();
    propertyConfigDetails.setProperty("dataPartitionId");
    propertyConfigDetails.setValue(attribute);
    return propertyConfigDetails;
  }
}
