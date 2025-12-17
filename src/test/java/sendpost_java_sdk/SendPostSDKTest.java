package sendpost_java_sdk;

import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.auth.ApiKeyAuth;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Comprehensive test class for SendPost Java SDK
 * 
 * This test class demonstrates how to use the SendPost Java SDK to:
 * - Send emails
 * - Retrieve domains
 * - Get sub-accounts
 * - Get messages
 * - Handle errors
 * 
 * To run this test:
 * 1. Set environment variables:
 *    - SENDPOST_SUB_ACCOUNT_API_KEY: Your sub-account API key
 *    - SENDPOST_ACCOUNT_API_KEY: Your account API key (optional, for account-level operations)
 * 2. Or modify the API_KEY constants below
 * 3. Compile and run: javac SendPostSDKTest.java && java SendPostSDKTest
 */
public class SendPostSDKTest {
    
    // API Configuration
    private static final String BASE_PATH = "https://api.sendpost.io/api/v1";
    
    // API Keys - Set these or use environment variables
    // You can get these from https://app.sendpost.io
    private static final String SUB_ACCOUNT_API_KEY = System.getenv("SENDPOST_SUB_ACCOUNT_API_KEY") != null 
        ? System.getenv("SENDPOST_SUB_ACCOUNT_API_KEY") 
        : "YOUR_SUB_ACCOUNT_API_KEY_HERE";
    
    private static final String ACCOUNT_API_KEY = System.getenv("SENDPOST_ACCOUNT_API_KEY") != null 
        ? System.getenv("SENDPOST_ACCOUNT_API_KEY") 
        : "YOUR_ACCOUNT_API_KEY_HERE";
    
    // Test email addresses - Replace with your verified email addresses
    private static final String TEST_FROM_EMAIL = "test@yourdomain.com";
    private static final String TEST_TO_EMAIL = "recipient@example.com";
    
    private ApiClient apiClient;
    
    public SendPostSDKTest() {
        // Initialize API client
        apiClient = Configuration.getDefaultApiClient();
        apiClient.setBasePath(BASE_PATH);
    }
    
    /**
     * Configure sub-account authentication
     */
    private void configureSubAccountAuth() {
        ApiKeyAuth subAccountAuth = (ApiKeyAuth) apiClient.getAuthentication("subAccountAuth");
        subAccountAuth.setApiKey(SUB_ACCOUNT_API_KEY);
    }
    
    /**
     * Configure account authentication
     */
    private void configureAccountAuth() {
        ApiKeyAuth accountAuth = (ApiKeyAuth) apiClient.getAuthentication("accountAuth");
        accountAuth.setApiKey(ACCOUNT_API_KEY);
    }
    
    /**
     * Test 1: Send a simple email
     */
    public void testSendEmail() {
        System.out.println("\n=== Test 1: Send Email ===");
        
        try {
            configureSubAccountAuth();
            EmailApi emailApi = new EmailApi(apiClient);
            
            // Create email message object
            EmailMessageObject emailMessage = new EmailMessageObject();
            
            // Set sender
            EmailAddress from = new EmailAddress();
            from.setEmail(TEST_FROM_EMAIL);
            from.setName("SendPost Test");
            emailMessage.setFrom(from);
            
            // Set recipient
            List<Recipient> recipients = new ArrayList<>();
            Recipient recipient = new Recipient();
            recipient.setEmail(TEST_TO_EMAIL);
            recipient.setName("Test Recipient");
            recipients.add(recipient);
            emailMessage.setTo(recipients);
            
            // Set email content
            emailMessage.setSubject("Test Email from SendPost Java SDK");
            emailMessage.setHtmlBody("<h1>Hello from SendPost!</h1><p>This is a test email sent using the SendPost Java SDK.</p>");
            emailMessage.setTextBody("Hello from SendPost! This is a test email sent using the SendPost Java SDK.");
            
            // Enable tracking
            emailMessage.setTrackOpens(true);
            emailMessage.setTrackClicks(true);
            
            // Send email
            System.out.println("Sending email from " + TEST_FROM_EMAIL + " to " + TEST_TO_EMAIL + "...");
            List<EmailResponse> responses = emailApi.sendEmail(emailMessage);
            
            System.out.println("✓ Email sent successfully!");
            System.out.println("Response count: " + responses.size());
            for (EmailResponse response : responses) {
                System.out.println("  - Message ID: " + response.getMessageId());
                System.out.println("  - Status: " + response.getStatus());
            }
            
        } catch (ApiException e) {
            System.err.println("✗ Failed to send email:");
            System.err.println("  Status code: " + e.getCode());
            System.err.println("  Response body: " + e.getResponseBody());
            System.err.println("  Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("✗ Unexpected error:");
            e.printStackTrace();
        }
    }
    
    /**
     * Test 2: Get all domains
     */
    public void testGetAllDomains() {
        System.out.println("\n=== Test 2: Get All Domains ===");
        
        try {
            configureSubAccountAuth();
            DomainApi domainApi = new DomainApi(apiClient);
            
            System.out.println("Fetching domains...");
            List<Domain> domains = domainApi.getAllDomains(50, 0, null);
            
            System.out.println("✓ Domains retrieved successfully!");
            System.out.println("Total domains: " + domains.size());
            
            for (Domain domain : domains) {
                System.out.println("  - Domain: " + domain.getName());
                System.out.println("    ID: " + domain.getId());
                System.out.println("    Verified: " + domain.getVerified());
                if (domain.getDkim() != null) {
                    System.out.println("    DKIM: " + domain.getDkim().getVerified());
                }
            }
            
        } catch (ApiException e) {
            System.err.println("✗ Failed to get domains:");
            System.err.println("  Status code: " + e.getCode());
            System.err.println("  Response body: " + e.getResponseBody());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("✗ Unexpected error:");
            e.printStackTrace();
        }
    }
    
    /**
     * Test 3: Get all sub-accounts (requires account API key)
     */
    public void testGetAllSubAccounts() {
        System.out.println("\n=== Test 3: Get All Sub-Accounts ===");
        
        if (ACCOUNT_API_KEY.equals("YOUR_ACCOUNT_API_KEY_HERE")) {
            System.out.println("⚠ Skipping test - Account API key not configured");
            return;
        }
        
        try {
            configureAccountAuth();
            SubAccountApi subAccountApi = new SubAccountApi(apiClient);
            
            System.out.println("Fetching sub-accounts...");
            List<SubAccount> subAccounts = subAccountApi.getAllSubAccounts(50, 0, null);
            
            System.out.println("✓ Sub-accounts retrieved successfully!");
            System.out.println("Total sub-accounts: " + subAccounts.size());
            
            for (SubAccount subAccount : subAccounts) {
                System.out.println("  - Sub-Account: " + subAccount.getName());
                System.out.println("    ID: " + subAccount.getId());
                System.out.println("    Created: " + subAccount.getCreated());
            }
            
        } catch (ApiException e) {
            System.err.println("✗ Failed to get sub-accounts:");
            System.err.println("  Status code: " + e.getCode());
            System.err.println("  Response body: " + e.getResponseBody());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("✗ Unexpected error:");
            e.printStackTrace();
        }
    }
    
    /**
     * Test 4: Get messages
     */
    public void testGetMessages() {
        System.out.println("\n=== Test 4: Get Messages ===");
        
        if (ACCOUNT_API_KEY.equals("YOUR_ACCOUNT_API_KEY_HERE")) {
            System.out.println("⚠ Skipping test - Account API key not configured");
            return;
        }
        
        try {
            configureAccountAuth();
            MessageApi messageApi = new MessageApi(apiClient);
            
            // Get messages from last 7 days
            OffsetDateTime to = OffsetDateTime.now(ZoneOffset.UTC);
            OffsetDateTime from = to.minusDays(7);
            
            System.out.println("Fetching messages from " + from + " to " + to + "...");
            List<Message> messages = messageApi.getAllMessages(from, to, 10, 0);
            
            System.out.println("✓ Messages retrieved successfully!");
            System.out.println("Total messages: " + messages.size());
            
            for (Message message : messages) {
                System.out.println("  - Message ID: " + message.getId());
                System.out.println("    Subject: " + message.getSubject());
                System.out.println("    From: " + message.getFrom());
                System.out.println("    Status: " + message.getStatus());
            }
            
        } catch (ApiException e) {
            System.err.println("✗ Failed to get messages:");
            System.err.println("  Status code: " + e.getCode());
            System.err.println("  Response body: " + e.getResponseBody());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("✗ Unexpected error:");
            e.printStackTrace();
        }
    }
    
    /**
     * Test 5: Get suppressions
     */
    public void testGetSuppressions() {
        System.out.println("\n=== Test 5: Get Suppressions ===");
        
        try {
            configureSubAccountAuth();
            SuppressionApi suppressionApi = new SuppressionApi(apiClient);
            
            System.out.println("Fetching suppressions...");
            // Get suppressions from last 30 days
            LocalDate toDate = LocalDate.now();
            LocalDate fromDate = toDate.minusDays(30);
            List<Suppression> suppressions = suppressionApi.getSuppressionList(fromDate, toDate, 50, 0, null, null);
            
            System.out.println("✓ Suppressions retrieved successfully!");
            System.out.println("Total suppressions: " + suppressions.size());
            
            for (Suppression suppression : suppressions) {
                System.out.println("  - Email: " + suppression.getEmail());
                System.out.println("    Type: " + suppression.getType());
                System.out.println("    Created: " + suppression.getCreated());
            }
            
        } catch (ApiException e) {
            System.err.println("✗ Failed to get suppressions:");
            System.err.println("  Status code: " + e.getCode());
            System.err.println("  Response body: " + e.getResponseBody());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("✗ Unexpected error:");
            e.printStackTrace();
        }
    }
    
    /**
     * Test 6: Send email with template
     */
    public void testSendEmailWithTemplate() {
        System.out.println("\n=== Test 6: Send Email With Template ===");
        
        try {
            configureSubAccountAuth();
            EmailApi emailApi = new EmailApi(apiClient);
            
            // Create email message with template
            EmailMessageWithTemplate emailMessage = new EmailMessageWithTemplate();
            
            // Set sender
            EmailAddress from = new EmailAddress();
            from.setEmail(TEST_FROM_EMAIL);
            from.setName("SendPost Test");
            emailMessage.setFrom(from);
            
            // Set recipient
            List<Recipient> recipients = new ArrayList<>();
            Recipient recipient = new Recipient();
            recipient.setEmail(TEST_TO_EMAIL);
            recipient.setName("Test Recipient");
            recipients.add(recipient);
            emailMessage.setTo(recipients);
            
            // Set template ID (replace with your actual template ID)
            emailMessage.setTemplateId("your-template-id");
            
            // Set template variables if needed
            Map<String, String> templateVariables = new HashMap<>();
            templateVariables.put("name", "Test User");
            templateVariables.put("company", "SendPost");
            emailMessage.setTemplateVariables(templateVariables);
            
            System.out.println("Sending email with template...");
            List<EmailResponse> responses = emailApi.sendEmailWithTemplate(emailMessage);
            
            System.out.println("✓ Email with template sent successfully!");
            System.out.println("Response count: " + responses.size());
            
        } catch (ApiException e) {
            System.err.println("✗ Failed to send email with template:");
            System.err.println("  Status code: " + e.getCode());
            System.err.println("  Response body: " + e.getResponseBody());
            // This is expected if template ID is not valid
            System.out.println("  Note: Make sure you have a valid template ID configured");
        } catch (Exception e) {
            System.err.println("✗ Unexpected error:");
            e.printStackTrace();
        }
    }
    
    /**
     * Run all tests
     */
    public void runAllTests() {
        System.out.println("========================================");
        System.out.println("SendPost Java SDK Test Suite");
        System.out.println("========================================");
        System.out.println("Base URL: " + BASE_PATH);
        System.out.println("Sub-Account API Key: " + (SUB_ACCOUNT_API_KEY.startsWith("YOUR") ? "Not configured" : "Configured"));
        System.out.println("Account API Key: " + (ACCOUNT_API_KEY.startsWith("YOUR") ? "Not configured" : "Configured"));
        System.out.println("========================================");
        
        // Check if API keys are configured
        if (SUB_ACCOUNT_API_KEY.startsWith("YOUR")) {
            System.err.println("\n⚠ WARNING: Sub-Account API key not configured!");
            System.err.println("Please set SENDPOST_SUB_ACCOUNT_API_KEY environment variable or update the constant in the code.");
            System.err.println("Some tests will be skipped.\n");
        }
        
        // Run tests
        testGetAllDomains();
        testGetSuppressions();
        testSendEmail();
        testSendEmailWithTemplate();
        testGetAllSubAccounts();
        testGetMessages();
        
        System.out.println("\n========================================");
        System.out.println("Test Suite Completed");
        System.out.println("========================================");
    }
    
    /**
     * Main method to run tests
     */
    public static void main(String[] args) {
        SendPostSDKTest test = new SendPostSDKTest();
        test.runAllTests();
    }
}
