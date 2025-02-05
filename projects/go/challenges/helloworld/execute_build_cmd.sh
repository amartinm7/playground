#bin/bash

echo "generating executable helloworld program!\n"
## sudo go build -o /bin/helloworld cmd/cli/main.go

## creates bin folder
echo "creating the bin folder if doesn't exists\n"
mkdir ./bin

## creates the main exec file
echo "\ncreating helloworld exec file\n"
go build -o helloworld cmd/cli/main.go

## move the helloworld file to bin folder
echo "moving helloworld exec file to the bin folder\n"
mv helloworld ./bin/helloworld

## execute the main exec file
echo "execute helloworld exec on to the bin folder\n"
cd ./bin && ./helloworld

