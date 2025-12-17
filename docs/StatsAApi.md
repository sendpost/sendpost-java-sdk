# StatsAApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAccountAggregateStats**](StatsAApi.md#getAccountAggregateStats) | **GET** /account/stat/aggregate | Get Account Aggregate Stats |
| [**getAccountAggregateStatsByGroup**](StatsAApi.md#getAccountAggregateStatsByGroup) | **GET** /account/stat/aggregate/group | Get Account Aggregate Stats by Group |
| [**getAccountStatsByGroup**](StatsAApi.md#getAccountStatsByGroup) | **GET** /account/stat/group | Get All Account Stats By Group |
| [**getAllAccountStats**](StatsAApi.md#getAllAccountStats) | **GET** /account/stat | Get All Account Stats |


<a id="getAccountAggregateStats"></a>
# **getAccountAggregateStats**
> AggregateStats getAccountAggregateStats(from, to)

Get Account Aggregate Stats

Retrieve aggregated email statistics for all sub-accounts of a specific account for a given date range.

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.StatsAApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    StatsAApi apiInstance = new StatsAApi(defaultClient);
    LocalDate from = LocalDate.parse("2019-01-01"); // LocalDate | The start date for retrieving aggregated stats (inclusive)
    LocalDate to = LocalDate.parse("2019-12-31"); // LocalDate | The end date for retrieving aggregated stats (inclusive). The difference between `from` and `to` should not exceed 366 days.
    try {
      AggregateStats result = apiInstance.getAccountAggregateStats(from, to);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatsAApi#getAccountAggregateStats");
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
| **from** | **LocalDate**| The start date for retrieving aggregated stats (inclusive) | |
| **to** | **LocalDate**| The end date for retrieving aggregated stats (inclusive). The difference between &#x60;from&#x60; and &#x60;to&#x60; should not exceed 366 days. | |

### Return type

[**AggregateStats**](AggregateStats.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Aggregated statistics for the specified date range. |  -  |
| **400** | Bad request, invalid date range |  -  |
| **401** | Unauthorized, invalid API key |  -  |
| **404** | Data not found for the given date range |  -  |

<a id="getAccountAggregateStatsByGroup"></a>
# **getAccountAggregateStatsByGroup**
> AggregateStat getAccountAggregateStatsByGroup(group, from, to)

Get Account Aggregate Stats by Group

Gets aggregated email stats for a specific group in all sub-accounts of a specific account for the given daterange. The maximum daterange for which these stats can be retrieved is 366 days.

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.StatsAApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    StatsAApi apiInstance = new StatsAApi(defaultClient);
    String group = "shopify"; // String | Group whose aggregate stats need to be retrieved.
    LocalDate from = LocalDate.parse("2019-01-01"); // LocalDate | Date from which stats should be retrieved (should be in the format `YYYY-MM-DD`).
    LocalDate to = LocalDate.parse("2019-12-31"); // LocalDate | Date to which stats should be retrieved (should be in the format `YYYY-MM-DD`). Note that the difference between `from` and `to` should not be more than 366 days.
    try {
      AggregateStat result = apiInstance.getAccountAggregateStatsByGroup(group, from, to);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatsAApi#getAccountAggregateStatsByGroup");
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
| **group** | **String**| Group whose aggregate stats need to be retrieved. | |
| **from** | **LocalDate**| Date from which stats should be retrieved (should be in the format &#x60;YYYY-MM-DD&#x60;). | |
| **to** | **LocalDate**| Date to which stats should be retrieved (should be in the format &#x60;YYYY-MM-DD&#x60;). Note that the difference between &#x60;from&#x60; and &#x60;to&#x60; should not be more than 366 days. | |

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
| **200** | Successfully retrieved aggregate stats by group. |  -  |
| **400** | Bad Request, invalid query parameters. |  -  |
| **401** | Unauthorized, invalid API key. |  -  |

<a id="getAccountStatsByGroup"></a>
# **getAccountStatsByGroup**
> List&lt;Stat&gt; getAccountStatsByGroup(group, from, to)

Get All Account Stats By Group

Gets a list of all email stats for all sub-accounts of a specific account by group for a given daterange. The maximum daterange for which these stats can be retrieved is 31 days.

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.StatsAApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    StatsAApi apiInstance = new StatsAApi(defaultClient);
    String group = "shopify"; // String | Group whose stats need to be retrieved
    LocalDate from = LocalDate.parse("2020-03-12"); // LocalDate | Date from which stats should be retrieved (should be in the format `YYYY-MM-DD`)
    LocalDate to = LocalDate.parse("2020-04-14"); // LocalDate | Date to which stats should be retrieved (should be in the format `YYYY-MM-DD`). Note that the difference between `from` and `to` should not be more than 31 days.
    try {
      List<Stat> result = apiInstance.getAccountStatsByGroup(group, from, to);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatsAApi#getAccountStatsByGroup");
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
| **group** | **String**| Group whose stats need to be retrieved | |
| **from** | **LocalDate**| Date from which stats should be retrieved (should be in the format &#x60;YYYY-MM-DD&#x60;) | |
| **to** | **LocalDate**| Date to which stats should be retrieved (should be in the format &#x60;YYYY-MM-DD&#x60;). Note that the difference between &#x60;from&#x60; and &#x60;to&#x60; should not be more than 31 days. | |

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
| **200** | Successfully retrieved stats by group. |  -  |
| **400** | Bad Request, invalid query parameters. |  -  |
| **401** | Unauthorized, invalid API key. |  -  |

<a id="getAllAccountStats"></a>
# **getAllAccountStats**
> List&lt;AccountStats&gt; getAllAccountStats(from, to)

Get All Account Stats

Retrieve email statistics for all sub-accounts of a specific account for a given date range.

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.StatsAApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    StatsAApi apiInstance = new StatsAApi(defaultClient);
    LocalDate from = LocalDate.parse("2020-03-12"); // LocalDate | The start date for retrieving stats (inclusive)
    LocalDate to = LocalDate.parse("2020-04-14"); // LocalDate | The end date for retrieving stats (inclusive). The difference between `from` and `to` should not exceed 31 days.
    try {
      List<AccountStats> result = apiInstance.getAllAccountStats(from, to);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatsAApi#getAllAccountStats");
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
| **from** | **LocalDate**| The start date for retrieving stats (inclusive) | |
| **to** | **LocalDate**| The end date for retrieving stats (inclusive). The difference between &#x60;from&#x60; and &#x60;to&#x60; should not exceed 31 days. | |

### Return type

[**List&lt;AccountStats&gt;**](AccountStats.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of statistics for the specified date range. |  -  |
| **400** | Bad request, invalid date range |  -  |
| **401** | Unauthorized, invalid API key |  -  |
| **404** | Data not found for the given date range |  -  |

