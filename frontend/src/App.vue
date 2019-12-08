<template>
    <div id="app">
        <header-info/>
        <login-and-registration/>
    </div>
</template>

<script>
    import HeaderInfo from "./components/HeaderInfo";
    import LoginAndRegistration from "./components/LoginAndRegistration";

    export default {
        name: 'app',
        components: {
            LoginAndRegistration,
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
