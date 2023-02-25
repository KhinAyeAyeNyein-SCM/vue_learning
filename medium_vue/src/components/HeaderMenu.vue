<template>
  <div class="header py-2 w-100 position-fixed b-y">
    <div class="container">
      <div class="header align-middle">
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
          <div class="container-fluid">
            <router-link
              to="/"
              class="navbar-brand d-flex justify-content-start"
              href="#"
            >
              <label class="label1 my-auto mr-1"></label>
              <label class="label2 my-auto mr-1"></label>
              <label class="label3 my-auto mr-1"></label>
              <h2 class="mb-0">Medium</h2></router-link
            >
            <button
              class="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                  <router-link
                    class="nav-link active"
                    aria-current="page"
                    to="/post"
                    >Write</router-link
                  >
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">Sign In</a>
                </li>
                <li class="nav-item dropdown">
                  <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    role="button"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    Get Started
                  </a>
                  <ul class="dropdown-menu">
                    <li>
                      <router-link class="dropdown-item" to="/about"
                        >Post</router-link
                      >
                    </li>
                    <li><a class="dropdown-item" href="#">Profile</a></li>
                    <li><hr class="dropdown-divider" /></li>
                    <li>
                      <a class="dropdown-item" href="#">Logout</a>
                    </li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HeaderMenu",
};
</script>

<style>
@import "@/assets/css/style.css";
</style>
