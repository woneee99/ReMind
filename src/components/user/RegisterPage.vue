<template>
  <main id="main">
    <breadcrumb-section title="회원가입" description="임시 회원가입창" />
    <section id="contact" class="contact">
      <div class="container" data-aos="fade-up">
          <div class="wrap-container">
            <form action="forms/contact.php" method="post" role="form" class="php-email-form">
                <div class="text">
                    이메일 주소 (*)
                    <input type="text" name="name" class="form-control" id="name" placeholder="Email" required />
                </div>
                <div class="text">
                    비밀번호 (*)
                    <input type="password" class="form-control" name="subject" id="subject" placeholder="Password" required />
                </div>
                <div class="text">
                    비밀번호 재확인 (*)
                    <input type="password" class="form-control" name="subject" id="subject" placeholder="Password" required />
                </div>
                <div class="text">
                    이름 (*)
                    <input type="text" class="form-control" name="subject" id="subject" placeholder="name" required />
                </div>
                <div class="text">
                    프로필 사진 첨부
                    <input type="file" class="img-container" accept="image/*"/>
                </div>
                <div class="my-3">
                    <div class="loading">Loading</div>
                    <div class="error-message"></div>
                    <div class="sent-message">Your message has been sent. Thank you!</div>
                </div>
                <div id="login-btn">
                    <button type="submit" style="width: 100%">가입하기</button>
                </div>
            </form>
        </div>
      </div>
    </section>
  </main>
</template>

<script>
import BreadcrumbSection from "@/components/BreadcrumbSection.vue";

export default {
  components: {
    BreadcrumbSection,
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

.img-container {
  
}

</style>
