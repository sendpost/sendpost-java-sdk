

# SubAccount

A sub-account represents an isolated sending environment within your main account.  **Use cases for sub-accounts:** - Separate transactional vs marketing emails - Multi-tenant applications (one sub-account per customer) - Different products or business units - Development, staging, and production environments  Each sub-account has: - Its own API key for sending - Separate domains and sender verification - Independent suppression list - Isolated statistics and reporting 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Long** | Unique identifier for the sub-account |  [optional] |
|**accountId** | **Long** | Identifier of the parent account this sub-account belongs to |  [optional] |
|**name** | **String** | Display name for the sub-account. Must be unique within your account. Use descriptive names.  |  [optional] |
|**apiKey** | **String** | API key for this sub-account. Use this as the &#x60;X-SubAccount-ApiKey&#x60; header when making API calls for this sub-account (sending emails, managing domains, etc.).  **Security:** Treat this like a password. Rotate if compromised.  |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | Type of sub-account: - &#x60;0&#x60; &#x3D; Default (the primary sub-account created with your account) - &#x60;1&#x60; &#x3D; Custom (additional sub-accounts you create)  Note: The default sub-account cannot be deleted.  |  [optional] |
|**isPlus** | **Boolean** | Whether this sub-account belongs to a SendX Plus customer. SendX Plus is a premium tier that provides enhanced features and support.  |  [optional] |
|**labels** | [**List&lt;Label&gt;**](Label.md) | Custom labels for organizing and filtering sub-accounts |  [optional] |
|**blocked** | **Boolean** | Whether the sub-account is blocked from sending. A blocked sub-account cannot send emails. Common reasons: - High bounce/spam rates - Billing issues - Policy violations - Manual suspension by administrator  |  [optional] |
|**created** | **Long** | UNIX epoch timestamp in nanoseconds when the sub-account was created |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| NUMBER_0 | 0 |
| NUMBER_1 | 1 |



