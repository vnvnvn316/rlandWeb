// import m1test1 , {test2} from './module1.js';
import m2test1 , {test2 as m2test2} from './module2.js';
import m1test1  , {test2} from 'gm';

//module
{
    m1test1();
    //m2test2();

    let rand=1;

    //동적으로 import해서 함수 사용하고 싶을때
    if(rand ==1){
        import("./module1.js")
        .then(({test2})=>{ //destructing
            //test2();
        });
    }

    if(rand ==1){
        import("./module1.js")
        .then(({default:test1, test2})=>{
            test2();
            test1();
        });
        // import("./module1.js")
        // .then(m1=>{
        //     m1.test2();
        //     m1.default();
        // });

    }
}