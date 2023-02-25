<template>
  <div class="home">
    <header-menu></header-menu>
    <login-view></login-view>
  </div>
</template>

<script>
// @ is an alias to /src
import HeaderMenu from "@/components/HeaderMenu.vue";
import LoginView from "@/components/LoginView.vue";

export default {
  name: "HomeView",
  components: {
    HeaderMenu,
    LoginView,
  },
};
</script>
