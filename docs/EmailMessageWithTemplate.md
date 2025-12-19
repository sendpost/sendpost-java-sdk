

# EmailMessageWithTemplate


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**from** | [**EmailAddress**](EmailAddress.md) |  |  [optional] |
|**replyTo** | [**EmailAddress**](EmailAddress.md) |  |  [optional] |
|**to** | [**List&lt;Recipient&gt;**](Recipient.md) |  |  [optional] |
|**subject** | **String** |  |  [optional] |
|**preText** | **String** |  |  [optional] |
|**htmlBody** | **String** |  |  [optional] |
|**textBody** | **String** |  |  [optional] |
|**ampBody** | **String** |  |  [optional] |
|**ippool** | **String** |  |  [optional] |
|**headers** | **Map&lt;String, String&gt;** |  |  [optional] |
|**trackOpens** | **Boolean** |  |  [optional] |
|**trackClicks** | **Boolean** |  |  [optional] |
|**groups** | **List&lt;String&gt;** |  |  [optional] |
|**attachments** | [**List&lt;Attachment&gt;**](Attachment.md) |  |  [optional] |
|**webhookEndpoint** | **String** |  |  [optional] |
|**template** | **String** |  |  [optional] |
|**templateId** | **String** | Template ID for the email template |  [optional] |
|**templateVariables** | **Map&lt;String, String&gt;** | Key-Value pair of template variables |  [optional] |



