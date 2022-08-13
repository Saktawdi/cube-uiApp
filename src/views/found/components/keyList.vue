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
    props: {
        swipeData: Array
    },
    methods: {
        onItemClick(item) {
            console.log('click item:', item.value)
            this.$emit("showKeyJson",item.JsonData)
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
.swipe-wrapper {
    color: rgb(82, 136, 76);
    // background-image: linear-gradient(to top, #09203f 0%, #537895 100%);
    width: 100%;
    height: 100%;
}
</style>