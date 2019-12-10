<template>
    <form>
        <div align="center">
            <h3>Форма ввода данных</h3>
        </div>
        <div class="form-row">
            <input type="text" id="xValue" name="xValue" maxlength="10" required>
            <label for="xValue">Координата X</label>
        </div>
        <div class="form-row">
                <input type="text" id="yValue" name="yValue" maxlength="10" required>
            <label for="yValue">Координата Y</label>
        </div>
        <div class="form-row">
            <input type="text" id="radius" name="radius" maxlength="10" required>
            <label for="radius">Радиус</label>
        </div>
        <div class="submit-row">
                <input type="submit" value="Отправить">
        </div>
    </form>
</template>

<script>
    export default {
        name: "PointCreateForm"
    }
</script>

<style scoped>
form{
    margin: 50px auto 30px;
}
</style>