import axios from '@/utils/axios'
// 获取
export const getCurrentPageMovie = (data) => {
    return axios({
        url: '/admin/movie/getMovies',
        method: 'GET',
        params: data
    })
}
// 修改电影信息
export const updateMovieInfo = (data) => {
    return axios({
        url: '/admin/movie/addMoive',
        method: 'POST',
        data: data
    })
}
// 删除
export const deleteMovieInfo = (data) => {
    return axios({
        url: '/admin/movie/deleteMovie',
        method: 'POST',
        data: data
    })
}
// 获取电影排片信息
export const getCurrentPageMovieSchedule = (data) => {
    return axios({
        url: '/admin/schedule/getMovieSchedule',
        method: 'GET',
        params: data
    })
}
// 获取所有电影和影院
export const getOptions2 = (data) => {
    return axios({
        url: '/admin/schedule/getOptions',
        method: 'GET',
        params: data
    })
}
export const getHallByCinemaId = (data) => {
    return axios({
        url: '/admin/schedule/getHallByCinema',
        method: 'GET',
        params: data
    })
}
//  添加排片信息
export const addScheduleInfo = (data) => {
    return axios({
        url: '/admin/schedule/addScheduleInfo',
        method: 'POST',
        data: data
    })
}
// 删除
export const deleteMovieSchedule = (data) => {
    return axios({
        url: '/admin/schedule/deleteMovieSchedule',
        method: 'POST',
        data: data
    })
}
// 商家获取排片
export const getCurrentPageBMovieSchedule = (data) => {
    return axios({
        url: '/admin/schedule/getMovieSchedule',
        method: 'GET',
        params: data
    })
}