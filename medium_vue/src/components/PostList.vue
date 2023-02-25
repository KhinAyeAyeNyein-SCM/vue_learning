<template>
  <div class="body pt-10">
    <div class="container d-flex">
      <div class="col-md-12 d-flex">
        <section class="col-md-8 pt-3 px-4 mb-4 border-right">
          <div
            class="post d-flex mb-5"
            v-for="post in posts"
            :key="post.postId"
          >
            <div class="content col-md-8 pe-3 lh-sm">
              <div class="profile d-flex justify-content-start">
                <a rel="noopener follow" href="#">
                  <div class="">
                    <img
                      alt="Scott Belsky"
                      class="rounded-circle"
                      :src="image + post.user.profile"
                      width="20"
                      height="20"
                      loading="lazy"
                    />
                  </div>
                </a>
                <div class="ps-1 ms-1">
                  <router-link
                    :to="`/user/${post.user.id}`"
                    class="d-flex"
                    rel="noopener follow"
                  >
                    <p class="fs-6">{{ post.user.name }}</p>
                    <span class="mx-2">·</span>
                    <span class="text-secondary">Nov 15</span>
                  </router-link>
                </div>
              </div>
              <router-link :to="`/post/${post.postId}`" rel="noopener follow">
                <h4 class="fw-bold lh-sm font-sh">
                  {{ post.title }}
                </h4>
                <div class="mt-2">
                  <p class="lh-sm fs-6 me-2">
                    {{ post.description }}
                  </p>
                </div>
              </router-link>
              <div class="d-flex justify-content-between">
                <div class="d-flex justify-content-start text-secondary fs-15">
                  <button
                    class="btn btn-gray fs-15 br-1 px-1 py-1"
                    v-if="post.categoryList.length > 0"
                  >
                    <div>
                      {{ post.categoryList[0].categoryName }}
                    </div>
                  </button>
                  <span class="fs-6 ms-2 my-auto">10 min read</span>
                  <span class="mx-2 my-auto">.</span>
                  <span class="fs-6 my-auto">Selected for you</span>
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
                        src="../assets/image/image1.jpg"
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
                        src="../assets/image/image1.jpg"
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
                        src="../assets/image/image1.jpg"
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
  name: "PostList",
  data() {
    return {
      posts: [],
      categories: [],
      image: "http://localhost:8085/Medium/",
    };
  },
  created() {
    axios
      .get("http://localhost:8085/Medium/api/postList")
      .then(
        (response) => (
          (this.posts = response.data.postList),
          (this.categories = response.data.categories)
        )
      );
  },
};
</script>

<style>
@import "@/assets/css/style.css";
</style>
