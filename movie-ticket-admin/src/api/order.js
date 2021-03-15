import axios from '@/utils/axios'

export const getCurrentPageOrder = (data) => {
    return axios({
        url: '/admin/order/getOrders',
        method: 'GET',
        params: data
    })
}
export const deleteOrder = (data) => {
    return axios({
        url: '/admin/order/deleteOrder',
        method: 'POST',
        data: data
    })
}