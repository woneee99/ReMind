<template>
  <div>
    <breadcrumb-section title="나의 세부 여행 일정" description="계획한 여행지를 상세히 보세요" />
    <div class="container">
      <div class="row justify-content-center mt-3">
        <div class="col-sm-8 col-md-6">
          <div v-for="(spots, index) in mySpots" :key="index" class="card border-light px-0 shadow mb-5 bg-body rounded text-center">
            <div class="card-header">
              <h5 class="fw-bold m-0">{{ `${index + 1}일차` }}</h5>
              <small class="">{{ spots[0].tripDate }}</small>
            </div>
            <div class="card-body">
              <div v-for="(spot, spotIndex) in spots" :key="spot.tripSpotId" class="m-2 mb-1">
                <h6 class="fw-bold">{{ spot.spotName }}</h6>
                <p class="mb-0" v-if="spotIndex === 0">{{ formatTime(spot.departureTime) }} ~</p>
                <p class="mb-0" v-else-if="spotIndex === spots.length - 1">
                  {{ formatTime(spot.departureTime) }}
                </p>
                <p class="mb-0" v-else>
                  {{ formatTime(spot.departureTime - spot.duration) }} ~
                  {{ formatTime(spot.departureTime) }}
                </p>
                <i v-if="spotIndex !== spots.length - 1" class="bi bi-caret-down-fill"></i>
              </div>
            </div>
          </div>
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
      token: null,
      mySpots: [],
    };
  },
  async created() {
    const planId = this.$route.query.planId;
    console.log(planId);
    this.token = localStorage.getItem("token");
    if (planId && this.token) {
      // getUser 메서드가 완료될 때까지 대기하는 async/await
      await http
        .get("/api/v1/plan/my-plan/" + planId, {
          headers: {
            Authorization: "Bearer " + this.token,
          },
        })
        .then((response) => {
          let { data } = response;
          this.mySpots = data;
        })
        .catch((error) => {
          console.error(error);
        });

      console.log(this.mySpots);
    }
  },
  methods: {
    formatTime(time) {
      const hours = Math.floor(time / 3600) % 24;
      const minutes = Math.floor((time % 3600) / 60);
      const nextDay = Math.floor(time / 86400); // 계산된 시간이 24시간 이상인 경우 다음 날로 계산

      let formattedTime = `${hours}시 ${minutes}분`;
      if (nextDay > 0) {
        formattedTime = "다음 날 " + formattedTime;
      }
      return formattedTime;
    },
  },
};
</script>

<style></style>
