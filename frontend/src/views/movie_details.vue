<template>
  <div class="my-5 container">
    <!-- Poster, Movie Info -->
    <v-row class="ma-0">
      <!-- Movie Info -->
      <v-col cols="7">
        <div class="text-h3">{{ movie.title }}</div>
        <div class="text-caption ml-2 my-2">{{ movie.category.name }}</div>
        <div class="text-body-1 ml-2 my-2">{{ movie.budget }} Kyat</div>
        <div v-show="movie.adult" class="text-body-1 ml-2 my-2">Adult</div>
        <v-card class="text-body-1 ml-1 my-2 pa-2">{{ movie.overview }}</v-card>
      </v-col>

      <!-- Poster -->
      <v-col cols="3" class="ma-0 pa-0">
        <v-img
          class="ma-0"
          :src="localDomain + movie.posterPath"
          contain
        ></v-img>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="7">
        <v-list>
          <v-list-item three-line>
            <v-list-item-avatar>
              <v-img src="https://cdn.vuetifyjs.com/images/lists/2.jpg"></v-img>
            </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title>Name</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-col>
      <v-col cols="3" class="ma-0 pa-0">
        <v-btn
          class="ma-2"
          outlined
          color="indigo"
          @click="goToMovieDetails(movie)"
        >
          Enroll/Go to Course Button
        </v-btn>
      </v-col>
    </v-row>

    <v-row
      ><v-col cols="10">
        <h3 class="mb-3">Syllabus</h3>
        <v-card class="mx-auto">
          <v-list>
            <v-list-group
              v-for="item in items"
              :key="item.title"
              v-model="item.active"
              :prepend-icon="item.action"
              no-action
            >
              <template v-slot:activator>
                <v-list-item-content>
                  <v-list-item-title v-text="item.title"></v-list-item-title>
                </v-list-item-content>
              </template>

              <v-list-item v-for="child in item.items" :key="child.title">
                <v-list-item-content>
                  <v-list-item-title v-text="child.title"></v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list-group>
          </v-list>
        </v-card> </v-col
    ></v-row>
  </div>
</template>

<script>
import utils from "../utils/utils";

export default {
  name: "movie_details",

  data() {
    return {
      localDomain: utils.constant.localDomain,

      loginUser: {},

      // Movie ID from Path
      movie_id: this.$route.params.id,
      movie: {},

      items: [
        {
          action: "mdi-ticket",
          items: [{ title: "List Item" }],
          title: "Attractions",
        },
        {
          action: "mdi-silverware-fork-knife",
          active: true,
          items: [
            { title: "Breakfast & brunch" },
            { title: "New American" },
            { title: "Sushi" },
          ],
          title: "Dining",
        },
        {
          action: "mdi-school",
          items: [{ title: "List Item" }],
          title: "Education",
        },
      ],
    };
  },

  async created() {
    // LoginUser from Vuex
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

    await this.fetchMovie();
    await this.recordMovieHistory();
  },

  methods: {
    async fetchMovie() {
      const resp = await utils.http.get("/movie/" + this.movie_id);
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.movie = data;
        }
      }
    },
    async recordMovieHistory() {
      const resp = await utils.http.post("/record/add", {
        user: {
          id: this.loginUser.id,
        },
        movie: {
          id: this.movie_id,
        },
      });
      if (resp && resp.status !== 200) {
        console.log("Record Movie History Failed!");
      }
    },

    goToMovieDetails(movie) {
      this.$router.push({
        path: "/movie_details/" + movie.id + "/learning_page",
      });
    },
  },
};
</script>

<style scoped>
.container {
  margin-left: 10vw !important;
  margin-right: 10vw !important;
}
</style>
