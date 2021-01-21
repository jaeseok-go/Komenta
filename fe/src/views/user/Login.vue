<template>
    <div>
        <div>
            <h1 class="title-logo">Komenta</h1>
            <hr>
        </div>
        <!-- <h1>하이</h1> -->
        <LoginForm></LoginForm>
    </div>
  
</template>

<script>
import '@/assets/css/app.css';
import LoginForm from '../../components/LoginForm'
export default {
    components: {
        LoginForm
    }

}
</script>

<style>

</style>