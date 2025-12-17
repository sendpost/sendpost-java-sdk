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
import java.util.List;
import sendpost_java_sdk.EventMetadata;

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
 * Event
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-12-17T13:20:11.010065+05:30[Asia/Kolkata]", comments = "Generator version: 7.13.0")
public class Event {
  public static final String SERIALIZED_NAME_EVENT_I_D = "eventID";
  @SerializedName(SERIALIZED_NAME_EVENT_I_D)
  @javax.annotation.Nullable
  private String eventID;

  public static final String SERIALIZED_NAME_GROUPS = "groups";
  @SerializedName(SERIALIZED_NAME_GROUPS)
  @javax.annotation.Nullable
  private List<String> groups = new ArrayList<>();

  public static final String SERIALIZED_NAME_IP_I_D = "ipID";
  @SerializedName(SERIALIZED_NAME_IP_I_D)
  @javax.annotation.Nullable
  private Integer ipID;

  public static final String SERIALIZED_NAME_IP_POOL_I_D = "ipPoolID";
  @SerializedName(SERIALIZED_NAME_IP_POOL_I_D)
  @javax.annotation.Nullable
  private Integer ipPoolID;

  public static final String SERIALIZED_NAME_DOMAIN_I_D = "domainID";
  @SerializedName(SERIALIZED_NAME_DOMAIN_I_D)
  @javax.annotation.Nullable
  private Integer domainID;

  public static final String SERIALIZED_NAME_TPSP_ID = "tpspId";
  @SerializedName(SERIALIZED_NAME_TPSP_ID)
  @javax.annotation.Nullable
  private Integer tpspId;

  public static final String SERIALIZED_NAME_MESSAGE_TYPE = "messageType";
  @SerializedName(SERIALIZED_NAME_MESSAGE_TYPE)
  @javax.annotation.Nullable
  private String messageType;

  public static final String SERIALIZED_NAME_MESSAGE_SUBJECT = "messageSubject";
  @SerializedName(SERIALIZED_NAME_MESSAGE_SUBJECT)
  @javax.annotation.Nullable
  private String messageSubject;

  public static final String SERIALIZED_NAME_ACCOUNT_I_D = "accountID";
  @SerializedName(SERIALIZED_NAME_ACCOUNT_I_D)
  @javax.annotation.Nullable
  private Integer accountID;

  public static final String SERIALIZED_NAME_SUB_ACCOUNT_I_D = "subAccountID";
  @SerializedName(SERIALIZED_NAME_SUB_ACCOUNT_I_D)
  @javax.annotation.Nullable
  private Integer subAccountID;

  public static final String SERIALIZED_NAME_MESSAGE_I_D = "messageID";
  @SerializedName(SERIALIZED_NAME_MESSAGE_I_D)
  @javax.annotation.Nullable
  private String messageID;

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  @javax.annotation.Nullable
  private Integer type;

  public static final String SERIALIZED_NAME_FROM = "from";
  @SerializedName(SERIALIZED_NAME_FROM)
  @javax.annotation.Nullable
  private String from;

  public static final String SERIALIZED_NAME_FROM_NAME = "fromName";
  @SerializedName(SERIALIZED_NAME_FROM_NAME)
  @javax.annotation.Nullable
  private String fromName;

  public static final String SERIALIZED_NAME_TO = "to";
  @SerializedName(SERIALIZED_NAME_TO)
  @javax.annotation.Nullable
  private String to;

  public static final String SERIALIZED_NAME_TO_NAME = "toName";
  @SerializedName(SERIALIZED_NAME_TO_NAME)
  @javax.annotation.Nullable
  private String toName;

  public static final String SERIALIZED_NAME_SUBMITTED_AT = "submittedAt";
  @SerializedName(SERIALIZED_NAME_SUBMITTED_AT)
  @javax.annotation.Nullable
  private Integer submittedAt;

  public static final String SERIALIZED_NAME_SMTP_CODE = "smtpCode";
  @SerializedName(SERIALIZED_NAME_SMTP_CODE)
  @javax.annotation.Nullable
  private Integer smtpCode;

  public static final String SERIALIZED_NAME_SMTP_DESCRIPTION = "smtpDescription";
  @SerializedName(SERIALIZED_NAME_SMTP_DESCRIPTION)
  @javax.annotation.Nullable
  private String smtpDescription;

  public static final String SERIALIZED_NAME_EVENT_METADATA = "eventMetadata";
  @SerializedName(SERIALIZED_NAME_EVENT_METADATA)
  @javax.annotation.Nullable
  private EventMetadata eventMetadata;

  public Event() {
  }

  public Event eventID(@javax.annotation.Nullable String eventID) {
    this.eventID = eventID;
    return this;
  }

  /**
   * Get eventID
   * @return eventID
   */
  @javax.annotation.Nullable
  public String getEventID() {
    return eventID;
  }

  public void setEventID(@javax.annotation.Nullable String eventID) {
    this.eventID = eventID;
  }


  public Event groups(@javax.annotation.Nullable List<String> groups) {
    this.groups = groups;
    return this;
  }

  public Event addGroupsItem(String groupsItem) {
    if (this.groups == null) {
      this.groups = new ArrayList<>();
    }
    this.groups.add(groupsItem);
    return this;
  }

  /**
   * Get groups
   * @return groups
   */
  @javax.annotation.Nullable
  public List<String> getGroups() {
    return groups;
  }

  public void setGroups(@javax.annotation.Nullable List<String> groups) {
    this.groups = groups;
  }


  public Event ipID(@javax.annotation.Nullable Integer ipID) {
    this.ipID = ipID;
    return this;
  }

  /**
   * Get ipID
   * @return ipID
   */
  @javax.annotation.Nullable
  public Integer getIpID() {
    return ipID;
  }

  public void setIpID(@javax.annotation.Nullable Integer ipID) {
    this.ipID = ipID;
  }


  public Event ipPoolID(@javax.annotation.Nullable Integer ipPoolID) {
    this.ipPoolID = ipPoolID;
    return this;
  }

  /**
   * Get ipPoolID
   * @return ipPoolID
   */
  @javax.annotation.Nullable
  public Integer getIpPoolID() {
    return ipPoolID;
  }

  public void setIpPoolID(@javax.annotation.Nullable Integer ipPoolID) {
    this.ipPoolID = ipPoolID;
  }


  public Event domainID(@javax.annotation.Nullable Integer domainID) {
    this.domainID = domainID;
    return this;
  }

  /**
   * Get domainID
   * @return domainID
   */
  @javax.annotation.Nullable
  public Integer getDomainID() {
    return domainID;
  }

  public void setDomainID(@javax.annotation.Nullable Integer domainID) {
    this.domainID = domainID;
  }


  public Event tpspId(@javax.annotation.Nullable Integer tpspId) {
    this.tpspId = tpspId;
    return this;
  }

  /**
   * Get tpspId
   * @return tpspId
   */
  @javax.annotation.Nullable
  public Integer getTpspId() {
    return tpspId;
  }

  public void setTpspId(@javax.annotation.Nullable Integer tpspId) {
    this.tpspId = tpspId;
  }


  public Event messageType(@javax.annotation.Nullable String messageType) {
    this.messageType = messageType;
    return this;
  }

  /**
   * Get messageType
   * @return messageType
   */
  @javax.annotation.Nullable
  public String getMessageType() {
    return messageType;
  }

  public void setMessageType(@javax.annotation.Nullable String messageType) {
    this.messageType = messageType;
  }


  public Event messageSubject(@javax.annotation.Nullable String messageSubject) {
    this.messageSubject = messageSubject;
    return this;
  }

  /**
   * Get messageSubject
   * @return messageSubject
   */
  @javax.annotation.Nullable
  public String getMessageSubject() {
    return messageSubject;
  }

  public void setMessageSubject(@javax.annotation.Nullable String messageSubject) {
    this.messageSubject = messageSubject;
  }


  public Event accountID(@javax.annotation.Nullable Integer accountID) {
    this.accountID = accountID;
    return this;
  }

  /**
   * Get accountID
   * @return accountID
   */
  @javax.annotation.Nullable
  public Integer getAccountID() {
    return accountID;
  }

  public void setAccountID(@javax.annotation.Nullable Integer accountID) {
    this.accountID = accountID;
  }


  public Event subAccountID(@javax.annotation.Nullable Integer subAccountID) {
    this.subAccountID = subAccountID;
    return this;
  }

  /**
   * Get subAccountID
   * @return subAccountID
   */
  @javax.annotation.Nullable
  public Integer getSubAccountID() {
    return subAccountID;
  }

  public void setSubAccountID(@javax.annotation.Nullable Integer subAccountID) {
    this.subAccountID = subAccountID;
  }


  public Event messageID(@javax.annotation.Nullable String messageID) {
    this.messageID = messageID;
    return this;
  }

  /**
   * Get messageID
   * @return messageID
   */
  @javax.annotation.Nullable
  public String getMessageID() {
    return messageID;
  }

  public void setMessageID(@javax.annotation.Nullable String messageID) {
    this.messageID = messageID;
  }


  public Event type(@javax.annotation.Nullable Integer type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @javax.annotation.Nullable
  public Integer getType() {
    return type;
  }

  public void setType(@javax.annotation.Nullable Integer type) {
    this.type = type;
  }


  public Event from(@javax.annotation.Nullable String from) {
    this.from = from;
    return this;
  }

  /**
   * Get from
   * @return from
   */
  @javax.annotation.Nullable
  public String getFrom() {
    return from;
  }

  public void setFrom(@javax.annotation.Nullable String from) {
    this.from = from;
  }


  public Event fromName(@javax.annotation.Nullable String fromName) {
    this.fromName = fromName;
    return this;
  }

  /**
   * Get fromName
   * @return fromName
   */
  @javax.annotation.Nullable
  public String getFromName() {
    return fromName;
  }

  public void setFromName(@javax.annotation.Nullable String fromName) {
    this.fromName = fromName;
  }


  public Event to(@javax.annotation.Nullable String to) {
    this.to = to;
    return this;
  }

  /**
   * Get to
   * @return to
   */
  @javax.annotation.Nullable
  public String getTo() {
    return to;
  }

  public void setTo(@javax.annotation.Nullable String to) {
    this.to = to;
  }


  public Event toName(@javax.annotation.Nullable String toName) {
    this.toName = toName;
    return this;
  }

  /**
   * Get toName
   * @return toName
   */
  @javax.annotation.Nullable
  public String getToName() {
    return toName;
  }

  public void setToName(@javax.annotation.Nullable String toName) {
    this.toName = toName;
  }


  public Event submittedAt(@javax.annotation.Nullable Integer submittedAt) {
    this.submittedAt = submittedAt;
    return this;
  }

  /**
   * Get submittedAt
   * @return submittedAt
   */
  @javax.annotation.Nullable
  public Integer getSubmittedAt() {
    return submittedAt;
  }

  public void setSubmittedAt(@javax.annotation.Nullable Integer submittedAt) {
    this.submittedAt = submittedAt;
  }


  public Event smtpCode(@javax.annotation.Nullable Integer smtpCode) {
    this.smtpCode = smtpCode;
    return this;
  }

  /**
   * Get smtpCode
   * @return smtpCode
   */
  @javax.annotation.Nullable
  public Integer getSmtpCode() {
    return smtpCode;
  }

  public void setSmtpCode(@javax.annotation.Nullable Integer smtpCode) {
    this.smtpCode = smtpCode;
  }


  public Event smtpDescription(@javax.annotation.Nullable String smtpDescription) {
    this.smtpDescription = smtpDescription;
    return this;
  }

  /**
   * Get smtpDescription
   * @return smtpDescription
   */
  @javax.annotation.Nullable
  public String getSmtpDescription() {
    return smtpDescription;
  }

  public void setSmtpDescription(@javax.annotation.Nullable String smtpDescription) {
    this.smtpDescription = smtpDescription;
  }


  public Event eventMetadata(@javax.annotation.Nullable EventMetadata eventMetadata) {
    this.eventMetadata = eventMetadata;
    return this;
  }

  /**
   * Get eventMetadata
   * @return eventMetadata
   */
  @javax.annotation.Nullable
  public EventMetadata getEventMetadata() {
    return eventMetadata;
  }

  public void setEventMetadata(@javax.annotation.Nullable EventMetadata eventMetadata) {
    this.eventMetadata = eventMetadata;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event event = (Event) o;
    return Objects.equals(this.eventID, event.eventID) &&
        Objects.equals(this.groups, event.groups) &&
        Objects.equals(this.ipID, event.ipID) &&
        Objects.equals(this.ipPoolID, event.ipPoolID) &&
        Objects.equals(this.domainID, event.domainID) &&
        Objects.equals(this.tpspId, event.tpspId) &&
        Objects.equals(this.messageType, event.messageType) &&
        Objects.equals(this.messageSubject, event.messageSubject) &&
        Objects.equals(this.accountID, event.accountID) &&
        Objects.equals(this.subAccountID, event.subAccountID) &&
        Objects.equals(this.messageID, event.messageID) &&
        Objects.equals(this.type, event.type) &&
        Objects.equals(this.from, event.from) &&
        Objects.equals(this.fromName, event.fromName) &&
        Objects.equals(this.to, event.to) &&
        Objects.equals(this.toName, event.toName) &&
        Objects.equals(this.submittedAt, event.submittedAt) &&
        Objects.equals(this.smtpCode, event.smtpCode) &&
        Objects.equals(this.smtpDescription, event.smtpDescription) &&
        Objects.equals(this.eventMetadata, event.eventMetadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventID, groups, ipID, ipPoolID, domainID, tpspId, messageType, messageSubject, accountID, subAccountID, messageID, type, from, fromName, to, toName, submittedAt, smtpCode, smtpDescription, eventMetadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Event {\n");
    sb.append("    eventID: ").append(toIndentedString(eventID)).append("\n");
    sb.append("    groups: ").append(toIndentedString(groups)).append("\n");
    sb.append("    ipID: ").append(toIndentedString(ipID)).append("\n");
    sb.append("    ipPoolID: ").append(toIndentedString(ipPoolID)).append("\n");
    sb.append("    domainID: ").append(toIndentedString(domainID)).append("\n");
    sb.append("    tpspId: ").append(toIndentedString(tpspId)).append("\n");
    sb.append("    messageType: ").append(toIndentedString(messageType)).append("\n");
    sb.append("    messageSubject: ").append(toIndentedString(messageSubject)).append("\n");
    sb.append("    accountID: ").append(toIndentedString(accountID)).append("\n");
    sb.append("    subAccountID: ").append(toIndentedString(subAccountID)).append("\n");
    sb.append("    messageID: ").append(toIndentedString(messageID)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    fromName: ").append(toIndentedString(fromName)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    toName: ").append(toIndentedString(toName)).append("\n");
    sb.append("    submittedAt: ").append(toIndentedString(submittedAt)).append("\n");
    sb.append("    smtpCode: ").append(toIndentedString(smtpCode)).append("\n");
    sb.append("    smtpDescription: ").append(toIndentedString(smtpDescription)).append("\n");
    sb.append("    eventMetadata: ").append(toIndentedString(eventMetadata)).append("\n");
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
    openapiFields.add("eventID");
    openapiFields.add("groups");
    openapiFields.add("ipID");
    openapiFields.add("ipPoolID");
    openapiFields.add("domainID");
    openapiFields.add("tpspId");
    openapiFields.add("messageType");
    openapiFields.add("messageSubject");
    openapiFields.add("accountID");
    openapiFields.add("subAccountID");
    openapiFields.add("messageID");
    openapiFields.add("type");
    openapiFields.add("from");
    openapiFields.add("fromName");
    openapiFields.add("to");
    openapiFields.add("toName");
    openapiFields.add("submittedAt");
    openapiFields.add("smtpCode");
    openapiFields.add("smtpDescription");
    openapiFields.add("eventMetadata");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to Event
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!Event.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in Event is not found in the empty JSON string", Event.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!Event.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `Event` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("eventID") != null && !jsonObj.get("eventID").isJsonNull()) && !jsonObj.get("eventID").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `eventID` to be a primitive type in the JSON string but got `%s`", jsonObj.get("eventID").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("groups") != null && !jsonObj.get("groups").isJsonNull() && !jsonObj.get("groups").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `groups` to be an array in the JSON string but got `%s`", jsonObj.get("groups").toString()));
      }
      if ((jsonObj.get("messageType") != null && !jsonObj.get("messageType").isJsonNull()) && !jsonObj.get("messageType").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `messageType` to be a primitive type in the JSON string but got `%s`", jsonObj.get("messageType").toString()));
      }
      if ((jsonObj.get("messageSubject") != null && !jsonObj.get("messageSubject").isJsonNull()) && !jsonObj.get("messageSubject").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `messageSubject` to be a primitive type in the JSON string but got `%s`", jsonObj.get("messageSubject").toString()));
      }
      if ((jsonObj.get("messageID") != null && !jsonObj.get("messageID").isJsonNull()) && !jsonObj.get("messageID").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `messageID` to be a primitive type in the JSON string but got `%s`", jsonObj.get("messageID").toString()));
      }
      if ((jsonObj.get("from") != null && !jsonObj.get("from").isJsonNull()) && !jsonObj.get("from").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `from` to be a primitive type in the JSON string but got `%s`", jsonObj.get("from").toString()));
      }
      if ((jsonObj.get("fromName") != null && !jsonObj.get("fromName").isJsonNull()) && !jsonObj.get("fromName").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `fromName` to be a primitive type in the JSON string but got `%s`", jsonObj.get("fromName").toString()));
      }
      if ((jsonObj.get("to") != null && !jsonObj.get("to").isJsonNull()) && !jsonObj.get("to").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `to` to be a primitive type in the JSON string but got `%s`", jsonObj.get("to").toString()));
      }
      if ((jsonObj.get("toName") != null && !jsonObj.get("toName").isJsonNull()) && !jsonObj.get("toName").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `toName` to be a primitive type in the JSON string but got `%s`", jsonObj.get("toName").toString()));
      }
      if ((jsonObj.get("smtpDescription") != null && !jsonObj.get("smtpDescription").isJsonNull()) && !jsonObj.get("smtpDescription").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `smtpDescription` to be a primitive type in the JSON string but got `%s`", jsonObj.get("smtpDescription").toString()));
      }
      // validate the optional field `eventMetadata`
      if (jsonObj.get("eventMetadata") != null && !jsonObj.get("eventMetadata").isJsonNull()) {
        EventMetadata.validateJsonElement(jsonObj.get("eventMetadata"));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!Event.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'Event' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<Event> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(Event.class));

       return (TypeAdapter<T>) new TypeAdapter<Event>() {
           @Override
           public void write(JsonWriter out, Event value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public Event read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of Event given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of Event
   * @throws IOException if the JSON string is invalid with respect to Event
   */
  public static Event fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, Event.class);
  }

  /**
   * Convert an instance of Event to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

