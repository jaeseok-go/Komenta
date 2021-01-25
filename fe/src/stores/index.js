import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state:{
        userId:"",
        password: "",
        passwordConfirm: "",
        username:"",
        nickName: "",
        userPhoneNumber: "",
        // 추가
        platform : "",
    },
    mutations:{
        setUser(state,payload){
            state.usename = payload.name;
            state.platform = payload.platform;
        }
    }

});

