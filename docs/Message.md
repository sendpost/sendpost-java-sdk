

# Message

A previously submitted email message with its metadata. Use the message lookup API to retrieve details about emails you have sent. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**messageId** | **String** | Unique identifier (UUID) for this email message |  [optional] |
|**subAccountId** | **Long** | ID of the sub-account that sent this email |  [optional] |
|**publicIp** | **String** | The public IP address used to send this email |  [optional] |
|**emailType** | **String** | Classification of the email, e.g. \&quot;transactional\&quot; or \&quot;marketing\&quot;.  |  [optional] |
|**submittedAt** | **Long** | UNIX epoch timestamp in nanoseconds when the email was submitted |  [optional] |
|**from** | [**EmailAddress**](EmailAddress.md) | The sender&#39;s email address and display name |  [optional] |
|**replyTo** | [**EmailAddress**](EmailAddress.md) | The Reply-To email address and display name |  [optional] |
|**to** | [**Recipient**](Recipient.md) | The primary recipient, including any per-recipient CC/BCC and custom fields |  [optional] |
|**headerTo** | [**Recipient**](Recipient.md) | The address rendered in the visible To header (may differ from the envelope recipient) |  [optional] |
|**headerCc** | [**List&lt;CopyTo&gt;**](CopyTo.md) | Addresses rendered in the visible Cc header |  [optional] |
|**headerBcc** | [**List&lt;CopyTo&gt;**](CopyTo.md) | Addresses rendered in the visible Bcc header |  [optional] |
|**attachments** | [**List&lt;Attachment&gt;**](Attachment.md) | File attachments included with the email |  [optional] |
|**groups** | **List&lt;String&gt;** | Tags/groups associated with this email |  [optional] |
|**ipPool** | **String** | Name of the IP pool used for sending |  [optional] |
|**headers** | **Map&lt;String, String&gt;** | Custom SMTP headers set on the message |  [optional] |
|**subject** | **String** | The email subject line |  [optional] |
|**preText** | **String** | Preheader/preview text shown by many email clients after the subject |  [optional] |
|**htmlBody** | **String** | The HTML body of the email |  [optional] |
|**textBody** | **String** | The plain-text body of the email |  [optional] |
|**ampBody** | **String** | The AMP for Email body, if provided |  [optional] |
|**trackOpens** | **Boolean** | Whether open tracking was enabled for this email |  [optional] |
|**trackClicks** | **Boolean** | Whether click tracking was enabled for this email |  [optional] |



