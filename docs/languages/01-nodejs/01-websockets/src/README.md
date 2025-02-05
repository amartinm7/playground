# websocket

nvm: node version manager
```bash
nvm current
nvm list
nvm install v18.12.0
nvm use v18.12.0
nvm install v20.12.2
nvm use v20.12.2
```

Create folder and navigate to it
```bash
mkdir websocket && cd $_ 
```

Create project
```bash
npm init -y

npm install --save express socket.io
npm install --save-dev nodemon

npm install
```

open the project in atom
```bash
atom .
```

Edit package.json scripts: `"start": "nodemon server/index.js"`

Run server:
```bash
npm start
```

