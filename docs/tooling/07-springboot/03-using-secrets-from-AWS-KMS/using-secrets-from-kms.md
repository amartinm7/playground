# Using secrets from Param Store    

In this guide, we will learn how to use secrets from AWS KMS in a Spring Boot application.

setup your build.gradle.kt with parameter-store lib

![param-store-dependencies.png](_img%2Fparam-store-dependencies.png)

or kms-lib

![kms-dependencies.png](_img%2Fkms-dependencies.png)

## Setup springboot bootstrap.yml

Into the bootstrap file, setup the kms config:

```yaml
spring:
  application:
    name: ms-your-application-name
      
aws:
  kms:
    region: eu-west-1
    enabled: true
```

![spring_boot_param_store_config.png](_img%2Fspring_boot_param_store_config.png)

Create several properties files enable the kms in the application-dev.yml, application-pre.yml, and application-pro.yml files.

application-dev.yml
```yaml
aws:
  kms:
    enabled: false
```
application-pre.yml
```yaml
aws:
  kms:
    enabled: true
```
application-pro.yml
```yaml
aws:
  kms:
    enabled: true
```
![enable-services-on-bootstrapping.png](_img%2Fenable-services-on-bootstrapping.png)

## AWS Console Parameter Store

Go to the AWS Console and KMS to create a new master key for encrypting and decrypting:

![kms-console.png](_img%2Fkms-console.png)

![kms-console-new.png](_img%2Fkms-console-new.png)

## Encrypt the secret value

Use the `KMS command` to encrypt the value you want to use as password or secret in your application.
For instance, the database password, the API key, or any other secret you want to use in your application.
You can use a random key generator online service to generate the value.

Apply the command to encrypt the value:

```bash
aws kms encrypt \
--key-id <ARN_of_my_KMS_master_key> \
--plaintext "my-secret-token-before-encrypt" \
--output text \
--query CiphertextBlob
```
## Decrypt the secret value on springboot

It occurs automatically when you use the value in your application.yml file in boostrapping time.

In your application.yml, use the {cipher} token to mark the secret to decrypt the value automatically when springboot starts up. The decrypt value is used in the application in runtime as password to connect to the database for instance.

```yaml
    password: "{cipher}AQIC..."
```

## Decrypt the secret value on local

```bash
aws kms decrypt \
--ciphertext-blob 'AQICAHjmSj9FB9J0...' \
--key-id alias/my-kms-key \
--output text \
--query Plaintext | base64 -d
```
