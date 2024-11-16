#

## entities

- sede olimplica
- cities
- countries
- atletas
- equipos
- eventos deportivos
- inscripciones en diferentes disciplinas
- resultados


## modelo conceptual E/R

## modelo logico relacional

- COUNTRY (id, nombre)
- CITY (id, nombre, pais)
- ANFITRION (OLIMPIC_GAMES_ID, CITY_ID): (1,PARIS), (1,LILE), (1,LYON), (1,BURDEOS)
- 
- ENUM SEASON (VERANO, INVIERNO)

- OLIMPIC_GAMES (id, nombre, año, SEASON_VALUE) 

- TEAM (id, nombre, pais, ciudad)
- ATHLETE (id, nombre-completo, edad, sexo, peso, altura) -- falta pais y ciudad
- EVENT (id, descripcion) -- (maratón, media maratón, 100 metros lisos, etc.
- EVENT_X_ATHLETE (EVENT_ID, ATHLETE_ID) --
- SPORT (id, descripcion) -- (atletismo, natación, ciclismo, etc.)