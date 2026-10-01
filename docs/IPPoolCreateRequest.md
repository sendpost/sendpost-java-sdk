

# IPPoolCreateRequest

Request body for creating a new IP pool

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**name** | **String** | Display name for the IP pool. Must be unique within your account. Use descriptive names like \&quot;transactional\&quot;, \&quot;marketing-bulk\&quot;, \&quot;high-priority\&quot;  |  |
|**ips** | [**List&lt;EIP&gt;**](EIP.md) | List of dedicated IP addresses to include in this pool. IPs must already be allocated to your account.  |  [optional] |
|**tpsps** | **List&lt;Long&gt;** | List of third-party sending provider IDs to include in this pool. TPSPs must be pre-configured in your account.  |  [optional] |
|**routingStrategy** | [**RoutingStrategyEnum**](#RoutingStrategyEnum) | Email routing strategy: - &#x60;0&#x60; &#x3D; Round Robin (equal distribution) - &#x60;1&#x60; &#x3D; Email Provider Strategy (route by recipient domain) - &#x60;2&#x60; &#x3D; Volume Percentage Strategy (weighted distribution) - &#x60;3&#x60; &#x3D; Sending Domain Strategy (route by sender domain)  |  [optional] |
|**routingMetaData** | **String** | JSON-encoded routing configuration. See IPPools documentation for format. Use &#x60;{}&#x60; for round-robin strategy.  |  [optional] |
|**shouldOverflow** | **Boolean** | Whether to overflow to shared pool when this pool is unavailable |  [optional] |
|**overflowPoolName** | **String** | Name of the IP pool to overflow to (if shouldOverflow is true) |  [optional] |



## Enum: RoutingStrategyEnum

| Name | Value |
|---- | -----|
| NUMBER_0 | 0 |
| NUMBER_1 | 1 |
| NUMBER_2 | 2 |
| NUMBER_3 | 3 |



