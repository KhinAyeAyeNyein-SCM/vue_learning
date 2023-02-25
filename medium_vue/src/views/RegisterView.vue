<template>
  <header-menu></header-menu>
  <sign-up></sign-up>
</template>

<script>
import HeaderMenu from "@/components/HeaderMenu.vue";
import SignUp from "@/components/SignUp.vue";

export default {
  name: "RegisterView",
  components: {
    HeaderMenu,
    SignUp,
  },
};
</script>
