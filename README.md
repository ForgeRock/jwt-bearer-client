# Sample JWT Bearer Client

This sample client performs a POST with an JWT that it has signed.

Before trying this client, configure a top-level realm OAuth 2.0 client profile
with client_id: `jwt-bearer-client`, client_secret: `password`,
and Client JWT Bearer Public Key:

    -----BEGIN PUBLIC KEY-----
    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgPhln8MgYQGvhALa7PQZ
    gU4JSUA9qfFfbVpgdK8QuxbIxrsMmUP6aeNoXxw9FEySFhkVcMmj8BVyPJuXJPee
    2ov7YmzcPtOZLoFKOTFlawkfKEkhgShTDCWhzSSLOBKbXkzAExxueSxBU3lVaI57
    jLgkxuLfS3qTfyUzrYoq1pI8/qO+dbUtOYv26KotbPngAzqFTc2UIjcnpF+HzBoE
    g8ASOYdk2q46Xa4aBa6bO5tgMUluiYtJCo7nxG4pVpJUzANuYQ20xhZcgpAvbFO3
    lcBvARASAn/OyTCkDZpC3UcTp17dTKY6E42FGK7iuTcAgScRVvGu/LgiT7BeDstl
    WwIDAQAB
    -----END PUBLIC KEY-----

Then to use this client, pass it the OpenAM Server URL
such as `http://openam.example.com:8080/openam`.


## Using the Sample Client

After registering the client with OpenAM, build the client and try it out.

    $ mvn package
    
    $ java -jar target/jwt-bearer-client-1.0-SNAPSHOT-jar-with-dependencies.jar 
    Usage: OpenAM-serverUrl
    
    Before trying this client, configure a top-level realm OAuth 2.0 client profile
    with client_id: jwt-bearer-client, client_secret: password,
    and Client JWT Bearer Public Key:
    
    -----BEGIN PUBLIC KEY-----
    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgPhln8MgYQGvhALa7PQZ
    gU4JSUA9qfFfbVpgdK8QuxbIxrsMmUP6aeNoXxw9FEySFhkVcMmj8BVyPJuXJPee
    2ov7YmzcPtOZLoFKOTFlawkfKEkhgShTDCWhzSSLOBKbXkzAExxueSxBU3lVaI57
    jLgkxuLfS3qTfyUzrYoq1pI8/qO+dbUtOYv26KotbPngAzqFTc2UIjcnpF+HzBoE
    g8ASOYdk2q46Xa4aBa6bO5tgMUluiYtJCo7nxG4pVpJUzANuYQ20xhZcgpAvbFO3
    lcBvARASAn/OyTCkDZpC3UcTp17dTKY6E42FGK7iuTcAgScRVvGu/LgiT7BeDstl
    WwIDAQAB
    -----END PUBLIC KEY-----

Then to use this client, pass it the OpenAM Server URL
such as `http://openam.example.com:8080/openam`.

    $ java -jar target/jwt-bearer-client-1.0-SNAPSHOT-jar-with-dependencies.jar \
     http://openam.example.com:8080/openam
      
    POSTing the following as a JWT bearer token:
    eyAidHlwIjogIkpXVCIsICJhbGciOiAiUlMyNTYiIH0.
    eyAic3ViIjogImp3dC1iZWFyZXItY2xpZW50IiwgImF1ZCI6IFsgImh0dHA6Ly9vcGVuYW0u
    ZXhhbXBsZS5jb206ODA4OC9vcGVuYW0iIF0sICJuYmYiOiAxNDE0NzQ0NTMzLCAiaXNzIjog
    Imp3dC1iZWFyZXItY2xpZW50IiwgImV4cCI6IDE0MTQ3NDUxMzMgfQ.ZgK09uLxwloziYGTT
    BdVPb5-1sFxVaGkXLp1ptk1Bw1UFIfo9cD2IryHi0kXFyklnOlUcWLS_EzX0iM0FFJ7w-_LL
    P038jZvpW3Bs7IuPqmPh2qVxAm698LCiZyNYBb6icfzYUi-cOkX23_EF62URL61Fe1Q7Suy0
    1bRTfdhCZ34JUGX9MaGBMKKDVGu9Q3gZ7dgzqVczkPnYIITx97uZ0QqfTS1XSwAo6BptwSV1
    03Hl7GhTYTUxcF7v9BYxKekBRuFE6guTu17UyOOZjSZJbRFu_kuloXEYF8MDbgXhdlAfWCZq
    bg2SJ3KRhBUKPE26Et1-znPDAuaKt9X8KIF3A
    
    Response code: 200
    {"expires_in":600,"token_type":"Bearer","access_token":"451f1703-9ee1-42
    09-bd45-9ee19b26d3b8"}


## Commands Used to Create the Key Pair & Get the Public Key

The Java `keytool` command created the key pair for this sample client.

    $ cd src/main/resources

    $ keytool \
     -genkeypair \
     -keysize 2048 \
     -alias self-signed \
     -keyalg rsa \
     -dname "CN=jwt-bearer-client,O=openam.example.com" \
     -keystore keystore.jks \
     -keypass changeit \
     -storepass changeit \
     -validity 3650 \
     -v
    Generating 2,048 bit RSA key pair and self-signed certificate (SHA256withRSA)
     with a validity of 3,650 days
        for: CN=jwt-bearer-client, O=openam.example.com
    [Storing keystore.jks]

    $ keytool \
     -list \
     -alias self-signed \
     -rfc \
     -keystore keystore.jks \
     -keypass changeit \
     -storepass changeit
    Alias name: self-signed
    Creation date: Oct 27, 2014
    Entry type: PrivateKeyEntry
    Certificate chain length: 1
    Certificate[1]:
    -----BEGIN CERTIFICATE-----
    MIIDETCCAfmgAwIBAgIEU8SXLjANBgkqhkiG9w0BAQsFADA5MRswGQYDVQQKExJvcGVuYW0uZXhh
    bXBsZS5jb20xGjAYBgNVBAMTEWp3dC1iZWFyZXItY2xpZW50MB4XDTE0MTAyNzExNTY1NloXDTI0
    MTAyNDExNTY1NlowOTEbMBkGA1UEChMSb3BlbmFtLmV4YW1wbGUuY29tMRowGAYDVQQDExFqd3Qt
    YmVhcmVyLWNsaWVudDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAID4ZZ/DIGEBr4QC
    2uz0GYFOCUlAPanxX21aYHSvELsWyMa7DJlD+mnjaF8cPRRMkhYZFXDJo/AVcjyblyT3ntqL+2Js
    3D7TmS6BSjkxZWsJHyhJIYEoUwwloc0kizgSm15MwBMcbnksQVN5VWiOe4y4JMbi30t6k38lM62K
    KtaSPP6jvnW1LTmL9uiqLWz54AM6hU3NlCI3J6Rfh8waBIPAEjmHZNquOl2uGgWumzubYDFJbomL
    SQqO58RuKVaSVMwDbmENtMYWXIKQL2xTt5XAbwEQEgJ/zskwpA2aQt1HE6de3UymOhONhRiu4rk3
    AIEnEVbxrvy4Ik+wXg7LZVsCAwEAAaMhMB8wHQYDVR0OBBYEFIuI7ejuZTg5tJsh1XyRopGOMBcs
    MA0GCSqGSIb3DQEBCwUAA4IBAQBM/+/tYYVIS6LvPl3mfE8V7x+VPXqj/uK6UecAbfmRTrPk1ph+
    jjI6nmLX9ncomYALWL/JFiSXcVsZt3/412fOqjakFVS0PmK1vEPxDlav1drnVA33icy1wORRRu5/
    qA6mwDYPAZSbm5cDVvCR7Lt6VqJ+D0V8GABFxUw9IaX6ajTqkWhldY77usvNeTD0Xc4R7OqSBrnA
    SNCaUlJogWyzhbFlmE9Ne28j4RVpbz/EZn0oc/cHTJ6Lryzsivf4uDO1m3M3kM/MUyXc1Zv3rqBj
    TeGSgcqEAd6XlGXY1+M/yIeouUTi0F1bk1rNlqJvd57Xb4CEq17tVbGBm0hkECM8
    -----END CERTIFICATE-----

The `openssl x509` command helped to get the public key from the certificate.

    $ keytool \
     -exportcert \
     -keystore keystore.jks \
     -storepass changeit \
     -alias self-signed \
     -rfc \
     -file cert.pem
    Certificate stored in file <cert.pem>
    
    $ openssl x509 -pubkey -noout -in cert.pem > public.pem


* * * * *

The contents of this file are subject to the terms of the Common Development and
Distribution License (the License). You may not use this file except in compliance with the
License.

You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
specific language governing permission and limitations under the License.

When distributing Covered Software, include this CDDL Header Notice in each file and include
the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
Header, with the fields enclosed by brackets [] replaced by your own identifying
information: "Portions copyright [year] [name of copyright owner]".

Copyright 2014 ForgeRock AS.
