<template>
  <div class="wrapper">
    <parallax class="page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <!-- <img :src="leaf4" alt="leaf4" class="leaf4" v-show="leafShow" />
            <img :src="leaf3" alt="leaf3" class="leaf3" v-show="leafShow" />
            <img :src="leaf2" alt="leaf2" class="leaf2" v-show="leafShow" />
            <img :src="leaf1" alt="leaf1" class="leaf1" v-show="leafShow" /> -->
            <div class="brand">
              <!-- <h1>Farm Diary</h1> -->
              <!-- <h3>도시농부들을 위한 길라잡이</h3> -->
            </div>
          </div>
        </div>
      </div>
    </parallax>

    <div class="main main-raised" style="padding-bottom:100px;margin-top:0px">
      <br /><br /><br />
      <div id="testBugMark">
        <div class="tim-typo">
          <div
            class="title"
            style="font-size:18px;width:400px;margin-left:-20%;margin-top:30px"
          >
            당신의 텃밭작물을 AI를 통해 진단 받으세요!
          </div>
          <router-link to="/pestsDiagnosis">
            <v-btn id="bugBtn" style="margin-left:97%;margin-top:-118px"
              >진단받기</v-btn
            >
          </router-link>
        </div>
        <!-- <v-btn id ="bugBtn" style=""
          >진단받기</v-btn
        > -->
      </div>

      <div id="bugTable">
        <span id="alert">
          경보
        </span>
        <span id="warning" style="color:black">
          주의보
        </span>
        <span id="forecast">
          예보
        </span>
      </div>
      <div id="weather">
        <h3>날씨 정보</h3>
        <!-- <v-img :src="weatherImg" height="200px" width="150px"></v-img> -->
        <!-- <i class="wi wi-day-sunny" style="width:150px"></i> -->
        <!-- <i class="wi wi-owm-200 width:500px"></i>
          -->
        <vue-weather api-key="8b2333e996db9ac6859d281721dd4987" units="uk" />
      </div>
      <v-simple-table style="margin-top:130px;width:50%;margin-left:2%">
        <colgroup>
          <col width="33%" />
          <col width="33%" />
          <col width="33%" />
        </colgroup>
        <tbody>
          <tr v-for="item in bugInfo" :key="item.yellow">
            <td></td>
            <td>{{ item.yellow }}</td>
            <td>{{ item.green }}</td>
          </tr>
        </tbody>
      </v-simple-table>
      <div v-if="list.length >= 5">
        <h3 style="margin-left:3%;padding-right:70%;margin-top:70px">
          나눔 게시판
        </h3>
        <div
          @click="goToSharing()"
          style="margin-top:-30px ; margin-left:42% ; cursor:pointer ;"
        >
          더보기
        </div>

        <v-carousel
          :show-arrows="false"
          style="border-radius:30px;width:40%;margin-left:5%;margin-top:30px;height:400px;border: 0.1rem solid grey; "
        >
          <v-carousel-item
            v-for="n in 5"
            :key="n"
            :src="list[n - 1].img[0]"
            @click="goToReadSharing(list[n - 1].id)"
            style="cursor:pointer ;"
          ></v-carousel-item>
        </v-carousel>
      </div>
      <div style="margin-top:-540px;margin-left:53%" v-if="list.length >= 5">
        <h3 style="margin-left:3%;margin-top:70px">오늘의 텃밭 소식</h3>
        <div
          @click="goToBoard()"
          style="margin-top:-30px ; margin-left:77% ; cursor:pointer ;"
        >
          더보기
        </div>
        <v-carousel
          :show-arrows="false"
          style="border-radius:30px;width:80%;margin-left:5%;margin-top:30px;height:400px;border: 0.1rem solid grey; "
        >
          <v-carousel-item
            v-for="n in 1"
            :key="n"
            :src="list2[n - 1].img"
            @click="goToReadBoard(list2[n - 1].id)"
            style="cursor:pointer ;"
          ></v-carousel-item>
        </v-carousel>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import VueWeather from "vue-weather-widget";
// import Navigation from "./components/NavigationSection";
// import SmallNavigation from "./components/SmallNavigationSection";
// import Tabs from "./components/TabsSection";
// import NavPills from "./components/NavPillsSection";
// import Notifications from "./components/NotificationsSection";
// import TypographyImages from "./components/TypographyImagesSection";
// import JavascriptComponents from "./components/JavascriptComponentsSection";
// import { LoginCard } from "@/components";
// import { Badge } from "@/components";
const SERVER_URL = process.env.VUE_APP_SERVER_URL2;
export default {
  components: {
    // Navigation,
    // SmallNavigation,
    // Tabs,
    // NavPills,
    // Notifications,
    // TypographyImages,
    // JavascriptComponents,
    // LoginCard
    // Badge
    VueWeather,
  },
  name: "index",
  bodyClass: "index-page",
  props: {
    image: {
      type: String,
      default: require("@/assets/img/facebook_cover_photo_2.png")
    },
    leaf4: {
      type: String,
      default: require("@/assets/img/leaf4.png")
    },
    leaf3: {
      type: String,
      default: require("@/assets/img/leaf3.png")
    },
    leaf2: {
      type: String,
      default: require("@/assets/img/leaf2.png")
    },
    leaf1: {
      type: String,
      default: require("@/assets/img/leaf1.png")
    },
    signup: {
      type: String,
      default: require("@/assets/img/city.jpg")
    },
    landing: {
      type: String,
      default: require("@/assets/img/landing.jpg")
    },
    profile: {
      type: String,
      default: require("@/assets/img/profile.jpg")
    },
  },
  data() {
    return {
      firstname: null,
      email: null,
      password: null,
      leafShow: false,
      weatherImg: "",
      list: [],
      list2: [],
      page: 1,
      bugInfo: [
        {
          yellow: "(채소-마늘) 뿌리응애 ",
          green: "(채소-딸기)흰가루병"
        },
        {
          yellow: "(채소-마늘) 잎마름병 ",
          green: "(채소-상추)잿빛곰팡이병"
        },
        {
          yellow: "(채소-마늘) 흑색썩음균핵병 ",
          green: "(채소-오이)꽃노랑총채벌레"
        },
        {
          yellow: "(채소-양파) 노균병 ",
          green: "(채소-오이)대만총채벌레"
        },
      ],
    };
  },
  created() {
    // this.readWeather();
    this.readSharing();
    this.readBoard();
  },
  methods: {
    leafActive() {
      if (window.innerWidth < 768) {
        this.leafShow = false;
      } else {
        this.leafShow = true;
      }
    },
    // async readWeather() {
    //   try {
    //     const resp = await axios.get(
    //       `http://api.openweathermap.org/data/2.5/weather?q=seoul&appid=8b2333e996db9ac6859d281721dd4987`,
    //       {}
    //     );
    //     console.log(resp);
    //     console.log('현재온도 : ' + (resp.data.main.temp - 273.15));
    //     console.log('현재습도 : ' + resp.data.main.humidity);
    //     console.log('날씨 : ' + resp.data.weather[0].main);
    //     console.log('상세날씨설명 : ' + resp.data.weather[0].description);
    //     console.log('날씨 이미지 : ' + resp.data.weather[0].icon);
    //     console.log('바람   : ' + resp.data.wind.speed);
    //     console.log('나라   : ' + resp.data.sys.country);
    //     console.log('도시이름  : ' + resp.data.name);
    //     console.log('구름  : ' + resp.data.clouds.all + '%');
    //     this.weatherImg =
    //       'http://openweathermap.org/img/w/' +
    //       resp.data.weather[0].icon +
    //       '.png';
    //   } catch (err) {
    //     console.log(err);
    //   }
    // },

    goToSharing() {
      this.$router.push("sharing");
    },
    goToBoard() {
      this.$router.push("board");
    },

    async readSharing() {
      try {
        const res = await axios.get(`${SERVER_URL}/sharing/read`, {
          params: { type: "", word: this.page }
        });
        this.list = res.data;
        console.log(res.data[1].title + "?");
        // this.hashKey = res.data.vote.hashKey;
        // const idx = res.data.vote.contractAddress * 1;
        // await this.getData(idx);

        // this.n = idx;
      } catch (err) {
        console.log(err);
      }
    },

    async readBoard() {
      console.log("sdfsd");
      try {
        const res = await axios.get(`${SERVER_URL}/board/read`, {
          params: { type: "", word: this.page }
        });
        this.list2 = res.data;
        console.log(res.data[1].title + "?");
        // this.hashKey = res.data.vote.hashKey;
        // const idx = res.data.vote.contractAddress * 1;
        // await this.getData(idx);

        // this.n = idx;
      } catch (err) {
        console.log(err);
      }
    },

    goToReadSharing(id) {
      this.$router.push("Sharing/" + id);
    },

    goToReadBoard(id) {
      this.$router.push("Board/" + id);
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`
      };
    },
    signupImage() {
      return {
        backgroundImage: `url(${this.signup})`
      };
    },
  },
  mounted() {
    this.leafActive();
    window.addEventListener("resize", this.leafActive);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.leafActive);
  },
};
</script>
<style lang="scss">
.section-download {
  .md-button + .md-button {
    margin-left: 5px;
  }
}

@media all and (min-width: 991px) {
  .btn-container {
    display: flex;
  }
}

#testBugMark {
  display: flex;
  background-color: #66bb6a;
  width: 50%;
  margin-left: 26%;
  // margin-top: 100px;
  height: 80px;
  border-radius: 100px;
}

#alert {
  background-color: red;
}
#warning {
  background-color: yellow;
}
#forecast {
  background-color: #66bb6a;
}

#bugTable span {
  width: 15%;
  height: 40px;
  border-radius: 100px;
  float: left;
  margin-left: 1.5%;
  // margin-right: 5px;
  margin-top: 80px;
  text-align: center;
  padding-top: 7px;
  color: white;
}

#weather {
  float: right;
  margin-top: 50px;
  margin-right: 5%;
  width: 40%;
  height: 200px;
}

#bugBtn {
  font-size: 25px;
  // margin: right -300px;;
  // padding-right:-300px;
  // margin-top:0.9%;
  background-color: #cddbdb;
}
</style>
