var express = require("express");
var app = express();
var server = require("http").Server(app);
var io = require("socket.io")(server);

var messages = [
    {
        id: 1,
        text: "message",
        author: "me",
    },
];

app.use(express.static("public"));

app.get("/hello", function (req, res) {
    res.status(200).send("Hello World!");
});

io.on("connection", function (socket) {
    console.log("Connecting with Sockets");
    socket.emit("messages", messages);

    socket.on("new-message", function (data) {
        messages.push(data);

        io.sockets.emit("messages", messages);
    });
});

server.listen(3000, function () {
    console.log("Running on http://localhost:3000");
});