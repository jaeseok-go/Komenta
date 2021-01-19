<template>
    <select class="select-component">
        <!-- option value가 null, 선택되면 disabled -->
        <option :value="null" disabled selected>선택해주세요</option>
        <!-- options배열을 for문 돌림 -->
        <option v-for="target in options" :value="target" :key="target.value">
            {{target.title}}
        </option>
    </select>
</template>

<script>
    export default {
        name: "select",
        props : ['options'],
    }
</script>
