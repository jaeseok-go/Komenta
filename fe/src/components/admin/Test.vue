<template>
    <div>
        <h3>Video 입니다.</h3>
        <video id="videotag" controls="controls" autoplay>
            <source src="https://drive.google.com/uc?export=download&id=0B4BsAbG4atWHQzVfLUU3UnhhZTA" id="player" type='video/webm'/>
        </video>

        <b-button @click="a(5)">아니 여기가 어디입니까??</b-button>
    </div>
</template>
<script>

export default {
    methods:{
        a(count){
            console.log("hey");
            // var vid = document.getElementById("player");
            var tag = document.getElementById("videotag");
            tag.currentTime = count;
            //    document.getElementById('player').addEventListener('loadedmetadata', function() {
            //         this.currentTime = count;
            //     }, false);
        }
    }
}
</script>