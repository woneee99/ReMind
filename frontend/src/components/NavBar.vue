<template>
  <header id="header" class="header d-flex align-items-center fixed-top">
    <div class="container-fluid container-xl d-flex align-items-center justify-content-between">
      <router-link to="/" class="logo d-flex align-items-center">
        <!-- Uncomment the line below if you also wish to use an image logo -->
        <img src="assets/img/brand_logo.png" alt="" />
        <!-- <h1>Enjoy Trip</h1> -->
      </router-link>

      <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
      <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>
      <nav id="navbar" class="navbar">
        <ul>
          <li><router-link to="/myplans">여행계획</router-link></li>
          <li><router-link to="/trip">지역 관광지</router-link></li>
          <li><router-link to="/board">Community</router-link></li>
          <li class="dropdown" v-if="isLogin">
            <router-link to="/profile">
              <span>{{ name }}</span>
              <i class="bi bi-chevron-down dropdown-indicator"></i>
            </router-link>
            <ul>
              <li><router-link to="/profile">프로필 관리</router-link></li>
              <li><router-link to="/myplans">내 여행</router-link></li>
              <li><router-link to="/faq">공지사항 / FAQ</router-link></li>
              <li><router-link @click.native="logout" to="/" v-bind="login"> Logout </router-link></li>
            </ul>
          </li>
          <li v-if="!isLogin">
            <router-link to="/login" class="get-a-quote">Log In / Sign Up</router-link>
          </li>
        </ul>
      </nav>
      <!-- .navbar -->
    </div>
  </header>
</template>

<script>
import http from "@/common/axios";

export default {
  props: ["isLogin"],
  data() {
    return {
      name: "",
      token: localStorage.getItem("token"),
    };
  },
  methods: {
    async getUser() {
      console.log(this.token);
      await http
        .get("/api/v1/users", {
          headers: {
            Authorization: "Bearer " + this.token,
          },
        })
        .then((response) => {
          let { data } = response;
          console.log(data);
          this.name = data.userName;
          this.$emit("login-success", true);
        })
        .catch((error) => {
          console.error(error);
        });

      console.log(this.name);
    },
    async logout() {
      await http
        .get("/api/v1/auth/logout", {
          headers: {
            Authorization: "Bearer " + this.token,
          },
        })
        .then((response) => {
          console.log(response.data);
          if (response.data == 1) {
            localStorage.removeItem("token");
            this.token = "";
            this.$emit("login-success", false);
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
  computed: {
    login: function () {
      this.token = localStorage.getItem("token");
      if (this.token) {
        this.getUser();
      }
    },
  },
  created() {
    this.token = localStorage.getItem("token");
    if (this.token != null) {
      console.log(this.token);
      this.$emit("login-success", true);
      this.getUser();
    }
  },
};
</script>

<style></style>
