# Dependabot

over .github/dependabot.yml

you can define a github-action to have your repo updated.

```bash
# To get started with Dependabot version updates, you'll need to specify which
# package ecosystems to update and where the package manifests are located.
# Please see the documentation for all configuration options:
# https://docs.github.com/github/administering-a-repository/configuration-options-for-dependency-updates

version: 2
updates:
  # Maintain dependencies for GitHub Actions
  - package-ecosystem: "github-actions"
    directory: "/"
    schedule:
      interval: "daily"
      time: "09:00"
      timezone: "Europe/Madrid"

  # Maintain dependencies for npm for JS challenges
  - package-ecosystem: "npm"
    directory: "/"
    schedule:
      interval: "daily"
      time: "09:00"
      timezone: "Europe/Madrid"
    versioning-strategy: increase
```

Ignore updates

```bash
OK, I won't notify you again about this release, but will get in touch when a new version is available. If you'd rather skip all updates until the next major or minor version, let me know by commenting @dependabot ignore this major version or @dependabot ignore this minor version . You can also ignore all major, minor, or patch releases for a dependency by adding an ignore condition with the desired update_types to your config file.
If you change your mind, just re-open this PR and I'II resolve any conflicts on it.
```

![ignore-dependantbot.jpg](_img%2Fignore-dependantbot.png)

```bash
@dependabot ignore this major version
```

```bash
OK, I won't notify you about version 3.x. again, unless you re-open this PR. I
```
