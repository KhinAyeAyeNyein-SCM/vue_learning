<template>
  <header-view></header-view>
  <profile-detail></profile-detail>
</template>

<script>
import HeaderView from "@/components/HeaderView.vue";
import ProfileDetail from "@/components/ProfileDetail.vue";

export default {
  name: "ProfileView",
  components: {
    HeaderView,
    ProfileDetail,
  },
};
</script>
