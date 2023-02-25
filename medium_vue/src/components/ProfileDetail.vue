<template>
  <div class="body pt-5">
    <div class="container d-flex">
      <div class="col-md-12 d-flex">
        <section class="col-md-8 pt-5 px-4 mb-4 border-right">
          <div class="profile d-flex mb-4 justify-content-between">
            <div class="d-flex">
              <a class="" href="" rel="noopener follow">
                <img
                  alt="Adrian H. Raudaschl"
                  class="rounded-circle"
                  :src="image + user.profile"
                  width="48"
                  height="48"
                  loading="lazy"
                />
              </a>
              <div class="ps-1 ms-1 my-auto">
                <div
                  class="my-auto"
                  aria-hidden="false"
                  aria-describedby="49"
                  aria-labelledby="49"
                >
                  <a class="d-flex" rel="noopener follow" href="#">
                    <p class="fs-6 fw-bold mb-0">{{ user.name }}</p>
                  </a>
                </div>
              </div>
            </div>
            <div class="btn me-5">
              <button class="btn btn-secondary br-2 fs-10 py-1">
                Edit Profile
              </button>
            </div>
          </div>
          <div class="about mx-3 pe-5">
            <h5 class="info fw-bold mb-4 pb-2 border-bottom">Information</h5>
            <div class="mail mb-4">
              <h5 class="fs-20">Email</h5>
              <p class="" type="">{{ user.email }}</p>
            </div>
            <div class="post-count mb-4">
              <h5 class="fs-20">Post count</h5>
              <label class="bg-secondary text-white px-2 rounded-circle">
                {{ user.postCount }}
              </label>
            </div>
            <div class="bio mb-5">
              <h5 class="fs-20">Bio</h5>
              <p class="pe-5" type="">
                {{ user.bio }}
              </p>
            </div>
            <h4 class="border-bottom pb-2 mb-3 me-5">My Posts</h4>
            <div v-if="user.postCount > 0">
              <div
                class="post d-flex mb-5"
                v-for="post in postList"
                :key="post.postId"
              >
                <div class="content col-md-8 pe-3 lh-sm">
                  <div class="profile d-flex justify-content-start">
                    <a rel="noopener follow" href="#">
                      <div class="">
                        <img
                          alt="Scott Belsky"
                          class="rounded-circle"
                          :src="image + post.image"
                          width="20"
                          height="20"
                          loading="lazy"
                        />
                      </div>
                    </a>
                    <div class="ps-1 ms-1">
                      <div
                        class="y"
                        aria-hidden="false"
                        aria-describedby="49"
                        aria-labelledby="49"
                      >
                        <a class="d-flex" rel="noopener follow" href="#">
                          <p class="fs-6">
                            Software Surplus in Level Up Coding
                          </p>
                          <span class="mx-2">·</span>
                          <span class="text-secondary">Nov 15</span>
                        </a>
                      </div>
                    </div>
                  </div>
                  <a class="" href="post-detail.html" rel="noopener follow">
                    <h4 class="fw-bold lh-sm font-sh">
                      {{ post.title }}
                    </h4>
                    <div class="mt-2">
                      <p class="lh-sm fs-6 me-2">
                        {{ post.description }}
                      </p>
                    </div>
                  </a>
                  <div class="d-flex justify-content-between">
                    <div
                      class="d-flex justify-content-start text-secondary fs-15"
                    >
                      <button class="btn btn-secondary fs-10 br-1 px-1 py-1">
                        {{ post.categoryList[0].categoryName }}
                      </button>
                    </div>
                    <div class="al">
                      <div class="gn y lm">
                        <div>
                          <div
                            class="co"
                            aria-hidden="false"
                            aria-describedby="70"
                            aria-labelledby="70"
                          >
                            <span
                              ><a
                                class="fs-5 text-secondary mx-2"
                                rel="noopener follow"
                                href=""
                              >
                                <i class="fas fa-minus-circle"></i> </a
                            ></span>
                            <span
                              ><a
                                class="fs-5 text-secondary mx-2"
                                rel="noopener follow"
                                href=""
                              >
                                <i class="fas fa-ellipsis-h"></i> </a
                            ></span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="image col-md-4 my-auto mx-0">
                  <a
                    class="w-100 d-inline"
                    href="post-detail.html"
                    rel="noopener follow"
                    ><img
                      alt="image1"
                      class="w-75 h-auto float-right"
                      :src="image + post.image"
                      width="200"
                      height="150"
                      loading="lazy"
                  /></a>
                </div>
              </div>
            </div>
          </div>
        </section>
        <aside class="col-md-4 ms-3 px-3">
          <div class="aside ms-4 position-sticky" style="top: 100px">
            <div class="posts w-100 pb-4 border-bottom border-secondary">
              <p class="title fs-5 fw-bold">2022 in Latest Posts</p>
              <div class="post">
                <div class="profile d-flex justify-content-start">
                  <a rel="noopener follow" href="#">
                    <div class="d-flex align-items-center">
                      <img
                        alt="Scott Belsky"
                        class="rounded-circle"
                        src="../assets/image/d-image4.jpg"
                        width="20"
                        height="20"
                        loading="lazy"
                      />
                      <p class="align-middle ms-2 fs-15 m-auto">Zulie Rane</p>
                    </div>
                  </a>
                </div>
                <div>
                  <p class="fw-bold fs-15 mt-1">2022 in Word : Permastressed</p>
                </div>
              </div>
              <div class="post">
                <div class="profile d-flex justify-content-start">
                  <a rel="noopener follow" href="#">
                    <div class="d-flex align-items-center">
                      <img
                        alt="Scott Belsky"
                        class="rounded-circle"
                        src="../assets/image/d-image1.jpg"
                        width="20"
                        height="20"
                        loading="lazy"
                      />
                      <p class="align-middle ms-2 fs-15 m-auto">Zulie Rane</p>
                    </div>
                  </a>
                </div>
                <div>
                  <p class="fw-bold fs-15 mt-1">2022 in Word : Permastressed</p>
                </div>
              </div>
              <div class="post">
                <div class="profile d-flex justify-content-start">
                  <a rel="noopener follow" href="#">
                    <div class="d-flex align-items-center">
                      <img
                        alt="Scott Belsky"
                        class="rounded-circle"
                        src="../assets/image/image3.jpg"
                        width="20"
                        height="20"
                        loading="lazy"
                      />
                      <p class="align-middle ms-2 fs-15 m-auto">Zulie Rane</p>
                    </div>
                  </a>
                </div>
                <div>
                  <p class="fw-bold fs-15 mt-1">2022 in Word : Permastressed</p>
                </div>
              </div>
            </div>
            <div class="cate mt-4 font">
              <button
                class="btn mb-2 fs-15 mx-1 br-2 fw-bold bg-ash"
                v-for="category in categories"
                :key="category.categoryId"
              >
                {{ category.categoryName }}
              </button>
            </div>
          </div>
        </aside>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "ProfileDetail",
  data() {
    return {
      user: {},
      categories: [],
      postList: [],
      image: "http://localhost:8085/Medium/",
    };
  },
  created() {
    axios
      .get(`http://localhost:8085/Medium/api/profile/${this.$route.params.id}`)
      .then(
        (response) => (
          (this.user = response.data.userForm),
          (this.categories = response.data.categories),
          (this.postList = response.data.postList)
        )
      );
  },
};
</script>
