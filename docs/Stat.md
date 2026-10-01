

# Stat

Email statistics for a specific time period. These metrics help you understand your email performance and deliverability.  **Metric Flow:** ``` processed → sent → delivered → opened → clicked           ↘ dropped           ↘ softBounced           ↘ smtpDropped       ↘ hardBounced                               ↘ unsubscribed                               ↘ spam ``` 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**processed** | **Long** | Total number of emails accepted by SendPost API for processing. This is the starting point - all emails submitted through the API.  |  [optional] |
|**sent** | **Long** | Number of emails sent to recipient mail servers. sent &#x3D; processed - dropped - smtpDropped  |  [optional] |
|**dropped** | **Long** | Number of emails dropped before sending. Common reasons: - Recipient email in suppression list (hard bounce, spam complaint, unsubscribe) - Invalid recipient email format - Sender domain not verified  |  [optional] |
|**smtpDropped** | **Long** | Number of emails dropped at SMTP level due to policy violations or rate limiting by the receiving server before delivery attempt completed.  |  [optional] |
|**delivered** | **Long** | Number of emails successfully delivered to recipient mail servers. Note: Delivered means accepted by the server, not necessarily in inbox.  |  [optional] |
|**softBounced** | **Long** | Number of temporary delivery failures (soft bounces). Common causes: - Recipient mailbox full - Server temporarily unavailable - Message too large SendPost automatically retries soft bounces.  |  [optional] |
|**hardBounced** | **Long** | Number of permanent delivery failures (hard bounces). Common causes: - Recipient email doesn&#39;t exist - Domain doesn&#39;t exist - Recipient has blocked sender Hard bounced addresses are automatically added to suppression list.  |  [optional] |
|**opened** | **Long** | Number of emails opened (tracking pixel loaded). Requires trackOpens&#x3D;true. Note: Some email clients block tracking pixels.  |  [optional] |
|**clicked** | **Long** | Number of emails with at least one link clicked. Requires trackClicks&#x3D;true.  |  [optional] |
|**unsubscribed** | **Long** | Number of recipients who clicked the unsubscribe link. Unsubscribed addresses are automatically added to suppression list.  |  [optional] |
|**spam** | **Long** | Number of spam complaints (recipient marked email as spam). High spam rates can severely impact your sender reputation. Target: Keep spam rate below 0.1%.  |  [optional] |



