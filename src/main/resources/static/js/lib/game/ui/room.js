import Boy from "../item/boy.js";


export default class Room {
    #img
    #ctx
    #boy
    #canvas
    #timerId

    constructor(){
        this.#timerId;
        const gameSection =document.querySelector("#game-section");
        this.#canvas = gameSection.querySelector(".room");
        this.#canvas.onclick=this.clickHandler.bind(this);

        /** @type {CanvasRenderingContext2D} */
        this.#ctx = this.#canvas.getContext("2d");

        this.#img = new Image();
        this.#img.src="./res/map.png";
        
        this.#boy = new Boy();

        this.run();

    }

    //for event handling
    clickHandler(e){
        this.#boy.move(e.x,e.y);
        //this.#boy.draw(this.#ctx);
    }

    //-for animation---------------------

    draw(){
        this.#ctx.drawImage(this.#img,0,0);
        this.#boy.draw(this.#ctx);

    }

    update(){
        this.#boy.update();
    }

    //for service -----------------

    run(){
        this.#timerId = setInterval(()=>{
            this.update();
            this.draw();
        },17); //1000/60 1초에 fps(frame per second) 60번
    }

    stop(){
        clearInterval(this.#timerId);
    }

}