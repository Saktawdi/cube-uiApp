<template>
    <div>
        <div class="container">
            <div class="p_top">
                <div>
                    <img :src='info.avatarUrl || defaultHeadImg' alt="头像" />
                    <router-link to="/login" v-if="getToken === ''">
                        <p>立即登录</p>
                    </router-link>
                    <p v-else>{{ info.name }} </p>
                </div>
            </div>
        </div>
        <div class="mainInfo" v-if="getToken === ''">
            <user-msg-list :swipeData="swipeData" hidden></user-msg-list>
        </div>
        <div class="mainInfo" v-else>
            <user-msg-list :swipeData="swipeData"></user-msg-list>
        </div>
        <div>
            <button v-if="getToken !== ''" class="green" @click="signOut">
            退出登录
            </button>
        </div>
        <common-footer-vue></common-footer-vue>
    </div>
</template>

<script>
import CommonFooterVue from '@/components/CommonFooter.vue'
import userMsgList from './components/userMsgList.vue'
import defaultHeadImg from "@/assets/logo.png";
import { getUserInfoApi } from "@/api/getData";
import { requestConfig } from '@/request';

export default {
    components: {
    CommonFooterVue,
    userMsgList,
},
    data() {
        return {
            info: {},
            defaultHeadImg: defaultHeadImg,
            swipeData: [{
                item: {
                    text: 'id：',
                    value: 1,
                },
            }, {
                item: {
                    text: 'VIP：',
                    value: 2,
                },
                btns: [
                    {
                        action: 'upVip',
                        text: '升级',
                        color: '#00FF7F'
                    }
                ]
            }, {
                item: {
                    text: '注册时间：',
                    value: 3
                },
            }]
        };
    },
    computed: {
        getToken() {
            return this.$store.state.token;
        }
    },
    methods: {
        async signOut() {
            await this.$store.dispatch('clearToken');
            localStorage.removeItem('token');
            //刷新页面
            location.reload();
        },
        async getUserInfo() {
            try {
                const result = await getUserInfoApi(this.getToken)
                if (result.data.success === true) {
                    this.info = result.data.data;
                    if (this.info.avatarUrl != "") {
                        this.info.avatarUrl = requestConfig.baseURL + this.info.avatarUrl;
                    } else {
                        this.info.avatarUrl = requestConfig.baseURL + "defaultFile/default0.png"
                    }
                    this.swipeData[0].item.text+=this.info.id;
                    this.swipeData[1].item.text+=this.info.vip;
                    this.swipeData[2].item.text+=this.info.createTime;
                }
            } catch (error) {
                console.log(error)
            }

        }
    },
    mounted() {
        if (this.getToken) {
            this.getUserInfo(this.$store.state.token);
        }
    }
}
</script>

<style lang="scss" scoped>
.container {

    // 顶部头像区域
    .p_top {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 20px 0;
        background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
        background-size: 600% 600%;
        animation: gradientBG 5s ease infinite;


        div {
            text-align: center;

            img {
                width: 60px;
                height: 60px;
                border-radius: 50px;
            }

            p {
                font-size: 16px;
                color: #fff;
                margin-top: 10px;
            }
        }
    }
}

@keyframes gradientBG {
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

// 退出登录
.green {
    display: block;
    background-color: #3bb149;
    border: none;
    outline: none;
    width: 80%;
    height: 40px;
    margin: 20px auto 0;
    color: #fff;
    border-radius: 20px;
}
</style>