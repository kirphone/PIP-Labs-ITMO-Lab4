import Vue from 'vue'
import VueRouter from "vue-router"
import App from './App.vue'
import Home from "./components/Home";

Vue.config.productionTip = false;
Vue.use(VueRouter);

const router = new VueRouter({
  routes : [
    {
      path: '/home',
      name: "home",
      component: Home
    }
  ]
});

new Vue({
  render: h => h(App),
  router
}).$mount('#app');