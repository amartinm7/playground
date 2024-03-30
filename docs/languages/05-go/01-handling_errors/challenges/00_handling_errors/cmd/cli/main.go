package main

import (
	"fmt"
)

func main() {
	fmt.Println("handling errors!...")
	var r ResultHandler = Result{Success: 1, Failure: nil}
	r.sum(2).multiplyBy(3).divideBy(1).onSuccess().print().onFailure().printError()
	r.sum(2).multiplyBy(3).divideBy(0).onSuccess().print().onFailure().printError()

	// Define a monoid.
	// addition := IntAddition{}

	// Use the monoid to add some numbers.
	// sum := addition.Op(1)(2)(3)(4)

	//fmt.Println(sum) // Output: 10
}

type Result struct {
	Success interface{}
	Failure error
}

type ResultHandler interface {
	multiplyBy(a int) Result
	sum(a int) Result
	divideBy(a int) Result
	print() Result
	printError() Result
	onSuccess() Result
	onFailure() Result
}

func (r Result) sum(a int) Result {
	return Result{Success: r.Success.(int) + a, Failure: nil}
}

func (r Result) multiplyBy(a int) Result {
	return Result{Success: r.Success.(int) * a, Failure: nil}
}

func (r Result) divideBy(a int) Result {
	if a != 0 {
		return Result{Success: r.Success.(int) / a, Failure: nil}
	} else {
		return Result{Success: nil, Failure: fmt.Errorf("division by zero is not allowed")}
	}
}

func (r Result) print() Result {
	fmt.Println(fmt.Sprintf("Result: %v", r.Success))
	return r
}

func (r Result) printError() Result {
	fmt.Println(fmt.Sprintf("Error: %v", r.Failure))
	return r
}

func (r Result) onSuccess() Result {
	return r
}

func (r Result) onFailure() Result {
	return r
}
