const http = require("http");
http.createServer((req ,resp)=>{
    console.log(req.url);
    console.log("배고프다!!");
    resp.write("===================");
    resp.end();
}).listen(80);

// server.on("request", ()=>{
//     console.log("배고프다!!");
// });
// server.listen(80);
