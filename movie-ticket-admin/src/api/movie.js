import axios from '@/utils/axios'
export const upLoadFile = (data) => {
    return axios({
        url: '/admin/movie/upLoadFile',
        method: 'POST',
        data: data
    })
}
export const getCurrentPageMovie = (data) => {
    return axios({
        url: '/admin/movie/getMovies',
        method: 'GET',
        params: data
    })
}
export const updateMovieInfo = (data) => {
    return axios({
        url: '/admin/movie/addMoive',
        method: 'POST',
        data: data
    })
}
export const deleteMovieInfo = (data) => {
    return axios({
        url: '/admin/movie/deleteMovie',
        method: 'POST',
        data: data
    })
}