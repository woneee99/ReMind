import Vue from "vue"; // defalut module
import VueRouter from "vue-router"; // installed module
import MainPage from "@/components/main/MainPage.vue";
import LoginPage from "@/components/user/LoginPage";
import ProfilePage from "@/components/user/ProfilePage";
import registerPage from "@/components/user/RegisterPage";
import findPwdPage from "@/components/user/FindPwd";
import TripPage from "@/components/trip/TripPage";
import FaqPage from "@/components/FAQ/FaqPage";

Vue.use(VueRouter);

export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      component: MainPage,
    },
    {
      path: "/login",
      component: LoginPage,
    },
    {
      path: "/register",
      component: registerPage,
    },
    {
      path: "/find",
      component: findPwdPage,
    },
    {
      path: "/trip",
      component: TripPage,
    },
    {
      path: "/profile",
      component: ProfilePage,
    },
    {
      path: "/faq",
      component: FaqPage,
    },
  ],
});
