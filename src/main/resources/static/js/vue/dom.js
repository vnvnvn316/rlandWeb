window.addEventListener("load", ()=>{
    const section = document.querySelector("#form-section");
    const xInput = section.querySelector("input[name='x']");
    const yInput = section.querySelector("input[name='y']");
    const initSubmit = section.querySelector("input[value='초기화']");
    const calcSubmit = section.querySelector("input[value='계산하기']");
    const resultSpan = section.querySelector(".result-span");

    let x = 3;
    let y = 4;

    xInput.value =x;
    yInput.value =y;

    calcSubmit.onclick = (e) =>{
        
        e.preventDefault();

        let x = xInput.value;
        let y = yInput.value;

        let result = parseInt(x)+parseInt(y);

        console.log(resultSpan);

        resultSpan.innerText = result;

    };

});