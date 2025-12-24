# SendPost Java SDK

The official Java SDK for SendPost API. Send emails, track opens and clicks, manage domains, and monitor statistics with ease.

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Quick Start](#quick-start)
- [Authentication](#authentication)
- [Usage Examples](#usage-examples)
- [API Reference](#api-reference)
- [Error Handling](#error-handling)
- [Support](#support)

## Requirements

- Java 1.8 or higher
- Maven 3.8.3+ or Gradle 7.2+
- SendPost account with API keys ([Get your API keys](https://app.sendpost.io/register))

## Installation

### Maven

Add this dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>io.sendpost</groupId>
  <artifactId>sendpost-java-sdk</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Gradle

Add this dependency to your `build.gradle`:

```groovy
  dependencies {
     implementation "io.sendpost:sendpost-java-sdk:1.0.0"
  }
```

### Building from Source

If you need to build the SDK from source:

```bash
mvn clean install
```

This will create a JAR file in the `target/` directory that you can add to your project.

## Quick Start

Here's a simple example to send your first email:

```java
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.ApiKeyAuth;
import sendpost_java_sdk.*;
import java.util.ArrayList;
import java.util.List;

public class SendEmailExample {
  public static void main(String[] args) {
        // Step 1: Set up the API client
        ApiClient apiClient = Configuration.getDefaultApiClient();
        apiClient.setBasePath("https://api.sendpost.io/api/v1");
        
        // Step 2: Configure authentication
        ApiKeyAuth subAccountAuth = (ApiKeyAuth) apiClient.getAuthentication("subAccountAuth");
        subAccountAuth.setApiKey("YOUR_SUB_ACCOUNT_API_KEY_HERE");
        
        // Step 3: Create the email message
        EmailApi emailApi = new EmailApi(apiClient);
        EmailMessageObject emailMessage = new EmailMessageObject();
        
        // Set sender
        EmailAddress from = new EmailAddress();
        from.setEmail("sender@yourdomain.com");
        from.setName("Your Name");
        emailMessage.setFrom(from);
        
        // Set recipient
        List<Recipient> recipients = new ArrayList<>();
        Recipient recipient = new Recipient();
        recipient.setEmail("recipient@example.com");
        recipient.setName("Recipient Name");
        recipients.add(recipient);
        emailMessage.setTo(recipients);
        
        // Set email content
        emailMessage.setSubject("Hello from SendPost!");
        emailMessage.setHtmlBody("<h1>Hello!</h1><p>This is a test email.</p>");
        emailMessage.setTextBody("Hello! This is a test email.");
        
        // Enable tracking
        emailMessage.setTrackOpens(true);
        emailMessage.setTrackClicks(true);
        
        // Step 4: Send the email
        try {
            List<EmailResponse> responses = emailApi.sendEmail(emailMessage);
            if (!responses.isEmpty()) {
                EmailResponse response = responses.get(0);
                System.out.println("Email sent successfully!");
                System.out.println("Message ID: " + response.getMessageId());
            }
    } catch (ApiException e) {
            System.err.println("Error sending email:");
      System.err.println("Status code: " + e.getCode());
            System.err.println("Response: " + e.getResponseBody());
        }
    }
}
```

## Authentication

SendPost uses API keys for authentication. You need two types of API keys:

### Sub-Account API Key

Used for most email operations:
- Sending emails
- Managing domains
- Viewing sub-account statistics
- Managing suppressions

**Header:** `X-SubAccount-ApiKey`

```java
ApiKeyAuth subAccountAuth = (ApiKeyAuth) apiClient.getAuthentication("subAccountAuth");
subAccountAuth.setApiKey("YOUR_SUB_ACCOUNT_API_KEY");
```

### Account API Key

Used for account management:
- Creating and managing sub-accounts
- Managing IPs and IP pools
- Creating webhooks
- Viewing account-level statistics

**Header:** `X-Account-ApiKey`

```java
ApiKeyAuth accountAuth = (ApiKeyAuth) apiClient.getAuthentication("accountAuth");
accountAuth.setApiKey("YOUR_ACCOUNT_API_KEY");
```

### Getting Your API Keys

1. Sign up at [SendPost](https://app.sendpost.io/register)
2. Log in to your account
3. Navigate to Settings → API Keys
4. Copy your Account API Key and Sub-Account API Key

## Usage Examples

### Sending a Simple Email

```java
EmailApi emailApi = new EmailApi(apiClient);
EmailMessageObject emailMessage = new EmailMessageObject();

// Set sender
EmailAddress from = new EmailAddress();
from.setEmail("sender@yourdomain.com");
from.setName("Your Company");
emailMessage.setFrom(from);

// Set recipient
List<Recipient> recipients = new ArrayList<>();
Recipient recipient = new Recipient();
recipient.setEmail("customer@example.com");
recipient.setName("Customer");
recipients.add(recipient);
emailMessage.setTo(recipients);

// Set content
emailMessage.setSubject("Welcome!");
emailMessage.setHtmlBody("<h1>Welcome to our service!</h1>");
emailMessage.setTextBody("Welcome to our service!");

// Send
List<EmailResponse> responses = emailApi.sendEmail(emailMessage);
```

### Sending Email with Multiple Recipients

```java
List<Recipient> recipients = new ArrayList<>();

// Primary recipient
Recipient to = new Recipient();
to.setEmail("customer@example.com");
to.setName("Customer");
recipients.add(to);

// CC recipient
Recipient cc = new Recipient();
cc.setEmail("manager@example.com");
cc.setName("Manager");
// Note: CC and BCC are set separately on the email message object

emailMessage.setTo(recipients);
```

### Sending Email with Attachments

```java
List<Attachment> attachments = new ArrayList<>();

Attachment attachment = new Attachment();
attachment.setFilename("document.pdf");
attachment.setContent("base64-encoded-content-here"); // Base64 encoded file content
attachment.setType("application/pdf");
attachments.add(attachment);

emailMessage.setAttachments(attachments);
```

### Sending Email with Custom Fields

```java
Recipient recipient = new Recipient();
recipient.setEmail("customer@example.com");

// Add custom fields
Map<String, Object> customFields = new HashMap<>();
customFields.put("customer_id", "12345");
customFields.put("order_number", "ORD-001");
recipient.setCustomFields(customFields);
```

### Sending Email with Groups (for Analytics)

```java
List<String> groups = new ArrayList<>();
groups.add("transactional");
groups.add("order-confirmation");
emailMessage.setGroups(groups);
```

### Sending Email with Template

```java
EmailApi emailApi = new EmailApi(apiClient);
EmailMessageWithTemplate templateMessage = new EmailMessageWithTemplate();

// Set sender
EmailAddress from = new EmailAddress();
from.setEmail("sender@yourdomain.com");
from.setName("Your Company");
templateMessage.setFrom(from);

// Set recipient
List<Recipient> recipients = new ArrayList<>();
Recipient recipient = new Recipient();
recipient.setEmail("customer@example.com");
recipients.add(recipient);
templateMessage.setTo(recipients);

// Set template ID
templateMessage.setTemplate("template_id_here");
templateMessage.setSubject("Your Subject");

// Send
List<EmailResponse> responses = emailApi.sendEmailWithTemplate(templateMessage);
```

### Managing Domains

```java
DomainApi domainApi = new DomainApi(apiClient);

// Add a new domain
CreateDomainRequest domainRequest = new CreateDomainRequest();
domainRequest.setName("yourdomain.com");
Domain domain = domainApi.subaccountDomainPost(domainRequest);

System.out.println("Domain ID: " + domain.getId());
System.out.println("DKIM Record: " + domain.getDkim().getTextValue());
// Add the DKIM record to your DNS to verify the domain

// List all domains
List<Domain> domains = domainApi.getAllDomains(null, null, null);
for (Domain d : domains) {
    System.out.println("Domain: " + d.getName() + " - Verified: " + d.getVerified());
}

// Get domain details
Domain domainDetails = domainApi.subaccountDomainDomainIdGet(domain.getId().toString());
```

### Viewing Statistics

```java
StatsApi statsApi = new StatsApi(apiClient);

// Get sub-account statistics for the last 7 days
LocalDate toDate = LocalDate.now();
LocalDate fromDate = toDate.minusDays(7);
Long subAccountId = 12345L; // Your sub-account ID

List<Stat> stats = statsApi.accountSubaccountStatSubaccountIdGet(fromDate, toDate, subAccountId);

for (Stat stat : stats) {
    System.out.println("Date: " + stat.getDate());
    StatStats statData = stat.getStats();
    System.out.println("  Processed: " + statData.getProcessed());
    System.out.println("  Delivered: " + statData.getDelivered());
    System.out.println("  Opens: " + statData.getOpens());
    System.out.println("  Clicks: " + statData.getClicks());
}

// Get aggregate statistics
AggregateStat aggregate = statsApi.accountSubaccountStatSubaccountIdAggregateGet(
    fromDate, toDate, subAccountId);
System.out.println("Total Processed: " + aggregate.getProcessed());
System.out.println("Total Delivered: " + aggregate.getDelivered());
```

### Managing Sub-Accounts

```java
SubAccountApi subAccountApi = new SubAccountApi(apiClient);

// Create a new sub-account
NewSubAccount newSubAccount = new NewSubAccount();
newSubAccount.setName("My New Sub-Account");
SubAccount subAccount = subAccountApi.createSubAccount(newSubAccount);

System.out.println("Created sub-account:");
System.out.println("  ID: " + subAccount.getId());
System.out.println("  API Key: " + subAccount.getApiKey());

// List all sub-accounts
List<SubAccount> subAccounts = subAccountApi.getAllSubAccounts(null, null, null);
for (SubAccount sa : subAccounts) {
    System.out.println("Sub-Account: " + sa.getName() + " (ID: " + sa.getId() + ")");
}

// Get sub-account details
SubAccount details = subAccountApi.getSubAccount(subAccount.getId().longValue());
```

### Creating Webhooks

```java
WebhookApi webhookApi = new WebhookApi(apiClient);

NewWebhook newWebhook = new NewWebhook();
newWebhook.setUrl("https://your-app.com/webhook");
newWebhook.setEnabled(true);

// Configure which events to receive
newWebhook.setProcessed(true);      // Email processed
newWebhook.setDelivered(true);       // Email delivered
newWebhook.setOpened(true);          // Email opened
newWebhook.setClicked(true);         // Link clicked
newWebhook.setHardBounced(true);     // Hard bounce
newWebhook.setSoftBounced(true);     // Soft bounce
newWebhook.setUnsubscribed(true);    // Unsubscribed
newWebhook.setSpam(true);            // Marked as spam

Webhook webhook = webhookApi.createWebhook(newWebhook);
System.out.println("Webhook created with ID: " + webhook.getId());
```

### Retrieving Message Details

```java
MessageApi messageApi = new MessageApi(apiClient);
String messageId = "your-message-id-here";

Message message = messageApi.getMessageById(messageId);

System.out.println("Message ID: " + message.getMessageID());
System.out.println("From: " + message.getFrom().getEmail());
System.out.println("To: " + message.getTo().getEmail());
System.out.println("Subject: " + message.getSubject());
System.out.println("Submitted At: " + message.getSubmittedAt());
```

### Managing Suppressions

```java
SuppressionApi suppressionApi = new SuppressionApi(apiClient);

// Add emails to suppression list
CreateSuppressionRequest suppressionRequest = new CreateSuppressionRequest();

// Add hard bounces
List<CreateSuppressionRequestHardBounceInner> hardBounces = new ArrayList<>();
CreateSuppressionRequestHardBounceInner hardBounce = new CreateSuppressionRequestHardBounceInner();
hardBounce.setEmail("bounced@example.com");
hardBounces.add(hardBounce);
suppressionRequest.setHardBounce(hardBounces);

// Add manual suppressions
List<CreateSuppressionRequestManualInner> manual = new ArrayList<>();
CreateSuppressionRequestManualInner manualSuppression = new CreateSuppressionRequestManualInner();
manualSuppression.setEmail("unsubscribed@example.com");
manual.add(manualSuppression);
suppressionRequest.setManual(manual);

suppressionApi.createSuppression(suppressionRequest);

// List all suppressions
List<Suppression> suppressions = suppressionApi.getSuppressionList(null, null, null);
for (Suppression s : suppressions) {
    System.out.println("Email: " + s.getEmail() + " - Type: " + s.getType());
}
```

## API Reference

All API endpoints are documented in the `docs/` directory. Here's a quick reference:

### Email Operations
- `EmailApi.sendEmail()` - Send an email
- `EmailApi.sendEmailWithTemplate()` - Send email using a template

### Domain Operations
- `DomainApi.getAllDomains()` - List all domains
- `DomainApi.subaccountDomainPost()` - Add a new domain
- `DomainApi.subaccountDomainDomainIdGet()` - Get domain details
- `DomainApi.subaccountDomainDomainIdDelete()` - Delete a domain

### Statistics
- `StatsApi.accountSubaccountStatSubaccountIdGet()` - Get sub-account statistics
- `StatsApi.accountSubaccountStatSubaccountIdAggregateGet()` - Get aggregate statistics
- `StatsAApi.getAllAccountStats()` - Get account-level statistics

### Sub-Account Management
- `SubAccountApi.createSubAccount()` - Create a new sub-account
- `SubAccountApi.getAllSubAccounts()` - List all sub-accounts
- `SubAccountApi.getSubAccount()` - Get sub-account details
- `SubAccountApi.updateSubAccount()` - Update a sub-account
- `SubAccountApi.deleteSubAccount()` - Delete a sub-account

### Webhooks
- `WebhookApi.createWebhook()` - Create a webhook
- `WebhookApi.getAllWebhooks()` - List all webhooks
- `WebhookApi.getWebhook()` - Get webhook details
- `WebhookApi.updateWebhook()` - Update a webhook
- `WebhookApi.deleteWebhook()` - Delete a webhook

### Messages
- `MessageApi.getMessageById()` - Get message details by ID

### Suppressions
- `SuppressionApi.createSuppression()` - Add emails to suppression list
- `SuppressionApi.getSuppressionList()` - List all suppressions
- `SuppressionApi.deleteSuppression()` - Remove emails from suppression list

For complete API documentation, see the [API Reference Documentation](docs/).

## Error Handling

Always wrap API calls in try-catch blocks to handle errors:

```java
try {
    List<EmailResponse> responses = emailApi.sendEmail(emailMessage);
    // Handle success
} catch (ApiException e) {
    // Handle API errors
    System.err.println("Error Code: " + e.getCode());
    System.err.println("Error Message: " + e.getMessage());
    System.err.println("Response Body: " + e.getResponseBody());
    
    // Common error codes:
    // 401 - Unauthorized (invalid or missing API key)
    // 403 - Forbidden (resource already exists or insufficient permissions)
    // 404 - Not Found (resource doesn't exist)
    // 422 - Unprocessable Entity (invalid request data)
    // 500 - Internal Server Error (SendPost server issue)
} catch (Exception e) {
    // Handle other exceptions
    e.printStackTrace();
}
```

### Common Error Codes

- **200** - Success
- **401** - Unauthorized: Invalid or missing API key
- **403** - Forbidden: Resource already exists or insufficient permissions
- **404** - Not Found: Resource ID doesn't exist
- **422** - Unprocessable Entity: Invalid request body or parameters
- **500** - Internal Server Error: SendPost server issue
- **503** - Service Unavailable: SendPost is offline for maintenance

## Best Practices

1. **Store API keys securely**: Never commit API keys to version control. Use environment variables or secure configuration files.

```java
String apiKey = System.getenv("SENDPOST_SUB_ACCOUNT_API_KEY");
```

2. **Use separate API clients for different operations**: Create separate `ApiClient` instances for account-level and sub-account-level operations if needed.

3. **Handle errors gracefully**: Always implement proper error handling and logging.

4. **Verify domains before sending**: Make sure your sending domains are verified before sending emails.

5. **Use groups for analytics**: Add groups to your emails to better organize and analyze your email campaigns.

6. **Monitor statistics regularly**: Check your email statistics to monitor deliverability and engagement.

## Complete Example

See the [example project](../example-sdk-java/) for a complete working example that demonstrates:
- Creating sub-accounts
- Setting up webhooks
- Managing domains
- Sending emails
- Viewing statistics
- Managing IP pools

## Support

- **Documentation**: [https://docs.sendpost.io](https://docs.sendpost.io)
- **Email**: hello@sendpost.io
- **Website**: [https://sendpost.io](https://sendpost.io)
- **Developer Portal**: [https://app.sendpost.io](https://app.sendpost.io)

## License

This SDK is provided as-is. See the LICENSE file for details.

---

*Automatically generated by the [OpenAPI Generator](https://openapi-generator.tech)*
