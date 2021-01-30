<template>
  <div>
      <!-- 여기 플레이리스트 타이틀, 플레이 리스트 대표사진, 플레이 리스트 작성자, 플레이 리스트 안에 콘텐츠들(각각 장르, 제목, 몇화인지, 러닝타임)-->
      <h1>스트리밍 리스트 디테일 페이지</h1>
      <div>
          포스터 / 타이틀 / 즐겨찾기(좋아요) 버튼 / 닉네임
      </div>

      <div>

      </div>
      

  </div>
</template>

<script>
export default {
    data() {
        return {
            streamingListDetail:{},
        }
    },
    computed: {
    ...mapState({
        userInfo: state => state.user.userInfo
            }),
    },
    // methods: {
    //     fetchStreamingListDetail() {
    //         const u_id = userInfo.u_id
    //         //플레이리스트 아이디 이건 어디 있는걸로 해야하지??
    //         //유저가 좋아하는 플레이리스트는 store에서 관리하는게 좋을것같다
    //         // const pl_id = userlikeplaylist 어쩌고.pl_id
    //         const data = {u_id, pl_id}
    //         this.streamingListDetail = getStreamingListDetail(data)
    //     }
    // },
    // created() {
    //     this.fetchStreamingListDetail()

    // },

}
</script>

<style>

</style>