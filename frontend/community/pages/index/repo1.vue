<template>
    <view>
		<!-- 姓名和身份证号不传入后台，直接由用户数据导入，不可修改 -->
		<view class="uni-form-item uni-column">
		    <view class="title">姓名</view>
		    <input class="uni-input" name="name" v-model="username" placeholder="这是姓名"  disabled=ture />
		</view>
		<view class="uni-form-item uni-column">
		    <view class="title">身份证号</view>
		    <input class="uni-input" name="ic" v-model="useric" disabled=ture/>
		</view>
        <view>
            <form @submit="formSubmit" @reset="formReset">
				<!-- 电话需要随表单传入后台，直接由用户数据导入，不可修改 -->
				<view class="uni-form-item uni-column">
				    <view class="title">联系电话</view>
				    <input class="uni-input" name="tel" v-model="usertel" placeholder="这是电话" disabled=ture />
				</view>

                <view class="uni-form-item uni-column">
                    <view class="title">健康状况</view>
                    <radio-group name="cdt">
                        <label>
                            <radio value="1" /><text>健康</text>
                        </label>
                        <label>
                            <radio value="3" /><text>发热</text>
                        </label>
						<label>
						    <radio value="4" /><text>疑似</text>
						</label>
						<label>
						    <radio value="5" /><text>确诊</text>
						</label>
						<label>
						    <radio value="2" /><text>已治愈</text>
						</label>
                    </radio-group>
                </view>
                <view class="uni-form-item uni-column">
                    <view class="title">体温</view>
                    <slider min="35" max="42" value="35" step="0.1" name="temp" show-value></slider>
                </view>
                <view class="uni-btn-v">
                    <button form-type="submit">提交</button>
                    <button type="default" form-type="reset">清空</button>
                </view>
            </form>
        </view>
    </view>
</template>
<script>
    export default {
        data() {
            return {
				username:'',
				useric:'',
				usertel:''
            }
        },
		
        methods: {
			onLoad(){
				try {
					//console.log(this.username);
				    var value = uni.getStorageSync('storage_user');
				    if (value) {
						// this.username=value.data.name;
						//console.log(this.username);
						//console.log(value.name);
				        this.username=value.name;
						this.useric=value.ic;
						this.usertel=value.tel;
						//console.log(this.username);
						
				    }
				} catch (e) {
				    // error
				}
				
				//console.log(this.username);
				//this.username=1
				//console.log(this.username);
				// uni.getStorage({
				// 	key: 'storage_user',
				// 	success: function (res) {
				// 		//console.log(res.data);
				// 		//console.log(res.data.name);
				// 		console.log(this.username);
				// 		this.username=res.data.name;
				// 		//this.tel=res.data.tel;
				// 		console.log(this.username);
				// 	},
			 //   });
			 
			   
				
			},	
			
            formSubmit: function(e) {
				
                console.log('form发生了submit事件，携带数据为：' + JSON.stringify(e.detail.value))
                // var formdata = e.detail.value
				// console.log(e.detail.value.cdt)
				// 弹出提示框
				uni.request({
					url:'http://localhost:9090/health/report',
					method:'GET',
					data:{
						// data:JSON.stringify(formdata),
						tel:e.detail.value.tel,
						temp:e.detail.value.temp,
						cdt:e.detail.value.cdt
					},
					success: res => {
						console.log(res);
						if(res.data.statusCode == 1){
							uni.showToast({
								 title: '打卡成功'
							})
						};
						if(res.data.statusCode == 2){
							uni.showToast({
								 title: '打卡更新成功'
							})
						};
						if(res.data.statusCode != 1 && res.data.statusCode != 2 )
						{
							uni.showToast({
								 icon: 'none',
								 title: '打卡失败'
							})
						}
						
						},

						// this.news = res.data;
						// uni.hideLoading();
					
					fail: () => {},
					complete: () => {}
				})
                uni.showModal({
                    content: '表单数据内容：' + JSON.stringify(formdata),
                    showCancel: false
                });
            },
            formReset: function(e) {
                console.log('清空数据')
            }
        }
    }
</script>

<style>
    .uni-form-item .title {
        padding: 20rpx 0;
    }
</style>