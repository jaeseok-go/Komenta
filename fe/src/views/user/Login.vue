<template>
    <div>
        <div>
            <h1 class="title-logo">Komenta</h1>
            <hr>
        </div>
        <button @click="signOutGoogle" >Logout</button>
        <!-- <h1>하이</h1> -->
        <LoginForm></LoginForm>

        <!-- <button @click="signOutGoogle" >Logout</button> -->
    </div>
  
</template>

<script>
import '@/assets/css/app.css';
import LoginForm from '../../components/LoginForm'
// import firebase from 'firebase'

export default {
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
    }

}
</script>

<style>

</style>