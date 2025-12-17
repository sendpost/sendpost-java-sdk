# StatsApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**accountSubaccountStatSubaccountIdAggregateGet**](StatsApi.md#accountSubaccountStatSubaccountIdAggregateGet) | **GET** /account/subaccount/stat/{subaccount_id}/aggregate | Get Aggregate Stats |
| [**accountSubaccountStatSubaccountIdGet**](StatsApi.md#accountSubaccountStatSubaccountIdGet) | **GET** /account/subaccount/stat/{subaccount_id} | Get All Stats |
| [**getAggregateStatsByGroup**](StatsApi.md#getAggregateStatsByGroup) | **GET** /account/subaccount/stat/{subaccount_id}/group | Get aggregated stats by group |


<a id="accountSubaccountStatSubaccountIdAggregateGet"></a>
# **accountSubaccountStatSubaccountIdAggregateGet**
> AggregateStat accountSubaccountStatSubaccountIdAggregateGet(from, to, subaccountId)

Get Aggregate Stats

Retrieves aggregated email stats for a specific sub-account for a date range.   **Note**: The maximum date range is 366 days. 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.StatsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    StatsApi apiInstance = new StatsApi(defaultClient);
    LocalDate from = LocalDate.now(); // LocalDate | Start date for stats retrieval.
    LocalDate to = LocalDate.now(); // LocalDate | Date to which stats should be retrieved ( Note than from date should be earlier than to date. Also the difference between from and to date shouldn't ne more than 60 days ) 
    Long subaccountId = 11L; // Long | The ID of the subaccount to retrieve
    try {
      AggregateStat result = apiInstance.accountSubaccountStatSubaccountIdAggregateGet(from, to, subaccountId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatsApi#accountSubaccountStatSubaccountIdAggregateGet");
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
| **from** | **LocalDate**| Start date for stats retrieval. | |
| **to** | **LocalDate**| Date to which stats should be retrieved ( Note than from date should be earlier than to date. Also the difference between from and to date shouldn&#39;t ne more than 60 days )  | |
| **subaccountId** | **Long**| The ID of the subaccount to retrieve | |

### Return type

[**AggregateStat**](AggregateStat.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful response |  -  |

<a id="accountSubaccountStatSubaccountIdGet"></a>
# **accountSubaccountStatSubaccountIdGet**
> List&lt;Stat&gt; accountSubaccountStatSubaccountIdGet(from, to, subaccountId)

Get All Stats

Retrieves a list of email stats for a specific sub-account within a given date range. Both &#x60;from&#x60; and &#x60;to&#x60; dates are inclusive.   **Note**: The maximum date range is 31 days. 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.StatsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    StatsApi apiInstance = new StatsApi(defaultClient);
    LocalDate from = LocalDate.now(); // LocalDate | Start date for stats retrieval.
    LocalDate to = LocalDate.now(); // LocalDate | Date to which stats should be retrieved ( Note than from date should be earlier than to date. Also the difference between from and to date shouldn't ne more than 60 days ) 
    Long subaccountId = 11L; // Long | The ID of the subaccount to retrieve
    try {
      List<Stat> result = apiInstance.accountSubaccountStatSubaccountIdGet(from, to, subaccountId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatsApi#accountSubaccountStatSubaccountIdGet");
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
| **from** | **LocalDate**| Start date for stats retrieval. | |
| **to** | **LocalDate**| Date to which stats should be retrieved ( Note than from date should be earlier than to date. Also the difference between from and to date shouldn&#39;t ne more than 60 days )  | |
| **subaccountId** | **Long**| The ID of the subaccount to retrieve | |

### Return type

[**List&lt;Stat&gt;**](Stat.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful response |  -  |

<a id="getAggregateStatsByGroup"></a>
# **getAggregateStatsByGroup**
> AggregateStat getAggregateStatsByGroup(group, from, to, subaccountId)

Get aggregated stats by group

Retrieves aggregated email stats for a specific group in a sub-account for the specified daterange. The maximum daterange for which these stats can be retrieved is 366 days. Ensure that the difference between the &#x60;from&#x60; and &#x60;to&#x60; dates does not exceed 366 days. 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.StatsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    StatsApi apiInstance = new StatsApi(defaultClient);
    String group = "group_example"; // String | Group whose aggregated stats need to be retrieved
    LocalDate from = LocalDate.now(); // LocalDate | The starting date for the aggregated stats
    LocalDate to = LocalDate.now(); // LocalDate | The ending date for the aggregated stats (Note: `from` should be earlier than `to` and the date range should not exceed 366 days) 
    Long subaccountId = 11L; // Long | The ID of the subaccount to retrieve
    try {
      AggregateStat result = apiInstance.getAggregateStatsByGroup(group, from, to, subaccountId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatsApi#getAggregateStatsByGroup");
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
| **group** | **String**| Group whose aggregated stats need to be retrieved | |
| **from** | **LocalDate**| The starting date for the aggregated stats | |
| **to** | **LocalDate**| The ending date for the aggregated stats (Note: &#x60;from&#x60; should be earlier than &#x60;to&#x60; and the date range should not exceed 366 days)  | |
| **subaccountId** | **Long**| The ID of the subaccount to retrieve | |

### Return type

[**AggregateStat**](AggregateStat.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Aggregated email stats for the group within the specified date range |  -  |

