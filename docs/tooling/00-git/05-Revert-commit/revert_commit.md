# Git delete remove a commit

## Revert a commit

```bash
git log 
git revert "hash del commit" 
git push
```

## Remove last commit

```bash
#
git reset HEAD^ --hard 
# force push to remote
git push origin HEAD --force
# remove the commit from the history
git reset --hard HEAD~1
```

## Remove up to a commit

```bash
git reset --hard <sha1-commit-id>
```

## Delete the last commit

Deleting the last commit is the easiest case. Let's say we have a remote mathnet with branch master that currently points to commit dd61ab32. We want to remove the top commit. Translated to git terminology, we want to force the master branch of t mathnet remote repository to the parent of dd61ab32:

```
$ git push mathnet +dd61ab32^:master

```

Where git interprets`x^`as the parent of x and`+`as a forced non-fastforward push. If you have the master branch checked out locally, you can also do it in two simpler steps: First reset the branch to the parent of the current commit, then force-push it to the remote.
