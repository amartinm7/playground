# Create a Custom Auto-Configuration with Spring Boot

reference:
- https://www.baeldung.com/spring-boot-custom-auto-configuration

Imagine that we have some configuration that is common or repeated between several projects.

Instead of to duplicate the configuration in everywhere we can create a common project o library with the 
common configuration. That is, a project which has the @configuration files.

To do that:

- create a resources/META-INF/spring folder
- inside the folder, creates the `org.springframework.boot.autoconfigure.AutoConfiguration.imports` file
- fill the file with the class names of the configurations to share.
- the config files has the `@Configuration` annotation

## Class conditions
 
- @ConditionalOnClass specify that we want to include a configuration bean if a specified class is present
- @ConditionalOnMissingClass the opposite to before

## Bean conditions

- @ConditionalOnBean include a bean only if a specified bean is present or not,
- @ConditionalOnMissingBean the opposite to before

## Property conditions

- @ConditionalOnProperty specify if a configuration loads based on the presence and value of a Spring Environment property.

## Resource conditions 

- @ConditionalOnResource configuration loads only when a specified resource is present: a file is present, for instance

## Custom Conditions

define custom conditions by extending the SpringBootCondition class and overriding the getMatchOutcome() method.

## Application Conditions

specify that the configuration can load only inside/outside a web context.

- @ConditionalOnWebApplication
- @ConditionalOnNotWebApplication

## Disabling Auto-Configuration Classes

exclude the auto-configuration from loading.

We could add the @EnableAutoConfiguration annotation with exclude or excludeName attribute to a configuration class:

```kotlin
@Configuration
@EnableAutoConfiguration(
  exclude={MySQLAutoconfiguration.class})
public class AutoconfigurationApplication {
    //...
}
```

We can also set the spring.autoconfigure.exclude property

```properties
spring.autoconfigure.exclude=com.amm.autoconfiguration.PostgresAutoconfiguration
```