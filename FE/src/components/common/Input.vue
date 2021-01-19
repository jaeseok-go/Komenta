<template>
    <div class="input-with-label">
        <!-- 컴포넌트재사용성을 위해 아래같이 작성 -->
        <!-- errorText가 있으면 error class적용, text가 있고 errorText가 없으면 complete class적용 -->
        <!-- input변화가 있으면 changeInput(값,type을 보냄) 그러면 components의 enterInput함수 실행해서 부모의 email,password -->
        <!-- type은 password=true면 type=password false라면 text -->
        <input v-model="text"
               :id="inputValue" :placeholder="placeholder"
               :class="{error : errorText.length > 0, complete : text.length > 0 && errorText.length === 0}"
               @input="changeInput($event, inputValue)"
               :type="password ? type :'text'"/>
        <label :for="inputValue">{{label}}</label>

        <div class="error-text" v-if="errorText.length>0">
            {{errorText}}
        </div>
        <!-- 비밀번호보기(눈버튼) -->
        <!-- 누르면 viewPassword실행, 만약 props로 내려온 password가 true면(컴포넌트에서 내려옴) 보여지고, type이 text면 active class적용 -->
        <span @click="viewPassword" v-if="password" :class="{active : type==='text'}" class="eyes-icon">
            <i class="fas fa-eye"></i>
        </span>
    </div>
</template>

<script>

    export default {
        name: "input",
        props : ['inputValue', 'errorText', 'password', 'placeholder', 'label', 'enterInput'],
        methods: {
            viewPassword() {
                // tupe이 password가 tureaus text, false라면 type이 password
                this.type = this.type==="password" ? "text" : "password";
            },
            // 
            changeInput(event,type){
                // enterInput함수 : type이 email이면, email에 값을 넣고, password면 password에 값넣기
                // type에 따라 value값을 넣음!
                this.enterInput(event.target.value, type);
            }
        },
        data: () => {
            return {
                type:"password",
                text:"",
            }
        },
    }
</script>
