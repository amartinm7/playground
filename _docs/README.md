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

Generate de _sidebar.md file
```bash
docsify generate <path> [--sidebar _sidebar.md]

docsify generate _docs
```

```bash
# Utilities for generate all the _sidebar.md files, althought you don't need it
find _docs -type d | grep -v _img | xargs -I{} sh -c 'docsify generate "{}"'

# utility to delete all the _sidebar.md in order to regenerate new ones
find _docs -type f -name "_sidebar.md" -exec rm {} \;
```


