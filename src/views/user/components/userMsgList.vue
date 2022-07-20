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
        swipeData:[]
    },
    methods: {
        onItemClick(item) {
            console.log('click item:', item)
        },
        onBtnClick(btn, index) {
            if (btn.action === 'delete') {
                this.$createActionSheet({
                    title: '确认要删除吗',
                    active: 0,
                    data: [
                        { content: '删除' }
                    ],
                    onSelect: () => {
                        this.swipeData.splice(index, 1)
                    }
                }).show()
            }
        }
    }
}
</script>

<style lang="scss" scoped>
</style>