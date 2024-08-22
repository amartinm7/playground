package main

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"github.com/playground/projects/go/challenges/book_store/internal/books/fetchall"
)

func main() {
	ginEngine := gin.Default()
	fetchall.InitDependencies(*ginEngine)
	fmt.Sprintf("Server is running on port %d", 8080)
	ginEngine.Run("localhost:8080")
}
