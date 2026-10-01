

# EmailResponse

Response object returned for each recipient when sending an email. Contains the message ID for tracking and any error information if the submission failed. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**to** | **String** | The recipient email address this response corresponds to |  [optional] |
|**submittedAt** | **Long** | UNIX epoch timestamp in nanoseconds when the email was accepted for processing. Use this for precise timing and correlation with webhook events.  |  [optional] |
|**messageId** | **String** | Unique identifier (UUID) for this email message. Use this ID to track the email through webhooks and the message lookup API.  |  [optional] |
|**errorCode** | **Integer** | Error code if the email submission failed. Common codes: - 0: Success (no error) - 1: Invalid recipient email - 2: Recipient in suppression list - 3: Domain not verified - 4: Rate limit exceeded - 5: Invalid sender email  |  [optional] |
|**message** | **String** | Human-readable message describing the result. On success: \&quot;Email submitted successfully\&quot; On error: Description of what went wrong  |  [optional] |



