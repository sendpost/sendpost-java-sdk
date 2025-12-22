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

Allocates a new IP resource to the account. 

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
| **200** | Details of the allocated IP |  -  |

<a id="deleteIp"></a>
# **deleteIp**
> IPDeletionResponse deleteIp(ipId)

Delete IP

Deletes a specific IP resource based on the provided IP ID. 

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
    Integer ipId = 56; // Integer | The ID of the IP resource to delete
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
| **ipId** | **Integer**| The ID of the IP resource to delete | |

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
| **200** | Confirmation message after successful IP deletion |  -  |

<a id="getAllIps"></a>
# **getAllIps**
> List&lt;IP&gt; getAllIps(limit, offset, search)

List IPs

Retrieves a list of all IPs associated with the main account. 

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
    Integer limit = 56; // Integer | Number of records to return per request
    Integer offset = 56; // Integer | Number of initial records to skip
    String search = "search_example"; // String | Case insensitive search against IP's public IP address
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
| **limit** | **Integer**| Number of records to return per request | [optional] |
| **offset** | **Integer**| Number of initial records to skip | [optional] |
| **search** | **String**| Case insensitive search against IP&#39;s public IP address | [optional] |

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

Retrieves detailed information about a specific IP based on the provided ID. 

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
    Integer ipId = 56; // Integer | The ID of the IP resource to retrieve
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
| **ipId** | **Integer**| The ID of the IP resource to retrieve | |

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
| **200** | Information about the specified IP |  -  |

<a id="updateIp"></a>
# **updateIp**
> IP updateIp(ipId, ipUpdateRequest)

Update IP

Updates an existing IP resource based on the provided IP ID. 

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
    Integer ipId = 56; // Integer | The ID of the IP resource to update
    IPUpdateRequest ipUpdateRequest = new IPUpdateRequest(); // IPUpdateRequest | 
    try {
      IP result = apiInstance.updateIp(ipId, ipUpdateRequest);
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
| **ipId** | **Integer**| The ID of the IP resource to update | |
| **ipUpdateRequest** | [**IPUpdateRequest**](IPUpdateRequest.md)|  | |

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
| **200** | The updated IP information |  -  |

