import axios from 'axios'
console.log(process.env)
// console.log(localStorage.getItem('X-ACCESS-TOKEN'))
const Instance = axios.create({
  baseURL: process.env.VUE_APP_BASEURL,
  timeout: 600000,
  // headers: {
  //   'X-ACCESS-TOKEN': localStorage.getItem('X-ACCESS-TOKEN') || ''
  // }
})
// let __retryCount = 1
// let retry = 2
// let retryDelay = 2000
// let timeoutCount = 0

Instance.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么
  // window.vm.showLoading = true
  console.log(config)
  return config
}, function (error) {
  // window.vm.showLoading = false
  // 对请求错误做些什么
  return Promise.reject(error)
})

// 添加响应拦截器
Instance.interceptors.response.use(function (response) {
  // 对响应数据做点什么
  // window.vm.showLoading = false
  console.log(response)
  return response
}, function (error) {
  // 对响应错误做点什么
  // window.vm.showLoading = false
  // let config = error.config
  // if (error.response.status === 403 || error.response.status === 505) {
  // }
  // if (error.response.status === 500) {
  // }
  // if (error.response.status === 401) {
  // }
  return Promise.reject(error)
})
export default Instance
