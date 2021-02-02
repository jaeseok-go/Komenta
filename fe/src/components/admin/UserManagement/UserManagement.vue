<template>
  <b-container>
    <div>
      <router-link :to="{name:'AllUser', params: {allUserList:userList}}">전체 회원</router-link> |
      <router-link :to="{name:'BlockedUser', params: {blockedList:blockedUserList}}">제한 회원</router-link> |
      <router-link :to="{name:'AdminUser', params: {adminList:adminList}}">관리자 회원</router-link>
    </div>
    <router-view></router-view>
    <v-app id="list-sample">
      <v-flex sm6 offset-sm3>
        <v-list two-line v-for="(listItem, index) in calData" :key="index">
          <v-list-tile>
            <v-list-tile-content>
              <v-list-tile-title class="text--primary">
                {{ listItem.u_nickname }}
              </v-list-tile-title>
              <v-list-tile-sub-title>
                {{ listItem.u_email }}
              </v-list-tile-sub-title>
            </v-list-tile-content>

            <v-list-tile-action>
              <v-icon ripple>
                edit
              </v-icon>
              <v-icon ripple>
                delete
              </v-icon>
            </v-list-tile-action>
          </v-list-tile>
          <v-divider></v-divider>
        </v-list>
        <br/>
        <v-pagination v-model="curPageNum" :length="numOfPages">
        </v-pagination>
      </v-flex>
    </v-app>
  </b-container>
</template>
<script>
import { fetchAllUsers } from '@/api/user';
export default { 
  data() {
    return {
      userList:[],
      blockedUserList:[],
      adminList:[],
      dataPerPage: 5,
      curPageNum: 1,
    }
  },
  created() {
    this.getUserList();
    console.log("뭐가 먼저 들어오나...",this.userList);
    this.pushLink();
  },
  computed:{
    startOffset() {
      return ((this.curPageNum - 1) * this.dataPerPage);
    },
    endOffset() {
      return (this.startOffset + this.dataPerPage);
    },  
    numOfPages() {
      return Math.ceil(this.userList.length / this.dataPerPage);
    },
    calData() {
      return this.userList.slice(this.startOffset, this.endOffset)
    }
  },
  methods: {
    pushLink(){
      setTimeout(()=>{
        this.$router.push({name:'AllUser', params: {allUserList:this.userList}});
      },50);
    },
    async getUserList() {
      const response = await fetchAllUsers();
      console.log("회원 목록 : ",response);

      for (let user = 0; user < response.data.length; user++) {
        if(response.data[user].u_is_admin) {
          response.data[user].u_state = '관리자';
        }else {
          if(response.data[user].u_is_blocked) {
            response.data[user].u_state = '제한회원';
          }else {
            response.data[user].u_state = '일반회원';
          }
        }
      }
      this.userList = response.data;

      //각 list에 저장
      for(let num = 0; num<response.data.length; num++) {
        if(response.data[num].u_is_admin){
          this.adminList = this.adminList.concat(response.data[num]);
        }else{
          if(response.data[num].u_is_blocked){
            this.blockedUserList = this.blockedUserList.concat(response.data[num]);
          }
        }
      }
    },
  },
}
</script>

<style>

</style>