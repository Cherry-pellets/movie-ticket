import axios from '@/utils/axios'
export const getCurrentPageHall = (data) => {
    return axios({
        url: '/admin/hall/getHalls',
        method: 'GET',
        params: data
    })
}
export const getOptions = (data) => {
    return axios({
        url: '/admin/hall/getOptions',
        method: 'GET',
        params: data
    })
}
export const getSeats = (data) => {
    return axios({
        url: '/admin/hall/getSeatByHallId',
        method: 'GET',
        params: data
    })
}
export const deleteHall = (data) => {
    return axios({
        url: '/admin/hall/deleteHall',
        method: 'POST',
        data: data
    })
}
export const updateHallInfo = (data) => {
    return axios({
        url: '/admin/hall/updateHall',
        method: 'POST',
        data: data
    })
}
export const addHall = (data) => {
    return axios({
        url: '/admin/hall/addHall',
        method: 'POST',
        data: data
    })
}
export const commitSeat = (data) => {
    return axios({
        url: '/admin/hall/commitSeat',
        method: 'POST',
        data: data
    })
}