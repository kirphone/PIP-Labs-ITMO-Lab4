<template>
    <div class="hello">
        <h1>{{ msg }}</h1>
        <button @click="printHelloMessage">Call REST API</button>

        <h3>{{helloMessage}}</h3>
    </div>
</template>

<script>
    import {api} from '../http-commons.js'
    export default {
        name: 'HelloWorld',
        props: {
            msg: String
        },
        data() {
            return {
                helloMessage: ''
            }
        },
        methods : {
            printHelloMessage: function() {
                api("hello").then(responce => {
                    this.helloMessage = responce.data.msg;
                })
            }
        }
    }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
