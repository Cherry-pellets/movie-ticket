// pages/home/index.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentTab: 0,
    swiperViewHeight: 0,
    indicatorDots: true,
    vertical: false,
    autoplay: true,
    interval: 3000,
    duration: 1200,
    bannerList: []
  },


  /**
   * 页面事件
   */
// 点击切换头部tab栏
  clickTab: function(e) {
    // console.log(e.target.dataset.current)
    this.setData({
      currentTab: e.target.dataset.current
    })
  },
// 滑动切换tab栏
swiperTab: function(e) {
  console.log(e.detail.current)
  this.setData({
    currentTab: e.detail.current
  })
},
// 轮播图
swiperchange: function() {

},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    //调用应用实例的方法获取全局数据
    //bannerList
    wx.request({
      url: app.globalData.url +'/home//getBannerList',
      method: 'GET',
      data: {},
      success: function (res) {
        that.setData({ bannerList: res.data.data })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})