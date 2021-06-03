<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <h2 class="text-center">
        {{ cropName }}
      </h2>
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="tim-typo" v-if="cropsDetail.environment">
            <h3 class="title">
              재배 적응 환경
            </h3>
            <p class="text-muted" v-html="cropsDetail.environment"></p>
          </div>
          <div class="tim-typo" v-if="cropsDetail.preparation">
            <h3 class="title">
              본 밭 준비
            </h3>
            <p class="text-muted" v-html="cropsDetail.preparation"></p>
          </div>
          <div class="tim-typo" v-if="cropsDetail.plant">
            <h3 class="title">
              모종 아주심기
            </h3>
            <p class="text-muted" v-html="cropsDetail.plant"></p>
          </div>
          <div class="tim-typo" v-if="cropsDetail.manage">
            <h3 class="title">
              일반 관리
            </h3>
            <p class="text-muted" v-html="cropsDetail.manage"></p>
          </div>
          <div class="tim-typo" v-if="cropsDetail.harvest">
            <h3 class="title">
              수확 및 저장
            </h3>
            <p class="text-muted" v-html="cropsDetail.harvest"></p>
          </div>
          <div class="tim-typo" v-if="cropsDetail.nutri">
            <h3 class="title">
              영양소
            </h3>
            <p class="text-muted" v-html="cropsDetail.nutri"></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import { Tabs } from "@/components";
export default {
  components: {},
  bodyClass: "profile-page",
  data() {
    return {
      cropsDetail: [],
      cropName: ""
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/city-profile.jpg")
    },
    img: {
      type: String,
      default: require("@/assets/img/faces/christian.jpg")
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    },
  },
  created() {
    this.cropName = this.$route.params.cropName;
    axios({
      method: "get",
      url: `${SERVER_URL}/guide/crop/${this.cropName}/`
    })
      .then((res) => {
        this.cropsDetail = [];
        if (res.data.length != 0) this.cropsDetail = res.data[0];
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>

<style lang="scss" scoped>
.section {
  padding: 0;
}
</style>
