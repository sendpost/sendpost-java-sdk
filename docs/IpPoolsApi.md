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

Creates a new IPPool with the specified name, IPs, and third-party sending providers.

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

Delete a specific IPPool based on its ID.

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
    Integer ippoolId = 756; // Integer | The ID of the IPPool to delete
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
| **ippoolId** | **Integer**| The ID of the IPPool to delete | |

### Return type

[**IPPoolDeleteResponse**](IPPoolDeleteResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful deletion of the IPPool |  -  |
| **404** | IPPool not found |  -  |
| **401** | Unauthorized |  -  |

<a id="getAllIPPools"></a>
# **getAllIPPools**
> List&lt;IPPool&gt; getAllIPPools(limit, offset, search)

List IPPools

Retrieves a list of all IPPools and information about all IPs contained in that pool.

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
    Integer limit = 10; // Integer | Number of records to return per request
    Integer offset = 0; // Integer | Number of initial records to skip
    String search = "Transactional"; // String | Case insensitive search against IPPool name
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
| **limit** | **Integer**| Number of records to return per request | [optional] |
| **offset** | **Integer**| Number of initial records to skip | [optional] |
| **search** | **String**| Case insensitive search against IPPool name | [optional] |

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

Retrieves details of a specific IPPool based on its ID.

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
    Integer ippoolId = 74; // Integer | The ID of the IPPool whose information you want to retrieve
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
| **ippoolId** | **Integer**| The ID of the IPPool whose information you want to retrieve | |

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
> IPPool updateIPPool(ippoolId, ipPoolUpdateRequest)

Update IPPool

Update the details of an existing IPPool by its ID.

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
    Integer ippoolId = 756; // Integer | The ID of the IPPool to update
    IPPoolUpdateRequest ipPoolUpdateRequest = new IPPoolUpdateRequest(); // IPPoolUpdateRequest | 
    try {
      IPPool result = apiInstance.updateIPPool(ippoolId, ipPoolUpdateRequest);
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
| **ippoolId** | **Integer**| The ID of the IPPool to update | |
| **ipPoolUpdateRequest** | [**IPPoolUpdateRequest**](IPPoolUpdateRequest.md)|  | |

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

