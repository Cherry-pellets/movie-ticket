import axios from '@/utils/axios'
// 上传
export const upLoadFile = (data) => {
    return axios({
        url: '/admin/movie/upLoadFile',
        method: 'POST',
        data: data
    })
}