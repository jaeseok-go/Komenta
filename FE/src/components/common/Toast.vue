<template>
    <!-- isError가 true면 error class적용 -->
    <div class="toast-component" :class="{error:isError}">
        <h5>
            <!-- 스크립, 500에러, 스킵 표시 -->
            {{toastTitle}}
        </h5>
        <!-- isCancel이 true면 취소 -->
        <!-- 스킵표시 -->
        <button v-if="isCancel">
            취소
        </button>
    </div>
</template>

<script>
    export default {
        name: "toast",
        props : ['toastTitle', 'isError', 'isCancel'],
    }
</script>
