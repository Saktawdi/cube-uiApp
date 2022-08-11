<template>
    <div>
        <CommonHearder :Info="title"></CommonHearder>
        <div class="gradient-border" id="box">
            <button class="button" v-on:click="addGame">
                添加    游戏
                <div class="button__horizontal"></div>
                <div class="button__vertical"></div>
            </button>
        </div>
        <key-list :swipeData="swipeData"></key-list>
    </div>
</template>

<script>
import CommonHearder from '@/components/CommonHearder.vue';
import keyList from './components/keyList.vue';

export default {
    components: {
        CommonHearder,
        keyList,

    },
    data() {
        return {
            title: "返回主页",
            swipeData: [
            ]
        }
    },
    methods: {
        addGame() {
            if(!this.getToken){
                this.showErrorTips("您还没有登录呢，去主页登录再来吧！")
                return
            }
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
                        const newData=[{
                            item: {
                                text: promptValue,
                                value: this.swipeData.length+1
                            },
                            btns:[
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
                        this.swipeData=this.swipeData.concat(newData)

                    }

                }
            }).show()
        },
        showErrorTips(text){
            this.$createToast({
                type: 'warn',
                time: 1000,
                txt: text
            }).show()
        }
    },
    computed: {
        getToken() {
            return this.$store.state.token;
        }
    },
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
    --offset: 10px;
    --border-size: 2px;
    display: block;
    position: relative;
    padding: 1.5em 3em;
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