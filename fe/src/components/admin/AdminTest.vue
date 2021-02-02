<template>
  <div>
    <b-container>
      <!-- VOD 등록하는 부분 입니다. -->
      <b-col>
        <h2>Vod 등록</h2>
        <b-form>
          <b-select v-model="genre_id" @change="detailGenre()">
            <option selected disabled>종류</option>
            <option v-for="(item, index) in genre_list" :value="item.g_id" :key="index">
              {{ item.g_name }}
            </option>
          </b-select>
          <b-select v-model="genre_detail_id" @change="showVodList()">
            <option selected disabled>장르</option>
            <option v-for="(item, index) in genre_detail_list" :value="item.gd_id" :key="index">
              {{ item.gd_name }}
            </option>
          </b-select>
          <b-select v-model="vod" @change="autoWriteVodInfo()">
            <option selected>직접 입력</option>
            <option v-for="(item, index) in vod_list_by_gd" :value="item" :key="index">
              {{ item.v_title }}
            </option>
          </b-select>
          <br>
          <input type="hidden" id="v_id" v-model="vod.v_id">
          제목<b-input type="text" id="v_title" v-model="vod.v_title"></b-input>
          요약<b-input type="text" id="v_summary" v-model="vod.v_summary"></b-input>
          감독<b-input type="text" id="v_director" v-model="vod.v_director"></b-input>
          출연진<b-input type="text" id="v_actors" v-model="vod.v_actors"></b-input>
          연령<b-input type="text" id="v_age_grade" v-model="vod.v_age_grade"></b-input>
          <!-- <b-input type="file" id="v_poster"> -->
          <!-- 포스터<b-input type="file" id="v_poster" v-model="vod.v_poster"></b-input> -->
          
          
          몇화<b-input type="text" id="ve_episode_num" v-model="vod_episode.ve_episode_num"></b-input>
          해당 화 내용<b-input type="text" id="ve_contents" v-model="vod_episode.ve_contents"></b-input>
          <!-- 작성자<b-input type="text" id="ve_admin" v-model="vod_episode.ve_admin" value="admin"></b-input> -->
          <!-- 작성날짜<b-input type="text" id="ve_upload_date" v-model="vod_episode.ve_upload_date" value="0"></b-input> -->
          
          VOD<input type="file" id="file" ref="file" v-on:change="handleFileUpload()" required>
          poster<input type="file" id="v_poster" ref="file1" v-on:change="handleFileUpload1()" required>
          <b-button @click="send" class="testbtn">추가</b-button>
        </b-form>
      </b-col>

      {{vod}} <br>
      {{vod_episode}}

      <b-col>
        <table>
          <b-tr>
            <b-th>등록번호</b-th>
            <b-th>제목</b-th>
            <b-th>종류/장르</b-th>
            <b-th>등록일</b-th>
            <b-th>담당자</b-th>
            <b-th>설정</b-th>
          </b-tr>
          <b-tr @click="toVideo()" v-for="all_episode in all_episode" :key=all_episode.ve_id>
            <b-td>{{ all_episode.ve_id }}</b-td>
            <b-td>{{ all_episode.v_title }}-{{ all_episode.ve_episode_num }}화</b-td>
            <b-td>{{ all_episode.g_name }}/{{all_episode.gd_name}}</b-td>
            <b-td>{{ all_episode.ve_upload_date }}</b-td>
            <b-td>{{ all_episode.ve_admin }}</b-td>
            <b-td>수정/삭제</b-td>
          </b-tr>
        </table>
      </b-col>


      
    </b-container>


  </div>
</template>
<style>
.testbtn{
  color : black;
  background-color: gray;
}
</style>

<script>
import {fetchVodList, fetchAllEpi, fetchAllGenre, fetchGenreDetail, fetchVodListByGenreDetailId} from '@/api/vod';
import axios from 'axios';
export default {
  components: { 
    },
  data() {
    return {  
      genre_id:0,
      genre_detail_id:0,
      vod_id :0,
      vod:{},
      is_exist_vod:false,
      vod_episode :{},
      genre_list:[],
      vod_list_by_gd:[],
      vod_list:[],
      vod_episode_list:[],
      genre_detail_list:[],
      all_episode:[],
      file: '',
      file1: '',
    };
  },
  created(){
    fetchVodList()
    .then((response) => {
      this.vod_list = response.data;
      console.log("VOD 리스트 가져오기 : ",this.vod_list);
    })
    .catch((ex) => {
      console.log(ex);
    });

    fetchAllEpi()
    .then((response)=>{
      this.all_episode = response.data;
      console.log("모든 episode 가져오기 : ",this.all_episode);
    });
    fetchAllGenre()
    .then((response) =>{
      console.log("모든 장르 가져오기 : ",response.data)
      this.genre_list = response.data;
    })
    .catch(()=>{
      alert('genre error');
    });
  },
  methods: {
    handleFileUpload(){
        this.file = this.$refs.file.files[0];
    },
    handleFileUpload1(){
      this.file1 = this.$refs.file1.files[0];
    },
   detailGenre(){ //VOD 종류 불러오기
     fetchGenreDetail(this.genre_id)
     .then((response) => {
       this.genre_detail_list = response.data;
       console.log("genre detail 여깄다"+ response);
     })
     .catch(()=>{
       alert('genre detail error');
     })
   },
    showVodList(){ //선택한 장르 기준으로 등록된 VOD list 불러오기
     fetchVodListByGenreDetailId(this.genre_detail_id)
     .then((response) => {
       this.vod_list_by_gd= response.data;
       this.vod.v_id = response.data.v_id;
       console.log("vod 확인",this.vod);
       console.log("gd에 해당하는 vod 여깄다", response.data);
     })
     .catch(()=>{
       alert('show vod list error');
     })
   },
   autoWriteVodInfo(){
     console.log("vod id : ",this.vod.v_id)
     if(this.vod.v_id > 0){
       this.vod_id = this.vod.v_id; 
       this.vod_episode.v_id = this.vod_id;
       this.is_exist_vod = true;
     }else if(this.vod.v_id == undefined) {
       console.log("직접입력")
       this.vod = {}
     }
   },
   send(){
      console.log("vod :",this.vod);
     console.log("vod episode : ",this.vod_episode);
     console.log("video : ", this.file);
     console.log("poster : ", this.file1);
     let formData = new FormData();
      formData.append('vdto', this.vod);
      formData.append('vedto', this.vod_episode);
      formData.append("file", this.file);
      formData.append("v_poster", this.file1);
      console.log("폼데이터",formData.get("file"));
      axios
     .post('http://localhost:8080/admin/vod_regist',formData, { headers: {
          'Content-Type': 'multipart/form-data'
      }
     })
     .then((response)=>{
       alert(response);
     })
     .catch(()=>{
       console.log("업로드에러");
     })
     
   },
   toVideo(){
     location.href="test";
  }
  }
};
</script>
