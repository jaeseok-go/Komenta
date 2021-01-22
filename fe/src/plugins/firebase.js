import Vue from 'vue';
import firebase from "firebase/app";
// Add the Firebase services that you want to use
import "firebase/auth";
import 'firebase/database';
// import 'firebase/firestore'

import firebaseConfig from '../../firebaseConfig';

firebase.initializeApp(firebaseConfig)

Vue.prototype.$firebase = firebase

export const GoogleProvider = new firebase.auth.GoogleAuthProvider()
export const auth = firebase.auth()

export default context => {
    const { store } = context
    return new Promise((resolve, reject) => {
        console.log(reject)
        auth.onAuthStateChanged(user => {
            if (user){
                return resolve(store.commit('setUser', user))
            }
            return resolve(store.commit('setUser', null))
        })
    })
}