

# IPPoolUpdateRequest

Request body for updating an existing IP pool

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**name** | **String** | New display name for the IP pool |  [optional] |
|**ips** | [**List&lt;EIP&gt;**](EIP.md) | Updated list of IP addresses for this pool. This replaces the current IP list - include all IPs you want in the pool.  |  [optional] |
|**tpsps** | **List&lt;Long&gt;** | Updated list of third-party sending provider IDs |  [optional] |
|**routingStrategy** | [**RoutingStrategyEnum**](#RoutingStrategyEnum) | Updated routing strategy (see IPPoolCreateRequest for values) |  [optional] |
|**routingMetaData** | **String** | Updated routing configuration (JSON) |  [optional] |
|**shouldOverflow** | **Boolean** | Whether to enable overflow to backup pool |  [optional] |
|**overflowPoolName** | **String** | Name of the overflow pool |  [optional] |



## Enum: RoutingStrategyEnum

| Name | Value |
|---- | -----|
| NUMBER_0 | 0 |
| NUMBER_1 | 1 |
| NUMBER_2 | 2 |
| NUMBER_3 | 3 |



