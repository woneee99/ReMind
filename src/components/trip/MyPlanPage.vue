<template>
  <div>
    <breadcrumb-section title="나의 여행 일정" description="계획한 여행을 한 눈에 보세요." />
    <div class="container">
      <div class="row p-3 justify-content-center">
        <div class="card mb-3 col-5 mx-3">
          <div class="row g-0">
            <div class="col-md-5">
              <div class="card-body">
                <h5 class="card-title mt-3 fw-bold">제주도 여행</h5>
                <button type="button" class="btn btn-outline-primary col-8 my-1">일정 보기</button><br />
                <button type="button" class="btn btn-outline-danger col-8 my-1">일정 삭제</button>
              </div>
            </div>
            <div class="col-md-7">
              <div class="card-body">
                <h6 class="card-title">여행 일자</h6>
                <p class="card-text">2023.05.30 - 2023.06.02</p>
                <h6 class="card-title">최종 수정</h6>
                <p class="card-text">2023.05.21</p>
                <p class="card-text"><small class="text-muted">D-12</small></p>
              </div>
            </div>
          </div>
        </div>
        <div class="card mb-3 col-5 mx-3">
          <div class="row g-0">
            <div class="col-md-5">
              <div class="card-body">
                <h5 class="card-title mt-3">My First Trip Plan</h5>
                <button type="button" class="btn btn-outline-primary col-8 my-1">일정 보기</button><br />
                <button type="button" class="btn btn-outline-danger col-8 my-1">일정 삭제</button>
              </div>
            </div>
            <div class="col-md-7">
              <div class="card-body">
                <h6 class="card-title">여행 일자</h6>
                <p class="card-text">2023.05.30 - 2023.06.02</p>
                <h6 class="card-title">최종 수정</h6>
                <p class="card-text">2023.05.21</p>
                <p class="card-text"><small class="text-muted">D-12</small></p>
              </div>
            </div>
          </div>
        </div>
        <div class="d-grid gap-2 col-6 mx-auto">
          <router-link to="/plan" class="btn btn-outline-primary" type="button">새 여행 일정 만들기</router-link>
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
