<template>
    <div>
        <div class="container">
            <div class="p_top">
                <div>
                    <img :src='info.head_img || defaultHeadImg' alt="头像" />
                    <router-link to="/login" v-if="getToken === ''">
                        <p>立即登录</p>
                    </router-link>
                    <p v-else>{{ info.name }} </p>
                </div>
            </div>
            <button v-if="getToken !== ''" class="green" @click="signOut">
                退出登录
            </button>
        </div>
        <common-footer-vue></common-footer-vue>
    </div>
</template>

<script>
import CommonFooterVue from '@/components/CommonFooter.vue'
import defaultHeadImg from "@/assets/logo.png";

export default {
    components: {
        CommonFooterVue
    },
    data() {
        return {
            info: { name: "sakta" },
            defaultHeadImg: defaultHeadImg
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
        background-size:600% 600%;
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