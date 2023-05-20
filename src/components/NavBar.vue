<template>
  <header id="header" class="header d-flex align-items-center fixed-top">
    <div class="container-fluid container-xl d-flex align-items-center justify-content-between">
      <router-link to="/" class="logo d-flex align-items-center">
        <!-- Uncomment the line below if you also wish to use an image logo -->
        <!-- <img src="assets/img/logo.png" alt=""> -->
        <h1>Enjoy Trip</h1>
      </router-link>

      <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
      <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>
      <nav id="navbar" class="navbar">
        <ul>
          <li><router-link to="/">여행계획</router-link></li>
          <li><router-link to="/trip">지역 관광지</router-link></li>
          <li class="dropdown" v-if="isLogin">
            <a href="/">
              <span >{{ name }}</span> 
              <i class="bi bi-chevron-down dropdown-indicator"></i>
            </a>
            <ul>
              <li><router-link to="/profile">프로필 관리</router-link></li>
              <li><a href="/">내 여행</a></li>
              <li><router-link to="/faq">공지사항 / FAQ</router-link></li>
            </ul>
          </li>
          <li>
            <router-link to="/" v-if="isLogin"> Logout </router-link>
            <router-link to="/login" v-else class="get-a-quote">Log In / Sign Up</router-link>
          </li>
        </ul>
      </nav>
      <!-- .navbar -->
    </div>
  </header>
</template>

<script>
import http from '@/common/axios'

export default {
  data() {
    return {
      name: "",
      isLogin: false,
      token: localStorage.getItem("token")
    }
  },
  methods: {
    async getUser() {
      let response = await http.get("/api/v1/users");
      let { data } = response;
      this.name = data.userName;
      console.log(data);
      console.log(this.name)
    }
  },
  created() {
    this.token = localStorage.getItem("token");
    console.log(this.token)
    if(this.token != null) {
      this.isLogin = true;
      this.getUser();
    }
  },
  watch: {
    'isLogin': function() {
      if(this.token != null) {
        this.isLogin = true;
        this.getUser();
      }
    },
    'token': function() {
      if(this.token != null) {
        this.isLogin = true;
        this.getUser();
      }
    }
  }
};
</script>

<style></style>
