# Using secrets from Param Store    

In this guide, we will learn how to use secrets from AWS Param Store in a Spring Boot application.

setup your build.gradle.kt with parameter-store lib

![param-store-dependencies.png](_img%2Fparam-store-dependencies.png)

or kms-lib

![kms-dependencies.png](_img%2Fkms-dependencies.png)

## Setup springboot bootstrap.yml

Into the bootstrap file, setup the param-store config:

```yaml
spring:
  application:
    name: ms-your-application-name
  cloud:
    config:
      enabled: false
      
aws:
  paramstore:
    prefix: /microservices/${spring.application.name}
    default-context: application
    name: ${aws.paramstore.default-context}
    enabled: false
  kms:
    region: eu-west-1
```

![spring_boot_param_store_config.png](_img%2Fspring_boot_param_store_config.png)

Create several properties files enable the param-store in the application-dev.yml, application-pre.yml, and application-pro.yml files.

application-dev.yml
```yaml
aws:
  paramstore:
    enabled: false
```
application-pre.yml
```yaml
aws:
  paramstore:
    enabled: true
```
application-pro.yml
```yaml
aws:
  paramstore:
    enabled: true
```
![enable-services-on-bootstrapping.png](_img%2Fenable-services-on-bootstrapping.png)

## AWS Console Parameter Store

Go to the AWS Console and create a new parameter:

![aws-parameter-store.png](_img%2Faws-parameter-store.png)

To do that, click on the "create parameter" button and fill the form:

- fill a key name, split by "/" to create a path
- select standard tier
- select secure string to use KMS to encrypt the API-KEY value
- Paste the value and the end of form and click on the "create parameter" button

![aws-parameter-store-form.png](_img%2Faws-parameter-store-form.png)

The KMS service encrypts the value and stores it in the parameter store.

## How to use it in your code

In your application.yml, use the `${your-param-store.key}` to get the value from the parameter store. Use the part without the prefix to get the value. 

If you see the bootstrap.yml file, you can see the prefix used to get the value from the parameter store. So you need to use the key without the prefix in the application.yml file.

```application.yml
app:
  yourapp:
    apiKey: "${your.key}"
```

Springboot will use the KMS library to decrypt the value and use it in your application.