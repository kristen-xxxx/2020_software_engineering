<template>
	<view class="content">
		<view class="cu-bar bg-white solid-bottom margin-top">
			<view class="action">
				<text class="cuIcon-title text-blue"></text>七日内健康打卡情况
			</view>
		</view>
			<view class="uni-list">
				<view class="uni-list-cell" hover-class="uni-list-cell-hover" v-for="(item,index) in news" :key="index" @tap="openinfo" :data-newsid="item.post_id">
					<view class="uni-media-list">
						
						<!-- <image class="uni-media-list-logo" :src="item.author_avatar"></image> -->
						<view class="uni-media-list-body">
							<view class="uni-media-list-text-top">{{item.healthDate}}</view>
							<view class="uni-media-list-text-bottom uni-ellipsis">
								<!-- 根据item.healthCdt来返回健康状态 -->
								<p v-if="item.healthCdt==1">健康状况：健康</p>
								<p v-if="item.healthCdt==2">健康状况：已痊愈</p>
								<p v-if="item.healthCdt==3">健康状况：发热</p>
								<p v-if="item.healthCdt==4">健康状况：疑似</p>
								<p v-if="item.healthCdt==5">健康状况：确诊</p>
								</view>
							<view class="uni-media-list-text-bottom uni-ellipsis">体温：{{item.healthTemp}}</view>
						</view>
					</view>
				</view>
			</view>
			<view class="cu-bar bg-white solid-bottom margin-top">
				<view class="action">
					<text class="cuIcon-title text-blue"></text>七日内出入社区情况
				</view>
			</view>
			<view class="uni-list">
				<view class="uni-list-cell" hover-class="uni-list-cell-hover" v-for="(item,index) in news" :key="index" @tap="openinfo" :data-accessid="item.post_id">
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
			<button class="bg-prm margin-tb-sm" >允许出社区</button>
			<button class="bg-prm margin-tb-sm">允许入社区</button>
			<button class="bg-red margin-tb-sm">禁止通行</button>
			
		</view>
</template>

<script>
	export default {
		data() {
			return {
				usertel:'',
				news : [],
				access : []
			};
		},
		onLoad:function(){
			// uni.showLoading({
			// 	title:"加载中...."
			// })
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
				url: 'http://localhost:9090/health/query',
				method: 'GET',
				data: {
					//tel:this.usertel
				},
				success: res => {
					var sd = res.data.statusCode;
					if(sd == 1){
						this.news = res.data.data;
						//数组倒置
						this.news=this.news.reverse();
						
						uni.hideLoading();
					}else{
						// uni.showToast({
						// 	 title: '暂无打卡记录'
						// })
					}
				},
				fail: () => {},
				complete: () => {}
			});
			
			uni.request({
				url: 'http://localhost:9090/access/query',
				method: 'GET',
				data: {
					//tel:this.usertel
				},
				success: res => {
					var sd = res.data.statusCode;
					if(sd == 1){
						this.access = res.data.data;
						//数组倒置
						this.access=this.news.reverse();
						
						uni.hideLoading();
					}else{
						// uni.showToast({
						// 	 title: '暂无打卡记录'
						// })
					}
				},
				fail: () => {},
				complete: () => {}
			});
			
		},
		methods: {
			// openinfo(e) {
			// 	var newsid = e.currentTarget.dataset.newsid;
			// 	uni.navigateTo({
			// 		url: '../info/info?newsid='+newsid
			// 	});
			// }
		},
	}
</script>

<style>
.uni-media-list-body{height: auto;}
.uni-media-list-text-top{line-height:1.6em;}
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
