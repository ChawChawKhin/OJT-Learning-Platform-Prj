<template>
  <v-app-bar app color="#3F52E3" dense dark>
    <v-icon class="mr-2">mdi-cast-education</v-icon>
    <a class="navtitle" href="/">U<strong>Tutorial</strong></a>
    <v-btn v-if="isLogin" text class="ml-2" to="/savedCourses">Saved Courses</v-btn>
    <v-spacer></v-spacer>

    <!-- Home -->
    <router-link class="mx-2 navlink" to="/"
      ><v-icon>mdi-home</v-icon> ホーム</router-link
    >

    <!-- Register -->
    <span v-if="!isLogin">|</span>
    <router-link v-if="!isLogin" class="mx-2 navlink" to="/register"
      >アカウント登録</router-link
    >

    <!-- Login -->
    <span v-if="!isLogin">|</span>
    <router-link v-if="!isLogin" class="mx-2 navlink" to="/login"
      ><v-icon>mdi-login</v-icon></router-link
    >

    <!-- Profile -->
    <span v-if="isLogin">|</span>
    <!-- <router-link v-if="isLogin" class="mx-2 navlink" to="/profile"
      >プロフィール</router-link
    > -->
    <router-link v-if="isLogin" class="mx-2 navlink" to="/profile"
      ><v-badge bordered bottom color="green" dot offset-x="10" offset-y="10">
        <v-icon>mdi-account-circle</v-icon>
      </v-badge></router-link
    >

    <!-- Login out -->
    <span v-if="isLogin">|</span>
    <a v-if="isLogin" class="mx-2 navlink" @click="logout()"
      ><v-icon>mdi-logout</v-icon></a
    >
  </v-app-bar>
</template>

<script>
import utils from "../utils/utils";
export default {
  name: "heading",

  data: () => ({
    loginUser: {},
    isLogin: false,
    items: [
      { title: "Click Me" },
      { title: "Click Me" },
      { title: "Click Me" },
      { title: "Click Me 2" },
    ],
  }),

  created() {
    // Login User
    this.loginUser = this.$store.state.loginUser;
    this.$store.watch(
      () => {
        return this.$store.state.loginUser;
      },
      (newVal, oldVal) => {
        this.loginUser = newVal;
      },
      {
        deep: true,
      }
    );

    // IsLogin
    this.isLogin = this.$store.state.isLogin;
    this.$store.watch(
      () => {
        return this.$store.state.isLogin;
      },
      (newVal, oldVal) => {
        this.isLogin = newVal;
      },
      {
        deep: true,
      }
    );
  },

  methods: {
    logout() {
      this.$store.commit("logout");
      // If current path is not home page, Go to home page
      if (this.$route.path != "/") {
        this.$router.push({ path: "/" });
      }
    },
  },
};
</script>

<style scoped>
.navlink {
  color: white !important;
  text-decoration: none;
}

.navlink:hover {
  cursor: pointer;
  text-decoration: underline;
}

.navtitle {
  color: white !important;
  text-decoration: none;
  cursor: pointer !important;
}
</style>
