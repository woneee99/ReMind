import Vue from "vue"; // defalut module
import VueRouter from "vue-router"; // installed module
import MainPage from "@/components/MainPage.vue"

Vue.use(VueRouter);


export default new VueRouter({
    routes: [
        {
            path: "/",
            component: MainPage,
        }
    ]
})