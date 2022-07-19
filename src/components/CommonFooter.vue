<template>
    <div class="tab">
        <cube-tab-bar v-model="selectedLabelSlots" show-slider inline @click="clickHandler">
            <cube-tab v-for="(item) in tabs" :label="item.label" :icon="item.icon" :key="item.path" :value="item.path">
                <!-- name为icon的插槽 -->
                <i slot="icon" :class="item.icon"></i>
                <!-- 默认插槽 -->
                {{ item.label }}
            </cube-tab>
        </cube-tab-bar>
    </div>
</template>

<script>
export default {
    data() {
        return {
            selectedLabelSlots: '/home',
            tabs: [
                {
                    label: "首页",
                    icon: "cubeic-home",
                    path: '/'
                },
                {
                    label:"发现",
                    icon:"cubeic-star",
                    path:"/found"
                },
                {
                    label: "我的",
                    icon: "cubeic-person",
                    path: '/userInfo'
                }
            ]
        }
    },
    methods:{
        clickHandler(path) {
            if (path != this.$route.path) {
                this.$router.push(path)
            }
        }
    },
    created() {
        this.selectedLabelSlots = this.$route.path
    }
}
</script>

<!--SCSS是一种CSS预处理语言, scoped 是指这个scss样式 只作用于当前组件-->
<style lang="scss" scoped>
.tab {
  position: fixed;
  bottom: 0;
  z-index: 999;
  background-color:#fff;
  width: 100%;
  border-top: 1px solid rgba($color: #000000, $alpha: 0.1);
} 
.cube-tab_active {
  color: #3bb149;
}
</style>