import axios from '@/utils/axios'

// 获取电影订单
export const getCurrentPageOrder = (data) => {
    return axios({
        url: '/admin/order/getOrders',
        method: 'GET',
        params: data
    })
}
// 删除订单
export const deleteOrder = (data) => {
    return axios({
        url: '/admin/order/deleteOrder',
        method: 'POST',
        data: data
    })
}
// 获取小吃订单
export const getSnacks = (data) => {
    return axios({
        url: '/admin/snack/getSnacks',
        method: 'GET',
        params: data
    })
}
// 更新小吃订单信息
export const updateSnackInfo = (data) => {
    return axios({
        url: '/admin/snack/addSnack',
        method: 'POST',
        data: data
    })
}
// 删除小吃订单
export const deleteSnackInfo = (data) => {
    return axios({
        url: '/admin/snack/deleteSnack',
        method: 'POST',
        data: data
    })
}
// 小吃
export const getOptions = (data) => {
    return axios({
        url: '/admin/hall/getOptions',
        method: 'GET',
        params: data
    })
}