<template>
  <div class="my-5 container">
    <!-- Poster, Movie Info -->
    <v-row class="ma-0">
      <!-- Poster -->
      <v-col cols="3" sm="4">
        <v-hover v-slot="{ hover }" open-delay="200">
          <v-card :elevation="hover ? 16 : 2" :class="{ 'on-hover': hover }">
            <v-img
              class="ma-0"
              :src="localDomain + movie.posterPath"
              contain
            ></v-img>
          </v-card>
        </v-hover>
      </v-col>

      <!-- Movie Info -->
      <v-col cols="7" sm="6">
        <h1 class="grey--text text-darken-3 mt-5">{{ movie.title }}</h1>
        <div class="text-caption ml-2 my-2">{{ movie.category.name }}</div>
        <div class="text-body-1 ml-2 my-2">
          {{ movie.budget }} replace total time
        </div>
        <v-card class="text-body-1 ml-1 my-2 pa-2" id="p">
          <p class="textTruncation">
            {{ movie.overview }}
          </p>
          <v-dialog v-model="dialog" scrollable max-width="300px">
            <template v-slot:activator="{ on, attrs }">
              <a color="primary" dark v-bind="attrs" v-on="on"> more </a>
            </template>
            <v-card>
              <v-card-title>Description</v-card-title>
              <v-divider></v-divider>
              <v-card-text style="height: 300px">
                {{ movie.overview }}
              </v-card-text>
              <v-divider></v-divider>
              <v-card-actions>
                <v-btn color="blue darken-1" text @click="dialog = false">
                  Close
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-card>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="10">
        <v-card class="text-body-1 ml-1 my-2 pa-2">
          <v-col>
            <v-list>
              <v-list-item three-line>
                <v-list-item-avatar>
                  <v-img
                    src="https://cdn-icons-png.flaticon.com/512/3410/3410142.png"
                  ></v-img>
                </v-list-item-avatar>

                <v-list-item-content>
                  <v-list-item-title>
                    {{ movie.category.name }} Instructor
                  </v-list-item-title>
                  <v-spacer></v-spacer>
                  <v-list-item-subtitle
                    ><a
                      :href="youtubeChannelURL"
                      target="_blank"
                      style="text-decoration: none"
                      ><v-btn>Go</v-btn></a
                    ></v-list-item-subtitle
                  >
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-col>
        </v-card>
      </v-col>
    </v-row>

    <v-row>
      <v-row> <v-btn @click="loadURL()">play</v-btn></v-row>
      <v-row v-if="result">
        <iframe
          width="560"
          @click="loadURL()"
          height="315"
          :src="result"
          frameborder="0"
          allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
          allowfullscreen
        ></iframe>
      </v-row>
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

      youtubeChannelURL: "https://www.youtube.com/@aaeideapro1192",
      youtubeURL: "https://www.youtube.com/watch?v=fIdp1zrXhF4",
      result: "",

      dialogm1: "",
      dialog: false,
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

    loadURL() {
      const youtubeEmbedTemplate = "https://www.youtube.com/embed/";
      const url = this.youtubeURL.split(
        /(vi\/|v%3D|v=|\/v\/|youtu\.be\/|\/embed\/)/
      );
      console.log("url", url);
      const YId =
        undefined !== url[2] ? url[2].split(/[^0-9a-z_/\\-]/i)[0] : url[0];
      console.log("YId", YId);
      if (YId === url[0]) {
        console.log("not a youtube link");
      } else {
        console.log("it's  a youtube video");
      }
      const topOfQueue = youtubeEmbedTemplate.concat(YId);
      console.log("topOfQueue", topOfQueue);
      this.result = topOfQueue;
      this.youtubeURL = "";
    },
  },
};
</script>

<style scoped>
.container {
  margin-left: 10vw !important;
  margin-right: 10vw !important;
}

.textTruncation {
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>
