import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import AboutView from "../views/AboutView.vue";
import RegisterView from "../views/RegisterView.vue";
import PostView from "../views/PostView.vue";
import PostDetail from "../views/PostDetail.vue";
import CreatePost from "../views/PostCreate.vue";
import ProfileView from "../views/ProfileView.vue";

const routes = [
  {
    path: "/",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    component: AboutView,
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
  },
  {
    path: "/post",
    name: "post",
    component: PostView,
  },
  {
    path: "/post/:id",
    name: "postDetail",
    component: PostDetail,
  },
  {
    path: "/post/create",
    name: "postCreate",
    component: CreatePost,
  },
  {
    path: "/user/:id",
    name: "userProfile",
    component: ProfileView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
