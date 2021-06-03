<template>
  <div class="example-drag">
    <div v-show="$refs.upload && $refs.upload.dropActive" class="drop-active">
      <h3>Drop files to upload</h3>
    </div>
    <template v-if="files.length">
      <v-data-table 
        dense 
        :headers="headers" 
        :items="files" 
        item-key="name" 
        class="elevation-1"
        hide-default-footer
      >
      </v-data-table>
      <v-btn
        class="ma-2"
        style="padding:0px;"
        color="info"
        @click="uploadStart()"
      >
        <v-icon style="margin-right:5px;">arrow_upward</v-icon>사진 등록
      </v-btn>
    </template>
    <template v-else>
      <FileUpload
        class="btn btn-primary"
        :multiple="true"
        :drop="true"
        :drop-directory="true"
        v-model="files"
        ref="upload"
        @input="onDrop()"
        id='img'
      >
      </FileUpload>
      <v-row>
        <v-col cols="12" sm="12" md="12">
          <div class="text-center p-5">
            <h4>Drop files anywhere to upload<br/>or</h4>
              <v-btn
                class="ma-2"
                style="padding:0px;"
                color="info"
              >
                <label for="file" style="padding:0px 8px;"><v-icon style="margin-right:5px;">add_circle</v-icon>Select Files</label>
              </v-btn>
          </div>
        </v-col>
      </v-row>
    </template>
  </div>
</template>

<script>
import FileUpload from "vue-upload-component";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  components: {
    FileUpload
  },
  data: ()=> ({
    files: [],
    headers: [
      { text: 'name', value: 'name' },
      { text: 'size', value: 'size' },
    ],
  }),
  methods: {
    onDrop(){
      console.log(this.files);
      console.log(this.$refs);
      const frm = new FormData();
      var img = document.getElementById('img');
      console.log("사진11" + img);
      console.log("사진" + img.files[0]);
      if (img.files.length != 0) {
        frm.append('file', img.files[0]);
        axios
          .post(`${SERVER_URL}/LSH/file/upload/`, frm)
          .then((res) => {
            // console.log(res.data.message)
            // item.push({img: SERVER_URL + "/file/read/" + res.data.message})
            item['img'] = SERVER_URL + '/LSH/file/read/' + res.data.message;
        })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    uploadStart(){
      //업로드 처리로직
    }
  }
}
</script>

<style>

.example-drag .drop-active {
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  position: fixed;
  z-index: 9999;
  opacity: .6;
  text-align: center;
  width:100%;
  background: #000;
}
.example-drag .drop-active h3 {
  margin: -.5em 0 0;
  position: absolute;
  top: 50%;
  left: 0;
  right: 0;
  -webkit-transform: translateY(-50%);
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
  font-size: 40px;
  color: #fff;
  padding: 0;
}
</style>