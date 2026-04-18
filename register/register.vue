<template>
<view class="content">
	<view class="register-box" :style="{'backgroundImage': indexBgUrl?`url(${$base.url + indexBgUrl})`:''}">
		<view :style='{"padding":"40rpx","borderRadius":"20rpx","flexWrap":"wrap","background":"#ffffff80","display":"flex","width":"100%","position":"relative","justifyContent":"center","height":"auto"}'>
			<image :style='{"width":"160rpx","margin":"0 auto 24rpx auto","borderRadius":"8rpx","display":"none","height":"160rpx"}' :src="indexLogoUrl?($base.url + indexLogoUrl):'http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg'" mode="aspectFill"></image>
			<view :style='{"width":"100%","margin":"0 0 4rpx 0","flexWrap":"wrap","display":"flex","height":"auto","order":"1"}' v-if="tableName=='consumer'" class="uni-form-item uni-column">
				<view :style='{"width":"100%","lineHeight":"40rpx","fontSize":"26rpx","color":"#000","textAlign":"left"}' class="label">账号：</view>
				<input :style='{"border":"4rpx solid #FAC69680","padding":"0px 24rpx","margin":"0px","color":"#9D835E","borderRadius":"10rpx","flex":"1","background":"#fff","fontSize":"28rpx","height":"84rpx"}' :disabled="ro.account" v-model="ruleForm.account"  type="text"  class="uni-input" name="" placeholder="账号" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 4rpx 0","flexWrap":"wrap","display":"flex","height":"auto","order":"1"}' v-if="tableName=='consumer'" class="uni-form-item uni-column">
				<view :style='{"width":"100%","lineHeight":"40rpx","fontSize":"26rpx","color":"#000","textAlign":"left"}' class="label">密码：</view>
				<input :style='{"border":"4rpx solid #FAC69680","padding":"0px 24rpx","margin":"0px","color":"#9D835E","borderRadius":"10rpx","flex":"1","background":"#fff","fontSize":"28rpx","height":"84rpx"}' :disabled="ro.password" v-model="ruleForm.password" type="password"  class="uni-input" name="" placeholder="密码" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 4rpx 0","flexWrap":"wrap","display":"flex","height":"auto","order":"1"}' v-if="tableName=='consumer'" class="uni-form-item uni-column">
				<view :style='{"width":"100%","lineHeight":"40rpx","fontSize":"26rpx","color":"#000","textAlign":"left"}' class="label">确认密码：</view>
				<input :style='{"border":"4rpx solid #FAC69680","padding":"0px 24rpx","margin":"0px","color":"#9D835E","borderRadius":"10rpx","flex":"1","background":"#fff","fontSize":"28rpx","height":"84rpx"}' v-model="ruleForm.password2" type="password" class="uni-input" name="" placeholder="确认密码" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 4rpx 0","flexWrap":"wrap","display":"flex","height":"auto","order":"1"}' v-if="tableName=='consumer'" class="uni-form-item uni-column">
				<view :style='{"width":"100%","lineHeight":"40rpx","fontSize":"26rpx","color":"#000","textAlign":"left"}' class="label">姓名：</view>
				<input :style='{"border":"4rpx solid #FAC69680","padding":"0px 24rpx","margin":"0px","color":"#9D835E","borderRadius":"10rpx","flex":"1","background":"#fff","fontSize":"28rpx","height":"84rpx"}' :disabled="ro.fullName" v-model="ruleForm.fullName"  type="text"  class="uni-input" name="" placeholder="姓名" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 4rpx 0","flexWrap":"wrap","display":"flex","height":"auto","order":"1"}' v-if="tableName=='consumer'" class="">
				<view :style='{"width":"100%","lineHeight":"40rpx","fontSize":"26rpx","color":"#000","textAlign":"left"}' class="label">性别：</view>
				<picker :style='{"width":"100%","margin":"0 0 24rpx 0","flexWrap":"wrap","display":"flex","height":"auto","order":"3"}' v-if="tableName=='consumer'" :disabled="ro.gender" @change="consumergenderChange" :value="consumergenderIndex" :range="consumergenderOptions">
					<view>
						<view :style='{"border":"4rpx solid #FAC69680","padding":"0px 24rpx","color":"#9D835E","borderRadius":"10rpx","flex":"1","background":"#fff","lineHeight":"76rpx","fontSize":"28rpx"}' class="uni-input">{{ruleForm.gender?ruleForm.gender:"请选择性别"}}</view>
					</view>
				</picker>
			</view>
			<view :style='{"width":"100%","margin":"0 0 4rpx 0","flexWrap":"wrap","display":"flex","height":"auto","order":"1"}' v-if="tableName=='consumer'" class="uni-form-item uni-column">
				<view :style='{"width":"100%","lineHeight":"40rpx","fontSize":"26rpx","color":"#000","textAlign":"left"}' class="label">手机：</view>
				<input :style='{"border":"4rpx solid #FAC69680","padding":"0px 24rpx","margin":"0px","color":"#9D835E","borderRadius":"10rpx","flex":"1","background":"#fff","fontSize":"28rpx","height":"84rpx"}' :disabled="ro.phone" v-model="ruleForm.phone"  type="text"  class="uni-input" name="" placeholder="手机" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 4rpx 0","flexWrap":"wrap","display":"flex","height":"auto","order":"1"}' v-if="tableName=='consumer'" class="uni-form-item uni-column">
				<view :style='{"width":"100%","lineHeight":"40rpx","fontSize":"26rpx","color":"#000","textAlign":"left"}' class="label">身高/m：</view>
				<input :style='{"border":"4rpx solid #FAC69680","padding":"0px 24rpx","margin":"0px","color":"#9D835E","borderRadius":"10rpx","flex":"1","background":"#fff","fontSize":"28rpx","height":"84rpx"}' :disabled="ro.height" v-model="ruleForm.height"  type="text"  class="uni-input" name="" placeholder="身高/m" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 4rpx 0","flexWrap":"wrap","display":"flex","height":"auto","order":"1"}' v-if="tableName=='consumer'" class="uni-form-item uni-column">
				<view :style='{"width":"100%","lineHeight":"40rpx","fontSize":"26rpx","color":"#000","textAlign":"left"}' class="label">体重/kg：</view>
				<input :style='{"border":"4rpx solid #FAC69680","padding":"0px 24rpx","margin":"0px","color":"#9D835E","borderRadius":"10rpx","flex":"1","background":"#fff","fontSize":"28rpx","height":"84rpx"}' :disabled="ro.weight" v-model="ruleForm.weight"  type="text"  class="uni-input" name="" placeholder="体重/kg" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 4rpx 0","flexWrap":"wrap","display":"flex","height":"auto","order":"1"}' v-if="tableName=='consumer'" class="uni-form-item uni-column">
				<view :style='{"width":"100%","lineHeight":"40rpx","fontSize":"26rpx","color":"#000","textAlign":"left"}' class="label">BMI：</view>
				<input :style='{"border":"4rpx solid #FAC69680","padding":"0px 24rpx","margin":"0px","color":"#9D835E","borderRadius":"10rpx","flex":"1","background":"#fff","fontSize":"28rpx","height":"84rpx"}' :disabled="ro.bmi" v-model="ruleForm.bmi"  type="text"  class="uni-input" name="" placeholder="BMI" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 4rpx 0","flexWrap":"wrap","display":"flex","height":"auto","order":"1"}' v-if="tableName=='consumer'" @tap="consumerprofileTap" class="">
				<view :style='{"width":"100%","lineHeight":"40rpx","fontSize":"26rpx","color":"#000","textAlign":"left"}' class="label">头像：</view>
				<image :style='{"width":"140rpx","margin":"0","borderRadius":"16rpx","display":"block","height":"140rpx"}' v-if="ruleForm.profile" class="avator" :src="baseUrl+ruleForm.profile" mode=""></image>
				<image :style='{"width":"140rpx","margin":"0","borderRadius":"16rpx","display":"block","height":"140rpx"}' v-else class="avator" src="../../static/gen/upload.png" mode=""></image>
			</view>
			<button :style='{"border":"0","padding":"0px","margin":"48rpx auto","color":"#fff","borderRadius":"8rpx","background":"#F4BC43","width":"100%","lineHeight":"80rpx","fontSize":"32rpx","height":"80rpx","order":"4"}' class="btn-submit" @tap="register" type="primary">注册</button>
			
			<view class="idea1" :style='{"color":"#7A531A","textAlign":"center","display":"none","width":"100%","fontSize":"40rpx","fontWeight":"bold","height":"80rpx"}'>欢迎注册</view>
			<view class="idea2" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea2</view>
			<view class="idea3" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea3</view>
		</view>
	</view>
</view>
</template>

<script>
	import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	export default {
		data() {
			return {
				consumergenderOptions: [],
				consumergenderIndex: 0,
				ruleForm: {
				account: '',
				password: '',
				fullName: '',
				gender: '',
				phone: '',
				height: '',
				weight: '',
				bmi: '',
				profile: '',
				openid: '',
				nickname: '',
				avatar: '',
				},
				ro: {},
				tableName:"",
				indexBgUrl: '',
				indexLogoUrl: '',
			}
		},
		components: {
			multipleSelect
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
		async onLoad() {
			let res = [];
			let table = uni.getStorageSync("loginTable");
			this.tableName = table;

			if(this.tableName=='consumer'){
				// 自定义下拉框值
				this.consumergenderOptions = "男,女".split(',');
				this.ruleForm.gender=this.consumergenderOptions[0]
				this.ro = {
					account: false,
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
			
			this.styleChange()
		},
		methods: {

			// 下拉变化
			consumergenderChange(e) {
					this.consumergenderIndex = e.target.value
					this.ruleForm.gender = this.consumergenderOptions[this.consumergenderIndex]
			},
			consumerprofileTap() {
				if(this.ro.profile) {
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

			async styleChange() {
				let rs = await this.$api.getPublic('config/info?name=appRegisterBackgroudImg')
				this.indexBgUrl = rs.data?rs.data.value:''
				rs = await this.$api.getPublic('config/info?name=appRegLogo')
				this.indexLogoUrl = rs.data?rs.data.value:''
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 注册
			async register() {
				
				if((!this.ruleForm.account) && `consumer` == this.tableName){
					this.$utils.msg(`账号不能为空`);
					return
				}
				if((!this.ruleForm.password) && `consumer` == this.tableName){
					this.$utils.msg(`密码不能为空`);
					return
				}
				if(`consumer` == this.tableName && (this.ruleForm.password!=this.ruleForm.password2)){
					this.$utils.msg(`两次密码输入不一致`);
					return
				}
				if((!this.ruleForm.fullName) && `consumer` == this.tableName){
					this.$utils.msg(`姓名不能为空`);
					return
				}
				if((!this.ruleForm.phone) && `consumer` == this.tableName){
					this.$utils.msg(`手机不能为空`);
					return
				}
				if(`consumer` == this.tableName && this.ruleForm.phone&&(!this.$validate.isMobile(this.ruleForm.phone))){
					this.$utils.msg(`手机应输入手机格式`);
					return
				}
				await this.$api.register(`${this.tableName}`, this.ruleForm);
				this.$utils.msgBack('注册成功');;
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
	.register-box{
		padding: 40rpx 48rpx 24rpx 48rpx;
		background: url(http://codegen.caihongy.cn/20250812/39821bee6dcd4abe85d0fb258d710cae.jpg) no-repeat center top / cover,#fff;
		width: 100%;
		min-height: 100%;
		height: auto;
	}
</style>
