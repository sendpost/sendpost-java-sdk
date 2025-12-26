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
import sendpost_java_sdk.IP;
import sendpost_java_sdk.ThirdPartySendingProvider;

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
 * IPPool
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-12-26T18:17:45.714434+05:30[Asia/Kolkata]", comments = "Generator version: 7.13.0")
public class IPPool {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  @javax.annotation.Nullable
  private Integer id;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  @javax.annotation.Nullable
  private String name;

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  @javax.annotation.Nullable
  private Integer type;

  public static final String SERIALIZED_NAME_CREATED = "created";
  @SerializedName(SERIALIZED_NAME_CREATED)
  @javax.annotation.Nullable
  private Long created;

  public static final String SERIALIZED_NAME_IPS = "ips";
  @SerializedName(SERIALIZED_NAME_IPS)
  @javax.annotation.Nullable
  private List<IP> ips = new ArrayList<>();

  public static final String SERIALIZED_NAME_THIRD_PARTY_SENDING_PROVIDERS = "thirdPartySendingProviders";
  @SerializedName(SERIALIZED_NAME_THIRD_PARTY_SENDING_PROVIDERS)
  @javax.annotation.Nullable
  private List<ThirdPartySendingProvider> thirdPartySendingProviders = new ArrayList<>();

  public static final String SERIALIZED_NAME_TO_ACCOUNT_I_P_POOLS = "toAccountIPPools";
  @SerializedName(SERIALIZED_NAME_TO_ACCOUNT_I_P_POOLS)
  @javax.annotation.Nullable
  private List<IPPool> toAccountIPPools = new ArrayList<>();

  public static final String SERIALIZED_NAME_ROUTING_STRATEGY = "routingStrategy";
  @SerializedName(SERIALIZED_NAME_ROUTING_STRATEGY)
  @javax.annotation.Nullable
  private Integer routingStrategy;

  public static final String SERIALIZED_NAME_ROUTING_META_DATA = "routingMetaData";
  @SerializedName(SERIALIZED_NAME_ROUTING_META_DATA)
  @javax.annotation.Nullable
  private String routingMetaData;

  public static final String SERIALIZED_NAME_AUTO_WARMUP_ENABLED = "autoWarmupEnabled";
  @SerializedName(SERIALIZED_NAME_AUTO_WARMUP_ENABLED)
  @javax.annotation.Nullable
  private Boolean autoWarmupEnabled;

  public static final String SERIALIZED_NAME_INFRA_MONITOR = "infraMonitor";
  @SerializedName(SERIALIZED_NAME_INFRA_MONITOR)
  @javax.annotation.Nullable
  private Boolean infraMonitor;

  public static final String SERIALIZED_NAME_IP_DOMAIN_WARMUP_STATUS = "ipDomainWarmupStatus";
  @SerializedName(SERIALIZED_NAME_IP_DOMAIN_WARMUP_STATUS)
  @javax.annotation.Nullable
  private String ipDomainWarmupStatus;

  public static final String SERIALIZED_NAME_SHOULD_OVERFLOW = "shouldOverflow";
  @SerializedName(SERIALIZED_NAME_SHOULD_OVERFLOW)
  @javax.annotation.Nullable
  private Boolean shouldOverflow;

  public static final String SERIALIZED_NAME_OVERFLOW_POOL_NAME = "overflowPoolName";
  @SerializedName(SERIALIZED_NAME_OVERFLOW_POOL_NAME)
  @javax.annotation.Nullable
  private String overflowPoolName;

  public static final String SERIALIZED_NAME_WARMUP_INTERVAL = "warmupInterval";
  @SerializedName(SERIALIZED_NAME_WARMUP_INTERVAL)
  @javax.annotation.Nullable
  private Integer warmupInterval;

  public IPPool() {
  }

  public IPPool id(@javax.annotation.Nullable Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @javax.annotation.Nullable
  public Integer getId() {
    return id;
  }

  public void setId(@javax.annotation.Nullable Integer id) {
    this.id = id;
  }


  public IPPool name(@javax.annotation.Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @javax.annotation.Nullable
  public String getName() {
    return name;
  }

  public void setName(@javax.annotation.Nullable String name) {
    this.name = name;
  }


  public IPPool type(@javax.annotation.Nullable Integer type) {
    this.type = type;
    return this;
  }

  /**
   * Type of IP pool (0 &#x3D; Shared, 1 &#x3D; Dedicated)
   * @return type
   */
  @javax.annotation.Nullable
  public Integer getType() {
    return type;
  }

  public void setType(@javax.annotation.Nullable Integer type) {
    this.type = type;
  }


  public IPPool created(@javax.annotation.Nullable Long created) {
    this.created = created;
    return this;
  }

  /**
   * Get created
   * @return created
   */
  @javax.annotation.Nullable
  public Long getCreated() {
    return created;
  }

  public void setCreated(@javax.annotation.Nullable Long created) {
    this.created = created;
  }


  public IPPool ips(@javax.annotation.Nullable List<IP> ips) {
    this.ips = ips;
    return this;
  }

  public IPPool addIpsItem(IP ipsItem) {
    if (this.ips == null) {
      this.ips = new ArrayList<>();
    }
    this.ips.add(ipsItem);
    return this;
  }

  /**
   * Get ips
   * @return ips
   */
  @javax.annotation.Nullable
  public List<IP> getIps() {
    return ips;
  }

  public void setIps(@javax.annotation.Nullable List<IP> ips) {
    this.ips = ips;
  }


  public IPPool thirdPartySendingProviders(@javax.annotation.Nullable List<ThirdPartySendingProvider> thirdPartySendingProviders) {
    this.thirdPartySendingProviders = thirdPartySendingProviders;
    return this;
  }

  public IPPool addThirdPartySendingProvidersItem(ThirdPartySendingProvider thirdPartySendingProvidersItem) {
    if (this.thirdPartySendingProviders == null) {
      this.thirdPartySendingProviders = new ArrayList<>();
    }
    this.thirdPartySendingProviders.add(thirdPartySendingProvidersItem);
    return this;
  }

  /**
   * Get thirdPartySendingProviders
   * @return thirdPartySendingProviders
   */
  @javax.annotation.Nullable
  public List<ThirdPartySendingProvider> getThirdPartySendingProviders() {
    return thirdPartySendingProviders;
  }

  public void setThirdPartySendingProviders(@javax.annotation.Nullable List<ThirdPartySendingProvider> thirdPartySendingProviders) {
    this.thirdPartySendingProviders = thirdPartySendingProviders;
  }


  public IPPool toAccountIPPools(@javax.annotation.Nullable List<IPPool> toAccountIPPools) {
    this.toAccountIPPools = toAccountIPPools;
    return this;
  }

  public IPPool addToAccountIPPoolsItem(IPPool toAccountIPPoolsItem) {
    if (this.toAccountIPPools == null) {
      this.toAccountIPPools = new ArrayList<>();
    }
    this.toAccountIPPools.add(toAccountIPPoolsItem);
    return this;
  }

  /**
   * Related account IP pools
   * @return toAccountIPPools
   */
  @javax.annotation.Nullable
  public List<IPPool> getToAccountIPPools() {
    return toAccountIPPools;
  }

  public void setToAccountIPPools(@javax.annotation.Nullable List<IPPool> toAccountIPPools) {
    this.toAccountIPPools = toAccountIPPools;
  }


  public IPPool routingStrategy(@javax.annotation.Nullable Integer routingStrategy) {
    this.routingStrategy = routingStrategy;
    return this;
  }

  /**
   * Get routingStrategy
   * @return routingStrategy
   */
  @javax.annotation.Nullable
  public Integer getRoutingStrategy() {
    return routingStrategy;
  }

  public void setRoutingStrategy(@javax.annotation.Nullable Integer routingStrategy) {
    this.routingStrategy = routingStrategy;
  }


  public IPPool routingMetaData(@javax.annotation.Nullable String routingMetaData) {
    this.routingMetaData = routingMetaData;
    return this;
  }

  /**
   * Get routingMetaData
   * @return routingMetaData
   */
  @javax.annotation.Nullable
  public String getRoutingMetaData() {
    return routingMetaData;
  }

  public void setRoutingMetaData(@javax.annotation.Nullable String routingMetaData) {
    this.routingMetaData = routingMetaData;
  }


  public IPPool autoWarmupEnabled(@javax.annotation.Nullable Boolean autoWarmupEnabled) {
    this.autoWarmupEnabled = autoWarmupEnabled;
    return this;
  }

  /**
   * Get autoWarmupEnabled
   * @return autoWarmupEnabled
   */
  @javax.annotation.Nullable
  public Boolean getAutoWarmupEnabled() {
    return autoWarmupEnabled;
  }

  public void setAutoWarmupEnabled(@javax.annotation.Nullable Boolean autoWarmupEnabled) {
    this.autoWarmupEnabled = autoWarmupEnabled;
  }


  public IPPool infraMonitor(@javax.annotation.Nullable Boolean infraMonitor) {
    this.infraMonitor = infraMonitor;
    return this;
  }

  /**
   * Get infraMonitor
   * @return infraMonitor
   */
  @javax.annotation.Nullable
  public Boolean getInfraMonitor() {
    return infraMonitor;
  }

  public void setInfraMonitor(@javax.annotation.Nullable Boolean infraMonitor) {
    this.infraMonitor = infraMonitor;
  }


  public IPPool ipDomainWarmupStatus(@javax.annotation.Nullable String ipDomainWarmupStatus) {
    this.ipDomainWarmupStatus = ipDomainWarmupStatus;
    return this;
  }

  /**
   * Get ipDomainWarmupStatus
   * @return ipDomainWarmupStatus
   */
  @javax.annotation.Nullable
  public String getIpDomainWarmupStatus() {
    return ipDomainWarmupStatus;
  }

  public void setIpDomainWarmupStatus(@javax.annotation.Nullable String ipDomainWarmupStatus) {
    this.ipDomainWarmupStatus = ipDomainWarmupStatus;
  }


  public IPPool shouldOverflow(@javax.annotation.Nullable Boolean shouldOverflow) {
    this.shouldOverflow = shouldOverflow;
    return this;
  }

  /**
   * Indicates whether the IP should overflow, once email capacity of the IP Pool has been reached, should we send remaining emails over shared IP or not
   * @return shouldOverflow
   */
  @javax.annotation.Nullable
  public Boolean getShouldOverflow() {
    return shouldOverflow;
  }

  public void setShouldOverflow(@javax.annotation.Nullable Boolean shouldOverflow) {
    this.shouldOverflow = shouldOverflow;
  }


  public IPPool overflowPoolName(@javax.annotation.Nullable String overflowPoolName) {
    this.overflowPoolName = overflowPoolName;
    return this;
  }

  /**
   * The name of the overflow pool
   * @return overflowPoolName
   */
  @javax.annotation.Nullable
  public String getOverflowPoolName() {
    return overflowPoolName;
  }

  public void setOverflowPoolName(@javax.annotation.Nullable String overflowPoolName) {
    this.overflowPoolName = overflowPoolName;
  }


  public IPPool warmupInterval(@javax.annotation.Nullable Integer warmupInterval) {
    this.warmupInterval = warmupInterval;
    return this;
  }

  /**
   * The interval for the warmup
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
    IPPool ipPool = (IPPool) o;
    return Objects.equals(this.id, ipPool.id) &&
        Objects.equals(this.name, ipPool.name) &&
        Objects.equals(this.type, ipPool.type) &&
        Objects.equals(this.created, ipPool.created) &&
        Objects.equals(this.ips, ipPool.ips) &&
        Objects.equals(this.thirdPartySendingProviders, ipPool.thirdPartySendingProviders) &&
        Objects.equals(this.toAccountIPPools, ipPool.toAccountIPPools) &&
        Objects.equals(this.routingStrategy, ipPool.routingStrategy) &&
        Objects.equals(this.routingMetaData, ipPool.routingMetaData) &&
        Objects.equals(this.autoWarmupEnabled, ipPool.autoWarmupEnabled) &&
        Objects.equals(this.infraMonitor, ipPool.infraMonitor) &&
        Objects.equals(this.ipDomainWarmupStatus, ipPool.ipDomainWarmupStatus) &&
        Objects.equals(this.shouldOverflow, ipPool.shouldOverflow) &&
        Objects.equals(this.overflowPoolName, ipPool.overflowPoolName) &&
        Objects.equals(this.warmupInterval, ipPool.warmupInterval);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, type, created, ips, thirdPartySendingProviders, toAccountIPPools, routingStrategy, routingMetaData, autoWarmupEnabled, infraMonitor, ipDomainWarmupStatus, shouldOverflow, overflowPoolName, warmupInterval);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IPPool {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    ips: ").append(toIndentedString(ips)).append("\n");
    sb.append("    thirdPartySendingProviders: ").append(toIndentedString(thirdPartySendingProviders)).append("\n");
    sb.append("    toAccountIPPools: ").append(toIndentedString(toAccountIPPools)).append("\n");
    sb.append("    routingStrategy: ").append(toIndentedString(routingStrategy)).append("\n");
    sb.append("    routingMetaData: ").append(toIndentedString(routingMetaData)).append("\n");
    sb.append("    autoWarmupEnabled: ").append(toIndentedString(autoWarmupEnabled)).append("\n");
    sb.append("    infraMonitor: ").append(toIndentedString(infraMonitor)).append("\n");
    sb.append("    ipDomainWarmupStatus: ").append(toIndentedString(ipDomainWarmupStatus)).append("\n");
    sb.append("    shouldOverflow: ").append(toIndentedString(shouldOverflow)).append("\n");
    sb.append("    overflowPoolName: ").append(toIndentedString(overflowPoolName)).append("\n");
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
    openapiFields.add("type");
    openapiFields.add("created");
    openapiFields.add("ips");
    openapiFields.add("thirdPartySendingProviders");
    openapiFields.add("toAccountIPPools");
    openapiFields.add("routingStrategy");
    openapiFields.add("routingMetaData");
    openapiFields.add("autoWarmupEnabled");
    openapiFields.add("infraMonitor");
    openapiFields.add("ipDomainWarmupStatus");
    openapiFields.add("shouldOverflow");
    openapiFields.add("overflowPoolName");
    openapiFields.add("warmupInterval");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to IPPool
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!IPPool.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in IPPool is not found in the empty JSON string", IPPool.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!IPPool.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `IPPool` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("name") != null && !jsonObj.get("name").isJsonNull()) && !jsonObj.get("name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("name").toString()));
      }
      if (jsonObj.get("ips") != null && !jsonObj.get("ips").isJsonNull()) {
        JsonArray jsonArrayips = jsonObj.getAsJsonArray("ips");
        if (jsonArrayips != null) {
          // ensure the json data is an array
          if (!jsonObj.get("ips").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `ips` to be an array in the JSON string but got `%s`", jsonObj.get("ips").toString()));
          }

          // validate the optional field `ips` (array)
          for (int i = 0; i < jsonArrayips.size(); i++) {
            IP.validateJsonElement(jsonArrayips.get(i));
          };
        }
      }
      if (jsonObj.get("thirdPartySendingProviders") != null && !jsonObj.get("thirdPartySendingProviders").isJsonNull()) {
        JsonArray jsonArraythirdPartySendingProviders = jsonObj.getAsJsonArray("thirdPartySendingProviders");
        if (jsonArraythirdPartySendingProviders != null) {
          // ensure the json data is an array
          if (!jsonObj.get("thirdPartySendingProviders").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `thirdPartySendingProviders` to be an array in the JSON string but got `%s`", jsonObj.get("thirdPartySendingProviders").toString()));
          }

          // validate the optional field `thirdPartySendingProviders` (array)
          for (int i = 0; i < jsonArraythirdPartySendingProviders.size(); i++) {
            ThirdPartySendingProvider.validateJsonElement(jsonArraythirdPartySendingProviders.get(i));
          };
        }
      }
      if (jsonObj.get("toAccountIPPools") != null && !jsonObj.get("toAccountIPPools").isJsonNull()) {
        JsonArray jsonArraytoAccountIPPools = jsonObj.getAsJsonArray("toAccountIPPools");
        if (jsonArraytoAccountIPPools != null) {
          // ensure the json data is an array
          if (!jsonObj.get("toAccountIPPools").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `toAccountIPPools` to be an array in the JSON string but got `%s`", jsonObj.get("toAccountIPPools").toString()));
          }

          // validate the optional field `toAccountIPPools` (array)
          for (int i = 0; i < jsonArraytoAccountIPPools.size(); i++) {
            IPPool.validateJsonElement(jsonArraytoAccountIPPools.get(i));
          };
        }
      }
      if ((jsonObj.get("routingMetaData") != null && !jsonObj.get("routingMetaData").isJsonNull()) && !jsonObj.get("routingMetaData").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `routingMetaData` to be a primitive type in the JSON string but got `%s`", jsonObj.get("routingMetaData").toString()));
      }
      if ((jsonObj.get("ipDomainWarmupStatus") != null && !jsonObj.get("ipDomainWarmupStatus").isJsonNull()) && !jsonObj.get("ipDomainWarmupStatus").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `ipDomainWarmupStatus` to be a primitive type in the JSON string but got `%s`", jsonObj.get("ipDomainWarmupStatus").toString()));
      }
      if ((jsonObj.get("overflowPoolName") != null && !jsonObj.get("overflowPoolName").isJsonNull()) && !jsonObj.get("overflowPoolName").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `overflowPoolName` to be a primitive type in the JSON string but got `%s`", jsonObj.get("overflowPoolName").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!IPPool.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'IPPool' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<IPPool> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(IPPool.class));

       return (TypeAdapter<T>) new TypeAdapter<IPPool>() {
           @Override
           public void write(JsonWriter out, IPPool value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public IPPool read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of IPPool given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of IPPool
   * @throws IOException if the JSON string is invalid with respect to IPPool
   */
  public static IPPool fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, IPPool.class);
  }

  /**
   * Convert an instance of IPPool to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

