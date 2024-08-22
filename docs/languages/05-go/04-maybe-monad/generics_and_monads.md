# Generics and Maybe Monad

- Una `monada` es un concepto de la programación funcional que proporciona un contexto para trabajar con valores encapsulados. Proporciona una forma de aplicar funciones a estos valores encapsulados mientras maneja el contexto de manera transparente, como el manejo de errores, el estado mutable, la entrada/salida, etc.

- A `monoid` is a design pattern that represents an `associative binary operation` with an `identity` element. In functional programming, a monoid is often used to combine values in a way that is both commutative and associative, such as adding numbers or concatenating strings. It uses to do a `reduce` operation over a list of elements.

- A `functor` is a design pattern that represents a container or structure that can be mapped over. In functional programming, a functor is often used to apply a function to each element of a collection and return a new collection with the transformed elements. 

## Generics Over the struct 

```go
type Maybe[T any] struct {
	value T
	err   error
}
```
or several types

```go
type Maybe[T int32|float32|string] struct {
    value T
    err   error
    }
```

## Generics Over a method

```go
func Just[T any](val T) Maybe[T] {
	return Maybe[T]{value: val}
}
```

## Generics Over a func method of a struct

```go
func (m Maybe[T]) OnSuccess(f func(T) Maybe[T]) Maybe[T] {
	if m.err != nil {
		return m
	}
	return f(m.value)
}
```