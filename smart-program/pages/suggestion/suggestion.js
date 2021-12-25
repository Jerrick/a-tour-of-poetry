var bmap = require('../../libs/bmap-wx.min.js');
import api from '../../utils/api'
var wxMarkerData = [];
Page({
    data: {
        markers: [],
        latitude: '',
        longitude: '',
        placeData: {},
        flag:true
    },
    onLoad: function() {
         
         
    },
    getHtmlContent(src,hight){
        let arr =  src.split("")
        let contentStr = '';
        let hightArr = hight.split("")
        arr.forEach((item)=>{
            hightArr.forEach((keyitem)=>{
                if(item == keyitem){
                item = '<span style="color:#f73131">'+item+'</span>';
                }else{
                    item = item;
                }
                    
            })
            contentStr = contentStr + item
        })
        return contentStr
    },
    searchPlace:function(e){
        var value = e.detail.value
        var that = this;  
        if(value ==''){
            return false
        }
        api.get('poetry/username', {username:value}, (data) => {
            // let arr =  data[0].content.split("")
            // let varArr =  value.split("")
            // let contentStr = '';
            // arr.forEach((item)=>{
            //     varArr.forEach((keyitem)=>{
            //         if(item == keyitem){
            //         item = '<span style="color:#f73131">'+item+'</span>';
            //         }else{
            //             item = item;
            //         }
                     
            //     })
            //     contentStr = contentStr + item
            // })
            if(data.length >= 1){
                that.setData({
                    placeData: {
                        name: '' + that.getHtmlContent(data[0].name,value) + '\n',
                        author: '' + that.getHtmlContent(data[0].author,value) + '\n',
                        content: '' + that.getHtmlContent(data[0].content,value),
                        address: '旅游指南：' + data[0].address
                    }
                });
                this.setData({
                    flag:false
                })
            }else{
                  wx.showModal({
                    title: '提示',
                    showCancel: false,
                    confirmColor: '#993399',
                    content: '抱歉,未能找到相关诗词',
                    success: (res) => {    
                    }
                })   
            }
             
        })
    }
})