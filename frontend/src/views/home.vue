<template>
  <div>
    <v-row>
      <h2 class="mx-3 grey--text">Featured Courses</h2>
      <carousel-3d
        :controls-visible="true"
        :clickable="false"
        :key="movieList.length"
        :listData="movieList"
        :height="200"
      >
        <slide :index="index" v-for="(movie, index) in movieList" :key="index">
          <figure>
            <v-img :src="localDomain + movie.posterPath"></v-img>
            <figcaption>
              <v-btn @click="goToMovieDetails(movie)" text color="white"> {{ movie.title }}</v-btn>
            </figcaption>
          </figure>
        </slide>
      </carousel-3d>
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
import { Carousel3d, Slide } from "vue-carousel-3d";

export default {
  name: "home",

  components: { Carousel3d, Slide },

  data() {
    return {
      localDomain: utils.constant.localDomain,

      catList: [],
      movieList: [],

      movieCategoryList: [],

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

<style>
.carousel-3d-container figure {
  margin: 0;
}

.carousel-3d-container figcaption {
  position: absolute;
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  bottom: 0;
  position: absolute;
  bottom: 0;
  padding: 15px;
  font-size: 12px;
  min-width: 100%;
  box-sizing: border-box;
}

.next span,
.prev span {
  color: red;
}
</style>
