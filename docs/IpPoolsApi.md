# IpPoolsApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createIPPool**](IpPoolsApi.md#createIPPool) | **POST** /account/ippool | Create IPPool |
| [**deleteIPPool**](IpPoolsApi.md#deleteIPPool) | **DELETE** /account/ippool/{ippool_id} | Delete IPPool |
| [**getAllIPPools**](IpPoolsApi.md#getAllIPPools) | **GET** /account/ippool | List IPPools |
| [**getIPPoolById**](IpPoolsApi.md#getIPPoolById) | **GET** /account/ippool/{ippool_id} | Get IPPool |
| [**updateIPPool**](IpPoolsApi.md#updateIPPool) | **PUT** /account/ippool/{ippool_id} | Update IPPool |


<a id="createIPPool"></a>
# **createIPPool**
> IPPool createIPPool(ipPoolCreateRequest)

Create IPPool

Create a new IP pool to organize your sending infrastructure. Pools group IPs and third-party sending providers (TPSPs) for intelligent routing.  **Pool Components:** - **IPs:** Dedicated IP addresses from your account - **TPSPs:** Third-party sending providers (SendGrid, Mailgun, etc.)  **TPSP Types:** | Value | Provider | |-------|----------| | &#x60;0&#x60; | Amazon SES | | &#x60;1&#x60; | SendGrid | | &#x60;2&#x60; | Mailgun | | &#x60;3&#x60; | Custom SMTP | | &#x60;4&#x60; | PostMark | | &#x60;5&#x60; | Gmail |  **Routing Strategies:** - &#x60;0&#x60; &#x3D; Round Robin - Distribute traffic evenly - &#x60;1&#x60; &#x3D; Email Provider - Route by recipient&#39;s mailbox provider - &#x60;2&#x60; &#x3D; Volume Percentage - Split by defined percentages - &#x60;3&#x60; &#x3D; Sending Domain - Route by your from domain  **Use Cases:** - Separate transactional from marketing emails - Route high-volume traffic through TPSPs - Implement provider-specific routing for deliverability - Create backup pools for failover  **Naming Best Practices:** - Use descriptive names: &#x60;Transactional_Orders&#x60;, &#x60;Marketing_Newsletter&#x60; - Include purpose: &#x60;HighPriority_Alerts&#x60;, &#x60;Bulk_Promotions&#x60; 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.IpPoolsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    IpPoolsApi apiInstance = new IpPoolsApi(defaultClient);
    IPPoolCreateRequest ipPoolCreateRequest = new IPPoolCreateRequest(); // IPPoolCreateRequest | 
    try {
      IPPool result = apiInstance.createIPPool(ipPoolCreateRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IpPoolsApi#createIPPool");
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
| **ipPoolCreateRequest** | [**IPPoolCreateRequest**](IPPoolCreateRequest.md)|  | |

### Return type

[**IPPool**](IPPool.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Created IPPool details |  -  |
| **403** | Forbidden, IPPool with the same name already exists |  -  |

<a id="deleteIPPool"></a>
# **deleteIPPool**
> IPPoolDeleteResponse deleteIPPool(ippoolId)

Delete IPPool

Remove an IP pool from your account. This action is irreversible.  **⚠️ Before Deleting:** - Ensure no sub-accounts are actively using this pool - Update any sending configurations that reference this pool - IPs in the pool will become unassigned (not deleted)  **Note:** The default system pool cannot be deleted. 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.IpPoolsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    IpPoolsApi apiInstance = new IpPoolsApi(defaultClient);
    Integer ippoolId = 756; // Integer | The unique ID of the IP pool to delete.
    try {
      IPPoolDeleteResponse result = apiInstance.deleteIPPool(ippoolId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IpPoolsApi#deleteIPPool");
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
| **ippoolId** | **Integer**| The unique ID of the IP pool to delete. | |

### Return type

[**IPPoolDeleteResponse**](IPPoolDeleteResponse.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Confirmation of successful pool deletion. |  -  |
| **404** | IPPool not found or already deleted. |  -  |
| **401** | Unauthorized. Invalid or missing API key. |  -  |

<a id="getAllIPPools"></a>
# **getAllIPPools**
> List&lt;IPPool&gt; getAllIPPools(limit, offset, search)

List IPPools

Retrieve all IP pools configured for your account. IP pools group IPs and third-party sending providers (TPSPs) for intelligent traffic routing.  **Pool Types:** | Type | Value | Description | |------|-------|-------------| | Shared | &#x60;0&#x60; | Pool uses shared IPs (shared with other SendPost customers) | | Dedicated | &#x60;1&#x60; | Pool uses dedicated IPs (exclusive to your account) |  **Routing Strategies:** | Strategy | Value | Description | |----------|-------|-------------| | Round Robin | &#x60;0&#x60; | Distribute traffic evenly across pool members | | Email Provider | &#x60;1&#x60; | Route based on recipient&#39;s mailbox provider (Gmail, Yahoo, etc.) | | Volume Percentage | &#x60;2&#x60; | Split traffic by defined percentages | | Sending Domain | &#x60;3&#x60; | Route based on your sending domain |  **Use Cases:** - Audit your sending infrastructure configuration - View IPs and TPSPs in each pool - Plan routing strategy changes - Verify pool setup before sending campaigns 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.IpPoolsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    IpPoolsApi apiInstance = new IpPoolsApi(defaultClient);
    Integer limit = 20; // Integer | Number of records to return per request. Default 20.
    Integer offset = 0; // Integer | Number of initial records to skip for pagination.
    String search = "Transactional"; // String | Case insensitive search against IP pool names.
    try {
      List<IPPool> result = apiInstance.getAllIPPools(limit, offset, search);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IpPoolsApi#getAllIPPools");
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
| **search** | **String**| Case insensitive search against IP pool names. | [optional] |

### Return type

[**List&lt;IPPool&gt;**](IPPool.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of IPPools |  -  |
| **401** | Unauthorized |  -  |

<a id="getIPPoolById"></a>
# **getIPPoolById**
> IPPool getIPPoolById(ippoolId)

Get IPPool

Retrieve complete details about a specific IP pool, including all IPs and TPSPs assigned to it.  **Response Includes:** - Pool name, ID, and creation date - Complete list of IPs with warmup status - All configured TPSPs with their settings - Current routing strategy and metadata - Warmup and monitoring configuration  **Use Cases:** - Verify pool configuration before sending - Check which IPs/TPSPs are in a pool - Debug routing issues - Audit pool settings for compliance 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.IpPoolsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    IpPoolsApi apiInstance = new IpPoolsApi(defaultClient);
    Integer ippoolId = 74; // Integer | The unique ID of the IP pool to retrieve.
    try {
      IPPool result = apiInstance.getIPPoolById(ippoolId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IpPoolsApi#getIPPoolById");
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
| **ippoolId** | **Integer**| The unique ID of the IP pool to retrieve. | |

### Return type

[**IPPool**](IPPool.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Details of a specific IPPool |  -  |
| **401** | Unauthorized |  -  |

<a id="updateIPPool"></a>
# **updateIPPool**
> IPPool updateIPPool(ipPoolUpdateRequest, ippoolId)

Update IPPool

Modify an existing IP pool&#39;s configuration, including name, IPs, TPSPs, and routing strategy.  **What Can Be Updated:** - Pool name - IP addresses assigned to the pool - Third-party sending providers (TPSPs) - Routing strategy and metadata - Warmup and monitoring settings  **Use Cases:** - Add new IPs to scale capacity - Remove underperforming IPs - Change routing strategy - Add/remove TPSP integrations - Rename pool for clarity  **Best Practices:** - Test routing changes during low-traffic periods - Ensure at least one sending option remains in the pool - Document changes for team awareness 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.IpPoolsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");

    IpPoolsApi apiInstance = new IpPoolsApi(defaultClient);
    IPPoolUpdateRequest ipPoolUpdateRequest = new IPPoolUpdateRequest(); // IPPoolUpdateRequest | 
    Integer ippoolId = 756; // Integer | The unique ID of the IP pool to update.
    try {
      IPPool result = apiInstance.updateIPPool(ipPoolUpdateRequest, ippoolId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IpPoolsApi#updateIPPool");
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
| **ipPoolUpdateRequest** | [**IPPoolUpdateRequest**](IPPoolUpdateRequest.md)|  | |
| **ippoolId** | **Integer**| The unique ID of the IP pool to update. | |

### Return type

[**IPPool**](IPPool.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The updated IPPool details |  -  |
| **400** | Bad request - invalid or missing data |  -  |
| **404** | IPPool not found |  -  |
| **401** | Unauthorized |  -  |

