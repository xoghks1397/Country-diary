<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-50 mx-auto"></div>
          </div>

          <div id="board">
            <h2 style="text-align:center">나눔 게시판</h2>
            <div style="text-align:right">
              <v-btn @click="create">글 작성</v-btn>
            </div>
          </div>
        </div>

        <div
          style="
          width: 100%;
          display: flex;
          flex-wrap: wrap;
          justify-content: center;
          padding-left:17%;
          padding-right:17%;
          margin-top:30px;
        "
        >
          <div
            v-for="item in list"
            :key="item.name"
            @click="read(item.id)"
            style="width:25% ; margin-bottom:50px ; "
          >
            <v-img
              :src="item.img[0]"
              height="220"
              width="220"
              style="border-radius:20px;cursor:pointer;border: 0.1rem solid grey; "
            >
            </v-img>
            <div style="margin-left:100;cursor:pointer ;">
              {{ item.title }}
            </div>
          </div>
        </div>
        <!-- <v-app id="inspire">
            <div class="text-center">
              <v-pagination
                v-model="page"
                :total-visible="7"
                :length="total/10 + 1"
                @input="getList()"
              ></v-pagination>
            </div>
            
          </v-app> -->
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
// import { Pagination } from "@/components";
const SERVER_URL = process.env.VUE_APP_SERVER_URL2;
export default {
  components: {
    // Pagination
  },
  data() {
    return {
      title: "ㅋㅋ",
      list: [],
      infoPagination: 3,
      page: 1,
      total: 0,
    };
  },
  created() {
    this.getTotal();
    this.getList();
  },
  components: {},
  bodyClass: "profile-page",

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
  methods: {
    async getList() {
      console.log("sdfsd");
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
    async getTotal() {
      try {
        const res = await axios.get(`${SERVER_URL}/sharing/count`);
        this.total = res.data;
        console.log("토탈 : " + this.total);
        // this.hashKey = res.data.vote.hashKey;
        // const idx = res.data.vote.contractAddress * 1;
        // await this.getData(idx);

        // this.n = idx;
      } catch (err) {
        console.log(err);
      }
    },

    create() {
      this.$router.push("createSharing");
    },
    read(id) {
      // alert("sdf");
      this.$router.push("Sharing/" + id);
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

.profile-tabs::v-deep {
  .md-card-tabs .md-list {
    justify-content: center;
  }

  [class*="tab-pane-"] {
    margin-top: 3.213rem;
    padding-bottom: 50px;

    img {
      margin-bottom: 2.142rem;
    }
  }
}
</style>
