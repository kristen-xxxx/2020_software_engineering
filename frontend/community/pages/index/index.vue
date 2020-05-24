<template>
	<view class="content">
		<button type="primary" @click="healthReport">健康打卡</button>
		<button type="primary" @click="reportHistory">打卡记录</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		onLoad() {

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
	
</style>
