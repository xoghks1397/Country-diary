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
              <div style="text-align:center"></div>
            </div>

            <br />
            <div style="width:90%;text-align:center;margin-top:20px">
              <h2>{{ board.title }}</h2>
            </div>
            <!-- </div> -->
            <!-- <img :src="board.img" > -->
            <div id="detailContents">
              <!-- <div style="text-align:center;margin-top:20px;margin-bottom:20px"> -->
              <div id="app" style="min-height:10vh">
                <v-app id="inspire" style="min-height:10vh">
                  <v-carousel
                    style="border-radius:30px;width:600px;margin-left:20%;margin-top:40px"
                  >
                    <v-carousel-item
                      v-for="(item, i) in board.img"
                      :key="i"
                      :src="item"
                    ></v-carousel-item>
                  </v-carousel>
                  <!-- <h2 style="padding-top:90px">{{ board.title }}</h2> -->

                  <div style="margin-bottom:0px ; padding-top:90px">
                    {{ board.contents }}
                  </div>
                </v-app>
              </div>

              <!-- <img :src="board.img[0]" style="width:30;height:30;" /> -->
              <!-- </div> -->
              <div
                class="my-2"
                style="width:30%;margin-top:50px;margin-bottom:20px;margin-left:35%"
              >
                <router-link to="/sharing">
                  <v-btn x-large color="success" dark width="90%">
                    <i class="material-icons">dashboard</i> 목록
                  </v-btn></router-link
                >
              </div>
            </div>
            <!-- <p style="width:90%;"> -->
            <!-- <v-btn
                style="width:10%; margin-left:79%;margin-top:10px;margin-right:20px"
                >수정</v-btn
              >
              <v-btn style="width:10%; margin-left:90%;margin-top:-60px"
                >삭제</v-btn
              > -->
            <!-- </p> -->

            <div style="margin-top:-130px">
              <div v-for="(item, index) in reply" :key="item.id" id="reply">
                <div @click="openText(index)" style="cursor:pointer">
                  <div>{{ item.text }}</div>
                  <div style="font-size:7px">{{ item.date }}</div>
                  <div v-for="comment in item.reReply" :key="comment.id">
                    └ {{ comment.text }}
                    <div style="font-size:7px;margin-left:20px">
                      {{ comment.date }}
                    </div>
                  </div>
                  <!-- <div>{{item.reReply[0].text}}</div> -->
                </div>

                <div v-if="tmp">
                  <div
                    id="app"
                    style="width:92%;margin-left:-14px;"
                    v-if="re_reply[index]"
                  >
                    <v-container fluid>
                      <v-textarea
                        label="댓글달기"
                        no-resize
                        rows="1"
                        :value="value"
                        style="width:92%;margin-top:-15px "
                        v-model="reReply"
                      ></v-textarea>
                      <v-btn
                        style="width:8%;margin-top:-58px;margin-left:952px"
                        @click="createReReply(item.id)"
                      >
                        입력
                      </v-btn>
                    </v-container>
                  </div>
                </div>
              </div>

              <div id="app" style="width:100%;margin-left:-14px">
                <v-container fluid>
                  <v-textarea
                    label="댓글달기"
                    no-resize
                    rows="1"
                    :value="value"
                    style="width:92%"
                    v-model="createdReply"
                  ></v-textarea>
                  <v-btn
                    style="width:8%;margin-top:-58px;margin-left:952px"
                    @click="createReply()"
                  >
                    입력
                  </v-btn>
                </v-container>
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
const SERVER_URL = process.env.VUE_APP_SERVER_URL2;
export default {
  data() {
    return {
      board: [],
      reply: [],
      createdReply: "",
      re_reply: [],
      tmp: false,
      reReply: ""
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
  created() {
    this.readBoard();
    this.readReply();
  },
  methods: {
    async readBoard() {
      try {
        const res = await axios.get(`${SERVER_URL}/sharing/read`, {
          params: { type: "id", word: this.$route.params.id }
        });
        this.board = res.data[0];
        //console.log(res.data[0]+ 'ㅋㅋㅋ?');
        // this.hashKey = res.data.vote.hashKey;
        // const idx = res.data.vote.contractAddress * 1;
        // await this.getData(idx);

        // this.n = idx;
      } catch (err) {
        console.log(err);
      }
    },

    async readReply() {
      try {
        const res = await axios.get(`${SERVER_URL}/reply/read`, {
          params: { boardId: this.$route.params.id }
        });
        this.reply = res.data;
        console.log("sdf" + this.reply.length);
        for (var i = 0; i < this.reply.length; i++) {
          this.re_reply.push(false);
        }
        console.log(this.re_reply[0] + "fhtr?");
        // this.hashKey = res.data.vote.hashKey;
        // const idx = res.data.vote.contractAddress * 1;
        // await this.getData(idx);

        // this.n = idx;
      } catch (err) {
        console.log(err);
      }
    },

    createReply() {
      this.form = {
        boardId: this.$route.params.id,
        text: this.createdReply,
        reReply: [],
      };
      axios
        .post(`${SERVER_URL}/reply/create`, this.form, {})
        .then((response) => {
          // confirm("작성하시겠습니까?");
          // alert('글쓰기 성공!');
          location.reload();
        })
        .catch(function(error) {});
    },

    createReReply(index) {
      this.form = {
        replyId: index,
        text: this.reReply
      };
      axios
        .put(`${SERVER_URL}/reply/updateReReply`, this.form, {})
        .then((response) => {
          // confirm("작성하시겠습니까?");
          // alert('글쓰기 성공!');
          location.reload();
        })
        .catch(function(error) {});
    },

    openText(index) {
      console.log(this.re_reply[index] + "sdfsdfsdfsdf");
      if (this.re_reply[index]) {
        this.re_reply[index] = false;
        this.tmp = false;
      } else {
        this.re_reply[index] = true;
        this.tmp = true;
      }
    }
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
#detailContents {
  border-bottom: 1px solid grey;
  border-top: 1px solid grey;
  width: 90%;
  margin-bottom: 200px;
  // height: 400px;
}
#reply {
  border-bottom: 1px solid rgb(185, 181, 181);
  width: 90%;
  margin-bottom: 10px;
}
</style>
