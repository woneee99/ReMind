<template>
  <main id="main">
    <div v-if="!editingAbout">
      <breadcrumb-section title="내 프로필" description="내 프로필" />
    </div>
    <div v-else>
      <breadcrumb-section title="내 프로필 수정" description="내 프로필 수정" />
    </div>
    <section class="h-80 gradient-custom-2">
      <div class="container">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col col-lg-9 col-xl-7">
            <div class="card">
              <div class="rounded-top text-white d-flex flex-row" style="background-color: #273f55; height: 200px">
                <div class="ms-4 mt-5 d-flex flex-column" style="width: 150px">
                  <img :src="imageSrc" alt="Generic placeholder image" class="img-fluid img-thumbnail mt-4 mb-2" style="width: 150px; z-index: 1" />
                </div>
                <div class="ms-3" style="margin-top: 130px">
                  <p class="fs-5 m-0">{{ name }}</p>
                  <p>{{ email }}</p>
                </div>
              </div>
              <div class="p-4 text-black" style="background-color: #f8f9fa">
                <template v-if="!editingAbout">
                  <button type="button" class="btn btn-outline-dark float-end" data-mdb-ripple-color="dark" style="z-index: 1" @click="editingAbout = true">
                    Edit profile
                  </button>
                </template>
                <template v-else>
                  <button type="button" class="btn btn-outline-dark float-end" data-mdb-ripple-color="dark" style="z-index: 1" @click="saveAbout">Save</button>
                </template>
              </div>
              <div class="card-body p-4 text-black">
                <!-- <div class="mb-5">
                  <p class="lead fw-normal mb-1">About</p>
                  <div class="p-4" style="background-color: #f8f9fa">
                    <div v-if="!editingAbout">
                      <p class="font-italic mb-1">{{ aboutText1 }}</p>
                    </div>
                    <div v-else>
                      <textarea v-model="aboutText1" class="form-control" rows="5" placeholder="자기소개를 입력하세요"></textarea>
                    </div>
                  </div>
                </div> -->
                <div class="d-flex justify-content-between align-items-center mb-4">
                  <p class="lead fw-normal mb-0">Recent Blogs</p>
                  <p class="mb-0"><a href="#!" class="text-muted">Show all</a></p>
                </div>
                <div class="row g-2">
                  <div class="col mb-2" v-for="(blog, index) in recentBlogs.slice(0, 4)" :key="index">
                    <img :src="`${blog.thumbNail}`" class="w-100 rounded-3" @click="moveBlog()" />
                  </div>
                </div>
              </div>
            </div>
            <button type="button" @click="deleteUser()" class="btn btn-sm text-secondary">탈퇴하기</button>
          </div>
        </div>
      </div>
    </section>
  </main>
</template>

<script>
import BreadcrumbSection from "@/components/BreadcrumbSection.vue";
import http from "@/common/axios";

export default {
  components: {
    BreadcrumbSection,
  },
  data() {
    return {
      imageSrc: null,
      name: "",
      email: "",
      photoCount: "",
      recentBlogs: [],
      editingAbout: false,
    };
  },
  methods: {
    saveAbout() {
      this.editingAbout = false;
    },
    movePage() {},
    async getUser() {
      console.log(this.token);
      await http
        .get("/api/v1/users", {
          headers: {
            Authorization: "Bearer " + this.token,
          },
        })
        .then((response) => {
          let { data } = response;
          this.imageSrc = "https://play-lh.googleusercontent.com/38AGKCqmbjZ9OuWx4YjssAz3Y0DTWbiM5HB0ove1pNBq_o9mtWfGszjZNxZdwt_vgHo=s200";
          if (data.profileImageUrl) {
            this.imageSrc = data.profileImageUrl;
          }
          this.name = data.userName;
          this.email = data.userEmail;
          console.log(data);
        })
        .catch((error) => {
          console.error(error);
        });
      this.getMyBlog();
    },
    async getMyBlog() {
      console.log(this.token);
      await http
        .get("/api/v1/blog/myList", {
          headers: {
            Authorization: "Bearer " + this.token,
          },
        })
        .then((response) => {
          let { data } = response;
          this.recentBlogs = data;
          console.log(data);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    async deleteUser() {
      await http
        .delete("/api/v1/users/myInfo", {
          headers: {
            Authorization: "Bearer " + this.token,
          },
        })
        .then((response) => {
          let { data } = response;
          console.log(data);
          if (data == 1) {
            localStorage.clear();
            this.$emit("login-success", false);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
  created() {
    this.token = localStorage.getItem("token");
    this.getUser();
  },
  mounted() {},
};
</script>

<style></style>
