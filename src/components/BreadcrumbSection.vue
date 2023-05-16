<template>
  <div class="breadcrumbs">
    <div class="page-header d-flex align-items-center" :style="headerStyle">
      <div class="container position-relative">
        <div class="row d-flex justify-content-center">
          <div class="col-lg-6 text-center">
            <h2>{{ title }}</h2>
            <p>{{ description }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      required: true,
    },
    description: {
      type: String,
      required: true,
    },
    headerStyle: {
      type: Object,
      default() {
        return {
          backgroundColor: "#8CC7D9",
        };
      },
    },
  },
};
</script>
