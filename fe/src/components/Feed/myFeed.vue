<template>
  <div>
      <!-- asidebar에서 내피드를 클릭했을때 feed/f_id로 오게 함 -->
      <!-- f_id로 정보 받아서 처리 -->
    <section>
        <h1>유저 프로필 영역</h1>
        <div>{{ fetchedUserFeedInfo.u_profile_pic }}</div>
        <div>{{ fetchedUserFeedInfo.u_nickname }}</div>
        <div v-if="showButton">
            <button @click="followUser">FOLLOW</button>
        </div>
    </section>

    <section v-if="showMyRecent">
        <h1>내가 시청한 콘텐츠 목록</h1>
        <!-- <div>{{ fetchedPlaylist.v_id }}</div> -->
    </section>

    <section>
        <h1>커스튬 콘텐츠 목록</h1>

        <button @click="showButton">+</button>
        <Modal v-if="showModal" @close="showModal=false">
            <h3 slot="header">
            나만의 스트리밍 리스트를 만들어보세요
            <i class="closeModalBtn fa fa-times"
            aria-hidden="true"
            @click="showModal = false">
            </i>
            </h3>
            <p slot="body">
                <input type="text" v-model="pl_name">
                <button @click="addPlaylist">제출</button>
            </p>

        </Modal>
        <div>
            <li></li>
        </div>
    </section>

    


  </div>
</template>

<script>
import { mapGetters } from 'vuex';

import Modal from '@/components/common/Modal';
export default {
    components: {
        Modal
    },
    data() {
        return {
            showModal:false,
            showFollow:false,
            showRecent:false,
            pl_name:'',
        }
    },
    methods: {        
        showModalForm() {
            this.showModal=true
        },
        showButton() {
            this.showModal=true
            // if (this.fetchedUserInfo.u_id !== this.fetchedUserFeedInfo.u_id) {
            //     this.showFollow=true
            // }
        },
        showMyRecent() {
            if (this.fetchedUserInfo.u_id == this.fetchedUserFeedInfo.u_id) {
                this.showRecent=true
            }
        },
        //팔로우하는 로직 추가 구현
        followUser() {

        },
        addPlaylist() {
            const userId = this.fetchedUserInfo.u_id
            const pl_name = this.pl_name
            const data = {userId, pl_name}
            this.$store.dispatch('ADD_PLAYLIST',data)
        }
    },

    computed: {
        //fetchedUserFeedInfo에 있어야 하는 정보는 pl_id,pl_name,pl_comment,u_nickname
        //u_profile_pic,v_poster, g_name ,gd_name,v_title ,ve_episode_num,vh_comment
        ...mapGetters(['fetchedUserFeedInfo']),
        ...mapGetters(['fetchedUserInfo']),
        ...mapGetters(['fetchedPlaylist']) 
  },
    created() {
        const userId = this.$route.params.id;
        this.$store.dispatch('FETCH_FEED',userId)

    },


}
</script>

<style>

</style>