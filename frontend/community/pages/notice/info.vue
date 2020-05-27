<template>
	<view class="content">
		<view class="title">{{title}}...</view>
		<view class="art-content">
			<rich-text class="richText" :nodes="strings"></rich-text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: '',
				strings : ''
			}
		},
		onLoad:function(e){
			console.log(e);
			uni.request({
				url: 'http://localhost:9090/news/search',
				method: 'GET',
				data: {
					newsId:e.newsId
				},
				success: res => {
					console.log(res.data);
					this.title = res.data.data.newsTitle;
					this.strings = res.data.data.newsContent;
				},
				fail: () => {},
				complete: () => {}
			});
		}
	}
</script>

<style>
.content{padding:10upx 2%; width: 96%; flex-wrap:wrap;}
.title{line-height:2em; font-weight:700; font-size:38upx;}
.art-content{line-height:2em;}
</style>
