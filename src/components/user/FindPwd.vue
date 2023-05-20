<template>
  <main id="main">
    <breadcrumb-section title="비밀번호 찾기" description="가입하신 이메일로 임시 비밀번호를 전송해드립니다."/>
    <section id="contact" class="contact">
      <div class="container" data-aos="fade-up">
          <div class="wrap-container">
            <div class="php-email-form">
              <div class="text">
                <p> 가입하신 Email </p>
                <input type="text" name="email" v-model="email" class="form-control" placeholder="Email" required />
              </div>
              <div class="my-3">
                <div class="loading">Loading</div>
                <div class="error-message"></div>
                <div class="sent-message">Your message has been sent. Thank you!</div>
              </div>
              <div id="submit-btn">
                <button type="submit" style="width: 100%" @click="emailPost">임시 비밀번호 전송</button>
              </div>
            </div>
          </div>
        </div>
    </section>
  </main>
</template>

<script>
import BreadcrumbSection from "@/components/BreadcrumbSection.vue";
import http from '@/common/axios'
export default {
  components: {
    BreadcrumbSection,
    http
  },
  data() {
    return {
      email: ""
    }
  },
  methods: {
    async emailPost() {
      console.log(this.email)
      
      await http.post('/api/v1/users/email', { 
        email: this.email 
      })
      .then(response => {
        let { data } = response;
        if(data == -1) {
          alert( " 등록하신 이메일이 없습니다. ");
        }
        else {
          alert("메일 전송 완료!")
          this.$router.push("/login");
        }
      })
      .catch(error => {
        console.log("errr: " + error);
      });
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

#submit-btn {
  border-radius: 6px 6px;
  padding: 10px 30px 10px;
}

</style>
