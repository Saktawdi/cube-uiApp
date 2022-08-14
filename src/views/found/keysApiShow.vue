<template>
  <div>
    <common-hearder-vue :Info="Info"></common-hearder-vue>
    <div class="userBox">
      <h3>您的密钥：{{this.SecretKey}}</h3>
    </div>
    <div class="markdown-body">
      <apiMd ></apiMd>
    </div>
  </div>
</template>

<script>

import CommonHearderVue from '@/components/CommonHearder.vue';
import apiMd from "./fileAssets/howToUse.md";

export default {
    components: {
        CommonHearderVue,
        apiMd,
    },
    data(){
        return{
            Info:"返回上一页",
            SecretKey:""
        }
    },
    mounted(){
      this.SecretKey=this.$route.query.secretKey
    },  
}
</script>

<style src="../../assets/css/markdown1.css" scoped>
</style>

<style lang="scss" scoped>
:deep(.userBox){
  position:sticky;
  z-index: 4;
  position: -webkit-sticky;
  top:0;
  width:100%;
  text-align:center;
  margin-top:0.4px;
  margin-bottom:5px;
  border: 2px solid;
  background-color:#1d2d50;
  box-shadow: 0 0 0 2px rgba(45,140,240,.2);
}

h3{
    font-family: "Microsoft YaHei";
    font-size: 18px;
    text-align: center;
    background: -webkit-linear-gradient(left, #0f0, #00f) 0 0 no-repeat;
    /*设置线性渐变*/
    -webkit-background-size: 160px;
    /*设置背景大小*/
    -webkit-background-clip: text;
    /*背景被裁剪到文字*/
    -webkit-text-fill-color: rgba(255, 255, 255, 0.3);
    /*设置文字的填充颜色*/
    -webkit-animation: shine 3s infinite;                 /*设置动画*/
}
@-webkit-keyframes shine{   /*创建动画*/
		    0%{
		        background-position: 0 0;
		    }
		    100%{
		        background-position: 100% 100%;
		    }
		}
</style>