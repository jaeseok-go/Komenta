import Vue from 'vue';
import firebase from "firebase/app";
// Add the Firebase services that you want to use
import "firebase/auth";
// import 'firebase/firestore'

import firebaseConfig from '../../firebaseConfig';



firebase.initializeApp(firebaseConfig)

Vue.prototype.$firebase = firebase