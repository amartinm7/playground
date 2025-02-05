package handler

import (
	"encoding/json"
	"github.com/gin-gonic/gin"
	"github.com/playground/projects/go/challenges/rest_example/internal/application/product/fetch_products"
	"github.com/playground/projects/go/challenges/rest_example/internal/application/product/fetch_products/mock"
	"github.com/playground/projects/go/challenges/rest_example/internal/domain/product"
	mock2 "github.com/playground/projects/go/challenges/rest_example/internal/infrastructure/product/repository/mock"
	"github.com/stretchr/testify/assert"
	"net/http"
	"net/http/httptest"
	"testing"
)

func TestFetchAllProductsHandler_fetchAll(t *testing.T) {
	// AAA (Arrange-Act-Assert)
	// AAA: arrange
	var productResponse = fetch_products.ProductsResponse{
		Products: mock2.DataProducts,
	}
	var serviceMock = new(mock.GetProductsServiceMock)
	var handler = NewFetchAllProductsHandler(serviceMock)
	serviceMock.On("Execute").Return(&productResponse, nil)

	tests := []struct {
		name    string
		want    *product.Products
		wantErr bool
	}{
		{
			name:    "Get all products",
			want:    &mock2.DataProducts,
			wantErr: false,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			// AAA: act
			// Create a mock context
			w := httptest.NewRecorder()
			c, _ := gin.CreateTestContext(w)
			// execute handler
			handler.GetHandlerFunc()(c)
			//listID := "123"
			//c.AddParam("list_id", listID)

			// AAA: assert
			assert.Equal(t, http.StatusOK, w.Code)

			var actual HttpProductsResponse
			err := json.Unmarshal(w.Body.Bytes(), &actual)
			assert.NoError(t, err, "Failed to unmarshal JSON response")

			// Make assertions on the parsed response
			// For example, check the length, content, etc.
			assert.Len(t, actual.Items, 2)
			assert.Equal(t, actual.Skip, 0)
			assert.Equal(t, actual.Limit, 10)
			assert.Equal(t, actual.Total, 2)
			// assert.Equal(t, tt.want, actual)

			//expectedResponse := `{
			//	"ad": {
			//	  "list_id": "123"
			//	}
			//}`
			//assert.JSONEq(t, expectedResponse, string(w.Body.Bytes()))
		})
	}
}
