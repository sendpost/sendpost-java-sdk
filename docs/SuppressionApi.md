# SuppressionApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createSuppression**](SuppressionApi.md#createSuppression) | **POST** /subaccount/suppression | Create new suppressions |
| [**deleteSuppression**](SuppressionApi.md#deleteSuppression) | **DELETE** /subaccount/suppression | Delete suppressions |
| [**getSuppressionList**](SuppressionApi.md#getSuppressionList) | **GET** /subaccount/suppression | Get all suppressions |


<a id="createSuppression"></a>
# **createSuppression**
> List&lt;Suppression&gt; createSuppression(createSuppressionRequest)

Create new suppressions

Creates new suppressions by posting to the suppression resource. You can specify different types of suppressions including &#x60;hardBounce&#x60;, &#x60;manual&#x60;, &#x60;unsubscribe&#x60;, and &#x60;spamComplaint&#x60;. 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.SuppressionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: subAccountAuth
    ApiKeyAuth subAccountAuth = (ApiKeyAuth) defaultClient.getAuthentication("subAccountAuth");
    subAccountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //subAccountAuth.setApiKeyPrefix("Token");

    SuppressionApi apiInstance = new SuppressionApi(defaultClient);
    CreateSuppressionRequest createSuppressionRequest = new CreateSuppressionRequest(); // CreateSuppressionRequest | 
    try {
      List<Suppression> result = apiInstance.createSuppression(createSuppressionRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SuppressionApi#createSuppression");
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
| **createSuppressionRequest** | [**CreateSuppressionRequest**](CreateSuppressionRequest.md)|  | |

### Return type

[**List&lt;Suppression&gt;**](Suppression.md)

### Authorization

[subAccountAuth](../README.md#subAccountAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of created suppressions |  -  |

<a id="deleteSuppression"></a>
# **deleteSuppression**
> DeleteSuppression200Response deleteSuppression(deleteSuppressionRequest)

Delete suppressions

Deletes one or more suppressions for a given sub-account. The request can contain a list of emails to delete specific suppressions or delete a single suppression. 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.SuppressionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: subAccountAuth
    ApiKeyAuth subAccountAuth = (ApiKeyAuth) defaultClient.getAuthentication("subAccountAuth");
    subAccountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //subAccountAuth.setApiKeyPrefix("Token");

    SuppressionApi apiInstance = new SuppressionApi(defaultClient);
    DeleteSuppressionRequest deleteSuppressionRequest = new DeleteSuppressionRequest(); // DeleteSuppressionRequest | 
    try {
      DeleteSuppression200Response result = apiInstance.deleteSuppression(deleteSuppressionRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SuppressionApi#deleteSuppression");
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
| **deleteSuppressionRequest** | [**DeleteSuppressionRequest**](DeleteSuppressionRequest.md)|  | |

### Return type

[**DeleteSuppression200Response**](DeleteSuppression200Response.md)

### Authorization

[subAccountAuth](../README.md#subAccountAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A message indicating successful suppression deletion |  -  |

<a id="getSuppressionList"></a>
# **getSuppressionList**
> List&lt;Suppression&gt; getSuppressionList(from, to, limit, offset, search, type)

Get all suppressions

Retrieves a list of suppressions associated with a specific sub-account within a given date range. The maximum difference between &#x60;from&#x60; and &#x60;to&#x60; dates should not exceed 60 days. 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.SuppressionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: subAccountAuth
    ApiKeyAuth subAccountAuth = (ApiKeyAuth) defaultClient.getAuthentication("subAccountAuth");
    subAccountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //subAccountAuth.setApiKeyPrefix("Token");

    SuppressionApi apiInstance = new SuppressionApi(defaultClient);
    LocalDate from = LocalDate.now(); // LocalDate | Start date for the suppression records
    LocalDate to = LocalDate.now(); // LocalDate | End date for the suppression records (Note: `from` should be earlier than `to` and the date range should not exceed 60 days) 
    Integer limit = 20; // Integer | Number of records to return per request
    Integer offset = 0; // Integer | Number of initial records to skip
    String search = "search_example"; // String | Case-insensitive search against suppression email
    String type = "hardBounce"; // String | Type of suppression. Valid values: `hardBounce`, `manual`, `spamComplaint`, `unsubscribe` 
    try {
      List<Suppression> result = apiInstance.getSuppressionList(from, to, limit, offset, search, type);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SuppressionApi#getSuppressionList");
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
| **from** | **LocalDate**| Start date for the suppression records | |
| **to** | **LocalDate**| End date for the suppression records (Note: &#x60;from&#x60; should be earlier than &#x60;to&#x60; and the date range should not exceed 60 days)  | |
| **limit** | **Integer**| Number of records to return per request | [optional] [default to 20] |
| **offset** | **Integer**| Number of initial records to skip | [optional] [default to 0] |
| **search** | **String**| Case-insensitive search against suppression email | [optional] |
| **type** | **String**| Type of suppression. Valid values: &#x60;hardBounce&#x60;, &#x60;manual&#x60;, &#x60;spamComplaint&#x60;, &#x60;unsubscribe&#x60;  | [optional] [enum: hardBounce, manual, spamComplaint, unsubscribe] |

### Return type

[**List&lt;Suppression&gt;**](Suppression.md)

### Authorization

[subAccountAuth](../README.md#subAccountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of suppressions |  -  |

