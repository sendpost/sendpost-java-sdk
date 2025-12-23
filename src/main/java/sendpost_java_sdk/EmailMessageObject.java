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
import sendpost_java_sdk.Attachment;
import sendpost_java_sdk.EmailAddress;
import sendpost_java_sdk.Recipient;

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
 * EmailMessageObject
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-12-23T14:51:06.742514+05:30[Asia/Kolkata]", comments = "Generator version: 7.13.0")
public class EmailMessageObject {
  public static final String SERIALIZED_NAME_FROM = "from";
  @SerializedName(SERIALIZED_NAME_FROM)
  @javax.annotation.Nullable
  private EmailAddress from;

  public static final String SERIALIZED_NAME_REPLY_TO = "replyTo";
  @SerializedName(SERIALIZED_NAME_REPLY_TO)
  @javax.annotation.Nullable
  private EmailAddress replyTo;

  public static final String SERIALIZED_NAME_TO = "to";
  @SerializedName(SERIALIZED_NAME_TO)
  @javax.annotation.Nullable
  private List<Recipient> to = new ArrayList<>();

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

  public static final String SERIALIZED_NAME_IPPOOL = "ippool";
  @SerializedName(SERIALIZED_NAME_IPPOOL)
  @javax.annotation.Nullable
  private String ippool;

  public static final String SERIALIZED_NAME_HEADERS = "headers";
  @SerializedName(SERIALIZED_NAME_HEADERS)
  @javax.annotation.Nullable
  private Map<String, String> headers = new HashMap<>();

  public static final String SERIALIZED_NAME_TRACK_OPENS = "trackOpens";
  @SerializedName(SERIALIZED_NAME_TRACK_OPENS)
  @javax.annotation.Nullable
  private Boolean trackOpens;

  public static final String SERIALIZED_NAME_TRACK_CLICKS = "trackClicks";
  @SerializedName(SERIALIZED_NAME_TRACK_CLICKS)
  @javax.annotation.Nullable
  private Boolean trackClicks;

  public static final String SERIALIZED_NAME_GROUPS = "groups";
  @SerializedName(SERIALIZED_NAME_GROUPS)
  @javax.annotation.Nullable
  private List<String> groups = new ArrayList<>();

  public static final String SERIALIZED_NAME_ATTACHMENTS = "attachments";
  @SerializedName(SERIALIZED_NAME_ATTACHMENTS)
  @javax.annotation.Nullable
  private List<Attachment> attachments = new ArrayList<>();

  public static final String SERIALIZED_NAME_WEBHOOK_ENDPOINT = "webhookEndpoint";
  @SerializedName(SERIALIZED_NAME_WEBHOOK_ENDPOINT)
  @javax.annotation.Nullable
  private String webhookEndpoint;

  public EmailMessageObject() {
  }

  public EmailMessageObject from(@javax.annotation.Nullable EmailAddress from) {
    this.from = from;
    return this;
  }

  /**
   * Get from
   * @return from
   */
  @javax.annotation.Nullable
  public EmailAddress getFrom() {
    return from;
  }

  public void setFrom(@javax.annotation.Nullable EmailAddress from) {
    this.from = from;
  }


  public EmailMessageObject replyTo(@javax.annotation.Nullable EmailAddress replyTo) {
    this.replyTo = replyTo;
    return this;
  }

  /**
   * Get replyTo
   * @return replyTo
   */
  @javax.annotation.Nullable
  public EmailAddress getReplyTo() {
    return replyTo;
  }

  public void setReplyTo(@javax.annotation.Nullable EmailAddress replyTo) {
    this.replyTo = replyTo;
  }


  public EmailMessageObject to(@javax.annotation.Nullable List<Recipient> to) {
    this.to = to;
    return this;
  }

  public EmailMessageObject addToItem(Recipient toItem) {
    if (this.to == null) {
      this.to = new ArrayList<>();
    }
    this.to.add(toItem);
    return this;
  }

  /**
   * Get to
   * @return to
   */
  @javax.annotation.Nullable
  public List<Recipient> getTo() {
    return to;
  }

  public void setTo(@javax.annotation.Nullable List<Recipient> to) {
    this.to = to;
  }


  public EmailMessageObject subject(@javax.annotation.Nullable String subject) {
    this.subject = subject;
    return this;
  }

  /**
   * Get subject
   * @return subject
   */
  @javax.annotation.Nullable
  public String getSubject() {
    return subject;
  }

  public void setSubject(@javax.annotation.Nullable String subject) {
    this.subject = subject;
  }


  public EmailMessageObject preText(@javax.annotation.Nullable String preText) {
    this.preText = preText;
    return this;
  }

  /**
   * Get preText
   * @return preText
   */
  @javax.annotation.Nullable
  public String getPreText() {
    return preText;
  }

  public void setPreText(@javax.annotation.Nullable String preText) {
    this.preText = preText;
  }


  public EmailMessageObject htmlBody(@javax.annotation.Nullable String htmlBody) {
    this.htmlBody = htmlBody;
    return this;
  }

  /**
   * Get htmlBody
   * @return htmlBody
   */
  @javax.annotation.Nullable
  public String getHtmlBody() {
    return htmlBody;
  }

  public void setHtmlBody(@javax.annotation.Nullable String htmlBody) {
    this.htmlBody = htmlBody;
  }


  public EmailMessageObject textBody(@javax.annotation.Nullable String textBody) {
    this.textBody = textBody;
    return this;
  }

  /**
   * Get textBody
   * @return textBody
   */
  @javax.annotation.Nullable
  public String getTextBody() {
    return textBody;
  }

  public void setTextBody(@javax.annotation.Nullable String textBody) {
    this.textBody = textBody;
  }


  public EmailMessageObject ampBody(@javax.annotation.Nullable String ampBody) {
    this.ampBody = ampBody;
    return this;
  }

  /**
   * Get ampBody
   * @return ampBody
   */
  @javax.annotation.Nullable
  public String getAmpBody() {
    return ampBody;
  }

  public void setAmpBody(@javax.annotation.Nullable String ampBody) {
    this.ampBody = ampBody;
  }


  public EmailMessageObject ippool(@javax.annotation.Nullable String ippool) {
    this.ippool = ippool;
    return this;
  }

  /**
   * Get ippool
   * @return ippool
   */
  @javax.annotation.Nullable
  public String getIppool() {
    return ippool;
  }

  public void setIppool(@javax.annotation.Nullable String ippool) {
    this.ippool = ippool;
  }


  public EmailMessageObject headers(@javax.annotation.Nullable Map<String, String> headers) {
    this.headers = headers;
    return this;
  }

  public EmailMessageObject putHeadersItem(String key, String headersItem) {
    if (this.headers == null) {
      this.headers = new HashMap<>();
    }
    this.headers.put(key, headersItem);
    return this;
  }

  /**
   * Get headers
   * @return headers
   */
  @javax.annotation.Nullable
  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(@javax.annotation.Nullable Map<String, String> headers) {
    this.headers = headers;
  }


  public EmailMessageObject trackOpens(@javax.annotation.Nullable Boolean trackOpens) {
    this.trackOpens = trackOpens;
    return this;
  }

  /**
   * Get trackOpens
   * @return trackOpens
   */
  @javax.annotation.Nullable
  public Boolean getTrackOpens() {
    return trackOpens;
  }

  public void setTrackOpens(@javax.annotation.Nullable Boolean trackOpens) {
    this.trackOpens = trackOpens;
  }


  public EmailMessageObject trackClicks(@javax.annotation.Nullable Boolean trackClicks) {
    this.trackClicks = trackClicks;
    return this;
  }

  /**
   * Get trackClicks
   * @return trackClicks
   */
  @javax.annotation.Nullable
  public Boolean getTrackClicks() {
    return trackClicks;
  }

  public void setTrackClicks(@javax.annotation.Nullable Boolean trackClicks) {
    this.trackClicks = trackClicks;
  }


  public EmailMessageObject groups(@javax.annotation.Nullable List<String> groups) {
    this.groups = groups;
    return this;
  }

  public EmailMessageObject addGroupsItem(String groupsItem) {
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


  public EmailMessageObject attachments(@javax.annotation.Nullable List<Attachment> attachments) {
    this.attachments = attachments;
    return this;
  }

  public EmailMessageObject addAttachmentsItem(Attachment attachmentsItem) {
    if (this.attachments == null) {
      this.attachments = new ArrayList<>();
    }
    this.attachments.add(attachmentsItem);
    return this;
  }

  /**
   * Get attachments
   * @return attachments
   */
  @javax.annotation.Nullable
  public List<Attachment> getAttachments() {
    return attachments;
  }

  public void setAttachments(@javax.annotation.Nullable List<Attachment> attachments) {
    this.attachments = attachments;
  }


  public EmailMessageObject webhookEndpoint(@javax.annotation.Nullable String webhookEndpoint) {
    this.webhookEndpoint = webhookEndpoint;
    return this;
  }

  /**
   * Get webhookEndpoint
   * @return webhookEndpoint
   */
  @javax.annotation.Nullable
  public String getWebhookEndpoint() {
    return webhookEndpoint;
  }

  public void setWebhookEndpoint(@javax.annotation.Nullable String webhookEndpoint) {
    this.webhookEndpoint = webhookEndpoint;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmailMessageObject emailMessageObject = (EmailMessageObject) o;
    return Objects.equals(this.from, emailMessageObject.from) &&
        Objects.equals(this.replyTo, emailMessageObject.replyTo) &&
        Objects.equals(this.to, emailMessageObject.to) &&
        Objects.equals(this.subject, emailMessageObject.subject) &&
        Objects.equals(this.preText, emailMessageObject.preText) &&
        Objects.equals(this.htmlBody, emailMessageObject.htmlBody) &&
        Objects.equals(this.textBody, emailMessageObject.textBody) &&
        Objects.equals(this.ampBody, emailMessageObject.ampBody) &&
        Objects.equals(this.ippool, emailMessageObject.ippool) &&
        Objects.equals(this.headers, emailMessageObject.headers) &&
        Objects.equals(this.trackOpens, emailMessageObject.trackOpens) &&
        Objects.equals(this.trackClicks, emailMessageObject.trackClicks) &&
        Objects.equals(this.groups, emailMessageObject.groups) &&
        Objects.equals(this.attachments, emailMessageObject.attachments) &&
        Objects.equals(this.webhookEndpoint, emailMessageObject.webhookEndpoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, replyTo, to, subject, preText, htmlBody, textBody, ampBody, ippool, headers, trackOpens, trackClicks, groups, attachments, webhookEndpoint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmailMessageObject {\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    replyTo: ").append(toIndentedString(replyTo)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
    sb.append("    preText: ").append(toIndentedString(preText)).append("\n");
    sb.append("    htmlBody: ").append(toIndentedString(htmlBody)).append("\n");
    sb.append("    textBody: ").append(toIndentedString(textBody)).append("\n");
    sb.append("    ampBody: ").append(toIndentedString(ampBody)).append("\n");
    sb.append("    ippool: ").append(toIndentedString(ippool)).append("\n");
    sb.append("    headers: ").append(toIndentedString(headers)).append("\n");
    sb.append("    trackOpens: ").append(toIndentedString(trackOpens)).append("\n");
    sb.append("    trackClicks: ").append(toIndentedString(trackClicks)).append("\n");
    sb.append("    groups: ").append(toIndentedString(groups)).append("\n");
    sb.append("    attachments: ").append(toIndentedString(attachments)).append("\n");
    sb.append("    webhookEndpoint: ").append(toIndentedString(webhookEndpoint)).append("\n");
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
    openapiFields.add("from");
    openapiFields.add("replyTo");
    openapiFields.add("to");
    openapiFields.add("subject");
    openapiFields.add("preText");
    openapiFields.add("htmlBody");
    openapiFields.add("textBody");
    openapiFields.add("ampBody");
    openapiFields.add("ippool");
    openapiFields.add("headers");
    openapiFields.add("trackOpens");
    openapiFields.add("trackClicks");
    openapiFields.add("groups");
    openapiFields.add("attachments");
    openapiFields.add("webhookEndpoint");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to EmailMessageObject
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!EmailMessageObject.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in EmailMessageObject is not found in the empty JSON string", EmailMessageObject.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!EmailMessageObject.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `EmailMessageObject` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // validate the optional field `from`
      if (jsonObj.get("from") != null && !jsonObj.get("from").isJsonNull()) {
        EmailAddress.validateJsonElement(jsonObj.get("from"));
      }
      // validate the optional field `replyTo`
      if (jsonObj.get("replyTo") != null && !jsonObj.get("replyTo").isJsonNull()) {
        EmailAddress.validateJsonElement(jsonObj.get("replyTo"));
      }
      if (jsonObj.get("to") != null && !jsonObj.get("to").isJsonNull()) {
        JsonArray jsonArrayto = jsonObj.getAsJsonArray("to");
        if (jsonArrayto != null) {
          // ensure the json data is an array
          if (!jsonObj.get("to").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `to` to be an array in the JSON string but got `%s`", jsonObj.get("to").toString()));
          }

          // validate the optional field `to` (array)
          for (int i = 0; i < jsonArrayto.size(); i++) {
            Recipient.validateJsonElement(jsonArrayto.get(i));
          };
        }
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
      if ((jsonObj.get("ippool") != null && !jsonObj.get("ippool").isJsonNull()) && !jsonObj.get("ippool").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `ippool` to be a primitive type in the JSON string but got `%s`", jsonObj.get("ippool").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("groups") != null && !jsonObj.get("groups").isJsonNull() && !jsonObj.get("groups").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `groups` to be an array in the JSON string but got `%s`", jsonObj.get("groups").toString()));
      }
      if (jsonObj.get("attachments") != null && !jsonObj.get("attachments").isJsonNull()) {
        JsonArray jsonArrayattachments = jsonObj.getAsJsonArray("attachments");
        if (jsonArrayattachments != null) {
          // ensure the json data is an array
          if (!jsonObj.get("attachments").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `attachments` to be an array in the JSON string but got `%s`", jsonObj.get("attachments").toString()));
          }

          // validate the optional field `attachments` (array)
          for (int i = 0; i < jsonArrayattachments.size(); i++) {
            Attachment.validateJsonElement(jsonArrayattachments.get(i));
          };
        }
      }
      if ((jsonObj.get("webhookEndpoint") != null && !jsonObj.get("webhookEndpoint").isJsonNull()) && !jsonObj.get("webhookEndpoint").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `webhookEndpoint` to be a primitive type in the JSON string but got `%s`", jsonObj.get("webhookEndpoint").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!EmailMessageObject.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'EmailMessageObject' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<EmailMessageObject> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(EmailMessageObject.class));

       return (TypeAdapter<T>) new TypeAdapter<EmailMessageObject>() {
           @Override
           public void write(JsonWriter out, EmailMessageObject value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public EmailMessageObject read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of EmailMessageObject given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of EmailMessageObject
   * @throws IOException if the JSON string is invalid with respect to EmailMessageObject
   */
  public static EmailMessageObject fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, EmailMessageObject.class);
  }

  /**
   * Convert an instance of EmailMessageObject to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

