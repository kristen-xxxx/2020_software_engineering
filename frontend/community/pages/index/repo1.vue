<template>
    <view>
		<!-- 姓名和身份证号不传入后台，直接由用户数据导入，不可修改 -->
		<view class="uni-form-item uni-column">
		    <view class="title">姓名</view>
		    <input class="uni-input" name="name" placeholder="这是姓名" />
		</view>
		<view class="uni-form-item uni-column">
		    <view class="title">身份证号</view>
		    <input class="uni-input" name="" placeholder="这是身份证号" />
		</view>
        <view>
            <form @submit="formSubmit" @reset="formReset">
				<!-- 电话需要随表单传入后台，直接由用户数据导入，不可修改 -->
				<view class="uni-form-item uni-column">
				    <view class="title">联系电话</view>
				    <input class="uni-input" name="tel" placeholder="这是电话" />
				</view>
                <view class="uni-form-item uni-column">
                    <view class="title">健康状况</view>
                    <radio-group name="healthCondition">
                        <label>
                            <radio value="健康" /><text>健康</text>
                        </label>
                        <label>
                            <radio value="发热" /><text>发热</text>
                        </label>
						<label>
						    <radio value="疑似" /><text>疑似</text>
						</label>
						<label>
						    <radio value="确诊" /><text>确诊</text>
						</label>
						<label>
						    <radio value="已治愈" /><text>已治愈</text>
						</label>
						<label>
						    <radio value="其他" /><text>其他</text>
						</label>
                    </radio-group>
                </view>
                <view class="uni-form-item uni-column">
                    <view class="title">体温</view>
                    <slider min="35" max="42" value="35" step="0.1" name="temp" show-value></slider>
                </view>
                <view class="uni-btn-v">
                    <button form-type="submit">提交</button>
                    <button type="default" form-type="reset">清空</button>
                </view>
            </form>
        </view>
    </view>
</template>
<script>
    export default {
        data() {
            return {
            }
        },
        methods: {
            formSubmit: function(e) {
                console.log('form发生了submit事件，携带数据为：' + JSON.stringify(e.detail.value))
                var formdata = e.detail.value
				// 弹出提示框
				uni.request({
					complete() {
						
					}
				})
                uni.showModal({
                    content: '表单数据内容：' + JSON.stringify(formdata),
                    showCancel: false
                });
            },
            formReset: function(e) {
                console.log('清空数据')
            }
        }
    }
</script>

<style>
    .uni-form-item .title {
        padding: 20rpx 0;
    }
</style>