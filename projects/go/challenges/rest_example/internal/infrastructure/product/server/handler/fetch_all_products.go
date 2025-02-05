package handler

import (
	"github.com/gin-gonic/gin"
	"github.com/playground/projects/go/challenges/rest_example/internal/application/product/fetch_products"
	"github.com/playground/projects/go/challenges/rest_example/internal/domain/product"
	"github.com/playground/projects/go/challenges/rest_example/internal/infrastructure/kit/server/ginhandler"
	"net/http"
)

type fetchAllProductsHandler struct {
	service fetch_products.GetProductsServiceInterface
}

func NewFetchAllProductsHandler(service fetch_products.GetProductsServiceInterface) ginhandler.Handler {
	return &fetchAllProductsHandler{service}
}

func (handler *fetchAllProductsHandler) GetHandlerFunc() gin.HandlerFunc {
	return func(ctx *gin.Context) {
		productsResponse, _ := handler.service.Execute()
		ctx.JSON(http.StatusOK, mapHttpProductsResponse(productsResponse.Products))
	}
}

type HttpProductResponse struct {
	Id                 int      `json:"id"`
	Title              string   `json:"title"`
	Description        string   `json:"description"`
	Price              int      `json:"price"`
	DiscountPercentage float32  `json:"discountPercentage"`
	Rating             float32  `json:"rating"`
	Stock              int      `json:"stock"`
	Brand              string   `json:"brand"`
	Category           string   `json:"category"`
	Thumbnail          string   `json:"thumbnail"`
	Images             []string `json:"images"`
}

type HttpProductsResponse struct {
	Items []HttpProductResponse `json:"products"`
	Total int                   `json:"total"`
	Skip  int                   `json:"skip"`
	Limit int                   `json:"limit"`
}

func mapHttpProductResponse(items []product.Product) []HttpProductResponse {
	var s = make([]HttpProductResponse, 0)
	for _, item := range items {
		s = append(s, HttpProductResponse{
			item.Id,
			item.Title,
			item.Description,
			item.Price,
			item.DiscountPercentage,
			item.Rating,
			item.Stock,
			item.Brand,
			item.Category,
			item.Thumbnail,
			item.Images,
		})
	}
	return s
}
func mapHttpProductsResponse(pr product.Products) HttpProductsResponse {
	return HttpProductsResponse{
		mapHttpProductResponse(pr.Items), pr.Total, pr.Skip, pr.Limit,
	}
}
