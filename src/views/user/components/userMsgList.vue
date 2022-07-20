<template>
    <div class="swipe-wrapper">
        <cube-scroll>
            <cube-swipe @item-click="onItemClick" @btn-click="onBtnClick" :data="swipeData">
            </cube-swipe>
        </cube-scroll>
    </div>
</template>

<script>
export default {
    //获取父组件传递过来的值
    props:{
        swipeData:Array
    },
    methods: {
        onItemClick(item) {
            console.log('click item:', item)
        },
        onBtnClick(btn, index) {
            if (btn.action === 'upVip') {
                this.$createActionSheet({
                    title: '确认要升级吗',
                    active: 0,
                    data: [
                        { content: '升级' }
                    ],
                    onSelect: () => {
                        console.log(btn.action+"逻辑实现=》》》")
                        // this.swipeData.splice(index, 1)
                    }
                }).show()
            }
        }
    }
}
</script>

<style lang="scss" scoped>
.cube-swipe{
    text-align: left;
}
</style>