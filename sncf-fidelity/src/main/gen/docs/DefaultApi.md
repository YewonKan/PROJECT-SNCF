# DefaultApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**fideliteVerifyClientStatusPost**](DefaultApi.md#fideliteVerifyClientStatusPost) | **POST** /fidelite/verifyClientStatus | Vérifie le statut du client |


<a name="fideliteVerifyClientStatusPost"></a>
# **fideliteVerifyClientStatusPost**
> Compensation fideliteVerifyClientStatusPost(compensation)

Vérifie le statut du client

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
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Compensation compensation = new Compensation(); // Compensation | Objet Compensation en entrée
    try {
      Compensation result = apiInstance.fideliteVerifyClientStatusPost(compensation);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#fideliteVerifyClientStatusPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **compensation** | [**Compensation**](Compensation.md)| Objet Compensation en entrée | |

### Return type

[**Compensation**](Compensation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Succès |  -  |
| **400** | Mauvaise requête |  -  |
| **500** | Erreur interne du serveur |  -  |

