<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <h2 class="text-center">
        {{ pestName }}
      </h2>
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="tim-typo" v-if="pestsDetail.environment">
            <h3 class="title" v-if="pestsDetail.type == '병'">
              발생 환경
            </h3>
            <h3 class="title" v-if="pestsDetail.type == '충'">
              생태 정보
            </h3>
            <p class="text-muted" v-html="pestsDetail.environment"></p>
          </div>
          <div class="tim-typo" v-if="pestsDetail.descript">
            <h3 class="title" v-if="pestsDetail.type == '병'">
              증상 설명
            </h3>
            <h3 class="title" v-if="pestsDetail.type == '충'">
              피해 정보
            </h3>
            <p class="text-muted" v-html="pestsDetail.descript"></p>
          </div>
          <div class="tim-typo" v-if="pestsDetail.prevent">
            <h3 class="title">
              방제 방법
            </h3>
            <p class="text-muted" v-html="pestsDetail.prevent"></p>
          </div>
          <div class="tim-typo">
            <h3 class="title">
              사진 정보
            </h3>
            <img
              :src="require(`@/assets/img/${cropName}/${pestName}.jpg`)"
              alt="Rounded Image"
              class="rounded"
            />
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
      pestsDetail: [],
      pestName: "",
      cropName: "",
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/city-profile.jpg")
    },
    img: {
      type: String,
      default: require("@/assets/img/고구마/검은무늬병.jpg")
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
    this.pestName = this.$route.params.pestName;
    this.cropName = this.$route.params.cropName;
    axios({
      method: "get",
      url: `${SERVER_URL}/pests/detail/${this.cropName}/${this.pestName}/`
    })
      .then((res) => {
        this.pestsDetail = [];
        if (res.data.length != 0) {
          this.pestsDetail = res.data[0];
        }
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
.rounded {
  width: 300px;
  height: 150px;
  object-fit: fill;
}
</style>
