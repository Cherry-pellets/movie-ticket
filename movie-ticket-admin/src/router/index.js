import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login')
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('@/views/home'),
    redirect: '/home/userManage',
    children: [
      {
        path: 'adminManage',
        component: () => import('@/views/home/children/admin-manage')
      },
            {
        path: 'bannerManage',
        component: () => import('@/views/home/children/banner-manage')
      },
            {
        path: 'cinemaManage',
        component: () => import('@/views/home/children/cinema-manage')
      },
            {
        path: 'commentManage',
        component: () => import('@/views/home/children/comment-manage')
      },
            {
        path: 'hallManage',
        component: () => import('@/views/home/children/hall-manage')
      },
            {
        path: 'movieManage',
        component: () => import('@/views/home/children/movie-manage')
      },
            {
        path: 'movieSchedule',
        component: () => import('@/views/home/children/movie-schedule')
      },
            {
        path: 'orderManage',
        component: () => import('@/views/home/children/order-manage')
      },
            {
        path: 'snackManage',
        component: () => import('@/views/home/children/snack-manage')
      },
            {
        path: 'userManage',
        component: () => import('@/views/home/children/user-manage')
      }
    ]
  }
]

const router = new VueRouter({
  // mode: 'history',
  // base: process.env.BASE_URL,
  routes
})

export default router
