# Repository Pattern in DDD: When **NOT** to Use It

**¿Qué es el Patrón de Repositorio?**

El patrón de repositorio actúa como un intermediario entre la capa de dominio y la capa de datos, encapsulando la lógica de acceso a los datos y las operaciones realizadas sobre ellos. Esto permite que la lógica del dominio permanezca agnóstica respecto a los mecanismos de persistencia, como `bases de datos` o `servicios externos`.

**Implementación del Patrón de Repositorio**

Un repositorio debe corresponder a una raíz de agregado específico en tu modelo de dominio. Aquí hay algunas recomendaciones para implementarlo:
Enfoque en el Agregado: Cada repositorio debe manejar un solo agregado, asegurando así que las reglas de negocio se mantengan intactas

1.- Interfaz Clara: Las interfaces de los repositorios deben centrarse en operaciones esenciales (agregar, eliminar, buscar), evitando complejidades innecesarias

2.- Métodos Específicos del Dominio: En lugar de exponer operaciones crudas de base de datos, los métodos deben reflejar las operaciones del dominio, como findByUserId o addCommentToPost

## Quick Reminder – What Is a Repository?
- Manages **Aggregate Roots** from the domain
- Exposes only **minimal** persistence/query operations (`findById`, `save`, etc.)
- Hides **infrastructure details** from higher layers
- Uses the **ubiquitous language** (business intent, not DB/API details)

---

## Example 1 – `FFRepository`

```kotlin
interface FFRepository {
  fun isAnyEnabled(): Boolean
}
```

**Discussion questions**
- Does it manage an Aggregate Root?
- Is it really about persistence or just config lookup?
- Would `FFRepositoryProvider` or `FFRepositoryService` be a better name?

---

## Example 2 – `AnyGatewayRepository`

```kotlin
interface AnyGatewayRepository {
  fun copyAd(
    adId: AdId,
    destinationTable: Table,
  )
  fun delete(adId: AdId)
}
```

**Discussion questions**
- Does it return or persist an aggregate?
- Does it mix infrastructure operations (copy/delete between tables)?
- Better alternative: `AnyGateway`, `AnyGatewayClient`?

## Example 3 – `BookRepository`

```kotlin
interface BookRepository {
  fun persist(offer: Book): Offer
  fun findBy(offerId: BookId): Offer?
  fun findBy(userId: UserId): List<Offer>
  fun findBy(adId: AdId): List<Offer>
  // this method is suspicious
  fun findByTop100UserIdOrderingByLastTimeUpdatedDesc(userId: UserId): List<Book>

}
```

**Why it’s a good Repository**
- ✅ Manages a clear **Aggregate Root** (`Book`)
- ✅ Exposes only essential persistence and query operations
- 🤔 Specific queries can be delegated to a Criteria, or Specification pattern, o use more meaninful names for the business.
---

## Quick Evaluation Criteria
✅ Does it manage an **Aggregate Root**?  
✅ Does it actually **persist or retrieve** the AR?  
✅ Does it use **domain language**, avoiding technical details?  
✅ Does it avoid **infrastructure logic** inside the repository?
