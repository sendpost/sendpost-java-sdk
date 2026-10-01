

# DnsRecord

DNS record configuration for domain verification

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**host** | **String** | The DNS hostname where this record should be created |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | The DNS record type (TXT or CNAME) |  [optional] |
|**textValue** | **String** | The value to set for this DNS record |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| TXT | &quot;TXT&quot; |
| CNAME | &quot;CNAME&quot; |



