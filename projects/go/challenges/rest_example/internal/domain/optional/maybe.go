package optional

type Maybe[T any] struct {
	value T
	err   error
}

func Just[T any](val T) Maybe[T] {
	return Maybe[T]{value: val}
}

func Nothing[T any](err error) Maybe[T] {
	return Maybe[T]{err: err}
}

func (m Maybe[T]) OnError(f func(error) error) Maybe[T] {
	if m.err != nil {
		return Nothing[T](f(m.err))
	}
	return m
}

func (m Maybe[T]) OnSuccess(f func(T) Maybe[T]) Maybe[T] {
	if m.err != nil {
		return m
	}
	return f(m.value)
}
