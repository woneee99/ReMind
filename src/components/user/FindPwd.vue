<template>
  <main id="main">
    <breadcrumb-section title="비밀번호 찾기" description="가입하신 이메일로 임시 비밀번호를 전송해드립니다."/>

    <section id="contact" class="contact">
      <div class="container" data-aos="fade-up">
          <div class="wrap-container">
            <form action="forms/contact.php" method="post" role="form" class="php-email-form">
              <div class="text">
                  <input type="text" name="name" class="form-control" id="name" placeholder="Email" required />
              </div>
              <div class="my-3">
                <div class="loading">Loading</div>
                <div class="error-message"></div>
                <div class="sent-message">Your message has been sent. Thank you!</div>
              </div>
              <div id="submit-btn">
                <button type="submit" style="width: 100%">임시 비밀번호 전송</button>
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

#submit-btn {
  border-radius: 6px 6px;
  padding: 10px 30px 10px;
}

</style>
