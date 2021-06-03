import Vue from "vue";
import Router from "vue-router";
import Index from "./views/Index.vue";
import Login from "./views/Login.vue";
import Profile from "./views/Profile.vue";
import Board from "./views/Board.vue";
import CreateBoard from "./views/CreateBoard.vue";
import ReadBoard from "./views/ReadBoard.vue";
import Guide from "./views/Guide.vue";
import Crop from "./views/Crop.vue";
import Pests from "./views/Pests.vue";
import PestsDetail from "./views/PestsDetail.vue";
import PestsDiagnosis from "./views/PestsDiagnosis.vue";
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";
import Sharing from "./views/Sharing.vue";
import CreateSharing from "./views/CreateSharing.vue";
import ReadSharing from "./views/ReadSharing.vue";
import Register from "./views/Register.vue";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "index",
      components: { default: Index, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/login",
      name: "login",
      components: { default: Login, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 }
      }
    },
    {
      path: "/profile",
      name: "profile",
      components: { default: Profile, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/board",
      name: "board",
      components: { default: Board, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 200 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/createBoard",
      name: "createBoard",
      components: {
        default: CreateBoard,
        header: MainNavbar,
        footer: MainFooter
      },
      props: {
        header: { colorOnScroll: 200 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/board/:id",
      name: "readBoard",
      components: {
        default: ReadBoard,
        header: MainNavbar,
        footer: MainFooter
      },
      props: {
        header: { colorOnScroll: 200 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/guide",
      name: "guide",
      components: { default: Guide, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/sharing",
      name: "sharing",
      components: { default: Sharing, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 200 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/crop/:cropName",
      name: "crop",
      components: { default: Crop, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/createSharing",
      name: "createSharing",
      components: {
        default: CreateSharing,
        header: MainNavbar,
        footer: MainFooter
      },
      props: {
        header: { colorOnScroll: 200 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/pests",
      name: "pests",
      components: { default: Pests, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/pestsDiagnosis",
      name: "pestsDiagnosis",
      components: {
        default: PestsDiagnosis,
        header: MainNavbar,
        footer: MainFooter
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/pestsDetail/:cropName/:pestName",
      name: "pestsDetail",
      components: {
        default: PestsDetail,
        header: MainNavbar,
        footer: MainFooter
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/sharing/:id",
      name: "readSharing",
      components: {
        default: ReadSharing,
        header: MainNavbar,
        footer: MainFooter
      },
      props: {
        header: { colorOnScroll: 200 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/register",
      name: "Register",
      components: { default: Register, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 200 },
        footer: { backgroundColor: "black" }
      }
    }
  ],
  scrollBehavior: to => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  }
});
