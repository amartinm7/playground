package mock

import (
	"github.com/playground/projects/go/challenges/rest_example/internal/application/product/fetch_products"
	"github.com/stretchr/testify/mock"
)

type GetProductsServiceMock struct {
	mock.Mock
}

func NewGetProductsServiceMock(mock mock.Mock) fetch_products.GetProductsServiceInterface {
	return &GetProductsServiceMock{Mock: mock}
}

func (m GetProductsServiceMock) Execute() (*fetch_products.ProductsResponse, error) {
	args := m.Called()
	return args.Get(0).(*fetch_products.ProductsResponse), args.Error(1)
}
