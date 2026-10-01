# StatsApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**accountSubaccountStatSubaccountIdAggregateGet**](StatsApi.md#accountSubaccountStatSubaccountIdAggregateGet) | **GET** /account/subaccount/stat/{subaccount_id}/aggregate | Get Aggregate Stats |
| [**accountSubaccountStatSubaccountIdGet**](StatsApi.md#accountSubaccountStatSubaccountIdGet) | **GET** /account/subaccount/stat/{subaccount_id} | List Stats |
| [**getAggregateStatsByGroup**](StatsApi.md#getAggregateStatsByGroup) | **GET** /account/subaccount/stat/{subaccount_id}/group | Get Group Aggregate Stats |


<a id="accountSubaccountStatSubaccountIdAggregateGet"></a>
# **accountSubaccountStatSubaccountIdAggregateGet**
> AggregateStat accountSubaccountStatSubaccountIdAggregateGet(from, to, subaccountId)

Get Aggregate Stats

Retrieve summarized email statistics for a sub-account over a date range. Unlike daily stats, this returns a single record with totals across all days—ideal for high-level reporting.  **Response includes total counts for:** - &#x60;processed&#x60; - Total emails submitted - &#x60;delivered&#x60; - Successfully delivered - &#x60;dropped&#x60; - Blocked before sending - &#x60;hardBounced&#x60; / &#x60;softBounced&#x60; - Bounce breakdowns - &#x60;opens&#x60; / &#x60;clicks&#x60; - Engagement totals - &#x60;unsubscribed&#x60; / &#x60;spams&#x60; - Negative signals  **Use Cases:** - Monthly performance reports - Executive dashboards - Billing period summaries - Year-over-year comparisons - SLA compliance reports  **Example:** To get Q1 2024 totals: &#x60;&#x60;&#x60; GET /account/subaccount/stat/11/aggregate?from&#x3D;2024-01-01&amp;to&#x3D;2024-03-31 &#x60;&#x60;&#x60;  **Note:** Maximum date range is 366 days (1 year). 

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
    LocalDate from = LocalDate.parse("2024-01-01"); // LocalDate | Start date for aggregation (inclusive). Format YYYY-MM-DD.
    LocalDate to = LocalDate.parse("2024-03-31"); // LocalDate | End date for aggregation (inclusive). Max 366 days from `from` date.
    Long subaccountId = 11L; // Long | The unique ID of the sub-account.
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
| **from** | **LocalDate**| Start date for aggregation (inclusive). Format YYYY-MM-DD. | |
| **to** | **LocalDate**| End date for aggregation (inclusive). Max 366 days from &#x60;from&#x60; date. | |
| **subaccountId** | **Long**| The unique ID of the sub-account. | |

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
| **200** | Successful response with aggregated statistics. |  -  |

<a id="accountSubaccountStatSubaccountIdGet"></a>
# **accountSubaccountStatSubaccountIdGet**
> List&lt;Stat&gt; accountSubaccountStatSubaccountIdGet(from, to, subaccountId)

List Stats

Retrieve daily email statistics for a specific sub-account. Returns one record per day within the date range, perfect for charting trends and identifying patterns.  **Metrics Per Day:** | Metric | Description | |--------|-------------| | &#x60;processed&#x60; | Emails submitted to SendPost | | &#x60;delivered&#x60; | Successfully delivered to recipient&#39;s mailbox | | &#x60;dropped&#x60; | Blocked before sending (suppressed, invalid, etc.) | | &#x60;hardBounced&#x60; | Permanent failures (invalid address, domain doesn&#39;t exist) | | &#x60;softBounced&#x60; | Temporary failures (mailbox full, server down) | | &#x60;opens&#x60; | Total email opens (includes multiple opens per recipient) | | &#x60;clicks&#x60; | Total link clicks | | &#x60;unsubscribed&#x60; | Recipients who unsubscribed | | &#x60;spams&#x60; | Emails marked as spam by recipients |  **Key Rates to Calculate:** - Delivery Rate &#x3D; &#x60;delivered / processed × 100&#x60; - Open Rate &#x3D; &#x60;opens / delivered × 100&#x60; - Click Rate &#x3D; &#x60;clicks / delivered × 100&#x60; - Bounce Rate &#x3D; &#x60;(hardBounced + softBounced) / processed × 100&#x60;  **Use Cases:** - Daily performance dashboard - Week-over-week trend analysis - Identifying delivery issues early - SLA monitoring and reporting  **Note:** Maximum date range is 31 days. Both &#x60;from&#x60; and &#x60;to&#x60; dates are inclusive. 

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
    LocalDate from = LocalDate.parse("2024-01-01"); // LocalDate | Start date for stats retrieval (inclusive). Format YYYY-MM-DD.
    LocalDate to = LocalDate.parse("2024-01-31"); // LocalDate | End date for stats retrieval (inclusive). Must be after `from` date with max 31 days range.
    Long subaccountId = 11L; // Long | The unique ID of the sub-account to retrieve stats for.
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
| **from** | **LocalDate**| Start date for stats retrieval (inclusive). Format YYYY-MM-DD. | |
| **to** | **LocalDate**| End date for stats retrieval (inclusive). Must be after &#x60;from&#x60; date with max 31 days range. | |
| **subaccountId** | **Long**| The unique ID of the sub-account to retrieve stats for. | |

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

Get Group Aggregate Stats

Retrieve aggregated email statistics filtered by a specific group/tag. Groups are labels you assign when sending emails to categorize and segment your analytics.  **What are Groups?** Groups (also called tags) are strings you attach to emails when sending. They help you: - Track different email types (transactional vs marketing) - Segment by campaign or feature - Compare performance across categories  **Setting Groups When Sending:** &#x60;&#x60;&#x60;json {   \&quot;from\&quot;: { \&quot;email\&quot;: \&quot;orders@shop.com\&quot; },   \&quot;to\&quot;: [{ \&quot;email\&quot;: \&quot;customer@example.com\&quot; }],   \&quot;subject\&quot;: \&quot;Order Confirmation\&quot;,   \&quot;groups\&quot;: [\&quot;order-confirmations\&quot;, \&quot;transactional\&quot;] } &#x60;&#x60;&#x60;  **Use Cases:** - Compare welcome email vs password reset performance - Track marketing campaign performance by campaign ID - Measure transactional vs promotional email metrics - Analyze A/B test results by variant group  **Example:** Get stats for \&quot;welcome-emails\&quot; group: &#x60;&#x60;&#x60; GET /account/subaccount/stat/11/group?group&#x3D;welcome-emails&amp;from&#x3D;2024-01-01&amp;to&#x3D;2024-03-31 &#x60;&#x60;&#x60;  **Note:** Maximum date range is 366 days. 

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
    String group = "order-confirmations"; // String | The group/tag name to filter statistics by. Must match the group name used when sending emails.
    LocalDate from = LocalDate.parse("2024-01-01"); // LocalDate | Start date for aggregation (inclusive). Format YYYY-MM-DD.
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
| **group** | **String**| The group/tag name to filter statistics by. Must match the group name used when sending emails. | |
| **from** | **LocalDate**| Start date for aggregation (inclusive). Format YYYY-MM-DD. | |
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

