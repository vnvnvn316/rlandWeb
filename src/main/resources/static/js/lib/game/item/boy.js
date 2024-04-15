export default class Boy{
    
    #name
    #gender
    #x
    #y
    #img

    constructor(){
        this.#img = new Image();
        this.#img.src="./res/boy.png";

        console.log(this.#img.width);
        console.log(this.#img.height);
    }
    // -------애니메이션을 위한 필수 메소드
    draw(ctx){
        ctx.drawImage(this.#img
            //source image
            ,106, 296, 106,149,
            //destination image
            150,150,200,200
        );
        

    }

    update(){

    }
    //-------------행위-------------

    move(x,y){

    }

    moveBy(dir){

    }
}