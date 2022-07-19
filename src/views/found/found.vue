<template>
<div>
  <div class="listView">
    <app-list-vue></app-list-vue>
  </div>
  <common-footer-vue></common-footer-vue>
</div>
</template>

<script>
import CommonFooterVue from '@/components/CommonFooter.vue'
import appListVue from './components/appList.vue'
export default {
components:{
    CommonFooterVue,
    appListVue
}
}
</script>

<style lang="scss" scoped>
.div{
  display: flex;
}
.listView{
  position: relative;
  width: 100%;
 height: calc(100vh);
}
</style>