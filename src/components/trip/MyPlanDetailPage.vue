<template>
  <div>
    <breadcrumb-section title="나의 세부 여행 일정" description="계획한 여행지를 상세히 보세요" />
    <div class="container">
      <div class="row justify-content-center mt-3">
        <div class="col-sm-8 col-md-6">
          <div class="card border-light px-0 shadow mb-5 bg-body rounded text-center">
            <h5 class="card-header fw-bold">1일차 5월 31일</h5>
            <div class="card-body">
              <div class="m-2 mb-1">
                <h6 class="fw-bold">여행지 1</h6>
                <p class="mb-0">09:00 ~</p>
              </div>
              <i class="bi bi-caret-down-fill"></i>
              <div class="m-2">
                <h6 class="fw-bold">여행지 2</h6>
                <p class="mb-0">11:00 ~ 11:30</p>
              </div>
              <i class="bi bi-caret-down-fill"></i>
              <div class="m-2">
                <h6 class="fw-bold">여행지 3</h6>
                <p class="mb-0">12:10 ~ 13:00</p>
              </div>
              <i class="bi bi-caret-down-fill"></i>
              <div class="m-2">
                <h6 class="fw-bold">여행지 4</h6>
                <p class="mb-0">13:20 ~ 14:00</p>
              </div>
              <i class="bi bi-caret-down-fill"></i>
              <div class="m-2">
                <h6 class="fw-bold">여행지 5</h6>
                <p class="mb-0">~ 14:00</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BreadcrumbSection from "@/components/BreadcrumbSection.vue";

export default {
  components: {
    BreadcrumbSection,
  },
};
</script>

<style></style>
