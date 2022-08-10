<template>
    <div>
        <header>
            <div class="header">
                <span @click="$router.back(-1)"> <i class="cubeic-back"></i> </span>
                <div class="title">
                    {{Info}}
                </div>
            </div>
        </header>

    </div>
</template>

<script>
export default {
     props:{
        Info:{
            type:String,
            required:true
        }
    }
}
</script>

<style lang="scss" scoped>
.header {
    display: flex;//flex左右布局
    background-color: #07111b;
    padding: 10px 20px;
    color: #fff;
}  
// 返回箭头
.cubeic-back {
    color: #fff;
    margin-right:5px;
}

.title {
    font-size: 16px;
    //超出省略
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
}
</style>