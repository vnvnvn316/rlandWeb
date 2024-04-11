//promise //성공, 실패에 대한 이벤트 처리로직을 분리하게 해주는 객체
{

    // 비동기 처리 함수 4 : 서비스 함수 예
    class MenuRepository{
        findAll(resolve){
            const xhr = new XMLHttpRequest();
            xhr.withCredentials = true;

            xhr.onload = function(){
                const list = JSON.parse(this.responseText);
                resolve(list);
            };

            const url = `http://localhost:8080/api/menus`;
            const method = "GET";
    
            xhr.open(method,url);
        
            xhr.send();
        }
    }

    let repository = new MenuRepository();
    
    /*let list = */repository.findAll((list)=>{
        console.log(list);
    });

    
    // 비동기 처리 함수2 : promise 방식의 비동기 처리함수
    function delayedPrint1(value){
        
        //promise : 콜백 넘어줄 놈 내놔
        const promise = new Promise((resolve, reject) => {
            let rand = Math.floor(Math.random()*2000)+1000;
        
            setTimeout(()=>{
                console.log(value);
                
                resolve(); //성공했을때
                //reject(); //실패했을때

            },rand);

        });
        
        return promise;
        
    }

    let pr = delayedPrint1("hello");

    pr.then(()=>{
        console.log("printed111 after");
    });

    //비동기 처리 함수 3 : async와 await를 이용한 동기식 호출이 가능하게 하기
    (async ()=>{
        await delayedPrint1("내 다음은 다 기다려! hello");
        console.log("printed async...");
    })();
    


    // 비동기 처리 함수 1 : 콜백 방식의 비동기 처리 함수
    function delayedPrint(value,completionHander,failureHander){
        let rand = Math.floor(Math.random()*2000)+1000;

        setTimeout(()=>{
            console.log(value);
            completionHander();

            //failureHander();
        },rand);
    }

    delayedPrint("hello", ()=>{
        console.log("printed after");
    }, ()=>{
        console.log("실패");
    });
}




//generator
{
    class Exam{
        constructor(){
            this.kor=10;
            this.eng=20;
            this.math=30;

        }

        *[Symbol.iterator](){
            yield this.kor;
            yield this.eng;
            yield this.math;

        }
        
        entries(){
            let [kor,eng,math] =this;
            
            return {
                *[Symbol.iterator](){
                    yield ["kor",kor];
                    yield ["eng",eng];
                    yield ["math",math];
                }
            };
        }
    }

    let exam = new Exam();

    for(let [k,v] of exam.entries())
        console.log("entries : ", k,v);

    // for(let n of exam) 
    // console.log("gen",n);
}


//Iterator 구현
{
    class Exam{
        constructor(){
            this.current=0;
            this.kor=10;
            this.eng=20;
            this.math=30;

        }

        [Symbol.iterator](){
            return this;
        }

    
        next(){
            this.current++; //인덱스를 관리한다
            switch(this.current){
                case 1: return {done:false, value:this.kor};
                case 2: return {done:false, value:this.eng};
                case 3: return {done:false, value:this.math};
                case 4: return {done:true, value:-1};
            }
        }
    }

    let exam = new Exam();
    
    for(let n of exam) // for of 문은 자동적으로 symbol.iterator 이름으로 구현한 메소드를 호출
        console.log("it",n);
    
    console.log(exam.next());
    console.log(exam.next());
    console.log(exam.next());
}



//set, List, Map collection
{
    let map = new Map();
    map.set("id",1);
    map.set("title","hello");
    map.set("content", "hihi");

    for(let entry of map.entries())
        console.log(entry[0],entry[1]);

    for(let [k,v] of map.entries())
    console.log(k,v);

    map.forEach((v,k)=> {
        console.log("foreach : ", k, v );
    })

    for(let k of map.keys())
        console.log(k);
    console.log("map 끝");

    //===================================================

    let set = new Set();
    set = new Set([2,3,4,5,6,3,1,2,3]);
    console.log(set.size);

    set.delete(5);
    console.log(set.size);

    set.add(10)
    console.log(set.size);

    //set.clear(); //set 객체를 비움
    //console.log(set.size);

    console.log("set iterate...");
    for(let n of set)
        console.log(n);
    console.log("/set interate..");

}

//-----------------------------------------------------------------------------------
//inteface
{   
    class NoticeService {
        static getList = Symbol();
        static getById = Symbol();
    }

    class NoticeServiceImp {
       [NoticeService.getList] (){
           return "list";
       }
    }
   
    class NoticeController {
       constructor(){
           this.service = new NoticeServiceImp();
       }
       printList(){
           console.log(this.service[NoticeService.getList]());
       }
    }
   
    let controller = new NoticeController();
    controller.printList();
   
   }





//symbol+computed property
{
 const getList = Symbol();

 class NoticeServiceImp {
    [getList] (){
        return "list";
    }
 }

 class NoticeController {
    constructor(){
        this.service = new NoticeServiceImp();
    }
    printList(){
        console.log(this.service[getList]());
    }
 }

 let controller = new NoticeController();
 controller.printList();

}

//inheritance
{
    class Exam{
        #kor
        #eng
        #math
    
        constructor(kor=10,eng=15,math=15){
            this.#kor = kor;
            this.#eng = eng;
            this.#math = math;
        }
    
        get kor(){
            return this.#kor;
        }
    
        set kor(value){
            this.#kor=value;
        }
    
        total(){
            return this.#kor+this.#eng+this.#math;
        }
    
    }

    class NewlecExam extends Exam{
        #com
        constructor(){
            super();
            this.#com=10;
        }

        // total(){
        //     return super.total()+this.#com;
        // }

        avg(){
            return (this.total()+this.#com)/4;
        }
    }

    console.log(new NewlecExam().total());
    
}
//==============================================================================
//static 선언
class Exam2{
            
    #kor
    #eng
    #math
    static #staticVariable

    static {
        this.#staticVariable =30;
    }

    static get staticVariable(){
        return Exam2.#staticVariable;
    }

    constructor(kor=10,eng=20,math=30){
        this.#kor = kor;
        this.#eng = eng;
        this.#math = math;
    }

    get kor(){
        return this.#kor;
    }

    set kor(value){
        this.#kor=value;
    }

    total(){
        return this.#kor+this.#eng+this.#math;
    }

}

console.log(Exam2.staticVariable);


//======================================================================================

{
    function createExam(){
        

        return class Exam{
            
            #kor
            #eng
            #math

            constructor(kor=10,eng=20,math=30){
                this.#kor = kor;
                this.#eng = eng;
                this.#math = math;
            }

            get kor(){
                return this.#kor;
            }

            set kor(value){
                this.#kor=value;
            }

            total(){
                return this.#kor+this.#eng+this.#math;
            }
        }
    }

    let ExamClass = createExam();
    let exam = new ExamClass();
    
    //exam.setKor(exam.getKor()+1);
    
    exam.kor++;
    
    console.log("ExamClass total : ", exam.total(),exam.kor);

    // let exam = new Exam();
    // console.log(exam.total());
    // console.log(typeof Exam);

}