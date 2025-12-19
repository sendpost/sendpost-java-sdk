

# Domain


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Integer** | Unique ID for the domain. |  [optional] |
|**name** | **String** | Name of the domain. |  [optional] |
|**dkim** | [**DomainDkim**](DomainDkim.md) |  |  [optional] |
|**returnPath** | [**DomainReturnPath**](DomainReturnPath.md) |  |  [optional] |
|**track** | [**DomainTrack**](DomainTrack.md) |  |  [optional] |
|**dmarc** | [**DomainDmarc**](DomainDmarc.md) |  |  [optional] |
|**dkimConfig** | **String** | DKIM configuration |  [optional] |
|**dkimVerified** | **Boolean** | Status of DKIM verification ( true or false ) |  [optional] |
|**dmarcVerified** | **Boolean** | Status of DMARC verification ( true or false) |  [optional] |
|**returnPathVerified** | **Boolean** | Status of ReturnPath verification ( true or false ) |  [optional] |
|**trackVerified** | **Boolean** | Status of Track verification ( true or false ) |  [optional] |
|**created** | **Long** | UNIX epoch timestamp in nanoseconds. |  [optional] |



