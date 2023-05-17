import Vue from "vue"; // defalut module
import VueRouter from "vue-router"; // installed module
import MainPage from "@/components/main/MainPage.vue";
import LoginPage from "@/components/user/LoginPage";
import ProfilePage from "@/components/user/ProfilePage";
import registerPage from "@/components/user/RegisterPage";
import TripPage from "@/components/trip/TripPage";

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
      path: "/trip",
      component: TripPage,
    },
    {
      path: "/register",
      component: registerPage,
    },
    {
      path: "/profile",
      component: ProfilePage,
    },
  ],
});
