<template>
  <main id="main">
    <breadcrumb-section title="로그인" description="등록하신 이메일, 패스워드로 로그인해주세요."/>

    <!-- ======= Contact Section ======= -->
    <section id="contact" class="contact">
      <div class="container" data-aos="fade-up">
          <div class="wrap-container">
            <form role="form" class="php-email-form">
              <div class="text">
                  <input type="text" name="name" class="form-control" id="name" placeholder="Email" required />
              </div>
              <div class="text">
                <input type="password" class="form-control" name="subject" id="subject" placeholder="Password" required />
              </div>
              <div class="my-3">
                <div class="loading">Loading</div>
                <div class="error-message"></div>
                <div class="sent-message">Your message has been sent. Thank you!</div>
              </div>
              <div id="login-btn">
                <button type="submit" style="width: 100%">로그인</button>
              </div>

            </form>
          <div id="forgot-pwd"> 비밀번호를 잊었나요?
            <router-link to="/find">
              비밀번호 찾기
            </router-link>
          </div>
          <hr />

          <div class="bottom-container">
            <div class="text-center"> 다른 계정으로 로그인 하기</div>
            <div class="img-container"> 
              <a :href="socialLogin('google')">
                <img src="https://d1nuzc1w51n1es.cloudfront.net/d99d8628713bb69bd142.png" style="width: 75px; height: 75px">
                <div class="img-text">Google</div>
              </a>
              <a :href="socialLogin('kakao')" >
                <img src="https://d1nuzc1w51n1es.cloudfront.net/7edcff9c01ccc20d1ef6.png" style="width: 75px; height: 75px">
                <div class="img-text">Kakao</div>
              </a>
            </div>
            </div>
          </div>
          <div class="text-center" style="margin-top: 30px"> 계정이 없으신가요? 
            <router-link to="/register">
              회원 가입
            </router-link>
          </div>
        </div>
    </section>
    <!-- End Contact Section -->
  </main>
  <!-- End #main -->
</template>

<script>
import BreadcrumbSection from "@/components/BreadcrumbSection.vue";

export default {
  components: {
    BreadcrumbSection,
  },
  data() {
    return {
      token: ""
    }
  },
  methods: {
    socialLogin(socialType) {
      return `http://localhost:8080/oauth2/authorization/${socialType}?redirect_uri=http://localhost:5500/oauth/redirect`;
    }
  },
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
}

#forgot-pwd {
  text-align: right;
  padding: 10px 30px 10px;
  font-size: 14px;
}

.bottom-container {
  margin-top: 20px;
}

.img-container {
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  margin-top: 10px;
}

.img-container .img-text{
  text-align: center;
  color: #000000;
}
</style>
