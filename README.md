# Sample JWT Bearer Client

This sample client performs a POST with an JWT that it has signed.

**Note** This sample client has been tested with OpenAM 13.0.0.

There are two basic modes of operation pubkey (default) and jwks.

For -mode pubkey 

Before trying this client, configure a top-level realm OAuth 2.0 client profile
with client_id: `jwt-bearer-client`, and Client JWT Bearer Public Key Certificate:

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

Also add a default scope to the client, such as `openid`.

Then to use this client, pass it the OpenAM Server URL
such as `http://openam.example.com:8080/openam`.


## Using the Sample Client

After registering the client with OpenAM, build the client and try it out.

    $ mvn package

    $ java -jar target/jwt-bearer-client-1.0-SNAPSHOT-jar-with-dependencies.jar
    Usage: OpenAM-serverUrl [-mode pubkey|jwks_uri] [-authn true|false] [-clientname clientname] [-user user] [-password password] [-keystore path_to_keystore] [-keystorepass keystorepass] [-alias keystore alias] [-keypass keypassword] [-kid kid_fromjwk] [-curlout true|false]

    Before trying this client, configure a top-level realm OAuth 2.0 client profile
    with client_id: jwt-bearer-client, and Client JWT Bearer Public Key Certificate:

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

Depending on the scope used, you may require authentication using the -authn true flag

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
    {"scope":"openid","expires_in":59,"token_type":"Bearer",
     "access_token":"90392e8b-086c-43ee-97e8-2c6f56d33fa9"}

To add the authentication (uses default demo:changeit)

     $ java -jar target/jwt-bearer-client-1.0-SNAPSHOT-jar-with-dependencies.jar \
     http://openam.example.com:8080/openam -authn true

## For jwks mode

For jwks_uri mode, you can use the keystore from OpenAM confighome/openam/keystore.jks. If you are replacing the current keystore then copy it into the src/resources folder or a sub-directory.
Also remember that this will take a different alias (default test). Configure the url $serverUrl/oauth2/connect/jwk_uri as the jwks_uri and also specify that you are using the jwks uri and RS256 in the oauth2 provider and client. openid should be in the scope. the private key should be used.
authentication is always used in this mode, again using -user demo -password changeit which can be changed on the command line

example for jwks: http://openam.example.com:18080/openam -mode jwks_uri  -keystore /jwks/keystore.jks -keystorepass changeit -alias test


POSTing the following as a JWT bearer token including kid value:
eyAidHlwIjogIkpXVCIsICJraWQiOiAiU3lsTEM2Tmp0MUtHUWt0RDlNdCswemNlUVNVPSIsICJhbGciOiAiUlMyNTYiIH0.eyAic3ViIjogImp3dC1iZWFyZXItY2xpZW50IiwgImF1ZCI6IFsgImh0dHA6Ly9vcGVuYW0uZXhhbXBsZS5jb206MTgwODAvb3BlbmFtL29hdXRoMi9hY2Nlc3NfdG9rZW4iIF0sICJpc3MiOiAiand0LWJlYXJlci1jbGllbnQiLCAiZXhwIjogMTQ2MDQ2MTA0MCB9.VRsTIEm4DBm_t2qkM3QcIMyTXDRAnvqHA0mxFVBPfqF8JPR_ns587dkT3DupInFTH-N1o4FdHZ6SAZwFQGGIJhSagYuVkSDAqpS1X1G42SIf3zoX139prcHtrpmH80qva6ujyDsJyNHF-GjkHpHu1qQirk3PTTKhY4kIQF_tfSk

Authenticating as user:demo;endpoint:http://openam.example.com:18080/openam/json/authenticate
Response code: 200
Response code: 200
{"scope":"email mail openid","expires_in":3599,"token_type":"Bearer","id_token":"eyAidHlwIjogIkpXVCIsICJhbGciOiAiUlMyNTYiLCAia2lkIjogIlN5bExDNk5qdDFLR1FrdEQ5TXQrMHpjZVFTVT0iIH0.eyAiYXV0aF90aW1lIjogMTQ2MDQ2MDc0MCwgInRva2VuTmFtZSI6ICJpZF90b2tlbiIsICJleHAiOiAxNDYwNDY0MzQwLCAic3ViIjogImRlbW8iLCAiYXpwIjogImp3dC1iZWFyZXItY2xpZW50IiwgInRva2VuVHlwZSI6ICJKV1RUb2tlbiIsICJyZWFsbSI6ICIvIiwgImlzcyI6ICJodHRwOi8vb3BlbmFtLmV4YW1wbGUuY29tOjE4MDgwL29wZW5hbS9vYXV0aDIiLCAiYXVkIjogWyAiand0LWJlYXJlci1jbGllbnQiIF0sICJvcmcuZm9yZ2Vyb2NrLm9wZW5pZGNvbm5lY3Qub3BzIjogIjQ1ZWI2NjVmLWU4ZmQtNGYzOC1hNmM2LWUxZTRlOTBiZGQxYyIsICJpYXQiOiAxNDYwNDYwNzQwIH0.I-dXcfwwQ-FVO5s06wp29ToVwW_fv6yJ9TjR4k2nMt-T2i5kz3QModv6SzbxZAL7zBMKlsi2nNGxJLOKxmzfubwWeGUxgGxSFrZorHx7DWRC3lrKqXXAIoOwLM2mfL6DTfNPRQavxbg8f5QOA22akaN4z7MxQHU22UNZ42uDP5c","access_token":"4dcd11a6-568b-4594-851e-e215084d3324"}


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

Copyright 2016 ForgeRock AS.
