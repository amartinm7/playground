package main

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"github.com/playground/projects/go/challenges/rest_example/internal/infrastructure/kit/server/ginhandler"
	"github.com/playground/projects/go/challenges/rest_example/internal/infrastructure/product"
)

func main() {
	fmt.Println("Starting engine")
	engine := gin.Default()
	handler := product.InitFetchAllProductsHandler()
	NewRouter(engine).init(handler)
}

type router struct {
	engine *gin.Engine
}

func NewRouter(engine *gin.Engine) *router {
	return &router{engine: engine}
}

func (r *router) init(handler ginhandler.Handler) {
	urlPrefix := "/api/product"
	r.engine.GET(urlPrefix, handler.GetHandlerFunc())
	r.engine.Run() // listen and serve on 0.0.0.0:8080 (for windows "localhost:8080")
}
