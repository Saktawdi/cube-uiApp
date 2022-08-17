<template>
    <div>
    <banner-list :banners="banners"></banner-list>
    <common-footer></common-footer>
    </div>
</template>

<script>
import CommonFooter from '@/components/CommonFooter.vue'
import bannerList from "./components/bannerList.vue"
import { getBannerApi} from '@/api/getData'
import { requestConfig } from '@/request'

export default {
    //注册组件
    components: {
        CommonFooter,
        bannerList,
    },
    data(){
        return {
            banners:[],
        }
    },
    methods:{
        async getBannerData(){
            try {
                const result = await getBannerApi();
                if(result.data.success === true){
                    this.banners= result.data.data;
                    for (let index = 0; index < this.banners.length; index++) {
                        this.banners[index].imgUrl = requestConfig.baseURL + this.banners[index].imgUrl;
                    }
                }
            } catch (error) {
                console.log(error)
            }
        },

    },
    mounted(){
        this.getBannerData();
    }
}
</script>

<style lang="scss" scoped>



</style>