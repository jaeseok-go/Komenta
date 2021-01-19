<template>
    <div class="textarea-wrap">
        <h4>
            {{label}}
        </h4>
        <span>{{contents.length}}/{{this.maxLength}}</span>
        <textarea v-model="contents" :placeholder="placeholder"/>
    </div>
</template>

<script>
    export default {
        name: "textarea",
        props : ['placeholder', 'label', 'maxLength'],
        data: () => {
            return {
                contents:"",
            }
        },
        watch:{
            // contents에 변화가 생기면
            contents : function(value){
                // length 변수는 maxLength
                let length = this.maxLength;
                // substr() 메서드는 문자열에서 특정 위치에서 시작하여 특정 문자 수 만큼의 문자들을 반환합니다.
                // value의 길이가 maxlength보다 크면 value의 0자리부터 max까지만 표시, max보다 짧으면 value그대로!
                value = value.length > length ? value.substr(0, length) : value;
                // contents에 value값 넣기
                this.contents = value;
            }
        },
    }
</script>
