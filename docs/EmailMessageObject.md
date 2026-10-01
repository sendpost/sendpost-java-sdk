

# EmailMessageObject

Email message object containing all the details required to send an email. At minimum, you need to provide `from`, `to`, and either `subject` with `htmlBody`/`textBody` or a `template`. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**from** | [**EmailAddress**](EmailAddress.md) | The sender&#39;s email address and optional display name |  |
|**replyTo** | [**EmailAddress**](EmailAddress.md) | The reply-to email address. If not specified, replies will go to the &#x60;from&#x60; address |  [optional] |
|**to** | [**List&lt;Recipient&gt;**](Recipient.md) | List of recipients. Each recipient can have their own CC, BCC, and custom fields for personalization. Maximum 1000 recipients per API call.  |  |
|**subject** | **String** | Email subject line. Supports Handlebars templating for personalization. Example: \&quot;Hello, {{firstName}}! Your order is ready\&quot;  |  [optional] |
|**preText** | **String** | Preview text (preheader) shown in email clients before opening the email. This text appears after the subject line in most email clients&#39; inbox view.  |  [optional] |
|**htmlBody** | **String** | HTML content of the email. Supports Handlebars templating for personalization. Use {{customFieldName}} to insert recipient-specific values.  |  [optional] |
|**textBody** | **String** | Plain text content of the email. Used as fallback when HTML cannot be rendered. Also improves deliverability as some spam filters prefer multipart emails.  |  [optional] |
|**ampBody** | **String** | AMP HTML content for supported email clients (Gmail, Yahoo). Enables interactive email experiences like carousels, forms, and real-time content. See https://amp.dev/about/email/ for more details.  |  [optional] |
|**template** | **String** | Name of a pre-defined template to use for this email. When specified, the template&#39;s subject, htmlBody, and textBody will be used unless explicitly overridden in this request.  |  [optional] |
|**ippool** | **String** | Name of the IP pool to use for sending this email. If not specified, the default IP pool for the sub-account will be used.  |  [optional] |
|**headers** | **Map&lt;String, String&gt;** | Custom email headers to include in the message. Common uses: adding List-Unsubscribe headers, custom tracking IDs, or priority flags. Note: Some headers like From, To, Subject are set automatically and cannot be overridden.  |  [optional] |
|**trackOpens** | **Boolean** | Whether to track email opens using a tracking pixel. When enabled, a 1x1 transparent image is inserted into the HTML body. Default: true (if not specified)  |  [optional] |
|**trackClicks** | **Boolean** | Whether to track link clicks by rewriting URLs through SendPost&#39;s tracking domain. When enabled, all links in htmlBody are replaced with tracking URLs. Default: true (if not specified)  |  [optional] |
|**groups** | **List&lt;String&gt;** | Tags/groups to categorize this email for analytics and reporting. Use groups to segment your email statistics (e.g., by campaign, email type, or customer segment).  |  [optional] |
|**attachments** | [**List&lt;Attachment&gt;**](Attachment.md) | File attachments to include with the email. Maximum total attachment size: 25MB. Supported formats: PDF, images, documents, etc.  |  [optional] |
|**webhookEndpoint** | **URI** | Custom webhook URL to receive events for this specific email. Overrides the default webhook configured at the account level. Useful for per-email or per-customer webhook routing.  |  [optional] |



