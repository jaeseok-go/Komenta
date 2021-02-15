<template>
  <b-col>
    <h4>내가 단 댓글</h4>
    <hr>
    <div>
      <div class="btn-right">
        <!-- 전체 리스트 선택하는 기능 -->
        <div class="inline-block" :style="{display:editInlineDisplay}">
          <input type="checkbox" id="allChk" value="allChk" v-model="allChk">
          <!-- <label for="allChk"><font-awesome-icon class="chk-label" :icon="[chkIcon, 'check-circle']" /></label> -->
          <label for="allChk">전체 선택</label>
        </div>
        <!-- 선택한 댓글 삭제 기능 -->
        <p class="inline-block" :style="{display:editInlineDisplay}">삭제</p>
        <!-- <button @click="commentDelete">{{editBtnText}}</button> -->
      </div>
      <table class="table-border-style">
        <tr>
          <td>VOD 명</td>
          <!-- 특정 글자 수 넘으면 ...처리 -->
          <td>댓글 내용</td>
          <td>좋아요</td>
          <td>날짜</td>
          <!-- <td :style="{display:editDisplay}">삭제</td> -->
        </tr>
        <tr v-if="commentContents.length == 0">
          <td colspan="5">등록한 댓글이 없습니다.</td>
        </tr>
        <!-- DB에 저장된 값 불러오기 기능 -->
        <tr v-for="(comm, index) in paginatedData" :key="index" v-else>
          <td @click="goVod(comm.ve_id)">{{comm.v_title}} {{comm.ve_episode_num}}화</td>
          <td>{{comm.c_contents}}</td>
          <td>{{comm.c_good_count}}개</td>
          <td>{{comm.c_upload_time}}</td>
          <!-- <td :style="{display:editDisplay}"><input type="checkbox" :id="index" :vlaue="index" :v-model="deleteList"></td> -->
        </tr>
      </table>
      <div class="admin-btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        <font-awesome-icon :icon="['fas', 'angle-left']"/>
      </button>
      <span class="page-count">{{pageNum+1}}/{{pageCount}} 페이지 </span>
      <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">
        <font-awesome-icon :icon="['fas', 'angle-right']"/>
      </button>
    </div>
      <!-- {{deleteList}} -->
    </div>
    <!-- 
      1. 컨텐츠 명(제목+회차)<br>
      2. 댓글 내용(a태그 -> 해당 회차 페이지로 이동)<br>
      3. 좋아요 수<br>
      4. 대댓글 수<br>
      5. 댓글 쓴 날짜<br>
      6. 편집 버튼 클릭 시, 댓글 수정, 삭제 가능<br> 
    -->
  </b-col>
</template>

<script>
import { fetchUserComment } from '@/api/comment';
import { mapState } from 'vuex';
export default {
  data() {
    return {
      pageNum:0,
      commentContents:[],
      deleteList:[],
      editDisplay:'none',
      editInlineDisplay:'none',
      editBtnText:'편집',
      chkIcon:'far',
      allChk:false,
    }
  },
  props:{
    pageSize: {
      type:Number,
      required: false,
      default: 5
    }
  },
  created(){
    this.getMyComment();
  },
  computed: {
    ...mapState({
      userInfo: state => state.user.userInfo
    }),
    pageCount() {
      let listLeng = this.commentContents.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);
      console.log("commentContents 수 : ",listLeng)
      if(listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;

      return this.commentContents.slice(start, end);
    }
  },
  methods: {
    goVod(veId){
      this.$router.push(`/voddetail/${veId}`)
    },
    async getMyComment(){
      const response = await fetchUserComment(this.userInfo.u_id);
      for(let i = response.data.length-1; i >= 0; i--){
        this.commentContents.push(response.data[i]);
      }
      // this.commentContents = response.data
      console.log("내 댓글 : ",this.commentContents)
    },
    commentDelete() {
      if(this.editDisplay == 'none'){
        this.editDisplay='block';
        this.editInlineDisplay='inline-block';
        this.editBtnText='편집완료';
      }else {
        this.editDisplay='none';
        this.editInlineDisplay='none';
        this.editBtnText='편집';
      }
    },
    // allCheck(){
  //   if(this.allChk) {
  //     this.chkIcon='fas'
  //   }else {
  //     this.chkIcon='far'
  //   }
  // },
  nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
  },
}
</script>

<style>
  .btn-right {
    text-align: right;
  }

  .btn-right .inline-block {
    margin: 0 5px;
  }
</style>