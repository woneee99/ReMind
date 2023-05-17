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
              <h3><a href="service-details.html" class="stretched-link">휴애리자연생활공원</a></h3>
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
              <h3><a href="service-details.html" class="stretched-link">여의도한강공원</a></h3>
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
              <h3><a href="service-details.html" class="stretched-link">옥연지 송해공원</a></h3>
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
              <h3><a href="service-details.html" class="stretched-link">하동 화개장터</a></h3>
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
              <h3><a href="service-details.html" class="stretched-link">신호공원</a></h3>
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
              <h3><a href="service-details.html" class="stretched-link">청호해변</a></h3>
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

    <!-- ======= Call To Action Section ======= -->
    <section id="call-to-action" class="call-to-action">
      <div class="container" data-aos="zoom-out">
        <div class="row justify-content-center">
          <div class="col-lg-8 text-center">
            <h3>Call To Action</h3>
            <p>
              Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
              proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
            <a class="cta-btn" href="#">Call To Action</a>
          </div>
        </div>
      </div>
    </section>
    <!-- End Call To Action Section -->

    <!-- ======= Features Section ======= -->
    <section id="features" class="features">
      <div class="container">
        <div class="row gy-4 align-items-center features-item" data-aos="fade-up">
          <div class="col-md-5">
            <img src="assets/img/features-1.jpg" class="img-fluid" alt="" />
          </div>
          <div class="col-md-7">
            <h3>Voluptatem dignissimos provident quasi corporis voluptates sit assumenda.</h3>
            <p class="fst-italic">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            </p>
            <ul>
              <li><i class="bi bi-check"></i> Ullamco laboris nisi ut aliquip ex ea commodo consequat.</li>
              <li><i class="bi bi-check"></i> Duis aute irure dolor in reprehenderit in voluptate velit.</li>
              <li><i class="bi bi-check"></i> Ullam est qui quos consequatur eos accusamus.</li>
            </ul>
          </div>
        </div>
        <!-- Features Item -->

        <div class="row gy-4 align-items-center features-item" data-aos="fade-up">
          <div class="col-md-5 order-1 order-md-2">
            <img src="assets/img/features-2.jpg" class="img-fluid" alt="" />
          </div>
          <div class="col-md-7 order-2 order-md-1">
            <h3>Corporis temporibus maiores provident</h3>
            <p class="fst-italic">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            </p>
            <p>
              Ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
              nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum
            </p>
          </div>
        </div>
        <!-- Features Item -->

        <div class="row gy-4 align-items-center features-item" data-aos="fade-up">
          <div class="col-md-5">
            <img src="assets/img/features-3.jpg" class="img-fluid" alt="" />
          </div>
          <div class="col-md-7">
            <h3>Sunt consequatur ad ut est nulla consectetur reiciendis animi voluptas</h3>
            <p>
              Cupiditate placeat cupiditate placeat est ipsam culpa. Delectus quia minima quod. Sunt saepe odit aut quia voluptatem hic voluptas dolor
              doloremque.
            </p>
            <ul>
              <li><i class="bi bi-check"></i> Ullamco laboris nisi ut aliquip ex ea commodo consequat.</li>
              <li><i class="bi bi-check"></i> Duis aute irure dolor in reprehenderit in voluptate velit.</li>
              <li><i class="bi bi-check"></i> Facilis ut et voluptatem aperiam. Autem soluta ad fugiat.</li>
            </ul>
          </div>
        </div>
        <!-- Features Item -->

        <div class="row gy-4 align-items-center features-item" data-aos="fade-up">
          <div class="col-md-5 order-1 order-md-2">
            <img src="assets/img/features-4.jpg" class="img-fluid" alt="" />
          </div>
          <div class="col-md-7 order-2 order-md-1">
            <h3>Quas et necessitatibus eaque impedit ipsum animi consequatur incidunt in</h3>
            <p class="fst-italic">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            </p>
            <p>
              Ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
              nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum
            </p>
          </div>
        </div>
        <!-- Features Item -->
      </div>
    </section>
    <!-- End Features Section -->

    <!-- ======= Pricing Section ======= -->
    <section id="pricing" class="pricing pt-0">
      <div class="container" data-aos="fade-up">
        <div class="section-header">
          <span>Pricing</span>
          <h2>Pricing</h2>
        </div>

        <div class="row gy-4">
          <div class="col-lg-4" data-aos="fade-up" data-aos-delay="100">
            <div class="pricing-item">
              <h3>Free Plan</h3>
              <h4><sup>$</sup>0<span> / month</span></h4>
              <ul>
                <li><i class="bi bi-check"></i> Quam adipiscing vitae proin</li>
                <li><i class="bi bi-check"></i> Nec feugiat nisl pretium</li>
                <li><i class="bi bi-check"></i> Nulla at volutpat diam uteera</li>
                <li class="na"><i class="bi bi-x"></i> <span>Pharetra massa massa ultricies</span></li>
                <li class="na"><i class="bi bi-x"></i> <span>Massa ultricies mi quis hendrerit</span></li>
              </ul>
              <a href="#" class="buy-btn">Buy Now</a>
            </div>
          </div>
          <!-- End Pricing Item -->

          <div class="col-lg-4" data-aos="fade-up" data-aos-delay="200">
            <div class="pricing-item featured">
              <h3>Business Plan</h3>
              <h4><sup>$</sup>29<span> / month</span></h4>
              <ul>
                <li><i class="bi bi-check"></i> Quam adipiscing vitae proin</li>
                <li><i class="bi bi-check"></i> Nec feugiat nisl pretium</li>
                <li><i class="bi bi-check"></i> Nulla at volutpat diam uteera</li>
                <li><i class="bi bi-check"></i> Pharetra massa massa ultricies</li>
                <li><i class="bi bi-check"></i> Massa ultricies mi quis hendrerit</li>
              </ul>
              <a href="#" class="buy-btn">Buy Now</a>
            </div>
          </div>
          <!-- End Pricing Item -->

          <div class="col-lg-4" data-aos="fade-up" data-aos-delay="300">
            <div class="pricing-item">
              <h3>Developer Plan</h3>
              <h4><sup>$</sup>49<span> / month</span></h4>
              <ul>
                <li><i class="bi bi-check"></i> Quam adipiscing vitae proin</li>
                <li><i class="bi bi-check"></i> Nec feugiat nisl pretium</li>
                <li><i class="bi bi-check"></i> Nulla at volutpat diam uteera</li>
                <li><i class="bi bi-check"></i> Pharetra massa massa ultricies</li>
                <li><i class="bi bi-check"></i> Massa ultricies mi quis hendrerit</li>
              </ul>
              <a href="#" class="buy-btn">Buy Now</a>
            </div>
          </div>
          <!-- End Pricing Item -->
        </div>
      </div>
    </section>
    <!-- End Pricing Section -->

    <!-- ======= Testimonials Section ======= -->
    <section id="testimonials" class="testimonials">
      <div class="container">
        <div class="slides-1 swiper" data-aos="fade-up">
          <div class="swiper-wrapper">
            <div class="swiper-slide">
              <div class="testimonial-item">
                <img src="assets/img/testimonials/testimonials-1.jpg" class="testimonial-img" alt="" />
                <h3>Saul Goodman</h3>
                <h4>Ceo &amp; Founder</h4>
                <div class="stars">
                  <i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i
                  ><i class="bi bi-star-fill"></i>
                </div>
                <p>
                  <i class="bi bi-quote quote-icon-left"></i>
                  Proin iaculis purus consequat sem cure digni ssim donec porttitora entum suscipit rhoncus. Accusantium quam, ultricies eget id, aliquam eget
                  nibh et. Maecen aliquam, risus at semper.
                  <i class="bi bi-quote quote-icon-right"></i>
                </p>
              </div>
            </div>
            <!-- End testimonial item -->

            <div class="swiper-slide">
              <div class="testimonial-item">
                <img src="assets/img/testimonials/testimonials-2.jpg" class="testimonial-img" alt="" />
                <h3>Sara Wilsson</h3>
                <h4>Designer</h4>
                <div class="stars">
                  <i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i
                  ><i class="bi bi-star-fill"></i>
                </div>
                <p>
                  <i class="bi bi-quote quote-icon-left"></i>
                  Export tempor illum tamen malis malis eram quae irure esse labore quem cillum quid cillum eram malis quorum velit fore eram velit sunt aliqua
                  noster fugiat irure amet legam anim culpa.
                  <i class="bi bi-quote quote-icon-right"></i>
                </p>
              </div>
            </div>
            <!-- End testimonial item -->

            <div class="swiper-slide">
              <div class="testimonial-item">
                <img src="assets/img/testimonials/testimonials-3.jpg" class="testimonial-img" alt="" />
                <h3>Jena Karlis</h3>
                <h4>Store Owner</h4>
                <div class="stars">
                  <i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i
                  ><i class="bi bi-star-fill"></i>
                </div>
                <p>
                  <i class="bi bi-quote quote-icon-left"></i>
                  Enim nisi quem export duis labore cillum quae magna enim sint quorum nulla quem veniam duis minim tempor labore quem eram duis noster aute
                  amet eram fore quis sint minim.
                  <i class="bi bi-quote quote-icon-right"></i>
                </p>
              </div>
            </div>
            <!-- End testimonial item -->

            <div class="swiper-slide">
              <div class="testimonial-item">
                <img src="assets/img/testimonials/testimonials-4.jpg" class="testimonial-img" alt="" />
                <h3>Matt Brandon</h3>
                <h4>Freelancer</h4>
                <div class="stars">
                  <i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i
                  ><i class="bi bi-star-fill"></i>
                </div>
                <p>
                  <i class="bi bi-quote quote-icon-left"></i>
                  Fugiat enim eram quae cillum dolore dolor amet nulla culpa multos export minim fugiat minim velit minim dolor enim duis veniam ipsum anim
                  magna sunt elit fore quem dolore labore illum veniam.
                  <i class="bi bi-quote quote-icon-right"></i>
                </p>
              </div>
            </div>
            <!-- End testimonial item -->

            <div class="swiper-slide">
              <div class="testimonial-item">
                <img src="assets/img/testimonials/testimonials-5.jpg" class="testimonial-img" alt="" />
                <h3>John Larson</h3>
                <h4>Entrepreneur</h4>
                <div class="stars">
                  <i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i
                  ><i class="bi bi-star-fill"></i>
                </div>
                <p>
                  <i class="bi bi-quote quote-icon-left"></i>
                  Quis quorum aliqua sint quem legam fore sunt eram irure aliqua veniam tempor noster veniam enim culpa labore duis sunt culpa nulla illum
                  cillum fugiat legam esse veniam culpa fore nisi cillum quid.
                  <i class="bi bi-quote quote-icon-right"></i>
                </p>
              </div>
            </div>
            <!-- End testimonial item -->
          </div>
          <div class="swiper-pagination"></div>
        </div>
      </div>
    </section>
    <!-- End Testimonials Section -->
  </main>
</template>

<script>
export default {};
</script>

<style></style>
