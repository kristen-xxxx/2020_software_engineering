<template>
	<view class="register">
	
		<view class="content">
			<!-- 头部logo -->
			<view class="header">
				<image :src="logoImage"></image>
			</view>
			<!-- 主体 -->
			<view class="main">
				<wInput
					v-model="tel"
					type="text"
					maxlength="11"
					placeholder="手机号(作为登录账号)"
				></wInput>
				<wInput
					v-model="name"
					type="text"
					maxlength="11"
					placeholder="姓名"
				></wInput>
				<wInput
					v-model="ic"
					type="text"
					maxlength="18"
					placeholder="身份证号码"
				></wInput>
				
				<wInput
					v-model="password"
					type="password"
					maxlength="11"
					placeholder="登录密码"
					isShowPass
				></wInput>
				<wInput
					v-model="password2"
					type="password"
					maxlength="11"
					placeholder="确认登录密码"
					isShowPass
				></wInput>
				<!-- <wInput
					v-model="verCode"
					type="number"
					maxlength="4"
					placeholder="验证码"
					
					isShowCode
					ref="runCode"
					@setCode="getVerCode()"
				></wInput> -->
					
			</view>
				
			<wButton 
				text="注 册"
				:rotate="isRotate" 
				@click.native="startReg()"
			></wButton>
			
			<!-- 底部信息 -->
			<view class="footer">
				<text 
					@tap="isShowAgree" 
					class="cuIcon"
					:class="showAgree?'cuIcon-radiobox':'cuIcon-round'"
				>同意</text>
				<!-- 协议地址 -->
				<navigator url="" open-type="navigate">《协议》</navigator>
			</view>
		</view>
	</view>
</template>

<script>
	var _this;
	import wInput from '../../components/watch-login/watch-input.vue' //input
	import wButton from '../../components/watch-login/watch-button.vue' //button
	
	export default {
		data() {
			return {
				//logo图片 base64
				logoImage:'../../static/logo1.png',
				tel:'', // 用户/电话
				name:'',//姓名
				ic:'',//证件号
				password:'', //密码
				password2:'', //确认密码
				verCode:"", //验证码
				sex:'',
				showAgree:true, //协议是否选择
				isRotate: false, //是否加载旋转
			}
		},
		components:{
			wInput,
			wButton,
		},
		mounted() {
			_this= this;
		},
		methods: {
			isShowAgree(){
				//是否选择协议
				_this.showAgree = !_this.showAgree;
			},
			
		    startReg() {
				//注册
				if(this.isRotate){
					//判断是否加载中，避免重复点击请求
					return false;
				}
				if (this.showAgree == false) {
				    uni.showToast({
				        icon: 'none',
						position: 'bottom',
				        title: '请先同意《协议》'
				    });
				    return false;
				}
				if (this.tel.length !=11) {
				    uni.showToast({
				        icon: 'none',
						position: 'bottom',
				        title: '手机号不正确'
				    });
				    return false;
				}
				if (this.ic.length !=18) {
				    uni.showToast({
				        icon: 'none',
						position: 'bottom',
				        title: '身份证号不正确'
				    });
				    return false;
				}
		        if (this.password.length < 6 || this.password2.length < 6 ) {
		            uni.showToast({
		                icon: 'none',
						position: 'bottom',
		                title: '密码至少6位'
		            });
		            return false;
		        }
				if (this.password!=this.password2) {
				    uni.showToast({
				        icon: 'none',
						position: 'bottom',
				        title: '两次密码输入不一致'
				    });
				    return false;
				}
				// if (this.verCode.length != 4) {
				//     uni.showToast({
				//         icon: 'none',
				// 		position: 'bottom',
				//         title: '验证码不正确'
				//     });
				//     return false;
				// }
				
				//根据身份证号判断性别
				
				var num = this.ic.charAt(16);
				//console.log(num);
				if(num%2==0){
				  // console.log('女');
				  this.sex='2';
				}else{
				  // console.log('男');
				  this.sex='1';
				}
				
				//发送登录请求
				uni.request({
					url:'http://localhost:9090/user/register',
					method:'GET',
					//header: {'content-type' : "application/x-www-form-urlencoded"},
					data:{
						tel:this.tel,
						name:this.name,
						ic:this.ic,
						sex:this.sex,
						password:this.password,
					},
					
					success: res => {
						console.log(res);
						// this.news = res.data;
						// uni.hideLoading();
						if (res.data.statusCode == 1){
							uni.showToast({
							    icon: 'none',
								position: 'bottom',
							    title: '注册成功'
							});
								uni.navigateTo({
									url: '../login/login'
								});
							}else{
								uni.showToast({
								    icon: 'none',
									position: 'bottom',
								    title: '注册失败：当前用户已注册'
								});
							}
					},
					
					fail: () => {},
					complete: () => {}
				})
				
				
				
				_this.isRotate=true
				setTimeout(function(){
					_this.isRotate=false
				},3000)
		    }
		}
	}
</script>

<style>
	@import url("../../components/watch-login/css/icon.css");
	@import url("./css/main.css");
</style>