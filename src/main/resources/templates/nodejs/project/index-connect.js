const connect = require("connect");
const path = require("path");
const serveStatic = require('serve-static');

const app = connect();

//home directory setting
app.use(serveStatic(path.join(__dirname,"public")));

app.use("/index",(req,resp)=>{
    console.log(path.join(__dirname,"public"));
    resp.end("index page");
});

app.use("/menu/list",(req,resp)=>{
    resp.end("menu list page");
});

app.listen(80);

console.log("서버가 80번 포트로 시작되었습니다..");
