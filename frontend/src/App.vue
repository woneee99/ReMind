<template>
  <div id="app">
    <nav-bar v-bind:isLogin="isLogin" @login-success="loginSuccess" />
    <router-view @login-success="loginSuccess"></router-view>
    <footer-section></footer-section>
  </div>
</template>

<script>
import FooterSection from "./components/FooterSection.vue";
import NavBar from "./components/NavBar.vue";

export default {
  name: "App",
  components: {
    NavBar,
    FooterSection,
  },
  data() {
    return {
      isLogin: false,
      token: localStorage.getItem("token"),
    };
  },
  methods: {
    loginSuccess(data) {
      console.log("login: " + data);
      this.isLogin = data;
    },
  },
};
</script>

<style>
@font-face {
  font-family: "Pretendard-Regular";
  src: url("https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff") format("woff");
  font-style: normal;
}
#app {
  font-family: "Pretendard-Regular";
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
