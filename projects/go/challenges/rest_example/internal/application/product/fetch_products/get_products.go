package fetch_products

import (
	"github.com/playground/projects/go/challenges/rest_example/internal/domain/product"
	"github.com/playground/projects/go/challenges/rest_example/internal/infrastructure/product/repository"
)

type GetProductsServiceInterface interface {
	Execute() (*ProductsResponse, error)
}

type GetProductsService struct {
	repository repository.ProductRepository
}

func NewGetProductsService(repository repository.ProductRepository) GetProductsServiceInterface {
	return &GetProductsService{repository: repository}
}

func (service *GetProductsService) Execute() (*ProductsResponse, error) {
	_products, err := service.repository.FetchAll()
	if err != nil {
		return nil, err
	}

	if err != nil {
		return nil, err
	}
	return mapProducts(_products), nil
}

type ProductsResponse struct {
	Products product.Products
}

func mapProducts(products *product.Products) *ProductsResponse {
	return &ProductsResponse{*products}
}
