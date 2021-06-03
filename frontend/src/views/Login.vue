<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
            style="backgroundColor : white"
          >
            <h4 slot="title" class="card-title" style="text-align : center">
              Login
            </h4>
            <v-form
              style="margin: 50px 40px"
              ref="form"
              v-model="valid"
              lazy-validation
            >
              <v-text-field
                v-model="user.id"
                :rules="idRules"
                label="아이디"
                required
                type="text"
                prepend-icon="mdi-account"
              ></v-text-field>
              <v-text-field
                :rules="passwordRules"
                required
                v-model="user.password"
                label="비밀번호"
                type="password"
                prepend-icon="mdi-lock"
                @keypress.enter="loginRequest"
              ></v-text-field>
              <v-btn
                style="width: 100%; margin-top: 45px; padding: 25px 0"
                color="#64FE2E"
                dark
                @click="loginRequest"
                >로그인</v-btn
              >
              <div style="width: 100%; margin-top: 30px">
                <v-btn
                  style="width: 100%"
                  @click="goRegister"
                  text
                  color="#64FE2E"
                >
                  회원가입
                </v-btn>
              </div>
            </v-form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import VueRouter from "vue-router";
Vue.use(VueRouter);
export default {
  components: {},
  bodyClass: "login-page",
  data() {
    return {
      valid: true,
      idRules: [
        (v) => !!v || "아이디을 입력해주세요",
        (v) => !!/^[a-z0-9]{4,10}$/.test(v) || "유효한 아이디가 아닙니다",
      ],
      passwordRules: [
        (v) => !!v || "비밀번호를 입력해주세요",
        // (v) => (v && v.length >= 8 && v.length <= 20) || "8~20자리 입력",
        (v) =>
          /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d$@$!%*#?&]{8,20}$/.test(v) ||
          "문자, 숫자 조합 8~20자리 입력",
      ],
      user: {
        id: "",
        password: "",
      },
      message: "",
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    },
  },
  methods: {
    loginRequest() {
      this.$store
        .dispatch("LOGIN", this.user)
        .then(() => {
          if (this.$store.getters.getLoginChk == true) {
            alert(this.$store.getters.getUserName + "님 환영합니다.");
            this.$router.replace(`/`);
            location.reload();
          } else {
            alert(this.$store.getters.getMessage);
            this.user.password = "";
          }
        })
        .catch(({ message }) => (this.msg = message));
    },

    goRegister() {
      this.$router.replace(`/register`);
    },
  },
};
</script>

<style lang="css"></style>
