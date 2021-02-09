<template>
    <div>
        <!-- <div class="temp-bc-title">
            <img src="@/imgs/3.png" alt="">
            <h1 class="title-logo">Komenta</h1>
            <hr>
        </div> -->
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
import LoginForm from '../../components/LoginForm'
import Modal from '@/components/common/Modal'


export default {
    data() {
        return {
            showModal:false,
            isSignin:false,
        }
    },
    components: {
        LoginForm,
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
        
    },


}
</script>
