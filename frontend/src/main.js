import Vue from 'vue'
import VueRouter from "vue-router"
import App from './App.vue'
import Home from "./components/home/Home";
import LoginAndRegistration from "./components/auth/LoginAndRegistration";
import NotFoundError from "./components/NotFoundError";

Vue.config.productionTip = false;
Vue.use(VueRouter);

const router = new VueRouter({
    mode: "history",
    routes: [
        {
            path: "/auth",
            name: "auth",
            component: LoginAndRegistration,
            beforeEnter: (to, from, next) => {
                if (localStorage.getItem("token") !== null)
                    next("/home");
                else
                    next();
            }
        },
        {
            path: '/home',
            name: "home",
            component: Home
            /*beforeEnter: (to, from, next) => {
                if (localStorage.getItem("token") !== null)
                    next();
                else
                    next("/auth");
            }*/
        },
        {
            path: "/",
            beforeEnter: (to, from, next) => {
              if (localStorage.getItem("token") !== null)
                next("/home");
              else
                next("/auth");
            }
        },
        {
            path: "/*",
            component: NotFoundError
        }
    ]
});

new Vue({
    render: h => h(App),
    router
}).$mount('#app');