<template>
    <main id="main">
    <!-- ======= Breadcrumbs ======= -->
    <div class="breadcrumbs">
      <div class="page-header d-flex align-items-center" style="background-image: url('assets/img/page-header.jpg');">
        <div class="container position-relative">
          <div class="row d-flex justify-content-center">
          </div>
        </div>
      </div>
      <nav>
        <div class="container">
          <ol>
            <li><router-link to="/board">Blog Main</router-link></li>
            <li>게시글</li>
          </ol>
        </div>
      </nav>
    </div><!-- End Breadcrumbs -->
    <!-- ======= Service Details Section ======= -->
    <section id="service-details" class="service-details">
      <div class="container" data-aos="fade-up">

        <div class="row gy-4">

          <div class="col-lg-4">
            <div class="services-list">
              <img :src='`${profileImageUrl}`' > <!-- 블로그 주인 프로필 사진 -->
              <p> @ {{userName}}</p> <!-- 이름-->
            </div>
            <!-- 트립쪽 가져와지면 여행 루트 보여주기-->
            <!-- <h4>Enim qui eos rerum in delectus</h4> 
            <p>Nam voluptatem quasi numquam quas fugiat ex temporibus quo est. Quia aut quam quod facere ut non occaecati ut aut. Nesciunt mollitia illum tempore corrupti sed eum reiciendis. Maxime modi rerum.</p> -->
          </div>

          <div class="col-lg-8">
            <!-- 캐러셀 시작 -->
            <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
              <div class="carousel-inner">
                <div class="carousel-item active">
                  <img :src='`${fileList[0].blogUrl}`' class="d-block w-100">
                </div>
                <div class="carousel-item" v-for="(file, index) in fileList.slice(1, fileList.length)"  :key="index" >
                  <img :src="file.blogUrl" class="d-block w-100">
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
            <br />
            <h5>
              <span class="badge bg-info text-dark" v-for="(tag, index) in hashTag"  :key="index"> # {{tag.tagName}} </span> 
            </h5>
            <p>
              {{content}}
            </p>
          </div>
        </div>
      </div>
    </section><!-- End Service Details Section -->

  </main><!-- End #main -->

</template>
<script>
import http from '@/common/axios'

export default {
    data() {
        return {
          fileList: [
            { blogFileId : ''},
            { blogUrl : ""},
            { blogId : ''}
          ],
          blogId: this.$route.params.blogId,
          content: "",
          likeCount: "",
          createdAt: "",
          userName: "",
          profileImageUrl: "",
          hashTag: [
            { id : ''},
            { tagName : ""},
            { blogId : ''}
          ],
        }
    },
    methods: {
      async getImg() {
        let response = await http.get("/api/v1/blog/" + this.blogId); // 블로그 가져오기
        let { data } = response;
        this.userName = data.userName;
        this.profileImageUrl = data.profileImageUrl;
        this.content = data.content;
        this.fileList = data.fileList;
        console.log(this.fileList)
        this.boards = data;
        this.hashTag = data.hashTag;
      }
    },
    created() {
      this.getImg();
    }
}
</script>
<style scoped>
.services-list img {
  width: 100%;
}
</style>