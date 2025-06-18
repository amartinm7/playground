# Repository Pattern in DDD: When **NOT** to Use It

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
