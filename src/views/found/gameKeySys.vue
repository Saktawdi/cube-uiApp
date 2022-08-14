<template>
    <div>
        <CommonHearder :Info="title"></CommonHearder>
        <div class="gradient-border" id="box">
            <button class="button" v-on:click="addGame">
                添加    游戏
                <div class="button__horizontal"></div>
                <div class="button__vertical"></div>
            </button>
             <button class="button" v-on:click="saveGame">
                保存    到云
                <div class="button__horizontal"></div>
                <div class="button__vertical"></div>
            </button>
        </div>
        <key-list :swipeData="swipeData" @deleteGamesData="deleteGamesData"></key-list>
    </div>
</template>

<script>
import CommonHearder from '@/components/CommonHearder.vue';
import keyList from './components/gameList.vue';
import {addGamesApi,getGamesApi} from "@/api/getData"
const gamesJson=[];
gamesJson.games=[];
let count = 0;
export default {
    components: {
        CommonHearder,
        keyList,
    },
    data() {
        return {
            title: "返回主页",
            swipeData: [],
            postJson:"",
            ifUsed:false
        }
    },
    methods: {
        async getGames(userNum){
            try {
                userNum=JSON.parse(userNum).num;
                const result=await getGamesApi(userNum,this.getToken)
                if (result.data.success===true) {
                    let onlineData=[];
                    onlineData=JSON.parse(result.data.data);
                    for (let index = 0; index < onlineData.length; index++) {
                        let newData=this.newSwipeTemp(onlineData[index].name);
                        this.swipeData = this.swipeData.concat(newData);
                    }
                    gamesJson.games=onlineData;
                    this.ifUsed=true;
                    this.reLoadPostJson(userNum);
                }else if(result.data.data==="无数据"){
                    this.ifUsed=false;
                }else if(result.data.message==="登录已过期，请重新登录"){
                    this.showErrorTips("登录已过期，请重新登录")
                }

            } catch (error) {
                console.log(error)
                this.showErrorTips(error)
            }
        },
        addGame() {
            if(!this.getToken){
                this.showErrorTips("您还没有登录呢，去主页登录再来吧！")
                return
            }
            if(!this.getUserInfo){
                this.showErrorTips("获取用户信息失败")
                return
            }
            var user_num=JSON.parse(this.getUserInfo).num;
            this.dialog = this.$createDialog({
                type: 'prompt',
                title: '新增游戏标题',
                $class: {
                    'my_class': true,
                },
                prompt: {
                    value: '',
                    placeholder: '请输入'
                },
                onConfirm: (e, promptValue) => {
                    if (promptValue === "") {
                        this.showErrorTips("标题不能为空哦~")
                    }else {
                        for (let index = 0; index < this.swipeData.length; index++) {
                            if(promptValue===this.swipeData[index].item.text){
                                this.showErrorTips("该游戏标题已存在!")
                                return
                            }
                        }
                        let newData=this.newSwipeTemp(promptValue);
                        gamesJson.games.push({
                            "name":promptValue
                        });
                        this.reLoadPostJson(user_num)
                        this.swipeData = this.swipeData.concat(newData)
                    }
                }
            }).show()
        },
        saveGame(){
            if(gamesJson.games.length===0 && this.ifUsed===false){
                this.showErrorTips("还没有数据呢~不用保存哦")
                return
            }
            try {
                addGamesApi(this.postJson, this.getToken).then(res => {
                    if (res.data.success === true) {
                         this.showErrorTips(res.data.data)
                    } else {
                        gamesJson.games = [];
                        this.showErrorTips(res.data.message)
                    }
                })
            } catch (error) {
                gamesJson.games = [];
                this.showErrorTips(error)
                console.log(error)
            }          
        },
        deleteGamesData(deleteIndex){
            gamesJson.games.splice(deleteIndex,1);
            var userNum=JSON.parse(this.getUserInfo).num
            this.reLoadPostJson(userNum)
        },
        showErrorTips(text){
            this.$createToast({
                type: 'warn',
                time: 1000,
                txt: text
            }).show()
        },
        newSwipeTemp(value){
            if (this.swipeData.length > 0) {
                count = this.swipeData[this.swipeData.length - 1].item.value + 1;
            }
            const newData = [{
                item: {
                    text: value,
                    value: count
                },
                btns: [
                    {
                        action: 'editor',
                        text: '管理',
                        color: '#4EEE94'
                    },
                    {
                        action: 'delete',
                        text: '删除',
                        color: '#ff3a32'
                    }
                ],
            }]
            return newData;
        },
        reLoadPostJson(userNum){
            this.postJson="{"+"\"user_num\""+":"+userNum+","
                                    +"\"games\""+":"+JSON.stringify(gamesJson.games)+"}"
        }
    },
    computed: {
        getToken(){
             return this.$store.state.token;
        },
        getUserInfo(){
            return this.$store.state.userInfo;
        }
    },
    mounted(){
        //渲染完后拿数据
        this.getGames(this.getUserInfo)
    }
}
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css?family=Raleway:200');
html,
body {
    height: 100%;
}

body {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background: #1D1F20;
}

#box {
    display: flex;
    align-items: center;
    justify-content: center;
    width: calc(100vw);
    height: 160px;
    color: white;
    font-family: 'Raleway';
    font-size: 2.5rem;
}

.gradient-border {
    --borderWidth: 5px;
    background: #1D1F20;
    position: relative;
    //   border-radius: var(--borderWidth);
}

.gradient-border:after {
    content: '';
    position: absolute;
    top: calc(-1 * var(--borderWidth));
    left: calc(-1 * var(--borderWidth));
    height: calc(100% + var(--borderWidth) * 2);
    width: calc(100% + var(--borderWidth) * 2);
    background: linear-gradient(60deg, #f79533, #f37055, #ef4e7b, #a166ab, #5073b8, #1098ad, #07b39b, #6fba82);
    border-radius: calc(2 * var(--borderWidth));
    z-index: -1;
    animation: animatedgradient 3s ease alternate infinite;
    background-size: 300% 300%;
}


@keyframes animatedgradient {
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
    margin-left: 25px;
    --offset: 8px;
    --border-size: 2px;
    position: relative;
    padding: 1em 2.5em;
    appearance: none;
    border: 0;
    background: transparent;
    color: #e55743;
    text-transform: uppercase;
    letter-spacing: .25em;
    outline: none;
    cursor: pointer;
    font-weight: bold;
    border-radius: 0;
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