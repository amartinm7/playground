# deepwiki-open with local ollama

https://ollama.com/library/granite3.1-dense

download model: llama2

```bash
docker exec ollama ollama pull llama2
````

```
mkdir deepwiki-setup
cd deepwiki-setup
# Guarda aquí los archivos: docker-compose.yml, setup.sh, .env

chmod +x setup.sh
./setup.sh

# Ver el estado de los contenedores
docker-compose ps

# Ver logs en tiempo real
docker-compose logs -f deepwiki

# Descargar más modelos
docker exec ollama ollama pull codellama:7b  # Especializado en código

# Parar todo
docker-compose down

# Reiniciar solo DeepWiki
docker-compose restart deepwiki

docker-compose down
docker system prune -f  # Opcional: limpia imágenes no usadas

chmod +x debug.sh
./debug.sh

./setup.sh

# 1. Solo Ollama primero
docker-compose up -d ollama

# 2. Espera y verifica manualmente
sleep 30
curl http://localhost:11434/api/tags

# 3. Si responde, continúa con DeepWiki
docker-compose up -d deepwiki

# path models
./data/ollama/

#macOS: 
~/.ollama/models/

docker exec ollama ollama list

docker exec ollama ollama rm llama3.2:3b

# Ver espacio ocupado:
du -sh ./data/ollama/

# Dentro del contenedor
docker exec ollama du -sh /root/.ollama/

# ollama ps
docker exec ollama ps
```

podemos crear un proyecto de lang4chain y engancharlo con ollama ejecutando el modelo en local
