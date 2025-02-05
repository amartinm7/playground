package fetchall

import (
	"github.com/gin-gonic/gin"
	"github.com/playground/projects/go/challenges/book_store/internal/books/fetchall/infrastructure/handler"
	"github.com/playground/projects/go/challenges/book_store/internal/books/fetchall/infrastructure/http"
)

func InitDependencies(engine gin.Engine) {
	http.NewRouter(engine, handler.NewController().GetHandlerFunc())
}
