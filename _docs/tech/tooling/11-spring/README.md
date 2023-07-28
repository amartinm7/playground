# Spring basics

## IoC

Spring creates a container, a bean container. (applicationContainer or webContainer for Spring-MVC...)

Spring instances the beans and do the injection between them. 

To instanciate the beans, use the default scope singleton. That is, creates only an instance of every bean. In this way can reuse them everytime that is required. 

But the problem is if any has state. They have to be stateless to work without any problem and to be reusable. 

Spring is not thread-safe, that is, is not aware if two or more threads are using the same resource at the same time. If the bean is stateful, then there were side effects, race conditions and so on. It's a bad practice use singleton stateful beans. 

If we need store some status for a bean, we can use the prototype scope. Everytime the bean is required, the bean is instanciated. This approach is a little bit slower than the singleton scope, but resolves the thread-safe problem. 


supported scopes (@Scope annotation ):

- Singleton: Scopes a single bean definition to a single object instance per Spring IoC container.
- Prototype: Scopes a single bean definition to any number of object instances.
- Request: Scopes a single bean definition to the lifecycle of a single HTTP request; that is, each HTTP request has its own instance of a bean created
- Session: Scopes a single bean definition to the lifecycle of an HTTP Sessio
- Global-session: scopes a single bean definition to the lifecycle of a global HTTP Session for portlets.

Remember that `java/kotlin`, when a method is called, the JVM handles the method execution for each thread independently. Each thread gets its own `stack frame`, and the method operates on `its own set of local variables`.

Remember that `java/kotlin`, when a method is called, the arguments are passed by value not by reference. So the parameters are copied in their own stack frame