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
            <h3>나의 최근 시청목록보기</h3>
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
                    <!-- {{ vod.v_title }}
                    {{vod.v_poster}} -->
                    <img :src="require('@/assets/images/poster00.jpg')" width="70%">
                    <!-- <img :src="getPoster(vod.v_poster)" width="10%"> -->
                    </div>
                </div>
        </div>

         <button @click="showButton"><i class="fas fa-plus"></i></button>
        <Modal v-if="showModal" @close="showModal=false">
            <h4 slot="header">
            나만의 스트리밍 리스트를 만들어보세요
            <i class="closeModalBtn fa fa-times"
            aria-hidden="true"
            @click="showModal = false">
            </i>
            </h4>
            <p slot="body">
            <input type="text" v-model="plName" placeholder="플레이리스트 제목을 적어주세요.">
            <input type="text" v-model="plComment">
            <button @click="createPlaylist">제출</button>
            </p>

        </Modal>
                <!-- 플레이리스트 수만큼 drop-zon v-for -->

        <div 
        v-for='(playlist,index) in playlists' 
        :key='playlist[0].pl_id'
        height="300"
        >
        {{playlist[0].pl_name}}
            <div class='plylist-zone'
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
                <!-- <img :src=getVodPoster(vod.v_id,vod.v_title) alt="vod.v_poster"> -->
                <span>{{vod.v_title}}</span>
                <Modal v-if="selectedId == index && vodEpiModal" @close="vodEpiModal=false">
                    <h4 slot="header">
                    <span
                       @click="goEpiDetail(vod.ve_id)"
                       >{{ vod.v_title }}</span> 
                    <i class="closeModalBtn fa fa-times"
                    aria-hidden="true"
                    @click="vodEpiModal = false">
                    </i>
                    </h4>
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
        <div 
        v-for='playlist in playlists' 
        :key='playlist[0].pl_id'
        height="300"
        >
        {{playlist[0].pl_name}}
            <div class='plylist-zone'>
            <!-- 한 플레이리스트의 컨텐츠만큼 v-for(5개씩 보여주면 옆으로 넘기는 식으로 해야될것같음) -->
            <!-- startDrag -1이면 -->
            <div
                v-for='(vod,index) in playlist[1]' 
                :key='vod.ve_id' 
                class='drag-el'
               >
                <!-- <img :src=getVodPoster(vod.v_id,vod.v_title) alt="vod.v_poster"> -->
               <Modal v-if="selectedId == index && vodEpiModal" @close="vodEpiModal=false">
                    <h4 slot="header">
                    {{ vod.v_title }}
                    <i class="closeModalBtn fa fa-times"
                    aria-hidden="true"
                    @click="vodEpiModal = false">
                    </i>
                    </h4>
                    <p slot="body">
                        {{vod.pl_comment}}
                        <button  @click="goEpiDetail(index)">지금시청하기</button>
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
import { fetchRecentPlaylist, fetchMyPlaylist,addPlaylist, modifyfollow, modifyPlaylist } from '@/api/user'
import { fetchVodEpiDetail } from '@/api/vod'
import Modal from '@/components/common/Modal';
// import store from '@/stores/modules/user'

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
                        v_poster:'@/assets/images/poster00.jpg'
  
                    },
                    {
                        ve_id: 2,
                        v_title: "test2",
                        v_poster:'@/assets/images/poster01.jpg'

                    },
                    {
                        ve_id: 3,
                        v_title: "test3",
                        v_poster:'@/assets/images/poster02.jpg'

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
                    v_poster:'@/assets/images/poster03.jpg',
                    ve_episode_num : 0,
                    vh_commnet : "string",
                },
                {   
                    ve_id : 5,
                    v_title : "최고다",
                    v_poster:'@/assets/images/poster04.jpg',
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
                    v_poster:'@/assets/images/poster05.jpg',
                    ve_episode_num : 0,
                    vh_commnet : "string",
                },
                {   
                    ve_id : 8,
                    v_title : "성공해용",
                    v_poster:'@/assets/images/poster06.jpg',
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
        modifyPlaylist(epiInfo)
        this.getUserPlayList();
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
            console.log(my_id,'마이')

            const bothId = { u_id : my_id, f_id :your_id }
            const response = modifyfollow(bothId)
            console.log(response)

        },
        showVodEpiModal(index){
            this.selectedId = index;
            console.log(this.selectedId,'선택된 VOD index')
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
            // this.playlists = res.data
            console.log(res.data,this.recentlistLen)
        } catch {
            console.log('에러')
        }
        },
        getPoster(poster){
        const picPath = require(poster);
        return picPath
    },


    },

    computed: {
        ...mapState({
        userInfo: state => state.user.userInfo,
    }),

    getVodPoster(vId,title){
        return require(`@/assets/images/${vId}${title}`)
    }
  },


}
</script>


<style scoped>
.drop-zone {
  background-color: #eee;
  background-image: linear-gradient(60deg, #3d3393 0%, #2b76b9 37%, #2cacd1 65%, #35eb93 100%);
  margin-bottom: 10px;
  padding: 10px;
}

.drag-el {
  background-image: linear-gradient(to top, #f3e7e9 0%, #e3eeff 99%, #e3eeff 100%);
  /* margin-bottom: 10px; */
  margin:5px;
  padding: 10px;
    display: inline-block;
    width: 200px;
    height: 200px;
    border: 1px solid blue;
}

.plylist-zone {
  background-color: #c1dfc4; 
  background-image: linear-gradient(-20deg, #fc6076 0%, #ff9a44 100%);
  margin-bottom: 10px;
  padding: 10px;
}

</style>
