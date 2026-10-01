

# IP

A dedicated IP address used for sending emails. Dedicated IPs give you full control over your sender reputation, as your deliverability is not affected by other senders.  **IP Warmup:** New IPs should be gradually \"warmed up\" by slowly increasing sending volume over 4-6 weeks to establish reputation with ISPs. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Long** | Unique identifier for the IP resource |  [optional] |
|**publicIp** | **String** | The public IPv4 address used for sending emails. This is the IP that receiving mail servers will see.  |  [optional] |
|**reverseDnsHostname** | **String** | The reverse DNS (PTR record) hostname for this IP. Properly configured rDNS is important for deliverability. Format: sp{id}.{region}.sendpost.email  |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | Type of IP allocation: - &#x60;0&#x60; &#x3D; Shared IP (shared with other SendPost senders, pooled reputation) - &#x60;1&#x60; &#x3D; Dedicated IP (exclusive to your account, your own reputation)  |  [optional] |
|**autoWarmupEnabled** | **Boolean** | Whether automatic IP warmup is enabled. When enabled, SendPost automatically manages sending volume to gradually build reputation on this IP.  |  [optional] |
|**labels** | [**List&lt;Label&gt;**](Label.md) | Custom labels/tags for organizing IPs |  [optional] |
|**state** | [**StateEnum**](#StateEnum) | Current state of the IP: - &#x60;0&#x60; &#x3D; Warmup (IP is in warmup phase, gradually building reputation) - &#x60;1&#x60; &#x3D; Normal (IP is fully warmed and ready for full sending volume)  |  [optional] |
|**created** | **Long** | UNIX epoch timestamp in nanoseconds when the IP was allocated |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| NUMBER_0 | 0 |
| NUMBER_1 | 1 |



## Enum: StateEnum

| Name | Value |
|---- | -----|
| NUMBER_0 | 0 |
| NUMBER_1 | 1 |



