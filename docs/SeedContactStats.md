

# SeedContactStats

Combined seed-contact (inbox-placement) statistics. Bundles the overall Stat totals with several per-dimension breakdowns. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**stat** | [**Stat**](Stat.md) |  |  [optional] |
|**rStats** | [**List&lt;RStat&gt;**](RStat.md) | Per-day statistics for the seed-contact run. |  [optional] |
|**groupStats** | [**List&lt;GroupStat&gt;**](GroupStat.md) | Statistics broken down by group/tag. |  [optional] |
|**domainStats** | [**List&lt;DomainStat&gt;**](DomainStat.md) | Statistics broken down by sending domain. |  [optional] |
|**ipStats** | [**List&lt;IPStat&gt;**](IPStat.md) | Statistics broken down by sending IP address. |  [optional] |
|**providerStats** | [**List&lt;ProviderStat&gt;**](ProviderStat.md) | Statistics broken down by email provider. |  [optional] |



