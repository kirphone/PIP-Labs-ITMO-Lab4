<template>
    <div id="app">
        <header-info/>
        <router-view/>
    </div>
</template>

<script>
    import HeaderInfo from "./components/HeaderInfo";

    export default {
        name: 'app',
        components: {
            HeaderInfo
        },
        methods: {

        }
    }
</script>

<style>
    @font-face {
        font-family: ElMessiri;
        src: url('assets/fonts/ElMessiri-Bold.ttf');
        font-weight: bold;
        font-style: normal;
    }

    @font-face {
        font-family: ElMessiri;
        src: url('assets/fonts/ElMessiri-Regular.ttf');
        font-weight: normal;
        font-style: normal;
    }

    *{
        box-sizing: border-box;
        font-family: ElMessiri, 'Comic Sans MS', fantasy;
        font-weight: normal;
    }

    body{
        margin: 0;
        padding: 0;
        background: #e6f4fd;
    }


</style>
