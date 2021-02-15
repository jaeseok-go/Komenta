<template>
    <div class="container margin__left">
        <div class="container__following">
            <!-- 버튼 누르기 -->
            <div class="btn-cover_people">
                <button :disabled="pageNum === 0" @click="prevPage" class="page-btn_people">
                    <font-awesome-icon :icon="['fas', 'angle-left']"/>
                </button>
            <!-- 프로필 팔로잉 사진 목록 -->
            <div class="container__following__Profle">
                <div v-for="(user,index) in paginatedData" :key="index">
                    <div @click="gotoFeed(user)" class="container__following__Profle__background"><img :src="getUpdateProfile(updateFollow_array[index].u_profile_pic)" class="container__following__Profle__img" width="100px" height="100px"></div>
                    <div class="container__following__Profle__NEW">NEW!</div>
                </div>
            </div>
            <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn_people">
                <font-awesome-icon :icon="['fas', 'angle-right']"/>
            </button>
            </div>
            
            <!-- 친구 카드 프로필 닉네임 같이 보여주면서 바로가기 -->
            <div class="at-section">
                <div class="at-section__title"><span class="at-section__nickname">{{userInfo.u_nickname}}</span>님의 친구들의 피드에서 소식을 확인해보세요</div>
                <span class="at-section__subtitleButton"><span class="at-section__subtitle"><i class="fas fa-check at-section__icon"></i>친구들의 플레이리스트를 확인하고, 취향에 맞는 VOD를 추천받아보세요!</span></span>
            </div>
            <div class="btn-cover_people">
                <button :disabled="pageNum_2 === 0" @click="prevPage_2" class="page-btn_people" style="margin: 4.5rem 2rem">
                    <font-awesome-icon :icon="['fas', 'angle-left']"/>
                </button>
            <div class="at-grid basic-scroll">
                <div class="at-column" v-for="(user,index) in paginatedData_people" :key="index">
                    <div class="at-user"  @click="gotoFeed(user.f_id)">
                        <div class="at-user__name">{{user.u_nickname}}</div>
                        <div class="at-user__profile"><img :src="getProfile(index)" width="50px" height="50px" class="at-user__profile__img"></div>
                    </div>
                </div>
            </div>
            <button :disabled="pageNum_2 >= pageCount_2 -1" @click="nextPage_2" class="page-btn_people" style="margin: 4.5rem 2rem">
                <font-awesome-icon :icon="['fas', 'angle-right']"/>
            </button>
            </div>
            
            <!-- 업데이트된 플레이리스트 카드형태로 2개씩 보여줌 -->
            <div class="at-section">
                <div class="at-section__title"><span class="at-section__nickname" style="font-size:2.8rem">새롭게 업데이트된 플레이리스트를 확인해보세요</span></div>
                <span class="at-section__subtitleButton"><span class="at-section__subtitle"><i class="fas fa-check at-section__icon"></i>최근 일주일 간 업데이트된 친구의 플레이리스트 보러가기</span></span>
            </div>

        <div class="play-list-form">
        <div class="play-list">
          <div class="playList" v-for="(playlist, index) in updateFollowPlaylists" :key="index">
            <div class="playList-Form" @click="gotoPlaylist(playlist[0].pl_id)">
              <div class="reprePoster">
                <img :src="getPoster(index)" width="210px" height="150px">
              </div>
              <div class="playlist_user_profile">
                <img :src="getUpdateProfiletoPlaylist(playlist)" class="playlist_user_profile">            
              </div>
              <div class="plInfo margin__left__five">
                <span class="container__info__button" @click="gotoFeed(playlist[0].u_id)"> {{ updateFollow_nickname[index] }}</span>'S PICK
                <font-awesome-icon :icon="[starType, 'star']" :style="{ color: '#e2c000'}"/>
                {{playlist[0].pl_good_count}}
                <p> 
                   <span style="text-decoration: underline; text-underline-position:under;"><strong>{{playlist[0].pl_name}}</strong><br></span>
                  {{playlist[0].pl_comment}} <br>
                </p>
                
              </div>
            </div>
          </div>
        </div>
        </div>
        </div>
    </div>
</template>

<script>
import { fetchfollowinglist,updateFollowPlaylist,fetchMyInfo } from '@/api/user'
// import { mapState } from 'vuex'
import store from '@/stores/modules/user'

export default {
    data() {
        return {
            pageNum:0,
            pageNum_2:0,
            following_list:[],
            updateFollowPlaylists:[],
            updateProfile:[],
            playlist_nickname:[],
            updateFollow_array : [],
            updateFollow_userId:[],
            updateFollow_nickname:[]
        }
    },
    props: {
        pageSize: {
            type: Number,
            required: false,
            default: 7
        },
        pageSize_2: {
            type: Number,
            required: false,
            default: 5
        }
    },
    computed: {
        userInfo() {
            return store.state.userInfo;
        },
        pageCount() {
            let listLeng = this.updateProfile.length,
            listSize = this.pageSize,
            page = Math.floor(listLeng / listSize);

            if(listLeng % listSize > 0) page += 1;

            return page;
        },
        pageCount_2() {
            let listLeng_2 = this.following_list.length,
            listSize_2 = this.pageSize_2,
            page_2 = Math.floor(listLeng_2 / listSize_2);

            if(listLeng_2 % listSize_2 > 0) page_2 += 1;

            return page_2;
        },
        paginatedData() {
            const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;
            console.log(this.updateProfile,'너는 어떻닝')
            return this.updateProfile.slice(start, end);
            
        },
        paginatedData_people() {
            const start_2 = this.pageNum_2 * this.pageSize_2,
            end_2 = start_2 + this.pageSize_2;

            return this.following_list.slice(start_2, end_2);
        },
        
    },
    created() {
        this.getFollowing()
        this.getUpdateFollowPlaylist()
        this.getNickname()
    },
    methods: {
        gotoFeed(userId) {
            this.$router.push(`/feed/${userId}`)
        },
        gotoPlaylist(plId){
            this.$router.push(`/playlist/${plId}`)
        },
        nextPage() {
            this.pageNum += 1;
        },
        prevPage() {
            this.pageNum -= 1;
        },
        nextPage_2() {
            this.pageNum_2 += 1;
        },
        prevPage_2() {
            this.pageNum_2 -=1;
        },
        getProfile(index) {
            const profile = this.following_list[index].u_profile_pic.split('.')[0]
            console.log(`${process.env.VUE_APP_PICTURE}profile/${profile}`)
            return `${process.env.VUE_APP_PICTURE}profile/${profile}`;
        },
        getUpdateProfiletoPlaylist(playlist) {
            for (let i = 0; i < this.updateFollow_array.length; i++) {
                const user = this.updateFollow_array[i];
                if (playlist[0].u_id == user.u_id) {
                    const path = user.u_profile_pic.split('.')[0]
                    return `${process.env.VUE_APP_PICTURE}profile/${path}`;
                }
                
            }
           
            return
        },
        getUpdateProfile(profile){
            const path = profile.split('.')[0]
            return `${process.env.VUE_APP_PICTURE}profile/${path}`;
        },
        getPoster(index) {
            const poster = this.updateFollowPlaylists[index][0].v_poster
            return `${process.env.VUE_APP_PICTURE}poster/${poster}`;
        },
        // 이미 있는 updateFollowPlaylist로 nickname 배열 만들기
        async getNickname() {
            let temp = [];
            for (let index = 0; index < this.updateFollow_userId.length; index++) {
                const element = this.updateFollow_userId[index];
                const response = await fetchMyInfo(element)
                if (temp.includes(element)) {
                    this.updateFollow_nickname.push(response.data.u_nickname)
                } else {
                    temp.push(element)
                    this.updateFollow_array.push(response.data)
                    this.updateFollow_nickname.push(response.data.u_nickname)
                }
                
            }
        },

        async getUpdateFollowPlaylist() {
            const response = await updateFollowPlaylist()
            this.updateFollowPlaylists = response.data
            console.log(this.updateFollowPlaylists,'업데잇')
            for (let index = 0; index < this.updateFollowPlaylists.length; index++) {
                const element = this.updateFollowPlaylists[index];
                if (this.updateProfile.includes(element[0].u_id)) {
                    console.log(element[0].u_id,'오잉')
                } else {
                    this.updateProfile.push(element[0].u_id)
                }
                this.updateFollow_userId.push(element[0].u_id)
                
            } 
            this.getNickname();
            console.log(this.updateProfile,this.updateFollowPlaylists,'너는,,,,,,,왜,,,,,날,,,')
        },
        async getFollowing() {
            const userId = store.state.userInfo.u_id
            const response = await fetchfollowinglist(userId)
            this.following_list = response.data
        },
    },
}
</script>

<style scoped>
.margin__left{
  margin-left: 10%;
}
.margin__left__five{
  margin-left: 5%;
}
</style>

