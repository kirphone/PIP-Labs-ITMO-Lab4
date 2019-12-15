<template>
    <form>
        <div align="center">
            <h3>Форма ввода данных</h3>
        </div>
        <div class="form-row">
            <input v-model="xValue" type="text" id="xValue" maxlength="10"
                   @input="validate(this, -5, 5)" required>
            <label for="xValue">Координата X</label>
        </div>
        <div class="form-row">
                <input v-model="yValue" type="text" id="yValue" maxlength="10"
                       @input="validate(this, -5, 5)" required>
            <label for="yValue">Координата Y</label>
        </div>
        <div class="form-row">
            <input v-model="radius" type="text" id="radius" maxlength="10"
                   @input="validate(this, -5, 5)" required>
            <label for="radius">Радиус</label>
        </div>
        <div class="submit-row">
                <input type="submit" value="Отправить">
        </div>
    </form>
</template>

<script>
    export default {
        name: "PointCreateForm",
        data: function(){
          return{
              xValue : "",
              yValue : "",
              radius : ""
          }
        },
        methods :{
            validate : function (input, l, r) {
                input.value = input.value.replace(/[^0-9,.-]/g, '').replace(/,/, ".");
                if (isNaN(input.value) && input.value !== "-")
                    input.value = "";
                if (input.value > r)
                    input.value = r;
                if (input.value < l) {
                    input.value = l;
                }
                if (input.value.length > 10) {
                    input.value = input.value.slice(0, -1);
                }
                /*if(input.id == "form:r-spinner_input")
                    drawPoints();*/
            }
        }
    }
</script>

<style scoped>
form{
    margin: 50px auto 30px;
}
</style>