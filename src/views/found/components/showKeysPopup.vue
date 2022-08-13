<template>
    <div class="showPopupBox" :style="{opacity:MaskOpacity}">
        <cube-popup type="my-popup" :position="position" :mask-closable="false" ref="myPopup4" class="popup">
            <div class="list">
                <KeyList :swipeData="swipeData" @showKeyJson="showKeyJson"></KeyList>
            </div>
            <div class="text">总共有数据：【{{swipeData.length}}】条</div>
            <div class="oneKeyData">
                <cube-textarea v-model="jsonValue" :readonly="true" :placeholder="placeholder" :maxlength="200">
                </cube-textarea>
            </div>
            <div class="btnBox">
                <button class="button" v-on:click="saveKeyList">
                    保存key列表数据到云
                    <div class="button__horizontal"></div>
                    <div class="button__vertical"></div>
                </button>
                <button class="button" v-on:click="copyGameKeyUrl">
                    复制此游戏的API到剪切板
                    <div class="button__horizontal"></div>
                    <div class="button__vertical"></div>
                </button>
            </div>
        </cube-popup>
    </div>
</template>

<script>
import KeyList from './keyList.vue';

const positions = ['top', 'right', 'bottom', 'left', 'center']
//用于赋值swipe，防止组件key重复
let count = 0;
export default {
    data() {
        return {
            position: "",
            MaskOpacity: 0,
            ifShow: false,
            swipeData:[],
            jsonValue:"",
            placeholder:"尝试点击上面的key项目来进一步管理"
        };
    },
    methods: {
        addKeyToswipeData(keyJson){
            if (keyJson[keyJson.length - 1].key !== null) {
                var swipeTiile = "key:" + keyJson[keyJson.length - 1].key;
                var newData=this.newSwipeTemp(swipeTiile, keyJson);
                this.swipeData=this.swipeData.concat(newData);
            }
        },
        saveKeyList(){

        },
        copyGameKeyUrl(){

        },
        showKeyJson(jsonData){
            this.jsonValue=JSON.stringify(jsonData);
        },
        showPopup() {
            this.MaskOpacity = 0.95;
            this.position = positions[0];
            const component = this.$refs.myPopup4;
            component.show();
        },
        hidePopup() {
            this.MaskOpacity = 0;
            const component = this.$refs.myPopup4;
            component.hide();
        },
        newSwipeTemp(value,data) {
            if (this.swipeData.length > 0) {
                count = this.swipeData[this.swipeData.length - 1].item.value + 1;
            }
            const newData = [{
                item: {
                    text: value,
                    value: count,
                    JsonData:data
                },
                btns: [
                    {
                        action: 'delete',
                        text: '删除',
                        color: '#ff3a32'
                    }
                ],
            }]
            return newData;
        },
    },
    components: { KeyList }
}
</script>

<style lang="scss" scoped>

.showPopupBox {
    position: fixed;
}
.popup{
    display:flex;
    margin-top:42px;
    margin-left: 5px;
    margin-bottom: 0.5px;
    box-shadow: 0 0 2px 2px rgba(71,167,235,.86);
    background-color:rgba(0, 0, 0, 0.874);
    width: calc(100vw - 10px);
}
.list{
    margin: 5px;
    height: 50vh;
    overflow: hidden;
    border: 1px solid;
    z-index: 2;
}
.oneKeyData{
    margin: 5px;
    border: 1px solid;
    z-index: 2;
}

.text {
    width: 100%;
    height: 24px;
    font-family: "Microsoft YaHei";
    font-size: 16px;
    text-align: center;
    background: -webkit-linear-gradient(left, #0f0, #00f) 0 0 no-repeat;
    /*设置线性渐变*/
    -webkit-background-size: 160px;
    /*设置背景大小*/
    -webkit-background-clip: text;
    /*背景被裁剪到文字*/
    -webkit-text-fill-color: rgba(255, 255, 255, 0.3);
    /*设置文字的填充颜色*/
    -webkit-animation: shine 3s infinite;
    /*设置动画*/
}
@-webkit-keyframes shine {

    /*创建动画*/
    0% {
        background-position: 0 0;
    }

    100% {
        background-position: 100% 100%;
    }
}

.button {
    margin-top: 30px;
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