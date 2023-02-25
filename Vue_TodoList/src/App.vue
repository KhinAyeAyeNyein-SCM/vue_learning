<template>
<img alt="Vue logo" class="logo" src="./assets/logo.png"/>
  <demo-proj msg="Welcome to Your Vue.js App">
  </demo-proj>
</template>

<script>
import DemoProj from './DemoProj.vue'

export default {
  name: 'App',
  components: {
    DemoProj
  }
}
</script>

<style>
#app {
  font-family: cursive;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 6rem auto;
  width: 50%;
}
.logo {
  width: 10%;
}
@media ( max-width :780px) {
  #app {
    width: 90%;
  }
}
</style>
