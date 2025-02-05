# Bash vs Zsh

over the `terminal` write down `opt + ñ` to get the `~`

```bash
# setup on bash
cat ~/.bash_profile
cat ~/.bashrc

# setup on zsh
cat ~/.zprofile
cat ~/.zshrc
```

- /etc/profile	This is a "System wide" initialisation file that is executed during login. This file provides initial environment variables and initial "PATH" locations.

- /etc/bashrc	This again is a "System Wide" initialisation file. This file is executed each time a Bash shell is opened by a user. Here you can define your default prompt and add alias information. Values in this file can be overridden by their local ~/.bashrc entry.

- `~/.bash_profile`	If this file exists, it is executed automatically after /etc/profile during the login process. This file can be used by each user to add individual entries. The file however is only executed once at login and normally then runs the users .bashrc file.

- ~/.bash_login	If the ".bash_profile" does not exist, then this file will be executed automatically at login.

- ~/.profile	If the ".bash_profile" or ".bash_login" do not exist, then this file is executed automatically at login.

- `~/.bashrc`	This file contains individual specific configurations. This file is read at login and also each time a new Bash shell is started. Ideally, this is where you should place any aliases.

- ~/.bash_logout	This file is executed automatically during logout.

- ~/.inputrc	This file is used to customize key bindings/key strokes.

![bash.jpg](_img%2Fbash.jpg)

Zsh

`~/.zprofile`

- `~/.zshrc`

## Binary search

shortcut: `ctrl + r`

click `ctrl + r` and start to write what do you what to find in your history.

For instance, I do `ctrl + r` and start to write `docker` and the prompt shows `docker rm -f $(docker ps -a -q)`

```bash
% docker rm -f $(docker ps -a -q)
bek-i-search: docker_
```

## SETUP ARTIFACTORY

If you have a gradle authentication/authorization error is because you don't have the next env vars.

Use `printenv` to list the env variables and look for the ARTIFACTORY_USER for instance

Use `echo $ARTIFACTORY_USER` to check if the env var is already setup.

Remember export this env variables on your `~/.bash_profile` and `~/.zprofile`

```bash
export ARTIFACTORY_USER=yourusergoeshere@yourdomain
export ARTIFACTORY_PASSWORD=yourAPIKEYpassgoeshere
export ARTIFACTORY_PWD=yourAPIKEYpassgoeshere
export ARTIFACTORY_NPM_SECRET=yourAPIKEYpassgoeshere
export ARTIFACTORY_CONTEXT=your/artifactory
export ARTIFACTORY_CONTEXTURL=your/artifactory
```

## SETUP ALIASES

Edit the files `~/.bashrc` and `~/.zshrc`

```bash
alias docker-compose='docker compose'
```