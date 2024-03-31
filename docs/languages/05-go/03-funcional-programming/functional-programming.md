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