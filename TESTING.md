# SendPost Java SDK Testing Guide

This guide explains how to test the SendPost Java SDK to verify it's working correctly.

## Prerequisites

1. **Java 8 or higher** - Make sure Java is installed:
   ```bash
   java -version
   ```

2. **Maven** - Required for building and running tests:
   ```bash
   mvn -version
   ```

3. **SendPost API Keys** - You need:
   - Sub-Account API Key (for sub-account operations like sending emails)
   - Account API Key (optional, for account-level operations)

   Get your API keys from: https://app.sendpost.io

## Setup

### Option 1: Using Environment Variables (Recommended)

Set the following environment variables:

```bash
export SENDPOST_SUB_ACCOUNT_API_KEY="your-sub-account-api-key"
export SENDPOST_ACCOUNT_API_KEY="your-account-api-key"  # Optional
```

### Option 2: Edit the Test File

Edit `src/test/java/sendpost_java_sdk/SendPostSDKTest.java` and update the constants:

```java
private static final String SUB_ACCOUNT_API_KEY = "your-sub-account-api-key";
private static final String ACCOUNT_API_KEY = "your-account-api-key";
```

Also update the test email addresses:

```java
private static final String TEST_FROM_EMAIL = "your-verified-email@yourdomain.com";
private static final String TEST_TO_EMAIL = "recipient@example.com";
```

## Building the SDK

First, build the SDK:

```bash
cd sendpost-java-sdk
mvn clean install
```

## Running the Tests

### Run All Tests Using Maven

```bash
mvn test
```

### Run the Comprehensive Test File

Compile and run the test file directly:

```bash
# Compile
mvn compile test-compile

# Run the test
java -cp "target/classes:target/test-classes:$(mvn dependency:build-classpath -q -Dmdep.outputFile=/dev/stdout)" \
  sendpost_java_sdk.SendPostSDKTest
```

Or use Maven to run it:

```bash
mvn exec:java -Dexec.mainClass="sendpost_java_sdk.SendPostSDKTest" \
  -Dexec.classpathScope=test
```

## Test Coverage

The test suite (`SendPostSDKTest.java`) includes tests for:

1. **Send Email** - Tests sending a simple email with HTML and text content
2. **Get All Domains** - Retrieves all domains associated with your sub-account
3. **Get All Sub-Accounts** - Lists all sub-accounts (requires account API key)
4. **Get Messages** - Retrieves sent messages from the last 7 days
5. **Get Suppressions** - Lists suppression entries
6. **Send Email With Template** - Tests sending emails using templates

## Expected Output

When tests run successfully, you should see output like:

```
========================================
SendPost Java SDK Test Suite
========================================
Base URL: https://api.sendpost.io/api/v1
Sub-Account API Key: Configured
Account API Key: Configured
========================================

=== Test 1: Get All Domains ===
Fetching domains...
✓ Domains retrieved successfully!
Total domains: 2
  - Domain: example.com
    ID: 123
    Verified: true
    DKIM: true

=== Test 2: Send Email ===
Sending email from test@yourdomain.com to recipient@example.com...
✓ Email sent successfully!
Response count: 1
  - Message ID: abc123
  - Status: queued

...
```

## Troubleshooting

### Authentication Errors (401)

- **Problem**: `Status code: 401`
- **Solution**: Check that your API keys are correct and properly set

### Invalid Email Address (422)

- **Problem**: `Status code: 422`
- **Solution**: Make sure the sender email address is verified in your SendPost account

### Connection Errors

- **Problem**: Connection timeout or network errors
- **Solution**: Check your internet connection and firewall settings

### Missing Dependencies

- **Problem**: ClassNotFoundException or NoClassDefFoundError
- **Solution**: Run `mvn clean install` to ensure all dependencies are downloaded

## Testing Individual API Methods

You can also test individual methods by modifying the `main` method in `SendPostSDKTest.java`:

```java
public static void main(String[] args) {
    SendPostSDKTest test = new SendPostSDKTest();
    // Run only specific tests
    test.testSendEmail();
    test.testGetAllDomains();
}
```

## Additional Resources

- [SendPost API Documentation](https://sendpost.io/docs)
- [SendPost Dashboard](https://app.sendpost.io)
- [SDK README](README.md)

## Support

If you encounter any issues:
1. Check the error messages in the test output
2. Verify your API keys are correct
3. Ensure your email addresses are verified
4. Contact SendPost support: hello@sendpost.io
