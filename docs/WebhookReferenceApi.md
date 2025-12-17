# WebhookReferenceApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**sendPostWebhooksPost**](WebhookReferenceApi.md#sendPostWebhooksPost) | **POST** /SendPostWebhooks | SendPost Webhook Object |


<a id="sendPostWebhooksPost"></a>
# **sendPostWebhooksPost**
> sendPostWebhooksPost(webhookObject)

SendPost Webhook Object

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.WebhookReferenceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");

    WebhookReferenceApi apiInstance = new WebhookReferenceApi(defaultClient);
    WebhookObject webhookObject = new WebhookObject(); // WebhookObject | 
    try {
      apiInstance.sendPostWebhooksPost(webhookObject);
    } catch (ApiException e) {
      System.err.println("Exception when calling WebhookReferenceApi#sendPostWebhooksPost");
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
| **webhookObject** | [**WebhookObject**](WebhookObject.md)|  | [optional] |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Return a 200 status to indicate that the data was received successfully |  -  |

