<template>
  <main id="main">
    <breadcrumb-section title="블로그" description="글 작성"/>
    <div class="container-insert">
      <!-- 캐러셀 시작 -->
      <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img :src='`${fileList[0]}`' class="d-block w-100">
          </div>
          <div class="carousel-item" v-for="(file, index) in fileList.slice(1, fileList.length)"  :key="index" >
            <img :src="file" class="d-block w-100">
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
      <div>
        <div class="dropdown">
          <button class="btn btn-secondary dropdown-toggle" @click="getMyTripList()" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
            내 여행 선택
          </button>
          <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
            <li><a class="dropdown-item" href="#">Action</a></li> <!-- 반복문 사용 -->
          </ul>
        </div>
        <div class="dropdown">
          <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
            위치 선택
          </button>
          <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1"> 
            <li><a class="dropdown-item">Action</a></li> <!-- 반복문 사용 -->
          </ul>
        </div>
        <!-- input box -->
        <div class="col-lg-8">
          <form action="forms/contact.php" method="post" role="form" class="php-email-form">
            <div class="form-group mt-3">
              <textarea class="form-control" name="message" rows="5" placeholder="Message" required></textarea>
            </div>
            <div class="text-center"><button type="submit">Send Message</button></div>
          </form>
        </div><!-- End Contact Form -->
      </div>
    </div>
  </main>
</template>

<script>
import BreadcrumbSection from "@/components/BreadcrumbSection.vue";
import http from '@/common/axios'

export default {
    components: {
        BreadcrumbSection
    },
    data() {
        return {
            fileList: this.$route.params.fileList,
            carouselList: []
        }
    },
    methods: {
      async getMyTripList() {
        let token = localStorage.getItem("token");
        console.log(token)
        let response = await http.get("/api/v1/blog/myList", {
          headers: {
            'Authorization': 'Bearer ' + token
          }
        })
        let { data } = response;
        console.log("data: " + data);
        this.carouselList = data;
        console.log("data: " + this.carouselList);
        this.boards = data;
      }
    },
}
</script>

<style scoped>
.container-insert{
  display: flex;
  flex-direction: row;
  justify-content: center;
}

#carouselExampleControls {
  max-width: 1080px;
  height: 1080px;
}
</style>