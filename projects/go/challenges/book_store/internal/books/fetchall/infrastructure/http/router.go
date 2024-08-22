package http

import "github.com/gin-gonic/gin"

type router struct {
	engine  gin.Engine
	handler gin.HandlerFunc
}

func NewRouter(engine gin.Engine, handler gin.HandlerFunc) router {
	r := router{engine: engine, handler: handler}
	r.RegisterRoutes()
	return r
}

func (r router) RegisterRoutes() {
	r.engine.GET("/api/books", r.handler)
}
