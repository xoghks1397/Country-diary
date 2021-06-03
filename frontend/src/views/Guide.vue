<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <h2 class="text-center">
        텃밭 종류별 키우기 좋은 작물
      </h2>
      <div class="md-layout">
        <div class="md-layout-item text-center">
          <md-button
            @click="getCropsList(place[0])"
            class="md-simple md-success md-lg"
          >
            {{ place[0] }}
          </md-button>
        </div>
        <div class="md-layout-item text-center">
          <md-button
            @click="getCropsList(place[1])"
            class="md-simple md-success md-lg"
          >
            {{ place[1] }}
          </md-button>
        </div>
        <div class="md-layout-item text-center">
          <md-button
            @click="getCropsList(place[2])"
            class="md-simple md-success md-lg"
          >
            {{ place[2] }}
          </md-button>
        </div>
      </div>
      <div class="section profile-content">
        <div class="container">
          <div class="md-layout">
            <div
              class="md-layout-item md-size-25 md-xsmall-size-100"
              v-for="crop in cropslist"
              v-bind:key="crop"
            >
              <div class="avatar">
                <img
                  :src="require(`@/assets/img/${crop.name}.jpg`)"
                  alt="Rounded Image"
                  class="rounded"
                />
              </div>
              <div class="md-layout-item text-center">
                <md-button
                  @click="move(crop.name)"
                  class="md-simple md-success md-lg"
                >
                  {{ crop.name }}
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
      cropslist: [],
      place: ["베란다", "옥상정원", "주말농장"],
    };
  },
  methods: {
    getCropsList(place) {
      axios({
        method: "get",
        url: `${SERVER_URL}/guide/${place}/`
      })
        .then((res) => {
          this.cropslist = [];
          for (var i = 0; i < res.data.length; i++) {
            this.cropslist.push(res.data[i]);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    move(crop) {
      this.$router.push("crop/" + crop);
    },
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/city-profile.jpg"),
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
    this.getCropsList(this.place[0]);
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
