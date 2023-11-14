package mock

import (
	"github.com/stretchr/testify/mock"

	"github.com/playground/projects/go/challenges/rest_example/internal/domain/product"
)

type ProductRepositoryMock struct {
	mock.Mock
}

func NewProductRepositoryMock(mock mock.Mock) *ProductRepositoryMock {
	return &ProductRepositoryMock{Mock: mock}
}

func (m *ProductRepositoryMock) FetchAll() (*product.Products, error) {
	args := m.Called()
	return args.Get(0).(*product.Products), args.Error(1)
}
