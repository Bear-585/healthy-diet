<template>
<view class="content">
	<view class="box" :style='{"width":"100%","padding":"0","position":"relative","background":"#fff","height":"auto"}'>
		<view :style='{"width":"100%","padding":"24rpx","background":"none","display":"block","height":"auto"}'>
			<view :style='{"padding":"0px","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='consumer'" class="">
				<view class="title" :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}'>账号</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.account"  v-model="ruleForm.account" placeholder="账号"></input>
			</view>
			<view :style='{"padding":"0px","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='consumer'" class="">
				<view class="title" :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}'>姓名</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.fullName"  v-model="ruleForm.fullName" placeholder="姓名"></input>
			</view>
			<view v-if="tableName=='consumer'" :style='{"padding":"0px","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">性别</view>
				<picker :style='{"width":"100%","flex":"1","height":"auto"}' :disabled="ro.gender" @change="consumergenderChange" :value="consumergenderIndex" :range="consumergenderOptions">
					<view :style='{"width":"100%","padding":"0px 20rpx","lineHeight":"80rpx","fontSize":"28rpx","color":"#F75433"}' class="uni-input picker-select-input">{{ruleForm.gender?ruleForm.gender:"请选择性别"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"0px","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='consumer'" class="">
				<view class="title" :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}'>手机</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.phone"  v-model="ruleForm.phone" placeholder="手机"></input>
			</view>
			<view :style='{"padding":"0px","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='consumer'" class="">
				<view class="title" :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}'>身高/m</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.height"  v-model="ruleForm.height" placeholder="身高/m"></input>
			</view>
			<view :style='{"padding":"0px","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='consumer'" class="">
				<view class="title" :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}'>体重/kg</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.weight"  v-model="ruleForm.weight" placeholder="体重/kg"></input>
			</view>
			<view :style='{"padding":"0px","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='consumer'" class="">
				<view class="title" :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}'>BMI</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.bmi"  v-model="ruleForm.bmi" placeholder="BMI"></input>
			</view>
			<view :style='{"padding":"0px","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='consumer'" @tap="consumerprofileTap" class="">
				<view class="title" :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}'>头像</view>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' v-if="ruleForm.profile" style="margin: 0;" class="avator" :src="baseUrl+ruleForm.profile" mode=""></image>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' v-else class="avator" style="margin: 0;" src="../../static/gen/upload.png" mode=""></image>
			</view>
			<view :style='{"width":"100%","padding":"0","flexWrap":"wrap","justifyContent":"center","display":"flex","height":"auto"}' class="btn">
				<!-- #ifdef MP-WEIXIN -->
				<button open-type="getUserInfo" @getuserinfo="getUserInfo" class="cu-btn lg" :style='{"border":"0","padding":"0px","margin":"0 0 20rpx 0","color":"#ffffff","borderRadius":"8rpx","background":"#2cdc98","width":"100%","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}'>{{ruleForm.openid?'解绑微信':'绑定微信'}}</button>
				<!-- #endif -->
				<button @tap="update()" class="cu-btn lg" :style='{"border":"0","padding":"0px","margin":"0 0 30rpx 0","color":"#fff","borderRadius":"8rpx","background":"#F4BC43","width":"100%","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}'>提交</button>
				<button @tap="logout()" class="cu-btn lg" :style='{"border":"0px dashed #7A531A","padding":"0px","margin":"0 0 30rpx","color":"#7A531A","borderRadius":"8rpx","background":"#FEF7EC","width":"100%","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}'>退出登录</button>
			</view>
		</view>
	</view>
</view>
</template>

<script>

	import xiaEditor from '@/components/xia-editor/xia-editor';
	import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	export default {
		data() {
			return {
				ruleForm: {
				},
				tableName:"",
				ro: {},
				consumergenderOptions: [],
				consumergenderIndex: 0,
				// #ifdef MP-WEIXIN
				code: '',
				// #endif
			}
		},
		components: {
			multipleSelect,
			xiaEditor
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			}
		},
		async onLoad() {
			let table = uni.getStorageSync("nowTable");
			let that = this
			let res = {}
			this.tableName = table;
			this.getSession()
			if(this.tableName=='consumer'){
				// 自定义下拉框值
				this.consumergenderOptions = "男,女".split(',');
				this.consumergenderOptions.forEach((item, index) => {
					if(item==this.ruleForm.gender) {
						this.consumergenderIndex = index;
					}
				});
				this.ro = {
					account: true,
					password: false,
					fullName: false,
					gender: false,
					phone: false,
					height: false,
					weight: false,
					bmi: false,
					profile: false,
					openid: false,
					nickname: false,
					avatar: false,
				}
			}
			// #ifdef MP-WEIXIN
			uni.login({
				provider: 'weixin',
				success(res) {
					that.code = res.code
				}
			})
			// #endif
			this.styleChange()
			this.$forceUpdate()
		},
		methods: {
			// #ifdef MP-WEIXIN
			async getUserInfo(e) {
				let that = this
				if (e.detail.errMsg === "getUserInfo:ok") {
					if(that.ruleForm.openid){
						uni.showModal({
							title: '提示',
							content: '是否解绑微信？',
							success:async (rs)=> {
								if(rs.confirm){
									let res = await that.$api.wxunbind(that.tableName)
									if(res.code==0) that.$utils.msg('解绑成功，可以再次绑定其他账号')
									that.getSession()
								}
							}
						})
						return false
					}
					uni.showModal({
						title: '提示',
						content: '是否绑定微信？',
						success:async (rs)=> {
							if(rs.confirm){
								let params = {
									code: that.code,
									encryptedData: e.detail.encryptedData,
									iv: e.detail.iv,
									rawData: e.detail.rawData,
									signature: e.detail.signature
								}
								let res = await that.$api.wxbind(that.tableName, params)
								if(res.code==0) that.$utils.msg('绑定成功，下次登录可使用微信登录')
								that.getSession()
							}
						}
					})
					
				}
			},
			// #endif
			async getSession(){
				let res = await this.$api.session(this.tableName)
				this.ruleForm = res.data;
				uni.setStorageSync('userSession',JSON.stringify(res.data))
			},
			// 下拉变化
			consumergenderChange(e) {
				this.consumergenderIndex = e.target.value
				this.ruleForm.gender = this.consumergenderOptions[this.consumergenderIndex]
			},
			consumerprofileTap() {
				if(this.ro.profile){
					return false
				}
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.profile = 'upload/' + res.file;
					_this.$forceUpdate();
				});
			},

			toggleTab(str,str1) {
				if(this.ro[str1]){
					return false
				}
				this.$refs[str + str1].show();
			},

			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('. .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.userInfoForm.list.input.backgroundColor
					// })
				})
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			logout() {
				uni.clearStorageSync()
				this.$utils.jump('../login/login');
			},
			// 注册
			async update() {
				if(`consumer` == this.tableName&&(!this.ruleForm.account)){
					this.$utils.msg(`账号不能为空`);
					return
				}
				if(`consumer` == this.tableName&&(!this.ruleForm.password)){
					this.$utils.msg(`密码不能为空`);
					return
				}
				if(`consumer` == this.tableName&&(!this.ruleForm.fullName)){
					this.$utils.msg(`姓名不能为空`);
					return
				}
				if(`consumer` == this.tableName&&(!this.ruleForm.phone)){
					this.$utils.msg(`手机不能为空`);
					return
				}
				if(`consumer` == this.tableName && this.ruleForm.phone&&(!this.$validate.isMobile(this.ruleForm.phone))){
					this.$utils.msg(`手机应输入手机格式`);
					return
				}
				let table = uni.getStorageSync("nowTable");
				await this.$api.update(table, this.ruleForm);
				this.$utils.msgBack('修改成功');;
				this.getSession()
			},
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
</style>
