export default {
    data() {
      return {
          x:3,
          y:4,
          result : 0
      }
    },
    methods : {
      calcSubmitHander(){
        this.result = this.x + this.y;
      }
    }, template :`
      <section>
            <h1>덧셈 계산기</h1>
            <form>
                <fieldset>
                    <legend>계산기 입력폼</legend>
                    <div>
                        <label>x:</label>
                        <input dir="rtl" name="x" v-model.number.trim="x">
                        <label>y:</label>
                        <input dir="rtl" name="y" v-model.number.trim="y">
                        <span>=</span>
                        <span v-text="result">0</span>
                        <span>{{x+y}}</span>
                    </div>
                    <hr>
                    <div>
                        <input type="submit" value="초기화">
                        <input type="submit" value="계산하기" @click.prevent="calcSubmitHander">
                    </div>
                </fieldset>
            </form>
        </section>
    `
  }