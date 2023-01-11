<template>
  <div>
    <v-container>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="10">
          <v-card class="elevation-6 mt-10">
            <v-window>
              <v-window-item>
                <v-row>
                  <v-col cols="12" md="6" class="blue rounded-br-xl">
                    <div style="text-align: center; padding: 180px 0">
                      <v-card-text class="white--text">
                        <h3 class="text-center">Already Registered?</h3>
                      </v-card-text>
                      <div class="text-center">
                        <v-btn tile outlined dark to="/login">
                          Log in
                        </v-btn>
                      </div>
                    </div>
                  </v-col>

                  <v-col cols="12" md="6">
                    <v-card-text class="mt-12">
                      <!-- Register Form -->
                      <v-form ref="registerForm" v-model="registerForm">
                        <h4 class="text-center">Register for an Account</h4>

                        <v-row align="center" justify="center">
                          <v-col cols="12" sm="8">
                            <!-- Name -->
                            <v-text-field
                              v-model="name"
                              :counter="10"
                              :rules="[
                                (v) => !!v || 'Required',
                                (v) =>
                                  (v && v.length <= 10) ||
                                  'Name must be less than 10 characters',
                              ]"
                              label="Name"
                              required
                              outlined
                              dense
                              color="blue"
                              autocomplete="false"
                              class="mt-4"
                            ></v-text-field>
                            <!-- Email -->
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
                            ></v-text-field>
                            <!-- Password -->
                            <v-text-field
                              v-model="password"
                              :counter="10"
                              :rules="[
                                (v) => !!v || 'Required',
                                (v) =>
                                  (v && v.length <= 10) ||
                                  'Password must be less than 10 characters',
                              ]"
                              label="Password"
                              required
                              outlined
                              dense
                              color="blue"
                              autocomplete="false"
                              type="password"
                            ></v-text-field>

                            <!-- Register Btn -->
                            <v-btn
                              color="blue"
                              class="mr-4"
                              dark
                              block
                              tile
                              @click="register()"
                            >
                              <span v-if="!loading">Register</span>
                              <v-progress-circular
                                v-else
                                indeterminate
                                color="primary"
                              ></v-progress-circular>
                            </v-btn>

                            <!-- Error Alert -->
                            <v-alert
                              class="mt-3"
                              v-show="errorAlert"
                              dense
                              type="error"
                            >
                              Register Failed!
                            </v-alert>
                          </v-col>
                        </v-row>
                      </v-form>
                    </v-card-text>
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
  name: "register",

  components: {},

  data() {
    return {
      registerForm: false,

      name: "",
      email: "",
      password: "",
      // name: "Mg Mg",
      // email: "test@gmail.com",
      // password: "1111",

      errorAlert: false,
      loading: false,
    };
  },

  async created() {},

  methods: {
    async register() {
      if (this.$refs.registerForm.validate()) {
        this.errorAlert = false;

        try {
          this.loading = true;

          // API Call
          const resp = await utils.http.post("/user/register", {
            name: this.name,
            gmail: this.email,
            password: this.password,
          });
          if (resp.status === 200) {
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
