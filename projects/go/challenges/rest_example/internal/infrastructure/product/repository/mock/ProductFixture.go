package mock

import "github.com/playground/projects/go/challenges/rest_example/internal/domain/product"

var Iphone9 = product.Product{Id: 1,
	Title:              "iPhone 9",
	Description:        "An apple mobile which is nothing like apple",
	Price:              549,
	DiscountPercentage: 12.96,
	Rating:             4.69,
	Stock:              94,
	Brand:              "Apple",
	Category:           "smartphones",
	Thumbnail:          "https://i.dummyjson.com/data/products/1/thumbnail.jpg",
	Images: []string{
		"https://i.dummyjson.com/data/products/1/1.jpg",
		"https://i.dummyjson.com/data/products/1/2.jpg",
		"https://i.dummyjson.com/data/products/1/3.jpg",
		"https://i.dummyjson.com/data/products/1/4.jpg",
		"https://i.dummyjson.com/data/products/1/thumbnail.jpg",
	},
}

var IphoneX = product.Product{Id: 2,
	Title:              "iPhone X",
	Description:        "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
	Price:              899,
	DiscountPercentage: 17.94,
	Rating:             4.44,
	Stock:              34,
	Brand:              "Apple",
	Category:           "smartphones",
	Thumbnail:          "https://i.dummyjson.com/data/products/2/thumbnail.jpg",
	Images: []string{
		"https://i.dummyjson.com/data/products/2/1.jpg",
		"https://i.dummyjson.com/data/products/2/2.jpg",
		"https://i.dummyjson.com/data/products/2/3.jpg",
		"https://i.dummyjson.com/data/products/2/thumbnail.jpg",
	},
}

var DataProducts = product.Products{Items: []product.Product{Iphone9, IphoneX}, Total: 2, Limit: 10}

var JsonProduct = "{\"products\":[{\"id\":1,\"title\":\"iPhone 9\",\"description\":\"An apple mobile which is nothing like apple\",\"price\":549,\"discountPercentage\":12.96,\"rating\":4.69,\"stock\":94,\"brand\":\"Apple\",\"category\":\"smartphones\",\"thumbnail\":\"https://i.dummyjson.com/data/products/1/thumbnail.jpg\",\"images\":[\"https://i.dummyjson.com/data/products/1/1.jpg\",\"https://i.dummyjson.com/data/products/1/2.jpg\",\"https://i.dummyjson.com/data/products/1/3.jpg\",\"https://i.dummyjson.com/data/products/1/4.jpg\",\"https://i.dummyjson.com/data/products/1/thumbnail.jpg\"]},{\"id\":2,\"title\":\"iPhone X\",\"description\":\"SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...\",\"price\":899,\"discountPercentage\":17.94,\"rating\":4.44,\"stock\":34,\"brand\":\"Apple\",\"category\":\"smartphones\",\"thumbnail\":\"https://i.dummyjson.com/data/products/2/thumbnail.jpg\",\"images\":[\"https://i.dummyjson.com/data/products/2/1.jpg\",\"https://i.dummyjson.com/data/products/2/2.jpg\",\"https://i.dummyjson.com/data/products/2/3.jpg\",\"https://i.dummyjson.com/data/products/2/thumbnail.jpg\"]}],\"total\":2,\"skip\":0,\"limit\":10}"
