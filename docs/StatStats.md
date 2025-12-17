

# StatStats


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**processed** | **Integer** | Number of emails accepted by SendPost API. |  [optional] |
|**delivered** | **Integer** | Number of emails we were able to successfully deliver at SMTP without encountering any error |  [optional] |
|**dropped** | **Integer** | Number of emails drop without attempting to deliver either because the email is invalid or email in in existing suppression list |  [optional] |
|**hardBounced** | **Integer** | Number of emails where we got SMTP hard bounce error code by the recipient mail provider |  [optional] |
|**softBounced** | **Integer** | Number of emails where we got temporary soft bounce error by the recipent mail provider. Soft bounced emails are retried upto 5 times over 24 hour period before marking them as hardBounced. |  [optional] |
|**unsubscribed** | **Integer** | Number of email recipients who unsubscribed from receiving further emails |  [optional] |
|**spam** | **Integer** | Number of email recipients who marked emails as spam |  [optional] |



