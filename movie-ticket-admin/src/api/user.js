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
        url: '/admin/user/getUsers',
        method: 'POST',
        data: data
    })
}