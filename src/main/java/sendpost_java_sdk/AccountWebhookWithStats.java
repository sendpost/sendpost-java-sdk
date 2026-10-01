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
import java.net.URI;
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
 * Account-level webhook enriched with its computed delivery success rate. This is the list item returned by &#x60;GET /account/webhook&#x60; (list all). It contains every field of the account webhook (see the Webhook schema, which mirrors the public AccountWebhook DTO) plus &#x60;successRate&#x60;. 
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.13.0")
public class AccountWebhookWithStats {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  @javax.annotation.Nullable
  private Long id;

  public static final String SERIALIZED_NAME_ENABLED = "enabled";
  @SerializedName(SERIALIZED_NAME_ENABLED)
  @javax.annotation.Nullable
  private Boolean enabled;

  public static final String SERIALIZED_NAME_URL = "url";
  @SerializedName(SERIALIZED_NAME_URL)
  @javax.annotation.Nullable
  private URI url;

  public static final String SERIALIZED_NAME_PROCESSED = "processed";
  @SerializedName(SERIALIZED_NAME_PROCESSED)
  @javax.annotation.Nullable
  private Boolean processed;

  public static final String SERIALIZED_NAME_SENT = "sent";
  @SerializedName(SERIALIZED_NAME_SENT)
  @javax.annotation.Nullable
  private Boolean sent;

  public static final String SERIALIZED_NAME_DROPPED = "dropped";
  @SerializedName(SERIALIZED_NAME_DROPPED)
  @javax.annotation.Nullable
  private Boolean dropped;

  public static final String SERIALIZED_NAME_SMTP_DROPPED = "smtpDropped";
  @SerializedName(SERIALIZED_NAME_SMTP_DROPPED)
  @javax.annotation.Nullable
  private Boolean smtpDropped;

  public static final String SERIALIZED_NAME_DELIVERED = "delivered";
  @SerializedName(SERIALIZED_NAME_DELIVERED)
  @javax.annotation.Nullable
  private Boolean delivered;

  public static final String SERIALIZED_NAME_SOFT_BOUNCED = "softBounced";
  @SerializedName(SERIALIZED_NAME_SOFT_BOUNCED)
  @javax.annotation.Nullable
  private Boolean softBounced;

  public static final String SERIALIZED_NAME_HARD_BOUNCED = "hardBounced";
  @SerializedName(SERIALIZED_NAME_HARD_BOUNCED)
  @javax.annotation.Nullable
  private Boolean hardBounced;

  public static final String SERIALIZED_NAME_OPENED = "opened";
  @SerializedName(SERIALIZED_NAME_OPENED)
  @javax.annotation.Nullable
  private Boolean opened;

  public static final String SERIALIZED_NAME_CLICKED = "clicked";
  @SerializedName(SERIALIZED_NAME_CLICKED)
  @javax.annotation.Nullable
  private Boolean clicked;

  public static final String SERIALIZED_NAME_UNSUBSCRIBED = "unsubscribed";
  @SerializedName(SERIALIZED_NAME_UNSUBSCRIBED)
  @javax.annotation.Nullable
  private Boolean unsubscribed;

  public static final String SERIALIZED_NAME_SPAM = "spam";
  @SerializedName(SERIALIZED_NAME_SPAM)
  @javax.annotation.Nullable
  private Boolean spam;

  public static final String SERIALIZED_NAME_UNIQUE_OPEN = "uniqueOpen";
  @SerializedName(SERIALIZED_NAME_UNIQUE_OPEN)
  @javax.annotation.Nullable
  private Boolean uniqueOpen;

  public static final String SERIALIZED_NAME_UNIQUE_CLICK = "uniqueClick";
  @SerializedName(SERIALIZED_NAME_UNIQUE_CLICK)
  @javax.annotation.Nullable
  private Boolean uniqueClick;

  /**
   * Health status of the webhook (read-only): - &#x60;active&#x60; - delivering normally - &#x60;degraded&#x60; - recent delivery failures - &#x60;disabled&#x60; - auto-disabled after repeated consecutive failures 
   */
  @JsonAdapter(StatusEnum.Adapter.class)
  public enum StatusEnum {
    ACTIVE("active"),
    
    DEGRADED("degraded"),
    
    DISABLED("disabled");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<StatusEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public StatusEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return StatusEnum.fromValue(value);
      }
    }

    public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      String value = jsonElement.getAsString();
      StatusEnum.fromValue(value);
    }
  }

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  @javax.annotation.Nullable
  private StatusEnum status;

  public static final String SERIALIZED_NAME_DISABLED_AT = "disabledAt";
  @SerializedName(SERIALIZED_NAME_DISABLED_AT)
  @javax.annotation.Nullable
  private Long disabledAt;

  public static final String SERIALIZED_NAME_DISABLED_REASON = "disabledReason";
  @SerializedName(SERIALIZED_NAME_DISABLED_REASON)
  @javax.annotation.Nullable
  private String disabledReason;

  public static final String SERIALIZED_NAME_CREATED = "created";
  @SerializedName(SERIALIZED_NAME_CREATED)
  @javax.annotation.Nullable
  private Long created;

  public static final String SERIALIZED_NAME_SUCCESS_RATE = "successRate";
  @SerializedName(SERIALIZED_NAME_SUCCESS_RATE)
  @javax.annotation.Nullable
  private Double successRate;

  public AccountWebhookWithStats() {
  }

  public AccountWebhookWithStats id(@javax.annotation.Nullable Long id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier for the webhook configuration
   * @return id
   */
  @javax.annotation.Nullable
  public Long getId() {
    return id;
  }

  public void setId(@javax.annotation.Nullable Long id) {
    this.id = id;
  }


  public AccountWebhookWithStats enabled(@javax.annotation.Nullable Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  /**
   * Whether the webhook is active. When false, no events will be sent to this webhook. Useful for temporarily pausing notifications during maintenance. 
   * @return enabled
   */
  @javax.annotation.Nullable
  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(@javax.annotation.Nullable Boolean enabled) {
    this.enabled = enabled;
  }


  public AccountWebhookWithStats url(@javax.annotation.Nullable URI url) {
    this.url = url;
    return this;
  }

  /**
   * HTTPS endpoint URL to receive webhook POST requests. Must be publicly accessible and return 2xx status code. 
   * @return url
   */
  @javax.annotation.Nullable
  public URI getUrl() {
    return url;
  }

  public void setUrl(@javax.annotation.Nullable URI url) {
    this.url = url;
  }


  public AccountWebhookWithStats processed(@javax.annotation.Nullable Boolean processed) {
    this.processed = processed;
    return this;
  }

  /**
   * Trigger webhook when an email is accepted for processing. Fires immediately when API call is successful. 
   * @return processed
   */
  @javax.annotation.Nullable
  public Boolean getProcessed() {
    return processed;
  }

  public void setProcessed(@javax.annotation.Nullable Boolean processed) {
    this.processed = processed;
  }


  public AccountWebhookWithStats sent(@javax.annotation.Nullable Boolean sent) {
    this.sent = sent;
    return this;
  }

  /**
   * Trigger webhook when an email is sent to the recipient&#39;s mail server. Indicates the email left SendPost&#39;s infrastructure. 
   * @return sent
   */
  @javax.annotation.Nullable
  public Boolean getSent() {
    return sent;
  }

  public void setSent(@javax.annotation.Nullable Boolean sent) {
    this.sent = sent;
  }


  public AccountWebhookWithStats dropped(@javax.annotation.Nullable Boolean dropped) {
    this.dropped = dropped;
    return this;
  }

  /**
   * Trigger webhook when an email is dropped before sending. Common reasons: suppressed address, invalid email, unverified domain. 
   * @return dropped
   */
  @javax.annotation.Nullable
  public Boolean getDropped() {
    return dropped;
  }

  public void setDropped(@javax.annotation.Nullable Boolean dropped) {
    this.dropped = dropped;
  }


  public AccountWebhookWithStats smtpDropped(@javax.annotation.Nullable Boolean smtpDropped) {
    this.smtpDropped = smtpDropped;
    return this;
  }

  /**
   * Trigger webhook when an email is dropped at SMTP level. Usually due to policy rejection by receiving server. 
   * @return smtpDropped
   */
  @javax.annotation.Nullable
  public Boolean getSmtpDropped() {
    return smtpDropped;
  }

  public void setSmtpDropped(@javax.annotation.Nullable Boolean smtpDropped) {
    this.smtpDropped = smtpDropped;
  }


  public AccountWebhookWithStats delivered(@javax.annotation.Nullable Boolean delivered) {
    this.delivered = delivered;
    return this;
  }

  /**
   * Trigger webhook when an email is successfully delivered. Note: \&quot;Delivered\&quot; means accepted by mail server, not inbox placement. 
   * @return delivered
   */
  @javax.annotation.Nullable
  public Boolean getDelivered() {
    return delivered;
  }

  public void setDelivered(@javax.annotation.Nullable Boolean delivered) {
    this.delivered = delivered;
  }


  public AccountWebhookWithStats softBounced(@javax.annotation.Nullable Boolean softBounced) {
    this.softBounced = softBounced;
    return this;
  }

  /**
   * Trigger webhook on temporary delivery failure (soft bounce). SendPost will retry delivery automatically. 
   * @return softBounced
   */
  @javax.annotation.Nullable
  public Boolean getSoftBounced() {
    return softBounced;
  }

  public void setSoftBounced(@javax.annotation.Nullable Boolean softBounced) {
    this.softBounced = softBounced;
  }


  public AccountWebhookWithStats hardBounced(@javax.annotation.Nullable Boolean hardBounced) {
    this.hardBounced = hardBounced;
    return this;
  }

  /**
   * Trigger webhook on permanent delivery failure (hard bounce). The recipient is automatically added to suppression list. 
   * @return hardBounced
   */
  @javax.annotation.Nullable
  public Boolean getHardBounced() {
    return hardBounced;
  }

  public void setHardBounced(@javax.annotation.Nullable Boolean hardBounced) {
    this.hardBounced = hardBounced;
  }


  public AccountWebhookWithStats opened(@javax.annotation.Nullable Boolean opened) {
    this.opened = opened;
    return this;
  }

  /**
   * Trigger webhook when recipient opens the email. Fires on every open (can fire multiple times per email). 
   * @return opened
   */
  @javax.annotation.Nullable
  public Boolean getOpened() {
    return opened;
  }

  public void setOpened(@javax.annotation.Nullable Boolean opened) {
    this.opened = opened;
  }


  public AccountWebhookWithStats clicked(@javax.annotation.Nullable Boolean clicked) {
    this.clicked = clicked;
    return this;
  }

  /**
   * Trigger webhook when recipient clicks a link. Fires on every click (can fire multiple times per email). 
   * @return clicked
   */
  @javax.annotation.Nullable
  public Boolean getClicked() {
    return clicked;
  }

  public void setClicked(@javax.annotation.Nullable Boolean clicked) {
    this.clicked = clicked;
  }


  public AccountWebhookWithStats unsubscribed(@javax.annotation.Nullable Boolean unsubscribed) {
    this.unsubscribed = unsubscribed;
    return this;
  }

  /**
   * Trigger webhook when recipient clicks the unsubscribe link. The recipient is automatically added to suppression list. 
   * @return unsubscribed
   */
  @javax.annotation.Nullable
  public Boolean getUnsubscribed() {
    return unsubscribed;
  }

  public void setUnsubscribed(@javax.annotation.Nullable Boolean unsubscribed) {
    this.unsubscribed = unsubscribed;
  }


  public AccountWebhookWithStats spam(@javax.annotation.Nullable Boolean spam) {
    this.spam = spam;
    return this;
  }

  /**
   * Trigger webhook when recipient marks email as spam. The recipient is automatically added to suppression list. Monitor this closely - high spam rates damage sender reputation. 
   * @return spam
   */
  @javax.annotation.Nullable
  public Boolean getSpam() {
    return spam;
  }

  public void setSpam(@javax.annotation.Nullable Boolean spam) {
    this.spam = spam;
  }


  public AccountWebhookWithStats uniqueOpen(@javax.annotation.Nullable Boolean uniqueOpen) {
    this.uniqueOpen = uniqueOpen;
    return this;
  }

  /**
   * Trigger webhook only on the first open of an email (unique opens). Use this instead of &#39;opened&#39; if you only care about unique engagement. 
   * @return uniqueOpen
   */
  @javax.annotation.Nullable
  public Boolean getUniqueOpen() {
    return uniqueOpen;
  }

  public void setUniqueOpen(@javax.annotation.Nullable Boolean uniqueOpen) {
    this.uniqueOpen = uniqueOpen;
  }


  public AccountWebhookWithStats uniqueClick(@javax.annotation.Nullable Boolean uniqueClick) {
    this.uniqueClick = uniqueClick;
    return this;
  }

  /**
   * Trigger webhook only on the first click of an email (unique clicks). Use this instead of &#39;clicked&#39; if you only care about unique engagement. 
   * @return uniqueClick
   */
  @javax.annotation.Nullable
  public Boolean getUniqueClick() {
    return uniqueClick;
  }

  public void setUniqueClick(@javax.annotation.Nullable Boolean uniqueClick) {
    this.uniqueClick = uniqueClick;
  }


  public AccountWebhookWithStats status(@javax.annotation.Nullable StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Health status of the webhook (read-only): - &#x60;active&#x60; - delivering normally - &#x60;degraded&#x60; - recent delivery failures - &#x60;disabled&#x60; - auto-disabled after repeated consecutive failures 
   * @return status
   */
  @javax.annotation.Nullable
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(@javax.annotation.Nullable StatusEnum status) {
    this.status = status;
  }


  public AccountWebhookWithStats disabledAt(@javax.annotation.Nullable Long disabledAt) {
    this.disabledAt = disabledAt;
    return this;
  }

  /**
   * UNIX epoch timestamp in nanoseconds when the webhook was auto-disabled (0 if never). Read-only.
   * @return disabledAt
   */
  @javax.annotation.Nullable
  public Long getDisabledAt() {
    return disabledAt;
  }

  public void setDisabledAt(@javax.annotation.Nullable Long disabledAt) {
    this.disabledAt = disabledAt;
  }


  public AccountWebhookWithStats disabledReason(@javax.annotation.Nullable String disabledReason) {
    this.disabledReason = disabledReason;
    return this;
  }

  /**
   * Human-readable reason the webhook was auto-disabled (empty if active). Read-only.
   * @return disabledReason
   */
  @javax.annotation.Nullable
  public String getDisabledReason() {
    return disabledReason;
  }

  public void setDisabledReason(@javax.annotation.Nullable String disabledReason) {
    this.disabledReason = disabledReason;
  }


  public AccountWebhookWithStats created(@javax.annotation.Nullable Long created) {
    this.created = created;
    return this;
  }

  /**
   * UNIX epoch timestamp in nanoseconds when the webhook was created
   * @return created
   */
  @javax.annotation.Nullable
  public Long getCreated() {
    return created;
  }

  public void setCreated(@javax.annotation.Nullable Long created) {
    this.created = created;
  }


  public AccountWebhookWithStats successRate(@javax.annotation.Nullable Double successRate) {
    this.successRate = successRate;
    return this;
  }

  /**
   * Percentage of webhook deliveries that succeeded (2xx responses) over the recent measurement window. Ranges from 0 to 100. 
   * @return successRate
   */
  @javax.annotation.Nullable
  public Double getSuccessRate() {
    return successRate;
  }

  public void setSuccessRate(@javax.annotation.Nullable Double successRate) {
    this.successRate = successRate;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountWebhookWithStats accountWebhookWithStats = (AccountWebhookWithStats) o;
    return Objects.equals(this.id, accountWebhookWithStats.id) &&
        Objects.equals(this.enabled, accountWebhookWithStats.enabled) &&
        Objects.equals(this.url, accountWebhookWithStats.url) &&
        Objects.equals(this.processed, accountWebhookWithStats.processed) &&
        Objects.equals(this.sent, accountWebhookWithStats.sent) &&
        Objects.equals(this.dropped, accountWebhookWithStats.dropped) &&
        Objects.equals(this.smtpDropped, accountWebhookWithStats.smtpDropped) &&
        Objects.equals(this.delivered, accountWebhookWithStats.delivered) &&
        Objects.equals(this.softBounced, accountWebhookWithStats.softBounced) &&
        Objects.equals(this.hardBounced, accountWebhookWithStats.hardBounced) &&
        Objects.equals(this.opened, accountWebhookWithStats.opened) &&
        Objects.equals(this.clicked, accountWebhookWithStats.clicked) &&
        Objects.equals(this.unsubscribed, accountWebhookWithStats.unsubscribed) &&
        Objects.equals(this.spam, accountWebhookWithStats.spam) &&
        Objects.equals(this.uniqueOpen, accountWebhookWithStats.uniqueOpen) &&
        Objects.equals(this.uniqueClick, accountWebhookWithStats.uniqueClick) &&
        Objects.equals(this.status, accountWebhookWithStats.status) &&
        Objects.equals(this.disabledAt, accountWebhookWithStats.disabledAt) &&
        Objects.equals(this.disabledReason, accountWebhookWithStats.disabledReason) &&
        Objects.equals(this.created, accountWebhookWithStats.created) &&
        Objects.equals(this.successRate, accountWebhookWithStats.successRate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, enabled, url, processed, sent, dropped, smtpDropped, delivered, softBounced, hardBounced, opened, clicked, unsubscribed, spam, uniqueOpen, uniqueClick, status, disabledAt, disabledReason, created, successRate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountWebhookWithStats {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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
    sb.append("    uniqueOpen: ").append(toIndentedString(uniqueOpen)).append("\n");
    sb.append("    uniqueClick: ").append(toIndentedString(uniqueClick)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    disabledAt: ").append(toIndentedString(disabledAt)).append("\n");
    sb.append("    disabledReason: ").append(toIndentedString(disabledReason)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    successRate: ").append(toIndentedString(successRate)).append("\n");
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
    openapiFields.add("id");
    openapiFields.add("enabled");
    openapiFields.add("url");
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
    openapiFields.add("uniqueOpen");
    openapiFields.add("uniqueClick");
    openapiFields.add("status");
    openapiFields.add("disabledAt");
    openapiFields.add("disabledReason");
    openapiFields.add("created");
    openapiFields.add("successRate");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to AccountWebhookWithStats
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!AccountWebhookWithStats.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in AccountWebhookWithStats is not found in the empty JSON string", AccountWebhookWithStats.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!AccountWebhookWithStats.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `AccountWebhookWithStats` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("url") != null && !jsonObj.get("url").isJsonNull()) && !jsonObj.get("url").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `url` to be a primitive type in the JSON string but got `%s`", jsonObj.get("url").toString()));
      }
      if ((jsonObj.get("status") != null && !jsonObj.get("status").isJsonNull()) && !jsonObj.get("status").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `status` to be a primitive type in the JSON string but got `%s`", jsonObj.get("status").toString()));
      }
      // validate the optional field `status`
      if (jsonObj.get("status") != null && !jsonObj.get("status").isJsonNull()) {
        StatusEnum.validateJsonElement(jsonObj.get("status"));
      }
      if ((jsonObj.get("disabledReason") != null && !jsonObj.get("disabledReason").isJsonNull()) && !jsonObj.get("disabledReason").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `disabledReason` to be a primitive type in the JSON string but got `%s`", jsonObj.get("disabledReason").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!AccountWebhookWithStats.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'AccountWebhookWithStats' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<AccountWebhookWithStats> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(AccountWebhookWithStats.class));

       return (TypeAdapter<T>) new TypeAdapter<AccountWebhookWithStats>() {
           @Override
           public void write(JsonWriter out, AccountWebhookWithStats value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public AccountWebhookWithStats read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of AccountWebhookWithStats given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of AccountWebhookWithStats
   * @throws IOException if the JSON string is invalid with respect to AccountWebhookWithStats
   */
  public static AccountWebhookWithStats fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, AccountWebhookWithStats.class);
  }

  /**
   * Convert an instance of AccountWebhookWithStats to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

