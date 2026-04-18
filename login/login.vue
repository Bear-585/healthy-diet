<template>
	<view class="content">
		<view class="login-box" :style="{'backgroundImage': indexBgUrl?`url(${$base.url + indexBgUrl})`:''}">
			<view :style='{"padding":"40rpx","borderRadius":"20rpx","flexWrap":"wrap","background":"#ffffff80","display":"flex","width":"100%","position":"relative","justifyContent":"center","height":"auto"}'>
				<image :style='{"width":"160rpx","margin":"0 auto 24rpx auto","borderRadius":"8rpx","display":"none","height":"160rpx"}' :src="indexLogoUrl?($base.url + indexLogoUrl):'http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg'" mode="aspectFill"></image>
				<view v-if="loginType==1" :style='{"width":"100%","margin":"0 0 30rpx 0","flexWrap":"wrap","display":"flex","height":"auto","order":"1"}' class="uni-form-item uni-column">
					<view :style='{"width":"100%","lineHeight":"40rpx","fontSize":"26rpx","color":"#000","textAlign":"left"}' class="label">账号：</view>
					<input v-model="username" :style='{"border":"4rpx solid #FAC69680","padding":"0px 24rpx","margin":"0px","color":"#9D835E","borderRadius":"10rpx","flex":"1","background":"#fff","fontSize":"28rpx","height":"84rpx"}' type="text" class="uni-input" name="" placeholder="请输入账号" />
				</view>
				<view v-if="loginType==1" :style='{"width":"100%","margin":"0 0 30rpx 0","flexWrap":"wrap","display":"flex","height":"auto","order":"1"}' class="uni-form-item uni-column">
					<view :style='{"width":"100%","lineHeight":"40rpx","fontSize":"26rpx","color":"#000","textAlign":"left"}' class="label">密码：</view>
					<input v-model="password" password :style='{"border":"4rpx solid #FAC69680","padding":"0px 24rpx","margin":"0px","color":"#9D835E","borderRadius":"10rpx","flex":"1","background":"#fff","fontSize":"28rpx","height":"84rpx"}' type="password" class="uni-input" name="" placeholder="请输入密码" />
				</view>
				<view v-if="roleNum>1&&loginType<=2" :style='{"width":"100%","margin":"0 0 40rpx 0","flexWrap":"wrap","display":"flex","height":"auto","order":"3"}'>
					<view :style='{"width":"100%","lineHeight":"40rpx","fontSize":"26rpx","color":"#000","textAlign":"left"}' class="label">用户类型：</view>
					<picker @change="optionsChange" :value="index" :range="options" :style='{"border":"4rpx solid #FAC69680","padding":"0px 24rpx","color":"#9D835E","borderRadius":"10rpx","flex":"1","background":"#fff","lineHeight":"76rpx","fontSize":"28rpx"}'>
						<view class="uni-picker-type">{{options[index]}}</view>
					</picker>
				</view>
				

				
				<button v-if="loginType==1||loginType==3||loginType==4" class="btn-submit" @tap="onLoginTap" type="primary" :style='{"border":"0","padding":"0px","margin":"0 0 24rpx 0","color":"#fff","borderRadius":"0px","background":"#F4BC43","width":"100%","lineHeight":"80rpx","fontSize":"32rpx","height":"80rpx","order":"4"}'>登录</button>
				<!-- #ifdef MP-WEIXIN -->
				<button v-if="loginType==1" class="btn-submit" open-type="getUserInfo" @getuserinfo="getUserInfo" type="primary" :style='{"border":"0","padding":"0px","margin":"0 0 24rpx 0","color":"#fff","borderRadius":"8rpx","background":"#2cdc98","width":"100%","lineHeight":"80rpx","fontSize":"32rpx","height":"80rpx","order":"5"}'>微信登录</button>
				<!-- #endif -->
				<button v-if="loginType==2" class="btn-submit" @tap="onFaceLoginTap" type="primary" :style='{"border":"0","padding":"0px","margin":"0 0 24rpx 0","color":"#fff","borderRadius":"0px","background":"#F4BC43","width":"100%","lineHeight":"80rpx","fontSize":"32rpx","height":"80rpx","order":"4"}'>人脸识别登录</button>
				<view class="links" :style='{"padding":"0","margin":"0 0 24rpx 0","flexWrap":"wrap","display":"flex","width":"100%","justifyContent":"space-between","height":"auto","order":"6"}'>
					<view class="link-highlight" @tap="onRegisterTap('consumer')" :style='{"padding":"0 10rpx","margin":"0 20rpx 40rpx 0","color":"#222","borderRadius":"8rpx","textAlign":"center","background":"#ffffff50","width":"auto","fontSize":"28rpx","lineHeight":"70rpx"}'>注册用户</view>
				</view>
				
				<view class="idea1" :style='{"color":"#7A531A","textAlign":"center","display":"none","width":"100%","fontSize":"40rpx","fontWeight":"bold","height":"80rpx"}'>欢迎登录</view>
				<view class="idea2" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea2</view>
				<view class="idea3" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea3</view>
			</view>
		</view>
	</view>
</template>

<script>
	import menu from '@/utils/menu'
	export default {
		data() {
			return {
				username: '',
				password: '',
				loginType:1,
				codes: [{
					num: 1,
					color: '#000',
					rotate: '10deg',
					size: '16px'
				}, {
					num: 2,
					color: '#000',
					rotate: '10deg',
					size: '16px'
				}, {
					num: 3,
					color: '#000',
					rotate: '10deg',
					size: '16px'
				}, {
					num: 4,
					color: '#000',
					rotate: '10deg',
					size: '16px'
				}],
				options: [
					'请选择登录用户类型',
				],
				optionsValues: [
					'',
					'consumer',
				],
				index: 0,
				roleNum:0,

				// #ifdef MP-WEIXIN
				code: '',
				// #endif
				indexBgUrl: '',
				indexLogoUrl: '',
			}
		},
		onLoad() {
			let options = ['请选择登录用户类型'];
			let menus = menu.list();
			this.menuList = menus;
			for(let i=0;i<this.menuList.length;i++){
				if(this.menuList[i].hasFrontLogin=='是'){
					options.push(this.menuList[i].roleName);
					this.roleNum++;
				}
			}
			if(this.roleNum==1) {
				this.index = 1;
			}	
			this.options = options;
			this.styleChange()
			
		},
		onShow() {
			// #ifdef MP-WEIXIN
			let that = this
			uni.login({
				provider: 'weixin',
				success(res) {
					that.code = res.code
				}
			})
			// #endif
		},
		mounted() {
		},
		methods: {
			// #ifdef MP-WEIXIN
			async getUserInfo(e) {
				let that = this
				if (!this.optionsValues[this.index]) {
					this.$utils.msg('请选择登录用户类型')
					return
				}
				if (e.detail.errMsg === "getUserInfo:ok") {
					uni.showModal({
						content:'是否使用微信授权登录？',
						success:async (rs)=> {
							if(rs.confirm){
								let params = {
									code: that.code,
									encryptedData: e.detail.encryptedData,
									iv: e.detail.iv,
									rawData: e.detail.rawData,
									signature: e.detail.signature
								}
								let res = await that.$api.wxlogin(that.optionsValues[that.index], params)
								uni.removeStorageSync("useridTag");
								uni.setStorageSync("appToken", res.token);
								uni.setStorageSync("nickname", res.username);
								uni.setStorageSync("nowTable", `${that.optionsValues[that.index]}`);
								res = await that.$api.session(`${that.optionsValues[that.index]}`);
								if (res.data.profile) {
									uni.setStorageSync('frontAvatar', res.data.profile);
								} else if (res.data.avatar) {
									uni.setStorageSync('frontAvatar', res.data.avatar);
								}
								uni.setStorageSync('userSession',JSON.stringify(res.data))
								// 保存用户id
								uni.setStorageSync("appUserid", res.data.id);
								if (res.data.vip) {
									uni.setStorageSync("vip", res.data.vip);
								}
								uni.setStorageSync("appRole", `${that.options[that.index]}`);
								that.$utils.tab('../index/index');
							}
						}
					})
					
				}
			},
			// #endif
			async styleChange() {
				let rs = await this.$api.getPublic('config/info?name=appLoginBackgroundImg')
				this.indexBgUrl = rs.data?rs.data.value:''
				rs = await this.$api.getPublic('config/info?name=appLoginLogo')
				this.indexLogoUrl = rs.data?rs.data.value:''
			},
			onRegisterTap(tableName) {
				uni.setStorageSync("loginTable", tableName);
				this.$utils.jump('../register/register')
			},
			async onLoginTap() {
				if(this.loginType==1) {
					if (!this.username) {
						this.$utils.msg('请输入用户名')
						return
					}
					if (!this.password) {
						this.$utils.msg('请输入用户密码')
						return
					}
					if (!this.optionsValues[this.index]) {
						this.$utils.msg('请选择登录用户类型')
						return
					}
				}

				this.loginPost()

			},
			async loginPost() {
				let that = this
				let res = await this.$api.login(`${this.optionsValues[this.index]}`, {
					username: this.username,
					password: this.password
				});
				uni.removeStorageSync("useridTag");
				uni.setStorageSync("appToken", res.token);
				uni.setStorageSync("nickname",this.username);
				uni.setStorageSync("nowTable", `${this.optionsValues[this.index]}`);
				res = await this.$api.session(`${this.optionsValues[this.index]}`);
				if(res.data.profile) {
					uni.setStorageSync('frontAvatar', res.data.profile);
				} else if(res.data.avatar) {
					uni.setStorageSync('frontAvatar', res.data.avatar);
				}
				uni.setStorageSync('userSession',JSON.stringify(res.data))
				// 保存用户id
				uni.setStorageSync("appUserid", res.data.id);
				if(res.data.vip) {
					uni.setStorageSync("vip", res.data.vip);
				}
				uni.setStorageSync("appRole", `${this.options[this.index]}`);
				this.$utils.tab('../index/index');
			},
			optionsChange(e) {
				this.index = e.target.value
			}
		}
	}
</script>

<style lang="scss" scoped>
	page {
		height: 100%;
	}
	
	.content {
		height: 100%;
		box-sizing: border-box;
	}
	.login-box {
		padding: 40rpx 48rpx 24rpx 48rpx;
		background: url(http://codegen.caihongy.cn/20250812/39821bee6dcd4abe85d0fb258d710cae.jpg) no-repeat center top / cover,#fff;
		width: 100%;
		min-height: 100%;
		height: auto;
	}
</style>
