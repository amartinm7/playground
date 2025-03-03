🚀 RDS Tuning en PostgreSQL - Notas de Formación

# 1. SQL y el Plan de Ejecución

SQL es un lenguaje declarativo: especificamos qué queremos obtener, pero no cómo hacerlo.
El motor de PostgreSQL determina el mejor método de ejecución a través del plan de ejecución, evaluando:

✅ Volumen de datos en las tablas  
✅ Índices disponibles  
✅ Tablas de estadísticas (`ANALYZE`)

# 2. Plan de Ejecución en PostgreSQL y su Caché

PostgreSQL calcula el plan de ejecución al ejecutar una consulta por primera vez en una sesión.  
Dicho plan se cachea y reutiliza en las siguientes ejecuciones dentro de la misma sesión.

**Problema:** Si los datos cambian drásticamente, el plan puede volverse ineficiente.

**Solución:**

✔ Usar un connection pool (`PgBouncer`, `RDS Proxy`) para evitar sesiones largas con planes desactualizados.  
✔ Actualizar las estadísticas periódicamente:

```sql
ANALYZE my_table;
```
✔ Forzar reoptimización de planes:

```sql
DISCARD PLANS;
```

# 3. Mantenimiento de PostgreSQL: Auto-Vacuum y Estadísticas

PostgreSQL cuenta con un **Auto-Vacuum** que se ejecuta regularmente para:

🔹 Actualizar estadísticas (`ANALYZE`) y mejorar los planes de ejecución.  
🔹 Liberar espacio (`VACUUM`) y reducir fragmentación.  
🔹 Manejar visibilidad de datos (`MVCC`).

## Optimización del Auto-Vacuum

Si hay mucha escritura, el auto-vacuum podría no ejecutarse lo suficientemente rápido. Opciones:

✔ Reducir el umbral de activación:

```sql
ALTER TABLE my_table SET (autovacuum_vacuum_scale_factor = 0.01);
```
✔ Ejecutar `VACUUM ANALYZE` en horarios estratégicos.  
✔ Usar `pg_repack` para reorganizar tablas sin bloquearlas.

# 4. Índices y Caché en PostgreSQL

PostgreSQL almacena en memoria los datos más accedidos usando:

✅ `shared_buffers` (caché de páginas de datos).  
✅ `work_mem` (memoria para ordenamientos y joins).

## Tipos de Índices en PostgreSQL

📌 **B-Tree** (default, ideal para búsquedas exactas y ordenaciones).  
📌 **GIN** (para búsquedas en arrays y full-text search).  
📌 **GiST** (datos espaciales con PostGIS).  
📌 **BRIN** (para tablas grandes con datos correlacionados).

## Problemas Comunes en Índices:

⚠ Alto consumo de **IOPS** → Puede indicar falta de índices o una mala estrategia de caché.  
⚠ Consultas que usan **Seq Scan** en lugar de **Index Scan** → Revisar con `EXPLAIN ANALYZE`.

# 5. Optimización de IOPS y Patrón de Escritura/Lectura

Si ves un alto consumo de IOPS en RDS, revisa:

✔ Falta de memoria (`shared_buffers`).  
✔ Uso incorrecto de índices (`EXPLAIN ANALYZE`).  
✔ Exceso de escrituras (`VACUUM`, `fillfactor`).

## Distribución Ideal de IOPS

📊 En bases de datos transaccionales (**OLTP**), un **70% lecturas / 30% escrituras** suele ser óptimo.  
📊 Bases de datos analíticas (**OLAP**) pueden requerir **90% lecturas**.

# 6. Monitoreo en CloudWatch

Para un análisis preciso: ver la última hora y ajustar el período a **1 minuto** para evitar gráficas suavizadas.

🔹 **CPUUtilization**: Mantener por debajo del **25%**.  
🔹 **ReadLatency**: Si es alto, revisar índices y memoria.  
🔹 **WriteLatency**: Si es alto, evaluar la estrategia de escrituras y `VACUUM`.  
🔹 **TotalIOPS**: Identificar picos de consumo.  
🔹 **EBSIOBalance%**: Revisar si la instancia ha alcanzado su límite de IOPS.

# 7. Comandos Útiles para Tuning

### Ver el plan de ejecución de una consulta

```sql
EXPLAIN ANALYZE SELECT * FROM my_table WHERE column = 'value';
```

### Actualizar estadísticas y optimizar planes

```sql
VACUUM ANALYZE my_table;
```

### Monitorear consultas más costosas

```sql
SELECT * FROM pg_stat_statements ORDER BY total_exec_time DESC LIMIT 10;