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
        <button @click="commentDelete">{{editBtnText}}</button>
      </div>
      <table>
        <tr>
          <td>VOD 명</td>
          <!-- 특정 글자 수 넘으면 ...처리 -->
          <td>댓글 내용</td>
          <td>좋아요</td>
          <td>대댓글</td>
          <td>날짜</td>
          <!-- <td :style="{display:editDisplay}">삭제</td> -->
        </tr>
        <tr v-if="commentContents.length == 0">
          <td colspan="5">등록한 댓글이 없습니다.</td>
        </tr>
        <!-- DB에 저장된 값 불러오기 기능 -->
        <tr v-for="(comm, index) in paginatedData" :key="index" v-else>
          <td>{{comm.v_title}} {{comm.ve_episode_num}}화</td>
          <td>{{comm.c_contents}}</td>
          <td>{{comm.comment_good_count}}개</td>
          <td>{{comm.re_comment_count}}개</td>
          <td>{{comm.c_upload_time}}</td>
          <!-- <td :style="{display:editDisplay}"><input type="checkbox" :id="index" :vlaue="index" :v-model="deleteList"></td> -->
        </tr>
      </table>
      <div class="btn-cover">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">이전</button>
        <span class="page-count">{{pageNum+1}}/{{pageCount}} 페이지 </span>
        <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">다음</button>
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
import { fetchMyComment } from '@/api/user';
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
  computed: {
    ...mapState({
      userInfo: state => state.user.userInfo
    }),
    pageCount() {
      let listLeng = this.commentContents.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);

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
    async fetchMyComment(){
      const response = await fetchMyComment(this.userInfo.u_id);
      console.log("내 댓글 : ",response)
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
  table {
    width: 100%;
    border-collapse: collapse;
  }
  table th {
    font-size: 1.2rem;
  }
  table tr {
    height: 2rem;
    text-align: center;
    border-bottom: 1px solid #505050;
  }
  table tr:first-of-type {
    border-top: 2px solid #404040;
  }
  table tr td {
    padding: 0.7rem 0;
    font-size: 1rem;
  }

  .btn-cover {
    margin-top: 1.5rem;
    text-align: center;
  }
  .btn-cover .page-btn {
    width: 5rem;
    height: 2rem;
    letter-spacing: 0.5px;
  }
  .btn-cover .page-count {
    padding: 0 1rem;
  }

  .btn-right {
    text-align: right;
  }

  .btn-right .inline-block {
    margin: 0 5px;
  }
</style>
//   {
//     c_num : '441331',
//     c_name : '사랑의 불시착',
//     c_ep_num : '12',
//     c_contents: '그래서 리정혁같은 남자 어디서 만나는데...',
//     c_liek_num : '1325',
//     c_reply_num : '55',
//     c_register_data: '2021-01-02'
//   },
//   {
//     c_num : '441330',
//     c_name : '경이로운 소문',
//     c_ep_num : '7',
//     c_contents: '우리 소문이ㅠㅠㅠㅠ죽지마ㅜㅜㅜ',
//     c_liek_num : '552',
//     c_reply_num : '13',
//     c_register_data: '2020-07-11'
//   },
//   {
//     c_num : '441001',
//     c_name : '사랑의 불시착',
//     c_ep_num : '10',
//     c_contents: '세리언니 그냥 나랑 살아',
//     c_liek_num : '15',
//     c_reply_num : '5',
//     c_register_data: '2020-03-25'
//   },
//   {
//     c_num : '413552',
//     c_name : '아는형님',
//     c_ep_num : '325',
//     c_contents: '이용진 겁나 웃기넼ㅋㅋㅋㅋㅋ',
//     c_liek_num : '100',
//     c_reply_num : '41',
//     c_register_data: '2019-05-24'
//   }
