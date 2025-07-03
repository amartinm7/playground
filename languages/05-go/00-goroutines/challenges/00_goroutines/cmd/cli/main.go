package main

import "fmt"

func main() {
	fmt.Println("Running goroutine app!...")
	//
	nums := []int{1, 2, 3, 4, 5}
	//
	doubleChannel := sliceDoubler(nums)
	//
	sqrChannel := squareNumbers(doubleChannel)
	//
	for num := range sqrChannel {
		fmt.Println(num)
	}
}

func sliceDoubler(nums []int) <-chan float64 {
	channel := make(chan float64)
	go func() {
		for _, num := range nums {
			channel <- float64(num * 2)
		}
		close(channel)
	}()
	return channel
}

func squareNumbers(inChannel <-chan float64) <-chan float64 {
	channel := make(chan float64)
	go func() {
		for num := range inChannel {
			channel <- num * num
		}
		close(channel)
	}()
	return channel
}
