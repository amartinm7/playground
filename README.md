# playground

this repo is intended to play with the code and for technical tests.

It's personal wiki, where I can store code snippets, and other useful information.

## How to use

…or create a new repository on the command line
```bash
echo "# playground" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/your-github-user-goes-here/playground.git

git push --set-upstream https://user:pass-apikey@github.com/amartinm7/playground.git master

git push -u origin main
```
…or push an existing repository from the command line
```bash
git remote add origin https://github.com/your-github-user-goes-here/playground.git

git branch -M main
git push -u origin main
```

## setup credentials

```bash
# global configuration
git config --global
git config --global user.name "user"
git config --global user.email "github.email"

# local configuration
git config --list --show-origin
git config --local --list
git config user.name "user"
git config user.email "github.email"
# repo configuration
cat .git/config

git remote -v 
cat .git/config
git push --set-upstream https://user:pass-apikey@github.com/amartinm7/playground.git master

# global configuration
echo "protocol=https\nhost=github.com" | git credential fill

cat ~/.gitconfig
```