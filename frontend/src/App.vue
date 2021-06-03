<template>
  <div id="app">
    <div :class="{ 'nav-open': NavbarStore.showNavbar }">
    <router-view name="header" />
    <div class="wrapper">
      <router-view />
    </div>
    <router-view name="footer" />
  </div>
  </div>
</template>
<script>
export default {};
</script>
