# GitHub Pages

Simple GitHub Pages Setup for Markdown Docs¶
Yack is built on top of MkDocs, which means you can publish your documentation to GitHub Pages with minimal effort. This guide walks you through getting your existing Markdown documentation online using GitHub-native tools.

1. Add a GitHub Actions workflow
2. To automate the publishing process, you'll need to define a GitHub Actions workflow. This will:

   - Build your MkDocs site every time you push to the master branch 
   - Deploy the output to the gh-pages branch using GitHub Pages 
   - Create the following file in your repository under .github/workflows/gh-pages.yml:


```yaml
name: Build GitHub Pages
on:
  push:
    branches:
      - master
permissions:
  contents: write
  pages: write
  id-token: write

jobs:
  build_mkdocs:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v4
        with:
          fetch-depth: 0
      - uses: actions/setup-python@v5
        with:
          python-version: 3.9
      - run: pip install mkdocs
      - run: mkdocs build
      - name: Deploy to gh-pages
        uses: peaceiris/actions-gh-pages@v3
        with:
          github_token: ${{ secrets.GITHUB_TOKEN }}
          publish_dir: ./site

```


2. Enable GitHub Pages¶
   This step tells GitHub to serve the generated site:

Go to your repository on GitHub

Navigate to Settings → Pages

Under Source, choose gh-pages and set the path to / (root)

Click Save

GitHub will now use the output of the workflow (./site) as your live documentation site.

3. Commit and push your changes

   Add, commit, and push the new workflow file. After a successful push to the master branch:

The GitHub Actions workflow will automatically run
If successful, your static site will be built and deployed to the gh-pages branch
You can monitor progress under Actions in your repository.


several MkDocs plugins — such as mkdocs-material, swagger, and plantuml — that won’t be available by default

If your documentation relies on any of these features, you’ll need to:

Install the relevant plugins in your pipeline (pip install <plugin>)

Enable them in your mkdocs.yml under the plugins: section

## Additional Resources
To go further with `MkDocs` and get the best out of your migration:

- [MkDocs Official Documentation](https://www.mkdocs.org/)
- [MkDocs Material Theme](https://squidfunk.github.io/mkdocs-material/)
- [MkDocs Plugin Directory](https://github.com/mkdocs/catalog)
- [GitHub Pages Docs](https://docs.github.com/en/pages)
- [peaceiris/actions-gh-pages — the GitHub Action used for deployment](https://github.com/peaceiris/actions-gh-pages)
