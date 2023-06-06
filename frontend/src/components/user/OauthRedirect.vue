<template>
</template>

<script>

export default {
  data() {
    return {
      token: ""
    }
  },
  created() {
    this.token = this.$route.query.token
    localStorage.setItem("token", this.token);
    window.location.href = '/';
  }
};
</script>

<style>
</style>
