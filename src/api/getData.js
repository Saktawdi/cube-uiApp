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

//获取轮播图数据
export const getBannerApi = () => axios.get("api/v1/pub/banner/list")


//添加游戏key管理系统的game名字
export const addGamesApi=(JsonData,token) => axios.post("api/v1/pri/gameKey/addGames",{JsonData},{
    headers:{
        "token":token
    }
})
//获取游戏key管理系统的game名字
export const getGamesApi = (user_num,token) => axios.post("api/v1/pri/gameKey/getGames",{user_num},{
    headers:{
        "token":token,
    }
})
//获取用户密钥
export const getSecretKeyApi = (user_num,game_name,token) => axios.post("api/v1/pri/gameKey/getSecretKey",{user_num,game_name},{
    headers:{
        "token":token,
    }
})
//保存游戏key列表
export const saveKeysApi = (secret_key,list_json,token) => axios.post("api/v1/pri/gameKey/saveKeys",{secret_key,list_json},{
    headers:{
        "token":token,
    }
})
//获取key列表
export const getKeysApi = (secret_key,token) => axios.post("api/v1/pri/gameKey/getKeys",{secret_key},{
    headers:{
        "token":token,
    }
})



//查看所有报销单接口
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