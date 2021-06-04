import axios from '@/utils/axios'
export const getCurrentPageUser = (data) => {
    return axios({
        url: '/admin/user/getUsers',
        method: 'GET',
        params: data
    })
}
export const banUserById = (data) => {
    return axios({
        url: '/admin/user/banUser',
        method: 'POST',
        data: data
    })
}
export const getCurrentPageAdmin = (data) => {
    return axios({
        url: '/admin/auser/getAdmins',
        method: 'GET',
        params: data
    })
}
export const updateAdminInfo = (data) => {
    console.log(data)
    return axios({
        url: '/admin/auser/updateInfo',
        method: 'POST',
        data: data
    })
}
export const getOptions3 = (data) => {
    return axios({
        url: '/admin/auser/getOptions',
        method: 'GET',
        params: data
    })
}