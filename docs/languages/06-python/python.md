# python

## list the installed packages and folder
```
pip list -v
```

## virtual environment to handle python packages
Follow the next steps to create a virtual environment:
```
pip install virtualenv
virtualenv venv
source venv/bin/activate
source venv/bin/deactivate
```
Once you have installed the venv, you can install the packages you need.
The virtual environment is a folder that contains all the packages you install locally. Referring to a current project.

If you don't want to install the packages in the global environment, you can use the virtual environment.

## install a package local o global
Depended on if you are using a virtual environment or not.
If it is so, you can install the package locally, and if not, you can install it globally.

```
pip install package_name
```

## install some lib with requirements.txt

```
pip install -r requirements.txt
```

# run test
```
python -m unittest discover -s tests
pytest tests
```
