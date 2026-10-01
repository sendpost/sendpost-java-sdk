

# Suppression

A suppressed email address that will not receive emails from this sub-account. Emails to suppressed addresses are automatically dropped before sending.  Suppressions are added automatically when: - An email hard bounces (permanent delivery failure) - A recipient marks an email as spam - A recipient clicks the unsubscribe link  You can also manually add suppressions via the API. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Long** | Unique identifier for the suppression record |  [optional] |
|**email** | **String** | The suppressed email address |  [optional] |
|**reason** | [**ReasonEnum**](#ReasonEnum) | Reason code for the suppression: - &#x60;0&#x60; &#x3D; Manual (added via API or dashboard) - &#x60;1&#x60; &#x3D; Unsubscribe (recipient clicked unsubscribe link) - &#x60;2&#x60; &#x3D; Hard Bounce (permanent delivery failure) - &#x60;3&#x60; &#x3D; Spam Complaint (recipient marked as spam) - &#x60;4&#x60; &#x3D; Hard Bounce (detected by post-send validation)  |  [optional] |
|**reasonText** | [**ReasonTextEnum**](#ReasonTextEnum) | Human-readable suppression reason |  [optional] |
|**smtpError** | **String** | SMTP error message from the receiving server (only for hard bounce suppressions). Useful for diagnosing delivery issues.  |  [optional] |
|**messageUUID** | **String** | UUID of the message whose bounce/complaint caused this suppression. Empty for manually added suppressions. Useful for tracing the origin.  |  [optional] |
|**created** | **Long** | UNIX epoch timestamp in nanoseconds when the suppression was added |  [optional] |



## Enum: ReasonEnum

| Name | Value |
|---- | -----|
| NUMBER_0 | 0 |
| NUMBER_1 | 1 |
| NUMBER_2 | 2 |
| NUMBER_3 | 3 |
| NUMBER_4 | 4 |



## Enum: ReasonTextEnum

| Name | Value |
|---- | -----|
| MANUAL | &quot;manual&quot; |
| UNSUBSCRIBE | &quot;unsubscribe&quot; |
| HARD_BOUNCE | &quot;hardBounce&quot; |
| SPAM_COMPLAINT | &quot;spamComplaint&quot; |



