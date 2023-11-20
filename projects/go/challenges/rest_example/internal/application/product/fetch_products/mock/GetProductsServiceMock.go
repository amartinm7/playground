package mock

import (
	"github.com/playground/projects/go/challenges/rest_example/internal/domain/product"
	"github.com/stretchr/testify/mock"
)

type GetProductsServiceMock struct {
	mock.Mock
}

func NewGetProductsServiceMock(mock mock.Mock) GetProductsServiceMock {
	return GetProductsServiceMock{
		Mock: mock,
	}
}

func (m GetProductsServiceMock) Execute() (*product.Products, error) {
	args := m.Called()
	return args.Get(0).(*product.Products), args.Error(1)
}
