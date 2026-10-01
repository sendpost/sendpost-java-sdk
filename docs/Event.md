

# Event

An email event representing a specific action or state change for a message. Events are sent to your webhook endpoints in real-time as they occur.  **Event Flow:** ``` processed → sent → delivered → opened → clicked                  ↘ softBounced (retried)                  ↘ hardBounced (permanent)                  ↘ dropped ``` 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**eventId** | **UUID** | Unique identifier for this specific event. Use this for idempotency - the same event may be delivered multiple times.  |  [optional] |
|**messageId** | **UUID** | Unique identifier of the email message this event belongs to. Use this to correlate events with the original send request.  |  [optional] |
|**type** | [**TypeEnum**](#TypeEnum) | Numeric event type code: - &#x60;0&#x60; &#x3D; processed (email accepted by API) - &#x60;1&#x60; &#x3D; dropped (not sent - suppression, invalid, etc.) - &#x60;2&#x60; &#x3D; delivered (accepted by recipient&#39;s mail server) - &#x60;3&#x60; &#x3D; softBounced (temporary failure, will retry) - &#x60;4&#x60; &#x3D; hardBounced (permanent failure) - &#x60;5&#x60; &#x3D; opened (tracking pixel loaded) - &#x60;6&#x60; &#x3D; clicked (link clicked) - &#x60;7&#x60; &#x3D; unsubscribed (clicked unsubscribe link) - &#x60;8&#x60; &#x3D; spam (marked as spam by recipient) - &#x60;9&#x60; &#x3D; sent (sent to mail server) - &#x60;10&#x60; &#x3D; smtpDropped (dropped at SMTP level)  |  [optional] |
|**typeName** | [**TypeNameEnum**](#TypeNameEnum) | Human-readable event type name |  [optional] |
|**from** | **String** | Sender email address |  [optional] |
|**to** | **String** | Recipient email address |  [optional] |
|**subject** | **String** | Email subject line (useful for identifying the email) |  [optional] |
|**groups** | **List&lt;String&gt;** | Tags/groups that were associated with the email |  [optional] |
|**submittedAt** | **Long** | UNIX epoch timestamp in nanoseconds when the email was originally submitted |  [optional] |
|**timestamp** | **Long** | UNIX epoch timestamp in nanoseconds when this event occurred |  [optional] |
|**eventMetadata** | [**EventMetadata**](EventMetadata.md) |  |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| NUMBER_0 | 0l |
| NUMBER_1 | 1l |
| NUMBER_2 | 2l |
| NUMBER_3 | 3l |
| NUMBER_4 | 4l |
| NUMBER_5 | 5l |
| NUMBER_6 | 6l |
| NUMBER_7 | 7l |
| NUMBER_8 | 8l |
| NUMBER_9 | 9l |
| NUMBER_10 | 10l |



## Enum: TypeNameEnum

| Name | Value |
|---- | -----|
| PROCESSED | &quot;processed&quot; |
| DROPPED | &quot;dropped&quot; |
| DELIVERED | &quot;delivered&quot; |
| SOFT_BOUNCED | &quot;softBounced&quot; |
| HARD_BOUNCED | &quot;hardBounced&quot; |
| OPENED | &quot;opened&quot; |
| CLICKED | &quot;clicked&quot; |
| UNSUBSCRIBED | &quot;unsubscribed&quot; |
| SPAM | &quot;spam&quot; |
| SENT | &quot;sent&quot; |
| SMTP_DROPPED | &quot;smtpDropped&quot; |



