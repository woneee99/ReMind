<template>
  <main id="main">
    <breadcrumb-section title="공지사항 & FAQ" description="공지사항 & 자주 묻는 질문" />
    <!-- ======= Frequently Asked Questions Section ======= -->
    <section id="faq" class="faq">
      <div class="container" data-aos="fade-up">
        <div class="section-header">
          <span>Enjoy Trip FAQ</span>
          <h2>Enjoy Trip FAQ</h2>
        </div>

        <div class="row justify-content-center" data-aos="fade-up" data-aos-delay="200">
          <div class="col-lg-10">
            <div class="accordion accordion-flush" id="faqlist">
              <div class="accordion-item">
                <h3 class="accordion-header">
                  <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq-content-1">
                    <i class="bi bi-question-circle question-icon"></i>
                    호텔에 대한 리뷰도 쓸 수 있나요?
                  </button>
                </h3>
                <div id="faq-content-1" class="accordion-collapse collapse" data-bs-parent="#faqlist">
                  <div class="accordion-body">
                    호텔 관련 리뷰는 어스토리에서 작성하실 수 없습니다. 해당 여행사 사이트나 호텔/항공사 사이트에 접속하여 작성하시기 바랍니다. 어스토리
                    안에서는 여행지의 장소 - 관광명소, 식당, 쇼핑 - 에만 리뷰를 작성하실 수 있습니다.
                  </div>
                </div>
              </div>
              <!-- # Faq item-->

              <div class="accordion-item">
                <h3 class="accordion-header">
                  <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq-content-2">
                    <i class="bi bi-question-circle question-icon"></i>
                    일정 만들기란 무엇인가요?
                  </button>
                </h3>
                <div id="faq-content-2" class="accordion-collapse collapse" data-bs-parent="#faqlist">
                  <div class="accordion-body">
                    '일정 만들기'란 어스토리에서 나만의 여행 일정을 계획하는 기능으로, 내가 가고 싶은(클립한) 명소들의 위치를 지도에서 보며 동선을 계획하고
                    경로보기를 통해 가는 방법을 확인할 수 있습니다. 드래그 & 드롭하여 방문 순서를 정렬하면 자동으로 이동 동선과 경로가 보여집니다. 방문 일자별로
                    클립 명소들을 정렬하여 나만의 여행 일정을 손쉽게 만들 수 있습니다.
                  </div>
                </div>
              </div>
              <!-- # Faq item-->

              <div class="accordion-item">
                <h3 class="accordion-header">
                  <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq-content-3">
                    <i class="bi bi-question-circle question-icon"></i>
                    완성된 일정을 수정할 수 있나요?
                  </button>
                </h3>
                <div id="faq-content-3" class="accordion-collapse collapse" data-bs-parent="#faqlist">
                  <div class="accordion-body">
                    내 일정은 언제든지 다시 수정할 수 있습니다. 마이페이지의 '여행일정'에서 일정 위에 마우스를 대면 '상세일정보기' 버튼이 보여집니다.
                    '상세일정'으로 들어가면 일정 제목과 내용하단에 '수정하기'버튼이 있습니다. '수정하기'을 클릭하여 일정을 수정해 보세요.
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
                    하나의 장소(명소, 음식점, 호텔 등)를 여러 번 방문하실 계획인가요? '일정만들기' 페이지에서 그 장소를 드래그 하여 필요한 횟수만큼 일정에
                    반복적으로 넣을 수 있습니다.
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
