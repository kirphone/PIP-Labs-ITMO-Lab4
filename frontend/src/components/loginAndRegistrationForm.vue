<template>
    <form action="" @submit="validateForm">
        <div align="center">
            <h3 v-if="formName === 'login'">Форма входа</h3>
            <h3 v-else>Форма регистрации</h3>
        </div>
        <div class="form-row">
            <input v-model="login" type="text" id="login" name="login" maxlength="30" required>
            <label for="login">Имя пользователя</label>
        </div>
        <div class="form-row">
            <input v-model="password" type="password" id="password" name="password" maxlength="50" required>
            <label for="password">Пароль</label>
        </div>

        <div v-if="formName === 'registration'" class="form-row">
            <input v-model="confirmPassword" type="password" id="confirmPassword" name="confirmPassword" maxlength="50" required>
            <label for="confirmPassword">Подтвердите пароль</label>
        </div>

        <div class="error-row" align="left">
            <label :hidden="isErrorMessageHidden">{{ errorMessageValue }}</label>
        </div>

        <div class="submit-row">
            <input type="submit" v-bind:value="formName === 'login' ? 'Вход' : 'Регистрация'">
        </div>
    </form>
</template>

<script>

    import axios from 'axios'

    export default {
        name: "loginAndRegistrationForm",
        props: {
            formName: String
        },
        data : function(){
            return {
                password : "",
                login : "",
                confirmPassword : "",
                isErrorMessageHidden : true,
                errorMessageValue : ""
            }
        },
        watch: {
            formName: function () {
                this.password = "";
                this.login = "";
                this.confirmPassword = "";
                this.isErrorMessageHidden = true;
            }
        },
        methods: {
            validateForm: function (e) {
                if(!/^[a-z]{1}[a-z\d]*$/i.test(this.login))
                    this.errorMessageValue = "Логин может состоять из латинских букв и цифр, но не может начинаться с цифры";
                else if(!/^[a-z\d]*$/i.test(this.password))
                    this.errorMessageValue = "Пароль может состоять из латинских букв и цифр";
                else if(this.formName === "registration" && this.password !== this.confirmPassword)
                    this.errorMessageValue = "Пароли не совпадают";
                else {
                    this.isErrorMessageHidden = true;
                    axios.get("api/login", {
                        withCredentials : true,
                        auth : {
                            username : this.login,
                            password : this.password
                        }
                    })
                        .then(() => {
                            this.$router.push("home");
                        })
                        .catch(error => {
                            if(error.response.status === 401){
                                this.errorMessageValue = "Неверный логин или пароль";
                            } else
                                this.errorMessageValue = error;
                            this.isErrorMessageHidden = false;
                        })
                }

                this.isErrorMessageHidden = false;
                e.preventDefault();
            }
        }
    }
</script>

<style scoped>
    form {
        background: white;
        max-width: 350px;
        margin: 20px auto 30px;
        padding: 50px 30px 30px;
    }

    form h3{
        color: #4a90e2;
        font-size: 30px;
        margin: 0 0 40px;
    }

    .form-row{
        position: relative;
        padding-bottom: 40px;
    }

    .form-row input {
        display: block;
        line-height: 40px;
        width: 100%;
        padding: 0 10px;
        background: none;
        border-width: 0;
        border-bottom: 2px solid #4a90e2;
        transition: all 0.2s ease;
    }

    .form-row label {
        position: absolute;
        left: 13px;
        color: #9d959d;
        font-size: 20px;
        font-weight: 300;
        transform: translateY(-35px);
        transition: all 0.2s ease;
    }

    .form-row input:focus{
        outline: 0;
        border-color: #F77A52;
    }

    .form-row input:focus+label,
    .form-row input:valid+label {
        transform: translateY(-60px);
        margin-left: -14px;
        font-size: 14px;
        font-weight: 400;
        outline: 0;
        border-color: #F77A52;
        color: #F77A52;
    }

    .error-row label{
        font-size: 18px
        !important;
        color: red
        !important;
    }

    .submit-row{
        padding-top: 10px;
        margin: 0;
    }

    input[type="submit"] {
        width: 100%;
        padding: 0;
        line-height: 40px;
        background: #4a90e2;
        border-width: 0;
        color: white;
        font-size: 20px;
    }
</style>