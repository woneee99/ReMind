<template>
  <main id="main">
    <breadcrumb-section title="블로그" description="글 작성"/>
    <div id="carouselExampleControlsNoTouching" class="carousel slide" data-bs-touch="false" data-bs-interval="false">
      <div class="carousel-inner">
        <div class="carousel-item active" v-for="(file, index) in fileList" :key="index">
          <div class="card mb-3" style="max-width: 540px; margin: 0 auto">
            <div class="row g-0">
              <div class="col-md-4">
                <img src="@/assets/noProfile.png" class="img-fluid rounded-start">
              </div>
              <div class="col-md-8">
                <div class="card-body">
                  <h5 class="card-title">
                    <input type="text" name="title" v-model="title" placeholder="제목 작성" />
                  </h5>
                  <p class="card-text">
                    <input type="text" name="content" v-model="content" placeholder="내용 작성" />
                  </p>
                  <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="carousel-item">
          <img src="@/assets/noProfile.png" class="d-block w-100">
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControlsNoTouching" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControlsNoTouching" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
  </main>
</template>

<script>
import BreadcrumbSection from "@/components/BreadcrumbSection.vue";
import {eventBus} from "../../main.js"

export default {
    components: {
        BreadcrumbSection
    },
    data() {
        return {
            fileList: []
        }
    },
    methods: {
      handleArray(arr) {
        this.fileList = arr;
      }
        
    },
    created() {
      eventBus.$on('fileList', this.handleArray())
    }
}
</script>

<style scoped>

</style>