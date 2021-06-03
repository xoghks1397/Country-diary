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
          <!-- <div v-for="(item, index) in list" :key="index">
            dsf{{ item.title }}
          </div>
          <div>gdgdg {{ title }}</div>
          <br /><br /><br /><br /><br /><br /> -->
          <div id="board">
            <h2 style="text-align:center">오늘의 전원일기 게시판</h2>
            <div style="text-align:right">
              <v-btn @click="create">글 작성</v-btn>
            </div>
            <v-simple-table>
              <template v-slot:default>
                <thead>
                  <tr>
                    <!-- <th class="text-left">no</th> -->
                    <th class="text-left">제목</th>
                    <th class="text-left">아이디</th>
                    <th class="text-left">날짜</th>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    v-for="(item,idx) in list"
                    :key="item.name"
                    @click="read(item.id)"
                    style="cursor:pointer ;"
                  >
                    <!-- <td>1</td> -->
                    <td>{{ item.title }}</td>
                    <td>{{nickname[idx]}}</td>
                    <td>{{ item.date }}</td>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
          </div>
        </div>
        <!-- <pagination
            class="pagination-info"
            v-model="infoPagination"
            with-text
            :page-count="5"
          >
          </pagination> -->

        <v-app id="inspire">
          <div class="text-center">
            <v-pagination
              v-model="page"
              :total-visible="7"
              :length="total / 10 + 1"
              @input="getList()"
            ></v-pagination>
          </div>
        </v-app>
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
      nickname : ["하이룽","미미광어","팡대","모히또","호이짜","사랑해유","와이키키","나스타","미미시스터","나천재","니코니코니","싸브레","솔라씨","카키","핫썸머","코피퐝"],
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
    }
  },
  methods: {
    async getList() {
      console.log("sdfsd");
      try {
        const res = await axios.get(`${SERVER_URL}/board/read`, {
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
        const res = await axios.get(`${SERVER_URL}/board/count`);
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
      this.$router.push("createBoard");
    },
    read(id) {
      // alert("sdf");
      this.$router.push("Board/" + id);
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
