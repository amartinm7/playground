# Java questions

## JDK - JRE - JVM - JIT - Class loader

### hierarchy 

![jdk-jre-jvm.jpg](_img%2Fjdk-jre-jvm.jpg)

### JRE flow

![jre.png](_img%2Fjre.png)

![JRE.gif](_img%2FJRE.gif)

### Flow

![java-execution-flow-diagram.jpg](_img%2Fjava-execution-flow-diagram.jpg)

Java Classloader is the program that belongs to JRE (Java Runtime Environment). The task of ClassLoader is to load the required classes and interfaces to the JVM when required.

### JIT compiler optimizer

![java-jit-compiler-working.png](_img%2Fjava-jit-compiler-working.png)


##  Is java a completely object-oriented programming language? 

We can say that - Java is not a pure object-oriented programming language, because it has direct access to primitive data types. And these primitive data types don't directly belong to the Integer classes.

## What are the default values assigned to variables and instances in java?

We need to initialize the value before using it. Otherwise, it will throw a compilation error of (Variable might not be initialized).

But for instance, if we create the object, then the default value will be initialized by the default constructor depending on the data type.

- If it is a reference, then it will be assigned to null.
- If it is numeric, then it will assign to 0.
- If it is a boolean, then it will be assigned to false. Etc.

## What do you mean by data encapsulation?

It is hiding the data attributes and their behaviours in a single unit.

class = data members (status) + methods (behavior)

## JIT compiler

First, the Java source code (.java) conversion to byte code (.class) occurs with the help of the `javac compiler`.

Then, the `.class` files are `loaded at run time by JVM` and with the help of an interpreter (JIT), these are converted to machine understandable code.

JIT compiler is a part of JVM. When the JIT compiler is enabled, the JVM analyzes the method calls in the `.class` files and `compiles` them to get more efficient and `native code`. It also ensures that the prioritized method calls are optimized.
Once the above step is done, the JVM executes the optimized code directly instead of interpreting the code again. This increases the performance and speed of the execution.

To resume it, the `JIT is the bytecode compiler to native-code` (codigo maquina)

![JIT_Compiler.jpg](_img%2FJIT_Compiler.jpg)

## difference between equals() method and equality operator (==) in Java?

`==` compares addresses of the object reference. So obj1 == obj2 is true if are the same object. For instance Object obj = new Object() and Object obj1 = obj and Object obj2 = obj.

but if it was Object obj1 = new Object(), Object obj2 = new Object() obj1 == obj2 is false, because are different objects, their hashcodes are different, and they appoint/target to different memory addresses.

`equals` compares the status of the objects. 

For instance, if I have a class MyAccount { String name }, and I have two instances of the MyAccount acc1 = new MyAccount("john"), MyAccount acc2 = new MyAccount("john"), acc1.equals(acc2) is true if we implement the equals method to compare the name. If the equals method is not implemented, then is compared by ==.

special use case: 

```java
String str1 = new String("InterviewBit"); // heap portion
String str2 = "InterviewBit"; // constant pool
 
System.out.println(str1 == str2); // false
```
str1 is located into the heap portion and the str2 into the constant pool, so the result is false.

![string-memory-use-case.png](_img%2Fstring-memory-use-case.png)

## java loops

```java
for (int i: numberList){}
for (int i = 0; i < 10; i++) {}
while (true) {}
do {} while(true);
```

## overloading and overriding

if you have several methods with the same name and return type, but with different number of arguments, you are `overloading the methods

if you have a method with the same name and return type of another parent class, you are `overriding` and hiding the parent method.

static methods can't be `overriding`

## final keyword

- final variable: only can be initialized one time or initialized into a constructor
- final method: you can't be overriding by children classes
- final class: you can't be extends by children classes

## try-catch-finally

Yes. It is possible that the ‘finally’ block will not be executed. The cases are:

- Suppose we use System.exit() in the above statement.
- If there are fatal errors like Stack overflow, Memory access error, etc.


## arraylist-vs-linked-list

LinkedList is ready to add/remove elements in an efficient way
Arraylist is ready to access to the elements in a fast way

## java 8 improvements: Streams

- when you have an arraylist you can do stream and iterate to do maps and reduce operations.
- Final operations are to collect the result to a list, map or set

## equals and hashcode 

If two objects are equal(according to `equals()` method then the `hashCode()` method should return the same integer value for both the objects. But, it is not necessary that the hashCode() method will return the distinct result for the objects that are not equal (according to equals() method)

So, we have just seen that the `equals()` and `hashCode()` methods work together inside the `HashMap` to get and put the data: `hashCode() is used to compute the bucket number` and `equals() is used to find the Entry with the same key`

![working-of-hashmap-in-java2.png](_img%2Fworking-of-hashmap-in-java2.png)

### Hashmap

You can store only a value for a key, if you use the same key to store two or more times some objects, everytime the map will update with the last object inserted.

But, if you have several objects with the same hashcode, when you store them into the map, they are stored in the same bucket using a structure called a red-black tree.

Use case, put the key "one" with the hashcode = 1234, and the store the object myAccount1 with the hashcode = 1111. After that, put the key "two" with the hashcode = 1234, and store the object myAccount2 with the hashcode = 2222. So the bucket for "one" and "two" is the same, because the hashcode is the same. So the first pair ("one", myAccount1) is stored and target to the second pair ("two", myAccount2) 

```java
Map<String,MyAccount> map = new HashMap<String,MyAccount>();
map.put("one", new MyAccount("john", "smith"))
map.put("two", new MyAccount("john", "lennon"))
map.get("one")
```

usecase: adding an object to a hashmap with the existent object inside.
- first, the bucket rule is applied to know which bucket belongs to the object to add, using the hashcode() method of the object.
- if the bucket is occupied for another object, looks if the previous object is equals to the new one. 
  - if exists the same object, then override it
  - if it's a different object, then add it to the linked-list(<=java8) or balanced tree(>java8)

usecase: get and object from a map
- first, with the key, gets the hashcode. Go to the bucket.
- if the bucket exists, get the first element.

## Heap and Stack Memory in java

![java_memory.jpg](_img%2Fjava_memory.jpg)

Memory Leak is a situation where there are objects present in the heap that is no longer used, but the garbage collector is unable to remove them from memory, and therefore, they’re unnecessarily maintained. A memory leak is bad because it blocks memory resources and degrades system performance over time.

Using Java profiling you can monitor different JVM parameters, including object creation, thread execution, method execution, and yes, garbage collection. also, Using tools that can detect memory leaks. Using heap dumps can also help.

Heap is cleaned in garbage collection process, the stack doesn't

Stack memory is the portion of memory that was assigned to every individual program. And it was fixed. On the other hand, Heap memory is the portion that was not allocated to the java program but it will be available for use by the java program when it is required, mostly during the runtime of the program.

Java Utilizes this memory as:

- When we write a java program then all the variables, methods, etc are stored in the stack memory.
- And when we create any object in the java program then that object was created in the heap memory. And it was referenced from the stack memory.

```java
class Main {
   public void printArray(int[] array){
       for(int i : array)
           System.out.println(i);
   }
   public static void main(String args[]) {
       int[] array = new int[10];
       printArray(array);
   }
}
```

![stack_and_heap_memory.png](_img%2Fstack_and_heap_memory.png)

Main and PrintArray is the method that will be available in the stack area and as well as the variables declared that will also be in the stack area.

And the Object (Integer Array of size 10) we have created, will be available in the Heap area because that space will be allocated to the program during runtime. 

## Shadow copy vs deep copy

- `Shallow copy: Clone this object and keep its references`. So when we modify the shadow copy object we are modifying the original object too. 

- `Deep copy: Clone this object and every reference to every other object it has`. So if we modify the copy we are not modifying the original object. 

the java `clone` method allows to do a deep copy but the Java API doesn't do it well. It's better to use another alternatives.

## String

If you create an string object, the reference will be stored into the heap and it's mutable, but if you use the string itself, it goes to the string pool and is immutable.

for multithreading porpoises, the string has to be immutable

for map (hashmap and hashtable), the string has to be immutable. The opposite broke the key index is the string changes and if was mutable. You can find the original stored object into the map.

![string_pool.png](_img%2Fstring_pool.png)

## multithreading

No external synchronization is required if the objects are immutable.

## Java 8 Optional

## Java 8 Stream API

the Stream is an API with the operations: filter, map, reduce, collect or whatever. 

Every time we use one of these methods, return is another Stream with the result of the method. This is why we can call several unchained methods.

```java
list.stream().map(n->n*2).collect(Collectors.toList())
```

```java 
ArrayList employeeList = new ArrayList()
Optional<Employee> maxSalaryEmployee = 
    employeeList.stream()
    .collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)));
```
We can use the Predicate on the `filter`Stream method. The Predicate is a `functional interface

```java
Predicate<Integer> p = (n) -> n % 2 == 0;
p.test(5); // output: false
```

## What is Method Reference in Java 8?

Method reference is used to refer method of the functional interface. It is a compact and easy form of lambda expression. Each time when you are using a lambda expression to just referring a method, you can replace your lambda expression with a method reference. You can use those methods with the “::” double colon operator.

```
ContainingClass::staticMethodName
```
# Usage of the index in java? Advantages and disadvantages of it in Database?

index means the position in the ArrayList, with the first position being 0 (zero). This is the case for most programming languages

Advantage — Searching is easy and hassle-free.

Disadvantage- Insertion, and deletion are not easy.

