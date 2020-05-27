<template>
	<view>
		<view class='UCenter-bg'>
			<view class='text-xl' style="font-size: 36upx;">
			<text>你好，{{value.name}}</text>
			</view>
			<view class='padding-top-sm' style="padding-top: 20upx">
				<!-- 用户头像 -->
				<!-- <view class="cu-avatar xl round margin-left">D</view> -->
				<!-- <text class="margin-left-sm">{{username}}</text> -->
			</view>
			<image src='https://api.szyashu.com/static/wave.gif' mode='scaleToFill' class='gif-wave'></image>
		</view>
		<view class="cu-list menu sm-border card-menu padding-top-xs">
			<!-- <view class="cu-item arrow" @tap="SelectShequ">
				<view class="content">
					<text class="cuIcon-list text-lg"/>
					<text class="text-left">修改个人信息</text>
				</view>				
			</view> -->
			<navigator url="update" class="cu-item arrow">
				<view class="content">
					<text class="cuIcon-list text-lg"/>
					<text class="text-left">修改个人信息</text>
				</view>		
			</navigator>
		</view>
		<view class="cu-list menu sm-border card-menu">
			<navigator url="../user/history" class="cu-item arrow">
				<view class="content">
					<text class="cuIcon-apps text-lg"/>
					<text class="text-left">查看打卡记录</text>
				</view>			
			</navigator>
			
			<navigator url="../user/access" class="cu-item arrow">
				<view class="content">
					<text class="cuIcon-exit text-lg"/>
					<text class="text-left">查看出入记录</text>
				</view>				
			</navigator>
			
		</view>
		<!-- <view class="flex padding-top-sm justify-center flex-direction align-center">
			<view class="text-gray">用户自助填写二维码</view>
			<view class="padding-top-sm">
				<tki-qrcode cid="qrcode2" ref="qrcode2" :val="val" :background="background" :foreground="foreground" :pdground="pdground" :size="200" :onval="true" :loadMake="true" :usingComponents="true" @result="qrR" />
			</view>
		</view> -->
		<view>
			<button class="margin-tb-sm cu-btn bg-red lg" type="warn" @click="quit">退出登录</button>
		</view>
	</view>
</template>

<script>
	import {mapState,mapMutations} from 'vuex';
	//import {post,get,showModal} from '../../utils/index.js';
	
	import tkiQrcode from '@/components/tki-qrcode/tki-qrcode.vue';
	var that;
	export default {
		computed:mapState(['login_info','WsClient']),
		components: {tkiQrcode},
		data() {
			return {
				
				src: '', // 二维码生成后的图片地址或base64
				foreground: '#000000', // 前景色
				pdground: '#309286', // 角标色
				background:'#ffffff',
				shequ:{},
				type:0,
				val:'',
				value:'',
				username:'用户',
				useric:'',
				usertel:''
				
			}
		},
		onShow() {
			that = this;
			this.shequ = uni.getStorageSync('storage_user');
			this.type = uni.getStorageSync('type');
			this.val = "https://www.zgnnet.com/aliapp/shequ?id="+uni.getStorageSync('shequ').shequ_id;	
			try {
				//console.log(this.username);
			    this.value = uni.getStorageSync('storage_user');
				console.log(value.data)
			    if (value) {
					//this.username=value.data.name;
					//console.log(this.username);
					console.log(value.data);
			        this.usertel=value.tel;
					this.useric=value.ic;
					this.username=value.name;
					console.log(this.username);
					
			    }
			} catch (e) {
			    // error
			}
		},
		methods: {
			qrR(res) {
				this.src = res
			},
			SelectShequ(){
				uni.$once('shequ_select',function(res){
					if(res.type === 1){
						post(that.ali_app + 'today_data',{id:res.shequ_id,type:3}).then(res=>{
							uni.setStorageSync('shequ',res.data.shequ);
							this.shequ = res.data.shequ;
						});
					}
				});
				uni.navigateTo({
					url:'../index/shequ_list/shequ_list'
				})
			},
			quit(){
				//清除本地缓存并退出
				// uni.removeStorage({
				//     key: 'storage_user',
				//     success: function (res) {
				//         console.log('success');
				//     }
				// });
				uni.clearStorage();
				uni.navigateTo({
					url: '../login/login'
				});
			}
		}
	}
</script>

<style>
	.UCenter-bg {
	  /* background-image: url('https://api.szyashu.com/static/index.jpg'); */
	  /* background-size: cover; */
	  background-color: #66ccff;
	  height: 250rpx;
	  display: flex;
	  justify-content: center;
	  padding-top: 40rpx;
	  overflow: hidden;
	  position: relative;
	  flex-direction: column;
	  align-items: center;
	  color: #fff;
	  font-weight: 300;
	  text-shadow: 0 0 3px rgba(0, 0, 0, 0.3);
	}
	
	.UCenter-bg text {
	  opacity: 0.8;
	}
	
	.UCenter-bg image {
	  width: 200rpx;
	  height: 200rpx;
	}
	
	.UCenter-bg .gif-wave{
	  position: absolute;
	  width: 100%;
	  bottom: 0;
	  left: 0;
	  z-index: 99;
	  mix-blend-mode: screen;  
	  height: 100rpx;   
	}
	.margin-tb-sm{
		margin-right: 120upx;
		margin-top:45upx;
		margin-left: 120upx;
		display: flex;
		align-items: center;
		justify-content: center;
		/* margin-right: auto;
		margin-left: auto; */
	}
	
</style>