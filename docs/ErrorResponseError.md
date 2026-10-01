

# ErrorResponseError


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**code** | **String** | Machine-readable error code. Common codes: - &#x60;invalid_request&#x60; - Malformed request body or parameters - &#x60;authentication_failed&#x60; - Invalid or missing API key - &#x60;resource_not_found&#x60; - Requested resource doesn&#39;t exist - &#x60;resource_exists&#x60; - Resource with same identifier already exists - &#x60;validation_error&#x60; - Request validation failed - &#x60;rate_limit_exceeded&#x60; - Too many requests - &#x60;internal_error&#x60; - Server-side error  |  [optional] |
|**message** | **String** | Human-readable error description |  [optional] |
|**param** | **String** | The parameter that caused the error (if applicable) |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | Error category |  [optional] |
|**details** | [**List&lt;ErrorResponseErrorDetailsInner&gt;**](ErrorResponseErrorDetailsInner.md) | Additional error details for validation errors |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| INVALID_REQUEST_ERROR | &quot;invalid_request_error&quot; |
| AUTHENTICATION_ERROR | &quot;authentication_error&quot; |
| NOT_FOUND_ERROR | &quot;not_found_error&quot; |
| CONFLICT_ERROR | &quot;conflict_error&quot; |
| RATE_LIMIT_ERROR | &quot;rate_limit_error&quot; |
| API_ERROR | &quot;api_error&quot; |



