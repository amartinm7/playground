package product

import (
	"github.com/playground/projects/go/challenges/rest_example/internal/application/product/fetch_products"
	"github.com/playground/projects/go/challenges/rest_example/internal/infrastructure/kit/server/ginhandler"
	"github.com/playground/projects/go/challenges/rest_example/internal/infrastructure/product/repository"
	"github.com/playground/projects/go/challenges/rest_example/internal/infrastructure/product/server/handler"
	"net/http"
)

func InitFetchAllProductsHandler() ginhandler.Handler {
	var httpClient = http.Client{}
	var repository = repository.NewProductRepositoryImpl(&httpClient)
	var service = fetch_products.NewGetProductsService(repository)
	return handler.NewFetchAllProductsHandler(service)
}
