# StatsAApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAccountAggregateStats**](StatsAApi.md#getAccountAggregateStats) | **GET** /account/stat/aggregate | Get Account Aggregate Stats |
| [**getAccountAggregateStatsByGroup**](StatsAApi.md#getAccountAggregateStatsByGroup) | **GET** /account/stat/aggregate/group | Get Account Group Aggregate Stats |
| [**getAccountStatsByGroup**](StatsAApi.md#getAccountStatsByGroup) | **GET** /account/stat/group | List Account Group Stats |
| [**getAllAccountStats**](StatsAApi.md#getAllAccountStats) | **GET** /account/stat | List Account Stats |


<a id="getAccountAggregateStats"></a>
# **getAccountAggregateStats**
> AggregateStats getAccountAggregateStats(from, to)

Get Account Aggregate Stats

Retrieve summarized email statistics across all sub-accounts for a date range. Returns a single aggregated record—perfect for high-level reporting and dashboards.  **Use Cases:** - Annual email program review - Quarterly business reports - Month-over-month comparison - Board-level metrics - ROI calculations for email program  **Example:** Get full year stats for 2024: &#x60;&#x60;&#x60; GET /account/stat/aggregate?from&#x3D;2024-01-01&amp;to&#x3D;2024-12-31 &#x60;&#x60;&#x60;  **Note:** Maximum date range is 366 days (1 year). 

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
    LocalDate from = LocalDate.parse("2024-01-01"); // LocalDate | Start date for aggregation (inclusive). Format YYYY-MM-DD.
    LocalDate to = LocalDate.parse("2024-12-31"); // LocalDate | End date for aggregation (inclusive). Max 366 days from `from` date.
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
| **from** | **LocalDate**| Start date for aggregation (inclusive). Format YYYY-MM-DD. | |
| **to** | **LocalDate**| End date for aggregation (inclusive). Max 366 days from &#x60;from&#x60; date. | |

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

Get Account Group Aggregate Stats

Retrieve summarized email statistics for a specific group across all sub-accounts. Returns a single aggregated record for the group—ideal for campaign reporting.  **Use Cases:** - Annual performance report for a specific product integration - Compare total metrics for different campaigns - Summarize email performance for a specific customer segment - Calculate ROI for a marketing campaign by group  **Example:** Get yearly stats for Shopify integration: &#x60;&#x60;&#x60; GET /account/stat/aggregate/group?group&#x3D;shopify&amp;from&#x3D;2024-01-01&amp;to&#x3D;2024-12-31 &#x60;&#x60;&#x60;  **Note:** Maximum date range is 366 days (1 year). 

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
    String group = "shopify"; // String | The group/tag name to filter and aggregate statistics by.
    LocalDate from = LocalDate.parse("2024-01-01"); // LocalDate | Start date for aggregation (inclusive). Format YYYY-MM-DD.
    LocalDate to = LocalDate.parse("2024-12-31"); // LocalDate | End date for aggregation (inclusive). Max 366 days from `from` date.
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
| **group** | **String**| The group/tag name to filter and aggregate statistics by. | |
| **from** | **LocalDate**| Start date for aggregation (inclusive). Format YYYY-MM-DD. | |
| **to** | **LocalDate**| End date for aggregation (inclusive). Max 366 days from &#x60;from&#x60; date. | |

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

List Account Group Stats

Retrieve daily email statistics for a specific group across all sub-accounts. Returns one record per day, filtered by the group/tag you specify.  **What are Groups?** Groups (tags) are labels attached to emails when sending. They enable segmented analytics across your entire account.  **Common Group Strategies:** | Strategy | Example Groups | |----------|----------------| | By Product | &#x60;shopify&#x60;, &#x60;wordpress&#x60;, &#x60;api-direct&#x60; | | By Type | &#x60;transactional&#x60;, &#x60;marketing&#x60;, &#x60;alerts&#x60; | | By Team | &#x60;sales-team&#x60;, &#x60;support&#x60;, &#x60;engineering&#x60; | | By Campaign | &#x60;black-friday-2024&#x60;, &#x60;summer-sale&#x60; |  **Use Cases:** - Compare performance across products/integrations - Track specific campaign performance account-wide - Analyze transactional vs marketing metrics - Benchmark different teams&#39; email performance  **Note:** Maximum date range is 31 days. 

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
    String group = "shopify"; // String | The group/tag name to filter statistics by.
    LocalDate from = LocalDate.parse("2024-01-01"); // LocalDate | Start date for stats retrieval (inclusive). Format YYYY-MM-DD.
    LocalDate to = LocalDate.parse("2024-01-31"); // LocalDate | End date for stats retrieval (inclusive). Max 31 days from `from` date.
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
| **group** | **String**| The group/tag name to filter statistics by. | |
| **from** | **LocalDate**| Start date for stats retrieval (inclusive). Format YYYY-MM-DD. | |
| **to** | **LocalDate**| End date for stats retrieval (inclusive). Max 31 days from &#x60;from&#x60; date. | |

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

List Account Stats

Retrieve daily email statistics aggregated across all sub-accounts. Returns one record per day within the date range—ideal for organization-wide reporting.  **Metrics Per Day:** | Metric | Description | |--------|-------------| | &#x60;processed&#x60; | Total emails submitted across all sub-accounts | | &#x60;delivered&#x60; | Successfully delivered to recipients | | &#x60;dropped&#x60; | Blocked before sending | | &#x60;hardBounced&#x60; | Permanent delivery failures | | &#x60;softBounced&#x60; | Temporary delivery failures | | &#x60;opens&#x60; | Total email opens | | &#x60;clicks&#x60; | Total link clicks | | &#x60;unsubscribed&#x60; | Recipients who unsubscribed | | &#x60;spams&#x60; | Spam complaints received |  **Use Cases:** - Organization-wide email performance dashboard - Billing and usage tracking across all sub-accounts - Executive reporting for email program health - Trend analysis across your entire email operation  **Note:** Maximum date range is 31 days. 

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
    LocalDate from = LocalDate.parse("2024-01-01"); // LocalDate | Start date for stats retrieval (inclusive). Format YYYY-MM-DD.
    LocalDate to = LocalDate.parse("2024-01-31"); // LocalDate | End date for stats retrieval (inclusive). Max 31 days from `from` date.
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
| **from** | **LocalDate**| Start date for stats retrieval (inclusive). Format YYYY-MM-DD. | |
| **to** | **LocalDate**| End date for stats retrieval (inclusive). Max 31 days from &#x60;from&#x60; date. | |

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

