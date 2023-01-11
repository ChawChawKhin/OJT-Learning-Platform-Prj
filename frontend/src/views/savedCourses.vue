<template>
  <div id="app">
    <v-app id="inspire">
      <v-card>
        <v-tabs>
          <v-tab @click="onClickCategory(movieCategoryList[0])"
            ><v-icon left>mdi-heart-multiple-outline</v-icon> Newly saved</v-tab
          >
          <v-tab @click="onClickCategory(movieCategoryList[1])"
            ><v-icon left>mdi-chart-multiple</v-icon>Learning</v-tab
          >
          <v-tab @click="onClickCategory(movieCategoryList[2])"
            ><v-icon left>mdi-checkbox-multiple-marked</v-icon>Completed</v-tab
          >
          <v-tab-item>
            <v-card flat>
              <v-card-text>
                <v-row>
                  <!-- Movie List -->
                  <v-col cols="5">
                    <v-row
                      cols="3"
                      v-for="(movie, index) in movieList"
                      :key="index"
                    >
                      <v-card
                        class="mx-auto"
                        outlined
                        @click="goToMovieDetails(movie)"
                      >
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
                            <v-list-item-subtitle>
                              <p class="text-truncate" style="max-width: 250px">
                                {{ movie.overview }}
                              </p>
                            </v-list-item-subtitle>
                            <div class="text-overline mb-4">
                              {{ movie.budget }} Kyats
                            </div>
                            <div v-show="movie.adult" class="text-body-1">
                              Adult
                            </div>
                            <v-card-actions>
                              <v-btn outlined rounded text> Details </v-btn>
                            </v-card-actions>
                          </v-list-item-content>
                        </v-list-item>
                      </v-card>
                    </v-row>
                  </v-col>
                  <v-col cols="13"><div>text</div></v-col>
                </v-row>
              </v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item>
            <v-card flat>
              <v-row>
                <!-- Movie List -->
                <v-col cols="5">
                  <v-row
                    cols="3"
                    v-for="(movie, index) in movieList"
                    :key="index"
                  >
                    <v-card
                      class="mx-auto"
                      outlined
                      @click="goToMovieDetails(movie)"
                    >
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
                          <v-list-item-subtitle>
                            <p class="text-truncate" style="max-width: 250px">
                              {{ movie.overview }}
                            </p>
                          </v-list-item-subtitle>
                          <div class="text-overline mb-4">
                            {{ movie.budget }} Kyats
                          </div>
                          <div v-show="movie.adult" class="text-body-1">
                            Adult
                          </div>
                          <v-card-actions>
                            <v-btn outlined rounded text> Details </v-btn>
                          </v-card-actions>
                        </v-list-item-content>
                      </v-list-item>
                    </v-card>
                  </v-row>
                </v-col>
              </v-row>
            </v-card>
          </v-tab-item>
          <v-tab-item>
            <v-card flat>
              <v-row>
                <!-- Movie List -->
                <v-col cols="5">
                  <v-row
                    cols="3"
                    v-for="(movie, index) in movieList"
                    :key="index"
                  >
                    <v-card
                      class="mx-auto"
                      outlined
                      @click="goToMovieDetails(movie)"
                    >
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
                          <v-list-item-subtitle>
                            <p class="text-truncate" style="max-width: 250px">
                              {{ movie.overview }}
                            </p>
                          </v-list-item-subtitle>
                          <div class="text-overline mb-4">
                            {{ movie.budget }} Kyats
                          </div>
                          <div v-show="movie.adult" class="text-body-1">
                            Adult
                          </div>
                          <v-card-actions>
                            <v-btn outlined rounded text> Details </v-btn>
                          </v-card-actions>
                        </v-list-item-content>
                      </v-list-item>
                    </v-card>
                  </v-row>
                </v-col>
              </v-row>
            </v-card>
          </v-tab-item>
        </v-tabs>
      </v-card>
    </v-app>
  </div>
</template>

<script>
import utils from "../utils/utils";
export default {
  name: "register",

  components: {},

  data() {
    return {
      localDomain: utils.constant.localDomain,

      catList: [],
      movieList: [],

      movieCategoryList: [],
      news: ["a", "b", "c"],
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

<style lang="scss" scoped></style>
<style scoped>
.textTruncation {
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>
