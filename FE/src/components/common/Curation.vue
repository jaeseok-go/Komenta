<template>
    <!-- isSelect가 true면 selected class적용 -->
    <div class="wrap curation-component" :class="{selected:isSelect}">
        <h4 class="title">
            {{curationTitle}}
        </h4>
        <!-- 만약 isSelect가 false면 btn보여줌 -->
        <div class="btns" v-if="!isSelect">
            <!-- 선택 t/f 토글 -->
            <button @click="selectTriggle">선택</button>
            <button>보기</button>
        </div>
        <!-- 선택되면 선택됨 뜨고, 누르면 cancle돼서(toggle) 다시 보기로 바뀜 -->
        <span v-if="isSelect" class="select-text" @click="cancelSelect">
            선택됨
        </span>



    </div>
</template>

<script>
    export default {
        name: "curation",
        props : ['curationTitle'],
        data: () => {
            return {
                isSelect:false,
            }
        },
        methods: {
            selectTriggle(){
                this.isSelect = true
            },
            cancelSelect() {
                this.isSelect = false;
            }
        },
    }
</script>
