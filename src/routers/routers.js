import Vue from "vue"; // defalut module
import VueRouter from "vue-router"; // installed module
import MainPage from "@/components/main/MainPage.vue";
import LoginPage from "@/components/user/LoginPage";
import ProfilePage from "@/components/user/ProfilePage";
import registerPage from "@/components/user/RegisterPage";
<<<<<<< HEAD
import findPwdPage from "@/components/user/FindPwd";
import TripPage from "@/components/trip/TripPage";
=======
import FaqPage from "@/components/FAQ/FaqPage";
>>>>>>> f32afa1fb55857b3f11257a44b079862f4f24846

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
      path: "/profile",
      component: ProfilePage,
    },
    {
      path: "/faq",
      component: FaqPage,
    },
  ],
});
