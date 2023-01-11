<template>
  <div>
    <v-container>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="10">
          <v-card class="elevation-6 mt-10">
            <v-window v-model="step">
              <v-window-item :value="1">
                <v-row>
                  <v-col cols="12" md="6">
                    <v-card-text class="mt-12">
                      <h4 class="text-center">Login in to Your Account</h4>
                      <v-form ref="loginForm" v-model="loginForm">
                        <v-row align="center" justify="center">
                          <v-col cols="12" sm="8">
                            <v-text-field
                              v-model="email"
                              :rules="[
                                (v) => !!v || 'Required',
                                (v) =>
                                  /.+@.+\..+/.test(v) || 'E-mail must be valid',
                              ]"
                              label="E-mail"
                              required
                              outlined
                              dense
                              color="blue"
                              autocomplete="false"
                              class="mt-16"
                            ></v-text-field>
                            <v-text-field
                              v-model="password"
                              :counter="10"
                              :rules="[
                                (v) => !!v || 'Required',
                                (v) =>
                                  (v && v.length <= 10) ||
                                  'Password must be less than 10 characters',
                              ]"
                              :type="passwordShow ? 'text' : 'password'"
                              :append-icon="
                                passwordShow ? 'mdi-eye' : 'mdi-eye-off'
                              "
                              @click:append="passwordShow = !passwordShow"
                              label="Password"
                              required
                              outlined
                              dense
                              color="blue"
                              autocomplete="false"
                            ></v-text-field>

                            <v-btn
                              color="blue"
                              dark
                              block
                              tile
                              @click="login()"
                            >
                              <span v-if="!loading">Login</span>
                              <v-progress-circular
                                v-else
                                indeterminate
                                color="primary"
                              ></v-progress-circular>
                            </v-btn>
                            <v-alert
                              class="mt-3"
                              v-show="errorAlert"
                              dense
                              type="error"
                            >
                              Login Failed! <br />
                              Email or Password is wrong!
                            </v-alert>
                          </v-col>
                        </v-row>
                      </v-form>
                    </v-card-text>
                  </v-col>
                  <v-col cols="12" md="6" class="blue rounded-bl-xl">
                    <div style="text-align: center; padding: 180px 0">
                      <v-card-text class="white--text">
                        <h3 class="text-center">Don't Have an Account Yet?</h3>
                      </v-card-text>
                      
                      <div class="text-center">
                        <v-btn tile outlined dark to="/register"
                          >Register</v-btn
                        >
                      </div>
                    </div>
                  </v-col>
                </v-row>
              </v-window-item>
              
            </v-window>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import utils from "../utils/utils";

export default {
  name: "login",

  components: {},

  data() {
    return {
      step: 1,

      loginForm: false,
      email: "",
      password: "",
      passwordShow: false,
      // email: "admin@gmail.com",
      // password: "1111",
      errorAlert: false,
      loading: false,
    };
  },

  props: {
    source: String,
  },

  async created() {},

  methods: {
    async login() {
      if (this.$refs.loginForm.validate()) {
        this.errorAlert = false;

        try {
          this.loading = true;

          // API Call
          const resp = await utils.http.post("/user/login", {
            gmail: this.email,
            password: this.password,
          });
          if (resp && resp.status === 200) {
            const data = await resp.json();
            if (data) {
              // Store Login Info in Vuex
              this.$store.commit("setLoginUser", data);
              // If Admin -> Go to Admin
              // If User -> Go to Home
              if (data.role == "admin") {
                this.$router.push({ path: "/admin" });
              } else {
                this.$router.push({ path: "/" });
              }
            }
          } else {
            this.errorAlert = true;
          }
        } catch (error) {
          console.log(error);
        } finally {
          this.loading = false;
        }
      }
    },
  },
};
</script>

<style scoped>
.v-application .rounded-bl-xl {
  border-bottom-left-radius: 300px !important;
}
.v-application .rounded-br-xl {
  border-bottom-right-radius: 300px !important;
}
</style>
