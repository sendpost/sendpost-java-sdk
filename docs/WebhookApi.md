# WebhookApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createWebhook**](WebhookApi.md#createWebhook) | **POST** /account/webhook | Create Webhook |
| [**deleteWebhook**](WebhookApi.md#deleteWebhook) | **DELETE** /account/webhook/{webhook_id} | Delete Webhook |
| [**getAllWebhooks**](WebhookApi.md#getAllWebhooks) | **GET** /account/webhook | List Webhooks |
| [**getWebhook**](WebhookApi.md#getWebhook) | **GET** /account/webhook/{webhook_id} | Get Webhook |
| [**updateWebhook**](WebhookApi.md#updateWebhook) | **PUT** /account/webhook/{webhook_id} | Update Webhook |


<a id="createWebhook"></a>
# **createWebhook**
> Webhook createWebhook(newWebhook)

Create Webhook

Create a new webhook to receive real-time notifications for email events. Your endpoint will receive HTTP POST requests with event data as they occur.  **Endpoint Requirements:** - Must be publicly accessible HTTPS URL - Should return 2xx status within 30 seconds - Handle potential duplicate events (use event ID for deduplication) - Implement retry/queue logic for reliability  **Choosing Events:** - **Engagement Tracking:** &#x60;uniqueOpened&#x60;, &#x60;uniqueClicked&#x60; for metrics - **Full History:** &#x60;opened&#x60;, &#x60;clicked&#x60; for complete event logs - **Delivery Monitoring:** &#x60;delivered&#x60;, &#x60;hardBounced&#x60;, &#x60;softBounced&#x60; - **Compliance:** &#x60;unsubscribed&#x60;, &#x60;spam&#x60;  **Best Practices:** - Only enable events you actually need - Store events before processing (async processing) - Implement idempotency using event IDs - Set up monitoring for webhook failures  **Webhook Payload Example:** &#x60;&#x60;&#x60;json {   \&quot;eventId\&quot;: \&quot;evt_123\&quot;,   \&quot;event\&quot;: \&quot;delivered\&quot;,   \&quot;messageId\&quot;: \&quot;msg_456\&quot;,   \&quot;recipient\&quot;: \&quot;user@example.com\&quot;,   \&quot;timestamp\&quot;: \&quot;2024-01-15T10:30:00Z\&quot; } &#x60;&#x60;&#x60; 

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
| **401** | Unauthorized. Invalid or missing API key. |  -  |
| **403** | Forbidden. Webhook with the same URL already exists. |  -  |
| **406** | Not Acceptable. Cannot create webhook for the default sub-account. |  -  |
| **422** | Unprocessable entity. Invalid URL or missing required fields. |  -  |

<a id="deleteWebhook"></a>
# **deleteWebhook**
> DeleteWebhookResponse deleteWebhook(webhookId)

Delete Webhook

Remove a webhook from your account. After deletion, no further events will be sent to that endpoint.  **Before Deleting:** - Ensure your application doesn&#39;t rely on these events - Consider updating to a new webhook instead if you&#39;re migrating  **Note:** Events that occurred before deletion are not affected. Historical data remains intact. 

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
    Integer webhookId = 117; // Integer | The unique ID of the webhook to delete.
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
| **webhookId** | **Integer**| The unique ID of the webhook to delete. | |

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
> List&lt;AccountWebhookWithStats&gt; getAllWebhooks(limit, offset, search)

List Webhooks

Retrieve all configured webhooks for your account. Webhooks enable real-time notifications when email events occur, allowing you to build reactive applications.  **Supported Events:** | Event | Description | |-------|-------------| | &#x60;processed&#x60; | Email accepted and queued for delivery | | &#x60;dropped&#x60; | Email blocked (suppressed, invalid, policy) | | &#x60;delivered&#x60; | Email successfully delivered to recipient | | &#x60;hardBounced&#x60; | Permanent delivery failure | | &#x60;softBounced&#x60; | Temporary delivery failure | | &#x60;opened&#x60; | Recipient opened the email (all opens) | | &#x60;uniqueOpened&#x60; | First open per recipient only | | &#x60;clicked&#x60; | Recipient clicked a link (all clicks) | | &#x60;uniqueClicked&#x60; | First click per recipient only | | &#x60;unsubscribed&#x60; | Recipient unsubscribed | | &#x60;spam&#x60; | Recipient marked email as spam |  **Use Cases:** - Audit configured webhook endpoints - Verify webhook URLs are correct - Review enabled events per webhook - Debug webhook delivery issues 

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
    Integer limit = 20; // Integer | Number of records to return per request. Default 20.
    Integer offset = 0; // Integer | Number of initial records to skip for pagination.
    String search = "api.yoursite.com"; // String | Case insensitive search against webhook URLs.
    try {
      List<AccountWebhookWithStats> result = apiInstance.getAllWebhooks(limit, offset, search);
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
| **limit** | **Integer**| Number of records to return per request. Default 20. | [optional] [default to 20] |
| **offset** | **Integer**| Number of initial records to skip for pagination. | [optional] [default to 0] |
| **search** | **String**| Case insensitive search against webhook URLs. | [optional] |

### Return type

[**List&lt;AccountWebhookWithStats&gt;**](AccountWebhookWithStats.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | List of configured webhooks, each with its delivery success rate. |  -  |

<a id="getWebhook"></a>
# **getWebhook**
> Webhook getWebhook(webhookId)

Get Webhook

Retrieve detailed information about a specific webhook, including its endpoint URL and enabled events.  **Use Cases:** - Verify webhook configuration - Debug event delivery issues - Check enabled events for a webhook - Audit webhook settings 

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
    Integer webhookId = 117; // Integer | The unique ID of the webhook to retrieve.
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
| **webhookId** | **Integer**| The unique ID of the webhook to retrieve. | |

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
| **200** | Webhook configuration details. |  -  |

<a id="updateWebhook"></a>
# **updateWebhook**
> Webhook updateWebhook(updateWebhook, webhookId)

Update Webhook

Modify an existing webhook&#39;s configuration. Use this to change the endpoint URL or update which events trigger notifications.  **What Can Be Updated:** - Webhook endpoint URL - Enabled/disabled events - Event-specific settings  **Use Cases:** - Migrate to a new endpoint URL - Enable additional events as needs grow - Disable events to reduce traffic - Update after infrastructure changes 

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
    UpdateWebhook updateWebhook = new UpdateWebhook(); // UpdateWebhook | 
    Integer webhookId = 117; // Integer | The unique ID of the webhook to update.
    try {
      Webhook result = apiInstance.updateWebhook(updateWebhook, webhookId);
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
| **updateWebhook** | [**UpdateWebhook**](UpdateWebhook.md)|  | |
| **webhookId** | **Integer**| The unique ID of the webhook to update. | |

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

