# MessageApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAllMessages**](MessageApi.md#getAllMessages) | **GET** /account/message | List Messages |
| [**getMessageById**](MessageApi.md#getMessageById) | **GET** /account/message/{message_id} | Get Message |


<a id="getAllMessages"></a>
# **getAllMessages**
> List&lt;Message&gt; getAllMessages(from, to, limit, offset)

List Messages

Retrieve a paginated list of all email messages sent through your account. Each message includes delivery status, timestamps, and metadata.  **Message Information Includes:** - Sender and recipient details - Subject line and message ID - Delivery status (delivered, bounced, opened, etc.) - Timestamps for each event - IP address and pool used for sending  **Use Cases:** - Search for specific emails sent to customers - Debug delivery issues for specific recipients - Audit email delivery for compliance - Export message logs for analysis - Customer support - lookup specific email by recipient  **Example:** Find all emails to a specific domain in the last week: &#x60;&#x60;&#x60; GET /account/message?from&#x3D;2024-01-01T00:00:00Z&amp;to&#x3D;2024-01-07T23:59:59Z &#x60;&#x60;&#x60;  **Note:** Maximum date range is 60 days. 

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
    OffsetDateTime from = OffsetDateTime.parse("2024-01-01T00:00:00Z"); // OffsetDateTime | Start timestamp for message retrieval. ISO 8601 format.
    OffsetDateTime to = OffsetDateTime.parse("2024-01-31T23:59:59Z"); // OffsetDateTime | End timestamp for message retrieval. Max 60 days from `from`.
    Integer limit = 50; // Integer | Number of records to return per request. Default 50, max 100.
    Integer offset = 0; // Integer | Number of initial records to skip for pagination.
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
| **from** | **OffsetDateTime**| Start timestamp for message retrieval. ISO 8601 format. | |
| **to** | **OffsetDateTime**| End timestamp for message retrieval. Max 60 days from &#x60;from&#x60;. | |
| **limit** | **Integer**| Number of records to return per request. Default 50, max 100. | [optional] [default to 50] |
| **offset** | **Integer**| Number of initial records to skip for pagination. | [optional] [default to 0] |

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
| **200** | List of messages matching the query. |  -  |
| **400** | Invalid input parameters (e.g., date range exceeds 60 days). |  -  |
| **401** | Unauthorized. Invalid or missing API key. |  -  |

<a id="getMessageById"></a>
# **getMessageById**
> Message getMessageById(messageId)

Get Message

Retrieve complete details about a specific email message, including full event timeline and metadata.  **Response Includes:** - Full message details (sender, recipients, subject) - Complete event timeline (submitted, sent, delivered, opened, clicked) - Bounce/drop information with reasons - IP and pool used for sending - Click and open tracking data  **Use Cases:** - Debug why a specific email wasn&#39;t delivered - Customer support - provide delivery proof - Audit trail for compliance requirements - Analyze engagement for specific messages 

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
    String messageId = "msg_01H2X3Y4Z5A6B7C8D9E0F1G2H3"; // String | The unique message ID returned when the email was sent.
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
| **messageId** | **String**| The unique message ID returned when the email was sent. | |

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
| **200** | Complete message details with event history. |  -  |
| **404** | Message not found. ID may be invalid or message has been archived. |  -  |
| **401** | Unauthorized. Invalid or missing API key. |  -  |

