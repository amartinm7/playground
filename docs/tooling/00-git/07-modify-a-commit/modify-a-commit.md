# Modify a commit

This is a guide on how to modify a commit in git.
For example, you can change the commit message, add or remove files, or change the content of the files.

```
HEAD
Commit1  aaaaaaaa
Commit2  cccccccc
Commit3  dddddddd
```

Choose the commit you want to modify. In this case, we want to modify `aaaaaaaa`.
```bash
git rebase --interactive cdc213ff^
```

This will open an editor with a list of commits. Find the commit you want to modify and change `pick` to `edit`.

```bash     

pick aaaaaaaa

edit cccccccc   # Change pick to edit       

pick dddddddd
```

Save and close the editor. Git will stop at the commit you want to modify.

```bash
git commit --amend
```

This will open an editor with the commit message. Change the message and save and close the editor.

```bash

git add <file>  # Add or remove files

git commit --amend --no-edit
```

This will add the changes to the commit. You can also change the content of the files.

```bash
git rebase --continue
```
    
This will continue the rebase process.

```bash
git push --force
```
    