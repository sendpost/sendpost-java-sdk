

# EmailStatsStats


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**processed** | **Integer** | Number of emails accepted by SendPost API |  [optional] |
|**sent** | **Integer** | Number of emails sent |  [optional] |
|**delivered** | **Integer** | Number of emails successfully delivered to SMTP without errors |  [optional] |
|**dropped** | **Integer** | Number of emails dropped without attempting to deliver due to invalid email or suppression list |  [optional] |
|**smtpDropped** | **Integer** | Number of emails dropped by SMTP |  [optional] |
|**hardBounced** | **Integer** | Number of emails that resulted in a hard bounce error |  [optional] |
|**softBounced** | **Integer** | Number of emails that resulted in a temporary soft bounce error |  [optional] |
|**opened** | **Integer** | Number of emails opened by recipients |  [optional] |
|**clicked** | **Integer** | Number of email links clicked by recipients |  [optional] |
|**unsubscribed** | **Integer** | Number of email recipients who unsubscribed |  [optional] |
|**spam** | **Integer** | Number of emails marked as spam by recipients |  [optional] |



