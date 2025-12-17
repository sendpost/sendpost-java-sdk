# WebhookApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createWebhook**](WebhookApi.md#createWebhook) | **POST** /account/webhook | Create a New Webhook |
| [**deleteWebhook**](WebhookApi.md#deleteWebhook) | **DELETE** /account/webhook/{webhook_id} | Delete a Specific Webhook |
| [**getAllWebhooks**](WebhookApi.md#getAllWebhooks) | **GET** /account/webhook | Get All Webhooks |
| [**getWebhook**](WebhookApi.md#getWebhook) | **GET** /account/webhook/{webhook_id} | Get a Specific Webhook |
| [**updateWebhook**](WebhookApi.md#updateWebhook) | **PUT** /account/webhook/{webhook_id} | Update an Existing Webhook |


<a id="createWebhook"></a>
# **createWebhook**
> Webhook createWebhook(newWebhook)

Create a New Webhook

Create a new webhook by specifying its properties.

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.WebhookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    WebhookApi apiInstance = new WebhookApi(defaultClient);
    NewWebhook newWebhook = new NewWebhook(); // NewWebhook | 
    try {
      Webhook result = apiInstance.createWebhook(newWebhook);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WebhookApi#createWebhook");
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
| **newWebhook** | [**NewWebhook**](NewWebhook.md)|  | |

### Return type

[**Webhook**](Webhook.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Webhook created successfully. |  -  |
| **401** | Unauthorized. Invalid API key. |  -  |
| **403** | Forbidden. Webhook with the same URL already exists. |  -  |
| **406** | Not Acceptable. Cannot create webhook for the default sub-account. |  -  |
| **422** | Unprocessable entity. Invalid request body. |  -  |

<a id="deleteWebhook"></a>
# **deleteWebhook**
> DeleteWebhookResponse deleteWebhook(webhookId)

Delete a Specific Webhook

Delete a webhook by its ID.

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.WebhookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    WebhookApi apiInstance = new WebhookApi(defaultClient);
    Integer webhookId = 117; // Integer | ID of the webhook to delete.
    try {
      DeleteWebhookResponse result = apiInstance.deleteWebhook(webhookId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WebhookApi#deleteWebhook");
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
| **webhookId** | **Integer**| ID of the webhook to delete. | |

### Return type

[**DeleteWebhookResponse**](DeleteWebhookResponse.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Webhook deleted successfully. |  -  |

<a id="getAllWebhooks"></a>
# **getAllWebhooks**
> List&lt;Webhook&gt; getAllWebhooks(limit, offset, search)

Get All Webhooks

Retrieves a list of all webhooks, their endpoints, and the events for which they are active.

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.WebhookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    WebhookApi apiInstance = new WebhookApi(defaultClient);
    Integer limit = 10; // Integer | Number of records to return per request.
    Integer offset = 0; // Integer | Number of initial records to skip.
    String search = "hooli"; // String | Case insensitive search against webhook URL.
    try {
      List<Webhook> result = apiInstance.getAllWebhooks(limit, offset, search);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WebhookApi#getAllWebhooks");
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
| **limit** | **Integer**| Number of records to return per request. | [optional] |
| **offset** | **Integer**| Number of initial records to skip. | [optional] |
| **search** | **String**| Case insensitive search against webhook URL. | [optional] |

### Return type

[**List&lt;Webhook&gt;**](Webhook.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of webhooks. |  -  |

<a id="getWebhook"></a>
# **getWebhook**
> Webhook getWebhook(webhookId)

Get a Specific Webhook

Retrieves a specific webhook based on its ID.

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.WebhookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    WebhookApi apiInstance = new WebhookApi(defaultClient);
    Integer webhookId = 117; // Integer | The ID of the webhook to retrieve.
    try {
      Webhook result = apiInstance.getWebhook(webhookId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WebhookApi#getWebhook");
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
| **webhookId** | **Integer**| The ID of the webhook to retrieve. | |

### Return type

[**Webhook**](Webhook.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Webhook details. |  -  |

<a id="updateWebhook"></a>
# **updateWebhook**
> Webhook updateWebhook(webhookId, updateWebhook)

Update an Existing Webhook

Update the properties of an existing webhook.

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.WebhookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    WebhookApi apiInstance = new WebhookApi(defaultClient);
    Integer webhookId = 117; // Integer | ID of the webhook to update.
    UpdateWebhook updateWebhook = new UpdateWebhook(); // UpdateWebhook | 
    try {
      Webhook result = apiInstance.updateWebhook(webhookId, updateWebhook);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WebhookApi#updateWebhook");
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
| **webhookId** | **Integer**| ID of the webhook to update. | |
| **updateWebhook** | [**UpdateWebhook**](UpdateWebhook.md)|  | |

### Return type

[**Webhook**](Webhook.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Webhook updated successfully. |  -  |

