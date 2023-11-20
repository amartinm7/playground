package handler

import (
	"github.com/playground/projects/go/challenges/rest_example/internal/application/product/fetch_products"
	"github.com/playground/projects/go/challenges/rest_example/internal/domain/product"
)

type FetchAllProductsHandler interface {
	FetchAll() (*product.Products, error)
}

type FetchAllProductsHandlerImpl struct {
	service fetch_products.GetProductsServiceInterface
}

func NewFetchAllProductsHandlerImpl(service fetch_products.GetProductsServiceInterface) FetchAllProductsHandlerImpl {
	return FetchAllProductsHandlerImpl{service}
}

func (fetchAllProductsController *FetchAllProductsHandlerImpl) FetchAll() (*product.Products, error) {
	return nil, nil
}
