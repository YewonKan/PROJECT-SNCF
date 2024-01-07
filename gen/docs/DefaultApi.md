# DefaultApi

All URIs are relative to *https://PROJECT_SNCF*

Method | HTTP request | Description
------------- | ------------- | -------------
[**isTicketValid**](DefaultApi.md#isTicketValid) | **POST** /ticket/validity | POST ticket/validity


<a name="isTicketValid"></a>
# **isTicketValid**
> Object isTicketValid(ticketValidationDataDTO)

POST ticket/validity

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://PROJECT_SNCF");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    TicketValidationDataDTO ticketValidationDataDTO = new TicketValidationDataDTO(); // TicketValidationDataDTO | 
    try {
      Object result = apiInstance.isTicketValid(ticketValidationDataDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#isTicketValid");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ticketValidationDataDTO** | [**TicketValidationDataDTO**](TicketValidationDataDTO.md)|  |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json, application/xml
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

