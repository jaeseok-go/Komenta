<template>
    <div class="container">
        <div class="container__friend">
        <div class="at-section">
            <div class="at-section__title"><span class="at-section__nickname">{{userInfo.u_nickname}}</span>님의 친구들의 새로운 소식을 확인해보세요</div>
            <span class="at-section__subtitle">친구들의 플레이리스트를 확인하고, 취향에 맞는 VOD를 추천받아보세요!</span>
        </div>
            <div class="at-grid basic-scroll">
                <div class="at-column" v-for="(user,index) in users" :key="index">
                    <div class="at-user">
                        <div class="at-user__avatar" :src="user.avatar"><i class="fab fa-apple"></i></div>
                        <div class="at-user__name">{{user.name}}</div>
                        <div class="at-user__title">{{user.title}}</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container__influencer">
            <div class="at-section">
                <div class="at-section__title popular-title at-section__nickname">KOMENTA TOP9 DJ</div>
                <span class="at-section__subtitle">{{userInfo.u_nickname }}님의 취향에 맞는 스트리밍DJ를 팔로우해보세요!</span>
            </div>
            <div class="at-grid basic-scroll">
                <div class="at-column" v-for="(user,index) in users" :key="index">
                    <div class="at-user">
                        <div class="at-user__avatar" :src="user.avatar"><i class="fab fa-apple"></i></div>
                        <div class="at-user__name">{{user.name}}</div>
                        <div class="at-user__title">{{user.title}}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>

</template>

<script>
import { TopInfluencers } from '@/api/user'
import { mapState } from 'vuex'
import store from '@/stores/modules/user'

export default {
    methods: {
      getImg (user) {
          return user.avatar
      },
      getTopInfluencer() {
          this.top9 = TopInfluencers()
      }
    },
    data() {
        return {
            top9:[],
            users:
                [{
                "name": "Aaron Rossi",
                "title": "CEO & Co-Founder",
                "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/adhamdannaway/128.jpg",
                },
                 {
                    "name": "Marco Gomez",
                    "title": "Co-Founder, Creative Director",
                    "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/rem/128.jpg",

                    },
                     {
                        "name": "Brad Joe",
                        "title": "Office Manager",
                        "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/boheme/128.jpg",

                    },   
                {
                    "name": "Mitch Petty",
                        "title": "Lead Developer",
                        "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/ok/128.jpg",

                    },
                {
                        "name": "Philip Satemburgo",
                        "title": "Community Manager",
                        "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg",

                    },
                  {
                        "name": "George Petty",
                        "title": "Lead Designer",
                        "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/marcogomes/128.jpg",

                    },
             {
                        "name": "Petty Rossi",
                        "title": "Brand Designer",
                        "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/dancounsell/128.jpg",

                    },
                 {
                        "name": "Mitch Rossi",
                        "title": "New Business",
                        "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/kolage/128.jpg",

                    },   
                     {
                        "name": "George Mitch",
                        "title": "UX Designer",
                        "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/tonychester/128.jpg",

                    },
                                         {
                        "name": "George Mitch",
                        "title": "UX Designer",
                        "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/tonychester/128.jpg",

                    },
                                         {
                        "name": "George Mitch",
                        "title": "UX Designer",
                        "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/tonychester/128.jpg",

                    },
                                         {
                        "name": "George Mitch",
                        "title": "UX Designer",
                        "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/tonychester/128.jpg",

                    },
                                         {
                        "name": "George Mitch",
                        "title": "UX Designer",
                        "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/tonychester/128.jpg",

                    },
                                         {
                        "name": "George Mitch",
                        "title": "UX Designer",
                        "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/tonychester/128.jpg",

                    },
                                         {
                        "name": "George Mitch",
                        "title": "UX Designer",
                        "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/tonychester/128.jpg",

                    }
                ]
    }},
    computed: {
        ...mapState({ myFollowingList : state => state.user.myFollowingList}),
        // ...mapState(['userInfo']),
        userInfo() {
            return store.state.userInfo;
        }
    },
    created() {
        const userId = store.state.userInfo.u_id
        this.$store.dispatch('FETCH_FOLLOWING',userId)

        // const top9 = TopInfluencers()

        //여기서 인기유저 갖고옴 -> 재석님 피드백 듣고 작업하면될듯
    }

}
</script>

<style>
#top9_color {
    background:backglinear-gradient(to right, #ff416c, #ff4b2b);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
}
/* .test {
  display: inline-block;
  margin: 50px auto 0;
  padding: 10px 25px;
  font-family: 'Roboto', sans-serif;
  background: #fff;
  font-size: 20px;
  font-weight: 700;
  text-align: center;
  cursor: pointer;
  user-select: none;
} */



/* .test:hover {
  background: linear-gradient(to right, #fbcac9, #8ca6ce);
} */

/* .test:hover span {
  background: none;
  color: #fff;
  -webkit-text-fill-color: #fff;
} */


</style>
