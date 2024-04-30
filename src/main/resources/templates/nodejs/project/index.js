const express = require("express");
const path = require("path");
const ejs = require("ejs");

const server = express();
server.set("views", path.join(__dirname,"views")); //views 경로설정
server.set("view engine","ejs"); //뷰엔진으로 ejs를 쓰겠다.
server.use(express.static(path.join(__dirname,"public")));

server.listen(80);

server
.route("/index")
.get((req,resp)=>{
    //resp.send("hhh"); // 클라이언트 화면에 뿌려줌
    resp.render("index.ejs",{test:"Hello"}); //index.ejs 페이지 렌더링, {}안은 데이터, 모델이라고 생각하면 됨
});