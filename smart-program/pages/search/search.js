var bmap = require('../../libs/bmap-wx.min.js');
import api from '../../utils/api'
var wxMarkerData = [];
Page({
    data: {
        markers: [],
        latitude: '',
        longitude: '',
        placeData: {},
        flag:true,
        curentCity:'',
        scaleNum:14
    },
    makertap: function(e) {
        console.log('e',e)
        var that = this;
        var id = e.markerId;
         id   = id -1
        that.showSearchInfo(wxMarkerData, id);
        that.changeMarkerColor(wxMarkerData, id);
    },
    onLoad: function() {
        var that = this;
        var BMap = new bmap.BMapWX({
            ak: '你的百度地图ak'
        });
        var fail = function(data) {
            console.log('data',data)
        };
        var success = function(data) {
            wxMarkerData = data.wxMarkerData;
            console.log('data',data)
            let city = wxMarkerData[0].address
            city =  city.split('市')[0]
            that.setData({
                curentCity: city
            });
            that.getInfo(city)
            that.setData({
                markers: wxMarkerData
            });
            that.setData({
                latitude: wxMarkerData[0].latitude
            });
            that.setData({
                longitude: wxMarkerData[0].longitude
            });
        }
        that.getlocation() 
        // BMap.search({
        //     "query": '美食',
        //     fail: fail,
        //     success: success,
        //     iconPath: '../../img/normal.png',
        //     iconTapPath: '../../img/normal.png'
        // });
        BMap.regeocoding({ 
            fail: fail, 
            success: success
        }); 
    },
    showSearchInfo: function(data, i) {
        var that = this;
        let index = i
        that.setData({
            placeData: {
                name: '' + data[index].name + '\n',
                // ancient_address: '' + data[index].ancient_address + '\n',
                content: '' + data[index].content,
                address: '' + data[index].address
            }
        });
    },
    changeMarkerColor: function(data, id) {
        var that = this;
        var markersTemp = [];
        for (var i = 0; i < data.length; i++) {
            if (i === id) {
                data[i].iconPath = "../../img/selected.png";
            } else {
                data[i].iconPath = "../../img/normal.png";
            }
            markersTemp[i] = data[i];
        }
        that.setData({
            markers: markersTemp
        });
        that.setData({
            flag:false
        })
    },
    closeMask: function (e) {
        console.log('e',e)
        this.setData({
          flag: true
       })
     },
     getInfo(value){
        if(value ==''){
            return false
        }
        var that = this
        api.get('poetry', {query:value}, (data) => {
            wxMarkerData = [];
            data.forEach((item,index)=>{
                 let one  = {};
                 one.id  = index + 1 
                 one.latitude = item.Latitude
                 one.longitude = item.longitude
                 one.iconPath  = "../../img/normal.png"
                 one.iconTapPath = "../../img/normal.png"
                 one.width       = 30
                 one.height      = 30
                 one.author       = item.author
                 one.name       = item.name
                 one.ancient_address     = item.ancient_address
                 one.content     = item.content
                 one.address     = '旅游指南：'+item.address
                 wxMarkerData.push(one)
            })
            console.log('wxMarkerData',wxMarkerData)
            if(wxMarkerData.length >0){
                that.setData({
                    markers: wxMarkerData
                });
                that.setData({
                    latitude: wxMarkerData[0].latitude
                }); 
                that.setData({
                    longitude: wxMarkerData[0].longitude
                });
                that.setData({
                    scaleNum: 3
                });
            } 
            //  wx.showModal({
            //     title: '提示',
            //     showCancel: false,
            //     confirmColor: '#993399',
            //     content: data.name+'xx',
            //     success: (res) => {    
            //     }
            // })
        })
     },
    searchPlace:function(e){
        var that = this
        var value = e.detail.value 
         that.getInfo(value)
    },
    getlocation(){
        var that  = this  
        wx.getSetting({
            success(res) {
              if (res.authSetting['scope.userLocation']) {
               wx.getLocation({
                   success(res){
                       that.setData({
                        latitude:res.latitude,
                        longitude:res.longitude
                       })
                    }
               })
                
              }else {
                  // 2. 用户未授权的情况下， 打开授权界面， 引导用户授权.
                  wx.openSetting({
                      success(res) {
                          // 2.1 如果二次授权允许了 userLocation 权限， 就再次执行获取位置的接口
                          if (res.authSetting["scope.userLocation"]) {
                               wx.getLocation({
                                  success(res){
                                    that.setData({
                                        latitude:res.latitude,
                                        longitude:res.longitude
                                    })
                                  }
                              })
                          }
                      }
                  })
              }
            }
        })
    }
})