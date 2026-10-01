

# IPAllocationRequest

Request body for allocating new dedicated IPs to your account. New IPs should be warmed up gradually before sending at full volume. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**ips** | **List&lt;String&gt;** | List of IP addresses to allocate. These must be available IPs from SendPost&#39;s IP pool. Contact support to request IP allocation.  |  |
|**autoWarmupEnabled** | **Boolean** | Enable automatic IP warmup for newly allocated IPs. Recommended: true for new IPs to gradually build sender reputation.  |  [optional] |



