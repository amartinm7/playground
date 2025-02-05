# Rest-Example

## create a empty module

```bash
# create a folder
mkdir /challenges/rest_example
# init the go module. It creates a go.mod file inside
go mod init github.com/playground/projects/go/challenges/book_store
#
go mod tidy
```
Once it's done, you have a go.mod file inside the folder like this: 

```bash
module github.com/playground/projects/go/challenges/book_store

go 1.21.1
```

## execute application

Over the main root of the project /challenges/book_store

```bash
go run cmd/cli/main.go
# 
go run cmd/main.go
```

## compile and build the artifact

```bash
# build
go build ./...
# or
go build -o ./bin ./...
# or to generate a binary executable
go build -o ./bin/main cmd/cli/main.go
# setup grants
chmod +x ./bin/main
# execute
./bin/main 
```

## adding/install dependencies 

```bash
# all dependencies
go get .
# a single dependency
go get github.com/stretchr/testify/mock
```

## testing
```bash
go test ./...
#or
go test github.com/playground/projects/go/challenges/book_store/...
```

## Running
move to the folder with the main.go and execute:
```bash
go run .
```

## execute cli
move to the folder with the main and execute:
```bash
./main
```