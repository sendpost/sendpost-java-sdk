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

Creates a new sub-account under the current account.

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
| **201** | Sub-account successfully created. |  -  |
| **403** | Forbidden, sub-account with the same name already exists. |  -  |
| **401** | Unauthorized, invalid API key. |  -  |

<a id="deleteSubAccount"></a>
# **deleteSubAccount**
> DeleteSubAccountResponse deleteSubAccount(subaccountId)

Delete Sub-Account

Deletes a specific sub-account by its ID.

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
    Integer subaccountId = 12; // Integer | The ID of the sub-account to delete.
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
| **subaccountId** | **Integer**| The ID of the sub-account to delete. | |

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
| **200** | Sub-account successfully deleted. |  -  |
| **406** | Not Acceptable. Cannot delete the default sub-account. |  -  |
| **401** | Unauthorized. Invalid API key. |  -  |

<a id="getAllSubAccounts"></a>
# **getAllSubAccounts**
> List&lt;SubAccount&gt; getAllSubAccounts(limit, offset, search)

List Sub-Accounts

Retrieves a list of all sub-accounts associated with a specific account.

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
    Integer limit = 10; // Integer | Number of records to return per request.
    Integer offset = 0; // Integer | Number of initial records to skip.
    String search = "Hooli"; // String | Case-insensitive search against the sub-account name.
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
| **limit** | **Integer**| Number of records to return per request. | [optional] |
| **offset** | **Integer**| Number of initial records to skip. | [optional] |
| **search** | **String**| Case-insensitive search against the sub-account name. | [optional] |

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
| **200** | Successfully retrieved sub-accounts. |  -  |
| **401** | Unauthorized, invalid API key. |  -  |

<a id="getSubAccount"></a>
# **getSubAccount**
> SubAccount getSubAccount(subaccountId)

Get Sub-Account

Retrieves a specific sub-account by its ID.

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
    Integer subaccountId = 11; // Integer | The ID of the sub-account to retrieve.
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
| **subaccountId** | **Integer**| The ID of the sub-account to retrieve. | |

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
> SubAccount updateSubAccount(subaccountId, updateSubAccount)

Update Sub-Account

Updates the details of an existing sub-account.

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
    Integer subaccountId = 12; // Integer | The ID of the sub-account to update.
    UpdateSubAccount updateSubAccount = new UpdateSubAccount(); // UpdateSubAccount | 
    try {
      SubAccount result = apiInstance.updateSubAccount(subaccountId, updateSubAccount);
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
| **subaccountId** | **Integer**| The ID of the sub-account to update. | |
| **updateSubAccount** | [**UpdateSubAccount**](UpdateSubAccount.md)|  | |

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
| **200** | Sub-account successfully updated. |  -  |
| **404** | Sub-account not found. |  -  |
| **401** | Unauthorized, invalid API key. |  -  |

