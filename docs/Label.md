

# Label

A label/tag for organizing and categorizing resources like IPs, sub-accounts, etc. Labels help you filter and group resources in the dashboard and API responses. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Long** | Unique identifier for the label |  [optional] |
|**name** | **String** | Display name for the label (max 50 characters) |  [optional] |
|**color** | **String** | Hex color code for visual identification in the dashboard. Format: 6-character hex without # prefix.  |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | Resource type this label applies to: - &#x60;0&#x60; &#x3D; IP label - &#x60;1&#x60; &#x3D; Sub-account label - &#x60;2&#x60; &#x3D; IP Pool label  |  [optional] |
|**created** | **Long** | UNIX epoch timestamp in nanoseconds when the label was created |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| NUMBER_0 | 0 |
| NUMBER_1 | 1 |
| NUMBER_2 | 2 |



