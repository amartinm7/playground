package main

import (
	"errors"
	"fmt"
)

// Libro representa un libro con su ID, título y precio.
type Libro struct {
	ID     int
	Titulo string
	Precio float64
}

// Maybe representa un valor opcional que puede estar presente o ausente.
type Maybe struct {
	value interface{}
	err   error
}

// Función de constructor para Maybe con un valor presente.
func Just(val interface{}) Maybe {
	return Maybe{value: val}
}

// Función de constructor para Maybe con un valor ausente.
func Nothing(err error) Maybe {
	return Maybe{value: nil, err: err}
}

// Método Bind para la monada Maybe.
func (m Maybe) Bind(f func(interface{}) Maybe) Maybe {
	if m.err != nil {
		return Nothing(m.err)
	}
	if m.value == nil {
		return Nothing(errors.New("El valor es nulo"))
	}
	return f(m.value)
}

// Método OnError para manejar el error.
func (m Maybe) OnError(f func(error) error) Maybe {
	if m.err != nil {
		return Nothing(f(m.err))
	}
	return m
}

// Método OnSuccess para manejar el éxito.
func (m Maybe) OnSuccess(f func(interface{}) Maybe) Maybe {
	if m.err != nil {
		return m
	}
	if m.value == nil {
		return Nothing(errors.New("El valor es nulo"))
	}
	return f(m.value)
}

// Método Save para guardar el libro en la base de datos.
func (m Maybe) Save() Maybe {
	if m.err != nil {
		return m
	}
	if m.value == nil {
		return Nothing(errors.New("El valor es nulo"))
	}
	libro, ok := m.value.(Libro)
	if !ok {
		return Nothing(errors.New("El valor no es un libro"))
	}
	fmt.Printf("El libro %s se guardó en la base de datos con el precio actualizado: %.2f\n", libro.Titulo, libro.Precio)
	return Just(libro)
}

// Función que busca un libro por su ID en la base de datos.
func FindBy(id int) Maybe {
	// Simulamos una llamada a la base de datos
	// Aquí iría el código real para buscar el libro por su ID
	// En este ejemplo, suponemos que encontramos el libro con ID = 1
	if id == 1 {
		libro := Libro{ID: 1, Titulo: "El nombre del viento", Precio: 20.0}
		return Just(libro)
	}
	// Si no se encuentra el libro, retornamos un Maybe vacío y un error
	return Nothing(errors.New("No se encontró el libro"))
}

// Función que actualiza el precio del libro.
func ActualizarPrecio(libro Libro, nuevoPrecio float64) Maybe {
	return Just(Libro{ID: libro.ID, Titulo: libro.Titulo, Precio: nuevoPrecio})
}

func main() {
	id := 1
	nuevoPrecio := 25.0

	resultado := FindBy(id).
		OnSuccess(func(val interface{}) Maybe {
			libro, ok := val.(Libro)
			if !ok {
				return Nothing(errors.New("El valor no es un libro"))
			}
			return ActualizarPrecio(libro, nuevoPrecio)
		}).
		Save().
		OnError(func(err error) error {
			return err
		})

	if resultado.err != nil {
		fmt.Println("Error:", resultado.err)
	}
}
