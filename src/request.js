import axios from "axios";

export const requestConfig={
    baseURL:"http://localhost:8089/",
    timeout:5000
}


const service =axios.create({
    // baseURL:"http://hospital.cnwdi.top/",
    baseURL:requestConfig.baseURL,
    //配置请求超时时间
    timeout:requestConfig.timeout
});

export default service;