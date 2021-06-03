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
                <h2>전원일기 글쓰기</h2>
              </div>
            </div>
            <!-- <div>gdgdg</div> -->

            <!-- <div style="width:800px"> -->
            <p style="width:90%">
              <v-text-field
                label="Title"
                :rules="rules"
                hide-details="auto"
                v-model="title"
              >
              </v-text-field>
            </p>
            <!-- </div> -->
            <br />
            <input type="file" id="img" style="margin-left:30px" />
            <!-- <div id="app">
              <v-app id="inspire">
                <v-file-input multiple label="File input" id="img" ></v-file-input>
              </v-app>
            </div> -->

            <p style="width:90%;text-align:center;margin-top:20px">
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
              <v-btn @click="create()" style="width:90%; margin-left:0px;"
                >글쓰기</v-btn
              >
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL2;
export default {
  data() {
    return {
      title: "",
      contents: "",
      form: []
    };
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
    create() {
      const item = {
        title: this.title,
        contents: this.contents
      };
      const frm = new FormData();
      var img = document.getElementById("img");
      console.log("사진11" + img);
      console.log("사진" + img.files[0]);
      if (img.files.length != 0) {
        frm.append("file", img.files[0]);
        axios
          .post(`${SERVER_URL}/file/uploadBrd/`, frm)
          .then((res) => {
            // console.log(res.data.message)
            // item.push({img: SERVER_URL + "/file/read/" + res.data.message})
            item["img"] = SERVER_URL + "/file/read/" + res.data.message;

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
