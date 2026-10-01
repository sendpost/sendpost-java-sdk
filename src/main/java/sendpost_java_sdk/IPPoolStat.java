/*
 * SendPost API
 * # Introduction  > ### 📌 API versioning & the v1 response contract > > This reference documents the **v1 response contract** — the stable, camelCase > response shape that SendPost commits to. This is the shape you should build against. > > **During the current deprecation window**, requests authenticated with an account > or sub-account API key receive the **legacy** response shape by default, so existing > integrations keep working unchanged. To receive the documented v1 shape today, send: > > ``` > X-SendPost-Public-Contract: v1 > ``` > > **How to tell which shape you got.** Every public response echoes the applied > contract in the `X-SendPost-Public-Contract` response header. While the legacy > shape is being served, responses also carry standard deprecation signals: > `Deprecation: true`, a `Sunset` header with the exact cut-over date, and a > `Link: <...>; rel=\"deprecation\"` header pointing at the migration guide. **Read the > `Sunset` header for the authoritative end date** rather than hardcoding one. > > **After the sunset date**, v1 becomes the default and the legacy shape is no longer > served. New integrations should send `X-SendPost-Public-Contract: v1` now and rely on > the shapes in this reference.  SendPost provides email API and SMTP relay which can be used not just to send & measure but also alert & optimised email sending.  You can use SendPost to:  * Send personalised emails to multiple recipients using email API   * Track opens and clicks  * Analyse statistics around open, clicks, bounce, unsubscribe and spam    At and advanced level you can use it to:  * Manage multiple sub-accounts which may map to your promotional or transactional sending, multiple product lines or multiple customers   * Classify your emails using groups for better analysis  * Analyse and fix email sending at sub-account level, IP Pool level or group level  * Have automated alerts to notify disruptions regarding email sending  * Manage different dedicated IP Pools so to better control your email sending  * Automatically know when IP or domain is blacklisted or sender score is down  * Leverage pro deliverability tools to get significantly better email deliverability & inboxing   [<img src=\"https://run.pstmn.io/button.svg\" alt=\"Run In Postman\" style=\"width: 128px; height: 32px;\">](https://god.gw.postman.com/run-collection/33476323-e6dbd27f-c4a7-4d49-bcac-94b0611b938b?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D33476323-e6dbd27f-c4a7-4d49-bcac-94b0611b938b%26entityType%3Dcollection%26workspaceId%3D6b1e4f65-96a9-4136-9512-6266c852517e)   # Overview  ## REST API  SendPost API is built on REST API principles. Authenticated users can interact with any of the API endpoints to perform:  * **GET**- to get a resource  * **POST** - to create a resource  * **PUT** - to update an existing resource  * **DELETE** - to delete a resource   The API endpoint for all API calls is: <code>https://api.sendpost.io/api/v1</code>   Some conventions that have been followed in the API design overall are following:   * All resources have either <code>/api/v1/subaccount</code> or <code>/api/v1/account</code> in their API call resource path based on who is authorised for the resource. All API calls with path <code>/api/v1/subaccount</code> use <code>X-SubAccount-ApiKey</code> in their request header. Likewise all API calls with path <code>/api/v1/account</code> use <code>X-Account-ApiKey</code> in their request header.  * All resource endpoints end with singular name and not plural. So we have <code>domain</code> instead of domains for domain resource endpoint. Likewise we have <code>sender</code> instead of senders for sender resource endpoint.  * Body submitted for POST / PUT API calls as well as JSON response from SendPost API follow camelcase convention  * All timestamps returned in response (created or submittedAt response fields) are UNIX nano epoch timestamp.   <aside class=\"success\"> All resources have either <code>/api/v1/subaccount</code> or <code>/api/v1/account</code> in their API call resource path based on who is authorised for the resource. All API calls with path <code>/api/v1/subaccount</code> use <code>X-SubAccount-ApiKey</code> in their request header. Likewise all API calls with path <code>/api/v1/account</code> use <code>X-Account-ApiKey</code> in their request header. </aside>   SendPost uses conventional HTTP response codes to indicate the success or failure of an API request.    * Codes in the <code>2xx</code> range indicate success.   * Codes in the <code>4xx</code> range indicate an error owing due to unauthorize access, incorrect request parameters or body etc.  * Code in the <code>5xx</code> range indicate an eror with SendPost's servers ( internal service issue or maintenance )   <aside class=\"info\"> SendPost all responses return <code>created</code> in UNIX nano epoch timestamp.  </aside>   ## Authentication  SendPost uses API keys for authentication. You can register a new SendPost API key at our [developer portal](https://app.sendpost.io/register).   SendPost expects the API key to be included in all API requests to the server in a header that looks like the following:   `X-SubAccount-ApiKey: AHEZEP8192SEGH`   This API key is used for all Sub-Account level operations such as:  * Sending emails  * Retrieving stats regarding open, click, bounce, unsubscribe and spam  * Uploading suppressions list  * Verifying sending domains and more  In addition to <code>X-SubAccount-ApiKey</code> you also have another API Key <code>X-Account-APIKey</code> which is used for Account level operations such as :  * Creating and managing sub-accounts  * Allocating IPs for your account  * Getting overall billing and usage information  * Email List validation  * Creating and managing alerts and more   <aside class=\"notice\"> You must look at individual API reference page to look at whether <code>X-SubAccount-ApiKey</code> is required or <code>X-Account-ApiKey</code> </aside>   In case an incorrect API Key header is specified or if it is missed you will get HTTP Response 401 ( Unauthorized ) response from SendPost.   ## HTTP Response Headers   Code           | Reason                 | Details ---------------| -----------------------| ----------- 200            | Success                | Everything went well 401            | Unauthorized           | Incorrect or missing API header either <code>X-SubAccount-ApiKey</code> or <code>X-Account-ApiKey</code> 403            | Forbidden              | Typically sent when resource with same name or details already exist 406            | Missing resource id    | Resource id specified is either missing or doesn't exist 422            | Unprocessable entity   | Request body is not in proper format 500            | Internal server error  | Some error happened at SendPost while processing API request 503            | Service Unavailable    | SendPost is offline for maintenance. Please try again later  # API SDKs  We have native SendPost SDKs in the following programming languages. You can integrate with them or create your own SDK with our API specification. In case you need any assistance with respect to API then do reachout to our team from website chat or email us at **hello@sendpost.io**   * [PHP](https://github.com/sendpost/sendpost_php_sdk)  * [Javascript](https://github.com/sendpost/sendpost_javascript_sdk)  * [Ruby](https://github.com/sendpost/sendpost_ruby_sdk)  * [Python](https://github.com/sendpost/sendpost_python_sdk)  * [Golang](https://github.com/sendpost/sendpost_go_sdk)   # API Reference  SendX REST API can be broken down into two major sub-sections:   * Sub-Account  * Account    Sub-Account API operations enable common email sending API use-cases like sending bulk email, adding new domains or senders for email sending programmatically, retrieving stats, adding suppressions etc. All Sub-Account API operations need to pass <code>X-SubAccount-ApiKey</code> header with every API call.   The Account API operations allow users to manage multiple sub-accounts and manage IPs. A single parent SendPost account can have 100's of sub-accounts. You may want to create sub-accounts for different products your company is running or to segregate types of emails or for managing email sending across multiple customers of yours.   # SMTP Reference  Simple Mail Transfer Protocol (SMTP) is a quick and easy way to send email from one server to another. SendPost provides an SMTP service that allows you to deliver your email via our servers instead of your own client or server.  This means you can count on SendPost's delivery at scale for your SMTP needs.    ## Integrating SMTP    1. Get the SMTP `username` and `password` from your SendPost account.  2. Set the server host in your email client or application to `smtp.sendpost.io`. This setting is sometimes referred to as the external SMTP server or the SMTP relay.  3. Set the `username` and `password`.  4. Set the port to `587` (or as specified below).  ## SMTP Ports   - For an unencrypted or a TLS connection, use port `25`, `2525` or `587`.  - For a SSL connection, use port `465`  - Check your firewall and network to ensure they're not blocking any of our SMTP Endpoints.   SendPost supports STARTTLS for establishing a TLS-encrypted connection. STARTTLS is a means of upgrading an unencrypted connection to an encrypted connection. There are versions of STARTTLS for a variety of protocols; the SMTP version is defined in [RFC 3207](https://www.ietf.org/rfc/rfc3207.txt).   To set up a STARTTLS connection, the SMTP client connects to the SendPost SMTP endpoint `smtp.sendpost.io` on port 25, 587, or 2525, issues an EHLO command, and waits for the server to announce that it supports the STARTTLS SMTP extension. The client then issues the STARTTLS command, initiating TLS negotiation. When negotiation is complete, the client issues an EHLO command over the new encrypted connection, and the SMTP session proceeds normally.   <aside class=\"success\"> If you are unsure which port to use, a TLS connection on port 587 is typically recommended. </aside>   ## Sending email from your application   ```javascript \"use strict\";  const nodemailer = require(\"nodemailer\");  async function main() { // create reusable transporter object using the default SMTP transport let transporter = nodemailer.createTransport({ host: \"smtp.sendpost.io\", port: 587, secure: false, // true for 465, false for other ports auth: { user:  \"<username>\" , // generated ethereal user pass: \"<password>\", // generated ethereal password }, requireTLS: true, debug: true, logger: true, });  // send mail with defined transport object try { let info = await transporter.sendMail({ from: 'erlich@piedpiper.com', to: 'gilfoyle@piedpiper.com', subject: 'Test Email Subject', html: '<h1>Hello Geeks!!!</h1>', }); console.log(\"Message sent: %s\", info.messageId); } catch (e) { console.log(e) } }  main().catch(console.error); ```  For PHP   ```php <?php // Import PHPMailer classes into the global namespace use PHPMailer\\PHPMailer\\PHPMailer; use PHPMailer\\PHPMailer\\SMTP; use PHPMailer\\PHPMailer\\Exception;  // Load Composer's autoloader require 'vendor/autoload.php';  $mail = new PHPMailer(true);  // Settings try { $mail->SMTPDebug = SMTP::DEBUG_CONNECTION;                  // Enable verbose debug output $mail->isSMTP();                                            // Send using SMTP $mail->Host       = 'smtp.sendpost.io';                     // Set the SMTP server to send through $mail->SMTPAuth   = true;                                   // Enable SMTP authentication $mail->Username   = '<username>';                           // SMTP username $mail->Password   = '<password>';                           // SMTP password $mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;         // Enable implicit TLS encryption $mail->Port       = 587;                                    // TCP port to connect to; use 587 if you have set `SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS`  //Recipients $mail->setFrom('erlich@piedpiper.com', 'Erlich'); $mail->addAddress('gilfoyle@piedpiper.com', 'Gilfoyle');  //Content $mail->isHTML(true);                                  //Set email format to HTML $mail->Subject = 'Here is the subject'; $mail->Body    = 'This is the HTML message body <b>in bold!</b>'; $mail->AltBody = 'This is the body in plain text for non-HTML mail clients';  $mail->send(); echo 'Message has been sent';  } catch (Exception $e) { echo \"Message could not be sent. Mailer Error: {$mail->ErrorInfo}\"; } ``` For Python ```python #!/usr/bin/python3  import sys import os import re  from smtplib import SMTP import ssl  from email.mime.text import MIMEText  SMTPserver = 'smtp.sendpost.io' PORT = 587 sender =     'erlich@piedpiper.com' destination = ['gilfoyle@piedpiper.com']  USERNAME = \"<username>\" PASSWORD = \"<password>\"  # typical values for text_subtype are plain, html, xml text_subtype = 'plain'  content=\"\"\"\\ Test message \"\"\"  subject=\"Sent from Python\"  try: msg = MIMEText(content, text_subtype) msg['Subject']= subject msg['From']   = sender  conn = SMTP(SMTPserver, PORT) conn.ehlo() context = ssl.create_default_context() conn.starttls(context=context)  # upgrade to tls conn.ehlo() conn.set_debuglevel(True) conn.login(USERNAME, PASSWORD)  try: resp = conn.sendmail(sender, destination, msg.as_string()) print(\"Send Mail Response: \", resp) except Exception as e: print(\"Send Email Error: \", e) finally: conn.quit()  except Exception as e: print(\"Error:\", e) ``` For Golang ```go package main  import ( \"fmt\" \"net/smtp\" \"os\" )  // Sending Email Using Smtp in Golang  func main() {  username := \"<username>\" password := \"<password>\"  from := \"erlich@piedpiper.com\" toList := []string{\"gilfoyle@piedpiper.com\"} host := \"smtp.sendpost.io\" port := \"587\" // recommended  // This is the message to send in the mail msg := \"Hello geeks!!!\"  // We can't send strings directly in mail, // strings need to be converted into slice bytes body := []byte(msg)  // PlainAuth uses the given username and password to // authenticate to host and act as identity. // Usually identity should be the empty string, // to act as username. auth := smtp.PlainAuth(\"\", username, password, host)  // SendMail uses TLS connection to send the mail // The email is sent to all address in the toList, // the body should be of type bytes, not strings // This returns error if any occured. err := smtp.SendMail(host+\":\"+port, auth, from, toList, body)  // handling the errors if err != nil { fmt.Println(err) os.Exit(1) }  fmt.Println(\"Successfully sent mail to all user in toList\") }  ``` For Java ```java // implementation 'com.sun.mail:javax.mail:1.6.2'  import java.util.Properties;  import javax.mail.Message; import javax.mail.Session; import javax.mail.Transport; import javax.mail.internet.InternetAddress; import javax.mail.internet.MimeMessage;  public class SMTPConnect {  // This address must be verified. static final String FROM = \"erlich@piedpiper.com\"; static final String FROMNAME = \"Erlich Bachman\";  // Replace recipient@example.com with a \"To\" address. If your account // is still in the sandbox, this address must be verified. static final String TO = \"gilfoyle@piedpiper.com\";  // Replace smtp_username with your SendPost SMTP user name. static final String SMTP_USERNAME = \"<username>\";  // Replace smtp_password with your SendPost SMTP password. static final String SMTP_PASSWORD = \"<password>\";  // SMTP Host Name static final String HOST = \"smtp.sendpost.io\";  // The port you will connect to on SendPost SMTP Endpoint. static final int PORT = 587;  static final String SUBJECT = \"SendPost SMTP Test (SMTP interface accessed using Java)\";  static final String BODY = String.join( System.getProperty(\"line.separator\"), \"<h1>SendPost SMTP Test</h1>\", \"<p>This email was sent with SendPost using the \", \"<a href='https://github.com/eclipse-ee4j/mail'>Javamail Package</a>\", \" for <a href='https://www.java.com'>Java</a>.\" );  public static void main(String[] args) throws Exception {  // Create a Properties object to contain connection configuration information. Properties props = System.getProperties(); props.put(\"mail.transport.protocol\", \"smtp\"); props.put(\"mail.smtp.port\", PORT); props.put(\"mail.smtp.starttls.enable\", \"true\"); props.put(\"mail.smtp.debug\", \"true\"); props.put(\"mail.smtp.auth\", \"true\");  // Create a Session object to represent a mail session with the specified properties. Session session = Session.getDefaultInstance(props);  // Create a message with the specified information. MimeMessage msg = new MimeMessage(session); msg.setFrom(new InternetAddress(FROM,FROMNAME)); msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO)); msg.setSubject(SUBJECT); msg.setContent(BODY,\"text/html\");  // Create a transport. Transport transport = session.getTransport();  // Send the message. try { System.out.println(\"Sending...\");  // Connect to SendPost SMTP using the SMTP username and password you specified above. transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);  // Send the email. transport.sendMessage(msg, msg.getAllRecipients()); System.out.println(\"Email sent!\");  } catch (Exception ex) {  System.out.println(\"The email was not sent.\"); System.out.println(\"Error message: \" + ex.getMessage()); System.out.println(ex); } // Close and terminate the connection. } } ```  Many programming languages support sending email using SMTP. This capability might be built into the programming language itself, or it might be available as an add-on, plug-in, or library. You can take advantage of this capability by sending email through SendPost from within application programs that you write.  We have provided examples in Python3, Golang, Java, PHP, JS.  # API Contract Versioning (Public REST)  The public REST API uses a versioned response contract so field changes stay non-breaking:  * Send `X-SendPost-Public-Contract: v1` to opt into the current v1 response shape, or `legacy` for the pre-v1 shape. If the header is omitted, the applied contract is policy-driven — `legacy` before the published sunset date, `v1` after it. * Every response echoes `X-SendPost-Public-Contract: <applied>`. When the `legacy` contract is served, responses also include `Deprecation: true`, `Sunset: <RFC1123 date>`, and `Link: <doc-url>; rel=\"deprecation\"`. * Migrate to `v1` before the sunset date. Notable legacy → v1 field changes: Suppression `smtp_error` → `smtpError`, Stat `email_type` → `emailType`.  > `X-SendPost-Private-Api: true` is an internal header used only by the SendPost dashboard to receive richer internal objects. It is not part of the public SDK contract and should not be set by API integrations. 
 *
 * The version of the OpenAPI document: 1.3.0
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
import java.util.Arrays;

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
 * Email statistics aggregated by IP pool. Combines the shared Stat metrics with pool identity plus a &#x60;pending&#x60; counter. 
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.13.0")
public class IPPoolStat {
  public static final String SERIALIZED_NAME_PROCESSED = "processed";
  @SerializedName(SERIALIZED_NAME_PROCESSED)
  @javax.annotation.Nullable
  private Long processed;

  public static final String SERIALIZED_NAME_SENT = "sent";
  @SerializedName(SERIALIZED_NAME_SENT)
  @javax.annotation.Nullable
  private Long sent;

  public static final String SERIALIZED_NAME_DROPPED = "dropped";
  @SerializedName(SERIALIZED_NAME_DROPPED)
  @javax.annotation.Nullable
  private Long dropped;

  public static final String SERIALIZED_NAME_SMTP_DROPPED = "smtpDropped";
  @SerializedName(SERIALIZED_NAME_SMTP_DROPPED)
  @javax.annotation.Nullable
  private Long smtpDropped;

  public static final String SERIALIZED_NAME_DELIVERED = "delivered";
  @SerializedName(SERIALIZED_NAME_DELIVERED)
  @javax.annotation.Nullable
  private Long delivered;

  public static final String SERIALIZED_NAME_SOFT_BOUNCED = "softBounced";
  @SerializedName(SERIALIZED_NAME_SOFT_BOUNCED)
  @javax.annotation.Nullable
  private Long softBounced;

  public static final String SERIALIZED_NAME_HARD_BOUNCED = "hardBounced";
  @SerializedName(SERIALIZED_NAME_HARD_BOUNCED)
  @javax.annotation.Nullable
  private Long hardBounced;

  public static final String SERIALIZED_NAME_OPENED = "opened";
  @SerializedName(SERIALIZED_NAME_OPENED)
  @javax.annotation.Nullable
  private Long opened;

  public static final String SERIALIZED_NAME_CLICKED = "clicked";
  @SerializedName(SERIALIZED_NAME_CLICKED)
  @javax.annotation.Nullable
  private Long clicked;

  public static final String SERIALIZED_NAME_UNSUBSCRIBED = "unsubscribed";
  @SerializedName(SERIALIZED_NAME_UNSUBSCRIBED)
  @javax.annotation.Nullable
  private Long unsubscribed;

  public static final String SERIALIZED_NAME_SPAM = "spam";
  @SerializedName(SERIALIZED_NAME_SPAM)
  @javax.annotation.Nullable
  private Long spam;

  public static final String SERIALIZED_NAME_IP_POOL_ID = "ipPoolId";
  @SerializedName(SERIALIZED_NAME_IP_POOL_ID)
  @javax.annotation.Nullable
  private Long ipPoolId;

  public static final String SERIALIZED_NAME_IP_POOL_NAME = "ipPoolName";
  @SerializedName(SERIALIZED_NAME_IP_POOL_NAME)
  @javax.annotation.Nullable
  private String ipPoolName;

  public static final String SERIALIZED_NAME_PENDING = "pending";
  @SerializedName(SERIALIZED_NAME_PENDING)
  @javax.annotation.Nullable
  private Long pending;

  public IPPoolStat() {
  }

  public IPPoolStat processed(@javax.annotation.Nullable Long processed) {
    this.processed = processed;
    return this;
  }

  /**
   * Total number of emails accepted by SendPost API for processing. This is the starting point - all emails submitted through the API. 
   * @return processed
   */
  @javax.annotation.Nullable
  public Long getProcessed() {
    return processed;
  }

  public void setProcessed(@javax.annotation.Nullable Long processed) {
    this.processed = processed;
  }


  public IPPoolStat sent(@javax.annotation.Nullable Long sent) {
    this.sent = sent;
    return this;
  }

  /**
   * Number of emails sent to recipient mail servers. sent &#x3D; processed - dropped - smtpDropped 
   * @return sent
   */
  @javax.annotation.Nullable
  public Long getSent() {
    return sent;
  }

  public void setSent(@javax.annotation.Nullable Long sent) {
    this.sent = sent;
  }


  public IPPoolStat dropped(@javax.annotation.Nullable Long dropped) {
    this.dropped = dropped;
    return this;
  }

  /**
   * Number of emails dropped before sending. Common reasons: - Recipient email in suppression list (hard bounce, spam complaint, unsubscribe) - Invalid recipient email format - Sender domain not verified 
   * @return dropped
   */
  @javax.annotation.Nullable
  public Long getDropped() {
    return dropped;
  }

  public void setDropped(@javax.annotation.Nullable Long dropped) {
    this.dropped = dropped;
  }


  public IPPoolStat smtpDropped(@javax.annotation.Nullable Long smtpDropped) {
    this.smtpDropped = smtpDropped;
    return this;
  }

  /**
   * Number of emails dropped at SMTP level due to policy violations or rate limiting by the receiving server before delivery attempt completed. 
   * @return smtpDropped
   */
  @javax.annotation.Nullable
  public Long getSmtpDropped() {
    return smtpDropped;
  }

  public void setSmtpDropped(@javax.annotation.Nullable Long smtpDropped) {
    this.smtpDropped = smtpDropped;
  }


  public IPPoolStat delivered(@javax.annotation.Nullable Long delivered) {
    this.delivered = delivered;
    return this;
  }

  /**
   * Number of emails successfully delivered to recipient mail servers. Note: Delivered means accepted by the server, not necessarily in inbox. 
   * @return delivered
   */
  @javax.annotation.Nullable
  public Long getDelivered() {
    return delivered;
  }

  public void setDelivered(@javax.annotation.Nullable Long delivered) {
    this.delivered = delivered;
  }


  public IPPoolStat softBounced(@javax.annotation.Nullable Long softBounced) {
    this.softBounced = softBounced;
    return this;
  }

  /**
   * Number of temporary delivery failures (soft bounces). Common causes: - Recipient mailbox full - Server temporarily unavailable - Message too large SendPost automatically retries soft bounces. 
   * @return softBounced
   */
  @javax.annotation.Nullable
  public Long getSoftBounced() {
    return softBounced;
  }

  public void setSoftBounced(@javax.annotation.Nullable Long softBounced) {
    this.softBounced = softBounced;
  }


  public IPPoolStat hardBounced(@javax.annotation.Nullable Long hardBounced) {
    this.hardBounced = hardBounced;
    return this;
  }

  /**
   * Number of permanent delivery failures (hard bounces). Common causes: - Recipient email doesn&#39;t exist - Domain doesn&#39;t exist - Recipient has blocked sender Hard bounced addresses are automatically added to suppression list. 
   * @return hardBounced
   */
  @javax.annotation.Nullable
  public Long getHardBounced() {
    return hardBounced;
  }

  public void setHardBounced(@javax.annotation.Nullable Long hardBounced) {
    this.hardBounced = hardBounced;
  }


  public IPPoolStat opened(@javax.annotation.Nullable Long opened) {
    this.opened = opened;
    return this;
  }

  /**
   * Number of emails opened (tracking pixel loaded). Requires trackOpens&#x3D;true. Note: Some email clients block tracking pixels. 
   * @return opened
   */
  @javax.annotation.Nullable
  public Long getOpened() {
    return opened;
  }

  public void setOpened(@javax.annotation.Nullable Long opened) {
    this.opened = opened;
  }


  public IPPoolStat clicked(@javax.annotation.Nullable Long clicked) {
    this.clicked = clicked;
    return this;
  }

  /**
   * Number of emails with at least one link clicked. Requires trackClicks&#x3D;true. 
   * @return clicked
   */
  @javax.annotation.Nullable
  public Long getClicked() {
    return clicked;
  }

  public void setClicked(@javax.annotation.Nullable Long clicked) {
    this.clicked = clicked;
  }


  public IPPoolStat unsubscribed(@javax.annotation.Nullable Long unsubscribed) {
    this.unsubscribed = unsubscribed;
    return this;
  }

  /**
   * Number of recipients who clicked the unsubscribe link. Unsubscribed addresses are automatically added to suppression list. 
   * @return unsubscribed
   */
  @javax.annotation.Nullable
  public Long getUnsubscribed() {
    return unsubscribed;
  }

  public void setUnsubscribed(@javax.annotation.Nullable Long unsubscribed) {
    this.unsubscribed = unsubscribed;
  }


  public IPPoolStat spam(@javax.annotation.Nullable Long spam) {
    this.spam = spam;
    return this;
  }

  /**
   * Number of spam complaints (recipient marked email as spam). High spam rates can severely impact your sender reputation. Target: Keep spam rate below 0.1%. 
   * @return spam
   */
  @javax.annotation.Nullable
  public Long getSpam() {
    return spam;
  }

  public void setSpam(@javax.annotation.Nullable Long spam) {
    this.spam = spam;
  }


  public IPPoolStat ipPoolId(@javax.annotation.Nullable Long ipPoolId) {
    this.ipPoolId = ipPoolId;
    return this;
  }

  /**
   * Unique identifier of the IP pool.
   * @return ipPoolId
   */
  @javax.annotation.Nullable
  public Long getIpPoolId() {
    return ipPoolId;
  }

  public void setIpPoolId(@javax.annotation.Nullable Long ipPoolId) {
    this.ipPoolId = ipPoolId;
  }


  public IPPoolStat ipPoolName(@javax.annotation.Nullable String ipPoolName) {
    this.ipPoolName = ipPoolName;
    return this;
  }

  /**
   * Human-readable name of the IP pool.
   * @return ipPoolName
   */
  @javax.annotation.Nullable
  public String getIpPoolName() {
    return ipPoolName;
  }

  public void setIpPoolName(@javax.annotation.Nullable String ipPoolName) {
    this.ipPoolName = ipPoolName;
  }


  public IPPoolStat pending(@javax.annotation.Nullable Long pending) {
    this.pending = pending;
    return this;
  }

  /**
   * Number of emails queued on this pool but not yet processed.
   * @return pending
   */
  @javax.annotation.Nullable
  public Long getPending() {
    return pending;
  }

  public void setPending(@javax.annotation.Nullable Long pending) {
    this.pending = pending;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IPPoolStat ipPoolStat = (IPPoolStat) o;
    return Objects.equals(this.processed, ipPoolStat.processed) &&
        Objects.equals(this.sent, ipPoolStat.sent) &&
        Objects.equals(this.dropped, ipPoolStat.dropped) &&
        Objects.equals(this.smtpDropped, ipPoolStat.smtpDropped) &&
        Objects.equals(this.delivered, ipPoolStat.delivered) &&
        Objects.equals(this.softBounced, ipPoolStat.softBounced) &&
        Objects.equals(this.hardBounced, ipPoolStat.hardBounced) &&
        Objects.equals(this.opened, ipPoolStat.opened) &&
        Objects.equals(this.clicked, ipPoolStat.clicked) &&
        Objects.equals(this.unsubscribed, ipPoolStat.unsubscribed) &&
        Objects.equals(this.spam, ipPoolStat.spam) &&
        Objects.equals(this.ipPoolId, ipPoolStat.ipPoolId) &&
        Objects.equals(this.ipPoolName, ipPoolStat.ipPoolName) &&
        Objects.equals(this.pending, ipPoolStat.pending);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processed, sent, dropped, smtpDropped, delivered, softBounced, hardBounced, opened, clicked, unsubscribed, spam, ipPoolId, ipPoolName, pending);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IPPoolStat {\n");
    sb.append("    processed: ").append(toIndentedString(processed)).append("\n");
    sb.append("    sent: ").append(toIndentedString(sent)).append("\n");
    sb.append("    dropped: ").append(toIndentedString(dropped)).append("\n");
    sb.append("    smtpDropped: ").append(toIndentedString(smtpDropped)).append("\n");
    sb.append("    delivered: ").append(toIndentedString(delivered)).append("\n");
    sb.append("    softBounced: ").append(toIndentedString(softBounced)).append("\n");
    sb.append("    hardBounced: ").append(toIndentedString(hardBounced)).append("\n");
    sb.append("    opened: ").append(toIndentedString(opened)).append("\n");
    sb.append("    clicked: ").append(toIndentedString(clicked)).append("\n");
    sb.append("    unsubscribed: ").append(toIndentedString(unsubscribed)).append("\n");
    sb.append("    spam: ").append(toIndentedString(spam)).append("\n");
    sb.append("    ipPoolId: ").append(toIndentedString(ipPoolId)).append("\n");
    sb.append("    ipPoolName: ").append(toIndentedString(ipPoolName)).append("\n");
    sb.append("    pending: ").append(toIndentedString(pending)).append("\n");
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
    openapiFields.add("processed");
    openapiFields.add("sent");
    openapiFields.add("dropped");
    openapiFields.add("smtpDropped");
    openapiFields.add("delivered");
    openapiFields.add("softBounced");
    openapiFields.add("hardBounced");
    openapiFields.add("opened");
    openapiFields.add("clicked");
    openapiFields.add("unsubscribed");
    openapiFields.add("spam");
    openapiFields.add("ipPoolId");
    openapiFields.add("ipPoolName");
    openapiFields.add("pending");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to IPPoolStat
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!IPPoolStat.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in IPPoolStat is not found in the empty JSON string", IPPoolStat.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!IPPoolStat.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `IPPoolStat` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("ipPoolName") != null && !jsonObj.get("ipPoolName").isJsonNull()) && !jsonObj.get("ipPoolName").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `ipPoolName` to be a primitive type in the JSON string but got `%s`", jsonObj.get("ipPoolName").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!IPPoolStat.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'IPPoolStat' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<IPPoolStat> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(IPPoolStat.class));

       return (TypeAdapter<T>) new TypeAdapter<IPPoolStat>() {
           @Override
           public void write(JsonWriter out, IPPoolStat value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public IPPoolStat read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of IPPoolStat given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of IPPoolStat
   * @throws IOException if the JSON string is invalid with respect to IPPoolStat
   */
  public static IPPoolStat fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, IPPoolStat.class);
  }

  /**
   * Convert an instance of IPPoolStat to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

