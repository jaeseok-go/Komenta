<template>
  <div>
    <b-container>
      <!-- VOD 등록하는 부분 입니다. -->
      <b-col>
        <button @click="test">replace 버튼</button>
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
          <b-select v-model="vod" @change="autoWriteVodInfo()" ref="directInput">
            <option selected>직접 입력</option>
            <option v-for="(item, index) in vod_list_by_gd" :value="item" :key="index">
              {{ item.v_title }}
            </option>
          </b-select>
          <br>
          <input type="hidden" id="v_id" v-model="vod_all.v_id">
          제목<b-input type="text" id="v_title" v-model="vod_all.v_title"></b-input>
          요약<b-input type="text" id="v_summary" v-model="vod_all.v_summary"></b-input>
          감독<b-input type="text" id="v_director" v-model="vod_all.v_director"></b-input>
          출연진<b-input type="text" id="v_actors" v-model="vod_all.v_actors"></b-input>
          연령<b-input type="text" id="v_age_grade" v-model="vod_all.v_age_grade"></b-input> 
          
          몇화<b-input type="text" id="ve_episode_num" v-model="vod_all.ve_episode_num"></b-input>
          해당 화 내용<b-input type="text" id="ve_contents" v-model="vod_all.ve_contents"></b-input>
          
          VOD<input type="file" id="file" ref="file" v-on:change="handleFileUpload()" required accept="video/mp4"><br>
          poster<input type="file" id="v_poster" ref="file1" v-on:change="handleFileUpload1()" required>
          <b-button @click="send" class="testbtn">추가</b-button>
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
<style>
.testbtn{
  color : black;
  background-color: gray;
}
</style>

<script>
import {fetchVodList, fetchAllEpi, fetchAllGenre, fetchGenreDetail, fetchVodListByGenreDetailId, sendVODInfo, insertVOD, insertVodPoster} from '@/api/vod';
export default {
  components: { 
  },
  
  data() {
    return {  
      genre_id:0,
      genre_detail_id:0,
      vod_id :0,
      vod:{},
      vod_all:{},
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
      // console.log("VOD 리스트 가져오기 : ",this.vod_list);
    })
    .catch((ex) => {
      alert('VOD 리스트를 가져오는데 문제가 발생했습니다.')
      console.log(ex);
    });

    fetchAllEpi()
    .then((response)=>{
      this.all_episode = response.data;
      // console.log("모든 episode 가져오기 : ",this.all_episode);
    })
    .catch(() => {
      alert('VOD episode를 가져오는데 문제가 생겼습니다.');
    });

    fetchAllGenre()
    .then((response) =>{
      // console.log("모든 장르 가져오기 : ",response.data)
      this.genre_list = response.data;
    })
    .catch(()=>{
      alert('장르를 가져오는데 문제가 생겼습니다.');
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
      //  console.log("genre detail 여깄다"+ response);
     })
     .catch(()=>{
       alert('소장르를 가져오는데 문제가 생겼습니다.');
     })
   },
    showVodList(){ //선택한 장르 기준으로 등록된 VOD list 불러오기
     fetchVodListByGenreDetailId(this.genre_detail_id)
     .then((response) => {
       this.vod_list_by_gd= response.data;
     })
     .catch(()=>{
        alert('해당 장르에 저장된 VOD가 없습니다. 직접 입력을 통해 등록해주세요.');
        this.$refs.directInput.focus();
     })
   },
   autoWriteVodInfo(){
     if(this.vod.v_id > 0){
      for (var step = 0; step < this.vod_list_by_gd.length; step++) {
          if(this.vod_list_by_gd[step].v_id== this.vod.v_id){
            this.vod_all = this.vod_list_by_gd[step];
          }
      }
     }
   },
   async send(){
     
    this.vod_all.gd_id = this.genre_detail_id;
    // let vposter_name = this.file1.name.split('.');
    // this.vod_all.v_poster = vposter_name[0]+'.'+vposter_name[1].toLowerCase();
    // console.log("들어가기전 최종",this.vod_all);
    let formData1 = new FormData();
    let vodTitle = this.vod_all.v_title;
    let vodReplace = vodTitle.replace(" ","");
    formData1.append("file",this.file, String(this.vod_all.gd_id+'_'+vodReplace+"_"+this.vod_all.ve_episode_num+'화.mp4'))
    let formData2 = new FormData();
    // let poster_name = this.file1.name;
    // let extensions = poster_name.split('.');
    formData2.append("v_poster", this.file1, String(this.vod_all.gd_id+'_'+this.vod_all.v_title+'.jpg'));
    this.vod_all.v_poster = String(this.vod_all.gd_id+'_'+this.vod_all.v_title);

    await sendVODInfo(this.vod_all)
    .then((response)=>{
      console.log("vod regist 잘들어감",  response.data);
    })
    .catch((err)=>{
      console.log("vod, vod epi 업로드에러");
      console.log(err)
      alert("vod 정보를 업로드 하던 중 오류가 발생했습니다.")
      return;
    });
     
    await insertVOD(formData1)
    .then((response)=>{
      console.log("video data 잘들어감", response.data);
    })
    .catch((err)=>{
      console.log("video 업로드에러");
      console.log(err)
      alert("vod 영상을 업로드 하던 중 오류가 발생했습니다.")
      return;
    });
     
    await insertVodPoster(formData2)
    .then((response)=>{
      console.log("poster 사진 잘 들어감", response.data);
    })
    .catch((err)=>{
      console.log("poster 업로드에러");
      console.log(err)
      alert("vod 포스터를 업로드 하던 중 오류가 발생했습니다.")
      return;
    })

    alert("VOD가 정상적으로 등록되었습니다.");
    window.location.reload();
  },
  test() {
    
  },
  toVideo(){
    location.href="test";
  }
  }
};
</script>
