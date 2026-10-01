# IpApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**allocateNewIp**](IpApi.md#allocateNewIp) | **PUT** /account/ip/allocate | Allocate IP |
| [**deleteIp**](IpApi.md#deleteIp) | **DELETE** /account/ip/{ip_id} | Delete IP |
| [**getAllIps**](IpApi.md#getAllIps) | **GET** /account/ip/ | List IPs |
| [**getSpecificIp**](IpApi.md#getSpecificIp) | **GET** /account/ip/{ip_id} | Get IP |
| [**updateIp**](IpApi.md#updateIp) | **PUT** /account/ip/{ip_id} | Update IP |


<a id="allocateNewIp"></a>
# **allocateNewIp**
> IP allocateNewIp(ipAllocationRequest)

Allocate IP

Request allocation of a new dedicated IP address to your account. New IPs start in warmup state to build sender reputation gradually.  **Warmup Process:** - New IPs have limited daily sending capacity - Volume increases automatically each day while &#x60;autoWarmupEnabled&#x60; is set - Full capacity typically reached after 30-45 days - Consistent, engagement-positive sending accelerates warmup  **When to Allocate New IPs:** - Scaling beyond current IP capacity - Separating different email streams (transactional vs marketing) - Geographic IP requirements - Replacing an IP with poor reputation  **Best Practices:** - Dedicated IPs require consistent volume (10k+ emails/month ideal) - Low volume on dedicated IPs can harm deliverability - Consider shared IPs for low-volume senders 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.IpApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    IpApi apiInstance = new IpApi(defaultClient);
    IPAllocationRequest ipAllocationRequest = new IPAllocationRequest(); // IPAllocationRequest | 
    try {
      IP result = apiInstance.allocateNewIp(ipAllocationRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IpApi#allocateNewIp");
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
| **ipAllocationRequest** | [**IPAllocationRequest**](IPAllocationRequest.md)|  | |

### Return type

[**IP**](IP.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Details of the newly allocated IP, including initial warmup status. |  -  |

<a id="deleteIp"></a>
# **deleteIp**
> IPDeletionResponse deleteIp(ipId)

Delete IP

Remove an IP address from your account. This action is irreversible.  **⚠️ Before Deleting:** - Remove the IP from all IP pools first - Ensure no active sending relies on this IP - Consider impact on overall sending capacity  **Note:** You cannot delete an IP that is currently assigned to an IP pool. 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.IpApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    IpApi apiInstance = new IpApi(defaultClient);
    Integer ipId = 11322; // Integer | The unique ID of the IP resource to delete.
    try {
      IPDeletionResponse result = apiInstance.deleteIp(ipId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IpApi#deleteIp");
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
| **ipId** | **Integer**| The unique ID of the IP resource to delete. | |

### Return type

[**IPDeletionResponse**](IPDeletionResponse.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Confirmation of successful IP deletion. |  -  |

<a id="getAllIps"></a>
# **getAllIps**
> List&lt;IP&gt; getAllIps(limit, offset, search)

List IPs

Retrieve all IP addresses allocated to your account. IPs are the foundation of your sending infrastructure and directly impact deliverability.  **IP Types:** | Type | Value | Description | |------|-------|-------------| | Shared | &#x60;0&#x60; | IP shared with other SendPost senders. Cost-effective, reputation is pooled. | | Dedicated | &#x60;1&#x60; | Exclusive IP for your account. Full control over sender reputation. |  **IP States:** | State | Value | Description | |-------|-------|-------------| | Warmup | &#x60;0&#x60; | New IP building reputation. Volume is limited and gradually increases. | | Normal | &#x60;1&#x60; | Fully warmed IP ready for normal sending volume. |  **Warmup Information:** - &#x60;autoWarmupEnabled&#x60; - Whether SendPost is automatically increasing volume  **Use Cases:** - Monitor IP warmup progress for new IPs - Audit shared vs dedicated IP allocation - Plan IP pool configurations - Check available sending capacity 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.IpApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    IpApi apiInstance = new IpApi(defaultClient);
    Integer limit = 20; // Integer | Number of records to return per request. Default 20.
    Integer offset = 0; // Integer | Number of initial records to skip for pagination.
    String search = "52.34"; // String | Case insensitive search against public IP addresses.
    try {
      List<IP> result = apiInstance.getAllIps(limit, offset, search);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IpApi#getAllIps");
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
| **search** | **String**| Case insensitive search against public IP addresses. | [optional] |

### Return type

[**List&lt;IP&gt;**](IP.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of IPs associated with the account |  -  |

<a id="getSpecificIp"></a>
# **getSpecificIp**
> IP getSpecificIp(ipId)

Get IP

Retrieve detailed information about a specific IP address, including its warmup status, type, and configuration.  **Use Cases:** - Check warmup progress for a new dedicated IP - Verify IP configuration before adding to a pool - Debug deliverability issues by checking IP state - Monitor auto-warmup progress 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.IpApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    IpApi apiInstance = new IpApi(defaultClient);
    Integer ipId = 11322; // Integer | The unique ID of the IP resource to retrieve.
    try {
      IP result = apiInstance.getSpecificIp(ipId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IpApi#getSpecificIp");
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
| **ipId** | **Integer**| The unique ID of the IP resource to retrieve. | |

### Return type

[**IP**](IP.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Detailed information about the specified IP. |  -  |

<a id="updateIp"></a>
# **updateIp**
> IP updateIp(ipUpdateRequest, ipId)

Update IP

Modify settings for an existing IP address. Use this to manage warmup configuration.  **Configurable Settings:** - &#x60;autoWarmupEnabled&#x60; - Enable/disable automatic warmup schedule  **Use Cases:** - Pause auto-warmup during low-volume periods - Re-enable warmup after manual intervention - Adjust warmup settings based on sending patterns 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.IpApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    IpApi apiInstance = new IpApi(defaultClient);
    IPUpdateRequest ipUpdateRequest = new IPUpdateRequest(); // IPUpdateRequest | 
    Integer ipId = 11322; // Integer | The unique ID of the IP resource to update.
    try {
      IP result = apiInstance.updateIp(ipUpdateRequest, ipId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IpApi#updateIp");
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
| **ipUpdateRequest** | [**IPUpdateRequest**](IPUpdateRequest.md)|  | |
| **ipId** | **Integer**| The unique ID of the IP resource to update. | |

### Return type

[**IP**](IP.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The updated IP information. |  -  |

