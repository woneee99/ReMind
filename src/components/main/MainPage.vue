<template>
  <div>
    <hero-section></hero-section>
    <main-section></main-section>
  </div>
</template>

<script>
import HeroSection from "./HeroSection.vue";
import MainSection from "./MainSection.vue";

export default {
  name: "MainPage",
  components: {
    HeroSection,
    MainSection,
  },
};
</script>

<style></style>
