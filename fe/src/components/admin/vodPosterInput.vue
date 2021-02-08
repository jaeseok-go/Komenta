<template>
  <div>
    <b-form>
      poster<input type="file" id="v_poster" ref="file1" v-on:change="handleFileUpload1()" required>
      <b-button @click="send" class="testbtn">추가</b-button>
    </b-form>
  </div>
</template>

<script>
import {insertVodPoster} from '@/api/vod'
export default {
  methods: {
    handleFileUpload1(){
      this.file1 = this.$refs.file1.files[0];
    },
    async send(){
      let formData2 = new FormData();
      // let poster_name = this.file1.name;
      // let extensions = poster_name.split('.');
      formData2.append("v_poster", this.file1, String('11_인간극장.jpg'));

      await insertVodPoster(formData2)
      .then((response)=>{
        console.log("poster 사진 잘 들어감", response.data);
      })
      .catch((err)=>{
        console.log("poster 업로드에러");
        console.log(err)
      })

      alert("VOD가 정상적으로 등록되었습니다.");
      window.location.reload();
    }
  },
}
</script>

<style>

</style>