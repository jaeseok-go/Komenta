<template>
    <!-- 누르면 toggle됨 -->
    <!-- isOn이 true면 active적용 -->
    <button @click="toggleSwitch" :class="{active:isOn}" class="switch-component"></button>
</template>

<script>
    export default {
        name: "switch",
        data: function () {
            return {
                isOn:false,
            }
        },
        methods: {
            toggleSwitch(){
                this.isOn = !this.isOn
            },
        }
    }
</script>
