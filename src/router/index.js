import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/user/login.vue";
import Register from "../views/user/register.vue";
import Home from "../views/home/home.vue";
import Found from "../views/found/found.vue";
import UserInfo from "../views/user/userInfo.vue";
import GameKeySys from "@/views/found/gameKeySys.vue"
import EditorKey from "@/views/found/editorKey.vue"

Vue.use(VueRouter)

const routes = [{
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/register",
    name: "Register",
    component: Register
  },
  {
    path: "/",
    name: "Home",
    //按需加载
    component: () => import("../views/home/home.vue")
    //一次全加载
    //component:Home
  },
  {
    path: "/found",
    name: "Found",
    component: Found
  },
  {
    path: "/userInfo",
    name: "UserInfo",
    component: UserInfo
  }, 
  {
    path: "/gameKeySys",
    name: "GameKeySys",
    component: GameKeySys
  },
  {
    path:"/editorKey",
    name:"EditorKey",
    component:EditorKey
  }
]

const router = new VueRouter({
  routes
})

export default router
