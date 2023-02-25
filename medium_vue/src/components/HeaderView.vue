<template>
  <div
    class="header-cont py-2 px-4 w-100 position-fixed bg-white"
    style="z-index: 10"
  >
    <div class="">
      <div class="header d-flex justify-content-between align-middle">
        <h2 class="mb-0">
          <div class="d-flex justify-content-start">
            <a href="#" class="d-flex justify-content-start">
              <label class="label1 my-auto mr-1"></label>
              <label class="label2 my-auto mr-1"></label>
              <label class="label3 my-auto mr-1"></label>
            </a>
            <div class="d-flex font br-2 py-1 ms-2 pe-1 bg-ash">
              <div class="mx-3 d-flex py-1 text-secondary">
                <svg width="24" height="24" viewBox="0 0 24 24" fill="none">
                  <path
                    fill-rule="evenodd"
                    clip-rule="evenodd"
                    d="M4.1 11.06a6.95 6.95 0 1 1 13.9 0 6.95 6.95 0 0 1-13.9 0zm6.94-8.05a8.05 8.05 0 1 0 5.13 14.26l3.75 3.75a.56.56 0 1 0 .8-.79l-3.74-3.73A8.05 8.05 0 0 0 11.04 3v.01z"
                    fill="currentColor"
                  ></path>
                </svg>
              </div>
              <input
                type="search"
                class="transparent fs-6"
                placeholder="Search Medium"
              />
            </div>
          </div>
        </h2>
        <nav class="my-auto">
          <ul class="mb-0">
            <li class>
              <router-link to="/post/create" class="fs-6">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="16"
                  height="16"
                  fill="currentColor"
                  class="bi bi-pencil-square"
                  viewBox="0 0 16 16"
                >
                  <path
                    d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"
                  />
                  <path
                    fill-rule="evenodd"
                    d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"
                  />
                </svg>
                Write
              </router-link>
            </li>
            <li class="bg-transparent">
              <a href="#" class="text-dark">
                <img
                  alt="Scott Belsky"
                  class="rounded-circle img"
                  src="../assets/image/image2.jpg"
                  width="20"
                  height="20"
                  loading="lazy"
                />
                <i class="fas fa-angle-down"></i>
              </a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HeaderView",
};
</script>

<style>
@import "@/assets/css/style.css";
</style>
