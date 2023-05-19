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
    console.log('token', this.token)
    window.location.href = '/';
  }
};
</script>

<style>
</style>
