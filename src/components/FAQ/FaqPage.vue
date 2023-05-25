<template>
  <main id="main">
    <breadcrumb-section title="공지사항 & FAQ" description="공지사항 & 자주 묻는 질문" />
    <!-- ======= Frequently Asked Questions Section ======= -->
    <section id="faq" class="faq">
      <div class="container" data-aos="fade-up">
        <div class="section-header">
          <span>자주 묻는 질문</span>
          <h2>자주 묻는 질문</h2>
        </div>

        <div class="row justify-content-center" data-aos="fade-up" data-aos-delay="200">
          <div class="col-lg-10">
            <div class="accordion accordion-flush" id="faqlist">
              <div class="accordion-item">
                <h3 class="accordion-header">
                  <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq-content-1">
                    <i class="bi bi-question-circle question-icon"></i>
                    로그인은 어떻게 할 수 있나요?
                  </button>
                </h3>
                <div id="faq-content-1" class="accordion-collapse collapse" data-bs-parent="#faqlist">
                  <div class="accordion-body">
                    로그인은 회원가입을 한 계정이 있는 경우 가능합니다. 왼쪽 상단에 버튼을 클릭하여 들어가 회원가입을 진행해보세요.<br />
                    구글 로그인, 카카오 로그인을 사용하면 보다 쉽고 빠르게 저희 서비스를 이용하실 수 있습니다.
                  </div>
                </div>
              </div>
              <!-- # Faq item-->

              <div class="accordion-item">
                <h3 class="accordion-header">
                  <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq-content-2">
                    <i class="bi bi-question-circle question-icon"></i>
                    여행 계획이란 무엇인가요?
                  </button>
                </h3>
                <div id="faq-content-2" class="accordion-collapse collapse" data-bs-parent="#faqlist">
                  <div class="accordion-body">
                    '여행 계획'이란 SSAFY 9기 3반 13조만의 여행 계획 사이트의 서비스로 원하는 날짜의 원하는 출발 시간을 입력하고, 원하는 장소를 넣어서 여행
                    계획을 세울 수 있습니다. <br />
                    이렇게 세운 여행계획은 저희가 각 여행지마다 도착 예상 시간과 출발 시간을 계산해서 계획을 보다 자세히 세울 수 있도록 도와드립니다.
                  </div>
                </div>
              </div>
              <!-- # Faq item-->

              <div class="accordion-item">
                <h3 class="accordion-header">
                  <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq-content-3">
                    <i class="bi bi-question-circle question-icon"></i>
                    완성한 여행 계획은 어떻게 볼 수 있나요?
                  </button>
                </h3>
                <div id="faq-content-3" class="accordion-collapse collapse" data-bs-parent="#faqlist">
                  <div class="accordion-body">
                    생성한 여행 계획이 있으시다면 왼쪽 상단의 본인 이름에서 '내 여행'을 클릭하거나 '여행계획'을 클릭하면 생성한 여행계획을 볼 수 있습니다.<br />
                    생성한 여행 계획은 예쁜 카드 형식으로 여행에 꼭 필요한 정보만 제공해드리고 있습니다. 만약 생성한 여행 계획이 없으시다면 여행 계획을 편하고
                    멋있게 만들어보세요!
                  </div>
                </div>
              </div>
              <!-- # Faq item-->

              <div class="accordion-item">
                <h3 class="accordion-header">
                  <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq-content-4">
                    <i class="bi bi-question-circle question-icon"></i>
                    한 일정에 같은 장소를 여러 번 넣을 수 있나요?
                  </button>
                </h3>
                <div id="faq-content-4" class="accordion-collapse collapse" data-bs-parent="#faqlist">
                  <div class="accordion-body">
                    <i class="bi bi-question-circle question-icon"></i>
                    여행 날짜 하루마다 넣을 수 있는 장소의 수는 7개로 제한됩니다. 따라서, 같은 장소를 중복해서 넣는 건 아쉽지 않으신가요?<br />
                    현재는 당일에 한 번 넣었던 장소는 넣지 못하게 되어있습니다.
                  </div>
                </div>
              </div>
              <!-- # Faq item-->

              <div class="accordion-item">
                <h3 class="accordion-header">
                  <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq-content-5">
                    <i class="bi bi-question-circle question-icon"></i>
                    비밀번호를 잊어버렸습니다. 어떻게 찾을 수 있나요?
                  </button>
                </h3>
                <div id="faq-content-5" class="accordion-collapse collapse" data-bs-parent="#faqlist">
                  <div class="accordion-body">
                    로그인 화면에서 '비밀번호 찾기'를 클릭하고 가입시 기입한 이메일 주소를 적어주세요. 해당 이메일로 임시 비밀번호를 발송해 드립니다.
                  </div>
                </div>
              </div>
              <div class="accordion-item">
                <h3 class="accordion-header">
                  <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq-content-6">
                    <i class="bi bi-question-circle question-icon"></i>
                    Community란 무엇인가요?
                  </button>
                </h3>
                <div id="faq-content-6" class="accordion-collapse collapse" data-bs-parent="#faqlist">
                  <div class="accordion-body">
                    계획하신 여행을 즐기고 나서 누군가에게 나의 여행에 대해서 이야기하고 싶은 마음이 든 적 있지 않나요? 그러한 마음을 예쁜 사진과 함께 글로
                    표현해주세요.<br />
                    나의 추억을 소중히 간직할 수 있는 기회가 될 수 있습니다.
                  </div>
                </div>
              </div>
              <!-- # Faq item-->
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- End Frequently Asked Questions Section -->
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
<style></style>
