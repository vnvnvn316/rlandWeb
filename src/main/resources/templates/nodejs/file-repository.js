const fs = require('fs');
const { finished } = require('stream');

function findAll(path,{typeName}) {
    let fileList = [];

    if(typeName){
        let list = fs
        .readdirSync(path , {withFileTypes:true}) 
        .filter(item=>item.isDirectory())
        .map(item=>item.name)
        //.map((directory)=>directory);

        // let list = fs
        //             .readdirSync(path) //리스트를 return함
        //             .filter((fileName)=>fileName.endsWith(".js")) //파일리스트에 담긴 파일을 하나씩 꺼내서 필터링 후 리턴
        //             .map((fileName)=>fileName.replace(".js",""))
        //             .reduce((pre/*앞에서 집계한 값 */,curItem/*filename */)=>pre+curItem.length,0); // 0이 초기값

        // let listFiltered = fileList.filter((fileName)=> fileName.endWith('.js')); // filter는 .js가 가진 file을 껴줄까말까
        // let listMapped = listFiltered.map((fileName)=> fileName.replace(".js",""));
        //fileList = fileList.filter((fileName)=>fileName.lenght>5);

        return list;
    }

    return fileList;

}

exports.findAll = findAll;