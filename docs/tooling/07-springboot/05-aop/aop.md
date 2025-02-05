# AOP for logging

## Introduction, the problem of using logs
Example of using AOP to log method calls.
```java
public void processOrder(Order order) {
    Logger logger = LoggerFactory.getLogger(OrderService.class);
    logger.info("Processing order: " + order.getId());
    
    try {
        // Business logic for processing the order
        orderRepository.save(order);
        logger.info("Order processed successfully: " + order.getId());
    } catch (Exception e) {
        logger.error("Error processing order: " + order.getId(), e);
        throw new OrderProcessingException("Failed to process order", e);
    }
}
```

## Old Usage with Spring Boot
example of using a Java Proxy to add logging:
```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggingProxy implements InvocationHandler {

    private final Object target;

    public LoggingProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before method: " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("After method: " + method.getName());
        return result;
    }

    public static <T> T createProxy(T target, Class<T> interfaceType) {
        return (T) Proxy.newProxyInstance(
            interfaceType.getClassLoader(),
            new Class<?>[]{interfaceType},
            new LoggingProxy(target)
        );
    }
}

```

## Modern Usage with Spring Boot

First, define the class:
```java
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public void processOrder(Order order) {
        // Business logic for processing the order
        orderRepository.save(order);
    }
}
```

Then, define the aspect:
```
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.service.OrderService.processOrder(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.example.service.OrderService.processOrder(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After method: " + joinPoint.getSignature().getName());
    }
}
```

Finally, enable AOP in your Spring Boot application:
```java 
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.example")
@EnableAspectJAutoProxy
public class AppConfig {
}
```

Enable it all on the main Application Class:
```java
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.service.OrderService;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = context.getBean(OrderService.class);
        orderService.processOrder(new Order(1));
        context.close();
    }
}
```