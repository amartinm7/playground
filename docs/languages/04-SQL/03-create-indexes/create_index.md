# creating indexes

It's not only important to create tables and columns in a database, but also to create indexes. Indexes are used to speed up the retrieval of rows from a table. They are used to quickly locate data without having to search every row in a database table every time a database table is accessed.

Indexes are created using the `CREATE INDEX` statement. The syntax for creating an index is as follows:

```sql 
CREATE INDEX index_name
ON table_name (column1, column2, ...);
```

Un índice es una estructura de datos auxiliar que se construye sobre una tabla existente. Su propósito principal es acelerar el proceso de búsqueda y recuperación de datos. Imagina un índice de un libro: te permite encontrar rápidamente una palabra o frase sin tener que leer todo el libro. De manera similar, un índice en una base de datos te permite encontrar registros específicos de forma mucho más eficiente.

## Partial index

A partial index is an index that includes only a subset of the rows in a table. It is created using the `WHERE` clause in the `CREATE INDEX` statement. The syntax for creating a partial index is as follows:

```sql
CREATE INDEX table_index_name
    ON my_table_name (id)
    WHERE (processed = false);
```

`create index tabble`: Esta parte inicia la creación del índice y le da el nombre "tabble" al índice. Es importante destacar que el nombre del índice no debe coincidir con el nombre de la tabla.
`on tabble (id)`: Especifica que el índice se creará en la tabla tabble y que se indexará la columna id.
`where (processed = false)`: Esta es la parte clave que define un índice parcial. Solo se indexarán las filas de la tabla tabble donde el valor de la columna processed sea false.

El crear un indice implica que se crea una estructura B-Tree, Hash, Bitmaps solo con los campos indexados.

¿Qué es un índice parcial?

Un índice parcial es un tipo de índice que solo incluye una subconjunto de las filas de una tabla. En este caso, el índice solo incluirá las filas donde processed es false.

### Beneficios de los índices parciales:

Reducción del tamaño del índice: Al indexar solo un subconjunto de las filas, el tamaño del índice se reduce significativamente.
Mejora del rendimiento de las consultas: Los índices parciales pueden mejorar el rendimiento de las consultas que filtran los datos basados en la condición del WHERE. En este caso, las consultas que filtran por processed = false se beneficiarán de este índice.
Uso eficiente del espacio de almacenamiento: Al ocupar menos espacio en disco, los índices parciales pueden liberar espacio para otros datos.
Ejemplo de uso:

Imagina una tabla que almacena tareas. La columna processed indica si una tarea ya ha sido procesada. Un índice parcial en id con la condición where (processed = false) sería útil para las consultas que buscan tareas pendientes de procesamiento.


### ¿Cómo sabe una consulta qué índice utilizar? 

El proceso de determinar qué índice utilizar en una consulta es una tarea compleja que se delega al optimizador de consultas del sistema de gestión de bases de datos (SGBD).

El optimizador de consultas es un componente fundamental de cualquier SGBD y su función principal es determinar el plan de ejecución más eficiente para una consulta dada. Este plan incluye, entre otras cosas, qué índices utilizar para acceder a los datos de la manera más rápida.

Factores que influyen en la elección del índice:
- Condición WHERE: El optimizador analiza la cláusula WHERE de la consulta para identificar las columnas que se están filtrando. Si existe un índice en esas columnas, es muy probable que se utilice.
- Tipo de operación: Operaciones como JOIN, GROUP BY y ORDER BY también influyen en la elección del índice. Por ejemplo, un índice en una columna utilizada en un ORDER BY puede acelerar la ordenación de los resultados.
- Cardinalidad de las columnas: La cardinalidad de una columna se refiere al número de valores distintos que contiene. Los índices en columnas con alta cardinalidad suelen ser más eficientes.
- Tamaño de las tablas y los índices: El tamaño de la tabla y del índice también influye en la decisión del optimizador.
- Estadísticas de la tabla: El SGBD almacena estadísticas sobre la tabla, como la distribución de los valores en cada columna. Estas estadísticas ayudan al optimizador a estimar el costo de diferentes planes de ejecución.

### ¿Cómo funciona el proceso de selección?

- Análisis de la consulta: El optimizador analiza la consulta SQL y crea un árbol de expresiones que representa la lógica de la consulta.
- Generación de planes de ejecución: El optimizador genera múltiples planes de ejecución posibles, cada uno utilizando diferentes combinaciones de índices y operaciones de acceso a datos.
- Estimación de costos: Para cada plan de ejecución, el optimizador estima el costo asociado, que generalmente se mide en términos del número de filas que deben ser leídas y procesadas.
- Selección del mejor plan: El optimizador selecciona el plan de ejecución con el costo estimado más bajo.

```sql
SELECT * FROM usuarios WHERE id = 123;
```

El optimizador probablemente utilice el índice en la columna id para buscar rápidamente el registro con el ID 123, ya que el índice está optimizado para búsquedas exactas por ese campo.

### indices complejos

Para este caso 

```sql
SELECT * FROM usuarios WHERE id = 123 and name = "xx" and surname = "dff" and city = "dsfsdd"
```

si tenemos un indice complejo creado como con las columnas en el mismo orden que la query anterior, el optimizador de la base de datos podrá utilizar este indice para realizar la busqueda de manera más eficiente.

```sql
CREATE INDEX idx_usuarios_id_name_surname_city ON usuarios (id, name, surname, city);
```

si tenemos tantos indices como columnas, la query anterior tiene que usar un indice por cada columna cuando ejecuta la query, lo que puede ser ineficiente.

### EXPLAIN vs EXPLAIN ANALYZE

`EXPLAIN` es una instrucción que muestra el plan de ejecución de una consulta SQL. Muestra cómo la base de datos planea ejecutar la consulta y qué índices y operaciones utilizará.

`EXPLAIN ANALYZE` es similar a `EXPLAIN`, pero además de mostrar el plan de ejecución, también ejecuta la consulta y muestra el tiempo que tardó en ejecutarse.

```sql
EXPLAIN * FROM usuarios WHERE id = 123;
```
El explain muestra si usa o no indices, la ejecución de la query y el tiempo que tarda en ejecutarla.
Si tenemos indices y no aparecen en la query entonces tenemos que revisar la query y los indices para ver si se pueden optimizar.

```sql

```bash
Index Scan using usuarios_id_idx on usuarios  (cost=0.29..8.30 rows=1 width=32)
  Index Cond: (id = 123)
```