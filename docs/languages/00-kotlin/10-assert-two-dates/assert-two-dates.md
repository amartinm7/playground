# assert two dates

```kotlin
import java.sql.Timestamp
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

Assertions.assertThat((it["updated_at"] as Timestamp).toOffsetDateTime())
    .isCloseTo(NOW, Assertions.within(3, ChronoUnit.MINUTES))
```