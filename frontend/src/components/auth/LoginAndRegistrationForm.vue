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
            <input v-model="confirmPassword" type="password" id="confirmPassword" name="confirmPassword" maxlength="50"
                   required>
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
        data: function () {
            return {
                password: "",
                login: "",
                confirmPassword: "",
                isErrorMessageHidden: true,
                errorMessageValue: ""
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
                if (!/^[a-z]{1}[a-z\d]*$/i.test(this.login))
                    this.errorMessageValue = "Логин может состоять из латинских букв и цифр, но не может начинаться с цифры";
                else if (!/^[a-z\d]*$/i.test(this.password))
                    this.errorMessageValue = "Пароль может состоять из латинских букв и цифр";
                else if (this.formName === "registration" && this.password !== this.confirmPassword)
                    this.errorMessageValue = "Пароли не совпадают";
                else {
                    this.isErrorMessageHidden = true;

                    if(this.formName === "login"){
                        this.processLogin();
                    }
                }

                this.isErrorMessageHidden = false;
                e.preventDefault();
            },
            processLogin: function () {
                axios.post("api/auth/login", {
                    "username": this.login,
                    "password": this.password
                })
                    .then(() => {
                        this.$router.push("home");
                    })
                    .catch(error => {
                        /*if (error.response.status === 401) {
                            this.errorMessageValue = "Неверный логин или пароль";
                        } else
                            this.errorMessageValue = error;*/
                        this.errorMessageValue = error.response.data;
                        this.isErrorMessageHidden = false;
                    })
            },
            processRegister : function () {
                axios.post("api/auth/registration", {
                    "username": this.login,
                    "password": this.password
                })
                    .then(() => {
                        this.$router.push("home");
                    })
                    .catch(error => {
                        if (error.response.status === 401) {
                            this.errorMessageValue = "Неверный логин или пароль";
                        } else
                            this.errorMessageValue = error;
                        this.isErrorMessageHidden = false;
                    })
            }
        }
    }
</script>