<template>
  <header-menu></header-menu>
  <post-create></post-create>
</template>

<script>
import HeaderMenu from "@/components/HeaderMenu.vue";
import PostCreate from "@/components/PostCreate.vue";

export default {
  name: "CreatePost",
  components: {
    HeaderMenu,
    PostCreate,
  },
};
</script>
