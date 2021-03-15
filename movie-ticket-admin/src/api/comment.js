import axios from '@/utils/axios'
export const getComments = (data) => {
    return axios({
        url: '/admin/comment/getComments',
        method: 'GET',
        params: data
    })
}
export const deleteComment = (data) => {
    return axios({
        url: '/admin/comment/deleteComment',
        method: 'POST',
        data: data
    })
}