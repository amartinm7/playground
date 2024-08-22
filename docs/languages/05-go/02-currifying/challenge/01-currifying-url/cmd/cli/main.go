package main

import "fmt"

func main() {
	fmt.Println("Running currifying app!...")
	baseURL := "https://api.example.com"
	url := createURL(baseURL)
	fmt.Println(url("users"))
	fmt.Println(url("users", "1"))
	fmt.Println(url("users", "1", "posts"))
}

func createURL(fromBaseURL string) func(...string) string {
	return func(segmentURL ...string) string {
		path := ""
		for _, segment := range segmentURL {
			path += "/" + segment
		}
		return fromBaseURL + path
	}
}
