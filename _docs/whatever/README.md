# General notes

this repo is intended to play with the code and for technical tests.

## docsify

https://docsify.js.org/#/quickstart

```bash
npm i docsify-cli -g
```

init docs
```bash
docsify init ./_docs
```

run the server
```bash
docsify serve ./_docs
```

```bash
docsify generate <path> [--sidebar _sidebar.md]

docsify generate _docs/tech/devops [--sidebar _sidebar.md]


find _docs -type d | grep -v _img | xargs -I{} sh -c 'docsify generate "{}"'


find _docs -type f -name "_sidebar.md" -exec rm {} \;
```


