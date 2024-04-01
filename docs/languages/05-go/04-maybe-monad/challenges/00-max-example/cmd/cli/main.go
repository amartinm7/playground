package main

import "fmt"

// Función genérica Max encuentra el máximo entre dos valores del mismo tipo.
func Max[T int | float64 | string](a, b T) T {
	if a > b {
		return a
	}
	return b
}

func main() {
	// Usamos la función Max con diferentes tipos de datos.
	fmt.Println(Max(3, 7))        // Output: 7
	fmt.Println(Max(3.14, 1.618)) // Output: 3.14
	fmt.Println(Max("a", "b"))    // Output: b
}
