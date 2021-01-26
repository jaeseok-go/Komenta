<template>
    <div>
        <div>
            <h1 class="title-logo">Komenta</h1>
            <hr>
        </div>
        <button @click="signOut">Logout</button>
        <!-- <h1>하이</h1> -->
        
        <LoginForm></LoginForm>

        <button @click="modalshow">??</button>
             <Modal v-if="showModal" @close="showModal = false">
      <h3 slot="header">
          <h1>하이..</h1>
        <router-link :to="{name:'FindId'}">아이디 찾기</router-link> |
        <router-link :to="{name:'FindPw'}">비밀번호 찾기</router-link> |
        <i class="closeModalBtn fa fa-times"
          aria-hidden="true"
          @click="showModal = false">
        </i>
      </h3>
      <p slot="body"><router-view></router-view></p>
    </Modal>

        <!-- <button @click="signOutGoogle" >Logout</button> -->
    </div>
  
</template>

<script>
// import '@/assets/css/app.css';
import LoginForm from '../../components/LoginForm'
import Modal from '@/components/common/Modal'

// import FindIdPw from '@/views/user/FindIdPw'
// import firebase from 'firebase'

export default {
    data() {
        return {
            showModal:false,
            isSignin:false,
        }
    },
    components: {
        LoginForm,
        // FindIdPw,
        Modal,
    },
    methods: {
        async signOut() {
        const response = await this.$firebase.auth().signOut()
        .then((res)=>console.log(res))
        .catch((err)=>console.error(err))
        console.log('로그아웃',response)
        },
        
    },
    created () {
    // 현재 로그인한 사용자를 가져오는 함수
    // 방법1
    this.$firebase.auth().onAuthStateChanged((user)=> {
      if (user) {
        // User is signed in.
        console.log('유저있당',user);
        const userInfo = {
          name : user.displayName,
          userId : user.email,
          phoneNumber: user.phoneNumber,
          token : user.refreshToken,
          uid : user.uid  
        }
        console.log('유저정보다',userInfo)
      } else {
          console.log('유저없당')
        // No user is signed in.
      }
    });
    // 방법2 사용자가 로그인 상태가 아니라면 currentUser 값이 null값
    // var user = firebase.auth().currentUser;
    // var name, email, photoUrl, uid, emailVerified;

    // if (user != null) {
    //   name = user.displayName;
    //   email = user.email;
    //   photoUrl = user.photoURL;
    //   emailVerified = user.emailVerified;
    //   uid = user.uid;  // The user's ID, unique to the Firebase project. Do NOT use
    //                   // this value to authenticate with your backend server, if
    //                   // you have one. Use User.getToken() instead.
    // }
  },

}
</script>

<style>

</style>