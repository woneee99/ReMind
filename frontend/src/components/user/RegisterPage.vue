<template>
  <main id="main">
    <breadcrumb-section title="회원가입" description="이메일 주소, 비밀번호, 이름은 필수 입력값입니다." />
    <section id="contact" class="contact">
      <div class="container" data-aos="fade-up">
          <div class="wrap-container">
            <div class="php-email-form">
                <div class="text">
                    이메일 주소 *
                    <input type="text" v-model="email" class="form-control" id="name" placeholder="Email" required />
                    <p v-show="valid.email" class="error"> 이메일 형식이 올바르지 않습니다. </p>
                </div>
                <div class="text">
                    비밀번호 *
                    <input type="password" v-model="password" class="form-control" placeholder="Password" required />
                </div>
                <div class="text">
                    비밀번호 재확인 *
                    <input type="password" v-model="password1" class="form-control" placeholder="Password" required />
                    <p v-show="valid.password" class="error"> 비밀번호가 일치하지 않습니다. </p>
                </div>
                <div class="text">
                    이름 *
                    <input type="text" v-model="name" class="form-control" placeholder="name" required />
                </div>
                <div class="text">
                    프로필 사진 첨부
                    <input @change="selectImg" type="file" id="inputImg" class="img-container" accept="image/*"/>
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
import BreadcrumbSection from "@/components/BreadcrumbSection.vue";
import http from '@/common/axios'

export default {
  data() {
    return {
      valid:{
        password: false,
        email: false
      },
      email: "",
      password: "",
      password1: "",
      name: "",
      img: ''
    }

  },
  components: {
    BreadcrumbSection,
  },
  methods: {
    selectImg(fileEvent) {
      this.img = fileEvent.target.files[0];
    },
    async register() {
      if(this.email == "" || this.password == "" || this.name == "") {
        alert("필수 입력 값을 작성해주세요");
        return;
      }
      
      let formData = new FormData();
      formData.append("userEmail", this.email);
      formData.append("userPassword", this.password);
      formData.append("userName", this.name);
      
      console.log(typeof this.img)
      if(this.img != "") formData.append("profilePostImage", this.img);
      
      let options = {
        headers: { 'Content-Type': 'multipart/form-data'}
      }
      let response = await http.post("/api/v1/users/register", formData, options);
      let { data } = response;
      if(data == 1) {
        alert("회원가입에 성공했습니다.")
        this.$router.push("/login")
      }
    }
  },
  watch: {
    'password1': function() {
      if(this.password != "" && this.password != this.password1) {
        this.valid.password = true;
      }else {
        this.valid.password = false;
      }
    },
    'email': function() {
      const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;
      if(!validateEmail.test(this.email)) {
        this.valid.email = true;
      }else {
        this.valid.email = false;
      }
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
