<template>
    <div>
        <common-hearder-vue :Info="Info"></common-hearder-vue>
        <div class="container">
            <div class="bg">
                <div class="addKey">
                    <input type="text" placeholder="新增字段" v-model="nameValue">
                    <div class="btnBox">
                        <button class="button" v-on:click="addKeyName">
                            新增字段
                            <div class="button__horizontal"></div>
                            <div class="button__vertical"></div>
                        </button>
                        <button class="button" v-on:click="clearKeyName">
                            清 空
                            <div class="button__horizontal"></div>
                            <div class="button__vertical"></div>
                        </button>
                    </div>
                </div>
                <div class="showJson">
                    <cube-textarea v-model="keyValue" :readonly="readonly" :placeholder="placeholder"
                        :maxlength="maxlength"></cube-textarea>
                </div>
                <div class="btnBox">
                    <button class="button" v-on:click="bulidKey">
                        生成 模板
                        <div class="button__horizontal"></div>
                        <div class="button__vertical"></div>
                    </button>
                    <button class="button" v-on:click="reLoadKeyVaule">
                        重置key值
                        <div class="button__horizontal"></div>
                        <div class="button__vertical"></div>
                    </button>
                </div>
                <div id="builderBox" :hidden=ifBuilded>
                </div>
                <button class="button" v-on:click="addKey" :hidden=ifBuilded>
                    添加KEY到游戏
                    <div class="button__horizontal"></div>
                    <div class="button__vertical"></div>
                </button>
                <div class="showKeysBox">
                    <FlotBall :text="FloatIcon" @FloatBallClick="FloatBallClick"></FlotBall>
                    <ShowKeysPopup ref="myPopup"></ShowKeysPopup>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import CommonHearderVue from '@/components/CommonHearder.vue';
import DiyInput from "./components/input.vue";
import FlotBall from "./components/FloatBall.vue";
import ShowKeysPopup from './components/showKeysPopup.vue';

import { create } from "@/utils/create";


var keyJson=[];
export default {
    components: {
    CommonHearderVue,
    DiyInput,
    FlotBall,
    ShowKeysPopup
},
    data() {
        return {
            Info: "管理",
            nameValue:"",
            keyValue: '',
            readonly: true,
            placeholder:"这是示例",
            maxlength:200,
            ifBuilded:true,
            FloatIcon:"≡",
        }
    },
    methods:{
        addKeyName(){
            if(this.nameValue===""){
                this.showErrorTips("请输入要创建的字段");
            }else{
                this.keyValue+="\""+this.nameValue+"\""+":\"\""+"\,\n";
                keyJson.unshift({
                    "name":this.nameValue,
                    "value":""
                })
                this.nameValue="";
            }
        },
        clearKeyName(){
            this.keyValue='';
            this.ifBuilded=true;
            keyJson=[];
        },
        bulidKey(){
            if(keyJson.length<1){
                this.showErrorTips("还未添加字段!");
                return
            }
            document.getElementById("builderBox").innerHTML = "";
            //显示BuilderDIV盒子
            this.ifBuilded=false
            //生成key
            let keyUUID=this.getUuid();
            if(keyJson[keyJson.length-1].name==="key"){
                keyJson[keyJson.length-1].value=keyUUID;
            }else{
                keyJson.push({
                    "name": "key",
                    "value": keyUUID
                })
            }
            for (let index = 0; index < keyJson.length; index++) {
                create(DiyInput, {
                    name: keyJson[index].name,
                    value: keyJson[index].value,
                    index:index
                })
            }

            // create(DiyInput, {
            //     name: "key",
            //     value: keyUUID,
            //     index: keyJson.length - 1
            // })
        },
        //前端响应式添加key，无调用API
        addKey(){
            let newKeyData=[];
            for (let index = 0; index < keyJson.length; index++) {
                let keyName=keyJson[index].name;
                let data=document.getElementById("myInput"+index).value;
                newKeyData.push({
                   [keyName]:data
                })
            }
           this.$createDialog({
                type: 'confirm',
                icon: 'cubeic-alert',
                title: '确定要添加吗',
                content: JSON.stringify(newKeyData),
                confirmBtn: {
                    text: '确定按钮',
                    active: true,
                    disabled: false,
                    href: 'javascript:;'
                },
                cancelBtn: {
                    text: '取消按钮',
                    active: false,
                    disabled: false,
                    href: 'javascript:;'
                },
                onConfirm: () => {
                    this.onConfirmAddKey(newKeyData);
                },
                onCancel: () => {
                    this.$createToast({
                        type: 'warn',
                        time: 1000,
                        txt: '取消'
                    }).show()
                }
            }).show()
        },
        onConfirmAddKey(keyData){
            this.$refs.myPopup.addKeyToswipeData(keyData)
        },
        reLoadKeyVaule(){
            if(keyJson[keyJson.length - 1].name!=="key"){
                this.showErrorTips("还未生成过模板！")
                return
            }
            var newKey=this.getUuid();
            keyJson[keyJson.length-1].value=newKey;
            var elID="myInput"+(keyJson.length-1);
            document.getElementById(elID).value=newKey;
        },
        //悬浮球
        FloatBallClick(){
            if(this.FloatIcon==="≡"){
                this.FloatIcon="X"
               this.$refs.myPopup.showPopup();
            }else{
                this.FloatIcon="≡"
                this.$refs.myPopup.hidePopup();
            }

        },
        getUuid() {
            return 'xxxxx-xxxx-4xxx-yxxx-xxxxx'.replace(/[xy]/g, function (c) {
                var r = (Math.random() * 16) | 0,
                    v = c == 'x' ? r : (r & 0x3) | 0x8;
                return v.toString(16);
            });
        },
        showErrorTips(text){
            this.$createToast({
                type: 'warn',
                time: 1000,
                txt: text
            }).show()
        }
    },
    mounted() {
        this.Info += "【" + this.$route.query.game_name + "】";
    }
}
</script>

<style lang="scss" scoped>


body{
    background: #1D1F20;
}
input {
    appearance: none;
    text-align: center;
    height: 36px;
    width: 248px;
    border: 0px solid #fff;
    padding: 0 8px;
    outline: 0;
    letter-spacing: 1px;
    color: #fff;
    font-weight: 600;
    background: rgba(45,45,45,.10);
    border: 1px solid rgba(255,255,255,.15);
    box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    text-shadow: 0 1px 2px rgba(0,0,0,.1);
}
input {
    box-shadow: 0 0 5px 4px rgba(0, 0, 0,0.1);
}
.container {
    width: calc(100vw - 6px);
    height: calc(100vh - 45px);
    display: flex;
    border: 0.18em solid;
    flex-direction: column;
    align-items: center;
    padding: 2px 0;
    background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
    background-size: 600% 600%;
    animation: gradient 5s ease infinite;

    div {
        text-align: center;
    }

    .bg {
        width: 100%;
        height: 100%;
        --borderWidth: 5px;
        background: #1D1F20;
        position: relative;
    }
    .addKey{
        padding:10px;
        text-shadow: 0 1px 2px rgba(0,0,0,.1);
        text-align: center;
        font-weight: 600;
        color:#807b79
    }
    .showJson {
        padding: 10px;
    }
    .btnBox{
        display:flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
    }
    #builderBox{
        margin-top:20px;
        width: calc(100vw - 9px);
        border: solid 1px;
        --borderWidth: 5px;
        background: #000000;
    }
}

@keyframes gradient {
    0% {
        background-position: 0% 50%;
    }

    50% {
        background-position: 100% 50%;
    }

    100% {
        background-position: 0% 50%;
    }
}
.button {
    margin-top: 15px;
    margin-left:25px;
    left:-10px;
    --offset: 8px;
    --border-size: 2px;
    position: relative;
    padding: 1em 3em;
    appearance: none;
    border: 0;
    background: transparent;
    color: #e55743;
    text-transform: uppercase;
    letter-spacing: .25em;
    outline: none;
    cursor: pointer;
    font-weight: bold;
    box-shadow: inset 0 0 0 var(--border-size) currentcolor;
    transition: background .8s ease;

    &:hover {
        background: rgba(100, 0, 0, .03);
    }

    &__horizontal,
    &__vertical {
        position: absolute;
        top: var(--horizontal-offset, 0);
        right: var(--vertical-offset, 0);
        bottom: var(--horizontal-offset, 0);
        left: var(--vertical-offset, 0);
        transition: transform .8s ease;
        will-change: transform;

        &::before {
            content: '';
            position: absolute;
            border: inherit;
        }
    }

    &__horizontal {
        --vertical-offset: calc(var(--offset) * -1);
        border-top: var(--border-size) solid currentcolor;
        border-bottom: var(--border-size) solid currentcolor;

        &::before {
            top: calc(var(--vertical-offset) - var(--border-size));
            bottom: calc(var(--vertical-offset) - var(--border-size));
            left: calc(var(--vertical-offset) * -1);
            right: calc(var(--vertical-offset) * -1);
        }
    }

    &:hover &__horizontal {
        transform: scaleX(0);
    }

    &__vertical {
        --horizontal-offset: calc(var(--offset) * -1);
        border-left: var(--border-size) solid currentcolor;
        border-right: var(--border-size) solid currentcolor;

        &::before {
            top: calc(var(--horizontal-offset) * -1);
            bottom: calc(var(--horizontal-offset) * -1);
            left: calc(var(--horizontal-offset) - var(--border-size));
            right: calc(var(--horizontal-offset) - var(--border-size));
        }
    }

    &:hover &__vertical {
        transform: scaleY(0);
    }
}
</style>