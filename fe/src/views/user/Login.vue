<template>
    <div>
        <div class="temp-bc-title">
            <h1 class="title-logo">Komenta</h1>
            <hr>
        </div>
        <!-- <button @click="signOut">Logout</button> -->
        <LoginForm v-on:showModalForm="showModalForm"></LoginForm>
      
        <Modal v-if="showModal" @click="$emit('close')">
            <h3 slot="header">
              <div class="findIdPw__title">아이디 및 비밀번호 찾기</div>
              <span id="closeModalBtn">
                <i class="fa fa-times" aria-hidden="true" @click="showModal = false"></i>
              </span>
              <hr>
              <div class="findIdPw__contents">
                <div>
                 <router-link :to="{name:'FindId'}"><span class="findIdPw__content">아이디 찾기</span></router-link> 
                </div>
                <div>
                  <router-link :to="{name:'FindPw'}"><span class="findIdPw__content">비밀번호 찾기</span></router-link> 
                </div>
              </div>                                                                                                        
            </h3>
            <p slot="body">
              <router-view></router-view>
              </p>
        </Modal>
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
        closeModal() {
          this.showModal = false
          console.log('들어와라,,')
        },
        showModalForm() {
          this.showModal = true
        },
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
          u_nickname : user.displayName,
          u_email : user.email,
          u_phone_number: user.phoneNumber,
          u_pw : user.refreshToken,
          u_id : user.uid  
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

<style scoped>
  .temp-bc-title {
    background-color: #F9F9F9;
  }
</style>