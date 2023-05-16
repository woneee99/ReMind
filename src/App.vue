<template>
  <div>
    <nav-bar />
    <router-view></router-view>
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
};
</script>

<style></style>
