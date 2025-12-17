# EmailApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**sendEmail**](EmailApi.md#sendEmail) | **POST** /subaccount/email/ | Send Email |
| [**sendEmailWithTemplate**](EmailApi.md#sendEmailWithTemplate) | **POST** /subaccount/email/template | Send Email With Template |


<a id="sendEmail"></a>
# **sendEmail**
> List&lt;EmailResponse&gt; sendEmail(emailMessageObject)

Send Email

Use this API to send either a single or batch email.

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.EmailApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: subAccountAuth
    ApiKeyAuth subAccountAuth = (ApiKeyAuth) defaultClient.getAuthentication("subAccountAuth");
    subAccountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //subAccountAuth.setApiKeyPrefix("Token");

    EmailApi apiInstance = new EmailApi(defaultClient);
    EmailMessageObject emailMessageObject = new EmailMessageObject(); // EmailMessageObject | Email message details
    try {
      List<EmailResponse> result = apiInstance.sendEmail(emailMessageObject);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmailApi#sendEmail");
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
| **emailMessageObject** | [**EmailMessageObject**](EmailMessageObject.md)| Email message details | |

### Return type

[**List&lt;EmailResponse&gt;**](EmailResponse.md)

### Authorization

[subAccountAuth](../README.md#subAccountAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of email message response objects. |  -  |

<a id="sendEmailWithTemplate"></a>
# **sendEmailWithTemplate**
> List&lt;EmailResponse&gt; sendEmailWithTemplate(emailMessageWithTemplate)

Send Email With Template

Use this API to send an email with a predefined template. This makes it easy to integrate transactional emails with minimal code. 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.EmailApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: subAccountAuth
    ApiKeyAuth subAccountAuth = (ApiKeyAuth) defaultClient.getAuthentication("subAccountAuth");
    subAccountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //subAccountAuth.setApiKeyPrefix("Token");

    EmailApi apiInstance = new EmailApi(defaultClient);
    EmailMessageWithTemplate emailMessageWithTemplate = new EmailMessageWithTemplate(); // EmailMessageWithTemplate | Email message details with template information
    try {
      List<EmailResponse> result = apiInstance.sendEmailWithTemplate(emailMessageWithTemplate);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmailApi#sendEmailWithTemplate");
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
| **emailMessageWithTemplate** | [**EmailMessageWithTemplate**](EmailMessageWithTemplate.md)| Email message details with template information | |

### Return type

[**List&lt;EmailResponse&gt;**](EmailResponse.md)

### Authorization

[subAccountAuth](../README.md#subAccountAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of email message response objects. |  -  |

