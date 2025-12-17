

# SubAccount


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Integer** | Unique ID for the sub-account. |  [optional] |
|**name** | **String** | Name of the sub-account. |  [optional] |
|**created** | **Integer** | UNIX epoch nano timestamp when the sub-account was created. |  [optional] |
|**apiKey** | **String** | API key for the sub-account. |  [optional] |
|**labels** | **List&lt;String&gt;** | Labels associated with the sub-account |  [optional] |
|**isPlus** | **Boolean** | Indicates whether the sub-account is a Plus sub-account |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | Type of the sub-account |  [optional] |
|**createdBy** | **Member** | Member who created the sub-account |  [optional] |
|**updatedBy** | **Member** | Member who updated the sub-account |  [optional] |
|**smtpAuths** | [**List&lt;SMTPAuth&gt;**](SMTPAuth.md) | SMTP Auths associated with the sub-account |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| NUMBER_0 | 0 |
| NUMBER_1 | 1 |



