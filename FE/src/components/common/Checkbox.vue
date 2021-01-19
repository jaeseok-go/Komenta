<template>
    <label class="checkbox-component">
        <!-- 부모한테 받은 checkValue, label표시 -->
        <input type="checkbox" v-model="check" :value="checkValue">
        <span class="title">{{label}}</span>
    </label>
</template>

<script>
    export default {
        name: "checkbox",
        props : ['checkValue','label'],
    }
</script>
