# Project Name
# user-auth-server


## Overview
This project is a Java application designed to run on **Java 17** or higher. 
It is configured to use the default port **8080**. 
Please follow the instructions.

## Prerequisites
- **Java 17** or higher installed
- **Git** installed for cloning the repository
- **cURL** installed for testing API endpoints

## Setup Instructions

### 1. Clone the Project
git clone https://github.com/ArmenParonyan/user-auth-server

### 2. Build and Run the Project
   Once the repository cloned, navigate to the project directory and run below commands:

    cd <project-directory>
    mvn clean install
    mvn spring-boot:run


### 3. Obtain a Bearer Token
   You need a bearer token for authorization. To obtain this token, use below curl command:

curl --location --request POST 'https://dev-o3g2iunvcwrt4kzh.us.auth0.com/oauth/token' \
--header 'content-type: application/json' \
--header 'Cookie: did=s%3Av0%3A0dd94f62-1c9e-48ab-8809-5e0a13c69460.7%2F7zlM4iE2AIf8lPqqeTcq1Pz3qiib8aErTri%2Fbgpgg; did_compat=s%3Av0%3A0dd94f62-1c9e-48ab-8809-5e0a13c69460.7%2F7zlM4iE2AIf8lPqqeTcq1Pz3qiib8aErTri%2Fbgpgg' \
--data-raw '{"client_id":"2Msz0E0UhsMMTDN2YaGZVEXGXZtOiAaC","client_secret":"ANJNMvy6kjAv_rGTt4G93fvIf10hTj-kFInW32RBZvcBT_dNPIMviUiVrKKRX3iz","audience":"https://dev-o3g2iunvcwrt4kzh.us.auth0.com/api/v2/","grant_type":"client_credentials"}'


Example of response:

{
"access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImJSQXRza0g5RzhzRVNIS3FmbDBnOCJ9.eyJpc3MiOiJodHRwczovL2Rldi1vM2cyaXVudmN3cnQ0a3poLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiIyTXN6MEUwVWhzTU1URE4yWWFHWlZFWEdYWnRPaUFhQ0BjbGllbnRzIiwiYXVkIjoiaHR0cHM6Ly9kZXYtbzNnMml1bnZjd3J0NGt6aC51cy5hdXRoMC5jb20vYXBpL3YyLyIsImlhdCI6MTcyNTYzMTg2MywiZXhwIjoxNzI1NzE4MjYzLCJzY29wZSI6InJlYWQ6dXNlcnMgdXBkYXRlOnVzZXJzIGRlbGV0ZTp1c2VycyBjcmVhdGU6dXNlcnMiLCJndHkiOiJjbGllbnQtY3JlZGVudGlhbHMiLCJhenAiOiIyTXN6MEUwVWhzTU1URE4yWWFHWlZFWEdYWnRPaUFhQyJ9.QLH4_G6hQ-YkIUuv8p22EsjGraU4-qM-bhAXlPeTCrE2zos7HcjR5byw7c9ww_nQncTP2u3mHMajLXhzmAP_Pwo60UmisZ5QPQ-4EclmXvd8tEhQDRX3FNY5Mz4zgnabCUsGrGMI2iAWqU7vU2FEN1JYgxErk_6mNrd8nz5Gy8Cf-DJ2rSyO-NevGydFmY11TsC86LKeV26T9KUFxbsocjF5hku7wx9wL9QMC0eX0CO9E_Ap8GPGdDrgRyVd6SYHHcXlQ9VfM_g7-O7q3RYiXLlpnLoVFLoT67nnAGfEa4kXXgwxe42SzQiHbc3Zxp-fXdfX-Yp-sJRWsE8lV96uWw",
"scope": "read:users update:users delete:users create:users",
"expires_in": 86400,
"token_type": "Bearer"
}

Copy the access_token from the response.


### 4. Call user auth GET endpoint
To check if a user authorized, use the below curl command. 
Replace <access_token> with access_token obtained in the previous step.

curl --location --request GET 'http://localhost:8080/userAuth' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImJSQXRza0g5RzhzRVNIS3FmbDBnOCJ9.eyJpc3MiOiJodHRwczovL2Rldi1vM2cyaXVudmN3cnQ0a3poLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiIyTXN6MEUwVWhzTU1URE4yWWFHWlZFWEdYWnRPaUFhQ0BjbGllbnRzIiwiYXVkIjoiaHR0cHM6Ly9kZXYtbzNnMml1bnZjd3J0NGt6aC51cy5hdXRoMC5jb20vYXBpL3YyLyIsImlhdCI6MTcyNTYzMTg2MywiZXhwIjoxNzI1NzE4MjYzLCJzY29wZSI6InJlYWQ6dXNlcnMgdXBkYXRlOnVzZXJzIGRlbGV0ZTp1c2VycyBjcmVhdGU6dXNlcnMiLCJndHkiOiJjbGllbnQtY3JlZGVudGlhbHMiLCJhenAiOiIyTXN6MEUwVWhzTU1URE4yWWFHWlZFWEdYWnRPaUFhQyJ9.QLH4_G6hQ-YkIUuv8p22EsjGraU4-qM-bhAXlPeTCrE2zos7HcjR5byw7c9ww_nQncTP2u3mHMajLXhzmAP_Pwo60UmisZ5QPQ-4EclmXvd8tEhQDRX3FNY5Mz4zgnabCUsGrGMI2iAWqU7vU2FEN1JYgxErk_6mNrd8nz5Gy8Cf-DJ2rSyO-NevGydFmY11TsC86LKeV26T9KUFxbsocjF5hku7wx9wL9QMC0eX0CO9E_Ap8GPGdDrgRyVd6SYHHcXlQ9VfM_g7-O7q3RYiXLlpnLoVFLoT67nnAGfEa4kXXgwxe42SzQiHbc3Zxp-fXdfX-Yp-sJRWsE8lV96uWw'

If the user authorized status code will be 200 and response:

Success: User authorized

### Notes
Ensure that Java 17 or a later version
The application configured to run on port 8080, do not change the port.
You can test the API endpoints using the curl commands provided.

### Troubleshooting
Ensure all required variables are correctly set in the authorization curl command.
Check for Java version compatibility by running java -version.
Ensure port 8080 is free.