<template>
    <div class="header-default">
        <!-- isBack이 false면 뒤로가는 버튼 안보임-->
        <button class="menu" v-if="!isBack"></button>
        <!-- true이면 뒤로가는 버튼 보임 -->
        <button v-if="isBack" class="back">
            <i class="fas fa-chevron-left"></i>
        </button>
        <!-- 헤더타이틀보임 -->
        <h4 class="title">
            {{headerTitle}}
        </h4>
        <!-- isDisabled가 true이면 class disabled적용과 버튼이 비활성화 -->
        <button v-if="rightText" class="right-text" :class="{disabled:isDisabled}" :disabled="isDisabled">
            {{rightText}}
        </button>
        <!-- isSearch가 true면 검색버튼나옴 -->
        <button class="btn-search" v-if="isSearch">
            <i class="fas fa-search"></i>
        </button>
    </div>
</template>

<script>
    export default {
        name: "header",
        props : ['headerTitle', 'isBack', 'isSearch', 'rightText', 'isDisabled'],
    }
</script>
