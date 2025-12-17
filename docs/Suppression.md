

# Suppression


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Integer** | The ID of the suppression |  [optional] |
|**reason** | **Integer** | The reason for the suppression (0 &#x3D; manual, 1 &#x3D; unsubscribe, 2 &#x3D; hard bounce, 3 &#x3D; spam complaint) |  [optional] |
|**email** | **String** | The email address for the suppression |  [optional] |
|**created** | **Long** | UNIX epoch nano timestamp when the suppression was created |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | Type of suppression. Valid values: hardBounce, manual, spamComplaint, unsubscribe |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| HARD_BOUNCE | &quot;hardBounce&quot; |
| MANUAL | &quot;manual&quot; |
| SPAM_COMPLAINT | &quot;spamComplaint&quot; |
| UNSUBSCRIBE | &quot;unsubscribe&quot; |



