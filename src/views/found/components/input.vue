<template>
<div class="newCom">
<span>{{name}}  :  </span>
<input type="text" placeholder="请输入字段值" v-model=value v-bind:id="idName">
</div>
   
</template>

<script>
export default {
    props:{
        name:{
            type:String,
            default:""
        },
        value:{
            type:String,
            defualt:""
        },
        index:{
            type:Number,
            defualt: 1
        }
    },
    data(){
        return{
            idName:"myInput"+this.index,
        }
    }
}
</script>

<style lang="scss" scoped>
.newCom{
    color:rgb(49, 139, 117)
}
input {
    margin-top:5px;
    appearance: none;
    text-align: center;
    height: 36px;
    width: 248px;
    border: 0px solid #fff;
    padding: 0 8px;
    outline: 0;
    letter-spacing: 1px;
    color: rgb(65, 160, 65);
    font-weight: 600;
    background: rgba(60, 95, 95, 0.1);
    border: 1px solid rgba(64, 161, 129, 0.15);
    box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    text-shadow: 0 1px 2px rgba(0,0,0,.1);
}
input {
    box-shadow: 0 0 5px 4px rgba(0, 0, 0,0.1);
}
</style>