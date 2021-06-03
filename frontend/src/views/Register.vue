<!-- 회원가입 -->
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
              Signup
            </h4>
            <v-form
              style="margin: 50px 50px;"
              ref="form"
              v-model="valid"
              lazy-validation
            >
              <v-text-field
                class="pt-3 pb-3"
                v-model="member.id"
                :counter="8"
                :rules="idRules"
                label="아이디"
                required
                hint="영문, 숫자 2~8자 이내"
                type="text"
                prepend-icon="mdi-face"
              ></v-text-field>
              <v-text-field
                class="pt-3 pb-3"
                :rules="passwordRules"
                required
                v-model="member.password"
                label="비밀번호"
                type="password"
                :counter="20"
                prepend-icon="mdi-lock"
                hint="영문, 숫자 조합 8~20자 이내"
              ></v-text-field>
              <v-text-field
                class="pt-3 pb-3"
                :rules="validatePasswordRules"
                required
                v-model="validatePassword"
                :counter="20"
                label="비밀번호 확인"
                type="password"
                prepend-icon="mdi-lock"
              ></v-text-field>
              <v-text-field
                class="pt-3 pb-3"
                v-model="member.name"
                :counter="10"
                :rules="nameRules"
                label="이름"
                required
                type="text"
                prepend-icon="mdi-account"
              ></v-text-field>
              <v-text-field
                class="pt-3 pb-3"
                v-model="member.email"
                :counter="30"
                :rules="emailRules"
                label="이메일"
                required
                type="email"
                hint="이메일 주소 입력"
                prepend-icon="mdi-email"
              ></v-text-field>
              <v-btn
                style="width:100%; height:40px; margin-top: 30px;"
                color="#64FE2E"
                dark
                @click="joinRequest"
                >회원가입</v-btn
              >
            </v-form>
            <div style="width: 100%; margin-left:20%">
              <a style="color : #64FE2E">이미 회원이신가요?</a>
              <v-btn style="width:30%" @click="goLogin" text color="#64FE2E">
                로그인
              </v-btn>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  bodyClass: "login-page",
  data() {
    return {
      valid: true,
      idRules: [
        (v) => !!v || "아이디를 입력해주세요",
        (v) => (v && v.length >= 2 && v.length <= 8) || "2~8자리 입력"
      ],
      passwordRules: [
        (v) => !!v || "비밀번호를 입력해주세요",
        // (v) => (v && v.length >= 8 && v.length <= 20) || "8~20자리 입력",
        (v) =>
          /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d$@$!%*#?&]{8,20}$/.test(v) ||
          "문자, 숫자 조합 8~20자리 입력",
      ],
      validatePasswordRules: [
        (v) => !!v || "비밀번호를 입력해주세요",
        (v) => v === this.member.password || "비밀번호 불일치"
      ],
      nameRules: [
        (v) => !!v || "이름을 입력해주세요",
        (v) => (v && v.length <= 10) || "10자리 이하 입력"
      ],
      emailRules: [
        (v) => !!v || "이메일을 입력해주세요",
        (v) => /.+@.+\..+/.test(v) || "유효한 이메일이 아닙니다"
      ],
      member: {
        id: "",
        password: "",
        name: "",
        email: "",
      },
      validatePassword: "",
    };
  },
  methods: {
    validate() {
      this.$refs.form.validate();
    },
    joinRequest() {
      if (this.$refs.form.validate()) {
        axios({
          method: "post",
          url:
            "http://ec2-3-135-227-210.us-east-2.compute.amazonaws.com:8089/user/signup",
          headers: {
            contentType: "application/json"
          },
          data: this.member,
        })
          .then((res) => {
            alert(res.data.data);
            if (res.data.status == true) {
              this.$router.replace(`/`);
            }
            if (res.data.status == false) {
              location.reload();
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    goLogin() {
      this.$router.push("/login");
    },
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg")
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    },
  },
};
</script>
<style lang="css"></style>
