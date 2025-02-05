#bin/bash

echo "generating executable main program!\n"
## sudo go build -o /bin/main cmd/cli/main.go

## creates bin folder
echo "creating the bin folder if doesn't exists\n"
mkdir ./bin

## creates the main exec file
echo "\ncreating main exec file\n"
go build -o main cmd/cli/main.go

## move the main file to bin folder
echo "moving main exec file to the bin folder\n"
mv main ./bin/main

## execute the main exec file
echo "execute main exec on to the bin folder\n"
cd ./bin && ./main

