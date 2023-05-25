<template>
  <main id="main">
    <breadcrumb-section title="블로그" description="글 작성" />
    <div class="container-insert">
      <!-- 캐러셀 시작 -->
      <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img :src="`${fileList[0]}`" class="d-block w-100" />
          </div>
          <div class="carousel-item" v-for="(file, index) in fileList.slice(1, fileList.length)" :key="index">
            <img :src="file" class="d-block w-100" />
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
      <!-- 캐러셀 끝 -->
      <div class="right-container">
        <div class="form-floating">
          <select class="form-select" id="floatingSelect" @change="selectTrip" aria-label="Floating label select example">
            <!-- <option selected>Zero</option> -->
            <option v-for="(trip, index) in myTripList" :key="index" :value="index">{{ trip.planTitle }}</option>
          </select>
          <label for="floatingSelect">나의 여행 목록</label>
        </div>
        <div class="form-floating">
          <select class="form-select" id="floatingSelect" @change="selectLocation" aria-label="Floating label select example">
            <option v-for="(location, index) in myTripLocationList" :key="index" :value="index">{{ location.spotName }}</option>
          </select>
          <label for="floatingSelect">위치 선택</label>
        </div>
        해시태그
        <div class="input-group mb-3">
          <button class="btn btn-outline-secondary" type="button" id="button-addon1">#</button>
          <input
            type="text"
            class="form-control"
            v-model="hashTag"
            placeholder=""
            aria-label="Example text with button addon"
            aria-describedby="button-addon1"
          />
        </div>

        <!-- input box -->
        <div class="form-floating">
          <textarea class="form-control" v-model="content" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 400px"></textarea>
          <label for="floatingTextarea2">내용</label>
        </div>
        <button type="button" class="btn btn-primary" @click="postBlog()" style="float: right">등록하기</button>
      </div>
    </div>
  </main>
</template>

<script>
import BreadcrumbSection from "@/components/BreadcrumbSection.vue";
import http from "@/common/axios";

export default {
  components: {
    BreadcrumbSection,
  },
  data() {
    return {
      fileList: this.$route.params.fileList,
      myTripList: [],
      selectTripIdx: 1,
      myTripLocationList: [],
      locationIdx: 1,
      hashTag: "",
      content: "",
    };
  },
  methods: {
    async getMyTripList() {
      let token = localStorage.getItem("token");
      console.log(token);
      let response = await http.get("/api/v1/plan/my-plans", {
        headers: {
          Authorization: "Bearer " + token,
        },
      });
      let { data } = response;
      this.myTripList = data;
      this.selectTripIdx = this.myTripList[0].planId;
      this.boards = data;
      this.getMyTripLocation();
    },
    async getMyTripLocation() {
      let token = localStorage.getItem("token");
      console.log(token);
      let response = await http.get("/api/v1/plan/my-plan/" + this.selectTripIdx);
      let { data } = response;
      this.myTripLocationList = data;
    },
    async postBlog() {
      let token = localStorage.getItem("token");
      console.log(token);
      const val = {
        planId: this.selectTripIdx,
        tripPlanSpotId: this.locationIdx,
        content: this.content,
        hashTag: this.hashTag,
        fileList: this.fileList,
      };
      console.log(val);
      let response = await http.post("/api/v1/blog", val, {
        headers: {
          Authorization: "Bearer " + token,
        },
      });
      let { data } = response;
      console.log("data: " + data);
      if (data == 1) {
        alert("글 작성 완료");
        this.$router.push("/board");
      } else alert("글 작성에 실패했습니다.");
    },
    selectTrip(event) {
      const selectedOptionIndex = event.target.value;
      this.selectTripIdx = selectedOptionIndex + 1;
      this.getMyTripLocation();
    },
    selectLocation(event) {
      const selectedLocationIndex = event.target.value;
      this.locationIdx = selectedLocationIndex + 1;
    },
  },
  created() {
    this.getMyTripList();
  },
};
</script>

<style scoped>
.container-insert {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

#carouselExampleControls {
  max-width: 1080px;
  height: 1080px;
}
</style>
