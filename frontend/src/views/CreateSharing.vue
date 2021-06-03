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
            <div class="md-layout-item md-size-50 mx-auto">
              <div style="text-align:center">
                <h2>나눔 글쓰기</h2>
              </div>
            </div>
            <!-- <div>gdgdg</div> -->

            <!-- <div style="width:800px"> -->
            <div style="width:90%;margin-left:3%">
              <v-text-field
                label="Title"
                :rules="rules"
                hide-details="auto"
                v-model="title"
                style="width:90%;margin-left:3%"
              >
              </v-text-field>
              <div id="app">
                <!-- <v-app id="inspire"> -->
                <v-file-input
                  multiple
                  label="사진 업로드"
                  id="img"
                  style="width:20%;margin-left:3%"
                ></v-file-input>
                <!-- </v-app> -->
              </div>
              <p style="width:90%;text-align:center;margin-left:30px">
                <v-textarea
                  solo
                  name="input-7-4"
                  label="Contents"
                  v-model="contents"
                  id="contents"
                  height="500"
                ></v-textarea>
              </p>
              <p style="width:90%;text-align:center">
                <v-btn @click="create()" style="width:90%; margin-left:4%;"
                  >글쓰기</v-btn
                >
              </p>
            </div>
            <!-- </div> -->
            <br />
            <!-- <input multiple="multiple" type="file" id="img" style="margin-left:30px" /> -->
            <!-- <div id="app">
              <v-app id="inspire">
                <v-file-input multiple label="File input"></v-file-input>
              </v-app>
            </div> -->

            <!-- <UploadPage></UploadPage> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
// import UploadPage from "./components/UploadPage.vue";
const SERVER_URL = process.env.VUE_APP_SERVER_URL2;
export default {
  data() {
    return {
      title: "",
      contents: "",
      form: [],
      list: [],
      imgs: [],
    };
  },

  components: {
    // UploadPage,
  },
  bodyClass: "profile-page",

  props: {
    header: {
      type: String,
      default: require("@/assets/img/city-profile.jpg"),
    },
    img: {
      type: String,
      default: require("@/assets/img/faces/christian.jpg"),
    },
  },
  methods: {
    create() {
      const item = {
        title: this.title,
        contents: this.contents,
      };
      const frm = new FormData();
      // var img = document.getElementById('img');
      var ins = document.getElementById("img").files.length;
      console.log(ins + "sdfsd");
      if (ins != 0) {
        for (var x = 0; x < ins; x++) {
          frm.append("file", document.getElementById("img").files[x]);
        }
        axios
          .post(`${SERVER_URL}/file/upload/`, frm)
          .then((res) => {
            // console.log(res.data.message)
            // item.push({img: SERVER_URL + "/file/read/" + res.data.message})
            this.list = res.data;

            for (var i = 0; i < this.list.length; i++) {
              this.imgs.push(SERVER_URL + "/file/read/" + this.list[i]);
            }
            item["img"] = this.imgs;
            // DB에 저장
            axios
              .post(`${SERVER_URL}/sharing/create`, item, {})
              .then((response) => {
                confirm("작성하시겠습니까?");
                alert("글쓰기 성공!");
                this.$router.push("Sharing");
              })
              .catch(function(error) {
                console.log(err);
              });
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        // DB에 저장
        axios
          .post(`${SERVER_URL}/board/create`, item, {})
          .then((response) => {
            confirm("작성하시겠습니까?");
            alert("글쓰기 성공!");
            this.$router.push("Board");
          })
          .catch(function(error) {
            console.log(err);
          });
      }
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

  #contents {
    width: 400px;
    height: 500px;
  }
}
</style>
