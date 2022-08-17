<template>
    <div class="main">
        <cube-form :model="model" @submit="submitHandler">
            <cube-form-group>
                <!-- 账号 -->
                <cube-form-item :field="fields[0]"></cube-form-item>
                <!-- 密码 -->
                <cube-form-item :field="fields[1]"></cube-form-item>
                <!-- 确认密码 -->
                <cube-form-item :field="fields[2]"></cube-form-item>
                <!-- name -->
                <cube-form-item :field="fields[3]"></cube-form-item>
                <!-- 头像 -->
                <cube-form-item :field="fields[4]">
                    <cube-upload ref="upload" :simultaneous-uploads="1" :max="1" :auto="false"
                        @files-added="filesAdded" />
                </cube-form-item>
            </cube-form-group>
            <cube-form-group>
                <cube-button type="submit"  :disabled=ifDisabled>注册</cube-button>
            </cube-form-group>
        </cube-form>
        <cube-button class="loginBtn" :outline="true" @click="ToLogin">登录</cube-button>
    </div>

</template>

<script>
import { registerApi, uploadImgApi } from '@/api/getData'
export default {
    data() {
        return {
            ifDisabled:false,
            fileList: new FormData(),
            model: {
                numValue: "",
                pwdValue: '',
                reallyPwdValue: '',
                nameValue: '',
                uploadValue: []
            },
            fields: [{
                type: "input",
                modelKey: "numValue",
                label: "账号",
                props: {
                    placeholder: "请输入账号",
                    type: "number",
                },
                rules: {
                    required: true
                },
                messages: {
                    required: "账号不能为空"
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
            },
            {
                type: "input",
                modelKey: "reallyPwdValue",
                label: "确认密码",
                props: {
                    placeholder: "请输入确认密码",
                    type: "password",
                    eye: {
                        open: false
                    }
                },
                rules: {
                    required: true
                },
                messages: {
                    required: "确认密码不能为空"
                }
            },
            {
                type: "input",
                modelKey: "nameValue",
                label: "名字",
                props: {
                    placeholder: "请输入您的名字"
                },
                rules: {
                    required: true
                },
                messages: {
                    required: "名字不能为空"
                }
            },
            {
                type: "upload",
                modelKey: "uploadValue",
                label: "您的头像",
                props: {
                    multiple: false,
                    max: 1
                },
                rules: {
                    required: false,
                },
                messages: {
                    uploaded: '上传失败，只能上传一个'
                }
            },
            ]
        }
    },
    methods: {
        submitHandler(e, model) {
            if (model.reallyPwdValue != model.pwdValue) {
                let toast = this.$createToast({
                    txt: "两次输入的密码不一致",
                    type: "error",
                    time: 1000
                });
                toast.show();
                return;
            }
            this.uploadImgAndRegister(model);
        },
        async uploadImgAndRegister(model) {
            try {
                if (model.uploadValue.length === 0) { 
                    model.uploadValue = "defaultFile/default"+parseInt(Math.random()*8)+".png";
                } else {
                    const uploadResult = await uploadImgApi(this.fileList);
                    const toast = this.$createToast({
                        txt: 'Loading...',
                        mask: true
                    })
                    toast.show();
                    this.ifDisabled=true;
                    if (uploadResult.data.success === true) {
                        model.uploadValue = uploadResult.data.data
                        toast.hide();
                    }
                }
                registerApi(model.numValue, model.pwdValue, model.nameValue, model.uploadValue).then(res => {
                    if (res.data.success === true) {
                        let toast = this.$createToast({
                            txt: "注册成功",
                            type: "correct",
                            time: 1000
                        });
                        toast.show();
                    } else {
                        let toast = this.$createToast({
                            txt: "注册失败",
                            type: "error",
                            time: 1000
                        });
                        toast.show();
                    }
                    this.ifDisabled=false;
                })
            } catch (error) {
                console.log(error)
            }
        },
        filesAdded(files) {
            for (let k in files) {
                const file = files[k];
                this.existFile = file;
                this.fileList.append('files', file);
            }
        },
        errHandler(file) {
            this.$createToast({
                type: "warn",
                txt: "Upload fail",
                time: 1000
            }).show();
        },
        ToLogin(){
            this.$router.push("/login");
        }
    }
}
</script>

<style lang="scss" scoped>
.main{
    margin-top: -10px;
     padding: 45px 5% 0;
  text-align: center;
}
.loginBtn{
    margin-top:10px;
}
</style>