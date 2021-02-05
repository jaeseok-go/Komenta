<template>
    <div class="container">
        <div class="at-section">
            <div class="at-section__title">The Team</div>
        </div>
        <div class="at-grid">
            <div v-for="(user,index) in users" :key="index">
                <div class="at-column">
                    <div class="at-user">
                        <div class="at-user__avatar" src="user.avatar"></div>
                        <div class="at-user__name">{{user.name}}</div>
                        <div class="at-user__title">{{user.title}}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import store from '@/stores/modules/user'

export default {
    data() {
        return {
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

                    }
                ]
    }},
    computed: {
        ...mapGetters(['fetchedFollowingList'])
    },
    created() {
        const userId = store.state.userInfo.u_id
        this.store.dispatch('FETCH_FOLLOWING',userId)
        //여기서 인기유저 갖고옴 -> 재석님 피드백 듣고 작업하면될듯
    }

}
</script>

<style>

</style>