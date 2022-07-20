import axios from "../request";

//登录接口
export const loginApi = (num, pwd) => axios.post("/api/v1/pri/user/login", {
    "num": num,
    "pwd": pwd
})

//注册接口
export const registerApi = (num, pwd, name, avatarUrl) => axios.post("/api/v1/pri/user/register", {
    num,
    pwd,
    name,
    avatarUrl
})
//上传图片文件接口
export const uploadImgApi = (formData) => axios.post("api/v1/pub/upload/avatar", formData)
//获取用户信息接口
export const getUserInfoApi = (token) => axios.get("api/v1/pri/user/userInfo",{
    params:{
        "token":token
    }
})


// //查看所有报销单接口
// export const allRequestApi = (token) => axios.post("/api/v1/pri/user/find_allRequests", {}, {
//     Headers: {
//         "token": token
//     }
// })

//写params的写法:
// export const exampleApi=(token)=>axios.get("/api/v1/pri/user/find_allRequests",{
//     params:{
//         "token":token
//     }
// })