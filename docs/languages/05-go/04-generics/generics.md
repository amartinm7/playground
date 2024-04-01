# Generics

## Over the struct 

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

## Over a method

```go
func Just[T any](val T) Maybe[T] {
	return Maybe[T]{value: val}
}
```

## Over a func method of a struct

```go
func (m Maybe[T]) OnSuccess(f func(T) Maybe[T]) Maybe[T] {
	if m.err != nil {
		return m
	}
	return f(m.value)
}
```