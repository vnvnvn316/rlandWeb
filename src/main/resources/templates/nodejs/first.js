let repository = require('./file-repository');
let fs = require('fs');
let newlec = require("newlec-hello");

console.log(newlec.hello());


// let dirList = repository.findAll("../",{
//     typeName : ".js"
// });

// let csv = dirList.join(",");

// fs.writeFileSync("./foldlist.txt",csv);