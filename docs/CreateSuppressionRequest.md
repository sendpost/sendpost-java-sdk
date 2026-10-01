

# CreateSuppressionRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**hardBounce** | [**List&lt;CreateSuppressionRequestHardBounceInner&gt;**](CreateSuppressionRequestHardBounceInner.md) | Email addresses with known permanent delivery issues (invalid, non-existent domains). |  [optional] |
|**manual** | [**List&lt;CreateSuppressionRequestManualInner&gt;**](CreateSuppressionRequestManualInner.md) | Email addresses to suppress without specific categorization (do-not-contact requests, etc.). |  [optional] |
|**unsubscribe** | [**List&lt;CreateSuppressionRequestUnsubscribeInner&gt;**](CreateSuppressionRequestUnsubscribeInner.md) | Email addresses of users who opted out via external unsubscribe mechanisms. |  [optional] |
|**spamComplaint** | [**List&lt;CreateSuppressionRequestSpamComplaintInner&gt;**](CreateSuppressionRequestSpamComplaintInner.md) | Email addresses that reported spam via external feedback loops. |  [optional] |



