/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2014 ForgeRock AS.
 */

package org.forgerock.openam.doc.jwt.bearer;

import org.forgerock.json.jose.builders.JwtBuilderFactory;
import org.forgerock.json.jose.jws.JwsAlgorithm;
import org.forgerock.json.jose.jws.SigningManager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Collections;
import java.util.Date;

/**
 * Use a JWT as a bearer token to get an OAuth 2.0 access token.
 */
public final class Main {

    private static String clientId = "jwt-bearer-client";
    private static String tokenEndpoint = null;

    /**
     * Use a JWT as a bearer token to get an OAuth 2.0 access token.
     *
     * @param args
     *            Command line arguments: OpenAM-serverUrl
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println(getUsage());
            System.exit(-1);
        }

        String serverUrl = args[0];
        tokenEndpoint = serverUrl.replaceAll("/$", "") + "/oauth2/access_token";

        String jws = getJws(tokenEndpoint);
        System.out.println("\nPOSTing the following as a JWT bearer token:\n"
                + jws);
        System.out.println();

        try {
            post(jws);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static String getUsage() {
        String certificate = "-----BEGIN CERTIFICATE-----\n"
                + "MIIDETCCAfmgAwIBAgIEU8SXLjANBgkqhkiG9w0BAQsFADA5MRswGQYDVQQKExJvcGVuYW0uZXhh\n"
                + "bXBsZS5jb20xGjAYBgNVBAMTEWp3dC1iZWFyZXItY2xpZW50MB4XDTE0MTAyNzExNTY1NloXDTI0\n"
                + "MTAyNDExNTY1NlowOTEbMBkGA1UEChMSb3BlbmFtLmV4YW1wbGUuY29tMRowGAYDVQQDExFqd3Qt\n"
                + "YmVhcmVyLWNsaWVudDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAID4ZZ/DIGEBr4QC\n"
                + "2uz0GYFOCUlAPanxX21aYHSvELsWyMa7DJlD+mnjaF8cPRRMkhYZFXDJo/AVcjyblyT3ntqL+2Js\n"
                + "3D7TmS6BSjkxZWsJHyhJIYEoUwwloc0kizgSm15MwBMcbnksQVN5VWiOe4y4JMbi30t6k38lM62K\n"
                + "KtaSPP6jvnW1LTmL9uiqLWz54AM6hU3NlCI3J6Rfh8waBIPAEjmHZNquOl2uGgWumzubYDFJbomL\n"
                + "SQqO58RuKVaSVMwDbmENtMYWXIKQL2xTt5XAbwEQEgJ/zskwpA2aQt1HE6de3UymOhONhRiu4rk3\n"
                + "AIEnEVbxrvy4Ik+wXg7LZVsCAwEAAaMhMB8wHQYDVR0OBBYEFIuI7ejuZTg5tJsh1XyRopGOMBcs\n"
                + "MA0GCSqGSIb3DQEBCwUAA4IBAQBM/+/tYYVIS6LvPl3mfE8V7x+VPXqj/uK6UecAbfmRTrPk1ph+\n"
                + "jjI6nmLX9ncomYALWL/JFiSXcVsZt3/412fOqjakFVS0PmK1vEPxDlav1drnVA33icy1wORRRu5/\n"
                + "qA6mwDYPAZSbm5cDVvCR7Lt6VqJ+D0V8GABFxUw9IaX6ajTqkWhldY77usvNeTD0Xc4R7OqSBrnA\n"
                + "SNCaUlJogWyzhbFlmE9Ne28j4RVpbz/EZn0oc/cHTJ6Lryzsivf4uDO1m3M3kM/MUyXc1Zv3rqBj\n"
                + "TeGSgcqEAd6XlGXY1+M/yIeouUTi0F1bk1rNlqJvd57Xb4CEq17tVbGBm0hkECM8\n"
                + "-----END CERTIFICATE-----";

        return "Usage: OpenAM-serverUrl\n\n" + "Before trying this client, "
                + "configure a top-level realm OAuth 2.0 client profile\n"
                + "with client_id: " + clientId + ", "
                + "and Client JWT Bearer Public Key Certificate:\n\n" + certificate + "\n\n"
                + "Then to use this client, pass it the OpenAM Server URL\n"
                + "such as http://openam.example.com:8080/openam";
    }

    private static String getJws(String tokenEndpoint) {
        Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 5);

        JwtBuilderFactory jwtBuilderFactory = new JwtBuilderFactory();
        return jwtBuilderFactory
                .jws(new SigningManager().newRsaSigningHandler(getPrivateKey()))
                .headers()
                .alg(JwsAlgorithm.RS256)
                .done()
                .claims(jwtBuilderFactory.claims().iss(clientId).sub(clientId)
                        .aud(Collections.singletonList(tokenEndpoint)).exp(exp)
                        .build()).build();
    }

    private static PrivateKey getPrivateKey() {
        PrivateKey privateKey = null;

        try {
            KeyStore keystore = KeyStore.getInstance("JKS");
            keystore.load(Main.class.getResourceAsStream("/keystore.jks"),
                    "changeit".toCharArray());
            privateKey = (PrivateKey) keystore.getKey("self-signed",
                    "changeit".toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        if (privateKey == null) {
            System.err
                    .println("Failed to retrieve private key from keystore.jks.");
            System.exit(-1);
        }

        return privateKey;
    }

    private static void post(String idToken) throws Exception {
        URL token = new URL(tokenEndpoint);

        HttpURLConnection connection = (HttpURLConnection) token
                .openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        // This client is of type confidential, so authentication is required
        // according to http://tools.ietf.org/html/rfc6749#section-3.2.1
        String clientAssertionType = URLEncoder.encode(
                "urn:ietf:params:oauth:client-assertion-type:jwt-bearer",
                "UTF-8");
        String grantType = URLEncoder.encode(
                "urn:ietf:params:oauth:grant-type:jwt-bearer", "UTF-8");
        String data = "client_assertion_type=" + clientAssertionType
                + "&client_assertion=" + idToken + "&grant_type=" + grantType
                + "&assertion=" + idToken;

        DataOutputStream dataOutputStream = new DataOutputStream(
                connection.getOutputStream());
        dataOutputStream.writeBytes(data);
        dataOutputStream.flush();
        dataOutputStream.close();

        int responseCode = connection.getResponseCode();

        BufferedReader input;
        if (responseCode == 200) {
            input = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
        } else {
            input = new BufferedReader(new InputStreamReader(
                    connection.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        if (input != null) {
            String line;
            while ((line = input.readLine()) != null) {
                response.append(line);
            }
            input.close();
        } else {
            response.append("No input stream from reader.");
        }

        System.out.println("Response code: " + responseCode);
        System.out.println(response.toString());
    }

    private Main() {
        // Prevent instantiation.
    }
}
