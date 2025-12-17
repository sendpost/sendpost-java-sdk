

# IPPool


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Integer** |  |  [optional] |
|**name** | **String** |  |  [optional] |
|**created** | **Integer** |  |  [optional] |
|**ips** | [**List&lt;IP&gt;**](IP.md) |  |  [optional] |
|**thirdPartySendingProviders** | [**List&lt;ThirdPartySendingProvider&gt;**](ThirdPartySendingProvider.md) |  |  [optional] |
|**routingStrategy** | **Integer** |  |  [optional] |
|**routingMetaData** | **String** |  |  [optional] |
|**autoWarmupEnabled** | **Boolean** |  |  [optional] |
|**infraMonitor** | **Boolean** |  |  [optional] |
|**ipDomainWarmupStatus** | **String** |  |  [optional] |
|**shouldOverflow** | **Boolean** | Indicates whether the IP should overflow, once email capacity of the IP Pool has been reached, should we send remaining emails over shared IP or not |  [optional] |
|**overflowPoolName** | **String** | The name of the overflow pool |  [optional] |
|**warmupInterval** | **Integer** | The interval for the warmup |  [optional] |



