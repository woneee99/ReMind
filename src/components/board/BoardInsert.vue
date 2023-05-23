<template>

<div id="carouselExampleControlsNoTouching" class="carousel slide" data-bs-touch="false" data-bs-interval="false">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <!-- <img src="..." class="d-block w-100" alt="..."> -->
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControlsNoTouching" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControlsNoTouching" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
</template>

<script>
export default {
    data() {
        return {
            fileList: localStorage.getItem("fileList")
        }
    },
    methods: {
      // async writeBoard() {
      //   let formData = new FormData();
      //   formData.append("title", this.title);
      //   formData.append("content", this.content);
      //   formData.append("blogFile", this.fileList);
      
      //   let options = {
      //     headers: { 'Content-Type': 'multipart/form-data'}
      //   }
      //   await http.post('/api/v1/board', formData, options)
      //     .then(response => {
      //       let { data } = response;
      //       if(data.result == 1) {
      //         this.$router.push("/board")
      //       }
      //       else alert("게시판 등록에 실패하였습니다.")
      //     })
      //     .catch(error => {
      //       console.log("errr: " + error);
      //     });
      // }
    }
}
</script>

<style scoped>

</style>