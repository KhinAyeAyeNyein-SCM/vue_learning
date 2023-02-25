<template>
  <header-view></header-view>
  <post-edit></post-edit>
</template>

<script>
import HeaderView from "@/components/HeaderView.vue";
import PostEdit from "@/components/PostEdit.vue";

export default {
  name: "PostDetail",
  components: {
    HeaderView,
    PostEdit,
  },
};
</script>
