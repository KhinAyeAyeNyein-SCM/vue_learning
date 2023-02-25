<template>
  <div class="body pt-5">
    <div class="container d-flex mb-4">
      <div class="col-md-12 d-flex border-bottom">
        <section class="col-md-8 pt-5 px-4 mb-4 border-right">
          <div class="post d-flex mb-2">
            <div class="col-md-12 pe-3 lh-sm">
              <div class="profile d-flex justify-content-between">
                <div class="d-flex">
                  <router-link
                    :to="`/user/${post.user.id}`"
                    rel="noopener follow"
                  >
                    <img
                      alt="Scott Belsky"
                      class="rounded-circle"
                      :src="image + post.user.profile"
                      width="48"
                      height="48"
                      loading="lazy"
                    />
                  </router-link>
                  <div class="ps-2">
                    <div
                      class="bk"
                      aria-hidden="false"
                      aria-describedby="1"
                      aria-labelledby="1"
                    >
                      <router-link
                        :to="`/user/{{post.user.id}}`"
                        class="fs-6 fw-bold"
                        rel="profile1"
                        >{{ post.user.name }}</router-link
                      >
                    </div>
                    <div class="d-flex py-1 fs-15 text-secondary">
                      <p class="pe-1"><span>Dec 20</span></p>
                      <div class="gf bk" aria-hidden="true">
                        <span class="mx-1">·</span>
                      </div>
                      <div class="pw-reading-time bd b be z dw">
                        11 min read .
                      </div>
                    </div>
                  </div>
                </div>
                <div class="btn">
                  <button class="btn btn-danger br-2 fs-10 py-1">Delete</button>
                </div>
              </div>
              <div class="image pb-3 my-2">
                <a href="#" class="d-inline-block">
                  <img
                    alt="Cat"
                    class="w-100 h-auto"
                    loading="eager"
                    :src="image + post.image"
                  />
                </a>
              </div>
              <div class="content">
                <h4 class="fw-bold lh-sm font-sh">
                  {{ post.title }}
                </h4>
                <div class="mt-2">
                  <p class="lh-reg fs-6 me-2">
                    {{ post.description }}
                  </p>
                </div>
              </div>
            </div>
          </div>
        </section>
        <aside class="col-md-4 ms-3 px-3">
          <div class="aside ms-4 position-sticky font-sh" style="top: 80px">
            <div class="posts w-100 pb-1">
              <div class="profile">
                <div class="d-block align-items-center">
                  <img
                    alt="Scott Belsky"
                    class="rounded-circle"
                    :src="image + post.user.profile"
                    width="60"
                    height="60"
                    loading="lazy"
                  />
                </div>
                <h5 class="align-middle fs-5 py-2 fw-bold m-auto">
                  {{ post.user.name }}
                </h5>
              </div>
              <p class="fs-6 mt-1 text-secondary">100 Posts</p>
              <div>
                <p class="fs-6 fw-bold mt-1 text-secondary">
                  Helping writers get published and bolstering their flagging
                  spirits at http://publishedtodeath.blogspot.com/
                </p>
              </div>
            </div>
            <h5 class="fw-bold">More Form Medium</h5>
            <div class="cate mt-4 font">
              <div class="post d-flex mb-2">
                <div class="col-md-9 d-block pe-3">
                  <div class="profile d-flex justify-content-start pb-2">
                    <a rel="noopener follow" href="#">
                      <img
                        alt="Scott Belsky"
                        class="rounded-circle"
                        src="../assets/image/image4.jpg"
                        width="25"
                        height="25"
                        loading="lazy"
                      />
                    </a>
                    <div class="ps-1 my-auto ms-1">
                      <div
                        class="y"
                        aria-hidden="false"
                        aria-describedby="49"
                        aria-labelledby="49"
                      >
                        <a class="d-flex" rel="noopener follow" href="#">
                          <p class="fs-15 m-auto">Alex Mathers</p>
                        </a>
                      </div>
                    </div>
                  </div>
                  <div>
                    <p class="fw-bold fs-6">
                      10 Little Behaviours that Attract People to You
                    </p>
                  </div>
                </div>
                <div class="col-md-3 my-auto">
                  <a
                    rel="noopener follow"
                    href="#"
                    class="d-inline-block w-100"
                  >
                    <img
                      alt="Scott Belsky"
                      class="w-100"
                      src="../assets/image/d-image3.jpg"
                      loading="lazy"
                    />
                  </a>
                </div>
              </div>
              <div class="post d-flex mb-2">
                <div class="col-md-9 d-block pe-3">
                  <div class="profile d-flex justify-content-start pb-2">
                    <a rel="noopener follow" href="#">
                      <img
                        alt="Scott Belsky"
                        class="rounded-circle"
                        src="../assets/image/image4.jpg"
                        width="25"
                        height="25"
                        loading="lazy"
                      />
                    </a>
                    <div class="ps-1 my-auto ms-1">
                      <div
                        class="y"
                        aria-hidden="false"
                        aria-describedby="49"
                        aria-labelledby="49"
                      >
                        <a class="d-flex" rel="noopener follow" href="#">
                          <p class="fs-15 m-auto">Alex Mathers</p>
                        </a>
                      </div>
                    </div>
                  </div>
                  <div>
                    <p class="fw-bold fs-6">
                      10 Little Behaviours that Attract People to You
                    </p>
                  </div>
                </div>
                <div class="col-md-3 my-auto">
                  <a
                    rel="noopener follow"
                    href="#"
                    class="d-inline-block w-100"
                  >
                    <img
                      alt="Scott Belsky"
                      class="w-100"
                      src="../assets/image/d-image3.jpg"
                      loading="lazy"
                    />
                  </a>
                </div>
              </div>
              <div class="post d-flex mb-2">
                <div class="col-md-9 d-block pe-3">
                  <div class="profile d-flex justify-content-start pb-2">
                    <a rel="noopener follow" href="#">
                      <img
                        alt="Scott Belsky"
                        class="rounded-circle"
                        src="../assets/image/image4.jpg"
                        width="25"
                        height="25"
                        loading="lazy"
                      />
                    </a>
                    <div class="ps-1 my-auto ms-1">
                      <div
                        class="y"
                        aria-hidden="false"
                        aria-describedby="49"
                        aria-labelledby="49"
                      >
                        <a class="d-flex" rel="noopener follow" href="#">
                          <p class="fs-15 m-auto">Alex Mathers</p>
                        </a>
                      </div>
                    </div>
                  </div>
                  <div>
                    <p class="fw-bold fs-6">
                      10 Little Behaviours that Attract People to You
                    </p>
                  </div>
                </div>
                <div class="col-md-3 my-auto">
                  <a
                    rel="noopener follow"
                    href="#"
                    class="d-inline-block w-100"
                  >
                    <img
                      alt="Scott Belsky"
                      class="w-100"
                      src="../assets/image/d-image3.jpg"
                      loading="lazy"
                    />
                  </a>
                </div>
              </div>
            </div>
          </div>
        </aside>
      </div>
    </div>
    <div class="container d-flex flex-column">
      <div class="col-md-12 mb-5">
        <h5>Comment</h5>
        <div class="comment-box mx-5 px-3">
          <p>Here you can left message!</p>
          <input
            type="text"
            v-model="commentForm.body"
            class="px-2 w-100 pb-5 mb-3 border border-secondary rounded"
            placeholder="What are your thoughts? "
          />
          <button class="btn btn-secondary float-end" @click="postComment">
            Comment
          </button>
        </div>
      </div>
      <div
        class="comment pb-5"
        v-for="comment in comments"
        :key="comment.commentId"
      >
        <div class="profile mb-4">
          <div class="d-flex">
            <a class="" href="" rel="noopener follow">
              <div class="">
                <img
                  alt="Profile"
                  class="rounded-circle"
                  :src="image + comment.user.profile"
                  width="48"
                  height="48"
                  loading="lazy"
                />
              </div>
            </a>
            <div class="ps-2">
              <div
                class="bk"
                aria-hidden="false"
                aria-describedby="1"
                aria-labelledby="1"
              >
                <a class="fs-6 fw-bold" href="" rel="profile1">Scott Belsky</a>
              </div>
              <div class="d-flex py-1 fs-15 text-secondary">
                <p class="pe-1 mb-1"><span>2 minutes ago</span></p>
              </div>
            </div>
          </div>
          <div>
            <p class="fw-bold fs-15">{{ comment.body }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "PostEdit",
  data() {
    return {
      post: {},
      comments: [],
      commentForm: {
        commentId: null,
        body: "",
        postId: this.$route.params.id,
      },
      image: "http://localhost:8085/Medium/",
    };
  },
  created() {
    axios
      .get(`http://localhost:8085/Medium/api/editPost/${this.$route.params.id}`)
      .then((response) => {
        this.post = response.data.postForm;
        this.comments = response.data.postForm.comment;
      });
  },
  methods: {
    postComment() {
      if (!this.commentForm.body.trim()) {
        return;
      }
      axios
        .post(
          `http://localhost:8085/Medium/api/saveComment`,
          this.commentForm,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        )
        .then((response) => (this.comments = response.data.commentForm));
    },
  },
};
</script>
