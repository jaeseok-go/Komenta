<template>
    <div class="wrap btns-half" v-if="buttons">
        <!-- buttons배열이 있으면 하나씩 for돌림 -->
        <!-- 누르면 button의 onClick함수실행 -->
        <!-- button의 highlight가 true면 highlight class적용 -->
        <button v-for="(button,index) in buttons" :key="index"
                @click="button.onClick" class="half"
                :class="{highlight : button.highlight}">
            {{button.title}}
        </button>
    </div>
</template>

<script>
    export default {
        name: "buttonHalf",
        props : ['buttons','title'],
    }
</script>
