<template>
    <div>
        1. 장르 대분류 / 소분류 셀렉트 박스
        1) 장르 대분류 선택시
        GET('g_name') 보냄 -> res(해당 대분류 장르의 VOD(v_id,v_poster)받음)
        2) 장르 대분류 + 소분류 선택시
        GET('g_id,gd_name') 보냄 -> res(해당 소분류 장르의 VOD(v_id,v_poster)받음)
        3)받은 VOD v-for


    </div>
</template>

<script>

export default {
    name: 'Category',
    components: {

    },
    data() {
        return {
            
        };
    },
    mounted() {
        
    },
    methods: {
        
    },
};
</script>

<style lang="scss" scoped>

</style>