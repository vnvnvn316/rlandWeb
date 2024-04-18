export default class Boy{
    
    #name
    #gender
    #x
    #y
    #vx
    #vy
    #dx
    #dy
    #img
    #w
    #h
    #moveIndex
    #movedelayCount
    #dirIndex
    

    constructor(){
        this.#img = new Image();
        this.#img.src="./res/boy.png";
        this.#w=this.#img.width/3;
        this.#h=this.#img.height/4;
        this.#x=100;
        this.#y=100;
        this.#vx=0;
        this.#vy=0;
        this.#dx=this.#x;
        this.#dy=this.#y;
        this.#moveIndex=1;
        this.#movedelayCount=10;
        this.#dirIndex=2;

    }

    // -------애니메이션을 위한 필수 메소드
    draw(ctx){
        let mi = this.#moveIndex;
        let di = this.#dirIndex;

        let w = this.#w;
        let h = this.#h;
        let sx = w*mi;
        let sy = h*di;

        //목적지 위치
        let dx = this.#x-w/2;
        let dy = this.#y-(h/2+50);

        ctx.drawImage(this.#img
            //source image
            ,sx,sy,w,h,
            //destination image
            dx,dy,w,h
        );

    }

    update(){
        this.#x += this.#vx;
        this.#y += this.#vy;

        // if(this.#x > this.#dx)
        //     console.log(this.#x ,this.#dx);
        
        if(Math.floor(this.#x)==this.#dx || Math.floor(this.#y)==this.#dy){
            this.#vx =0;
            this.#vy =0;
            this.#dirIndex=2;
            this.#moveIndex=1; //멈춤이미지
            this.#movedelayCount=10;
        }
        else
            if(this.#movedelayCount-- == 0) {
                this.#moveIndex = this.#moveIndex== 0 ? 2 :0; //toggle
                this.#movedelayCount = 10;
            }
        

    }


    //-------------행위-------------
    
    move(x,y){
        // this.#x = x;
        // this.#y = y;

        let w = x - this.#x;
        let h = y - this.#y;

        let d = Math.sqrt(w*w+h*h); //클릭한 곳까지의 직선거리

        this.#vx = w/d;
        this.#vy = h/d;
        
        this.#dx=x;
        this.#dy=y;

        console.log(this.#vx);

        let abs = Math.abs(w) - Math.abs(h);
        
        if(abs>0)
            this.#dirIndex = w<0 ? 3 : 1;
        
        if(abs<0)
            this.#dirIndex = h<0 ? 0 : 2;
        
    }

    moveBy(dir){

    }
}