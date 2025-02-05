package repository

import (
	"encoding/json"
	"fmt"
	"github.com/playground/projects/go/challenges/rest_example/internal/domain/product"
	"github.com/playground/projects/go/challenges/rest_example/internal/infrastructure/kit/server/httpclient"
	"io"
	"log"
)

type HttpApiProductResponse struct {
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

type HttpApiProductsResponse struct {
	Items []HttpApiProductResponse `json:"products"`
	Total int                      `json:"total"`
	Skip  int                      `json:"skip"`
	Limit int                      `json:"limit"`
}

type ProductRepository interface {
	FetchAll() (*product.Products, error)
}

type ProductRepositoryImpl struct {
	httpClient httpclient.WebClient
}

func NewProductRepositoryImpl(httpClient httpclient.WebClient) *ProductRepositoryImpl {
	return &ProductRepositoryImpl{
		httpClient: httpClient,
	}
}

func (repository *ProductRepositoryImpl) FetchAll() (*product.Products, error) {
	var productsResponse = &HttpApiProductsResponse{}
	resp, err := repository.httpClient.Get("https://dummyjson.com/products")
	if err != nil {
		return nil, err
	}
	defer resp.Body.Close()

	responseData, err := io.ReadAll(resp.Body)
	if err != nil {
		log.Fatal(err)
	}
	err = json.Unmarshal(responseData, productsResponse)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(productsResponse)
	return mapProductsJsonToDomain(*productsResponse), nil
}

func mapProductJsonToDomain(productResponse []HttpApiProductResponse) []product.Product {
	productList := make([]product.Product, 0)
	for _, item := range productResponse {
		product := product.Product{Id: item.Id, Title: item.Title, Description: item.Description, Price: item.Price, DiscountPercentage: item.DiscountPercentage, Rating: item.Rating, Stock: item.Stock, Brand: item.Brand, Category: item.Category, Thumbnail: item.Thumbnail, Images: item.Images}
		productList = append(productList, product)
	}
	return productList
}

func mapProductsJsonToDomain(productsResponse HttpApiProductsResponse) *product.Products {
	products := product.Products{
		Items: mapProductJsonToDomain(productsResponse.Items),
		Total: productsResponse.Total, Skip: productsResponse.Skip, Limit: productsResponse.Limit,
	}
	return &products
}
