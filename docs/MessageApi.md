# MessageApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAllMessages**](MessageApi.md#getAllMessages) | **GET** /account/message | Get all  messages |
| [**getMessageById**](MessageApi.md#getMessageById) | **GET** /account/message/{message_id} | Retrieve a specific message |


<a id="getAllMessages"></a>
# **getAllMessages**
> List&lt;Message&gt; getAllMessages(from, to, limit, offset)

Get all  messages

Retrieve messages sent via the SendPost platform within a specific date range.

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.MessageApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    MessageApi apiInstance = new MessageApi(defaultClient);
    OffsetDateTime from = OffsetDateTime.now(); // OffsetDateTime | Date from which messages should be retrieved.
    OffsetDateTime to = OffsetDateTime.now(); // OffsetDateTime | Date to which messages should be retrieved. (Should be later than `from` and no more than 60 days apart.)
    Integer limit = 50; // Integer | Number of records to return per request.
    Integer offset = 0; // Integer | Number of initial records to skip.
    try {
      List<Message> result = apiInstance.getAllMessages(from, to, limit, offset);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#getAllMessages");
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
| **from** | **OffsetDateTime**| Date from which messages should be retrieved. | |
| **to** | **OffsetDateTime**| Date to which messages should be retrieved. (Should be later than &#x60;from&#x60; and no more than 60 days apart.) | |
| **limit** | **Integer**| Number of records to return per request. | [optional] [default to 50] |
| **offset** | **Integer**| Number of initial records to skip. | [optional] [default to 0] |

### Return type

[**List&lt;Message&gt;**](Message.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful retrieval of messages. |  -  |
| **400** | Invalid input parameters. |  -  |
| **401** | Unauthorized. Invalid API key. |  -  |

<a id="getMessageById"></a>
# **getMessageById**
> Message getMessageById(messageId)

Retrieve a specific message

Retrieve detailed information about a specific message by its ID.

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.MessageApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    MessageApi apiInstance = new MessageApi(defaultClient);
    String messageId = "messageId_example"; // String | The ID of the message to retrieve.
    try {
      Message result = apiInstance.getMessageById(messageId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessageApi#getMessageById");
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
| **messageId** | **String**| The ID of the message to retrieve. | |

### Return type

[**Message**](Message.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful retrieval of the message. |  -  |
| **404** | Message not found. |  -  |
| **401** | Unauthorized. Invalid API key. |  -  |

