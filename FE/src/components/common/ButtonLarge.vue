<template>
    <!-- isBackground가 false면 noneBackground class적용 -->
    <button class="btn-default" :class="{noneBackground : !isBackground}">
        {{text}}
    </button>
</template>

<script>
    export default {
        name: "buttonLarge",
        props : ['text', 'isBackground'],
    }
</script>
