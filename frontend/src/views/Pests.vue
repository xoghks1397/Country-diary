<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <div class="md-layout">
        <div class="md-layout-item md-size-25 mx-auto">
          <md-field>
            <label>Search</label>
            <md-input
              v-model="cropName"
              type="text"
              @keypress.enter="getPestsList(cropName)"
            ></md-input>
          </md-field>
          <md-button class="md-success" @click="getPestsList(cropName)"
            >Search</md-button
          >
        </div>
      </div>
      <h2 class="text-center">
        {{ name }}
      </h2>
      <div class="section profile-content">
        <div class="container">
          <div class="md-layout">
            <div
              class="md-layout-item md-size-25 md-xsmall-size-100"
              v-for="pest in pestslist"
              v-bind:key="pest"
            >
              <div class="avatar">
                <img
                  :src="
                    require(`@/assets/img/${cropName}/${pest.pestName}.jpg`)
                  "
                  alt="Rounded Image"
                  class="rounded"
                />
              </div>
              <div class="md-layout-item text-center">
                <md-button
                  @click="move(pest.pestName)"
                  class="md-simple md-success md-lg"
                >
                  {{ pest.pestName }}
                </md-button>
              </div>
            </div>
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
      pestslist: [],
      cropName: "",
      name: "병충해 종류를 확인할 작물을 입력해주세요"
    };
  },
  methods: {
    getPestsList(crop) {
      console.log(crop);
      this.name = this.cropName;
      axios({
        method: "get",
        url: `${SERVER_URL}/pests/${crop}/`
      })
        .then((res) => {
          this.pestslist = [];
          for (var i = 0; i < res.data.length; i++) {
            this.pestslist.push(res.data[i]);
          }
          console.log(res.data);
          if (res.data.length == 0)
            this.name = "해당 작물에 대한 정보가 없습니다.";
        })
        .catch((error) => {
          console.log(error);
        });
    },
    move(pestName) {
      this.$router.push("pestsDetail/" + this.cropName + "/" + pestName);
    },
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/city-profile.jpg")
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
