<template>
    <div>
        <p>이 페이지는 없는 페이지입니다.</p>
        <button @click="goMain">메인페이지로 가기</button>
    </div>
</template>

<script>
export default {
    name: 'NotFoundPage',
    methods: {
    goMain() {
      this.$router.push('/');
    }
    },
};
</script>

<style lang="scss" scoped>

</style>