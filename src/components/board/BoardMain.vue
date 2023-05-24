<template>
  <div>
    <board-search></board-search>
    <section id="service" class="services pt-0">
      <div class="container" data-aos="fade-up">
        <div class="row gy-4">
          <router-link to="/imgs"><img src="../../assets/plus.png" style="width: 50px; float: right;" /></router-link>
          <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="100" v-for="(blog, index) in blogList" :key="index">
            <div class="card" > <!-- 여기 반복 -->
              <div class="card-img">
                <img :src='`${blog.thumbNail}`' alt="" class="img-fluid" />
              </div>
              <!-- <h3><router-link :to="`/${boards.boardId}`" class="stretched-link">title</router-link></h3> -->
              <!-- <a href="#" @click.prevent="sendImg">글 작성하러 가기</a> -->
              <h3><a href="#" @click.prevent="selectCard(blog.blogId)" class="stretched-link"> {{blog.content}}</a></h3>
              <p>
               # {{blog.hashTag}}
              </p>
            </div>
          </div>
          <!-- End Card Item -->
        </div>
      </div>
      <div class="pagination justify-content-center">
        <a href="" role="button" class="btn btn-lg bi bi-caret-left-square-fill"></a>
        <a href="" role="button" class="btn btn-lg bi bi-caret-right-square-fill"></a>
    </div>
    </section>
  </div>
</template>

<script>
import BoardSearch from "./BoardSearch.vue";
import http from '@/common/axios'

export default {
  name: "MainPage",
  components: {
    BoardSearch
  },
  data() {
    return {
      blogList: [],
      previous: '',
      next: '',
    }
  },
  methods: {
    async getBoardList() {
      let response = await http.get("/api/v1/blog/list");
      let data = response.data;
      console.log(data)
      this.blogList = data;
      console.log(this.blogList)
      this.boards = data;
    },
    async getImg() {
      console.log(typeof this.blogId)
      let response = await http.get("/api/v1/blog/" + this.blogId); // 블로그 가져오기

      let { data } = response;

      this.userName = data.userName;
      this.profileImageUrl = data.profileImageUrl;
      this.content = data.content;
      this.fileList = data.fileList;
      this.boards = data;
      this.hashTag = data.hashTag;
    },
    selectCard(index){
      console.log('BoardImgPreview : sendImg() ')
      console.log(index)
      this.$router.push( 
        { 
          name: 'details', 
          params: {
            blogId : index
          } 
        } 
      ); // Not Working
    },
  },
  created() {
    this.getBoardList();
  }
};
</script>

<style></style>
