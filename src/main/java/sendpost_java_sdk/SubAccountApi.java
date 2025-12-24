/*
 * SendPost API
 * # Introduction  SendPost provides email API and SMTP relay which can be used not just to send & measure but also alert & optimised email sending.  You can use SendPost to:  * Send personalised emails to multiple recipients using email API   * Track opens and clicks  * Analyse statistics around open, clicks, bounce, unsubscribe and spam    At and advanced level you can use it to:  * Manage multiple sub-accounts which may map to your promotional or transactional sending, multiple product lines or multiple customers   * Classify your emails using groups for better analysis  * Analyse and fix email sending at sub-account level, IP Pool level or group level  * Have automated alerts to notify disruptions regarding email sending  * Manage different dedicated IP Pools so to better control your email sending  * Automatically know when IP or domain is blacklisted or sender score is down  * Leverage pro deliverability tools to get significantly better email deliverability & inboxing   [<img src=\"https://run.pstmn.io/button.svg\" alt=\"Run In Postman\" style=\"width: 128px; height: 32px;\">](https://god.gw.postman.com/run-collection/33476323-e6dbd27f-c4a7-4d49-bcac-94b0611b938b?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D33476323-e6dbd27f-c4a7-4d49-bcac-94b0611b938b%26entityType%3Dcollection%26workspaceId%3D6b1e4f65-96a9-4136-9512-6266c852517e)   # Overview  ## REST API  SendPost API is built on REST API principles. Authenticated users can interact with any of the API endpoints to perform:  * **GET**- to get a resource  * **POST** - to create a resource  * **PUT** - to update an existing resource  * **DELETE** - to delete a resource   The API endpoint for all API calls is: <code>https://api.sendpost.io/api/v1</code>   Some conventions that have been followed in the API design overall are following:   * All resources have either <code>/api/v1/subaccount</code> or <code>/api/v1/account</code> in their API call resource path based on who is authorised for the resource. All API calls with path <code>/api/v1/subaccount</code> use <code>X-SubAccount-ApiKey</code> in their request header. Likewise all API calls with path <code>/api/v1/account</code> use <code>X-Account-ApiKey</code> in their request header.  * All resource endpoints end with singular name and not plural. So we have <code>domain</code> instead of domains for domain resource endpoint. Likewise we have <code>sender</code> instead of senders for sender resource endpoint.  * Body submitted for POST / PUT API calls as well as JSON response from SendPost API follow camelcase convention  * All timestamps returned in response (created or submittedAt response fields) are UNIX nano epoch timestamp.   <aside class=\"success\"> All resources have either <code>/api/v1/subaccount</code> or <code>/api/v1/account</code> in their API call resource path based on who is authorised for the resource. All API calls with path <code>/api/v1/subaccount</code> use <code>X-SubAccount-ApiKey</code> in their request header. Likewise all API calls with path <code>/api/v1/account</code> use <code>X-Account-ApiKey</code> in their request header. </aside>   SendPost uses conventional HTTP response codes to indicate the success or failure of an API request.    * Codes in the <code>2xx</code> range indicate success.   * Codes in the <code>4xx</code> range indicate an error owing due to unauthorize access, incorrect request parameters or body etc.  * Code in the <code>5xx</code> range indicate an eror with SendPost's servers ( internal service issue or maintenance )   <aside class=\"info\"> SendPost all responses return <code>created</code> in UNIX nano epoch timestamp.  </aside>   ## Authentication  SendPost uses API keys for authentication. You can register a new SendPost API key at our [developer portal](https://app.sendpost.io/register).   SendPost expects the API key to be included in all API requests to the server in a header that looks like the following:   `X-SubAccount-ApiKey: AHEZEP8192SEGH`   This API key is used for all Sub-Account level operations such as:  * Sending emails  * Retrieving stats regarding open, click, bounce, unsubscribe and spam  * Uploading suppressions list  * Verifying sending domains and more  In addition to <code>X-SubAccount-ApiKey</code> you also have another API Key <code>X-Account-APIKey</code> which is used for Account level operations such as :  * Creating and managing sub-accounts  * Allocating IPs for your account  * Getting overall billing and usage information  * Email List validation  * Creating and managing alerts and more   <aside class=\"notice\"> You must look at individual API reference page to look at whether <code>X-SubAccount-ApiKey</code> is required or <code>X-Account-ApiKey</code> </aside>   In case an incorrect API Key header is specified or if it is missed you will get HTTP Response 401 ( Unauthorized ) response from SendPost.   ## HTTP Response Headers   Code           | Reason                 | Details ---------------| -----------------------| ----------- 200            | Success                | Everything went well 401            | Unauthorized           | Incorrect or missing API header either <code>X-SubAccount-ApiKey</code> or <code>X-Account-ApiKey</code> 403            | Forbidden              | Typically sent when resource with same name or details already exist 406            | Missing resource id    | Resource id specified is either missing or doesn't exist 422            | Unprocessable entity   | Request body is not in proper format 500            | Internal server error  | Some error happened at SendPost while processing API request 503            | Service Unavailable    | SendPost is offline for maintenance. Please try again later  # API SDKs  We have native SendPost SDKs in the following programming languages. You can integrate with them or create your own SDK with our API specification. In case you need any assistance with respect to API then do reachout to our team from website chat or email us at **hello@sendpost.io**   * [PHP](https://github.com/sendpost/sendpost_php_sdk)  * [Javascript](https://github.com/sendpost/sendpost_javascript_sdk)  * [Ruby](https://github.com/sendpost/sendpost_ruby_sdk)  * [Python](https://github.com/sendpost/sendpost_python_sdk)  * [Golang](https://github.com/sendpost/sendpost_go_sdk)   # API Reference  SendX REST API can be broken down into two major sub-sections:   * Sub-Account  * Account    Sub-Account API operations enable common email sending API use-cases like sending bulk email, adding new domains or senders for email sending programmatically, retrieving stats, adding suppressions etc. All Sub-Account API operations need to pass <code>X-SubAccount-ApiKey</code> header with every API call.   The Account API operations allow users to manage multiple sub-accounts and manage IPs. A single parent SendPost account can have 100's of sub-accounts. You may want to create sub-accounts for different products your company is running or to segregate types of emails or for managing email sending across multiple customers of yours.   # SMTP Reference  Simple Mail Transfer Protocol (SMTP) is a quick and easy way to send email from one server to another. SendPost provides an SMTP service that allows you to deliver your email via our servers instead of your own client or server.  This means you can count on SendPost's delivery at scale for your SMTP needs.    ## Integrating SMTP    1. Get the SMTP `username` and `password` from your SendPost account.  2. Set the server host in your email client or application to `smtp.sendpost.io`. This setting is sometimes referred to as the external SMTP server or the SMTP relay.  3. Set the `username` and `password`.  4. Set the port to `587` (or as specified below).  ## SMTP Ports   - For an unencrypted or a TLS connection, use port `25`, `2525` or `587`.  - For a SSL connection, use port `465`  - Check your firewall and network to ensure they're not blocking any of our SMTP Endpoints.   SendPost supports STARTTLS for establishing a TLS-encrypted connection. STARTTLS is a means of upgrading an unencrypted connection to an encrypted connection. There are versions of STARTTLS for a variety of protocols; the SMTP version is defined in [RFC 3207](https://www.ietf.org/rfc/rfc3207.txt).   To set up a STARTTLS connection, the SMTP client connects to the SendPost SMTP endpoint `smtp.sendpost.io` on port 25, 587, or 2525, issues an EHLO command, and waits for the server to announce that it supports the STARTTLS SMTP extension. The client then issues the STARTTLS command, initiating TLS negotiation. When negotiation is complete, the client issues an EHLO command over the new encrypted connection, and the SMTP session proceeds normally.   <aside class=\"success\"> If you are unsure which port to use, a TLS connection on port 587 is typically recommended. </aside>   ## Sending email from your application   ```javascript \"use strict\";  const nodemailer = require(\"nodemailer\");  async function main() { // create reusable transporter object using the default SMTP transport let transporter = nodemailer.createTransport({ host: \"smtp.sendpost.io\", port: 587, secure: false, // true for 465, false for other ports auth: { user:  \"<username>\" , // generated ethereal user pass: \"<password>\", // generated ethereal password }, requireTLS: true, debug: true, logger: true, });  // send mail with defined transport object try { let info = await transporter.sendMail({ from: 'erlich@piedpiper.com', to: 'gilfoyle@piedpiper.com', subject: 'Test Email Subject', html: '<h1>Hello Geeks!!!</h1>', }); console.log(\"Message sent: %s\", info.messageId); } catch (e) { console.log(e) } }  main().catch(console.error); ```  For PHP   ```php <?php // Import PHPMailer classes into the global namespace use PHPMailer\\PHPMailer\\PHPMailer; use PHPMailer\\PHPMailer\\SMTP; use PHPMailer\\PHPMailer\\Exception;  // Load Composer's autoloader require 'vendor/autoload.php';  $mail = new PHPMailer(true);  // Settings try { $mail->SMTPDebug = SMTP::DEBUG_CONNECTION;                  // Enable verbose debug output $mail->isSMTP();                                            // Send using SMTP $mail->Host       = 'smtp.sendpost.io';                     // Set the SMTP server to send through $mail->SMTPAuth   = true;                                   // Enable SMTP authentication $mail->Username   = '<username>';                           // SMTP username $mail->Password   = '<password>';                           // SMTP password $mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;         // Enable implicit TLS encryption $mail->Port       = 587;                                    // TCP port to connect to; use 587 if you have set `SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS`  //Recipients $mail->setFrom('erlich@piedpiper.com', 'Erlich'); $mail->addAddress('gilfoyle@piedpiper.com', 'Gilfoyle');  //Content $mail->isHTML(true);                                  //Set email format to HTML $mail->Subject = 'Here is the subject'; $mail->Body    = 'This is the HTML message body <b>in bold!</b>'; $mail->AltBody = 'This is the body in plain text for non-HTML mail clients';  $mail->send(); echo 'Message has been sent';  } catch (Exception $e) { echo \"Message could not be sent. Mailer Error: {$mail->ErrorInfo}\"; } ``` For Python ```python #!/usr/bin/python3  import sys import os import re  from smtplib import SMTP import ssl  from email.mime.text import MIMEText  SMTPserver = 'smtp.sendpost.io' PORT = 587 sender =     'erlich@piedpiper.com' destination = ['gilfoyle@piedpiper.com']  USERNAME = \"<username>\" PASSWORD = \"<password>\"  # typical values for text_subtype are plain, html, xml text_subtype = 'plain'  content=\"\"\"\\ Test message \"\"\"  subject=\"Sent from Python\"  try: msg = MIMEText(content, text_subtype) msg['Subject']= subject msg['From']   = sender  conn = SMTP(SMTPserver, PORT) conn.ehlo() context = ssl.create_default_context() conn.starttls(context=context)  # upgrade to tls conn.ehlo() conn.set_debuglevel(True) conn.login(USERNAME, PASSWORD)  try: resp = conn.sendmail(sender, destination, msg.as_string()) print(\"Send Mail Response: \", resp) except Exception as e: print(\"Send Email Error: \", e) finally: conn.quit()  except Exception as e: print(\"Error:\", e) ``` For Golang ```go package main  import ( \"fmt\" \"net/smtp\" \"os\" )  // Sending Email Using Smtp in Golang  func main() {  username := \"<username>\" password := \"<password>\"  from := \"erlich@piedpiper.com\" toList := []string{\"gilfoyle@piedpiper.com\"} host := \"smtp.sendpost.io\" port := \"587\" // recommended  // This is the message to send in the mail msg := \"Hello geeks!!!\"  // We can't send strings directly in mail, // strings need to be converted into slice bytes body := []byte(msg)  // PlainAuth uses the given username and password to // authenticate to host and act as identity. // Usually identity should be the empty string, // to act as username. auth := smtp.PlainAuth(\"\", username, password, host)  // SendMail uses TLS connection to send the mail // The email is sent to all address in the toList, // the body should be of type bytes, not strings // This returns error if any occured. err := smtp.SendMail(host+\":\"+port, auth, from, toList, body)  // handling the errors if err != nil { fmt.Println(err) os.Exit(1) }  fmt.Println(\"Successfully sent mail to all user in toList\") }  ``` For Java ```java // implementation 'com.sun.mail:javax.mail:1.6.2'  import java.util.Properties;  import javax.mail.Message; import javax.mail.Session; import javax.mail.Transport; import javax.mail.internet.InternetAddress; import javax.mail.internet.MimeMessage;  public class SMTPConnect {  // This address must be verified. static final String FROM = \"erlich@piedpiper.com\"; static final String FROMNAME = \"Erlich Bachman\";  // Replace recipient@example.com with a \"To\" address. If your account // is still in the sandbox, this address must be verified. static final String TO = \"gilfoyle@piedpiper.com\";  // Replace smtp_username with your SendPost SMTP user name. static final String SMTP_USERNAME = \"<username>\";  // Replace smtp_password with your SendPost SMTP password. static final String SMTP_PASSWORD = \"<password>\";  // SMTP Host Name static final String HOST = \"smtp.sendpost.io\";  // The port you will connect to on SendPost SMTP Endpoint. static final int PORT = 587;  static final String SUBJECT = \"SendPost SMTP Test (SMTP interface accessed using Java)\";  static final String BODY = String.join( System.getProperty(\"line.separator\"), \"<h1>SendPost SMTP Test</h1>\", \"<p>This email was sent with SendPost using the \", \"<a href='https://github.com/eclipse-ee4j/mail'>Javamail Package</a>\", \" for <a href='https://www.java.com'>Java</a>.\" );  public static void main(String[] args) throws Exception {  // Create a Properties object to contain connection configuration information. Properties props = System.getProperties(); props.put(\"mail.transport.protocol\", \"smtp\"); props.put(\"mail.smtp.port\", PORT); props.put(\"mail.smtp.starttls.enable\", \"true\"); props.put(\"mail.smtp.debug\", \"true\"); props.put(\"mail.smtp.auth\", \"true\");  // Create a Session object to represent a mail session with the specified properties. Session session = Session.getDefaultInstance(props);  // Create a message with the specified information. MimeMessage msg = new MimeMessage(session); msg.setFrom(new InternetAddress(FROM,FROMNAME)); msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO)); msg.setSubject(SUBJECT); msg.setContent(BODY,\"text/html\");  // Create a transport. Transport transport = session.getTransport();  // Send the message. try { System.out.println(\"Sending...\");  // Connect to SendPost SMTP using the SMTP username and password you specified above. transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);  // Send the email. transport.sendMessage(msg, msg.getAllRecipients()); System.out.println(\"Email sent!\");  } catch (Exception ex) {  System.out.println(\"The email was not sent.\"); System.out.println(\"Error message: \" + ex.getMessage()); System.out.println(ex); } // Close and terminate the connection. } } ```  Many programming languages support sending email using SMTP. This capability might be built into the programming language itself, or it might be available as an add-on, plug-in, or library. You can take advantage of this capability by sending email through SendPost from within application programs that you write.  We have provided examples in Python3, Golang, Java, PHP, JS. 
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package sendpost_java_sdk;

import sendpost_java_sdk.ApiCallback;
import sendpost_java_sdk.ApiClient;
import sendpost_java_sdk.ApiException;
import sendpost_java_sdk.ApiResponse;
import sendpost_java_sdk.Configuration;
import sendpost_java_sdk.Pair;
import sendpost_java_sdk.ProgressRequestBody;
import sendpost_java_sdk.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import sendpost_java_sdk.CreateSubAccountRequest;
import sendpost_java_sdk.DeleteSubAccountResponse;
import sendpost_java_sdk.SubAccount;
import sendpost_java_sdk.UpdateSubAccount;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubAccountApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public SubAccountApi() {
        this(Configuration.getDefaultApiClient());
    }

    public SubAccountApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    /**
     * Build call for createSubAccount
     * @param createSubAccountRequest  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Sub-account successfully created. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden, sub-account with the same name already exists. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createSubAccountCall(@javax.annotation.Nonnull CreateSubAccountRequest createSubAccountRequest, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = createSubAccountRequest;

        // create path and map variables
        String localVarPath = "/account/subaccount/";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "accountAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createSubAccountValidateBeforeCall(@javax.annotation.Nonnull CreateSubAccountRequest createSubAccountRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'createSubAccountRequest' is set
        if (createSubAccountRequest == null) {
            throw new ApiException("Missing the required parameter 'createSubAccountRequest' when calling createSubAccount(Async)");
        }

        return createSubAccountCall(createSubAccountRequest, _callback);

    }

    /**
     * Create Sub-Account
     * Creates a new sub-account under the current account.
     * @param createSubAccountRequest  (required)
     * @return SubAccount
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Sub-account successfully created. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden, sub-account with the same name already exists. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public SubAccount createSubAccount(@javax.annotation.Nonnull CreateSubAccountRequest createSubAccountRequest) throws ApiException {
        ApiResponse<SubAccount> localVarResp = createSubAccountWithHttpInfo(createSubAccountRequest);
        return localVarResp.getData();
    }

    /**
     * Create Sub-Account
     * Creates a new sub-account under the current account.
     * @param createSubAccountRequest  (required)
     * @return ApiResponse&lt;SubAccount&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Sub-account successfully created. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden, sub-account with the same name already exists. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<SubAccount> createSubAccountWithHttpInfo(@javax.annotation.Nonnull CreateSubAccountRequest createSubAccountRequest) throws ApiException {
        okhttp3.Call localVarCall = createSubAccountValidateBeforeCall(createSubAccountRequest, null);
        Type localVarReturnType = new TypeToken<SubAccount>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Create Sub-Account (asynchronously)
     * Creates a new sub-account under the current account.
     * @param createSubAccountRequest  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Sub-account successfully created. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Forbidden, sub-account with the same name already exists. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createSubAccountAsync(@javax.annotation.Nonnull CreateSubAccountRequest createSubAccountRequest, final ApiCallback<SubAccount> _callback) throws ApiException {

        okhttp3.Call localVarCall = createSubAccountValidateBeforeCall(createSubAccountRequest, _callback);
        Type localVarReturnType = new TypeToken<SubAccount>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteSubAccount
     * @param subaccountId The ID of the sub-account to delete. (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Sub-account successfully deleted. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. Cannot delete the default sub-account. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. Invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteSubAccountCall(@javax.annotation.Nonnull Integer subaccountId, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/account/subaccount/{subaccount_id}"
            .replace("{" + "subaccount_id" + "}", localVarApiClient.escapeString(subaccountId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "accountAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteSubAccountValidateBeforeCall(@javax.annotation.Nonnull Integer subaccountId, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'subaccountId' is set
        if (subaccountId == null) {
            throw new ApiException("Missing the required parameter 'subaccountId' when calling deleteSubAccount(Async)");
        }

        return deleteSubAccountCall(subaccountId, _callback);

    }

    /**
     * Delete Sub-Account
     * Deletes a specific sub-account by its ID.
     * @param subaccountId The ID of the sub-account to delete. (required)
     * @return DeleteSubAccountResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Sub-account successfully deleted. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. Cannot delete the default sub-account. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. Invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public DeleteSubAccountResponse deleteSubAccount(@javax.annotation.Nonnull Integer subaccountId) throws ApiException {
        ApiResponse<DeleteSubAccountResponse> localVarResp = deleteSubAccountWithHttpInfo(subaccountId);
        return localVarResp.getData();
    }

    /**
     * Delete Sub-Account
     * Deletes a specific sub-account by its ID.
     * @param subaccountId The ID of the sub-account to delete. (required)
     * @return ApiResponse&lt;DeleteSubAccountResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Sub-account successfully deleted. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. Cannot delete the default sub-account. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. Invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<DeleteSubAccountResponse> deleteSubAccountWithHttpInfo(@javax.annotation.Nonnull Integer subaccountId) throws ApiException {
        okhttp3.Call localVarCall = deleteSubAccountValidateBeforeCall(subaccountId, null);
        Type localVarReturnType = new TypeToken<DeleteSubAccountResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Delete Sub-Account (asynchronously)
     * Deletes a specific sub-account by its ID.
     * @param subaccountId The ID of the sub-account to delete. (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Sub-account successfully deleted. </td><td>  -  </td></tr>
        <tr><td> 406 </td><td> Not Acceptable. Cannot delete the default sub-account. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized. Invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteSubAccountAsync(@javax.annotation.Nonnull Integer subaccountId, final ApiCallback<DeleteSubAccountResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteSubAccountValidateBeforeCall(subaccountId, _callback);
        Type localVarReturnType = new TypeToken<DeleteSubAccountResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getAllSubAccounts
     * @param limit Number of records to return per request. (optional)
     * @param offset Number of initial records to skip. (optional)
     * @param search Case-insensitive search against the sub-account name. (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved sub-accounts. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAllSubAccountsCall(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String search, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/account/subaccount/";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (limit != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("limit", limit));
        }

        if (offset != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("offset", offset));
        }

        if (search != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("search", search));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "accountAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getAllSubAccountsValidateBeforeCall(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String search, final ApiCallback _callback) throws ApiException {
        return getAllSubAccountsCall(limit, offset, search, _callback);

    }

    /**
     * List Sub-Accounts
     * Retrieves a list of all sub-accounts associated with a specific account.
     * @param limit Number of records to return per request. (optional)
     * @param offset Number of initial records to skip. (optional)
     * @param search Case-insensitive search against the sub-account name. (optional)
     * @return List&lt;SubAccount&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved sub-accounts. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public List<SubAccount> getAllSubAccounts(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String search) throws ApiException {
        ApiResponse<List<SubAccount>> localVarResp = getAllSubAccountsWithHttpInfo(limit, offset, search);
        return localVarResp.getData();
    }

    /**
     * List Sub-Accounts
     * Retrieves a list of all sub-accounts associated with a specific account.
     * @param limit Number of records to return per request. (optional)
     * @param offset Number of initial records to skip. (optional)
     * @param search Case-insensitive search against the sub-account name. (optional)
     * @return ApiResponse&lt;List&lt;SubAccount&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved sub-accounts. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<SubAccount>> getAllSubAccountsWithHttpInfo(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String search) throws ApiException {
        okhttp3.Call localVarCall = getAllSubAccountsValidateBeforeCall(limit, offset, search, null);
        Type localVarReturnType = new TypeToken<List<SubAccount>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * List Sub-Accounts (asynchronously)
     * Retrieves a list of all sub-accounts associated with a specific account.
     * @param limit Number of records to return per request. (optional)
     * @param offset Number of initial records to skip. (optional)
     * @param search Case-insensitive search against the sub-account name. (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved sub-accounts. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAllSubAccountsAsync(@javax.annotation.Nullable Integer limit, @javax.annotation.Nullable Integer offset, @javax.annotation.Nullable String search, final ApiCallback<List<SubAccount>> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAllSubAccountsValidateBeforeCall(limit, offset, search, _callback);
        Type localVarReturnType = new TypeToken<List<SubAccount>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getSubAccount
     * @param subaccountId The ID of the sub-account to retrieve. (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the sub-account. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Sub-account not found. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSubAccountCall(@javax.annotation.Nonnull Integer subaccountId, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/account/subaccount/{subaccount_id}"
            .replace("{" + "subaccount_id" + "}", localVarApiClient.escapeString(subaccountId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "accountAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getSubAccountValidateBeforeCall(@javax.annotation.Nonnull Integer subaccountId, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'subaccountId' is set
        if (subaccountId == null) {
            throw new ApiException("Missing the required parameter 'subaccountId' when calling getSubAccount(Async)");
        }

        return getSubAccountCall(subaccountId, _callback);

    }

    /**
     * Get Sub-Account
     * Retrieves a specific sub-account by its ID.
     * @param subaccountId The ID of the sub-account to retrieve. (required)
     * @return SubAccount
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the sub-account. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Sub-account not found. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public SubAccount getSubAccount(@javax.annotation.Nonnull Integer subaccountId) throws ApiException {
        ApiResponse<SubAccount> localVarResp = getSubAccountWithHttpInfo(subaccountId);
        return localVarResp.getData();
    }

    /**
     * Get Sub-Account
     * Retrieves a specific sub-account by its ID.
     * @param subaccountId The ID of the sub-account to retrieve. (required)
     * @return ApiResponse&lt;SubAccount&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the sub-account. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Sub-account not found. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<SubAccount> getSubAccountWithHttpInfo(@javax.annotation.Nonnull Integer subaccountId) throws ApiException {
        okhttp3.Call localVarCall = getSubAccountValidateBeforeCall(subaccountId, null);
        Type localVarReturnType = new TypeToken<SubAccount>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get Sub-Account (asynchronously)
     * Retrieves a specific sub-account by its ID.
     * @param subaccountId The ID of the sub-account to retrieve. (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the sub-account. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Sub-account not found. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSubAccountAsync(@javax.annotation.Nonnull Integer subaccountId, final ApiCallback<SubAccount> _callback) throws ApiException {

        okhttp3.Call localVarCall = getSubAccountValidateBeforeCall(subaccountId, _callback);
        Type localVarReturnType = new TypeToken<SubAccount>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for updateSubAccount
     * @param subaccountId The ID of the sub-account to update. (required)
     * @param updateSubAccount  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Sub-account successfully updated. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Sub-account not found. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call updateSubAccountCall(@javax.annotation.Nonnull Integer subaccountId, @javax.annotation.Nonnull UpdateSubAccount updateSubAccount, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = updateSubAccount;

        // create path and map variables
        String localVarPath = "/account/subaccount/{subaccount_id}"
            .replace("{" + "subaccount_id" + "}", localVarApiClient.escapeString(subaccountId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "accountAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call updateSubAccountValidateBeforeCall(@javax.annotation.Nonnull Integer subaccountId, @javax.annotation.Nonnull UpdateSubAccount updateSubAccount, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'subaccountId' is set
        if (subaccountId == null) {
            throw new ApiException("Missing the required parameter 'subaccountId' when calling updateSubAccount(Async)");
        }

        // verify the required parameter 'updateSubAccount' is set
        if (updateSubAccount == null) {
            throw new ApiException("Missing the required parameter 'updateSubAccount' when calling updateSubAccount(Async)");
        }

        return updateSubAccountCall(subaccountId, updateSubAccount, _callback);

    }

    /**
     * Update Sub-Account
     * Updates the details of an existing sub-account.
     * @param subaccountId The ID of the sub-account to update. (required)
     * @param updateSubAccount  (required)
     * @return SubAccount
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Sub-account successfully updated. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Sub-account not found. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public SubAccount updateSubAccount(@javax.annotation.Nonnull Integer subaccountId, @javax.annotation.Nonnull UpdateSubAccount updateSubAccount) throws ApiException {
        ApiResponse<SubAccount> localVarResp = updateSubAccountWithHttpInfo(subaccountId, updateSubAccount);
        return localVarResp.getData();
    }

    /**
     * Update Sub-Account
     * Updates the details of an existing sub-account.
     * @param subaccountId The ID of the sub-account to update. (required)
     * @param updateSubAccount  (required)
     * @return ApiResponse&lt;SubAccount&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Sub-account successfully updated. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Sub-account not found. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<SubAccount> updateSubAccountWithHttpInfo(@javax.annotation.Nonnull Integer subaccountId, @javax.annotation.Nonnull UpdateSubAccount updateSubAccount) throws ApiException {
        okhttp3.Call localVarCall = updateSubAccountValidateBeforeCall(subaccountId, updateSubAccount, null);
        Type localVarReturnType = new TypeToken<SubAccount>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Update Sub-Account (asynchronously)
     * Updates the details of an existing sub-account.
     * @param subaccountId The ID of the sub-account to update. (required)
     * @param updateSubAccount  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Sub-account successfully updated. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Sub-account not found. </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized, invalid API key. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call updateSubAccountAsync(@javax.annotation.Nonnull Integer subaccountId, @javax.annotation.Nonnull UpdateSubAccount updateSubAccount, final ApiCallback<SubAccount> _callback) throws ApiException {

        okhttp3.Call localVarCall = updateSubAccountValidateBeforeCall(subaccountId, updateSubAccount, _callback);
        Type localVarReturnType = new TypeToken<SubAccount>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
