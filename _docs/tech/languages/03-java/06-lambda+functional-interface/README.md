# Lambda functions and functional interfaces

1.- define a functional interface, which is an interface with only method

```java
@funcionalInterface
interface A {
    public void show();
}
```

2.- Implements the interface as anonymous class without the body, only the logic inside the method

```java
class B {
    public static void main (String args[]) {
        A a = new A() {// anonymous class
            public void show() {
                System.out.prinltln("hello world");
            }
        };
        a.show(); //output: "hello world" 
    }
}
```

```java
class B {
    public static void main (String args[]) {
        A a = () -> System.out.prinltln("hello world");
        a.show(); //output: "hello world" 
    }
}
```

## Default methods in interfaces

They are methods implemented, so the interface it's like an abstract class. 

You can add static default methods too.

```java
public interface Vehicle {
    
    String getBrand();
    
    String speedUp();
    
    String slowDown();
    
    default String turnAlarmOn() {
        return "Turning the vehicle alarm on.";
    }
    
    default String turnAlarmOff() {
        return "Turning the vehicle alarm off.";
    }
    
    static int getHorsePower(int rpm, int torque) {
        return (rpm * torque) / 5252;
    }
}
```

## functional interfaces

Functional interface is nothing but the interface which contains only one abstract method but can have multiple default and static methods. To put it in simple words Any interface with a SAM(Single Abstract Method) is a functional interface.

```java
Runnable -> run();
Callable -> call();
Comparable -> compareTo(T o);
Comparator -> compare(T o1, T o2);
Consumer -> accept(T t);
Predicate -> test(T t);
Supplier -> get();
```

## Consumer

```java
void accept(T t);
default Consumer<T> andThen(Consumer<? super T> after);
```

```java
Consumer<String> consume -> (n)->System.out.println(n);
consume.accept()
list.stream().forEach(consume)
```

## Predicate

```java
boolean test(T t);
default Predicate<T> and(Predicate<? super T> other);
default Predicate<T> or(Predicate<? super T> other);
static <T> Predicate<T> isEquals(Object targetRef);
default Predicate<T> negate();
```

```java
Predicate<Integer> predicate -> (i)-> i%2 == 0;
predicate.test()
list.stream().filter(predicate)
```

## Supplier

This Functional Interface is used in all contexts where there is no input but an output is expected.

```java
Supplier<Item> supplier = () -> new Item("Dummy", 0);
item.getPrice()>1000).findAny().orElseGet(supplier));
```

## Function

```java
Function<String, String> function = (s) -> "Hello" + s;
function.apply("world")
list.stream().forEach(function) // "hello world" 
```

## double colon operator ( :: ) 

```java
Arrays.asList(c1, c2, c3).forEach(System.out::print);
```

```java
Function<Computer, Integer> getAge = Computer::getAge;
        Integer computerAge = getAge.apply(c1);
```

## Optional

A value is present only if we have created Optional with a non-null value.

```java
Optional<String> opt = Optional.of("John");
assertTrue(opt.isPresent()); // true
```

```java
String name = null;
Optional<String> opt = Optional.ofNullable(name);
assertTrue(opt.isPresent()); // false, but doesn't throw any exception
assertTrue(opt.isEmpty()); // true
```

```java
String name = Optional.ofNullable(nullName).orElse("john");

String name = Optional.ofNullable(nullName).orElseGet(() -> "john");

String name = Optional.ofNullable(nullName).orElseThrow(
IllegalArgumentException::new);

Optional<String> opt = Optional.ofNullable(null);
String name = opt.get();

Optional.ofNullable(modem2)
.map(Modem::getPrice)
.filter(p -> p >= 10)
.filter(p -> p <= 15)
.isPresent();

listOptional
.map(List::size)
.orElse(0);
```