package ginhandler

import "github.com/gin-gonic/gin"

type Handler interface {
	GetHandlerFunc() gin.HandlerFunc
}
