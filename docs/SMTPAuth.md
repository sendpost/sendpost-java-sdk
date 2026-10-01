

# SMTPAuth

SMTP authentication credentials for sending emails via SMTP relay. Use these credentials to configure your application to send email through SendPost's SMTP servers (smtp.sendpost.io).  **Note:** The SMTP password is never returned by the API. It is shown only once, in the dashboard, when credentials are created or regenerated. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Long** | Unique identifier for the SMTP credentials |  [optional] |
|**username** | **String** | SMTP username for authentication. Format: {identifier}@{subaccount_id}.sendpost.io  |  [optional] |
|**created** | **Long** | UNIX epoch timestamp in nanoseconds when credentials were created |  [optional] |



