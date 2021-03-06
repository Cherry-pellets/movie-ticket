// pages/home/index.js
const app = getApp();
const util = require('../../utils/util.js')
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
    bannerList: [],
    itemsShow: [], //热映
    pageNumHost: 0,
    limit: 6,
    hasMoreHost: true,
    itemsPop: [],
    itemsWait: [],
    pageNumWait: 0,
  },


  /**
   * 页面事件
   */
  // 设置swiper高度
  setSwiperHeight: function(){
    var query = wx.createSelectorQuery();
    //选择id
    var that = this;
    var px1 = 208 / 750 * wx.getSystemInfoSync().windowWidth;
    var px2 = 376 / 750 * wx.getSystemInfoSync().windowWidth;
    query.selectAll('.swiperH').boundingClientRect(function (rect) {
      console.log(rect)
      var itemsLength = 0;
      if (that.data.currentTab == 0){
        itemsLength = rect[0].height*that.data.itemsShow.length + px1;
      }else if(that.data.currentTab == 1){
        itemsLength = rect[0].height*that.data.itemsWait.length + px2;
      }
      that.setData({
        swiperViewHeight: itemsLength
      })
    }).exec();
  },
// 点击切换头部tab栏
  clickTab: function(e) {
    var that = this;
    if (this.data.currentTab === e.target.dataset.current) {
      return false;
    } else {
      that.setSwiperHeight();
      that.setData({
        currentTab: e.target.dataset.current
      })
    }
  },
// 滑动切换tab栏
swiperTab: function(e) {
  var that = this;
  that.setSwiperHeight();
  console.log(e.detail.current)
  this.setData({
    currentTab: e.detail.current
  })
},
// 轮播图
swiperchange: function() {

},
// 获取热播电影数据
getMovieList: function() {
  var that = this;
  var pageNumHost = that.data.pageNumHost;
  var limit = that.data.limit;
  //playingList
  wx.request({
    url: app.globalData.url +'/home/getMovieList',
    method: 'GET',
    data: {
      pageNum: ++pageNumHost,
      limit: limit
    },
    header: {
      'Accept': 'application/json'
    },
    success: function (res) {
      const itemsShow = that.data.itemsShow.concat(res.data.data.beanList);
      that.setData({
        hasMoreHost: pageNumHost < res.data.data.tr,
        itemsShow: itemsShow,
        pageNumHost: pageNumHost
      })
      if (that.data.currentTab == 0)
        that.setSwiperHeight();
    }
  })
},
// 获取待映电影数据
getMovieWait: function() {
  var that = this;
  var pageNumWait = that.data.pageNumWait;
  var limit = that.data.limit;
  wx.request({
    url: app.globalData.url +'/home/getMovieWait',
    method: 'GET',
    data: {
      pageNum: ++pageNumWait,
      limit: limit
    },
    success: function (res) {
      const itemsWait = that.data.itemsWait.concat(res.data.data.beanList);
      that.setData({
        hasMoreWait: pageNumWait < res.data.data.tr,
        itemsWait: itemsWait,
        pageNumWait: pageNumWait
      })
      if (that.data.currentTab == 1)
        that.setSwiperHeight();
    }
  })
},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    //调用应用实例的方法获取全局数据
    //热映bannerList
    wx.request({
      url: app.globalData.url +'/home//getBannerList',
      method: 'GET',
      data: {},
      success: function (res) {
        that.setData({ bannerList: res.data.data })
      }
    })
    // 获取热映列表
    this.getMovieList()
    // 待映bannerList
    wx.request({
      url: app.globalData.url +'/home/getMoviePop',
      method: 'GET',
      data: {},
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        console.log(res)
        var list = res.data.data;
        for (var i in list) {
          list[i].rt = util.formatDate(new Date(list[i].rt));
        }
        that.setData({ itemsPop: list })
      }
    })
    // 获取待映列表
    this.getMovieWait()
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
    // 上拉到底部，获取更多电影数据
    if (this.data.currentTab == 0 && this.data.hasMoreHost)
      this.getMovieList();
    else if (this.data.currentTab == 1 && this.data.hasMoreWait)
      this.getMovieWait();
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})