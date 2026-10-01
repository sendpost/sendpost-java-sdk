# DomainApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createSubAccountDomain**](DomainApi.md#createSubAccountDomain) | **POST** /subaccount/domain | Create Domain |
| [**deleteSubAccountDomain**](DomainApi.md#deleteSubAccountDomain) | **DELETE** /subaccount/domain/{domain_id} | Delete Domain |
| [**getAllDomains**](DomainApi.md#getAllDomains) | **GET** /subaccount/domain | List Domains |
| [**getSubAccountDomain**](DomainApi.md#getSubAccountDomain) | **GET** /subaccount/domain/{domain_id} | Get Domain |


<a id="createSubAccountDomain"></a>
# **createSubAccountDomain**
> Domain createSubAccountDomain(createDomainRequest)

Create Domain

Register a new sending domain with SendPost. After creation, you&#39;ll receive DNS records that must be configured with your DNS provider before you can send emails.  **Domain Setup Process:** 1. Call this endpoint with your domain name 2. Copy the returned DNS records (DKIM, Return-Path, Track, DMARC) 3. Add records to your DNS provider (GoDaddy, Cloudflare, Route53, etc.) 4. Wait for DNS propagation (typically 15 minutes to 48 hours) 5. Verification happens automatically, or trigger manual verification  **DNS Records Explained:** | Record | Purpose | Required | |--------|---------|----------| | DKIM | Cryptographically signs emails to prove authenticity | Yes | | Return-Path | Routes bounce notifications through SendPost | Recommended | | Track | Enables click tracking with your domain | Optional | | DMARC | Adds additional authentication layer | Recommended |  **Best Practices:** - Use a subdomain like &#x60;mail.yourdomain.com&#x60; for sending - Keep your root domain for your website - Configure all records for best deliverability 

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
      Domain result = apiInstance.createSubAccountDomain(createDomainRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DomainApi#createSubAccountDomain");
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

<a id="deleteSubAccountDomain"></a>
# **deleteSubAccountDomain**
> DeleteResponse deleteSubAccountDomain(domainId)

Delete Domain

Remove a sending domain from your sub-account. Once deleted, you can no longer send emails from addresses on this domain.  **Before Deleting:** - Ensure no active email campaigns use this domain - Update sender addresses in your applications - Consider impact on email deliverability  **Note:** DNS records for the domain will become orphaned. You may want to remove them from your DNS provider. 

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
      DeleteResponse result = apiInstance.deleteSubAccountDomain(domainId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DomainApi#deleteSubAccountDomain");
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

<a id="getAllDomains"></a>
# **getAllDomains**
> List&lt;Domain&gt; getAllDomains(limit, offset, search)

List Domains

Retrieve all sending domains configured for this sub-account. Use this endpoint to audit your domain setup, check verification status, or retrieve DNS records for configuration.  **Each domain record includes:** - Domain name and unique ID - DNS records to configure (DKIM, Return-Path, Track, DMARC) - Verification status for each record type - Failure messages for troubleshooting - Creation timestamp  **Verification Status Values:** - &#x60;true&#x60; - Record verified and active - &#x60;false&#x60; - Record not verified (check DNS configuration)  **Use Cases:** - Audit all configured sending domains - Export DNS records for documentation - Identify domains pending verification - Monitor domain health across multiple domains  **Pagination:** Use &#x60;limit&#x60; and &#x60;offset&#x60; for large domain lists. 

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
    Integer limit = 20; // Integer | Number of records to return per request. Default is 20.
    Integer offset = 0; // Integer | Number of initial records to skip for pagination.
    String search = "mycompany"; // String | Case insensitive search against domain names. Useful for finding specific domains in large lists.
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
| **limit** | **Integer**| Number of records to return per request. Default is 20. | [optional] [default to 20] |
| **offset** | **Integer**| Number of initial records to skip for pagination. | [optional] [default to 0] |
| **search** | **String**| Case insensitive search against domain names. Useful for finding specific domains in large lists. | [optional] |

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

<a id="getSubAccountDomain"></a>
# **getSubAccountDomain**
> Domain getSubAccountDomain(domainId)

Get Domain

Retrieve detailed information about a specific sending domain, including DNS record configuration and verification status.  **Response includes:** - Domain name and ID - DKIM, Return-Path, Track, and DMARC DNS records to configure - Verification status for each record type - Failure reasons if verification failed - Domain registration date  **Use Cases:** - Check verification status before sending emails - Retrieve DNS records during domain setup - Debug DNS configuration issues - Audit domain settings for compliance 

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
      Domain result = apiInstance.getSubAccountDomain(domainId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DomainApi#getSubAccountDomain");
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

