<template>
  <main id="main">
    <!-- ======= Carousel Section ======= -->
    <div class="container">
      <div id="carouselExampleIndicators" class="carousel slide mt-3" data-bs-ride="carousel">
        <div class="carousel-indicators">
          <button
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide-to="0"
            class="active"
            aria-current="true"
            aria-label="Slide 1"
          ></button>
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="https://d2ur7st6jjikze.cloudfront.net/cms/2445_original_1681717950.jpg?1681717950" class="d-block w-100" alt="..." />
          </div>
          <div class="carousel-item">
            <img src="https://d2ur7st6jjikze.cloudfront.net/cms/2317_original_1682651078.jpg?1682651078" class="d-block w-100" alt="..." />
          </div>
          <div class="carousel-item">
            <img src="https://d2ur7st6jjikze.cloudfront.net/cms/1693_original_1682651081.jpg?1682651081" class="d-block w-100" alt="..." />
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </div>
    <!-- End Carousel Section -->

    <!-- ======= Recommendation Section ======= -->
    <section id="service" class="services pt-0">
      <div class="container" data-aos="fade-up">
        <div class="section-header mt-3">
          <span>추천 여행지</span>
          <h2>추천 여행지</h2>
        </div>

        <div class="row gy-4">
          <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="100">
            <div class="card">
              <div class="card-img">
                <img src="https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=903cc46f-8831-4569-ac7e-06fe418b6b11" alt="" class="img-fluid" />
              </div>
              <h3>
                <a
                  href="https://korean.visitkorea.or.kr/detail/ms_detail.do?cotid=0f3410d7-e89f-45fe-acd2-05231552d59c&big_category=A02&mid_category=A0202&big_area=39"
                  target="_blank"
                  class="stretched-link"
                  >휴애리자연생활공원</a
                >
              </h3>
              <p>
                <strong>제주 서귀포시</strong> <br />
                한라산 자락에 있는 자연생활 체험 공원으로, 상반기에는 매화축제를 시작으로 4월 ~ 7월까지 수국축제가 열린다.
              </p>
            </div>
          </div>
          <!-- End Card Item -->

          <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="200">
            <div class="card">
              <div class="card-img">
                <img src="https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=b26f9ada-9689-46fc-8a4c-e6839f7688ab" alt="" class="img-fluid" />
              </div>
              <h3>
                <a
                  href="https://korean.visitkorea.or.kr/detail/ms_detail.do?cotid=2a242f43-4a4d-4836-a17a-abcb34d0e9bc&big_category=A02&mid_category=A0202&big_area=1"
                  target="_blank"
                  class="stretched-link"
                  >여의도한강공원</a
                >
              </h3>
              <p>
                <strong>서울 영등포구</strong> <br />
                봄에는 벚꽃축제가, 가을에는 세계불꽃축제 및 각종 공연과 행사가 이어져 모두가 즐겨찾는 여의도 명소이다.
              </p>
            </div>
          </div>
          <!-- End Card Item -->

          <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="300">
            <div class="card">
              <div class="card-img">
                <img src="https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=bcd92f62-cd14-46e1-b2c4-8d40c465f489" alt="" class="img-fluid" />
              </div>
              <h3>
                <a
                  href="https://korean.visitkorea.or.kr/detail/ms_detail.do?cotid=413b7a3a-82c1-47ac-98b2-c57b4d59bb7c&big_category=A02&mid_category=A0202&big_area=4"
                  target="_blank"
                  class="stretched-link"
                  >옥연지 송해공원</a
                >
              </h3>
              <p>
                <strong>대구 달성군</strong> <br />
                달성군 명예군민인 방송인 송해 선생의 이름을 따 명칭한 곳으로, 공원과 함께 '송해공원 둘레길' 생태탐방로가 조성되어있다.
              </p>
            </div>
          </div>
          <!-- End Card Item -->

          <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="400">
            <div class="card">
              <div class="card-img">
                <img src="https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=28858c4b-4c0f-4eff-9c49-7ed286afdea7" alt="" class="img-fluid" />
              </div>
              <h3>
                <a
                  href="https://korean.visitkorea.or.kr/detail/ms_detail.do?cotid=0313c724-da5f-4095-9fb2-3b9aaf7fb8ce&big_category=A04&mid_category=A0401&big_area=36"
                  target="_blank"
                  class="stretched-link"
                  >하동 화개장터</a
                >
              </h3>
              <p>
                <strong>경남 하동군</strong> <br />
                김동리의 소설 역마의 배경이 된 곳으로 유명하며, 전통적인 재래식시장으로 5일장이었으나 현재는 상시시장으로 개장되어있다.
              </p>
            </div>
          </div>
          <!-- End Card Item -->

          <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="500">
            <div class="card">
              <div class="card-img">
                <img src="https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=833795f9-2750-4244-84dc-d3bc2a020b2f" alt="" class="img-fluid" />
              </div>
              <h3>
                <a
                  href="https://korean.visitkorea.or.kr/detail/ms_detail.do?cotid=568cf72c-88a8-4b25-a0f9-a823bcd476ee&big_category=A02&mid_category=A0202&big_area=6"
                  target="_blank"
                  class="stretched-link"
                  >신호공원</a
                >
              </h3>
              <p>
                <strong>부산 강서구</strong> <br />
                갯벌 체험으로 유명한 근린공원으로 바다갈맷길 주변으로 울창한 소나무 숲을 이루고 있어 산책하기 좋은 공원이다.
              </p>
            </div>
          </div>
          <!-- End Card Item -->

          <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="600">
            <div class="card">
              <div class="card-img">
                <img src="https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=b84c0f26-f9ce-4ee1-99f4-7da2e8b59031" alt="" class="img-fluid" />
              </div>
              <h3>
                <a
                  href="https://korean.visitkorea.or.kr/detail/ms_detail.do?cotid=5c8f5f37-1c5f-47e3-8ae9-64acef4a668a&big_category=A01&mid_category=A0101&big_area=32"
                  target="_blank"
                  class="stretched-link"
                  >청호해변</a
                >
              </h3>
              <p>
                <strong>강원 속초시</strong> <br />
                속초시의 유일무이한 해수욕장이기에 집중적인 관리로 다른 해수욕장에 비해 깔끔하고 질서정연한 느낌의 해수욕장이다.
              </p>
            </div>
          </div>
          <!-- End Card Item -->
        </div>
      </div>
    </section>
    <!-- End Services Section -->
  </main>
</template>

<script>
export default {};
</script>

<style></style>
