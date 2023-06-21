<template>
  <div>
    <section id="hero" class="hero d-flex align-items-center">
      <div class="container">
        <div class="row gy-4 d-flex justify-content-center">
          <div class="col-lg-6 order-2 order-lg-1 d-flex flex-column justify-content-center">
            <h2 data-aos="fade-up"># 여행 기록</h2>

            <form action="#" class="form-search d-flex align-items-stretch mb-3" data-aos="fade-up" data-aos-delay="200">
              <input type="text" v-model="hashTag" class="form-control" placeholder="# 해시 태그" />
              <button type="submit" @click="getBoardListWithParam()" class="btn btn-primary">Search</button>
            </form>
          </div>
        </div>
      </div>
    </section>
    <section id="service" class="services pt-0">
      <div class="container" data-aos="fade-up">
        <div class="row gy-4">
          <router-link to="/imgs"><img src="../../assets/plus.png" style="width: 50px; float: right" /></router-link>
          <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="100" v-for="(blog, index) in blogList" :key="index">
            <div class="card" style="max-height: 500px">
              <!-- 여기 반복 -->
              <div class="card-img" style="max-height: 400px">
                <img :src= "getImgSrc(index)" alt="" class="img-fluid" />
              </div>
              <h3>
                <a href="#" @click.prevent="selectCard(blog.blogId)" class="stretched-link"> {{ blog.content }}</a>
              </h3>
              <p># {{ blog.hashTag }}</p>
            </div>
          </div>
          <!-- End Card Item -->
        </div>
      </div>
      <div class="pagination justify-content-center">
        <button v-if="nowIdx > 0" @click="getBoardList(nowIdx-1, offset-9)" class="btn btn-lg bi bi-caret-left-square-fill"></button>
        <button v-if="(nowIdx+1)*9 < total" @click="getBoardList(nowIdx+1, offset+9)" class="btn btn-lg bi bi-caret-right-square-fill"></button>
      </div>
    </section>
  </div>
</template>

<script>
import BoardSearch from "./BoardSearch.vue";
import http from "@/common/axios";

export default {
  name: "MainPage",
  components: {
    BoardSearch,
  },
  data() {
    return {
      blogList: [],
      previous: "",
      next: "",
      hashTag: "",
      nowIdx: 0,
      offset: 0,
      total: 0
    };
  },
  methods: {
    getImgSrc(index) {
      return "data:image/jpeg;base64," + this.blogList[index].images;
    },
    async getBoardList(nowIdx, offset) {
      let response = await http.get("/api/v1/blog", {
        params: {
          offset: offset
        }
      });
      let data = response.data;
      console.log("dddd" + data);
      this.nowIdx = nowIdx;
      this.offset = offset;
      this.blogList = data;
      console.log(this.blogList);
    },
    async getBoardListWithParam() {
      let response = await http.get("/api/v1/blog/list", {
        params: {
          hashTag: this.hashTag,
        },
      });

      let data = response.data;
      console.log(data);
      this.blogList = data;
    },
    async getImg() {
      console.log(typeof this.blogId);
      let response = await http.get("/api/v1/blog/" + this.blogId); // 블로그 가져오기

      let { data } = response;

      this.userName = data.userName;
      this.profileImageUrl = data.profileImageUrl;
      this.content = data.content;
      this.fileList = data.fileList;
      this.hashTag = data.hashTag;
    },
    selectCard(index) {
      console.log("BoardImgPreview : sendImg() ");
      this.$router.push({
        name: "details",
        params: {
          blogId: index,
        },
      }); // Not Working
    },
    async getBoardCount() {
      let response = await http.get("/api/v1/blog/count");
      let { data } = response;
      console.log("total: " + data)
      this.total = data;

    }
  },
  created() {
    this.getBoardList(0, 0);
    this.getBoardCount();

  },
};
</script>

<style></style>
