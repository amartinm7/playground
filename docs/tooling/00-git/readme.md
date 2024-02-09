# Config user name and credentials to connect to github

Look into your github repo the `developer settings`.
create a personal token
check the `repo` on the grant list.
copy the `personal token`

when you do git push, 
- you have to write down the github user.name@gmail.com and 
- the pass y the previous personal token
- finally `git config --global credential.helper cache` to cache the token

## commands

```bash
# setup properties as key value pairs
git config --global user.name "myusername"
git config --global user.email "myemail@gmail.com"
git config --list
# Configure Credentials Caching
git config --global credential.helper cache
# remove cache
git config --global --unset credential.helper
```

```bash
# check configuration
git config --list
```
this is the list, you can setup all the keys as `git config --global key value`
user.name=John Doe
user.email=johndoe@example.com
color.status=auto
color.branch=auto
color.interactive=auto
color.diff=auto
