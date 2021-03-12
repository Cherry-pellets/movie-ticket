import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'

import "@/assets/css/style.css";
import "@/assets/css/reset.css"
import 'element-ui/lib/theme-chalk/index.css';

window.getQueryString = function getQueryString (paramName) {
  var url = window.location.href
  var hashSpArr = url.split(paramName + '=')
  if (hashSpArr.length >= 2) {
    var result = hashSpArr[1].split('&')
    var result2 = result[0].split('#')
    return result2[0]
  } else {
    return null
  }
}
// If you use Swiper 6.0.0 or higher
Vue.config.productionTip = false

Vue.use(ElementUI)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
