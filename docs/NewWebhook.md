

# NewWebhook

Request body for creating a new webhook endpoint.  **Event Selection Tips:** - For basic tracking: Enable `delivered`, `opened`, `clicked` - For suppression sync: Enable `hardBounced`, `unsubscribed`, `spam` - For debugging: Enable `dropped`, `softBounced` - Use `uniqueOpen`/`uniqueClick` instead of `opened`/`clicked` to reduce volume 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**enabled** | **Boolean** | Whether the webhook is active immediately after creation. Set to false to configure and test before activating.  |  [optional] |
|**url** | **URI** | HTTPS URL endpoint to receive webhook POST requests. Must: - Use HTTPS (HTTP not allowed for security) - Be publicly accessible - Return 2xx status within 10 seconds - Handle duplicate deliveries (use eventId for idempotency)  |  |
|**processed** | **Boolean** | Fire when email is accepted by SendPost API |  [optional] |
|**sent** | **Boolean** | Fire when email is sent to recipient&#39;s mail server |  [optional] |
|**delivered** | **Boolean** | Fire when email is accepted by recipient&#39;s mail server |  [optional] |
|**dropped** | **Boolean** | Fire when email is not sent (suppression, invalid, etc.) |  [optional] |
|**smtpDropped** | **Boolean** | Fire when email is rejected at SMTP level |  [optional] |
|**softBounced** | **Boolean** | Fire on temporary delivery failure (will retry) |  [optional] |
|**hardBounced** | **Boolean** | Fire on permanent delivery failure |  [optional] |
|**opened** | **Boolean** | Fire when email is opened. Fires on EVERY open. Consider using &#x60;uniqueOpen&#x60; instead to reduce volume.  |  [optional] |
|**clicked** | **Boolean** | Fire when a link is clicked. Fires on EVERY click. Consider using &#x60;uniqueClick&#x60; instead to reduce volume.  |  [optional] |
|**unsubscribed** | **Boolean** | Fire when recipient clicks unsubscribe link |  [optional] |
|**spam** | **Boolean** | Fire when recipient marks email as spam |  [optional] |
|**uniqueOpen** | **Boolean** | Fire only on FIRST open of each email (unique opens). More efficient than &#x60;opened&#x60; if you only need engagement metrics.  |  [optional] |
|**uniqueClick** | **Boolean** | Fire only on FIRST click of each email (unique clicks). More efficient than &#x60;clicked&#x60; if you only need engagement metrics.  |  [optional] |



