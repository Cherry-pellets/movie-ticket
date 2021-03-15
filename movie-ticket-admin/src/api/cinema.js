import axios from '@/utils/axios'
export const getCurrentPageCinema = (data) => {
    return axios({
        url: '/admin/cinema/getCinemas',
        method: 'GET',
        params: data
    })
}
export const getBrandList = (data) => {
    return axios({
        url: '/admin/cinema/getBrandList',
        method: 'GET',
        params: data
    })
}
export const updateCinemaInfo = (data) => {
    return axios({
        url: '/admin/cinema/addCinema',
        method: 'POST',
        data: data
    })
}
export const deleteCinemaInfo = (data) => {
    return axios({
        url: '/admin/cinema/deleteCinema',
        method: 'POST',
        data: data
    })
}
export const addCinemaBrand = (data) => {
    return axios({
        url: '/admin/cinema/addCinemaBrand',
        method: 'POST',
        data: data
    })
}