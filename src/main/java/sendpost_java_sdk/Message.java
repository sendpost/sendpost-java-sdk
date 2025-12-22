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

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sendpost_java_sdk.MessageHeaderTo;
import sendpost_java_sdk.MessageTo;
import sendpost_java_sdk.Person;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sendpost_java_sdk.JSON;

/**
 * Message
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-12-22T15:50:51.966722+05:30[Asia/Kolkata]", comments = "Generator version: 7.13.0")
public class Message {
  public static final String SERIALIZED_NAME_MESSAGE_I_D = "messageID";
  @SerializedName(SERIALIZED_NAME_MESSAGE_I_D)
  @javax.annotation.Nullable
  private String messageID;

  public static final String SERIALIZED_NAME_ACCOUNT_I_D = "accountID";
  @SerializedName(SERIALIZED_NAME_ACCOUNT_I_D)
  @javax.annotation.Nullable
  private Integer accountID;

  public static final String SERIALIZED_NAME_SUB_ACCOUNT_I_D = "subAccountID";
  @SerializedName(SERIALIZED_NAME_SUB_ACCOUNT_I_D)
  @javax.annotation.Nullable
  private Integer subAccountID;

  public static final String SERIALIZED_NAME_IP_I_D = "ipID";
  @SerializedName(SERIALIZED_NAME_IP_I_D)
  @javax.annotation.Nullable
  private Integer ipID;

  public static final String SERIALIZED_NAME_ACCOUNT_I_P_POOL_I_D = "accountIPPoolID";
  @SerializedName(SERIALIZED_NAME_ACCOUNT_I_P_POOL_I_D)
  @javax.annotation.Nullable
  private Integer accountIPPoolID;

  public static final String SERIALIZED_NAME_PUBLIC_I_P = "publicIP";
  @SerializedName(SERIALIZED_NAME_PUBLIC_I_P)
  @javax.annotation.Nullable
  private String publicIP;

  public static final String SERIALIZED_NAME_LOCAL_I_P = "localIP";
  @SerializedName(SERIALIZED_NAME_LOCAL_I_P)
  @javax.annotation.Nullable
  private String localIP;

  public static final String SERIALIZED_NAME_EMAIL_TYPE = "emailType";
  @SerializedName(SERIALIZED_NAME_EMAIL_TYPE)
  @javax.annotation.Nullable
  private String emailType;

  public static final String SERIALIZED_NAME_SUBMITTED_AT = "submittedAt";
  @SerializedName(SERIALIZED_NAME_SUBMITTED_AT)
  @javax.annotation.Nullable
  private Integer submittedAt;

  public static final String SERIALIZED_NAME_FROM = "from";
  @SerializedName(SERIALIZED_NAME_FROM)
  @javax.annotation.Nullable
  private Person from;

  public static final String SERIALIZED_NAME_REPLY_TO = "replyTo";
  @SerializedName(SERIALIZED_NAME_REPLY_TO)
  @javax.annotation.Nullable
  private Person replyTo;

  public static final String SERIALIZED_NAME_TO = "to";
  @SerializedName(SERIALIZED_NAME_TO)
  @javax.annotation.Nullable
  private MessageTo to;

  public static final String SERIALIZED_NAME_HEADER_TO = "headerTo";
  @SerializedName(SERIALIZED_NAME_HEADER_TO)
  @javax.annotation.Nullable
  private MessageHeaderTo headerTo;

  public static final String SERIALIZED_NAME_HEADER_CC = "headerCc";
  @SerializedName(SERIALIZED_NAME_HEADER_CC)
  @javax.annotation.Nullable
  private List<String> headerCc = new ArrayList<>();

  public static final String SERIALIZED_NAME_HEADER_BCC = "headerBcc";
  @SerializedName(SERIALIZED_NAME_HEADER_BCC)
  @javax.annotation.Nullable
  private List<String> headerBcc = new ArrayList<>();

  public static final String SERIALIZED_NAME_ATTACHMENTS = "attachments";
  @SerializedName(SERIALIZED_NAME_ATTACHMENTS)
  @javax.annotation.Nullable
  private List<String> attachments = new ArrayList<>();

  public static final String SERIALIZED_NAME_GROUPS = "groups";
  @SerializedName(SERIALIZED_NAME_GROUPS)
  @javax.annotation.Nullable
  private List<String> groups = new ArrayList<>();

  public static final String SERIALIZED_NAME_IP_POOL = "ipPool";
  @SerializedName(SERIALIZED_NAME_IP_POOL)
  @javax.annotation.Nullable
  private String ipPool;

  public static final String SERIALIZED_NAME_HEADERS = "headers";
  @SerializedName(SERIALIZED_NAME_HEADERS)
  @javax.annotation.Nullable
  private Map<String, String> headers = new HashMap<>();

  public static final String SERIALIZED_NAME_CUSTOM_FIELDS = "customFields";
  @SerializedName(SERIALIZED_NAME_CUSTOM_FIELDS)
  @javax.annotation.Nullable
  private Map<String, String> customFields = new HashMap<>();

  public static final String SERIALIZED_NAME_SUBJECT = "subject";
  @SerializedName(SERIALIZED_NAME_SUBJECT)
  @javax.annotation.Nullable
  private String subject;

  public static final String SERIALIZED_NAME_PRE_TEXT = "preText";
  @SerializedName(SERIALIZED_NAME_PRE_TEXT)
  @javax.annotation.Nullable
  private String preText;

  public static final String SERIALIZED_NAME_HTML_BODY = "htmlBody";
  @SerializedName(SERIALIZED_NAME_HTML_BODY)
  @javax.annotation.Nullable
  private String htmlBody;

  public static final String SERIALIZED_NAME_TEXT_BODY = "textBody";
  @SerializedName(SERIALIZED_NAME_TEXT_BODY)
  @javax.annotation.Nullable
  private String textBody;

  public static final String SERIALIZED_NAME_AMP_BODY = "ampBody";
  @SerializedName(SERIALIZED_NAME_AMP_BODY)
  @javax.annotation.Nullable
  private String ampBody;

  public static final String SERIALIZED_NAME_TRACK_OPENS = "trackOpens";
  @SerializedName(SERIALIZED_NAME_TRACK_OPENS)
  @javax.annotation.Nullable
  private Boolean trackOpens;

  public static final String SERIALIZED_NAME_TRACK_CLICKS = "trackClicks";
  @SerializedName(SERIALIZED_NAME_TRACK_CLICKS)
  @javax.annotation.Nullable
  private Boolean trackClicks;

  public static final String SERIALIZED_NAME_ATTEMPT = "attempt";
  @SerializedName(SERIALIZED_NAME_ATTEMPT)
  @javax.annotation.Nullable
  private Integer attempt;

  public static final String SERIALIZED_NAME_WEBHOOK_ENDPOINT = "webhookEndpoint";
  @SerializedName(SERIALIZED_NAME_WEBHOOK_ENDPOINT)
  @javax.annotation.Nullable
  private String webhookEndpoint;

  public static final String SERIALIZED_NAME_MX_RECORDS = "mxRecords";
  @SerializedName(SERIALIZED_NAME_MX_RECORDS)
  @javax.annotation.Nullable
  private List<String> mxRecords = new ArrayList<>();

  public Message() {
  }

  public Message messageID(@javax.annotation.Nullable String messageID) {
    this.messageID = messageID;
    return this;
  }

  /**
   * Unique ID for the message.
   * @return messageID
   */
  @javax.annotation.Nullable
  public String getMessageID() {
    return messageID;
  }

  public void setMessageID(@javax.annotation.Nullable String messageID) {
    this.messageID = messageID;
  }


  public Message accountID(@javax.annotation.Nullable Integer accountID) {
    this.accountID = accountID;
    return this;
  }

  /**
   * Account ID associated with the message.
   * @return accountID
   */
  @javax.annotation.Nullable
  public Integer getAccountID() {
    return accountID;
  }

  public void setAccountID(@javax.annotation.Nullable Integer accountID) {
    this.accountID = accountID;
  }


  public Message subAccountID(@javax.annotation.Nullable Integer subAccountID) {
    this.subAccountID = subAccountID;
    return this;
  }

  /**
   * Sub-account ID associated with the message.
   * @return subAccountID
   */
  @javax.annotation.Nullable
  public Integer getSubAccountID() {
    return subAccountID;
  }

  public void setSubAccountID(@javax.annotation.Nullable Integer subAccountID) {
    this.subAccountID = subAccountID;
  }


  public Message ipID(@javax.annotation.Nullable Integer ipID) {
    this.ipID = ipID;
    return this;
  }

  /**
   * IP ID used for sending the message.
   * @return ipID
   */
  @javax.annotation.Nullable
  public Integer getIpID() {
    return ipID;
  }

  public void setIpID(@javax.annotation.Nullable Integer ipID) {
    this.ipID = ipID;
  }


  public Message accountIPPoolID(@javax.annotation.Nullable Integer accountIPPoolID) {
    this.accountIPPoolID = accountIPPoolID;
    return this;
  }

  /**
   * Account IP Pool ID associated with the message.
   * @return accountIPPoolID
   */
  @javax.annotation.Nullable
  public Integer getAccountIPPoolID() {
    return accountIPPoolID;
  }

  public void setAccountIPPoolID(@javax.annotation.Nullable Integer accountIPPoolID) {
    this.accountIPPoolID = accountIPPoolID;
  }


  public Message publicIP(@javax.annotation.Nullable String publicIP) {
    this.publicIP = publicIP;
    return this;
  }

  /**
   * Public IP address used for sending the message.
   * @return publicIP
   */
  @javax.annotation.Nullable
  public String getPublicIP() {
    return publicIP;
  }

  public void setPublicIP(@javax.annotation.Nullable String publicIP) {
    this.publicIP = publicIP;
  }


  public Message localIP(@javax.annotation.Nullable String localIP) {
    this.localIP = localIP;
    return this;
  }

  /**
   * Local IP address used for sending the message.
   * @return localIP
   */
  @javax.annotation.Nullable
  public String getLocalIP() {
    return localIP;
  }

  public void setLocalIP(@javax.annotation.Nullable String localIP) {
    this.localIP = localIP;
  }


  public Message emailType(@javax.annotation.Nullable String emailType) {
    this.emailType = emailType;
    return this;
  }

  /**
   * Type of email service used.
   * @return emailType
   */
  @javax.annotation.Nullable
  public String getEmailType() {
    return emailType;
  }

  public void setEmailType(@javax.annotation.Nullable String emailType) {
    this.emailType = emailType;
  }


  public Message submittedAt(@javax.annotation.Nullable Integer submittedAt) {
    this.submittedAt = submittedAt;
    return this;
  }

  /**
   * UNIX epoch nano timestamp when message was submitted.
   * @return submittedAt
   */
  @javax.annotation.Nullable
  public Integer getSubmittedAt() {
    return submittedAt;
  }

  public void setSubmittedAt(@javax.annotation.Nullable Integer submittedAt) {
    this.submittedAt = submittedAt;
  }


  public Message from(@javax.annotation.Nullable Person from) {
    this.from = from;
    return this;
  }

  /**
   * Object comprising name and email address of the sender
   * @return from
   */
  @javax.annotation.Nullable
  public Person getFrom() {
    return from;
  }

  public void setFrom(@javax.annotation.Nullable Person from) {
    this.from = from;
  }


  public Message replyTo(@javax.annotation.Nullable Person replyTo) {
    this.replyTo = replyTo;
    return this;
  }

  /**
   * Object comprising name and email addresses to which email replies will go to
   * @return replyTo
   */
  @javax.annotation.Nullable
  public Person getReplyTo() {
    return replyTo;
  }

  public void setReplyTo(@javax.annotation.Nullable Person replyTo) {
    this.replyTo = replyTo;
  }


  public Message to(@javax.annotation.Nullable MessageTo to) {
    this.to = to;
    return this;
  }

  /**
   * Get to
   * @return to
   */
  @javax.annotation.Nullable
  public MessageTo getTo() {
    return to;
  }

  public void setTo(@javax.annotation.Nullable MessageTo to) {
    this.to = to;
  }


  public Message headerTo(@javax.annotation.Nullable MessageHeaderTo headerTo) {
    this.headerTo = headerTo;
    return this;
  }

  /**
   * Get headerTo
   * @return headerTo
   */
  @javax.annotation.Nullable
  public MessageHeaderTo getHeaderTo() {
    return headerTo;
  }

  public void setHeaderTo(@javax.annotation.Nullable MessageHeaderTo headerTo) {
    this.headerTo = headerTo;
  }


  public Message headerCc(@javax.annotation.Nullable List<String> headerCc) {
    this.headerCc = headerCc;
    return this;
  }

  public Message addHeaderCcItem(String headerCcItem) {
    if (this.headerCc == null) {
      this.headerCc = new ArrayList<>();
    }
    this.headerCc.add(headerCcItem);
    return this;
  }

  /**
   * List of CC recipients from email headers
   * @return headerCc
   */
  @javax.annotation.Nullable
  public List<String> getHeaderCc() {
    return headerCc;
  }

  public void setHeaderCc(@javax.annotation.Nullable List<String> headerCc) {
    this.headerCc = headerCc;
  }


  public Message headerBcc(@javax.annotation.Nullable List<String> headerBcc) {
    this.headerBcc = headerBcc;
    return this;
  }

  public Message addHeaderBccItem(String headerBccItem) {
    if (this.headerBcc == null) {
      this.headerBcc = new ArrayList<>();
    }
    this.headerBcc.add(headerBccItem);
    return this;
  }

  /**
   * List of BCC recipients from email headers
   * @return headerBcc
   */
  @javax.annotation.Nullable
  public List<String> getHeaderBcc() {
    return headerBcc;
  }

  public void setHeaderBcc(@javax.annotation.Nullable List<String> headerBcc) {
    this.headerBcc = headerBcc;
  }


  public Message attachments(@javax.annotation.Nullable List<String> attachments) {
    this.attachments = attachments;
    return this;
  }

  public Message addAttachmentsItem(String attachmentsItem) {
    if (this.attachments == null) {
      this.attachments = new ArrayList<>();
    }
    this.attachments.add(attachmentsItem);
    return this;
  }

  /**
   * List of attachments
   * @return attachments
   */
  @javax.annotation.Nullable
  public List<String> getAttachments() {
    return attachments;
  }

  public void setAttachments(@javax.annotation.Nullable List<String> attachments) {
    this.attachments = attachments;
  }


  public Message groups(@javax.annotation.Nullable List<String> groups) {
    this.groups = groups;
    return this;
  }

  public Message addGroupsItem(String groupsItem) {
    if (this.groups == null) {
      this.groups = new ArrayList<>();
    }
    this.groups.add(groupsItem);
    return this;
  }

  /**
   * List of groups associated with the message
   * @return groups
   */
  @javax.annotation.Nullable
  public List<String> getGroups() {
    return groups;
  }

  public void setGroups(@javax.annotation.Nullable List<String> groups) {
    this.groups = groups;
  }


  public Message ipPool(@javax.annotation.Nullable String ipPool) {
    this.ipPool = ipPool;
    return this;
  }

  /**
   * IP Pool from which emails will go out. Relevant only for customers on dedicated IP plans.
   * @return ipPool
   */
  @javax.annotation.Nullable
  public String getIpPool() {
    return ipPool;
  }

  public void setIpPool(@javax.annotation.Nullable String ipPool) {
    this.ipPool = ipPool;
  }


  public Message headers(@javax.annotation.Nullable Map<String, String> headers) {
    this.headers = headers;
    return this;
  }

  public Message putHeadersItem(String key, String headersItem) {
    if (this.headers == null) {
      this.headers = new HashMap<>();
    }
    this.headers.put(key, headersItem);
    return this;
  }

  /**
   * Key-Value pair which are added to every email message being sent and also with webhooks triggered on events such as email delivered, open, click etc. They are useful to identify email, recipient etc. in your internal system
   * @return headers
   */
  @javax.annotation.Nullable
  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(@javax.annotation.Nullable Map<String, String> headers) {
    this.headers = headers;
  }


  public Message customFields(@javax.annotation.Nullable Map<String, String> customFields) {
    this.customFields = customFields;
    return this;
  }

  public Message putCustomFieldsItem(String key, String customFieldsItem) {
    if (this.customFields == null) {
      this.customFields = new HashMap<>();
    }
    this.customFields.put(key, customFieldsItem);
    return this;
  }

  /**
   * Key-Value pair of custom fields at message level
   * @return customFields
   */
  @javax.annotation.Nullable
  public Map<String, String> getCustomFields() {
    return customFields;
  }

  public void setCustomFields(@javax.annotation.Nullable Map<String, String> customFields) {
    this.customFields = customFields;
  }


  public Message subject(@javax.annotation.Nullable String subject) {
    this.subject = subject;
    return this;
  }

  /**
   * Email subject line.
   * @return subject
   */
  @javax.annotation.Nullable
  public String getSubject() {
    return subject;
  }

  public void setSubject(@javax.annotation.Nullable String subject) {
    this.subject = subject;
  }


  public Message preText(@javax.annotation.Nullable String preText) {
    this.preText = preText;
    return this;
  }

  /**
   * Text which appears on mobile right after email subject line.
   * @return preText
   */
  @javax.annotation.Nullable
  public String getPreText() {
    return preText;
  }

  public void setPreText(@javax.annotation.Nullable String preText) {
    this.preText = preText;
  }


  public Message htmlBody(@javax.annotation.Nullable String htmlBody) {
    this.htmlBody = htmlBody;
    return this;
  }

  /**
   * HTML email content.
   * @return htmlBody
   */
  @javax.annotation.Nullable
  public String getHtmlBody() {
    return htmlBody;
  }

  public void setHtmlBody(@javax.annotation.Nullable String htmlBody) {
    this.htmlBody = htmlBody;
  }


  public Message textBody(@javax.annotation.Nullable String textBody) {
    this.textBody = textBody;
    return this;
  }

  /**
   * Text email content.
   * @return textBody
   */
  @javax.annotation.Nullable
  public String getTextBody() {
    return textBody;
  }

  public void setTextBody(@javax.annotation.Nullable String textBody) {
    this.textBody = textBody;
  }


  public Message ampBody(@javax.annotation.Nullable String ampBody) {
    this.ampBody = ampBody;
    return this;
  }

  /**
   * AMP email content.
   * @return ampBody
   */
  @javax.annotation.Nullable
  public String getAmpBody() {
    return ampBody;
  }

  public void setAmpBody(@javax.annotation.Nullable String ampBody) {
    this.ampBody = ampBody;
  }


  public Message trackOpens(@javax.annotation.Nullable Boolean trackOpens) {
    this.trackOpens = trackOpens;
    return this;
  }

  /**
   * Indicates if email opens need to be tracked.
   * @return trackOpens
   */
  @javax.annotation.Nullable
  public Boolean getTrackOpens() {
    return trackOpens;
  }

  public void setTrackOpens(@javax.annotation.Nullable Boolean trackOpens) {
    this.trackOpens = trackOpens;
  }


  public Message trackClicks(@javax.annotation.Nullable Boolean trackClicks) {
    this.trackClicks = trackClicks;
    return this;
  }

  /**
   * Indicates if email clicks need to be tracked.
   * @return trackClicks
   */
  @javax.annotation.Nullable
  public Boolean getTrackClicks() {
    return trackClicks;
  }

  public void setTrackClicks(@javax.annotation.Nullable Boolean trackClicks) {
    this.trackClicks = trackClicks;
  }


  public Message attempt(@javax.annotation.Nullable Integer attempt) {
    this.attempt = attempt;
    return this;
  }

  /**
   * Number of delivery attempts made for the message.
   * @return attempt
   */
  @javax.annotation.Nullable
  public Integer getAttempt() {
    return attempt;
  }

  public void setAttempt(@javax.annotation.Nullable Integer attempt) {
    this.attempt = attempt;
  }


  public Message webhookEndpoint(@javax.annotation.Nullable String webhookEndpoint) {
    this.webhookEndpoint = webhookEndpoint;
    return this;
  }

  /**
   * Webhook endpoint URL for the message.
   * @return webhookEndpoint
   */
  @javax.annotation.Nullable
  public String getWebhookEndpoint() {
    return webhookEndpoint;
  }

  public void setWebhookEndpoint(@javax.annotation.Nullable String webhookEndpoint) {
    this.webhookEndpoint = webhookEndpoint;
  }


  public Message mxRecords(@javax.annotation.Nullable List<String> mxRecords) {
    this.mxRecords = mxRecords;
    return this;
  }

  public Message addMxRecordsItem(String mxRecordsItem) {
    if (this.mxRecords == null) {
      this.mxRecords = new ArrayList<>();
    }
    this.mxRecords.add(mxRecordsItem);
    return this;
  }

  /**
   * List of MX records for the recipient domain
   * @return mxRecords
   */
  @javax.annotation.Nullable
  public List<String> getMxRecords() {
    return mxRecords;
  }

  public void setMxRecords(@javax.annotation.Nullable List<String> mxRecords) {
    this.mxRecords = mxRecords;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Message message = (Message) o;
    return Objects.equals(this.messageID, message.messageID) &&
        Objects.equals(this.accountID, message.accountID) &&
        Objects.equals(this.subAccountID, message.subAccountID) &&
        Objects.equals(this.ipID, message.ipID) &&
        Objects.equals(this.accountIPPoolID, message.accountIPPoolID) &&
        Objects.equals(this.publicIP, message.publicIP) &&
        Objects.equals(this.localIP, message.localIP) &&
        Objects.equals(this.emailType, message.emailType) &&
        Objects.equals(this.submittedAt, message.submittedAt) &&
        Objects.equals(this.from, message.from) &&
        Objects.equals(this.replyTo, message.replyTo) &&
        Objects.equals(this.to, message.to) &&
        Objects.equals(this.headerTo, message.headerTo) &&
        Objects.equals(this.headerCc, message.headerCc) &&
        Objects.equals(this.headerBcc, message.headerBcc) &&
        Objects.equals(this.attachments, message.attachments) &&
        Objects.equals(this.groups, message.groups) &&
        Objects.equals(this.ipPool, message.ipPool) &&
        Objects.equals(this.headers, message.headers) &&
        Objects.equals(this.customFields, message.customFields) &&
        Objects.equals(this.subject, message.subject) &&
        Objects.equals(this.preText, message.preText) &&
        Objects.equals(this.htmlBody, message.htmlBody) &&
        Objects.equals(this.textBody, message.textBody) &&
        Objects.equals(this.ampBody, message.ampBody) &&
        Objects.equals(this.trackOpens, message.trackOpens) &&
        Objects.equals(this.trackClicks, message.trackClicks) &&
        Objects.equals(this.attempt, message.attempt) &&
        Objects.equals(this.webhookEndpoint, message.webhookEndpoint) &&
        Objects.equals(this.mxRecords, message.mxRecords);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageID, accountID, subAccountID, ipID, accountIPPoolID, publicIP, localIP, emailType, submittedAt, from, replyTo, to, headerTo, headerCc, headerBcc, attachments, groups, ipPool, headers, customFields, subject, preText, htmlBody, textBody, ampBody, trackOpens, trackClicks, attempt, webhookEndpoint, mxRecords);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Message {\n");
    sb.append("    messageID: ").append(toIndentedString(messageID)).append("\n");
    sb.append("    accountID: ").append(toIndentedString(accountID)).append("\n");
    sb.append("    subAccountID: ").append(toIndentedString(subAccountID)).append("\n");
    sb.append("    ipID: ").append(toIndentedString(ipID)).append("\n");
    sb.append("    accountIPPoolID: ").append(toIndentedString(accountIPPoolID)).append("\n");
    sb.append("    publicIP: ").append(toIndentedString(publicIP)).append("\n");
    sb.append("    localIP: ").append(toIndentedString(localIP)).append("\n");
    sb.append("    emailType: ").append(toIndentedString(emailType)).append("\n");
    sb.append("    submittedAt: ").append(toIndentedString(submittedAt)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    replyTo: ").append(toIndentedString(replyTo)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    headerTo: ").append(toIndentedString(headerTo)).append("\n");
    sb.append("    headerCc: ").append(toIndentedString(headerCc)).append("\n");
    sb.append("    headerBcc: ").append(toIndentedString(headerBcc)).append("\n");
    sb.append("    attachments: ").append(toIndentedString(attachments)).append("\n");
    sb.append("    groups: ").append(toIndentedString(groups)).append("\n");
    sb.append("    ipPool: ").append(toIndentedString(ipPool)).append("\n");
    sb.append("    headers: ").append(toIndentedString(headers)).append("\n");
    sb.append("    customFields: ").append(toIndentedString(customFields)).append("\n");
    sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
    sb.append("    preText: ").append(toIndentedString(preText)).append("\n");
    sb.append("    htmlBody: ").append(toIndentedString(htmlBody)).append("\n");
    sb.append("    textBody: ").append(toIndentedString(textBody)).append("\n");
    sb.append("    ampBody: ").append(toIndentedString(ampBody)).append("\n");
    sb.append("    trackOpens: ").append(toIndentedString(trackOpens)).append("\n");
    sb.append("    trackClicks: ").append(toIndentedString(trackClicks)).append("\n");
    sb.append("    attempt: ").append(toIndentedString(attempt)).append("\n");
    sb.append("    webhookEndpoint: ").append(toIndentedString(webhookEndpoint)).append("\n");
    sb.append("    mxRecords: ").append(toIndentedString(mxRecords)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("messageID");
    openapiFields.add("accountID");
    openapiFields.add("subAccountID");
    openapiFields.add("ipID");
    openapiFields.add("accountIPPoolID");
    openapiFields.add("publicIP");
    openapiFields.add("localIP");
    openapiFields.add("emailType");
    openapiFields.add("submittedAt");
    openapiFields.add("from");
    openapiFields.add("replyTo");
    openapiFields.add("to");
    openapiFields.add("headerTo");
    openapiFields.add("headerCc");
    openapiFields.add("headerBcc");
    openapiFields.add("attachments");
    openapiFields.add("groups");
    openapiFields.add("ipPool");
    openapiFields.add("headers");
    openapiFields.add("customFields");
    openapiFields.add("subject");
    openapiFields.add("preText");
    openapiFields.add("htmlBody");
    openapiFields.add("textBody");
    openapiFields.add("ampBody");
    openapiFields.add("trackOpens");
    openapiFields.add("trackClicks");
    openapiFields.add("attempt");
    openapiFields.add("webhookEndpoint");
    openapiFields.add("mxRecords");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to Message
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!Message.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in Message is not found in the empty JSON string", Message.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!Message.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `Message` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("messageID") != null && !jsonObj.get("messageID").isJsonNull()) && !jsonObj.get("messageID").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `messageID` to be a primitive type in the JSON string but got `%s`", jsonObj.get("messageID").toString()));
      }
      if ((jsonObj.get("publicIP") != null && !jsonObj.get("publicIP").isJsonNull()) && !jsonObj.get("publicIP").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `publicIP` to be a primitive type in the JSON string but got `%s`", jsonObj.get("publicIP").toString()));
      }
      if ((jsonObj.get("localIP") != null && !jsonObj.get("localIP").isJsonNull()) && !jsonObj.get("localIP").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `localIP` to be a primitive type in the JSON string but got `%s`", jsonObj.get("localIP").toString()));
      }
      if ((jsonObj.get("emailType") != null && !jsonObj.get("emailType").isJsonNull()) && !jsonObj.get("emailType").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `emailType` to be a primitive type in the JSON string but got `%s`", jsonObj.get("emailType").toString()));
      }
      // validate the optional field `to`
      if (jsonObj.get("to") != null && !jsonObj.get("to").isJsonNull()) {
        MessageTo.validateJsonElement(jsonObj.get("to"));
      }
      // validate the optional field `headerTo`
      if (jsonObj.get("headerTo") != null && !jsonObj.get("headerTo").isJsonNull()) {
        MessageHeaderTo.validateJsonElement(jsonObj.get("headerTo"));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("headerCc") != null && !jsonObj.get("headerCc").isJsonNull() && !jsonObj.get("headerCc").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `headerCc` to be an array in the JSON string but got `%s`", jsonObj.get("headerCc").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("headerBcc") != null && !jsonObj.get("headerBcc").isJsonNull() && !jsonObj.get("headerBcc").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `headerBcc` to be an array in the JSON string but got `%s`", jsonObj.get("headerBcc").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("attachments") != null && !jsonObj.get("attachments").isJsonNull() && !jsonObj.get("attachments").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `attachments` to be an array in the JSON string but got `%s`", jsonObj.get("attachments").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("groups") != null && !jsonObj.get("groups").isJsonNull() && !jsonObj.get("groups").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `groups` to be an array in the JSON string but got `%s`", jsonObj.get("groups").toString()));
      }
      if ((jsonObj.get("ipPool") != null && !jsonObj.get("ipPool").isJsonNull()) && !jsonObj.get("ipPool").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `ipPool` to be a primitive type in the JSON string but got `%s`", jsonObj.get("ipPool").toString()));
      }
      if ((jsonObj.get("subject") != null && !jsonObj.get("subject").isJsonNull()) && !jsonObj.get("subject").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `subject` to be a primitive type in the JSON string but got `%s`", jsonObj.get("subject").toString()));
      }
      if ((jsonObj.get("preText") != null && !jsonObj.get("preText").isJsonNull()) && !jsonObj.get("preText").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `preText` to be a primitive type in the JSON string but got `%s`", jsonObj.get("preText").toString()));
      }
      if ((jsonObj.get("htmlBody") != null && !jsonObj.get("htmlBody").isJsonNull()) && !jsonObj.get("htmlBody").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `htmlBody` to be a primitive type in the JSON string but got `%s`", jsonObj.get("htmlBody").toString()));
      }
      if ((jsonObj.get("textBody") != null && !jsonObj.get("textBody").isJsonNull()) && !jsonObj.get("textBody").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `textBody` to be a primitive type in the JSON string but got `%s`", jsonObj.get("textBody").toString()));
      }
      if ((jsonObj.get("ampBody") != null && !jsonObj.get("ampBody").isJsonNull()) && !jsonObj.get("ampBody").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `ampBody` to be a primitive type in the JSON string but got `%s`", jsonObj.get("ampBody").toString()));
      }
      if ((jsonObj.get("webhookEndpoint") != null && !jsonObj.get("webhookEndpoint").isJsonNull()) && !jsonObj.get("webhookEndpoint").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `webhookEndpoint` to be a primitive type in the JSON string but got `%s`", jsonObj.get("webhookEndpoint").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("mxRecords") != null && !jsonObj.get("mxRecords").isJsonNull() && !jsonObj.get("mxRecords").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `mxRecords` to be an array in the JSON string but got `%s`", jsonObj.get("mxRecords").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!Message.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'Message' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<Message> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(Message.class));

       return (TypeAdapter<T>) new TypeAdapter<Message>() {
           @Override
           public void write(JsonWriter out, Message value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public Message read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of Message given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of Message
   * @throws IOException if the JSON string is invalid with respect to Message
   */
  public static Message fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, Message.class);
  }

  /**
   * Convert an instance of Message to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

