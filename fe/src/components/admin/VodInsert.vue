<template>
    <b-container fluid>
      <!-- VOD 등록하는 부분 입니다. -->
      <div class="vod-insert">
        <b-col>
          <h2>VOD 등록</h2>
          <b-form class="vod-insert-form">
            <div class="category-select">
              <div class="__select-form select-first">
                <b-select v-model="genre_id" @change="detailGenre()">
                  <option selected disabled>종류</option>
                  <option v-for="(item, index) in genre_list" :value="item.g_id" :key="index">
                    {{ item.g_name }}
                  </option>
                </b-select>
              </div>
              <div class="__select-form">
                <b-select v-model="genre_detail_id" @change="showVodList()">
                  <option selected disabled>장르</option>
                  <option v-for="(item, index) in genre_detail_list" :value="item.gd_id" :key="index">
                    {{ item.gd_name }}
                  </option>
                </b-select>
              </div>
              <div class="__select-form">
                <b-select v-model="vod" @change="autoWriteVodInfo()" ref="directInput">
                  <option selected>직접 입력</option>
                  <option v-for="(item, index) in vod_list_by_gd" :value="item" :key="index">
                    {{ item.v_title }}
                  </option>
                </b-select>
              </div>
            </div>

            <input type="hidden" id="v_id" v-model="vod_all.v_id">
            <b-input type="text" id="v_title" v-model="vod_all.v_title" placeholder="VOD 제목을 입력하세요."></b-input>
            <b-input type="text" id="v_summary" v-model="vod_all.v_summary" placeholder="VOD 전체 줄거리를 입력하세요."></b-input>
            <b-input type="text" id="v_director" v-model="vod_all.v_director" placeholder="감독 정보를 입력하세요."></b-input>
            <b-input type="text" id="v_actors" v-model="vod_all.v_actors" placeholder="출연진 정보를 입력하세요."></b-input>
            <b-input type="text" id="v_age_grade" v-model="vod_all.v_age_grade" placeholder="권장 시청 연령대를 입력하세요."></b-input> 
            
            <b-input type="text" id="ve_episode_num" v-model="vod_all.ve_episode_num" placeholder="회차 정보를 입력하세요."></b-input>
            <b-input type="text" id="ve_contents" v-model="vod_all.ve_contents" placeholder="회차의 줄거리를 입력하세요."></b-input>
            
            <label for="file">VOD</label>
            <input type="file" id="file" ref="file" v-on:change="handleFileUpload()" required accept="video/mp4"><br>
            <label for="v_poster">POSTER</label>
            <input type="file" id="v_poster" ref="file1" v-on:change="handleFileUpload1()" required>
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
            </b-tr>
            <b-tr v-for="all_episode in all_episode" :key=all_episode.ve_id>
              <b-td>{{ all_episode.ve_id }}</b-td>
              <b-td>{{ all_episode.v_title }}-{{ all_episode.ve_episode_num }}화</b-td>
              <b-td>{{ all_episode.g_name }}/{{all_episode.gd_name}}</b-td>
              <b-td>{{ all_episode.ve_upload_date }}</b-td>
              <b-td>{{ all_episode.ve_admin }}</b-td>
            </b-tr>
          </table>
        </b-col>
      </div>
    </b-container>
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
    })
    .catch(() => {
      alert('VOD 리스트를 가져오는데 문제가 발생했습니다.')
    });

    fetchAllEpi()
    .then((response)=>{
      this.all_episode = response.data;
    })
    .catch(() => {
      alert('VOD episode를 가져오는데 문제가 생겼습니다.');
    });

    fetchAllGenre()
    .then((response) =>{
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
    
    let formData1 = new FormData();
    let vodTitle = this.vod_all.v_title;
    let vodReplace = vodTitle.replace(/(\s*)/g, "");
    formData1.append("file",this.file, String(this.vod_all.gd_id+'_'+vodReplace+"_"+this.vod_all.ve_episode_num+'화.mp4'))
    
    let formData2 = new FormData();
    formData2.append("v_poster", this.file1, String(this.vod_all.gd_id+'_'+this.vod_all.v_title+'.jpg'));
    this.vod_all.v_poster = String(this.vod_all.gd_id+'_'+this.vod_all.v_title);

    let errNum = 0;

    await sendVODInfo(this.vod_all)
    .catch(()=>{
      alert("vod 정보를 업로드 하던 중 오류가 발생했습니다.")
      errNum++;
      return;
    });
     
    await insertVOD(formData1)
    .catch(()=>{
      alert("vod 영상을 업로드 하던 중 오류가 발생했습니다.")
      errNum++;
      return;
    });
     
    await insertVodPoster(formData2)
    .catch(()=>{
      alert("vod 포스터를 업로드 하던 중 오류가 발생했습니다.")
      errNum++;
      return;
    })

    if(errNum == 0){
      alert("VOD가 정상적으로 등록되었습니다.");
      window.location.reload();
    }
  }
  }
};
</script>
