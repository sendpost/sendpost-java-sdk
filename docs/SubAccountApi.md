# SubAccountApi

All URIs are relative to *https://api.sendpost.io/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createSubAccount**](SubAccountApi.md#createSubAccount) | **POST** /account/subaccount/ | Create Sub-Account |
| [**deleteSubAccount**](SubAccountApi.md#deleteSubAccount) | **DELETE** /account/subaccount/{subaccount_id} | Delete Sub-Account |
| [**getAllSubAccounts**](SubAccountApi.md#getAllSubAccounts) | **GET** /account/subaccount/ | List Sub-Accounts |
| [**getSubAccount**](SubAccountApi.md#getSubAccount) | **GET** /account/subaccount/{subaccount_id} | Get Sub-Account |
| [**updateSubAccount**](SubAccountApi.md#updateSubAccount) | **PUT** /account/subaccount/{subaccount_id} | Update Sub-Account |


<a id="createSubAccount"></a>
# **createSubAccount**
> SubAccount createSubAccount(newSubAccount)

Create Sub-Account

Create a new sub-account to segment your email sending. Each sub-account gets its own API key, suppression list, and statistics.  **What You Get:** - Unique &#x60;X-SubAccount-ApiKey&#x60; for authentication - Isolated email statistics - Separate suppression management - Independent domain configuration - Optional SMTP credentials  **Naming Best Practices:** - Use descriptive names: &#x60;Transactional_Orders&#x60;, &#x60;Marketing_Newsletter&#x60; - Include environment: &#x60;Production_Alerts&#x60;, &#x60;Staging_Tests&#x60; - For multi-tenant: &#x60;Client_CompanyName&#x60;  **Use Cases:** - New application or microservice needing email - Onboarding a new client in multi-tenant setup - Creating isolated testing environment - Separating email streams for analytics 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.SubAccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    SubAccountApi apiInstance = new SubAccountApi(defaultClient);
    NewSubAccount newSubAccount = new NewSubAccount(); // NewSubAccount | 
    try {
      SubAccount result = apiInstance.createSubAccount(newSubAccount);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SubAccountApi#createSubAccount");
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
| **newSubAccount** | [**NewSubAccount**](NewSubAccount.md)|  | |

### Return type

[**SubAccount**](SubAccount.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Sub-account created successfully with API key. |  -  |
| **403** | Forbidden. Sub-account with the same name already exists. |  -  |
| **401** | Unauthorized. Invalid or missing API key. |  -  |

<a id="deleteSubAccount"></a>
# **deleteSubAccount**
> DeleteSubAccountResponse deleteSubAccount(subaccountId)

Delete Sub-Account

Remove a sub-account from your organization. This action is irreversible.  **⚠️ Before Deleting:** - Export any needed statistics or suppression lists - Update applications using this sub-account&#39;s API key - Ensure no active email sending relies on this sub-account  **What Gets Deleted:** - All sub-account configuration - Associated API keys (will stop working) - Statistics are retained for your account records  **Note:** The default sub-account (type &#x60;0&#x60;) cannot be deleted. 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.SubAccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    SubAccountApi apiInstance = new SubAccountApi(defaultClient);
    Integer subaccountId = 12; // Integer | The unique ID of the sub-account to delete.
    try {
      DeleteSubAccountResponse result = apiInstance.deleteSubAccount(subaccountId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SubAccountApi#deleteSubAccount");
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
| **subaccountId** | **Integer**| The unique ID of the sub-account to delete. | |

### Return type

[**DeleteSubAccountResponse**](DeleteSubAccountResponse.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Sub-account deleted successfully. |  -  |
| **406** | Not Acceptable. Cannot delete the default sub-account (type 0). |  -  |
| **401** | Unauthorized. Invalid or missing API key. |  -  |

<a id="getAllSubAccounts"></a>
# **getAllSubAccounts**
> List&lt;SubAccount&gt; getAllSubAccounts(limit, offset, search)

List Sub-Accounts

Retrieve all sub-accounts under your main account. Sub-accounts allow you to segment email sending for different applications, brands, or use cases.  **Sub-Account Types:** | Type | Value | Description | |------|-------|-------------| | Default | &#x60;0&#x60; | Primary sub-account created with your account (cannot be deleted) | | Custom | &#x60;1&#x60; | Additional sub-accounts you create |  **Each Sub-Account Has:** - Unique &#x60;X-SubAccount-ApiKey&#x60; for API authentication - Independent suppression list - Isolated email statistics - Own domain configurations - SMTP credentials (if enabled)  **Use Cases:** - Separate transactional and marketing emails - Multi-tenant SaaS applications (one sub-account per customer) - Different brands or product lines - Development/staging/production environments  **Note:** &#x60;isPlus&#x60; indicates SendX Plus customers with premium features. 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.SubAccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    SubAccountApi apiInstance = new SubAccountApi(defaultClient);
    Integer limit = 20; // Integer | Number of records to return per request. Default 20.
    Integer offset = 0; // Integer | Number of initial records to skip for pagination.
    String search = "Production"; // String | Case-insensitive search against sub-account names.
    try {
      List<SubAccount> result = apiInstance.getAllSubAccounts(limit, offset, search);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SubAccountApi#getAllSubAccounts");
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
| **search** | **String**| Case-insensitive search against sub-account names. | [optional] |

### Return type

[**List&lt;SubAccount&gt;**](SubAccount.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | List of sub-accounts. |  -  |
| **401** | Unauthorized. Invalid or missing API key. |  -  |

<a id="getSubAccount"></a>
# **getSubAccount**
> SubAccount getSubAccount(subaccountId)

Get Sub-Account

Retrieve detailed information about a specific sub-account, including API keys, SMTP credentials, and configuration.  **Response Includes:** - Sub-account name and ID - API key for sub-account authentication - SMTP credentials (if enabled) - Team members with access - Labels/tags for categorization - Creation timestamp 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.SubAccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    SubAccountApi apiInstance = new SubAccountApi(defaultClient);
    Integer subaccountId = 11; // Integer | The unique ID of the sub-account to retrieve.
    try {
      SubAccount result = apiInstance.getSubAccount(subaccountId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SubAccountApi#getSubAccount");
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
| **subaccountId** | **Integer**| The unique ID of the sub-account to retrieve. | |

### Return type

[**SubAccount**](SubAccount.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successfully retrieved the sub-account. |  -  |
| **404** | Sub-account not found. |  -  |
| **401** | Unauthorized, invalid API key. |  -  |

<a id="updateSubAccount"></a>
# **updateSubAccount**
> SubAccount updateSubAccount(updateSubAccount, subaccountId)

Update Sub-Account

Modify settings for an existing sub-account. Use this to rename sub-accounts, update labels, or modify configuration.  **What Can Be Updated:** - Sub-account name - Labels/tags for categorization - Other configuration settings  **Use Cases:** - Rename sub-account for clarity - Update labels for organizational changes - Modify settings after initial setup 

### Example
```java
// Import classes:
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.*;
import sendpost_java_sdk.models.*;
import sendpost_java_sdk.SubAccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.sendpost.io/api/v1");
    
    // Configure API key authorization: accountAuth
    ApiKeyAuth accountAuth = (ApiKeyAuth) defaultClient.getAuthentication("accountAuth");
    accountAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //accountAuth.setApiKeyPrefix("Token");

    SubAccountApi apiInstance = new SubAccountApi(defaultClient);
    UpdateSubAccount updateSubAccount = new UpdateSubAccount(); // UpdateSubAccount | 
    Integer subaccountId = 12; // Integer | The unique ID of the sub-account to update.
    try {
      SubAccount result = apiInstance.updateSubAccount(updateSubAccount, subaccountId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SubAccountApi#updateSubAccount");
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
| **updateSubAccount** | [**UpdateSubAccount**](UpdateSubAccount.md)|  | |
| **subaccountId** | **Integer**| The unique ID of the sub-account to update. | |

### Return type

[**SubAccount**](SubAccount.md)

### Authorization

[accountAuth](../README.md#accountAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Sub-account updated successfully. |  -  |
| **404** | Sub-account not found. |  -  |
| **401** | Unauthorized. Invalid or missing API key. |  -  |

