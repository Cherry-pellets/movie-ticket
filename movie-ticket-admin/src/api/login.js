import axios from '@/utils/axios'
export const login = (data) => {
    return axios({
        url: '/admin/user/login',
        method: 'POST',
        data: data
    })
}