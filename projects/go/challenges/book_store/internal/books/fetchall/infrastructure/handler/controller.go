package handler

import "github.com/gin-gonic/gin"

type controller struct {
}

func NewController() controller {
	return controller{}
}
func (c controller) GetHandlerFunc() gin.HandlerFunc {
	return func(ctx *gin.Context) {
		ctx.JSON(200, gin.H{"message": "Hello, World!"})
	}
}
