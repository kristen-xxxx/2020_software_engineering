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
					url: './repo1'
				});
			},
			reportHistory(){
				uni.navigateTo({
					url: './history'
				});
			},
			

		}
	}
</script>

<style>
	
</style>
