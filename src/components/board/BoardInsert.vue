<template>
  <main id="main">
    <breadcrumb-section title="로그인" description="등록하신 이메일, 패스워드로 로그인해주세요."/>
    <section id="contact" class="contact">
      <div class="container" data-aos="fade-up">
          <div class="wrap-container">
            <div class="php-email-form">
                <div class="text">
                    제목
                    <input type="text" v-model="title" class="form-control" id="name" placeholder="title" required />
                </div>
                <div class="text">
                    내용
                    <input type="text" v-model="content" class="form-control" placeholder="content" required />
                </div>
                <div class="text">
                    프로필 사진 첨부
                    <input @change="selectImg" type="file" id="inputImg" class="img-container" accept="image/*" multiple/>
                </div>
                <div class="my-3">
                    <div class="loading">Loading</div>
                    <div class="error-message"></div>
                    <div class="sent-message">Your message has been sent. Thank you!</div>
                </div>
                <button @click="register" type="submit" id="login-btn">가입하기</button>
            </div>
        </div>
      </div>
    </section>
  </main>
</template>

<script>
// import http from '@/common/axios'
import BreadcrumbSection from "@/components/BreadcrumbSection.vue";
export default {
    components: {
        BreadcrumbSection
    },
    data() {
        return {
            title: "",
            content: ""
        }
    }
};
</script>

<style>
.wrap-container {
  width: 460px;
  margin: 0 auto;
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

</style>
