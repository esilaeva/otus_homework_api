package otus.services;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import otus.annotations.UrlPrefix;

public abstract class ServiceApiAbs {
  
  public RequestSpecification spec;
  
  public String getBaseUrl() {
    return StringUtils.stripEnd(System.getProperty("base.url", "https://petstore.swagger.io/v2"), "/");
  }
  
  public String getUrlPrefix() {
    UrlPrefix urlAnnotation = getClass().getAnnotation(UrlPrefix.class);
    if (urlAnnotation != null) {
      return urlAnnotation.value();
    }
    
    return EMPTY;
  }
  
}
