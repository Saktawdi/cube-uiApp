<template>
  <div class="main">
    <cube-form :model="model" @submit="submitHandler">
      <cube-form-group>
        <!-- 账号 -->
        <cube-form-item :field="fields[0]"></cube-form-item>
        <!-- 密码 -->
        <cube-form-item :field="fields[1]"></cube-form-item>
      </cube-form-group>

      <cube-form-group>
        <cube-button type="submit">登录</cube-button>
      </cube-form-group>

    </cube-form>
  </div>
</template>

<script>
import { loginApi } from "@/api/getData.js";


export default {
  data() {
    return {
      model: {
        numValue: "",
        pwdValue: ''
      },
      fields: [{
        type: "input",
        modelKey: "numValue",
        label: "学号/职工号",
        props: {
          placeholder: "请输入学号/职工号"
        },
        rules: {
          required: true
        },
        messages: {
          required: "学号/职工号不能为空"
        }
      },
      {
        type: "input",
        modelKey: "pwdValue",
        label: "密码",
        props: {
          placeholder: "请输入密码",
          type: "password",
          eye: {
            open: false
          }
        },
        rules: {
          required: true
        },
        messages: {
          required: "密码不能为空"
        }
      }
      ]
    }
  },

  methods: {
    submitHandler(e, model) {
      e.preventDefault();
      loginApi(model.numValue, model.pwdValue).then(
        res => {
          if (res.data.success === true) {
            localStorage.setItem('token', res.data.data)
            this.$store.dispatch('setToken')

            this.$router.push({ path: '/' })
          } else {
            const toast = this.$createToast({
              txt: "登录失败",
              type: "error",
              time: 1500
            });
            toast.show();
          }
        }
      )
    }
  }
}
</script>

<style lang="scss" scoped>
.main {
  padding: 50px 5% 0;
  text-align: center;
}

//登录
.cube-btn {
  margin-top: 20px;
}
</style>