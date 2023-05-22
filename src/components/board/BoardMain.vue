<template>
  <div>
    <board-search></board-search>
    <router-link to="/board/posts">글 작성</router-link>
    <section id="service" class="services pt-0">
      <div class="container" data-aos="fade-up">
        <div class="row gy-4">
          <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="100">
            <div class="card" v-for="(board, index) in boards" :key="index"> <!-- 여기 반복 -->
              <div class="card-img">
                <img src="https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=903cc46f-8831-4569-ac7e-06fe418b6b11" alt="" class="img-fluid" />
              </div>
              <h3><router-link to="`/${board.boardId}`" class="stretched-link">title</router-link></h3>
              <p>
                # 제주코스 #휴애리자연생활공원 #서귀포갈곳
              </p>
            </div>
          </div>
          <!-- End Card Item -->
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import BoardSearch from "./BoardSearch.vue";

export default {
  name: "MainPage",
  components: {
    BoardSearch
  },
  data() {
    return {
      boards: []
    }
  },
  methods: {
    async getBoardList() {
      let response = await http.get("/api/v1/boards");
      let { data } = response;
      console.log("data: " + data);
      this.boards = data;
    }
  }
};
</script>

<style></style>
