# DomainApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAllDomains**](DomainApi.md#getAllDomains) | **GET** /subaccount/domain | Get All Domains |
| [**subaccountDomainDomainIdDelete**](DomainApi.md#subaccountDomainDomainIdDelete) | **DELETE** /subaccount/domain/{domain_id} | Delete a specific domain |
| [**subaccountDomainDomainIdGet**](DomainApi.md#subaccountDomainDomainIdGet) | **GET** /subaccount/domain/{domain_id} | Get a specific domain |
| [**subaccountDomainPost**](DomainApi.md#subaccountDomainPost) | **POST** /subaccount/domain | Create a new domain |


<a id="getAllDomains"></a>
# **getAllDomains**
> List&lt;Domain&gt; getAllDomains(limit, offset, search)

Get All Domains

Retrieve a list of all domains associated with the sub-account, including their DNS records and authentication status. 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.DomainApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: subAccountAuth
    ApiKeyAuth subAccountAuth = (ApiKeyAuth) defaultClient.getAuthentication("subAccountAuth");
    subAccountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //subAccountAuth.setApiKeyPrefix("Token");

    DomainApi apiInstance = new DomainApi(defaultClient);
    Integer limit = 56; // Integer | Number of records to return per request
    Integer offset = 56; // Integer | Number of initial records to skip
    String search = "search_example"; // String | Case insensitive search against domain names
    try {
      List<Domain> result = apiInstance.getAllDomains(limit, offset, search);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DomainApi#getAllDomains");
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
| **search** | **String**| Case insensitive search against domain names | [optional] |

### Return type

[**List&lt;Domain&gt;**](Domain.md)

### Authorization

[subAccountAuth](../README.md#subAccountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | List of domains retrieved successfully |  -  |

<a id="subaccountDomainDomainIdDelete"></a>
# **subaccountDomainDomainIdDelete**
> DeleteResponse subaccountDomainDomainIdDelete(domainId)

Delete a specific domain

Delete a specific domain using its &#x60;id&#x60;.

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.DomainApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: subAccountAuth
    ApiKeyAuth subAccountAuth = (ApiKeyAuth) defaultClient.getAuthentication("subAccountAuth");
    subAccountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //subAccountAuth.setApiKeyPrefix("Token");

    DomainApi apiInstance = new DomainApi(defaultClient);
    String domainId = "domainId_example"; // String | The unique ID of the domain to delete.
    try {
      DeleteResponse result = apiInstance.subaccountDomainDomainIdDelete(domainId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DomainApi#subaccountDomainDomainIdDelete");
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
| **domainId** | **String**| The unique ID of the domain to delete. | |

### Return type

[**DeleteResponse**](DeleteResponse.md)

### Authorization

[subAccountAuth](../README.md#subAccountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful response |  -  |

<a id="subaccountDomainDomainIdGet"></a>
# **subaccountDomainDomainIdGet**
> Domain subaccountDomainDomainIdGet(domainId)

Get a specific domain

Retrieve details of a specific domain using its &#x60;id&#x60;.

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.DomainApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: subAccountAuth
    ApiKeyAuth subAccountAuth = (ApiKeyAuth) defaultClient.getAuthentication("subAccountAuth");
    subAccountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //subAccountAuth.setApiKeyPrefix("Token");

    DomainApi apiInstance = new DomainApi(defaultClient);
    String domainId = "domainId_example"; // String | The unique ID of the domain to retrieve.
    try {
      Domain result = apiInstance.subaccountDomainDomainIdGet(domainId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DomainApi#subaccountDomainDomainIdGet");
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
| **domainId** | **String**| The unique ID of the domain to retrieve. | |

### Return type

[**Domain**](Domain.md)

### Authorization

[subAccountAuth](../README.md#subAccountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful response |  -  |

<a id="subaccountDomainPost"></a>
# **subaccountDomainPost**
> Domain subaccountDomainPost(createDomainRequest)

Create a new domain

Add a new domain using its &#x60;name&#x60;.

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.DomainApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: subAccountAuth
    ApiKeyAuth subAccountAuth = (ApiKeyAuth) defaultClient.getAuthentication("subAccountAuth");
    subAccountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //subAccountAuth.setApiKeyPrefix("Token");

    DomainApi apiInstance = new DomainApi(defaultClient);
    CreateDomainRequest createDomainRequest = new CreateDomainRequest(); // CreateDomainRequest | 
    try {
      Domain result = apiInstance.subaccountDomainPost(createDomainRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DomainApi#subaccountDomainPost");
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
| **createDomainRequest** | [**CreateDomainRequest**](CreateDomainRequest.md)|  | |

### Return type

[**Domain**](Domain.md)

### Authorization

[subAccountAuth](../README.md#subAccountAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Successful response |  -  |

