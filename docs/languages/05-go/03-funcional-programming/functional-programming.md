# Handling errors

functores, monoides, monadas

Functional programming in Go involves the following concepts:
- First-class and higher-order functions: functions can be assigned to variables, passed as arguments to other functions, or returned from other functions.
- Pure functions: functions that do not modify their input and always return the same output for the same input.
- Recursion: a function that calls itself.
- Lazy evaluation: a technique that delays the evaluation of an expression until its value is needed.
- Type system: Go's type system supports functional programming concepts.
- Referential transparency: an expression can be replaced with its value without changing the behavior of the program.
- Data structures: functional programming in Go can be done with immutable data structures.

## functors

A functor is a type that implements the `map` function. The `map` function applies a function to each element of the functor and returns a new functor with the transformed elements. In Go, we can define functors using interfaces and methods.

```go
package main

import "fmt"

// Definimos un struct IntSlice que actuará como nuestro functor.
type IntSlice struct {
    data []int
}

// Método Map para IntSlice.
func (is IntSlice) Map(f func(int) int) IntSlice {
    newData := make([]int, len(is.data))
    for i, v := range is.data {
        newData[i] = f(v)
    }
    return IntSlice{data: newData}
}

func main() {
    // Creamos una instancia de IntSlice con algunos valores iniciales.
    nums := IntSlice{data: []int{1, 2, 3, 4, 5}}

    // Definimos una función para duplicar un entero.
    double := func(x int) int {
        return x * 2
    }

    // Aplicamos la función double a cada elemento de nums usando el método Map.
    doubledNums := nums.Map(double)

    // Imprimimos el resultado.
    fmt.Println(doubledNums.data) // Output: [2 4 6 8 10]
}
```
## Monoids

A monoid is a type that implements the `mempty` and `mappend` functions. The `mempty` function returns the identity element of the monoid, and the `mappend` function combines two monoid values into a single value. Monoids are useful for combining values in a generic way.

```go
package main

import "fmt"

// Elemento neutro para la concatenación de strings.
const NeutralString = ""

// Definimos una estructura StringConcat que representa el monoide para la concatenación de strings.
type StringConcat struct{}

// Método de la estructura StringConcat para concatenar dos strings.
func (s StringConcat) Combine(a, b string) string {
    return a + b
}

func (s StringConcat) Mempty() string {
    return NeutralString
}

func main() {
    // Creamos una instancia de StringConcat (no necesitamos realmente instanciarla para este ejemplo).
    strConcat := StringConcat{}

    // Ejemplo de uso de la concatenación como monoide.
    result := strConcat.Combine(strConcat.Combine("Hola, ", "mundo"), "!")
    fmt.Println(result) // Output: Hola, mundo!

    // Verificamos la propiedad del elemento neutro.
    resultWithNeutral := strConcat.Combine(strConcat.Mempty(), "Hola")
    fmt.Println(resultWithNeutral) // Output: Hola
}
```

## Monads

A monad is a type that implements the `unit` and `bind` functions. The `unit` function wraps a value in the monad, and the `bind` function applies a function that returns a monad to the value inside the monad. Monads are useful for chaining computations and handling side effects.

```go
package main

import "fmt"

// Maybe representa un valor opcional que puede estar presente o ausente.
type Maybe struct {
    value interface{}
}

// Función de constructor para Maybe con un valor presente.
func Just(val interface{}) Maybe {
    return Maybe{value: val}
}

// Función de constructor para Maybe con un valor ausente.
func Nothing() Maybe {
    return Maybe{value: nil}
}

// Método Bind para la monada Maybe.
func (m Maybe) Bind(f func(interface{}) Maybe) Maybe {
    if m.value == nil {
        return Nothing()
    }
    return f(m.value)
}

func main() {
    // Definimos una función que duplica un entero.
    double := func(x interface{}) Maybe {
        if val, ok := x.(int); ok {
            return Just(val * 2)
        }
        return Nothing()
    }

    // Creamos una instancia de Maybe con un valor presente.
    maybeValue := Just(5)

    // Aplicamos la función double a la instancia de Maybe usando Bind.
    doubledValue := maybeValue.Bind(double)

    // Imprimimos el resultado.
    fmt.Println(doubledValue) // Output: {10}
}
```
### All together

En este ejemplo:

Maybe se utiliza como una monada para manejar valores opcionales.
SumMonoid actúa como un monoide para calcular la suma de enteros.
Lista representa una lista opcional de enteros y se utiliza como un functor para mapear una función sobre los elementos de la lista.
Este ejemplo muestra cómo las monadas, los monoides y los functores pueden trabajar juntos para manejar valores opcionales, realizar operaciones sobre ellos y combinar resultados de manera segura y flexible.

```go
package main

import "fmt"

// Maybe representa un valor opcional que puede estar presente o ausente.
type Maybe struct {
    value interface{}
}

// Función de constructor para Maybe con un valor presente.
func Just(val interface{}) Maybe {
    return Maybe{value: val}
}

// Función de constructor para Maybe con un valor ausente.
func Nothing() Maybe {
    return Maybe{value: nil}
}

// Método Bind para la monada Maybe.
func (m Maybe) Bind(f func(interface{}) Maybe) Maybe {
    if m.value == nil {
        return Nothing()
    }
    return f(m.value)
}

// SumMonoid representa un monoide para la suma de enteros.
type SumMonoid struct{}

// Método Combine para SumMonoid.
func (m SumMonoid) Combine(a, b int) int {
    return a + b
}

// Método Neutral para SumMonoid.
func (m SumMonoid) Neutral() int {
    return 0
}

// Lista representa una lista opcional de enteros.
type Lista struct {
    maybeValues Maybe
}

// Método Map para Lista.
func (l Lista) Map(f func(int) int) Lista {
    mappedMaybe := l.maybeValues.Bind(func(val interface{}) Maybe {
        if list, ok := val.([]int); ok {
            mappedList := make([]int, len(list))
            for i, v := range list {
                mappedList[i] = f(v)
            }
            return Just(mappedList)
        }
        return Nothing()
    })
    return Lista{maybeValues: mappedMaybe}
}

// Método Fold para Lista.
func (l Lista) Fold(m Monoid) int {
    values := l.maybeValues.value.([]int)
    result := m.Neutral()
    for _, v := range values {
        result = m.Combine(result, v)
    }
    return result
}

func main() {
    // Creamos una lista con algunos valores.
    list := Lista{maybeValues: Just([]int{1, 2, 3, 4, 5})}

    // Definimos una función para duplicar un entero.
    double := func(x int) int {
        return x * 2
    }

    // Aplicamos la función double a cada elemento de la lista usando Map.
    doubledList := list.Map(double)

    // Calculamos la suma de los elementos de la lista usando Fold y el monoide SumMonoid.
    sum := doubledList.Fold(SumMonoid{})

    // Imprimimos el resultado.
    fmt.Println(sum) // Output: 30
}
```

### All together: book example

```go
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
	libro.Precio = nuevoPrecio
	return Just(libro)
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
```