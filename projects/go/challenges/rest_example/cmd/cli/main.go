package main

import (
	"fmt"
	"net/http"

	"github.com/playground/projects/go/challenges/rest_example/internal/application/product/fetch_products"
	"github.com/playground/projects/go/challenges/rest_example/internal/infrastructure/product/repository"
)

func initService() fetch_products.GetProductsService {
	var httpClient = http.Client{}
	var repository = repository.NewProductRepositoryImpl(&httpClient)
	var service = fetch_products.NewGetProductsService(repository)
	return service
}

func main() {
	fmt.Println("Running my first app!...")
	//https://dummyjson.com/products
	var service = initService()
	var products, _ = service.Execute()
	fmt.Println(products)
}
