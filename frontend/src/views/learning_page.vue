<template>
  <div class="my-5 container">
    <!-- Poster, Movie Info -->
    <v-row class="ma-0">
      <!-- Movie Info -->
      <v-col cols="7">
        <div class="text-h5">{{ movie.title }}</div>
      </v-col>
    </v-row>

    <v-row
      ><v-col cols="3">
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
        </v-card>
      </v-col>
      <v-col cols="7">
        <!-- Trailer -->
        <div class="mx-2 mt-5">
          <h3 class="mb-3">Video 1</h3>
          <v-row>
          <v-btn
          class="ma-2"
          color="orange darken-2"
          dark
        >
          <v-icon
            dark
            left
          >
            mdi-arrow-left
          </v-icon>Back
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn
          class="ma-2"
          color="orange darken-2"
          dark
        >
          Next 
          <v-icon
            dark
            right
          >
            mdi-arrow-right
          </v-icon>
        </v-btn>
        </v-row>
        <v-row>
          <video
            class="mb-2"
            width="100%"
            :src="localDomain + movie.trailerPath"
            controls
          ></video>
        </v-row>
        </div>
      </v-col>
    </v-row>
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
  },
};
</script>

<style scoped>
.container {
  margin-left: 10vw !important;
  margin-right: 10vw !important;
}
</style>
