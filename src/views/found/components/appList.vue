<template>
    <cube-index-list :data="appData">
        <cube-index-list-group v-for="(group, index) in appData" :key="index" :group="group">
            <cube-index-list-item v-for="(item, index) in group.items" :key="index" :item="item" @select="selectItem">
                <div class="custom-item"><i class="cubeic-arrow"></i> {{ item.name }}</div>
            </cube-index-list-item>
        </cube-index-list-group>
    </cube-index-list>
</template>

<script>
import appListData from '@/api/getAppData';
import router from '@/router';
const appData = appListData;

export default {
    data() {
        return {
            appData: appData
        }
    },
    methods: {
        selectItem(item) {
            console.log(item.value)
             console.log(item.icon)
            this.$router.push(item.value)
        },
        clickTitle(title) {
            console.log(title)
        }
    }
}
</script>

<style lang="scss" scoped>
.custom-item {
    position: relative;
    height: 70px;
    line-height: 70px;
    padding: 0 16px;
}
</style>