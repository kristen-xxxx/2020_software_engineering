<template>
	<view class="content">
			<view class="uni-list">
				<view class="uni-list-cell" hover-class="uni-list-cell-hover" v-for="(item,index) in news" :key="index" @tap="openinfo" :data-newsid="item.post_id">
					<view class="uni-media-list">
						<!-- <image class="uni-media-list-logo" :src="item.author_avatar"></image> -->
						<view class="uni-media-list-body">
							<view class="uni-media-list-text-top">{{item.accessDate}}</view>
							<view class="uni-media-list-text-bottom uni-ellipsis">
								<!-- 根据item.healthCdt来返回健康状态 -->
								<p v-if="item.accessCdt==1">出入情况：出社区</p>
								<p v-if="item.accessCdt==2">出入情况：入社区</p>
								<p v-if="item.accessCdt==3">出入情况：禁止通行</p>
								</view>
							<!-- <view class="uni-media-list-text-bottom uni-ellipsis">体温：{{item.healthTemp}}</view> -->
							<!-- 个人页面不用显示联系电话 -->
							<!-- <view class="uni-media-list-text-bottom uni-ellipsis">体温：{{item.healthTemp}}</view> -->
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
				usertel:'',
				news : []
			};
		},
		onLoad:function(){
			uni.showLoading({
				title:"加载中...."
			})
			//从缓存获取用户tel
			try {
			    var value = uni.getStorageSync('storage_user');
			    if (value) {
					this.usertel=value.tel;
			    }
			} catch (e) {
			    // error
			}
			//发送get请求
			uni.request({
				url: 'http://localhost:9090/access/query',
				method: 'GET',
				data: {
					tel:this.usertel,
					
				},
				success: res => {
					var sd = res.data.statusCode;
					if(sd == 1){
						this.news = res.data.data;
						//数组倒置
						this.news=this.news.reverse();
						
						uni.hideLoading();
					}else{
						uni.showToast({
							 title: '暂无出入记录'
						})
					}

				},
				fail: () => {},
				complete: () => {}
			});
			
		},

	}
</script>

<style>
.uni-media-list-body{height: auto;}
.uni-media-list-text-top{line-height:1.6em;}
</style>
