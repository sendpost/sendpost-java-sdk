

# ValidationStat

Email validation statistics. Tracks the outcome distribution of an email validation (verification) run. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**initiated** | **Long** | Number of validation requests initiated. |  [optional] |
|**processed** | **Long** | Number of validation requests fully processed. |  [optional] |
|**valid** | **Long** | Number of addresses determined to be valid/deliverable. |  [optional] |
|**invalid** | **Long** | Number of addresses determined to be invalid/undeliverable. |  [optional] |
|**softBounced** | **Long** | Number of addresses that soft-bounced during validation. |  [optional] |
|**hardBounced** | **Long** | Number of addresses that hard-bounced during validation. |  [optional] |
|**catchAll** | **Long** | Number of addresses on catch-all (accept-all) domains. |  [optional] |
|**unknown** | **Long** | Number of addresses whose deliverability could not be determined. |  [optional] |



