<template>
  <div class="user">
    <login-box></login-box>
  </div>
</template>

<script>
import loginBox from "./components/loginbox.vue"
import Vue from "vue";

export default {
  components: {
    loginBox,
  },
}
</script>

<style lang="scss" scoped>
</style>