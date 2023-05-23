import Vue from "vue";
import App from "./App.vue";
import router from "./routers/routers.js";

Vue.config.productionTip = false;
export const eventBus = new Vue({
  methods: {
    sendImg(img) {
      const clonedArr = [...img];
      // console.log(clonedArr)
      this.$emit('fileList', clonedArr);
    }
  }
});

new Vue({
  render: (h) => h(App),
  router,

}).$mount("#app");
