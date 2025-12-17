

# Message


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **String** | Unique ID for the message. |  [optional] |
|**submittedAt** | **Integer** | UNIX epoch nano timestamp when message was submitted. |  [optional] |
|**from** | **Person** | Object comprising name and email address of the sender |  [optional] |
|**replyTo** | **Person** | Object comprising name and email addresses to which email replies will go to |  [optional] |
|**to** | [**List&lt;MessageToInner&gt;**](MessageToInner.md) | List of objects comprising name, email and customFields of the recipients |  [optional] |
|**headers** | **Map&lt;String, String&gt;** | Key-Value pair which are added to every email message being sent and also with webhooks triggered on events such as email delivered, open, click etc. They are useful to identify emai, recipient etc. in your internal system |  [optional] |
|**subject** | **String** | Email subject line. |  [optional] |
|**preText** | **String** | Text which appears on mobile right after email subject line. |  [optional] |
|**htmlBody** | **String** | HTML email content. |  [optional] |
|**textBody** | **String** | Text email content. |  [optional] |
|**ippool** | **String** | IP Pool from which emails will go out. Relevant only for customers on dedicated IP plans. |  [optional] |
|**trackOpens** | **Boolean** | Indicates if email opens need to be tracked. |  [optional] |
|**trackClicks** | **Boolean** | Indicates if email clicks need to be tracked. |  [optional] |
|**status** | **String** | Status of the message |  [optional] |



