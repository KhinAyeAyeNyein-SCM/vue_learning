<template>
  <div class="container pt-15">
    <form @submit.prevent="login">
      <div class="card px-5 py-4 w-50 m-auto border-2">
        <h3>Login</h3>
        <div class="text-danger">{{ error.responseDescription }}</div>
        <div class="row mb-3">
          <label>Email</label>
          <input
            class="py-2 px-2 mx-2 rounded border border-gray"
            type="text"
            v-model="loginForm.email"
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
          <label>Password</label>
          <input
            class="py-2 px-2 mx-2 rounded border border-gray"
            type="password"
            v-model="loginForm.password"
            placeholder="Password"
          />
          <div
            class="text-danger"
            v-if="error.errors != null && error.errors.password != null"
          >
            {{ error.errors.password }}
          </div>
        </div>
        <p>
          Don't Have account yet?
          <router-link to="/register" class="link">Sign Up</router-link>
        </p>
        <button class="btn btn-secondary button">Login</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "LoginView",
  data() {
    return {
      loginForm: {
        email: "",
        password: "",
      },
      error: {},
    };
  },
  methods: {
    login() {
      axios
        .post("http://localhost:8085/Medium/api/login", this.loginForm, {
          headers: { "Content-Type": "application/json" },
        })
        .then((response) => {
          console.log(response.data);
          if (response.data.responseCode == 200) {
            localStorage.setItem("token", response.data.token),
              this.$router.push("/post");
          } else {
            this.error = response.data;
          }
        })
        .catch((error) => {
          console.log(error);
          this.error = error.data.errors;
        });
    },
  },
};
</script>

<style>
@import "@/assets/css/style.css";
</style>
