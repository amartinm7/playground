package main

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"github.com/playground/projects/go/challenges/rest_example/internal/infrastructure/product"
	"net/http/httptest"
)

func main() {
	fmt.Println("Running my first app!...")
	//https://dummyjson.com/products
	w := httptest.NewRecorder()
	c, _ := gin.CreateTestContext(w)
	handler := product.InitFetchAllProductsHandler()
	handler.GetHandlerFunc()(c)
}
