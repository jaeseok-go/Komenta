<template>
    <div class="keyword-del">
        <!-- 키워드 삭제 -->
        <p class="title">{{keywordTitle}}</p>
        <button>
            <i class="fas fa-times"></i>
        </button>
    </div>
</template>

<script>
    export default {
        name: "keywordDel",
        props : ['keywordTitle'],
    }
</script>
