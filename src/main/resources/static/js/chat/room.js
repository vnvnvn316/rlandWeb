window.addEventListener("load", function(){
    const chatWindow = this.document.querySelector("#chat-window");

    const connButton = chatWindow.querySelector(".btn-conn");
    const sendButton = chatWindow.querySelector(".btn-send");

    const nameInput = chatWindow.querySelector(".input-name");
    const textInput = chatWindow.querySelector("input[type='text']");
    const ul = chatWindow.querySelector("ul");
    
    const TYPE_CONNECT =1;
    const TYPE_MESSAGE =2;

    let sock=null;

    connButton.onclick = function(){
        sock = new WebSocket("ws://192.168.0.54:8080/chat");
        sock.onopen = () => {
            
            //type 1이면 이름이 2번이면 메세지가 서버에 전송
            //나중에 type의 변수에 대해서 기억이 나지 않으니 상수형 변수 생성
            let data = {type:TYPE_CONNECT, content:nameInput.value};

            //data를 전달하고싶다.
            sock.send(JSON.parse(data));

            let li = `<li>서버에 연결되었습니다.</li>`;
            ul.insertAdjacentHTML("beforeend",li);
            textInput.disabled=false;
        };
        sock.onclose = ()=> {

        };
        sock.onmessage = (e)=> {
            let li = `<li>${e.data}</li>`;
            ul.insertAdjacentHTML("beforeend",li);
        };
    }

    sendButton.onclick = function(){
        if(sock)
            sock.send(textInput.value);
    }


});