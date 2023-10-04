package main

import (
	"fmt"
	"maps"
	"slices"
	"time"
)

func main() {
	fmt.Println("Language basics!!")
	timeExamples()
	checkTypeExamples()
	arrayExamples()
	sliceExamples()
	mapExamples()
}

func timeExamples() {
	// time
	switch time.Now().Weekday() {
	case time.Saturday, time.Sunday:
		fmt.Println("It's the weekend")
	default:
		fmt.Println("It's a weekday")
	}
	//
	t := time.Now()
	switch {
	case t.Hour() < 12:
		fmt.Println("It's before noon")
	default:
		fmt.Println("It's after noon")
	}
}

func checkTypeExamples() {
	// important: all the elements are interface's, interface is like Object in java,
	// you can create a func reference in this way
	whatAmI := func(i interface{}) {
		switch t := i.(type) {
		case bool:
			fmt.Println("I'm a bool")
		case int:
			fmt.Println("I'm an int")
		default:
			fmt.Printf("Don't know type %T\n", t)
		}
	}
	whatAmI(true)
	whatAmI(1)
	whatAmI("hey")
}

func arrayExamples() {
	// By default an array is zero-valued, which for ints means 0s.
	// arrays defines the size. They can't grow. Are a static structure
	// slices are arrays without size. They can grow. Are a dynamic structure
	var a [5]int
	fmt.Println("emp:", a)
	//
	b := [5]int{1, 2, 3, 4, 5}
	fmt.Println("dcl:", b)
	//
	var twoD [2][3]int
	for i := 0; i < 2; i++ {
		for j := 0; j < 3; j++ {
			twoD[i][j] = i + j
		}
	}
	fmt.Println("2d: ", twoD)
}

func sliceExamples() {
	// arrays defines the size. They can't grow. Are a static structure
	// slices are arrays without size. They can grow. Are a dynamic structure
	// creates an empty slice
	var s []string
	fmt.Println("no initialized slice:", s, s == nil, len(s) == 0)
	// creates an slice of strings of length 3 (initially zero-valued).
	s = make([]string, 3)
	fmt.Println("emp:", s, "len:", len(s), "cap:", cap(s))
	// creates a slice with values
	t := []string{"g", "h", "i"}
	fmt.Println("dcl:", t)
	// set/get the slice
	s[0] = "a"
	s[1] = "b"
	s[2] = "c"
	fmt.Println("set:", s)
	fmt.Println("get:", s[2])
	// increase the slice size with the append func
	s = append(s, "d")
	s = append(s, "e", "f")
	fmt.Println("apd:", s)
	// you can copy a slice
	c := make([]string, len(s))
	copy(c, s)
	fmt.Println("cpy:", c)
	// you can slice a slice
	l := s[2:5]
	fmt.Println("sl1:", l)
	// The slices package contains a number of useful utility functions for slices.
	// check the slices are equals
	t2 := []string{"g", "h", "i"}
	if slices.Equal(t, t2) {
		fmt.Println("t == t2")
	}
}

func mapExamples() {
	// creates an empty map
	m := make(map[string]int)
	// set values
	m["k1"] = 7
	m["k2"] = 13
	fmt.Println("map:", m)
	// get values
	v1 := m["k1"]
	fmt.Println("v1:", v1)
	// delete key
	delete(m, "k2")
	fmt.Println("map:", m)
	// clear map
	clear(m)
	fmt.Println("map:", m)
	// checks if the key exists
	_, exits := m["k2"]
	fmt.Println("exits:", exits)
	// init map
	n := map[string]int{"foo": 1, "bar": 2}
	fmt.Println("map:", n)
	// The maps package contains a number of useful utility functions for maps.
	n2 := map[string]int{"foo": 1, "bar": 2}
	if maps.Equal(n, n2) {
		fmt.Println("n == n2")
	}
}
