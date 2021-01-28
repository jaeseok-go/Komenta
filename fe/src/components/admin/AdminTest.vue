<template>
  <div>
    <b-container>
      <!-- VOD 등록하는 부분 입니다. -->
      <b-col>
    
        <h2>Vod 등록</h2>
        <b-form>
          <select v-model="genre_id" @change="detailGenre()">
            <option selected disabled>종류</option>
            <option v-for="(item, index) in genre_list" :value="item.g_id" :key="index">
              {{ item.g_name }}
            </option>
          </select>
          <select v-model="genre_detail_id" @change="showVodList()">
            <option selected disabled>장르</option>
            <option v-for="(item, index) in genre_detail_list" :value="item.gd_id" :key="index">
              {{ item.gd_name }}
            </option>
          </select>
          <select v-model="vod" @change="autoWriteVodInfo()">
            <option selected>직접 입력</option>
            <option v-for="(item, index) in vod_list_by_gd" :value="item" :key="index">
              {{ item.v_title }}
            </option>
          </select>
          번호<b-input type="text" id="v_id" v-model="vod.v_id"></b-input>
          제목<b-input type="text" id="v_title" v-model="vod.v_title"></b-input>
          요약<b-input type="text" id="v_summary" v-model="vod.v_summary"></b-input>
          감독<b-input type="text" id="v_director" v-model="vod.v_director"></b-input>
          출연진<b-input type="text" id="v_actors" v-model="vod.v_actors"></b-input>
          연령<b-input type="text" id="v_age_grade" v-model="vod.v_age_grade"></b-input>
          <!-- <b-input type="file" id="v_poster"> -->
          포스터<b-input type="text" id="v_poster" v-model="vod.v_poster"></b-input>
          
          
          몇화<b-input type="text" id="ve_episode_num" v-model="vod_episode.ve_episode_num"></b-input>
          해당 화 내용<b-input type="text" id="ve_contents" v-model="vod_episode.ve_contents"></b-input>
          작성자<b-input type="text" id="ve_admin" v-model="vod_episode.ve_admin" value="admin"></b-input>
          작성날짜<b-input type="text" id="ve_upload_date" v-model="vod_episode.ve_upload_date" value="0"></b-input>
          
          VOD<input type="file" id="file" required>
          <b-button @click="send">추가</b-button>
        </b-form>
      </b-col>


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

<script>
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
      vod_episode :{
        ve_id:0, v_id:0, ve_episode_num:0, ve_contents:'', ve_admin:'', ve_upload_date:''
      },
      genre_list:[],
      vod_list_by_gd:[],
      vod_list:[],
      vod_episode_list:[],
      genre_detail_list:[],
      all_episode:[],
    };
  },
  created(){
    axios
    .get(
        'http://localhost:8080/admin/vod_list'
      )
      .then((response) => {
        this.vod_list = response.data;
        console.log(this.vod_list);
      })
      .catch((ex) => {
        console.log(ex);
      });
    axios.get('http://localhost:8080/admin/episode_all')
    .then((response)=>{
      this.all_episode = response.data;
      console.log(this.all_episode);
    })
    axios
    .get( 'http://localhost:8080/genre/list_genre')
    .then((response) =>{
      console.log(response.data)
      this.genre_list = response.data;
    } )
    .catch(()=>{
      alert('genre error');
    });
  },
  methods: {
   detailGenre(){
     axios.get( 'http://localhost:8080/genre/list_genre_detail',{params:{g_id: this.genre_id}})
     .then((response) => {
       this.genre_detail_list = response.data;
       console.log("genre detila 여깄다"+ response.data);
     })
     .catch(()=>{
       alert('genre detail error');
     })
   },
    showVodList(){
     axios.get( 'http://localhost:8080/admin/vod_list_by_gdid/'+this.genre_detail_id)
     .then((response) => {
       this.vod_list_by_gd= response.data;
       this.vod.v_id = response.data.v_id;
       console.log(this.vod);
       console.log("gd에 해당하는 vod 여깄다", response.data);
     })
     .catch(()=>{
       alert('show vod list error');
     })
   },
   autoWriteVodInfo(){
     if(this.vod.v_id > 0){
       this.vod_id = this.vod.v_id; 
       this.vod_episode.v_id = this.vod_id;
       this.is_exist_vod = true;}
   },
   send(){
     if(this.is_exist_vod == true){
       console.log(this.vod_episode);
      axios
     .post('http://localhost:8080/admin/episode_upload', this.vod_episode)
     .then((response)=>{

       console.log(response.data);
     })
     .catch(()=>{
       console.log("업로드에러");
     })
     }
     else{
       axios
     .post('http://localhost:8080/admin/vod_regist', this.vod)
     .then((response)=>{
       alert(response);
     })
     .catch(()=>{
       console.log("업로드에러");
     })
     }
     
   },
   toVideo(){
     location.href="test";
   }
  }
};
</script>
