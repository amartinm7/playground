package product

import "errors"

var InvalidProductId = errors.New("invalid productId: nil")

var InvalidProductDesc = errors.New("invalid description: nil")

type Product struct {
	Id                 int
	Title              string
	Description        string
	Price              int
	DiscountPercentage float32
	Rating             float32
	Stock              int
	Brand              string
	Category           string
	Thumbnail          string
	Images             []string
}

type Products struct {
	Items []Product
	Total int
	Skip  int
	Limit int
}
