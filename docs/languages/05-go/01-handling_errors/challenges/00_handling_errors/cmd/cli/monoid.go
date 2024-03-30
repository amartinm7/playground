package main

// The Monoid interface represents a monoid.
type Monoid interface {
	Identity() interface{}
	Op(interface{}) interface{}
}

// The IntAddition type represents integer addition as a monoid.
type IntAddition struct{}

// The Identity method returns the identity element for integer addition (0).
func (IntAddition) Identity() interface{} {
	return 0
}

// The Op method returns the result of performing integer addition on two integers.
func (IntAddition) Op(x interface{}) interface{} {
	return func(y interface{}) interface{} {
		return x.(int) + y.(int)
	}
}
