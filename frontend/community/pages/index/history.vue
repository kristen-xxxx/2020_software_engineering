<template>
	<view class="content">
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
				url: 'http://localhost:9090/health/query',
				method: 'GET',
				data: {
					tel:this.usertel
				},
				success: res => {
					var sd = res.data.statusCode;
					if(sd == 1){
						this.news = res.data.data
						
						uni.hideLoading();
					}else{
						uni.showToast({
							 title: '暂无打卡记录'
						})
					}
					// // console.log(res.data);
					// this.news = res.data.data;
					// // console.log(this.news);
					// uni.hideLoading();
					// // switch(item.healthCdt) {
					// //      case 1:
					// //         item.healthCdt="健康";
					// //         break;
					// //      case 2:
					// //         item.healthCdt="健康";
					// //         break;
					// //      default:   
					// // } 
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
</style>
