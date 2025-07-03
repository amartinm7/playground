package main

import (
	"fmt"
)

type Book struct {
	ID    int
	Title string
	Price float64
}

func (book Book) updatePrice(price float64) Book {
	return Book{ID: book.ID, Title: book.Title, Price: price}
}

type Maybe[T any] struct {
	value T
	err   error
}

func Just[T any](val T) Maybe[T] {
	return Maybe[T]{value: val}
}

func Nothing[T any](err error) Maybe[T] {
	return Maybe[T]{err: err}
}

func (m Maybe[T]) OnError(f func(error) error) Maybe[T] {
	if m.err != nil {
		return Nothing[T](f(m.err))
	}
	return m
}

func (m Maybe[T]) OnSuccess(f func(T) Maybe[T]) Maybe[T] {
	if m.err != nil {
		return m
	}
	return f(m.value)
}

type Repository[T any] struct {
}

func (r *Repository[T]) Save(book T) Maybe[T] {
	fmt.Println("El libro se guardó en la base de datos, si no se guarda puede retornar Nothing(err)", book)
	return Just(book)
}

func (r *Repository[T]) FindBy(ID int, book T) Maybe[T] {
	if ID == 1 {
		return Just(book)
	}
	return Nothing[T](fmt.Errorf("no se encontró el libro %d", ID))
}

func main() {
	var defaultBook = Book{ID: 1, Title: "El nombre del viento", Price: 20.0}
	repository := Repository[Book]{}
	resultant1 := repository.FindBy(1, defaultBook).OnSuccess(
		func(this Book) Maybe[Book] {
			return Just(this.updatePrice(100))
		}).OnSuccess(
		func(this Book) Maybe[Book] { return repository.Save(this) }).OnError(
		func(thisError error) error {
			return thisError
		})

	if resultant1.err != nil {
		fmt.Println("Error:", resultant1.err)
	}

	resultant2 := repository.FindBy(2, defaultBook).OnSuccess(
		func(this Book) Maybe[Book] {
			return Just(this.updatePrice(100))
		}).OnSuccess(
		func(this Book) Maybe[Book] {
			return repository.Save(this)
		}).OnError(
		func(thisError error) error {
			return thisError
		})

	if resultant2.err != nil {
		fmt.Println("Error:", resultant2.err)
	}
}
