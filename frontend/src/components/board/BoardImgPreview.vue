<template>
  <main id="main">
    <breadcrumb-section title="Preview" description="이미지 미리보기"/>
    <section id="contact" class="contact">
      <div class="container" data-aos="fade-up">
          <div class="upload" @click="imgInput"> 이미지 첨부하기 </div>
          <input @change="selectImg" type="file" class="real-upload" accept="image/*" required multiple/>
          <ul class="image-preview"></ul>
          <div class="upload" @click="imgDelete"> 이미지 전체 삭제하기 </div>
        <a href="#" @click.prevent="sendImg">글 작성하러 가기</a>
      </div>
    </section>
  </main>
</template>

<script>

import BreadcrumbSection from "@/components/BreadcrumbSection.vue";

export default {
    components: {
        BreadcrumbSection
    },
    data() {
        return {
            title: "",
            content: "",
            CKEditor: '',
            attachFile: false,
            fileList: [],
            selectDiv: null
        }
    },
    methods: {
      initUI() {
        document.querySelector('li').innerHTML = "";
        document.querySelector('img').innerHTML = "";
      },
      imgInput() {
        const realUpload = document.querySelector('.real-upload');
        realUpload.click();
      },
      selectImg(fileEvent) {
        const imagePreview = document.querySelector('.image-preview');
        const fileArray = Array.from(fileEvent.target.files); // Array 로 변환 가능

        if((fileArray.length + this.fileList.length) > 6) alert("이미지 등록은 6개까지 가능합니다.");
        else {
          fileArray.forEach(file => {
              this.fileList.push(file);
              const reader = new FileReader();
              reader.onload = (e) => {
                const preview = this.createLi(e, file);
                imagePreview.appendChild(preview);
              }
              reader.readAsDataURL(file);
          });
        }
      },
      createLi(e, file) {
        const li = document.createElement('li');
        const img = document.createElement('img');
        img.setAttribute('src', e.target.result);
        img.setAttribute('data-file', file.name);
        li.appendChild(img);

        return li;
      },
      imgDelete() {
        const imgElements = document.querySelectorAll('img');
        const liElements = document.querySelectorAll('li');
        imgElements.forEach((img) => {
          img.parentNode.removeChild(img); // img 요소 삭제
        });
        liElements.forEach((li) => {
          li.parentNode.removeChild(li); // img 요소 삭제
        });
        this.fileList = [];
      },
      sendImg(){
        console.log('BoardImgPreview : sendImg() ')
        this.$router.push( 
          { 
            name: 'posts', 
            params: {
              fileList : this.fileList 
            } 
          } 
        ); // Not Working
      },
    }
};
</script>

<style>
.wrap-container {
  width: 460px;
  margin: 0 auto;
}
.container-mid {
  display: flex;
}

.right-container {
  margin-top: 22px;
  margin-right: 10%;
}

.text {
  display: table;
  table-layout: fixed;
  width: 100%;
  border-radius: 6px 6px 0 0;
  padding: 10px 30px 10px;
}

#login-btn {
  border-radius: 6px 6px;
  padding: 10px 30px 10px;
  width: 100%;
}

.error {
    font-size: 12px;
    color: rgb(255, 87, 87);
}

li img {
  width: 200px;
  height: 200px;
}
li {
  list-style: none;
}
.image-preview {
  flex-direction: row;
  display: flex;
  flex-wrap: wrap;
  width: 90%;
  min-width: 500px;
  gap: 20px;
}
.real-upload {
  display: none;
}
</style>
