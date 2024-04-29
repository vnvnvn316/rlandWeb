const express = require("express");

const server = express();
server.listen(80);

server
.route("/index")
.get((req,resp)=>{

});

// server.use("/index",(req,resp)=>{

// });
