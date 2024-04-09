//set, List, Map collection
{
    let set = new Set();
    set = new Set([2,3,4,5,6,3,1,2,3]);
    console.log(set.size);

    set.delete(5);
    console.log(set.size);

    set.add(10)
    console.log(set.size);

    set.clear(); //set 객체를 비움
    console.log(set.size);

}

//-----------------------------------------------------------------------------------

//symbol
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