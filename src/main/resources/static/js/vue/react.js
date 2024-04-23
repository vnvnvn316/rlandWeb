let hello = "hello";
let clickHandler = function(){
    console.log("aa");
}


ReactDOM.render(
    
    <section id="form-section">
        {/*<!-- 덧셈계산기 --> */}
        <h1 >덧셈계산기</h1>
        <form>
            <fieldset>
                <legend>계산기 입력폼</legend>
                <div>
                    <label>x:</label>
                    <input dir="rtl" name="x" value="0" />
                    <label>y:</label>
                    <input dir="rtl" name="y" value="1"/>
                    <span>=</span>
                    <span>{hello}</span>
                </div>
                <hr/>
                <div>
                    <input type="submit" value="초기화"/>
                    <input onClick={clickHandler} type="submit" value="계산하기"/>
                </div>
            </fieldset>
        </form>
    </section>,
    document.querySelector("#root")
);