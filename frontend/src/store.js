import Vuex from "vuex";
import Vue from 'vue'
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        token : "",
        tokenDate : null,
        points : []
    },
    mutations: {
        setToken(state, tokenString) {
            state.token = tokenString;
            state.tokenDate = new Date().getTime() + 3600000;
        },
        setPoints(state, pointsArray) {
            state.points = pointsArray;
        }
    },
    plugins: [createPersistedState()]
});

export default store