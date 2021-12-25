// 这里可以使用自己服务器的接口
const API = 'http://ip:port/api'

const get = (cmd, params, callback) => {
    wx.showToast({
         title: '数据加载中...',
        icon: 'loading',
        duration: 2000
    })
    wx.request({
        url: API+(cmd?('/'+cmd):''),
        data: params,
        success: (res) => {
            wx.hideToast()
            const data = res.data
            if(data.code){
                wx.showModal({
                    title: '提示',
                    showCancel: false,
                    confirmColor: '#993399',
                    content: data.message,
                    success: (res) => {
                    }
                    })
                return
            }
            if(typeof(callback) == 'function')
                callback(res.data)
        }
    })
}

const post = (cmd, params, callback) => {
    wx.showToast({
         title: '数据提交中...',
        icon: 'loading',
        duration: 2000
    })
    wx.request({
        url: API+(cmd?('/'+cmd):''),
        data: params,
        method: 'POST',
        success: (res) => {
            const data = res.data
            console.log('apid',res)
            if(data.code){
                wx.showModal({
                    title: '提示',
                    showCancel: false,
                    confirmColor: '#993399',
                    content: data.message,
                    success: (res) => {
                    }
                    })
                return
            }
            if(typeof(callback) == 'function')
                callback(res)
        }
    })
}

export default {
    get: get,
    post: post
}