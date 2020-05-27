<template name="basics">
	<view class="content">
		<cover-image src = "../../static/bg.png"></cover-image>
		<!-- <button class="bg-gradual-blue align-center justify-center"  @click="healthReport">健康打卡</button>
		<button class="bg-gradual-green" type="primary" @click="reportHistory">打卡记录</button> -->
		<view class="margin-list">
			<scroll-view scroll-y class="page">
				<!-- <image src="https://cdn.nlark.com/yuque/0/2019/png/280374/1552996358228-assets/web-upload/e256b4ce-d9a4-488b-8da2-032747213982.png"
				 mode="widthFix" class="response"></image> -->
				<view class="nav-list" >
					<navigator hover-class="none" :url="item.url" class="nav-li" navigateTo :class="'bg-'+item.color"
					 :style="[{animation: 'show ' + ((index+1)*0.2+1) + 's 1'}]" v-for="(item,index) in userauz?admin:user" :key="index">
						<view class="nav-title">{{item.title}}</view>
						<view class="nav-name">{{item.name}}</view>
						<text :class="'cuIcon-' + item.cuIcon"></text>
					</navigator>
				</view>
				
				
				<view class="cu-tabbar-height"></view>
			</scroll-view>
		</view>
	</view>
	
	
</template>

<script>
	export default {
		name: "basics",
		data() {
			userauz:''
			value:''
			return {
				admin: [{
						title: '健康打卡',
						name: 'Health punch-in',
						color: 'cyan',
						cuIcon: 'newsfill',
						url:'../index/repo1',
					},
					{
						title: '健康码',
						name: 'health QR code',
						color: 'blue',
						cuIcon: 'qrcode',
						url:'../index/QRcode',
					},
					{
						title: '出入验证',
						name: 'Scan health code',
						color: 'purple',
						cuIcon: 'scan',
						url:'../index/scanCode'
					},
					{
						title: '社区出入记录',
						name: 'Access records',
						color: 'pink',
						cuIcon: 'copy',
						url:'../index/records'
					}
				],
				user: [{
						title: '健康打卡',
						name: 'Health punch-in',
						color: 'cyan',
						cuIcon: 'newsfill',
						url:'../index/repo1'
					},
					{
						title: '健康码',
						name: 'health QR code',
						color: 'blue',
						cuIcon: 'qrcode',
						url:'../index/QRcode',
					},
				],
			};
		},
		onLoad() {
			try {
				//console.log(this.username);
			    var value = uni.getStorageSync('storage_user');
			    if (value) {
					this.userauz=value.auz;
			    }
			} catch (e) {
			    // error
			}

		},
		methods: {
			healthReport(){
				uni.navigateTo({
					url: '../index/repo1'
				});
				//测试
				// uni.getStorage({
				// key: 'storage_user',
				// success: function (res) {
				//     //console.log(res.data);
				// 	var name = res.data.name
				// 	console.log(name);
				// },
				// });
			},
			
			reportHistory(){
				uni.navigateTo({
					url: '../index/history'
				});
			},
			

		}
	}
</script>

<style>
	.page {
		height: 100vh;
	}
	.margin-list{
		margin-top: 30upx;
	}
</style>
