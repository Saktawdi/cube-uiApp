<template>
   <div class="bannerBox">
    <cube-slide  :data="banners">
      <cube-slide-item
        v-for="(item, index) in banners"
        :key="index">

        <a :href="item.toUrl">
          <img :src="item.imgUrl"  style="width:100%"/>
        </a>
      
      </cube-slide-item>
    </cube-slide>
  </div>
</template>

<script>
export default {
    props: {
        banners: {
            type: Array,
            required: true
        }
    }
}
</script>

<style lang="scss" scoped>
.bannerBox {
  font-size: 0; //消除图片元素产生的间隙
  box-shadow: 0 4px 11px 0 rgba(43, 51, 59, 0.6); //设置图片阴影，rgba前三个参数是颜色编码，最后一个是透明度
  border-radius: 8px; //设置图片圆角
  height:calc(20vh);
  }
</style>