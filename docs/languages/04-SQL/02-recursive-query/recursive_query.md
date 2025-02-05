# Recursive query

![recursive_query.png](_img%2Frecursive_query.png)

```sql
WITH RECURSIVE descendientes AS (
    SELECT id, name, child_of
    FROM books
    WHERE child_of = 14
UNION ALL
    SELECT cat.id, cat.name, cat.child_of
    FROM books cat
          JOIN descendientes d ON cat.child_of = d.id
)
SELECT * FROM descendientes;
```
