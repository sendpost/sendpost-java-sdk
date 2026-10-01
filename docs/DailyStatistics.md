

# DailyStatistics

Email metrics for a single day. Field names mirror the canonical Stat schema (opened/clicked/spam, plus sent and smtpDropped). 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**processed** | **Long** | Total emails accepted by the API |  [optional] |
|**sent** | **Long** | Total emails sent to recipient mail servers |  [optional] |
|**dropped** | **Long** | Total emails dropped before sending |  [optional] |
|**smtpDropped** | **Long** | Total emails dropped at the SMTP level before delivery |  [optional] |
|**delivered** | **Long** | Total emails delivered successfully |  [optional] |
|**softBounced** | **Long** | Total temporary delivery failures |  [optional] |
|**hardBounced** | **Long** | Total permanent delivery failures |  [optional] |
|**opened** | **Long** | Total email opens |  [optional] |
|**clicked** | **Long** | Total link clicks |  [optional] |
|**unsubscribed** | **Long** | Total unsubscribes |  [optional] |
|**spam** | **Long** | Total spam complaints |  [optional] |



