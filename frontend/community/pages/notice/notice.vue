<template>
	<view class="content">
			<view class="uni-list">
				<view class="uni-list-cell" hover-class="uni-list-cell-hover" v-for="(item,index) in news" :key="index" @tap="openinfo" :data-newsId="item.newsId">
					<view class="uni-media-list">
						<image class="uni-media-list-logo" :src="item.authorAvatar"></image>
						<view class="uni-media-list-body">
							<view class="uni-media-list-text-top">{{item.newsTitle}}</view>
							<view class="uni-media-list-text-bottom uni-ellipsis">{{item.postDate}}</view>
						</view>
					</view>
				</view>
			</view>
		</view>
</template>

<script>
	export default {
		data() {
			return {
				news : []
			};
		},
		onLoad:function(){
			uni.showLoading({
				title:"加载中...."
			})
			uni.request({
				url: 'http://localhost:9090/news/show',
				method: 'GET',
				data: {},
				success: res => {
					console.log(res);
					this.news = res.data;
					uni.hideLoading();
				},
				fail: () => {},
				complete: () => {}
			});
		},
		methods: {
			openinfo(e) {
				console.log(e);
				var newsId = e.currentTarget.dataset.newsid;
				// console.log();
				uni.navigateTo({
					url: './info?newsId='+newsId
				});
			}
			
		},
	}
</script>

<style>
.uni-media-list-body{height: auto;}
.uni-media-list-text-top{line-height:1.6em;}
</style>
