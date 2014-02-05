twitter-api
===========

Simple Wrapper for Twitter API (1.1)
=============

Introduction
------------

<b>Simple Wrapper for Twitter API (1.1)</b> is a Java Web Application based on Spring MVC Framework 3.1.
The core of the app, it's the exposure of a REST Service that responds in JSON and XML format.
It uses SpringSocial component to consume the Twitter API.
Keep in mind that you have to use a real Twitter account.

This API is protected with an access token, wich is a set of enabled tokens stored in the Database.

Pre-requisites
--------------

<pre>
  <code>
      * Apache Maven >=2.x
      * Apache Tomcat 7 (optional)
      * Java JRE/JDK 7.x
      * MySQL Server 5.x
      * git-core (for clonning the repository)
      * A Twitter Application with Read & Write Permissions
   </code>
</pre> 

Deploy
------
Before deploying the Application, you have to set up a few things.
Assuming that you have the access tokens of your Twitter Application, edit this file:
<code>src/main/resources/application.properties</code> and complete the values of the properties with your Twitter App.

After that, edit <code>src/main/resources/jdbc/jdbc.properties</code> and complete the MySQL Access. 

Now, open a Terminal, in the path of the project, and run <code>mvn jetty:run</code>
After Maven downloads dependencies and starts Jetty WebServer, goto to [twitter-api-address]

If you don't want to use Jetty, you can deploy the Application in another Web Container, such as Apache Tomcat 7 (it is tested with).

Usage
------

The API comes with a frontend Demo to obtain the API for a @username, and generates a token for you.

Besides the demo, the complete API is as follows:
<code>
  <pre>
    Obtain last 20 tweets of an user and it's following 
    (with the possibility to user a keyword filter), in JSON/XML format
  </pre>
  * <b>GET</b> /api/username/tweets.json?token=TOKEN_VALUE&search=KEYWORD (optional, one-only value)
  * <b>GET</b> /api/username/tweets.xml?token=TOKEN_VALUE&search=KEYWORD (optional, one-only value)

  <pre>
    Obtain the Followers that an user have
  </pre>
  * <b>GET</b> /api/username/followers.json?token=TOKEN_VALUE
  * <b>GET</b> /api/username/followers.xml?token=TOKEN_VALUE
  
  <pre>
    Obtain the Following users that an user have
  </pre>
  * <b>GET</b> /api/username/following.json?token=TOKEN_VALUE
  * <b>GET</b> /api/username/following.xml?token=TOKEN_VALUE
  
  <pre>
    To start following the given username with the account configured in the API
  </pre>
  * <b>GET</b> /api/username/follow.json?token=TOKEN_VALUE
  * <b>GET</b> /api/username/follow.xml?token=TOKEN_VALUE
  
  <pre>
    To stop following the given username with the account configured in the API
  </pre>
  * <b>GET</b> /api/username/unfollow.json?token=TOKEN_VALUE
  * <b>GET</b> /api/username/unfollow.xml?token=TOKEN_VALUE
</code>





[twitter-api-address]: http://localhost:8080/twitter-api