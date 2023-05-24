<template>
  <div>
    <breadcrumb-section title="나의 여행 일정" description="계획한 여행을 한 눈에 보세요." />
    <div class="container">
      <div class="row p-3 justify-content-center">
        <div v-for="plan in myPlans" :key="plan.planId" class="card mb-3 col-5 mx-3">
          <div class="row g-0">
            <div class="col-md-5">
              <div class="card-body">
                <h5 class="card-title mt-3 fw-bold">{{ plan.planTitle }}</h5>
                <button type="button" class="btn btn-outline-primary col-8 my-1" @click="redirectToMyPlan(plan.planId)">일정 보기</button><br />
                <button type="button" class="btn btn-outline-danger col-8 my-1" @click="deleteMyPlan(plan.planId)">일정 삭제</button>
              </div>
            </div>
            <div class="col-md-7">
              <div class="card-body">
                <h6 class="card-title">여행 일자</h6>
                <p class="card-text">{{ plan.startDate }} - {{ plan.endDate }}</p>
                <h6 class="card-title">계획 생성 일자</h6>
                <p class="card-text">{{ plan.createdAt }}</p>
                <p class="card-text"><small class="text-muted">D-2</small></p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row p-3 pt-0 justify-content-center">
        <div class="d-grid gap-2 col-6 mx-auto">
          <router-link to="/plan" class="btn btn-outline-primary" type="button">새 여행 일정 만들기</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/common/axios";
import BreadcrumbSection from "@/components/BreadcrumbSection.vue";

export default {
  components: {
    BreadcrumbSection,
  },
  data() {
    return {
      userSeq: 0,
      token: null,
      myPlans: [],
    };
  },
  created() {
    this.token = localStorage.getItem("token");
    if (this.token != null) {
      // this.getUser();

      // getUser 메서드가 완료될 때까지 대기하는 async/await
      this.$nextTick(async () => {
        try {
          // await this.getUser();
          const response = await http.get("/api/v1/plan/my-plans", {
            headers: {
              Authorization: "Bearer " + this.token,
            },
          });
          let { data } = response;
          this.myPlans = data;
        } catch (error) {
          console.error(error);
        }
      });

      console.log(this.myPlans);
    }
  },
  methods: {
    redirectToMyPlan(planId) {
      this.$router.push({ path: "/myplan", query: { planId } });
    },
    deleteMyPlan(planId) {
      if (this.token) {
        http
          .delete("/api/v1/plan/my-plan/" + planId, {
            headers: {
              Authorization: "Bearer " + this.token,
            },
          })
          .then((response) => {
            let { data } = response;
            console.log(data);
            const index = this.myPlans.findIndex((plan) => plan.planId === planId);
            if (index !== -1) {
              this.myPlans.splice(index, 1);
            }
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },
  },
};
</script>

<style></style>
