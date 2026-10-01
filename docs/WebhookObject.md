

# WebhookObject

The payload sent to your webhook endpoint when an email event occurs.  **Handling Webhooks:** 1. Verify the request signature (see documentation) 2. Process the event based on `event.type` or `event.typeName` 3. Use `event.eventId` for idempotency (same event may be sent multiple times) 4. Return 2xx status within 10 seconds 5. If processing takes longer, acknowledge immediately and process async  **Retry Policy:** - SendPost retries failed webhooks (non-2xx response) up to 5 times - Retries use exponential backoff: 1min, 5min, 30min, 2hr, 24hr 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**event** | [**Event**](Event.md) | Details about the email event that triggered this webhook |  [optional] |
|**emailMessage** | [**EmailMessage**](EmailMessage.md) | The original email message associated with this event |  [optional] |



