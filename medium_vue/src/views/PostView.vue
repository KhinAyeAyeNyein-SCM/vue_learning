<template>
  <header-view></header-view>
  <post-list></post-list>
</template>

<script>
import HeaderView from "@/components/HeaderView.vue";
import PostList from "@/components/PostList.vue";

export default {
  name: "PostView",
  components: {
    HeaderView,
    PostList,
  },
};
</script>
