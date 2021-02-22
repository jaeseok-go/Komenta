<template>
    <div class="sns-login-logo">
        <button @click="handleClickSignIn">
          <img src="@/assets/images/btn_google_signin_light_normal_web@2x.png" width="70%" alt="">
        </button>
    </div>
</template>

<script>
import { dupIdChk }  from '@/api/user'

export default {
    name: 'GoogleLogin',
    data() {
        return {
            isInit: false,
            isSignIn: false,
        };
    },
    methods: {
      async handleClickUpdateScope() {
      const option = new window.gapi.auth2.SigninOptionsBuilder();
      option.setScope("email https://www.googleapis.com/auth/drive.file");
      const googleUser = this.$gAuth.GoogleAuth.currentUser.get();
      try {
        await googleUser.grant(option);
      } catch (error) {
        console.error(error);
      }
    },

    handleClickLogin() {
      this.$gAuth
        .getAuthCode()
        .catch((error) => {
            console.log(error)
            this.handleClickSignIn();
        });
    },

    async handleClickSignIn() {
      try {
        const googleUser = await this.$gAuth.signIn();
          // 로그인한 아이디였으면!
          const response  = await dupIdChk(googleUser.Fs.lt)
          if (response.data) {
            await this.$store.dispatch('LOGIN',{
            u_email:googleUser.Fs.lt,
            u_pw:googleUser.Fs.lt
          })
          this.$router.push(`/main/vodpopular`);
          return
          }
        const userData = {
          u_email:googleUser.Fs.lt,
            u_pw: googleUser.Fs.lt,
            u_phone_number : null,
            u_nickname : googleUser.Fs.sd,
        };
        this.$store.commit('fetchInfo',userData);
        this.isSignIn = this.$gAuth.isAuthorized;
        if (userData.u_phone_number === null) {
            this.$router.push('/member/certification');
        }
      } catch (error) {
        console.error(error);
        return null;
      }
    },

    async handleClickSignOut() {
      try {
        await this.$gAuth.signOut();
        this.isSignIn = this.$gAuth.isAuthorized;
      } catch (error) {
        console.error(error);
      }
    },

    handleClickDisconnect() {
      window.location.href = `https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=${window.location.href}`;
    },
  },

  created() {
    let that = this;
    let checkGauthLoad = setInterval(function () {
      that.isInit = that.$gAuth.isInit;
      that.isSignIn = that.$gAuth.isAuthorized;
      if (that.isInit) clearInterval(checkGauthLoad);
    }, 1000);
  },

};
</script>

<style lang="scss" scoped>

</style>