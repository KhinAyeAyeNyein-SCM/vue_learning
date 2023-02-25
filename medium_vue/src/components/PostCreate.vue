<template>
  <div class="post-create pt-15">
    <div class="container">
      <div class="card px-5 py-4 w-90 m-auto border-2">
        <h3>Post Create</h3>
        <form
          @submit.prevent="createPost"
          enctype="multipart/form-data"
          id="formData"
        >
          <div v-if="error != null">{{ error }}</div>
          <div class="row mb-3">
            <input
              class="py-1 px-2 mx-2 mb-3 rounded border border-gray"
              type="text"
              name="title"
              v-model="postForm.title"
              placeholder="Post Title"
            />
            <select
              v-model="postForm.category"
              name="categories"
              class="py-1 px-2 mx-2 mb-3 rounded border border-gray text-secondary"
            >
              <option value="none">Choose Category</option>
              <option
                name="categories"
                v-for="category in categoryList"
                :key="category.categoryId"
                :value="category.categoryName"
              >
                {{ category.categoryName }}
              </option>
            </select>
            <input
              class="py-1 px-2 mx-2 mb-2 rounded border border-gray text-secondary"
              type="file"
              name="file"
              @change="selectImage($event.target)"
            />
            <div class="px-0 mx-2 mb-3">
              <textarea
                v-model="postForm.description"
                name="description"
                id=""
                cols="30"
                rows="10"
              ></textarea>
            </div>
            <!-- <div class="px-0 mx-2 mb-3"><div id="summernote"></div></div> -->
            <button class="ms-2 btn btn-secondary button">Publish</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "PostCreate",
  data() {
    return {
      loginForm: {
        email: "",
        password: "",
      },
      error: "",
      categoryList: [],
      postForm: {
        id: null,
        title: "",
        description: "",
        category: "",
      },
    };
  },
  async created() {
    const token = localStorage.getItem("token");
    let headers = {
      "Content-Type": "application/json;charset=utf-8",
    };
    if (token) {
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
      axios.defaults.headers.get["Authorization"] = `Bearer ${token}`;
      headers["Authorization"] = `Bearer ${token}`;
    }
    var accessToken = "Bearer " + token;
    let configAuth = {
      headers: { Authorization: accessToken },
    };
    await axios
      .get("http://localhost:8085/Medium/api/savePost", { configAuth })
      .then((response) => {
        this.categoryList = response.data.postForm.categoryList;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    selectImage(files) {
      if (!files.length) return;
      this.file = this.$refs.file.files.item(0);
      console.log(this.file);
      this.profile = files[0].name;
    },
    createPost() {
      const token = localStorage.getItem("token");
      let form = document.getElementById("formData");
      var formData = new FormData(form);
      axios
        .post("http://localhost:8085/Medium/api/savePost", formData, {
          headers: {
            Accept: "application/json",
            "Content-Type": "multipart/form-data",
            Authorization: "Bearer " + token,
          },
        })
        .then((response) => {
          console.log(response.data);
          if (response.data.responseCode == 200) {
            this.$router.push("/post");
          } else {
            this.error = response.data;
          }
        })
        .catch(this.$router.push("/post"));
    },
  },
};
</script>

<style>
@import "@/assets/css/style.css";
</style>
