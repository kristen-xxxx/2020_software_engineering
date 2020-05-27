<template>
    <view class="card-menu cu-list menu ">
		<!-- 姓名和身份证号不传入后台，直接由用户数据导入，不可修改 -->
		<!-- <view class="uni-form-item uni-column"> -->
		<view class="my-bg2 " ></view>
            <form @submit="formSubmit">
				<view class="cu-form-group">
				    <view class="title">姓名</view>
				    <input class="uni-input" name="name" v-model="username" placeholder="请填写姓名"/>
				</view>
				<view class="cu-form-group">
				    <view class="title">身份证号</view>
				    <input class="uni-input" name="ic" v-model="useric" placeholder="请填写身份证号"/>
				</view>
				<!-- <view class="cu-form-group"> -->
				<!-- 电话需要随表单传入后台，直接由用户数据导入，不可修改 -->
				<view class="cu-form-group">
				    <view class="title">联系电话</view>
				    <input class="uni-input" name="tel" v-model="usertel" placeholder="请填写电话"/>
				</view>
                <view class="uni-btn-v">
                    <button form-type="submit" class="bg-prm">提交</button>
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
				usertel:'',
				sex:''
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
				        this.usertel=value.tel;
						this.useric=value.ic;
						this.username=value.name;
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
				var num = e.detail.value.ic.charAt(16);
				//console.log(num);
				if(num%2==0){
				  // console.log('女');
				  this.sex='2';
				}else{
				  // console.log('男');
				  this.sex='1';
				}
				
				
				uni.request({
					url:'http://localhost:9090/user/update',
					method:'GET',
					data:{
						// data:JSON.stringify(formdata),
						tel:e.detail.value.tel,
						name:e.detail.value.name,
						sex:this.sex,
						ic:e.detail.value.ic,
					},
					success: res => {
						console.log(res);
						if(res.data.statusCode == 1){
							uni.showToast({
								 title: '修改成功'
							});
							//更新缓存中的个人信息
							try {
							    uni.setStorageSync('storage_user',  res.data.data);
							} catch (e) {
							    // error
							}
							uni.switchTab({
								url: './user'
							})
						};
						if(res.data.statusCode != 1)
						{
							uni.showToast({
								 icon: 'none',
								 title: '修改失败'
							})
						}
						
						},

						// this.news = res.data;
						// uni.hideLoading();
					
					fail: () => {},
					complete: () => {}
				})
                
            },
            
        }
    }
</script>

<style>
    .uni-form-item .title {
       /* //padding: 20rpx 0; */
		background-color: #ffffff;
		padding: 1upx 30upx;
		display: flex;
		align-items: center;
		min-height: 100upx;
		justify-content: space-between;
    }
	.my-bg {
	   /* //padding: 20rpx 0; */
		background-color: #ffffff;
		align-items: center;
		min-height: 100upx;
		justify-content: space-between;
		/*display: flex;
		
		padding: 1upx 30upx;
		justify-content: space-between; */
	}
	.my-bg2{
		margin-top: 30upx;
	} 
</style>