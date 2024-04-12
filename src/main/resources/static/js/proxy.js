let exam = {kor:10, eng:20, math:30}; // 왕자님 target
console.log("target, kor :", exam.kor);

let logHander ={
    get(target, prop, receiver) {
        
        console.log("로그찍기로그로그");

        //return target[prop];
        //return Reflect.get(target, prop, receiver);
        return Reflect.get(...arguments);
      },
};

let proxy = new Proxy(exam, logHander);
console.log("proxy, kor :", proxy.kor);