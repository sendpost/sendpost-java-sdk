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
import sendpost_java_sdk.Domain;

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
 * IP
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-12-17T13:23:02.403668+05:30[Asia/Kolkata]", comments = "Generator version: 7.13.0")
public class IP {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  @javax.annotation.Nonnull
  private Integer id;

  public static final String SERIALIZED_NAME_PUBLIC_I_P = "publicIP";
  @SerializedName(SERIALIZED_NAME_PUBLIC_I_P)
  @javax.annotation.Nonnull
  private String publicIP;

  public static final String SERIALIZED_NAME_SYSTEM_DOMAIN = "systemDomain";
  @SerializedName(SERIALIZED_NAME_SYSTEM_DOMAIN)
  @javax.annotation.Nullable
  private Domain systemDomain;

  public static final String SERIALIZED_NAME_REVERSE_D_N_S_HOSTNAME = "reverseDNSHostname";
  @SerializedName(SERIALIZED_NAME_REVERSE_D_N_S_HOSTNAME)
  @javax.annotation.Nullable
  private String reverseDNSHostname;

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  @javax.annotation.Nullable
  private Integer type;

  public static final String SERIALIZED_NAME_GMAIL_SETTINGS = "gmailSettings";
  @SerializedName(SERIALIZED_NAME_GMAIL_SETTINGS)
  @javax.annotation.Nullable
  private String gmailSettings;

  public static final String SERIALIZED_NAME_YAHOO_SETTINGS = "yahooSettings";
  @SerializedName(SERIALIZED_NAME_YAHOO_SETTINGS)
  @javax.annotation.Nullable
  private String yahooSettings;

  public static final String SERIALIZED_NAME_AOL_SETTINGS = "aolSettings";
  @SerializedName(SERIALIZED_NAME_AOL_SETTINGS)
  @javax.annotation.Nullable
  private String aolSettings;

  public static final String SERIALIZED_NAME_MICROSOFT_SETTINGS = "microsoftSettings";
  @SerializedName(SERIALIZED_NAME_MICROSOFT_SETTINGS)
  @javax.annotation.Nullable
  private String microsoftSettings;

  public static final String SERIALIZED_NAME_COMCAST_SETTINGS = "comcastSettings";
  @SerializedName(SERIALIZED_NAME_COMCAST_SETTINGS)
  @javax.annotation.Nullable
  private String comcastSettings;

  public static final String SERIALIZED_NAME_YANDEX_SETTINGS = "yandexSettings";
  @SerializedName(SERIALIZED_NAME_YANDEX_SETTINGS)
  @javax.annotation.Nullable
  private String yandexSettings;

  public static final String SERIALIZED_NAME_GMX_SETTINGS = "gmxSettings";
  @SerializedName(SERIALIZED_NAME_GMX_SETTINGS)
  @javax.annotation.Nullable
  private String gmxSettings;

  public static final String SERIALIZED_NAME_MAILRU_SETTINGS = "mailruSettings";
  @SerializedName(SERIALIZED_NAME_MAILRU_SETTINGS)
  @javax.annotation.Nullable
  private String mailruSettings;

  public static final String SERIALIZED_NAME_ICLOUD_SETTINGS = "icloudSettings";
  @SerializedName(SERIALIZED_NAME_ICLOUD_SETTINGS)
  @javax.annotation.Nullable
  private String icloudSettings;

  public static final String SERIALIZED_NAME_ZOHO_SETTINGS = "zohoSettings";
  @SerializedName(SERIALIZED_NAME_ZOHO_SETTINGS)
  @javax.annotation.Nullable
  private String zohoSettings;

  public static final String SERIALIZED_NAME_QQ_SETTINGS = "qqSettings";
  @SerializedName(SERIALIZED_NAME_QQ_SETTINGS)
  @javax.annotation.Nullable
  private String qqSettings;

  public static final String SERIALIZED_NAME_DEFAULT_SETTINGS = "defaultSettings";
  @SerializedName(SERIALIZED_NAME_DEFAULT_SETTINGS)
  @javax.annotation.Nullable
  private String defaultSettings;

  public static final String SERIALIZED_NAME_ATT_SETTINGS = "attSettings";
  @SerializedName(SERIALIZED_NAME_ATT_SETTINGS)
  @javax.annotation.Nullable
  private String attSettings;

  public static final String SERIALIZED_NAME_CREATED = "created";
  @SerializedName(SERIALIZED_NAME_CREATED)
  @javax.annotation.Nonnull
  private Integer created;

  public static final String SERIALIZED_NAME_INFRA_CLASSIFICATION = "infraClassification";
  @SerializedName(SERIALIZED_NAME_INFRA_CLASSIFICATION)
  @javax.annotation.Nullable
  private String infraClassification;

  public static final String SERIALIZED_NAME_INFRA_MONITOR = "infraMonitor";
  @SerializedName(SERIALIZED_NAME_INFRA_MONITOR)
  @javax.annotation.Nullable
  private Boolean infraMonitor;

  public static final String SERIALIZED_NAME_STATE = "state";
  @SerializedName(SERIALIZED_NAME_STATE)
  @javax.annotation.Nullable
  private Integer state;

  public static final String SERIALIZED_NAME_AUTO_WARMUP_PLAN = "autoWarmupPlan";
  @SerializedName(SERIALIZED_NAME_AUTO_WARMUP_PLAN)
  @javax.annotation.Nullable
  private String autoWarmupPlan;

  public IP() {
  }

  public IP id(@javax.annotation.Nonnull Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Unique ID for the IP
   * @return id
   */
  @javax.annotation.Nonnull
  public Integer getId() {
    return id;
  }

  public void setId(@javax.annotation.Nonnull Integer id) {
    this.id = id;
  }


  public IP publicIP(@javax.annotation.Nonnull String publicIP) {
    this.publicIP = publicIP;
    return this;
  }

  /**
   * The public IP address associated with the resource
   * @return publicIP
   */
  @javax.annotation.Nonnull
  public String getPublicIP() {
    return publicIP;
  }

  public void setPublicIP(@javax.annotation.Nonnull String publicIP) {
    this.publicIP = publicIP;
  }


  public IP systemDomain(@javax.annotation.Nullable Domain systemDomain) {
    this.systemDomain = systemDomain;
    return this;
  }

  /**
   * Details of the system domain associated with the IP
   * @return systemDomain
   */
  @javax.annotation.Nullable
  public Domain getSystemDomain() {
    return systemDomain;
  }

  public void setSystemDomain(@javax.annotation.Nullable Domain systemDomain) {
    this.systemDomain = systemDomain;
  }


  public IP reverseDNSHostname(@javax.annotation.Nullable String reverseDNSHostname) {
    this.reverseDNSHostname = reverseDNSHostname;
    return this;
  }

  /**
   * The reverse DNS hostname for the IP
   * @return reverseDNSHostname
   */
  @javax.annotation.Nullable
  public String getReverseDNSHostname() {
    return reverseDNSHostname;
  }

  public void setReverseDNSHostname(@javax.annotation.Nullable String reverseDNSHostname) {
    this.reverseDNSHostname = reverseDNSHostname;
  }


  public IP type(@javax.annotation.Nullable Integer type) {
    this.type = type;
    return this;
  }

  /**
   * Type of the IP
   * @return type
   */
  @javax.annotation.Nullable
  public Integer getType() {
    return type;
  }

  public void setType(@javax.annotation.Nullable Integer type) {
    this.type = type;
  }


  public IP gmailSettings(@javax.annotation.Nullable String gmailSettings) {
    this.gmailSettings = gmailSettings;
    return this;
  }

  /**
   * Configuration for Gmail delivery settings in JSON format
   * @return gmailSettings
   */
  @javax.annotation.Nullable
  public String getGmailSettings() {
    return gmailSettings;
  }

  public void setGmailSettings(@javax.annotation.Nullable String gmailSettings) {
    this.gmailSettings = gmailSettings;
  }


  public IP yahooSettings(@javax.annotation.Nullable String yahooSettings) {
    this.yahooSettings = yahooSettings;
    return this;
  }

  /**
   * Configuration for Yahoo delivery settings in JSON format
   * @return yahooSettings
   */
  @javax.annotation.Nullable
  public String getYahooSettings() {
    return yahooSettings;
  }

  public void setYahooSettings(@javax.annotation.Nullable String yahooSettings) {
    this.yahooSettings = yahooSettings;
  }


  public IP aolSettings(@javax.annotation.Nullable String aolSettings) {
    this.aolSettings = aolSettings;
    return this;
  }

  /**
   * Configuration for AOL delivery settings in JSON format
   * @return aolSettings
   */
  @javax.annotation.Nullable
  public String getAolSettings() {
    return aolSettings;
  }

  public void setAolSettings(@javax.annotation.Nullable String aolSettings) {
    this.aolSettings = aolSettings;
  }


  public IP microsoftSettings(@javax.annotation.Nullable String microsoftSettings) {
    this.microsoftSettings = microsoftSettings;
    return this;
  }

  /**
   * Configuration for Microsoft delivery settings in JSON format
   * @return microsoftSettings
   */
  @javax.annotation.Nullable
  public String getMicrosoftSettings() {
    return microsoftSettings;
  }

  public void setMicrosoftSettings(@javax.annotation.Nullable String microsoftSettings) {
    this.microsoftSettings = microsoftSettings;
  }


  public IP comcastSettings(@javax.annotation.Nullable String comcastSettings) {
    this.comcastSettings = comcastSettings;
    return this;
  }

  /**
   * Configuration for Comcast delivery settings in JSON format
   * @return comcastSettings
   */
  @javax.annotation.Nullable
  public String getComcastSettings() {
    return comcastSettings;
  }

  public void setComcastSettings(@javax.annotation.Nullable String comcastSettings) {
    this.comcastSettings = comcastSettings;
  }


  public IP yandexSettings(@javax.annotation.Nullable String yandexSettings) {
    this.yandexSettings = yandexSettings;
    return this;
  }

  /**
   * Configuration for Yandex delivery settings in JSON format
   * @return yandexSettings
   */
  @javax.annotation.Nullable
  public String getYandexSettings() {
    return yandexSettings;
  }

  public void setYandexSettings(@javax.annotation.Nullable String yandexSettings) {
    this.yandexSettings = yandexSettings;
  }


  public IP gmxSettings(@javax.annotation.Nullable String gmxSettings) {
    this.gmxSettings = gmxSettings;
    return this;
  }

  /**
   * Configuration for GMX delivery settings in JSON format
   * @return gmxSettings
   */
  @javax.annotation.Nullable
  public String getGmxSettings() {
    return gmxSettings;
  }

  public void setGmxSettings(@javax.annotation.Nullable String gmxSettings) {
    this.gmxSettings = gmxSettings;
  }


  public IP mailruSettings(@javax.annotation.Nullable String mailruSettings) {
    this.mailruSettings = mailruSettings;
    return this;
  }

  /**
   * Configuration for Mail.ru delivery settings in JSON format
   * @return mailruSettings
   */
  @javax.annotation.Nullable
  public String getMailruSettings() {
    return mailruSettings;
  }

  public void setMailruSettings(@javax.annotation.Nullable String mailruSettings) {
    this.mailruSettings = mailruSettings;
  }


  public IP icloudSettings(@javax.annotation.Nullable String icloudSettings) {
    this.icloudSettings = icloudSettings;
    return this;
  }

  /**
   * Configuration for iCloud delivery settings in JSON format
   * @return icloudSettings
   */
  @javax.annotation.Nullable
  public String getIcloudSettings() {
    return icloudSettings;
  }

  public void setIcloudSettings(@javax.annotation.Nullable String icloudSettings) {
    this.icloudSettings = icloudSettings;
  }


  public IP zohoSettings(@javax.annotation.Nullable String zohoSettings) {
    this.zohoSettings = zohoSettings;
    return this;
  }

  /**
   * Configuration for Zoho delivery settings in JSON format
   * @return zohoSettings
   */
  @javax.annotation.Nullable
  public String getZohoSettings() {
    return zohoSettings;
  }

  public void setZohoSettings(@javax.annotation.Nullable String zohoSettings) {
    this.zohoSettings = zohoSettings;
  }


  public IP qqSettings(@javax.annotation.Nullable String qqSettings) {
    this.qqSettings = qqSettings;
    return this;
  }

  /**
   * Configuration for QQ delivery settings in JSON format
   * @return qqSettings
   */
  @javax.annotation.Nullable
  public String getQqSettings() {
    return qqSettings;
  }

  public void setQqSettings(@javax.annotation.Nullable String qqSettings) {
    this.qqSettings = qqSettings;
  }


  public IP defaultSettings(@javax.annotation.Nullable String defaultSettings) {
    this.defaultSettings = defaultSettings;
    return this;
  }

  /**
   * Default delivery settings in JSON format
   * @return defaultSettings
   */
  @javax.annotation.Nullable
  public String getDefaultSettings() {
    return defaultSettings;
  }

  public void setDefaultSettings(@javax.annotation.Nullable String defaultSettings) {
    this.defaultSettings = defaultSettings;
  }


  public IP attSettings(@javax.annotation.Nullable String attSettings) {
    this.attSettings = attSettings;
    return this;
  }

  /**
   * Configuration for AT&amp;T delivery settings in JSON format
   * @return attSettings
   */
  @javax.annotation.Nullable
  public String getAttSettings() {
    return attSettings;
  }

  public void setAttSettings(@javax.annotation.Nullable String attSettings) {
    this.attSettings = attSettings;
  }


  public IP created(@javax.annotation.Nonnull Integer created) {
    this.created = created;
    return this;
  }

  /**
   * The timestamp (UNIX epoch) when the IP was created
   * @return created
   */
  @javax.annotation.Nonnull
  public Integer getCreated() {
    return created;
  }

  public void setCreated(@javax.annotation.Nonnull Integer created) {
    this.created = created;
  }


  public IP infraClassification(@javax.annotation.Nullable String infraClassification) {
    this.infraClassification = infraClassification;
    return this;
  }

  /**
   * Classification of the infrastructure
   * @return infraClassification
   */
  @javax.annotation.Nullable
  public String getInfraClassification() {
    return infraClassification;
  }

  public void setInfraClassification(@javax.annotation.Nullable String infraClassification) {
    this.infraClassification = infraClassification;
  }


  public IP infraMonitor(@javax.annotation.Nullable Boolean infraMonitor) {
    this.infraMonitor = infraMonitor;
    return this;
  }

  /**
   * Indicates whether infrastructure monitoring is enabled
   * @return infraMonitor
   */
  @javax.annotation.Nullable
  public Boolean getInfraMonitor() {
    return infraMonitor;
  }

  public void setInfraMonitor(@javax.annotation.Nullable Boolean infraMonitor) {
    this.infraMonitor = infraMonitor;
  }


  public IP state(@javax.annotation.Nullable Integer state) {
    this.state = state;
    return this;
  }

  /**
   * The state of the IP
   * @return state
   */
  @javax.annotation.Nullable
  public Integer getState() {
    return state;
  }

  public void setState(@javax.annotation.Nullable Integer state) {
    this.state = state;
  }


  public IP autoWarmupPlan(@javax.annotation.Nullable String autoWarmupPlan) {
    this.autoWarmupPlan = autoWarmupPlan;
    return this;
  }

  /**
   * The auto-warmup plan associated with the IP
   * @return autoWarmupPlan
   */
  @javax.annotation.Nullable
  public String getAutoWarmupPlan() {
    return autoWarmupPlan;
  }

  public void setAutoWarmupPlan(@javax.annotation.Nullable String autoWarmupPlan) {
    this.autoWarmupPlan = autoWarmupPlan;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IP IP = (IP) o;
    return Objects.equals(this.id, IP.id) &&
        Objects.equals(this.publicIP, IP.publicIP) &&
        Objects.equals(this.systemDomain, IP.systemDomain) &&
        Objects.equals(this.reverseDNSHostname, IP.reverseDNSHostname) &&
        Objects.equals(this.type, IP.type) &&
        Objects.equals(this.gmailSettings, IP.gmailSettings) &&
        Objects.equals(this.yahooSettings, IP.yahooSettings) &&
        Objects.equals(this.aolSettings, IP.aolSettings) &&
        Objects.equals(this.microsoftSettings, IP.microsoftSettings) &&
        Objects.equals(this.comcastSettings, IP.comcastSettings) &&
        Objects.equals(this.yandexSettings, IP.yandexSettings) &&
        Objects.equals(this.gmxSettings, IP.gmxSettings) &&
        Objects.equals(this.mailruSettings, IP.mailruSettings) &&
        Objects.equals(this.icloudSettings, IP.icloudSettings) &&
        Objects.equals(this.zohoSettings, IP.zohoSettings) &&
        Objects.equals(this.qqSettings, IP.qqSettings) &&
        Objects.equals(this.defaultSettings, IP.defaultSettings) &&
        Objects.equals(this.attSettings, IP.attSettings) &&
        Objects.equals(this.created, IP.created) &&
        Objects.equals(this.infraClassification, IP.infraClassification) &&
        Objects.equals(this.infraMonitor, IP.infraMonitor) &&
        Objects.equals(this.state, IP.state) &&
        Objects.equals(this.autoWarmupPlan, IP.autoWarmupPlan);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, publicIP, systemDomain, reverseDNSHostname, type, gmailSettings, yahooSettings, aolSettings, microsoftSettings, comcastSettings, yandexSettings, gmxSettings, mailruSettings, icloudSettings, zohoSettings, qqSettings, defaultSettings, attSettings, created, infraClassification, infraMonitor, state, autoWarmupPlan);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IP {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    publicIP: ").append(toIndentedString(publicIP)).append("\n");
    sb.append("    systemDomain: ").append(toIndentedString(systemDomain)).append("\n");
    sb.append("    reverseDNSHostname: ").append(toIndentedString(reverseDNSHostname)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    gmailSettings: ").append(toIndentedString(gmailSettings)).append("\n");
    sb.append("    yahooSettings: ").append(toIndentedString(yahooSettings)).append("\n");
    sb.append("    aolSettings: ").append(toIndentedString(aolSettings)).append("\n");
    sb.append("    microsoftSettings: ").append(toIndentedString(microsoftSettings)).append("\n");
    sb.append("    comcastSettings: ").append(toIndentedString(comcastSettings)).append("\n");
    sb.append("    yandexSettings: ").append(toIndentedString(yandexSettings)).append("\n");
    sb.append("    gmxSettings: ").append(toIndentedString(gmxSettings)).append("\n");
    sb.append("    mailruSettings: ").append(toIndentedString(mailruSettings)).append("\n");
    sb.append("    icloudSettings: ").append(toIndentedString(icloudSettings)).append("\n");
    sb.append("    zohoSettings: ").append(toIndentedString(zohoSettings)).append("\n");
    sb.append("    qqSettings: ").append(toIndentedString(qqSettings)).append("\n");
    sb.append("    defaultSettings: ").append(toIndentedString(defaultSettings)).append("\n");
    sb.append("    attSettings: ").append(toIndentedString(attSettings)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    infraClassification: ").append(toIndentedString(infraClassification)).append("\n");
    sb.append("    infraMonitor: ").append(toIndentedString(infraMonitor)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    autoWarmupPlan: ").append(toIndentedString(autoWarmupPlan)).append("\n");
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
    openapiFields.add("publicIP");
    openapiFields.add("systemDomain");
    openapiFields.add("reverseDNSHostname");
    openapiFields.add("type");
    openapiFields.add("gmailSettings");
    openapiFields.add("yahooSettings");
    openapiFields.add("aolSettings");
    openapiFields.add("microsoftSettings");
    openapiFields.add("comcastSettings");
    openapiFields.add("yandexSettings");
    openapiFields.add("gmxSettings");
    openapiFields.add("mailruSettings");
    openapiFields.add("icloudSettings");
    openapiFields.add("zohoSettings");
    openapiFields.add("qqSettings");
    openapiFields.add("defaultSettings");
    openapiFields.add("attSettings");
    openapiFields.add("created");
    openapiFields.add("infraClassification");
    openapiFields.add("infraMonitor");
    openapiFields.add("state");
    openapiFields.add("autoWarmupPlan");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("id");
    openapiRequiredFields.add("publicIP");
    openapiRequiredFields.add("created");
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to IP
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!IP.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in IP is not found in the empty JSON string", IP.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!IP.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `IP` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : IP.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("publicIP").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `publicIP` to be a primitive type in the JSON string but got `%s`", jsonObj.get("publicIP").toString()));
      }
      if ((jsonObj.get("reverseDNSHostname") != null && !jsonObj.get("reverseDNSHostname").isJsonNull()) && !jsonObj.get("reverseDNSHostname").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `reverseDNSHostname` to be a primitive type in the JSON string but got `%s`", jsonObj.get("reverseDNSHostname").toString()));
      }
      if ((jsonObj.get("gmailSettings") != null && !jsonObj.get("gmailSettings").isJsonNull()) && !jsonObj.get("gmailSettings").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `gmailSettings` to be a primitive type in the JSON string but got `%s`", jsonObj.get("gmailSettings").toString()));
      }
      if ((jsonObj.get("yahooSettings") != null && !jsonObj.get("yahooSettings").isJsonNull()) && !jsonObj.get("yahooSettings").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `yahooSettings` to be a primitive type in the JSON string but got `%s`", jsonObj.get("yahooSettings").toString()));
      }
      if ((jsonObj.get("aolSettings") != null && !jsonObj.get("aolSettings").isJsonNull()) && !jsonObj.get("aolSettings").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `aolSettings` to be a primitive type in the JSON string but got `%s`", jsonObj.get("aolSettings").toString()));
      }
      if ((jsonObj.get("microsoftSettings") != null && !jsonObj.get("microsoftSettings").isJsonNull()) && !jsonObj.get("microsoftSettings").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `microsoftSettings` to be a primitive type in the JSON string but got `%s`", jsonObj.get("microsoftSettings").toString()));
      }
      if ((jsonObj.get("comcastSettings") != null && !jsonObj.get("comcastSettings").isJsonNull()) && !jsonObj.get("comcastSettings").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `comcastSettings` to be a primitive type in the JSON string but got `%s`", jsonObj.get("comcastSettings").toString()));
      }
      if ((jsonObj.get("yandexSettings") != null && !jsonObj.get("yandexSettings").isJsonNull()) && !jsonObj.get("yandexSettings").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `yandexSettings` to be a primitive type in the JSON string but got `%s`", jsonObj.get("yandexSettings").toString()));
      }
      if ((jsonObj.get("gmxSettings") != null && !jsonObj.get("gmxSettings").isJsonNull()) && !jsonObj.get("gmxSettings").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `gmxSettings` to be a primitive type in the JSON string but got `%s`", jsonObj.get("gmxSettings").toString()));
      }
      if ((jsonObj.get("mailruSettings") != null && !jsonObj.get("mailruSettings").isJsonNull()) && !jsonObj.get("mailruSettings").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `mailruSettings` to be a primitive type in the JSON string but got `%s`", jsonObj.get("mailruSettings").toString()));
      }
      if ((jsonObj.get("icloudSettings") != null && !jsonObj.get("icloudSettings").isJsonNull()) && !jsonObj.get("icloudSettings").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `icloudSettings` to be a primitive type in the JSON string but got `%s`", jsonObj.get("icloudSettings").toString()));
      }
      if ((jsonObj.get("zohoSettings") != null && !jsonObj.get("zohoSettings").isJsonNull()) && !jsonObj.get("zohoSettings").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `zohoSettings` to be a primitive type in the JSON string but got `%s`", jsonObj.get("zohoSettings").toString()));
      }
      if ((jsonObj.get("qqSettings") != null && !jsonObj.get("qqSettings").isJsonNull()) && !jsonObj.get("qqSettings").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `qqSettings` to be a primitive type in the JSON string but got `%s`", jsonObj.get("qqSettings").toString()));
      }
      if ((jsonObj.get("defaultSettings") != null && !jsonObj.get("defaultSettings").isJsonNull()) && !jsonObj.get("defaultSettings").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `defaultSettings` to be a primitive type in the JSON string but got `%s`", jsonObj.get("defaultSettings").toString()));
      }
      if ((jsonObj.get("attSettings") != null && !jsonObj.get("attSettings").isJsonNull()) && !jsonObj.get("attSettings").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `attSettings` to be a primitive type in the JSON string but got `%s`", jsonObj.get("attSettings").toString()));
      }
      if ((jsonObj.get("infraClassification") != null && !jsonObj.get("infraClassification").isJsonNull()) && !jsonObj.get("infraClassification").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `infraClassification` to be a primitive type in the JSON string but got `%s`", jsonObj.get("infraClassification").toString()));
      }
      if ((jsonObj.get("autoWarmupPlan") != null && !jsonObj.get("autoWarmupPlan").isJsonNull()) && !jsonObj.get("autoWarmupPlan").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `autoWarmupPlan` to be a primitive type in the JSON string but got `%s`", jsonObj.get("autoWarmupPlan").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!IP.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'IP' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<IP> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(IP.class));

       return (TypeAdapter<T>) new TypeAdapter<IP>() {
           @Override
           public void write(JsonWriter out, IP value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public IP read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of IP given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of IP
   * @throws IOException if the JSON string is invalid with respect to IP
   */
  public static IP fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, IP.class);
  }

  /**
   * Convert an instance of IP to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

