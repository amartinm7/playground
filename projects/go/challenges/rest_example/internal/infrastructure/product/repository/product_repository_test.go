package repository

import (
	"testing"

	"github.com/stretchr/testify/assert"

	"github.com/playground/projects/go/challenges/rest_example/internal/infrastructure/product/repository/mock"
)

func Test_product_repository(t *testing.T) {
	t.Run("should get all products", func(t *testing.T) {
		products, error := NewProductRepositoryImpl(&mock.HttpClientMock{}).FetchAll()
		assert.Nil(t, error)
		assert.Equal(t, *products, mock.DataProducts)
	})
}
