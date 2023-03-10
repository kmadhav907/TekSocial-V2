<template>
  <div class="app">
    <div class="sidebar">
      <SideNav v-if="!isLogin" @toggle-menu="ToggleMenu" />

      <!-- <ProfileDetails :is-sidebar-expanded="isSidebarExpanded" /> -->
    </div>
    <div class="content" :class="sidebarClass">
      <router-view />
    </div>

  </div>
</template>



<script>
import SideNav from './components/SideNav.vue';
import ProfileDetails from './components/ProfileDetails.vue';
import 'bootstrap/dist/css/bootstrap.css';





// export default {
//   name: 'App',
//   components: {
//     SideNav
//   }
// }


export default {
  name: 'App',
  components: {
    SideNav,
    // ProfileDetails
  },
  data() {
    return {
      isSidebarExpanded: false
    };
  },
  methods: {
    ToggleMenu(is_expanded) {
      this.isSidebarExpanded = is_expanded;
    }

  },
  computed : {
    isLogin(){
      return this.$route.path === '/';
    }
  }
  //   isSignInScreen() {
  //     return this.$route.path === '/login';
  //   }
  // }


};
</script>



<style lang="scss">


*body{
  margin: 0px;
  padding: 0px;
}

.sidebar {
  position: relative;
  z-index: 1;
}
</style>


