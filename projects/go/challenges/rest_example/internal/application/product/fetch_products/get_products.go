package fetch_products

import (
	"github.com/playground/projects/go/challenges/rest_example/internal/domain/product"
	"github.com/playground/projects/go/challenges/rest_example/internal/infrastructure/product/repository"
)

type GetProductsService struct {
	repository repository.ProductRepository
}

func NewGetProductsService(repository repository.ProductRepository) GetProductsService {
	return GetProductsService{repository: repository}
}

func (service *GetProductsService) Execute() (*product.Products, error) {
	_products, err := service.repository.FetchAll()
	if err != nil {
		return nil, err
	}

	if err != nil {
		return nil, err
	}
	return _products, nil
}
