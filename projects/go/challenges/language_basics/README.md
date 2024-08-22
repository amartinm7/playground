# Go language basics

## init: creating new go.mod
over the project folder execute
```bash
`go mod init language_basics`
```

## clean: to add module requirements and sums
```bash
go mod tidy
```

## execute 
```bash
go run cmd/cli/main.go
```

## create executable
```bash
go build -o helloworld cmd/cli/main.go
```

We can then execute the built binary directly.
```bash
./helloworld
# > hello world
```

## Reference

https://gobyexample.com/