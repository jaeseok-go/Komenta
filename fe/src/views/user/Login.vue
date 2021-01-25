<template>
    <div>
        <div>
            <h1 class="title-logo">Komenta</h1>
            <hr>
        </div>
        <button @click="signOutGoogle">Logout</button>
        <!-- <h1>하이</h1> -->
        <LoginForm></LoginForm>

        <!-- <button @click="signOutGoogle" >Logout</button> -->
    </div>
  
</template>

<script>
// import '@/assets/css/app.css';
import LoginForm from '../../components/LoginForm'
// import firebase from 'firebase'

export default {
    data() {
        return {
            isSignin:false,
        }
    },
    components: {
        LoginForm
    },
    methods: {
        async signOutGoogle() {
        const response = await this.$firebase.auth().signOut()
        .then((res)=>console.log(res))
        .catch((err)=>console.error(err))
        console.log('로그아웃',response)
        }
    },
    created () {
    // 현재 로그인한 사용자를 가져오는 함수
    // 방법1
    this.$firebase.auth().onAuthStateChanged(function(user) {
      if (user) {
        // User is signed in.
        console.log('구글유저있당',user);
      } else {
          console.log('구글유저없당')
        // No user is signed in.
      }
    });
    // 방법2 사용자가 로그인 상태가 아니라면 currentUser 값이 null값
    // const user = this.$firebase.auth().currentUser;
    // console.log('구글유저야아아',user);
    // if(user) {
    //   this.isSignin = true;
    // }
  },

}
</script>

<style>

</style>