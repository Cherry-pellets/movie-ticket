import axios from '@/utils/axios'
export const getBanner = (data) => {
    return axios({
        url: '/admin/banner/getBanner',
        method: 'GET',
        params: data
    })
}
export const updateBannerInfo = (data) => {
    return axios({
        url: '/admin/banner/addBanner',
        method: 'POST',
        data: data
    })
}
export const deleteBannerInfo = (data) => {
    return axios({
        url: '/admin/banner/deleteBanner',
        method: 'POST',
        data: data
    })
}