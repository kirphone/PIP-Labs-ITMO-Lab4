<template>
    <div class="header-info">
        <div id="option"><h2>Вариант 31111</h2></div>
        <div id="fio"><h2>Буланов Кирилл, группа P3212</h2></div>
    </div>
</template>

<script>
    export default {
        name: "HeaderInfo"
    }
</script>

<style scoped>
    .header-info{
        color: #4a90e2;
        background-color: white;
        height: 75px;
        border-bottom-style: solid;
        border-width: medium;
    }

    .header-info div{
        height: 100%;
        margin: auto 0;
    }

    #option {
        float: left;
        padding-left: 30px;
    }

    #fio {
        float: right;
        padding-right: 30px;
    }
</style>