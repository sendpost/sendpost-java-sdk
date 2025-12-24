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
 * AutoWarmupPlan
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-12-24T18:45:16.698236+05:30[Asia/Kolkata]", comments = "Generator version: 7.13.0")
public class AutoWarmupPlan {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  @javax.annotation.Nullable
  private Integer id;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  @javax.annotation.Nullable
  private String name;

  public static final String SERIALIZED_NAME_GMAIL_WARMUP_PLAN = "gmailWarmupPlan";
  @SerializedName(SERIALIZED_NAME_GMAIL_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String gmailWarmupPlan;

  public static final String SERIALIZED_NAME_YAHOO_WARMUP_PLAN = "yahooWarmupPlan";
  @SerializedName(SERIALIZED_NAME_YAHOO_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String yahooWarmupPlan;

  public static final String SERIALIZED_NAME_AOL_WARMUP_PLAN = "aolWarmupPlan";
  @SerializedName(SERIALIZED_NAME_AOL_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String aolWarmupPlan;

  public static final String SERIALIZED_NAME_MICROSOFT_WARMUP_PLAN = "microsoftWarmupPlan";
  @SerializedName(SERIALIZED_NAME_MICROSOFT_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String microsoftWarmupPlan;

  public static final String SERIALIZED_NAME_COMCAST_WARMUP_PLAN = "comcastWarmupPlan";
  @SerializedName(SERIALIZED_NAME_COMCAST_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String comcastWarmupPlan;

  public static final String SERIALIZED_NAME_YANDEX_WARMUP_PLAN = "yandexWarmupPlan";
  @SerializedName(SERIALIZED_NAME_YANDEX_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String yandexWarmupPlan;

  public static final String SERIALIZED_NAME_GMX_WARMUP_PLAN = "gmxWarmupPlan";
  @SerializedName(SERIALIZED_NAME_GMX_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String gmxWarmupPlan;

  public static final String SERIALIZED_NAME_MAILRU_WARMUP_PLAN = "mailruWarmupPlan";
  @SerializedName(SERIALIZED_NAME_MAILRU_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String mailruWarmupPlan;

  public static final String SERIALIZED_NAME_ICLOUD_WARMUP_PLAN = "icloudWarmupPlan";
  @SerializedName(SERIALIZED_NAME_ICLOUD_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String icloudWarmupPlan;

  public static final String SERIALIZED_NAME_ZOHO_WARMUP_PLAN = "zohoWarmupPlan";
  @SerializedName(SERIALIZED_NAME_ZOHO_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String zohoWarmupPlan;

  public static final String SERIALIZED_NAME_QQ_WARMUP_PLAN = "qqWarmupPlan";
  @SerializedName(SERIALIZED_NAME_QQ_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String qqWarmupPlan;

  public static final String SERIALIZED_NAME_DEFAULT_WARMUP_PLAN = "defaultWarmupPlan";
  @SerializedName(SERIALIZED_NAME_DEFAULT_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String defaultWarmupPlan;

  public static final String SERIALIZED_NAME_ATT_WARMUP_PLAN = "attWarmupPlan";
  @SerializedName(SERIALIZED_NAME_ATT_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String attWarmupPlan;

  public static final String SERIALIZED_NAME_OFFICE365_WARMUP_PLAN = "office365WarmupPlan";
  @SerializedName(SERIALIZED_NAME_OFFICE365_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String office365WarmupPlan;

  public static final String SERIALIZED_NAME_GOOGLEWORKSPACE_WARMUP_PLAN = "googleworkspaceWarmupPlan";
  @SerializedName(SERIALIZED_NAME_GOOGLEWORKSPACE_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String googleworkspaceWarmupPlan;

  public static final String SERIALIZED_NAME_PROOFPOINT_WARMUP_PLAN = "proofpointWarmupPlan";
  @SerializedName(SERIALIZED_NAME_PROOFPOINT_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String proofpointWarmupPlan;

  public static final String SERIALIZED_NAME_MIMECAST_WARMUP_PLAN = "mimecastWarmupPlan";
  @SerializedName(SERIALIZED_NAME_MIMECAST_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String mimecastWarmupPlan;

  public static final String SERIALIZED_NAME_BARRACUDA_WARMUP_PLAN = "barracudaWarmupPlan";
  @SerializedName(SERIALIZED_NAME_BARRACUDA_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String barracudaWarmupPlan;

  public static final String SERIALIZED_NAME_CISCOIRONPORT_WARMUP_PLAN = "ciscoironportWarmupPlan";
  @SerializedName(SERIALIZED_NAME_CISCOIRONPORT_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String ciscoironportWarmupPlan;

  public static final String SERIALIZED_NAME_RACKSPACE_WARMUP_PLAN = "rackspaceWarmupPlan";
  @SerializedName(SERIALIZED_NAME_RACKSPACE_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String rackspaceWarmupPlan;

  public static final String SERIALIZED_NAME_ZOHOBUSINESS_WARMUP_PLAN = "zohobusinessWarmupPlan";
  @SerializedName(SERIALIZED_NAME_ZOHOBUSINESS_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String zohobusinessWarmupPlan;

  public static final String SERIALIZED_NAME_AMAZONWORKMAIL_WARMUP_PLAN = "amazonworkmailWarmupPlan";
  @SerializedName(SERIALIZED_NAME_AMAZONWORKMAIL_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String amazonworkmailWarmupPlan;

  public static final String SERIALIZED_NAME_SYMANTEC_WARMUP_PLAN = "symantecWarmupPlan";
  @SerializedName(SERIALIZED_NAME_SYMANTEC_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String symantecWarmupPlan;

  public static final String SERIALIZED_NAME_FORTINET_WARMUP_PLAN = "fortinetWarmupPlan";
  @SerializedName(SERIALIZED_NAME_FORTINET_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String fortinetWarmupPlan;

  public static final String SERIALIZED_NAME_SOPHOS_WARMUP_PLAN = "sophosWarmupPlan";
  @SerializedName(SERIALIZED_NAME_SOPHOS_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String sophosWarmupPlan;

  public static final String SERIALIZED_NAME_TRENDMICRO_WARMUP_PLAN = "trendmicroWarmupPlan";
  @SerializedName(SERIALIZED_NAME_TRENDMICRO_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String trendmicroWarmupPlan;

  public static final String SERIALIZED_NAME_CHECKPOINT_WARMUP_PLAN = "checkpointWarmupPlan";
  @SerializedName(SERIALIZED_NAME_CHECKPOINT_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String checkpointWarmupPlan;

  public static final String SERIALIZED_NAME_CREATED = "created";
  @SerializedName(SERIALIZED_NAME_CREATED)
  @javax.annotation.Nullable
  private Long created;

  public static final String SERIALIZED_NAME_UPDATED = "updated";
  @SerializedName(SERIALIZED_NAME_UPDATED)
  @javax.annotation.Nullable
  private Long updated;

  public static final String SERIALIZED_NAME_WARMUP_INTERVAL = "warmupInterval";
  @SerializedName(SERIALIZED_NAME_WARMUP_INTERVAL)
  @javax.annotation.Nullable
  private Integer warmupInterval;

  public AutoWarmupPlan() {
  }

  public AutoWarmupPlan id(@javax.annotation.Nullable Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Unique ID for the auto-warmup plan
   * @return id
   */
  @javax.annotation.Nullable
  public Integer getId() {
    return id;
  }

  public void setId(@javax.annotation.Nullable Integer id) {
    this.id = id;
  }


  public AutoWarmupPlan name(@javax.annotation.Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the auto-warmup plan
   * @return name
   */
  @javax.annotation.Nullable
  public String getName() {
    return name;
  }

  public void setName(@javax.annotation.Nullable String name) {
    this.name = name;
  }


  public AutoWarmupPlan gmailWarmupPlan(@javax.annotation.Nullable String gmailWarmupPlan) {
    this.gmailWarmupPlan = gmailWarmupPlan;
    return this;
  }

  /**
   * Gmail warmup plan configuration in JSON format
   * @return gmailWarmupPlan
   */
  @javax.annotation.Nullable
  public String getGmailWarmupPlan() {
    return gmailWarmupPlan;
  }

  public void setGmailWarmupPlan(@javax.annotation.Nullable String gmailWarmupPlan) {
    this.gmailWarmupPlan = gmailWarmupPlan;
  }


  public AutoWarmupPlan yahooWarmupPlan(@javax.annotation.Nullable String yahooWarmupPlan) {
    this.yahooWarmupPlan = yahooWarmupPlan;
    return this;
  }

  /**
   * Yahoo warmup plan configuration in JSON format
   * @return yahooWarmupPlan
   */
  @javax.annotation.Nullable
  public String getYahooWarmupPlan() {
    return yahooWarmupPlan;
  }

  public void setYahooWarmupPlan(@javax.annotation.Nullable String yahooWarmupPlan) {
    this.yahooWarmupPlan = yahooWarmupPlan;
  }


  public AutoWarmupPlan aolWarmupPlan(@javax.annotation.Nullable String aolWarmupPlan) {
    this.aolWarmupPlan = aolWarmupPlan;
    return this;
  }

  /**
   * AOL warmup plan configuration in JSON format
   * @return aolWarmupPlan
   */
  @javax.annotation.Nullable
  public String getAolWarmupPlan() {
    return aolWarmupPlan;
  }

  public void setAolWarmupPlan(@javax.annotation.Nullable String aolWarmupPlan) {
    this.aolWarmupPlan = aolWarmupPlan;
  }


  public AutoWarmupPlan microsoftWarmupPlan(@javax.annotation.Nullable String microsoftWarmupPlan) {
    this.microsoftWarmupPlan = microsoftWarmupPlan;
    return this;
  }

  /**
   * Microsoft warmup plan configuration in JSON format
   * @return microsoftWarmupPlan
   */
  @javax.annotation.Nullable
  public String getMicrosoftWarmupPlan() {
    return microsoftWarmupPlan;
  }

  public void setMicrosoftWarmupPlan(@javax.annotation.Nullable String microsoftWarmupPlan) {
    this.microsoftWarmupPlan = microsoftWarmupPlan;
  }


  public AutoWarmupPlan comcastWarmupPlan(@javax.annotation.Nullable String comcastWarmupPlan) {
    this.comcastWarmupPlan = comcastWarmupPlan;
    return this;
  }

  /**
   * Comcast warmup plan configuration in JSON format
   * @return comcastWarmupPlan
   */
  @javax.annotation.Nullable
  public String getComcastWarmupPlan() {
    return comcastWarmupPlan;
  }

  public void setComcastWarmupPlan(@javax.annotation.Nullable String comcastWarmupPlan) {
    this.comcastWarmupPlan = comcastWarmupPlan;
  }


  public AutoWarmupPlan yandexWarmupPlan(@javax.annotation.Nullable String yandexWarmupPlan) {
    this.yandexWarmupPlan = yandexWarmupPlan;
    return this;
  }

  /**
   * Yandex warmup plan configuration in JSON format
   * @return yandexWarmupPlan
   */
  @javax.annotation.Nullable
  public String getYandexWarmupPlan() {
    return yandexWarmupPlan;
  }

  public void setYandexWarmupPlan(@javax.annotation.Nullable String yandexWarmupPlan) {
    this.yandexWarmupPlan = yandexWarmupPlan;
  }


  public AutoWarmupPlan gmxWarmupPlan(@javax.annotation.Nullable String gmxWarmupPlan) {
    this.gmxWarmupPlan = gmxWarmupPlan;
    return this;
  }

  /**
   * GMX warmup plan configuration in JSON format
   * @return gmxWarmupPlan
   */
  @javax.annotation.Nullable
  public String getGmxWarmupPlan() {
    return gmxWarmupPlan;
  }

  public void setGmxWarmupPlan(@javax.annotation.Nullable String gmxWarmupPlan) {
    this.gmxWarmupPlan = gmxWarmupPlan;
  }


  public AutoWarmupPlan mailruWarmupPlan(@javax.annotation.Nullable String mailruWarmupPlan) {
    this.mailruWarmupPlan = mailruWarmupPlan;
    return this;
  }

  /**
   * Mail.ru warmup plan configuration in JSON format
   * @return mailruWarmupPlan
   */
  @javax.annotation.Nullable
  public String getMailruWarmupPlan() {
    return mailruWarmupPlan;
  }

  public void setMailruWarmupPlan(@javax.annotation.Nullable String mailruWarmupPlan) {
    this.mailruWarmupPlan = mailruWarmupPlan;
  }


  public AutoWarmupPlan icloudWarmupPlan(@javax.annotation.Nullable String icloudWarmupPlan) {
    this.icloudWarmupPlan = icloudWarmupPlan;
    return this;
  }

  /**
   * iCloud warmup plan configuration in JSON format
   * @return icloudWarmupPlan
   */
  @javax.annotation.Nullable
  public String getIcloudWarmupPlan() {
    return icloudWarmupPlan;
  }

  public void setIcloudWarmupPlan(@javax.annotation.Nullable String icloudWarmupPlan) {
    this.icloudWarmupPlan = icloudWarmupPlan;
  }


  public AutoWarmupPlan zohoWarmupPlan(@javax.annotation.Nullable String zohoWarmupPlan) {
    this.zohoWarmupPlan = zohoWarmupPlan;
    return this;
  }

  /**
   * Zoho warmup plan configuration in JSON format
   * @return zohoWarmupPlan
   */
  @javax.annotation.Nullable
  public String getZohoWarmupPlan() {
    return zohoWarmupPlan;
  }

  public void setZohoWarmupPlan(@javax.annotation.Nullable String zohoWarmupPlan) {
    this.zohoWarmupPlan = zohoWarmupPlan;
  }


  public AutoWarmupPlan qqWarmupPlan(@javax.annotation.Nullable String qqWarmupPlan) {
    this.qqWarmupPlan = qqWarmupPlan;
    return this;
  }

  /**
   * QQ warmup plan configuration in JSON format
   * @return qqWarmupPlan
   */
  @javax.annotation.Nullable
  public String getQqWarmupPlan() {
    return qqWarmupPlan;
  }

  public void setQqWarmupPlan(@javax.annotation.Nullable String qqWarmupPlan) {
    this.qqWarmupPlan = qqWarmupPlan;
  }


  public AutoWarmupPlan defaultWarmupPlan(@javax.annotation.Nullable String defaultWarmupPlan) {
    this.defaultWarmupPlan = defaultWarmupPlan;
    return this;
  }

  /**
   * Default warmup plan configuration in JSON format
   * @return defaultWarmupPlan
   */
  @javax.annotation.Nullable
  public String getDefaultWarmupPlan() {
    return defaultWarmupPlan;
  }

  public void setDefaultWarmupPlan(@javax.annotation.Nullable String defaultWarmupPlan) {
    this.defaultWarmupPlan = defaultWarmupPlan;
  }


  public AutoWarmupPlan attWarmupPlan(@javax.annotation.Nullable String attWarmupPlan) {
    this.attWarmupPlan = attWarmupPlan;
    return this;
  }

  /**
   * AT&amp;T warmup plan configuration in JSON format
   * @return attWarmupPlan
   */
  @javax.annotation.Nullable
  public String getAttWarmupPlan() {
    return attWarmupPlan;
  }

  public void setAttWarmupPlan(@javax.annotation.Nullable String attWarmupPlan) {
    this.attWarmupPlan = attWarmupPlan;
  }


  public AutoWarmupPlan office365WarmupPlan(@javax.annotation.Nullable String office365WarmupPlan) {
    this.office365WarmupPlan = office365WarmupPlan;
    return this;
  }

  /**
   * Office365 warmup plan configuration in JSON format
   * @return office365WarmupPlan
   */
  @javax.annotation.Nullable
  public String getOffice365WarmupPlan() {
    return office365WarmupPlan;
  }

  public void setOffice365WarmupPlan(@javax.annotation.Nullable String office365WarmupPlan) {
    this.office365WarmupPlan = office365WarmupPlan;
  }


  public AutoWarmupPlan googleworkspaceWarmupPlan(@javax.annotation.Nullable String googleworkspaceWarmupPlan) {
    this.googleworkspaceWarmupPlan = googleworkspaceWarmupPlan;
    return this;
  }

  /**
   * Google Workspace warmup plan configuration in JSON format
   * @return googleworkspaceWarmupPlan
   */
  @javax.annotation.Nullable
  public String getGoogleworkspaceWarmupPlan() {
    return googleworkspaceWarmupPlan;
  }

  public void setGoogleworkspaceWarmupPlan(@javax.annotation.Nullable String googleworkspaceWarmupPlan) {
    this.googleworkspaceWarmupPlan = googleworkspaceWarmupPlan;
  }


  public AutoWarmupPlan proofpointWarmupPlan(@javax.annotation.Nullable String proofpointWarmupPlan) {
    this.proofpointWarmupPlan = proofpointWarmupPlan;
    return this;
  }

  /**
   * Proofpoint warmup plan configuration in JSON format
   * @return proofpointWarmupPlan
   */
  @javax.annotation.Nullable
  public String getProofpointWarmupPlan() {
    return proofpointWarmupPlan;
  }

  public void setProofpointWarmupPlan(@javax.annotation.Nullable String proofpointWarmupPlan) {
    this.proofpointWarmupPlan = proofpointWarmupPlan;
  }


  public AutoWarmupPlan mimecastWarmupPlan(@javax.annotation.Nullable String mimecastWarmupPlan) {
    this.mimecastWarmupPlan = mimecastWarmupPlan;
    return this;
  }

  /**
   * Mimecast warmup plan configuration in JSON format
   * @return mimecastWarmupPlan
   */
  @javax.annotation.Nullable
  public String getMimecastWarmupPlan() {
    return mimecastWarmupPlan;
  }

  public void setMimecastWarmupPlan(@javax.annotation.Nullable String mimecastWarmupPlan) {
    this.mimecastWarmupPlan = mimecastWarmupPlan;
  }


  public AutoWarmupPlan barracudaWarmupPlan(@javax.annotation.Nullable String barracudaWarmupPlan) {
    this.barracudaWarmupPlan = barracudaWarmupPlan;
    return this;
  }

  /**
   * Barracuda warmup plan configuration in JSON format
   * @return barracudaWarmupPlan
   */
  @javax.annotation.Nullable
  public String getBarracudaWarmupPlan() {
    return barracudaWarmupPlan;
  }

  public void setBarracudaWarmupPlan(@javax.annotation.Nullable String barracudaWarmupPlan) {
    this.barracudaWarmupPlan = barracudaWarmupPlan;
  }


  public AutoWarmupPlan ciscoironportWarmupPlan(@javax.annotation.Nullable String ciscoironportWarmupPlan) {
    this.ciscoironportWarmupPlan = ciscoironportWarmupPlan;
    return this;
  }

  /**
   * Cisco IronPort warmup plan configuration in JSON format
   * @return ciscoironportWarmupPlan
   */
  @javax.annotation.Nullable
  public String getCiscoironportWarmupPlan() {
    return ciscoironportWarmupPlan;
  }

  public void setCiscoironportWarmupPlan(@javax.annotation.Nullable String ciscoironportWarmupPlan) {
    this.ciscoironportWarmupPlan = ciscoironportWarmupPlan;
  }


  public AutoWarmupPlan rackspaceWarmupPlan(@javax.annotation.Nullable String rackspaceWarmupPlan) {
    this.rackspaceWarmupPlan = rackspaceWarmupPlan;
    return this;
  }

  /**
   * Rackspace warmup plan configuration in JSON format
   * @return rackspaceWarmupPlan
   */
  @javax.annotation.Nullable
  public String getRackspaceWarmupPlan() {
    return rackspaceWarmupPlan;
  }

  public void setRackspaceWarmupPlan(@javax.annotation.Nullable String rackspaceWarmupPlan) {
    this.rackspaceWarmupPlan = rackspaceWarmupPlan;
  }


  public AutoWarmupPlan zohobusinessWarmupPlan(@javax.annotation.Nullable String zohobusinessWarmupPlan) {
    this.zohobusinessWarmupPlan = zohobusinessWarmupPlan;
    return this;
  }

  /**
   * Zoho Business warmup plan configuration in JSON format
   * @return zohobusinessWarmupPlan
   */
  @javax.annotation.Nullable
  public String getZohobusinessWarmupPlan() {
    return zohobusinessWarmupPlan;
  }

  public void setZohobusinessWarmupPlan(@javax.annotation.Nullable String zohobusinessWarmupPlan) {
    this.zohobusinessWarmupPlan = zohobusinessWarmupPlan;
  }


  public AutoWarmupPlan amazonworkmailWarmupPlan(@javax.annotation.Nullable String amazonworkmailWarmupPlan) {
    this.amazonworkmailWarmupPlan = amazonworkmailWarmupPlan;
    return this;
  }

  /**
   * Amazon WorkMail warmup plan configuration in JSON format
   * @return amazonworkmailWarmupPlan
   */
  @javax.annotation.Nullable
  public String getAmazonworkmailWarmupPlan() {
    return amazonworkmailWarmupPlan;
  }

  public void setAmazonworkmailWarmupPlan(@javax.annotation.Nullable String amazonworkmailWarmupPlan) {
    this.amazonworkmailWarmupPlan = amazonworkmailWarmupPlan;
  }


  public AutoWarmupPlan symantecWarmupPlan(@javax.annotation.Nullable String symantecWarmupPlan) {
    this.symantecWarmupPlan = symantecWarmupPlan;
    return this;
  }

  /**
   * Symantec warmup plan configuration in JSON format
   * @return symantecWarmupPlan
   */
  @javax.annotation.Nullable
  public String getSymantecWarmupPlan() {
    return symantecWarmupPlan;
  }

  public void setSymantecWarmupPlan(@javax.annotation.Nullable String symantecWarmupPlan) {
    this.symantecWarmupPlan = symantecWarmupPlan;
  }


  public AutoWarmupPlan fortinetWarmupPlan(@javax.annotation.Nullable String fortinetWarmupPlan) {
    this.fortinetWarmupPlan = fortinetWarmupPlan;
    return this;
  }

  /**
   * Fortinet warmup plan configuration in JSON format
   * @return fortinetWarmupPlan
   */
  @javax.annotation.Nullable
  public String getFortinetWarmupPlan() {
    return fortinetWarmupPlan;
  }

  public void setFortinetWarmupPlan(@javax.annotation.Nullable String fortinetWarmupPlan) {
    this.fortinetWarmupPlan = fortinetWarmupPlan;
  }


  public AutoWarmupPlan sophosWarmupPlan(@javax.annotation.Nullable String sophosWarmupPlan) {
    this.sophosWarmupPlan = sophosWarmupPlan;
    return this;
  }

  /**
   * Sophos warmup plan configuration in JSON format
   * @return sophosWarmupPlan
   */
  @javax.annotation.Nullable
  public String getSophosWarmupPlan() {
    return sophosWarmupPlan;
  }

  public void setSophosWarmupPlan(@javax.annotation.Nullable String sophosWarmupPlan) {
    this.sophosWarmupPlan = sophosWarmupPlan;
  }


  public AutoWarmupPlan trendmicroWarmupPlan(@javax.annotation.Nullable String trendmicroWarmupPlan) {
    this.trendmicroWarmupPlan = trendmicroWarmupPlan;
    return this;
  }

  /**
   * Trend Micro warmup plan configuration in JSON format
   * @return trendmicroWarmupPlan
   */
  @javax.annotation.Nullable
  public String getTrendmicroWarmupPlan() {
    return trendmicroWarmupPlan;
  }

  public void setTrendmicroWarmupPlan(@javax.annotation.Nullable String trendmicroWarmupPlan) {
    this.trendmicroWarmupPlan = trendmicroWarmupPlan;
  }


  public AutoWarmupPlan checkpointWarmupPlan(@javax.annotation.Nullable String checkpointWarmupPlan) {
    this.checkpointWarmupPlan = checkpointWarmupPlan;
    return this;
  }

  /**
   * Checkpoint warmup plan configuration in JSON format
   * @return checkpointWarmupPlan
   */
  @javax.annotation.Nullable
  public String getCheckpointWarmupPlan() {
    return checkpointWarmupPlan;
  }

  public void setCheckpointWarmupPlan(@javax.annotation.Nullable String checkpointWarmupPlan) {
    this.checkpointWarmupPlan = checkpointWarmupPlan;
  }


  public AutoWarmupPlan created(@javax.annotation.Nullable Long created) {
    this.created = created;
    return this;
  }

  /**
   * UNIX epoch nano timestamp when the warmup plan was created
   * @return created
   */
  @javax.annotation.Nullable
  public Long getCreated() {
    return created;
  }

  public void setCreated(@javax.annotation.Nullable Long created) {
    this.created = created;
  }


  public AutoWarmupPlan updated(@javax.annotation.Nullable Long updated) {
    this.updated = updated;
    return this;
  }

  /**
   * UNIX epoch nano timestamp when the warmup plan was last updated
   * @return updated
   */
  @javax.annotation.Nullable
  public Long getUpdated() {
    return updated;
  }

  public void setUpdated(@javax.annotation.Nullable Long updated) {
    this.updated = updated;
  }


  public AutoWarmupPlan warmupInterval(@javax.annotation.Nullable Integer warmupInterval) {
    this.warmupInterval = warmupInterval;
    return this;
  }

  /**
   * Warmup interval in hours
   * @return warmupInterval
   */
  @javax.annotation.Nullable
  public Integer getWarmupInterval() {
    return warmupInterval;
  }

  public void setWarmupInterval(@javax.annotation.Nullable Integer warmupInterval) {
    this.warmupInterval = warmupInterval;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AutoWarmupPlan autoWarmupPlan = (AutoWarmupPlan) o;
    return Objects.equals(this.id, autoWarmupPlan.id) &&
        Objects.equals(this.name, autoWarmupPlan.name) &&
        Objects.equals(this.gmailWarmupPlan, autoWarmupPlan.gmailWarmupPlan) &&
        Objects.equals(this.yahooWarmupPlan, autoWarmupPlan.yahooWarmupPlan) &&
        Objects.equals(this.aolWarmupPlan, autoWarmupPlan.aolWarmupPlan) &&
        Objects.equals(this.microsoftWarmupPlan, autoWarmupPlan.microsoftWarmupPlan) &&
        Objects.equals(this.comcastWarmupPlan, autoWarmupPlan.comcastWarmupPlan) &&
        Objects.equals(this.yandexWarmupPlan, autoWarmupPlan.yandexWarmupPlan) &&
        Objects.equals(this.gmxWarmupPlan, autoWarmupPlan.gmxWarmupPlan) &&
        Objects.equals(this.mailruWarmupPlan, autoWarmupPlan.mailruWarmupPlan) &&
        Objects.equals(this.icloudWarmupPlan, autoWarmupPlan.icloudWarmupPlan) &&
        Objects.equals(this.zohoWarmupPlan, autoWarmupPlan.zohoWarmupPlan) &&
        Objects.equals(this.qqWarmupPlan, autoWarmupPlan.qqWarmupPlan) &&
        Objects.equals(this.defaultWarmupPlan, autoWarmupPlan.defaultWarmupPlan) &&
        Objects.equals(this.attWarmupPlan, autoWarmupPlan.attWarmupPlan) &&
        Objects.equals(this.office365WarmupPlan, autoWarmupPlan.office365WarmupPlan) &&
        Objects.equals(this.googleworkspaceWarmupPlan, autoWarmupPlan.googleworkspaceWarmupPlan) &&
        Objects.equals(this.proofpointWarmupPlan, autoWarmupPlan.proofpointWarmupPlan) &&
        Objects.equals(this.mimecastWarmupPlan, autoWarmupPlan.mimecastWarmupPlan) &&
        Objects.equals(this.barracudaWarmupPlan, autoWarmupPlan.barracudaWarmupPlan) &&
        Objects.equals(this.ciscoironportWarmupPlan, autoWarmupPlan.ciscoironportWarmupPlan) &&
        Objects.equals(this.rackspaceWarmupPlan, autoWarmupPlan.rackspaceWarmupPlan) &&
        Objects.equals(this.zohobusinessWarmupPlan, autoWarmupPlan.zohobusinessWarmupPlan) &&
        Objects.equals(this.amazonworkmailWarmupPlan, autoWarmupPlan.amazonworkmailWarmupPlan) &&
        Objects.equals(this.symantecWarmupPlan, autoWarmupPlan.symantecWarmupPlan) &&
        Objects.equals(this.fortinetWarmupPlan, autoWarmupPlan.fortinetWarmupPlan) &&
        Objects.equals(this.sophosWarmupPlan, autoWarmupPlan.sophosWarmupPlan) &&
        Objects.equals(this.trendmicroWarmupPlan, autoWarmupPlan.trendmicroWarmupPlan) &&
        Objects.equals(this.checkpointWarmupPlan, autoWarmupPlan.checkpointWarmupPlan) &&
        Objects.equals(this.created, autoWarmupPlan.created) &&
        Objects.equals(this.updated, autoWarmupPlan.updated) &&
        Objects.equals(this.warmupInterval, autoWarmupPlan.warmupInterval);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, gmailWarmupPlan, yahooWarmupPlan, aolWarmupPlan, microsoftWarmupPlan, comcastWarmupPlan, yandexWarmupPlan, gmxWarmupPlan, mailruWarmupPlan, icloudWarmupPlan, zohoWarmupPlan, qqWarmupPlan, defaultWarmupPlan, attWarmupPlan, office365WarmupPlan, googleworkspaceWarmupPlan, proofpointWarmupPlan, mimecastWarmupPlan, barracudaWarmupPlan, ciscoironportWarmupPlan, rackspaceWarmupPlan, zohobusinessWarmupPlan, amazonworkmailWarmupPlan, symantecWarmupPlan, fortinetWarmupPlan, sophosWarmupPlan, trendmicroWarmupPlan, checkpointWarmupPlan, created, updated, warmupInterval);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AutoWarmupPlan {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    gmailWarmupPlan: ").append(toIndentedString(gmailWarmupPlan)).append("\n");
    sb.append("    yahooWarmupPlan: ").append(toIndentedString(yahooWarmupPlan)).append("\n");
    sb.append("    aolWarmupPlan: ").append(toIndentedString(aolWarmupPlan)).append("\n");
    sb.append("    microsoftWarmupPlan: ").append(toIndentedString(microsoftWarmupPlan)).append("\n");
    sb.append("    comcastWarmupPlan: ").append(toIndentedString(comcastWarmupPlan)).append("\n");
    sb.append("    yandexWarmupPlan: ").append(toIndentedString(yandexWarmupPlan)).append("\n");
    sb.append("    gmxWarmupPlan: ").append(toIndentedString(gmxWarmupPlan)).append("\n");
    sb.append("    mailruWarmupPlan: ").append(toIndentedString(mailruWarmupPlan)).append("\n");
    sb.append("    icloudWarmupPlan: ").append(toIndentedString(icloudWarmupPlan)).append("\n");
    sb.append("    zohoWarmupPlan: ").append(toIndentedString(zohoWarmupPlan)).append("\n");
    sb.append("    qqWarmupPlan: ").append(toIndentedString(qqWarmupPlan)).append("\n");
    sb.append("    defaultWarmupPlan: ").append(toIndentedString(defaultWarmupPlan)).append("\n");
    sb.append("    attWarmupPlan: ").append(toIndentedString(attWarmupPlan)).append("\n");
    sb.append("    office365WarmupPlan: ").append(toIndentedString(office365WarmupPlan)).append("\n");
    sb.append("    googleworkspaceWarmupPlan: ").append(toIndentedString(googleworkspaceWarmupPlan)).append("\n");
    sb.append("    proofpointWarmupPlan: ").append(toIndentedString(proofpointWarmupPlan)).append("\n");
    sb.append("    mimecastWarmupPlan: ").append(toIndentedString(mimecastWarmupPlan)).append("\n");
    sb.append("    barracudaWarmupPlan: ").append(toIndentedString(barracudaWarmupPlan)).append("\n");
    sb.append("    ciscoironportWarmupPlan: ").append(toIndentedString(ciscoironportWarmupPlan)).append("\n");
    sb.append("    rackspaceWarmupPlan: ").append(toIndentedString(rackspaceWarmupPlan)).append("\n");
    sb.append("    zohobusinessWarmupPlan: ").append(toIndentedString(zohobusinessWarmupPlan)).append("\n");
    sb.append("    amazonworkmailWarmupPlan: ").append(toIndentedString(amazonworkmailWarmupPlan)).append("\n");
    sb.append("    symantecWarmupPlan: ").append(toIndentedString(symantecWarmupPlan)).append("\n");
    sb.append("    fortinetWarmupPlan: ").append(toIndentedString(fortinetWarmupPlan)).append("\n");
    sb.append("    sophosWarmupPlan: ").append(toIndentedString(sophosWarmupPlan)).append("\n");
    sb.append("    trendmicroWarmupPlan: ").append(toIndentedString(trendmicroWarmupPlan)).append("\n");
    sb.append("    checkpointWarmupPlan: ").append(toIndentedString(checkpointWarmupPlan)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    updated: ").append(toIndentedString(updated)).append("\n");
    sb.append("    warmupInterval: ").append(toIndentedString(warmupInterval)).append("\n");
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
    openapiFields.add("name");
    openapiFields.add("gmailWarmupPlan");
    openapiFields.add("yahooWarmupPlan");
    openapiFields.add("aolWarmupPlan");
    openapiFields.add("microsoftWarmupPlan");
    openapiFields.add("comcastWarmupPlan");
    openapiFields.add("yandexWarmupPlan");
    openapiFields.add("gmxWarmupPlan");
    openapiFields.add("mailruWarmupPlan");
    openapiFields.add("icloudWarmupPlan");
    openapiFields.add("zohoWarmupPlan");
    openapiFields.add("qqWarmupPlan");
    openapiFields.add("defaultWarmupPlan");
    openapiFields.add("attWarmupPlan");
    openapiFields.add("office365WarmupPlan");
    openapiFields.add("googleworkspaceWarmupPlan");
    openapiFields.add("proofpointWarmupPlan");
    openapiFields.add("mimecastWarmupPlan");
    openapiFields.add("barracudaWarmupPlan");
    openapiFields.add("ciscoironportWarmupPlan");
    openapiFields.add("rackspaceWarmupPlan");
    openapiFields.add("zohobusinessWarmupPlan");
    openapiFields.add("amazonworkmailWarmupPlan");
    openapiFields.add("symantecWarmupPlan");
    openapiFields.add("fortinetWarmupPlan");
    openapiFields.add("sophosWarmupPlan");
    openapiFields.add("trendmicroWarmupPlan");
    openapiFields.add("checkpointWarmupPlan");
    openapiFields.add("created");
    openapiFields.add("updated");
    openapiFields.add("warmupInterval");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to AutoWarmupPlan
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!AutoWarmupPlan.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in AutoWarmupPlan is not found in the empty JSON string", AutoWarmupPlan.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!AutoWarmupPlan.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `AutoWarmupPlan` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("name") != null && !jsonObj.get("name").isJsonNull()) && !jsonObj.get("name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("name").toString()));
      }
      if ((jsonObj.get("gmailWarmupPlan") != null && !jsonObj.get("gmailWarmupPlan").isJsonNull()) && !jsonObj.get("gmailWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `gmailWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("gmailWarmupPlan").toString()));
      }
      if ((jsonObj.get("yahooWarmupPlan") != null && !jsonObj.get("yahooWarmupPlan").isJsonNull()) && !jsonObj.get("yahooWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `yahooWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("yahooWarmupPlan").toString()));
      }
      if ((jsonObj.get("aolWarmupPlan") != null && !jsonObj.get("aolWarmupPlan").isJsonNull()) && !jsonObj.get("aolWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `aolWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("aolWarmupPlan").toString()));
      }
      if ((jsonObj.get("microsoftWarmupPlan") != null && !jsonObj.get("microsoftWarmupPlan").isJsonNull()) && !jsonObj.get("microsoftWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `microsoftWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("microsoftWarmupPlan").toString()));
      }
      if ((jsonObj.get("comcastWarmupPlan") != null && !jsonObj.get("comcastWarmupPlan").isJsonNull()) && !jsonObj.get("comcastWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `comcastWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("comcastWarmupPlan").toString()));
      }
      if ((jsonObj.get("yandexWarmupPlan") != null && !jsonObj.get("yandexWarmupPlan").isJsonNull()) && !jsonObj.get("yandexWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `yandexWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("yandexWarmupPlan").toString()));
      }
      if ((jsonObj.get("gmxWarmupPlan") != null && !jsonObj.get("gmxWarmupPlan").isJsonNull()) && !jsonObj.get("gmxWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `gmxWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("gmxWarmupPlan").toString()));
      }
      if ((jsonObj.get("mailruWarmupPlan") != null && !jsonObj.get("mailruWarmupPlan").isJsonNull()) && !jsonObj.get("mailruWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `mailruWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("mailruWarmupPlan").toString()));
      }
      if ((jsonObj.get("icloudWarmupPlan") != null && !jsonObj.get("icloudWarmupPlan").isJsonNull()) && !jsonObj.get("icloudWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `icloudWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("icloudWarmupPlan").toString()));
      }
      if ((jsonObj.get("zohoWarmupPlan") != null && !jsonObj.get("zohoWarmupPlan").isJsonNull()) && !jsonObj.get("zohoWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `zohoWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("zohoWarmupPlan").toString()));
      }
      if ((jsonObj.get("qqWarmupPlan") != null && !jsonObj.get("qqWarmupPlan").isJsonNull()) && !jsonObj.get("qqWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `qqWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("qqWarmupPlan").toString()));
      }
      if ((jsonObj.get("defaultWarmupPlan") != null && !jsonObj.get("defaultWarmupPlan").isJsonNull()) && !jsonObj.get("defaultWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `defaultWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("defaultWarmupPlan").toString()));
      }
      if ((jsonObj.get("attWarmupPlan") != null && !jsonObj.get("attWarmupPlan").isJsonNull()) && !jsonObj.get("attWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `attWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("attWarmupPlan").toString()));
      }
      if ((jsonObj.get("office365WarmupPlan") != null && !jsonObj.get("office365WarmupPlan").isJsonNull()) && !jsonObj.get("office365WarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `office365WarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("office365WarmupPlan").toString()));
      }
      if ((jsonObj.get("googleworkspaceWarmupPlan") != null && !jsonObj.get("googleworkspaceWarmupPlan").isJsonNull()) && !jsonObj.get("googleworkspaceWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `googleworkspaceWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("googleworkspaceWarmupPlan").toString()));
      }
      if ((jsonObj.get("proofpointWarmupPlan") != null && !jsonObj.get("proofpointWarmupPlan").isJsonNull()) && !jsonObj.get("proofpointWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `proofpointWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("proofpointWarmupPlan").toString()));
      }
      if ((jsonObj.get("mimecastWarmupPlan") != null && !jsonObj.get("mimecastWarmupPlan").isJsonNull()) && !jsonObj.get("mimecastWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `mimecastWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("mimecastWarmupPlan").toString()));
      }
      if ((jsonObj.get("barracudaWarmupPlan") != null && !jsonObj.get("barracudaWarmupPlan").isJsonNull()) && !jsonObj.get("barracudaWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `barracudaWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("barracudaWarmupPlan").toString()));
      }
      if ((jsonObj.get("ciscoironportWarmupPlan") != null && !jsonObj.get("ciscoironportWarmupPlan").isJsonNull()) && !jsonObj.get("ciscoironportWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `ciscoironportWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("ciscoironportWarmupPlan").toString()));
      }
      if ((jsonObj.get("rackspaceWarmupPlan") != null && !jsonObj.get("rackspaceWarmupPlan").isJsonNull()) && !jsonObj.get("rackspaceWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `rackspaceWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("rackspaceWarmupPlan").toString()));
      }
      if ((jsonObj.get("zohobusinessWarmupPlan") != null && !jsonObj.get("zohobusinessWarmupPlan").isJsonNull()) && !jsonObj.get("zohobusinessWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `zohobusinessWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("zohobusinessWarmupPlan").toString()));
      }
      if ((jsonObj.get("amazonworkmailWarmupPlan") != null && !jsonObj.get("amazonworkmailWarmupPlan").isJsonNull()) && !jsonObj.get("amazonworkmailWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `amazonworkmailWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("amazonworkmailWarmupPlan").toString()));
      }
      if ((jsonObj.get("symantecWarmupPlan") != null && !jsonObj.get("symantecWarmupPlan").isJsonNull()) && !jsonObj.get("symantecWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `symantecWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("symantecWarmupPlan").toString()));
      }
      if ((jsonObj.get("fortinetWarmupPlan") != null && !jsonObj.get("fortinetWarmupPlan").isJsonNull()) && !jsonObj.get("fortinetWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `fortinetWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("fortinetWarmupPlan").toString()));
      }
      if ((jsonObj.get("sophosWarmupPlan") != null && !jsonObj.get("sophosWarmupPlan").isJsonNull()) && !jsonObj.get("sophosWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `sophosWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("sophosWarmupPlan").toString()));
      }
      if ((jsonObj.get("trendmicroWarmupPlan") != null && !jsonObj.get("trendmicroWarmupPlan").isJsonNull()) && !jsonObj.get("trendmicroWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `trendmicroWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("trendmicroWarmupPlan").toString()));
      }
      if ((jsonObj.get("checkpointWarmupPlan") != null && !jsonObj.get("checkpointWarmupPlan").isJsonNull()) && !jsonObj.get("checkpointWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `checkpointWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("checkpointWarmupPlan").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!AutoWarmupPlan.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'AutoWarmupPlan' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<AutoWarmupPlan> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(AutoWarmupPlan.class));

       return (TypeAdapter<T>) new TypeAdapter<AutoWarmupPlan>() {
           @Override
           public void write(JsonWriter out, AutoWarmupPlan value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public AutoWarmupPlan read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of AutoWarmupPlan given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of AutoWarmupPlan
   * @throws IOException if the JSON string is invalid with respect to AutoWarmupPlan
   */
  public static AutoWarmupPlan fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, AutoWarmupPlan.class);
  }

  /**
   * Convert an instance of AutoWarmupPlan to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

