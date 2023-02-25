<template>
  <div class="container pt-15">
    <div class="card px-5 py-4 w-90 m-auto border-2">
      <h3>Sign Up</h3>
      <form
        @submit.prevent="register"
        enctype="multipart/form-data"
        id="formData"
      >
        <div>{{ error.responseDescription }}</div>
        <div class="px-2 d-flex flex-column">
          <div class="col-md-12 d-flex justify-content-between">
            <div class="col-md-6 mx-2">
              <div class="row mb-3">
                <label>Name</label>
                <input
                  class="py-2 px-2 mx-2 w-90 rounded border border-gray"
                  type="text"
                  v-model="name"
                  name="name"
                  placeholder="Your Name"
                />
                <div
                  class="text-danger"
                  v-if="error.errors != null && error.errors.name != null"
                >
                  {{ error.errors.name }}
                </div>
              </div>
              <div class="row mb-3">
                <label>Password</label>
                <input
                  class="py-2 px-2 mx-2 w-90 rounded border border-gray"
                  type="password"
                  name="password"
                  v-model="password"
                  placeholder="Password"
                />
                <div
                  class="text-danger"
                  v-if="error.errors != null && error.errors.password != null"
                >
                  {{ error.errors.password }}
                </div>
              </div>
            </div>
            <div class="col-md-6 mx-2">
              <div class="row mb-3">
                <label>Email</label>
                <input
                  class="py-2 px-2 mx-2 w-90 rounded border border-gray"
                  type="text"
                  name="email"
                  v-model="email"
                  placeholder="Your Email (example@gmail.com)"
                />
                <div
                  class="text-danger"
                  v-if="error.errors != null && error.errors.email != null"
                >
                  {{ error.errors.email }}
                </div>
              </div>
              <div class="row mb-3">
                <label>Confirm Password</label>
                <input
                  class="py-2 px-2 mx-2 w-90 rounded border border-gray"
                  type="password"
                  name="confirmPassword"
                  v-model="confirmPassword"
                  placeholder="Confirm Password"
                />
                <div
                  class="text-danger"
                  v-if="
                    error.errors != null && error.errors.confirmPassword != null
                  "
                >
                  {{ error.errors.confirmPassword }}
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-12 mx-2">
            <div class="row w-100 mb-3">
              <label>Bio</label>
              <input
                class="py-2 px-2 mx-2 rounded border border-gray"
                type="text"
                name="bio"
                v-model="bio"
                placeholder="Your Bio"
              />
            </div>
          </div>
          <div class="col-md-12 mx-2">
            <div class="row w-100 mb-3">
              <label>Profile</label>
              <input
                class="py-1 px-2 mx-2 mb-2 rounded border border-gray text-secondary"
                type="file"
                name="file"
                id="file"
                @change="selectProfile($event.target)"
              />
            </div>
          </div>
          <button class="btn btn-secondary button ms-1">Sign Up</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
// import LoginService from "../services/Login.js";
export default {
  name: "RegisterView",
  data() {
    return {
      error: "",
      name: "",
      email: "",
      password: "",
      confirmPassword: "",
      bio: "",
      profile: "",
      file: "",
    };
  },
  methods: {
    selectProfile(files) {
      if (!files.length) return;
      this.file = this.$refs.file.files.item(0);
      console.log(this.file);
      this.profile = files[0].name;
    },
    register() {
      let form = document.getElementById("formData");
      var formData = new FormData(form);
      axios
        .post("http://localhost:8085/Medium/api/register", formData, {
          headers: {
            Accept: "application/json",
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          console.log(response.data);
          if (response.data.responseCode == 200) {
            this.$router.push("/");
          } else {
            this.error = response.data;
          }
        });
    },
  },
};
</script>

<style>
@import "@/assets/css/style.css";
</style>
