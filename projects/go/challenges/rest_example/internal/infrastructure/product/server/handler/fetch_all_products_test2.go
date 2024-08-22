package handler

//import (
//	"github.com/gin-gonic/gin"
//	"github.com/playground/projects/go/challenges/rest_example/internal/application/product/fetch_products/mock"
//	"github.com/playground/projects/go/challenges/rest_example/internal/domain/product"
//	mock2 "github.com/playground/projects/go/challenges/rest_example/internal/infrastructure/product/repository/mock"
//	"net/http/httptest"
//	"reflect"
//	"testing"
//)
//
//func TestFetchAllProductsHandler_fetchAll_(t *testing.T) {
//	var serviceMock = new(mock.GetProductsServiceMock)
//	var fetchAllProductsController = &FetchAllProductsHandlerImpl{
//		service: serviceMock,
//	}
//	tests := []struct {
//		name    string
//		want    *product.Products
//		wantErr bool
//	}{
//		{
//			name:    "Get all products",
//			want:    &mock2.DataProducts,
//			wantErr: false,
//		},
//	}
//	for _, tt := range tests {
//		t.Run(tt.name, func(t *testing.T) {
//			got, err := fetchAllProductsController.FetchAll()
//			if (err != nil) != tt.wantErr {
//				t.Errorf("fetchAll() error = %v, wantErr %v", err, tt.wantErr)
//				return
//			}
//			if !reflect.DeepEqual(got, tt.want) {
//				t.Errorf("fetchAll() got = %v, want %v", got, tt.want)
//			}
//		})
//	}
//}
