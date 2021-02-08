<template>
  <div>
      <!-- asidebar에서 내피드를 클릭했을때 feed/f_id로 오게 함 -->
      <!-- f_id로 정보 받아서 처리 -->
    <section>
        <div class="userprofile">
            <div class="userprofile__pic"><i class="fad fa-user-circle"></i></div>
            <div class="userprofile__name">{{feedUserInfo.u_nickname}}</div>
            <div v-if="!showMyRecent()">
                <button class="userprofile__button" @click="followUser">FOLLOW</button>
            </div>
        </div>
    

    <template v-if="showMyRecent()">
        <div>
        <!-- 최근 본 VOD div -->
          <button @click="showModalForm"><h4>플레이리스트 생성 <i class="fas fa-plus"></i></h4></button>
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
                    <img :src="getVodPoster(vod.gd_id,vod.v_title)" width="100%">
                    </div>
                </div>
        </div>

        
        <Modal v-if="showModal" @close="showModal=false">
            <h4 slot="header">
            나만의 스트리밍 리스트를 만들어보세요
            <span @click="showModal = false"><i class="closeModalBtn fa fa-times"
            aria-hidden="true"
            >
            </i></span>
            </h4>
            <p slot="body">
            <input class="input-group" type="text" v-model="plName" placeholder="플레이리스트 제목을 적어주세요." > <br>
            <input class="input-group" type="text" v-model="plComment" placeholder="플레이리스트 내용을 적어주세요."> <br>
            <button @click="createPlaylist">제출</button>
            </p>

        </Modal>
                <!-- 플레이리스트 수만큼 drop-zon v-for -->
        <div 
        v-for='(playlist,pindex) in playlists' 
        :key='pindex'
        >
        <h3><span @click="goPlaylsitDetail(playlist[0].pl_id)">{{playlist[0].pl_name}} </span><span @click="deleteUserPlaylist(playlist[0].pl_id)"><i class="far fa-trash-alt"></i> </span> </h3>  
            <div class='plylist-zone'
            @drop='onDrop($event, playlist[0].pl_id,pindex)'
            @dragover.prevent
            @dragenter.prevent
            >
            <!-- 한 플레이리스트의 컨텐츠만큼 v-for(5개씩 보여주면 옆으로 넘기는 식으로 해야될것같음) -->
            <!-- startDrag -1이면  -->
            <div
            v-for='(vod,index) in playlist' 
            :key='vod.ve_id' 
            class='drag-el'
            draggable
            @dragstart='startDrag($event, vod)'
            @click="showVodEpiModal(pindex,index)"
           
            >
                <!-- <img :src=getVodPoster(vod.v_id,vod.v_title) alt="vod.v_poster"> -->
                <span v-if="vod.gd_name"><img :src="getPlaylistVodPoster(vod.gd_name,vod.v_title)" width="100%"></span>
                <Modal v-if="selectedId == [pindex,index] && vodEpiModal" @close="vodEpiModal=false">
                    <h4 slot="header">
                    <span
                       @click="goEpiDetail(vod.ve_id)"
                       >{{ playlists[pindex][index].v_title }}</span> 
                    <span  @click="vodEpiModal = false"><i class="closeModalBtn fa fa-times"
                    aria-hidden="true"
                   >
                    </i></span>
                    </h4>
                    <p slot="body">
                        {{vod.v_title}}
                        <input class="input-group" type="text" v-model="epiComment">
                        <button @click="addComment">리뷰작성</button>
                    </p>
                </Modal>
            </div>
            </div>
    </div>
        
    </template>

    <template v-else>
        <div 
        v-for='(playlist,pindex) in playlists' 
        :key='playlist.pl_id'
        >
        <h3 @click="goPlaylsitDetail(playlist.pl_id)">{{playlist.pl_name}} <i class="far fa-star" @click="likePlaylist(playlist.pl_id)"></i></h3> 
            <div class='plylist-zone'>
            <!-- 한 플레이리스트의 컨텐츠만큼 v-for(5개씩 보여주면 옆으로 넘기는 식으로 해야될것같음) -->
            <!-- startDrag -1이면 -->
            <div
                v-for='(vod,index) in playlist' 
                :key='vod.ve_id' 
                class='drag-el'
                @click="showVodEpiModal(pindex,index)"
               >
               <span ><img :src="getPlaylistVodPoster(vod.gd_name,vod.v_title)" height="200px"></span>
               <Modal v-if="selectedId == [pindex,index] && vodEpiModal" @close="vodEpiModal=false">
                    <h4 slot="header">
                    {{ vod.v_title }}
                    <div @click="vodEpiModal = false"><i class="closeModalBtn fa fa-times"
                    aria-hidden="true"
                    >
                    </i></div>
                    </h4>
                    <p slot="body">
                        {{vod.pl_comment}}
                        <button  @click="goEpiDetail(index)">지금시청하기</button>
                    </p>
                </Modal>
                </div>
            </div>
        </div>
    </template>
    </section>

    


  </div>
</template>

<script>
import { mapState } from 'vuex';
// import store from '@/stores/modules/user'
import { fetchMyInfo, fetchRecentPlaylist, likePlaylist, deletePlaylist, addReviewPlaylist,fetchMyPlaylist,addPlaylist, modifyfollow, addPlaylistVod } from '@/api/user'
import { fetchVodEpiDetail } from '@/api/vod'
import Modal from '@/components/common/Modal';
// import store from '@/stores/modules/user'

export default {
    
    components: {
        Modal,
    },
    data(){
        return {
            feedUserInfo:{},
            myrecentlists :{},
            playlists : [],
            showModal:false,
            vodEpiModal:false,
            showFollow:false,
            showRecent:false,
            dragVod:{}, 
            plName:'',
            plComment:'',
            epiComment:'',     
            selectedId:[],
            genreDetails:[
            {
                "gd_id": 1,
                "gd_name": "멜로",
                "g_id": 1
            },
            {
                "gd_id": 2,
                "gd_name": "스릴러",
                "g_id": 1
            },
            {
                "gd_id": 3,
                "gd_name": "코미디",
                "g_id": 1
            },
            {
                "gd_id": 4,
                "gd_name": "액션",
                "g_id": 1
            }
            ,
            {
                "gd_id": 5,
                "gd_name": "음악",
                "g_id": 2
            },
            {
                "gd_id": 6,
                "gd_name": "버라이어티",
                "g_id": 2
            },
            {
                "gd_id": 7,
                "gd_name": "토크",
                "g_id": 2
            },
            {
                "gd_id": 8,
                "gd_name": "요리",
                "g_id": 2
            }
            ,

            {
                "gd_id": 9,
                "gd_name": "환경",
                "g_id": 3
            },
            {
                "gd_id": 10,
                "gd_name": "역사",
                "g_id": 3
            },
            {
                "gd_id": 11,
                "gd_name": "휴먼",
                "g_id": 3
            }
            ,

            {
                "gd_id": 12,
                "gd_name": "축구",
                "g_id": 4
            },
            {
                "gd_id": 13,
                "gd_name": "농구",
                "g_id": 4
            },
            {
                "gd_id": 14,
                "gd_name": "배구",
                "g_id": 4
            },
            {
                "gd_id": 15,
                "gd_name": "야구",
                "g_id": 4
            }
            ,
            {
                "gd_id": 16,
                "gd_name": "로맨스/순정",
                "g_id": 5
            },
            {
                "gd_id": 17,
                "gd_name": "스포츠",
                "g_id": 5
            },
            {
                "gd_id": 18,
                "gd_name": "액션/추리",
                "g_id": 5
            },
            {
                "gd_id": 19,
                "gd_name": "코미디",
                "g_id": 5
            }

            ]
        }
    },
    created(){
        this.getFeedInfo();
       this.getUserPlayList();
        this.getRecentPlayList();
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
            vh_id:vod.vh_id,
            pl_id:plId
            }
        console.log(epiInfo,'플레이리스트에 추가할 epi')
        addPlaylistVod(epiInfo)
        this.getUserPlayList();
        },
        showModalForm() {
            this.showModal=true
        },
        // showUserFeed() {
        //     if (this.userInfo.u_id !== this.feedUserInfo.u_id) {
        //         // this.showModalForm()
        //         return true
        //     }
        //     return false
        // },
        showMyRecent() {
            if (this.userInfo.u_id == this.feedUserInfo.u_id) {
                console.log(this.userInfo.u_id,this.feedUserInfo.u_id,'와이ㅣㅣㅣ')
                return true
            }
            return false
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
        async addComment(plId){
            try {
                const commentInfo = {
                    plc_id : plId,
                    vh_comment : this.epiComment
                }
                await addReviewPlaylist(commentInfo);
            } catch {
                console.log('플레이리스트 comment생성 실패')
            }
        },
        async createPlaylist() {
            const playlistinfo = {
                pl_name:this.plName,
                pl_comment:this.plComment,
            }
            try {
                await addPlaylist(playlistinfo)
                this.showModal = false
                this.getUserPlayList();
            } catch {
                console.log('플레이리스트 생성 실패')
            }

        },
        goPlaylsitDetail(plId){
            this.$router.push(`/playlist/${plId}`)
        },
        async goEpiDetail(veId){
            await fetchVodEpiDetail(veId)
            .then(this.$router.push(`/voddetail/${veId}`))
            .catch(err => console.log(err))
        },
        async getRecentPlayList() {
        try {
            // userId를 같이 안보내주고 토큰으로??
            const res = await fetchRecentPlaylist()
            this.myrecentlists = res.data
            console.log(this.myrecentlists,res,'나의 최신vod목록')
        } catch {
            console.log('최신vod에러')
        }
        },
        async getUserPlayList() {
        const userId = this.$route.params.id;
        try {
            const res = await fetchMyPlaylist(userId)
            this.playlists = res.data
            console.log(res.data,this.recentlistLen,'playlist목록')
        } catch {
            console.log('playlist목록에러')
        }
        },
        likeUserPlaylist(plId){
            likePlaylist(plId);
            alert('플레이리스트 좋아요 함')
        },
        async deleteUserPlaylist(plId){
            const response = await deletePlaylist(plId);
            console.log(response)
            this.getUserPlayList();
        },
        async getFeedInfo(){
            try {
                const feedUserId = this.$route.params.id;
                const response = await fetchMyInfo(feedUserId)
                this.feedUserInfo = response.data
                console.log(feedUserId,this.feedUserInfo)
            } catch {
                console.log('피드목록 에러', this.$route.params.id)
            }
        },
        getPlaylistVodPoster(gdname,title){
            const gdId = this.genreDetails.find(genre => genre.gd_name === gdname);
            return `http://i4b201.p.ssafy.io:7000/picture/poster/${gdId.gd_id}_${title}`
        },
        getVodPoster(gdId,title){
           return `http://i4b201.p.ssafy.io:7000/picture/poster/${gdId}_${title}`
        },
        
        

    },

    computed: {
        ...mapState({
        userInfo: state => state.user.userInfo,
    }),

  },



}
</script>


<style scoped>
.drop-zone {
  background-color: #eee;
  background-image: linear-gradient(60deg, #3d3393 0%, #2b76b9 37%, #2cacd1 65%, #35eb93 100%);
  height: 200px;
}

.drag-el {
  background-image: linear-gradient(to top, #f3e7e9 0%, #e3eeff 99%, #e3eeff 100%);
  margin:5px;
    display: inline-block;
    width: 10rem;
    height: 100%;
    border: 1px solid blue;
}

.plylist-zone {
  background-color: #c1dfc4; 
  background-image: linear-gradient(-20deg, #fc6076 0%, #ff9a44 100%);
  height: 200px;
}

</style>
