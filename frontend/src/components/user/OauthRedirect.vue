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
    window.location.href = '/';
  }
};
</script>

<style>
</style>
