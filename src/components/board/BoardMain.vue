<template>
  <div>
    <board-search></board-search>
    <board-img></board-img>
  </div>
</template>

<script>
import BoardSearch from "./BoardSearch.vue";
import BoardImg from "./BoardImg.vue";

export default {
  name: "MainPage",
  components: {
    BoardSearch,
    BoardImg
  },
};
</script>

<style></style>
