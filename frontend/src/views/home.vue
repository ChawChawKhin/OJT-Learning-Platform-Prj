<template>
  <div>
    <v-row>
      <v-col cols="2">
        <v-autocomplete
          v-model="value"
          :items="items"
          dense
          filled
          label="Search Course"
        ></v-autocomplete>
      </v-col>
      <v-col>
        <h2>Home Page</h2>
      </v-col>
    </v-row>

    <v-row>
      <!-- Category Menu -->
      <v-col cols="2">
        <v-card class="mx-auto">
          <v-navigation-drawer permanent>
            <v-list dense nav>
              <v-list-item
                v-for="(cat, index) in movieCategoryList"
                :key="index"
                link
                @click="onClickCategory(cat)"
              >
                <v-list-item-content>
                  <v-list-item-title>{{ cat.name }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-navigation-drawer>
        </v-card>
      </v-col>

      <!-- Movie List -->
      <v-col cols="10">
        <v-row cols="3" v-for="(movie, index) in movieList" :key="index">
          <v-card class="mx-auto" outlined @click="goToMovieDetails(movie)">
            <v-list-item three-line>
              <v-list-item-avatar tile size="150"
                ><v-img
                  :src="localDomain + movie.posterPath"
                  max-height="350"
                  cover
                ></v-img
              ></v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title class="text-h5 mb-1">
                  {{ movie.title }}
                </v-list-item-title>
                <v-list-item-subtitle>{{
                  movie.overview
                }}</v-list-item-subtitle>
                <div class="text-overline mb-4">{{ movie.budget }} Kyats</div>
                <div v-show="movie.adult" class="text-body-1">Adult</div>
                <v-card-actions>
                  <v-btn outlined rounded text> Details </v-btn>
                </v-card-actions>
              </v-list-item-content>
            </v-list-item>
          </v-card>          
        </v-row>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import utils from "../utils/utils";

export default {
  name: "home",

  components: {},

  data() {
    return {
      localDomain: utils.constant.localDomain,

      catList: [],
      movieList: [],

      movieCategoryList: [],

      items: ["foo", "bar", "fizz", "buzz"],
    };
  },

  async created() {
    await this.fetchMovieCategories();
    await this.fetchMovies();
  },

  methods: {
    async fetchMovies() {
      const resp = await utils.http.get("/movie");
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.movieList = data;
        }
      }
    },

    async fetchMovieCategories() {
      const resp = await utils.http.get("/admin/category");
      if (resp && resp.status === 200) {
        const data = await resp.json();
        // data is not undefined, not null
        if (data) {
          this.movieCategoryList = data;
        }
      }
    },

    goToMovieDetails(movie) {
      this.$router.push({
        path: "/movie_details/" + movie.id,
      });
    },

    async onClickCategory(cat) {
      const resp = await utils.http.get("/movie/category/" + cat.id);
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.movieList = data;
        }
      }
    },
  },
};
</script>
