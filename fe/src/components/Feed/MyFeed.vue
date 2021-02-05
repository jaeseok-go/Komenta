<template>
  <div>
      <!-- asidebar에서 내피드를 클릭했을때 feed/f_id로 오게 함 -->
      <!-- f_id로 정보 받아서 처리 -->
    <section>
        <div class="userprofile">
            <div class="userprofile__pic"><i class="fad fa-user-circle"></i></div>
            <div class="userprofile__name">1등이다</div>
            <div v-if="showButton">
                <button class="userprofile__button" @click="followUser">FOLLOW</button>
            </div>
        </div>
    </section>

    <section v-if="showMyRecent">

        <div>
        <!-- 최근 본 VOD div -->
            <h1>나의 최근 시청목록보기</h1>
                <div class='drop-zone'
                @dragover.prevent
                @dragenter.prevent
                >
            <!-- 최근 본 VOD 리스트 중 하나씩 v-for돌림 -->
                    <div 
                    v-for='vod in myrecentlists.episodeList' 
                    :key='vod.ve_id' 
                    class='drag-el'
                    draggable
                    @dragstart='startDrag($event, vod)'
                    >
                        {{ vod.v_title }}
                    </div>
                </div>
        </div>

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
                <input type="text" v-model="plName" placeholder="플레이리스트 제목을 적어주세요.">
                <input type="text" v-model="plComment">
                <button @click="createPlaylist">제출</button>
            </p>

        </Modal>
                <!-- 플레이리스트 수만큼 drop-zon v-for -->
        <h1>플레이리스트 목록</h1>
        <div 
        v-for='(playlist,index) in playlists' 
        :key='playlist[0].pl_id'
        height="300"
        >
        {{playlist[0].pl_name}}
            <div class='drop-zone'
            @drop='onDrop($event, playlist[0].pl_id,index)'
            @dragover.prevent
            @dragenter.prevent
            >
            <!-- 한 플레이리스트의 컨텐츠만큼 v-for(5개씩 보여주면 옆으로 넘기는 식으로 해야될것같음) -->
            <!-- startDrag -1이면  -->
            <div
                v-for='(vod,index) in playlist[1]' 
                :key='vod.ve_id' 
                class='drag-el'
                draggable
                @dragstart='startDrag($event, vod)'
                 @click="showVodEpiModal(index)"
            >
                <span >{{vod.v_title}}</span>
                <Modal v-if="selectedId == index && vodEpiModal" @close="vodEpiModal=false">
                    <h3 slot="header">
                    <span
                       @click="goEpiDetail(vod.ve_id)"
                       >{{ playlist.pl_name }}</span> 
                    <i class="closeModalBtn fa fa-times"
                    aria-hidden="true"
                    @click="vodEpiModal = false">
                    </i>
                    </h3>
                    <p slot="body">
                        {{vod.v_title}}
                        <input type="text" v-model="epiComment">
                        <button @click="addComment">리뷰작성</button>
                    </p>

                </Modal>
            </div>
            </div>
    </div>
        
    </section>

    <section v-else>
        <h3>타유저 플레이리스트</h3>
        <div 
        v-for='playlist in playlists' 
        :key='playlist[0].pl_id'
        height="300"
        >
        {{playlist[0].pl_name}}
            <div class='drop-zone'>
            <!-- 한 플레이리스트의 컨텐츠만큼 v-for(5개씩 보여주면 옆으로 넘기는 식으로 해야될것같음) -->
            <!-- startDrag -1이면  -->
            <div
                v-for='(vod,index) in playlist[1]' 
                :key='vod.ve_id' 
                class='drag-el'
                
               >
               <Modal v-if="selectedId == index && vodEpiModal" @close="vodEpiModal=false">
                    <h3 slot="header">
                    {{ playlist.pl_name }}
                    {{ vod.v_title }}
                    <i class="closeModalBtn fa fa-times"
                    aria-hidden="true"
                    @click="vodEpiModal = false">
                    </i>
                    </h3>
                    <p slot="body">
                        {{vod.pl_comment}}
                        <button  @click="goEpiDetail(vod.ve_id)">지금시청하기</button>
                    </p>

                </Modal>
                
            </div>
            </div>
            </div>
    </section>

    


  </div>
</template>

<script>
import { mapState } from 'vuex';
// import store from '@/stores/modules/user'
import { fetchRecentPlaylist, fetchMyPlaylist,addPlaylist, modifyfollow } from '@/api/user'
import { fetchVodEpiDetail } from '@/api/vod'
import Modal from '@/components/common/Modal';
import store from '@/stores/modules/user'

export default {
    
    components: {
        Modal,
    },
    data(){
        return {
            myrecentlists : {
                "historyList": [
                    {
                        vh_id: 1,
                    }
                ],
                "episodeList": [
                    {
                        ve_id: 1,
                        v_title: "test1",
                        

                    },
                    {
                        ve_id: 2,
                        v_title: "test2",

                    },
                    {
                        ve_id: 3,
                        v_title: "test3",

                    },
                ]
            },
            playlists : [ [
                {
                    pl_id : 0,
                    pl_name : "겁나재밌는 플레이리스트 모음",
                    pl_commnet : "string",
                },
                [
                {   
                    ve_id : 4,
                    v_title : "런닝맨",
                    v_poster:'poster1',
                    ve_episode_num : 0,
                    vh_commnet : "string",
                },
                {   
                    ve_id : 5,
                    v_title : "최고다",
                    v_poster:'poster2',
                    ve_episode_num : 0,
                    vh_commnet : "string",
                },
                ]
            ],  
            [
                {
                    pl_id : 1,
                    pl_name : "우리팀 1등하자",
                    pl_commnet : "string",
                },
                [
                {   
                    ve_id : 7,
                    v_title : "우리 모두 힘내서",
                    v_poster:'poster4',
                    ve_episode_num : 0,
                    vh_commnet : "string",
                },
                {   
                    ve_id : 8,
                    v_title : "성공해용",
                    v_poster:'poster5',
                    ve_episode_num : 0,
                    vh_commnet : "string",
                },
                ]
            ],  
            ],
            showModal:false,
            vodEpiModal:false,
            showFollow:false,
            showRecent:false,
            dragVod:{}, 
            plName:'',
            plComment:'',
            // plComment:`${this.fetchedUserInfo.u_nickname}님의 플레이리스트`,
            epiComment:'',     
            selectedId:0,
        }
    },
    created(){
       this.getRecentPlayList();
       this.getUserPlayList();
    },
    methods: {        
        // drag and drop 메소드
        // drag 시작한 vod
        startDrag: (evt, vod) => {
        evt.dataTransfer.dropEffect = 'move'
        evt.dataTransfer.effectAllowed = 'move'
        evt.dataTransfer.setData('vodID', vod.ve_id)
        },
        // vod를 끌어다가 놓는 플레이리스트
        onDrop (evt, plId,index) {
        const vodID = evt.dataTransfer.getData('vodID')
        const vod = this.myrecentlists.episodeList.find(vod => vod.ve_id == vodID)
        this.dragIndex = this.myrecentlists.episodeList.indexOf(vod,0)
        console.log(this.dragIndex)
        console.log(plId,vod,'플레이리스트ID')
        // 해당 vod를 플레이리스트에 추가
        this.myrecentlists.episodeList.pop(this.dragIndex)
        // 플레이리스트추가api(vod.ve_id,plId)
        // 아래 코드에 보면 플레이리스트에 추가 돼있음 근데 vod 이름이 안들어감,,이건 data받아올거니까 바뀔때마다 새로 보이게 해야겠다!
        // this.playlists[index][1].splice(0,0,vod)
        this.playlists[index][1].push(vod)
        console.log('플레이리스트 추가',this.playlists[index])
        const epiInfo = {
            u_id:this.fetchedUserInfo.u_id ,
            ve_id:vod.ve_id,
            pl_id:plId
            }
        console.log(epiInfo,'플레이리스트에 추가할 epi')
        },
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
            // if (this.fetchedUserInfo.u_id == this.fetchedUserFeedInfo.u_id) {
            //     this.showRecent=true
            // }
        },
        //팔로우하는 로직 추가 구현
        followUser() {
            const your_id = this.$route.params.id;
            console.log(your_id,'유어')
            const my_id = this.userInfo.u_id
            console.log(store.userInfo,'유저인포?????왜안들어외??진짜,,궁금...')
            console.log(my_id,'마이')

            const bothId = { u_id : my_id, f_id :your_id }
            const response = modifyfollow(bothId)
            console.log(response)

        },
        showVodEpiModal(index){
            this.selectedId = index;
            console.log(this.selectedId)
            this.vodEpiModal=true
        },
        addComment(plId){
            const userId = this.fetchedUserInfo.u_id
            const commentInfo = {
                pl_id : plId,
                u_id : userId,
                pl_comment : this.epiComment
            }
            console.log(commentInfo,'플레이리스트 comment생성 실패')
        },
        async createPlaylist() {
            const playlistinfo = {
                pl_name:this.plName,
                pl_comment:this.plComment,
            }
            try {
                await addPlaylist(playlistinfo)
            } catch {
                console.log('플레이리스트 생성 실패')
            }

        },
        async goEpiDetail(veId){
            await fetchVodEpiDetail(veId)
            .then(this.$router.push(`/voddetail/${veId}`))
            .catch(err => console.log(err))
        },
        async getRecentPlayList() {
        const userId = this.$route.params.id;
        try {
            // userId를 같이 안보내주고 토큰으로??
            const res = await fetchRecentPlaylist()
            // this.myrecentlists = res.data
            console.log(this.myrecentlists,userId,res,'???????????')
        } catch {
            console.log('에러')
        }
        },
        async getUserPlayList() {
        const userId = this.$route.params.id;
        try {
            const res = await fetchMyPlaylist(userId)
            this.playlists = res.data
            console.log(this.playlists,this.recentlistLen)
        } catch {
            console.log('에러')
        }
        },


    },

    computed: {
        ...mapState({
        userInfo: state => state.user.userInfo,
    }),
  },


}
</script>


<style>
.drop-zone {
  background-color: #eee;
  margin-bottom: 10px;
  padding: 10px;
}

.drag-el {
  background-color: #fff;
  margin-bottom: 10px;
  padding: 5px;
}

</style>
