  

<template>
<view class="content">
	<view :style='{"width":"100%","padding":"0","position":"relative","background":"#fff","height":"auto"}' v-if="showType==1">
		<form :style='{"width":"100%","padding":"24rpx","background":"#fff","display":"block","height":"auto"}' class="app-update-pv">














			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">标题</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.title" v-model="ruleForm.title" placeholder="标题"  type="text"></input>
			</view>
			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">类型</view>
				<picker :disabled="ro.type" :style='{"width":"100%","flex":"1","height":"auto"}' @change="typeChange" :value="typeIndex" :range="typeOptions">
					<view :style='{"width":"100%","padding":"0 20rpx","lineHeight":"80rpx","fontSize":"28rpx","color":"#F75433"}' class="uni-input">{{ruleForm.type?ruleForm.type:"请选择类型"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">提醒时间</view>
				<input :disabled="ro.remindTime" :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' v-model="ruleForm.remindTime" placeholder="提醒时间" @tap="toggleTab('remindTime')"></input>
			</view>
       
			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">简介</view>
				<textarea :style='{"border":"0","padding":"24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"300rpx"}' v-model="ruleForm.brief" placeholder="简介" :maxlength="-1" :disabled="ro.brief"></textarea>
			</view>
			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","flexWrap":"wrap","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">内容</view>
				<xia-editor ref="content" :style='{"minHeight":"300rpx","border":"0px solid #efefef","width":"100%","padding":"0 24rpx","height":"auto"}' v-model="ruleForm.content" placeholder="内容" @editorChange="contentChange"></xia-editor>
			</view>
			
			<view :style='{"width":"100%","padding":"0","flexWrap":"wrap","justifyContent":"space-between","display":"flex","height":"auto"}' class="btn" >
				<button :style='{"border":"0","padding":"0px","margin":"0 0 30rpx","color":"#fff","borderRadius":"8rpx","background":"#F4BC43","width":"100%","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}' @tap="onSubmitTap(null)" class="bg-red">提交</button>
			</view>
		</form>
		<w-picker  mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="remindTimeConfirm" ref="remindTime" themeColor="#333333"></w-picker>
	</view>
</view>
</template>

<script>
	import wPicker from "@/components/w-picker/w-picker.vue";
	import xiaEditor from '@/components/xia-editor/xia-editor';
	import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	export default {
		data() {
			return {
				cross:'',
				ruleForm: {
				userid: '',
				role: '',
				title: '',
				type: '',
				brief: '',
				content: '',
				remindTime: '',
				},
				typeOptions: [],
				typeIndex: 0,
				// 登录用户信息
				user: {},
				ro:{
				   userid : false,
				   role : true,
				   title : false,
				   type : true,
				   brief : false,
				   content : false,
				   remindTime : false,
				},
				virtualPay: false,
				showType: 1,
			}
		},
		components: {
			wPicker,
			xiaEditor,
			multipleSelect,
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
			sessionForm() {
				return uni.getStorageSync("appUserid")?JSON.parse(uni.getStorageSync('userSession')):{}
			},



		},
		async onLoad(options) {
			if(options.virtualPay){
				this.virtualPay = true
			}
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			

			// 跨表
			this.cross = options.cross;
			if(options.cross){
				var obj = uni.getStorageSync('crossObj');
				for (var o in obj){
					if(o=='userid'){
						this.ruleForm.userid = obj[o];
						this.ro.userid = true;
						continue;
					}
					if(o=='role'){
						this.ruleForm.role = obj[o];
						this.ro.role = true;
						continue;
					}
					if(o=='title'){
						this.ruleForm.title = obj[o];
						this.ro.title = true;
						continue;
					}
					if(o=='type'){
						this.ruleForm.type = obj[o];
						for(let x in this.typeOptions) {
							if(this.typeOptions[x] == this.ruleForm.type) {
								this.typeIndex = Number(x)
							}
						}
						this.ro.type = true;
						continue;
					}
					if(o=='brief'){
						this.ruleForm.brief = obj[o];
						this.ro.brief = true;
						continue;
					}
					if(o=='content'){
						this.ruleForm.content = obj[o];
						this.ro.content = true;
						continue;
					}
					if(o=='remindTime'){
						this.ruleForm.remindTime = obj[o];
						this.ro.remindTime = true;
						continue;
					}
				}
			}

			// 自定义下拉框值
			this.typeOptions = "个人,全局".split(',')
			this.typeOptions.unshift("请选择类型");
			if(this.ruleForm.type) {
				for(let x in this.typeOptions) {
					if(this.typeOptions[x] == this.ruleForm.type) {
						this.typeIndex = Number(x)
					}
				}
			}

			// 如果有登录，获取登录后保存的userid
			this.ruleForm.userid = uni.getStorageSync("appUserid")
			if (options.refId) {
				// 如果上一级页面传递了refId，获取改refId数据信息
				this.ruleForm.refId = Number(options.refId);
				this.ruleForm.nickname = uni.getStorageSync("nickname");
			}
			// 如果是更新操作
			if (options.id) {
				this.ruleForm.id = options.id;
				// 获取信息
				res = await this.$api.info(`popupRemind`, this.ruleForm.id);
				if(res.data.type) {
					for(let x in this.typeOptions) {
						if(this.typeOptions[x] == res.data.type) {
							this.typeIndex = Number(x)
						}
					}
				}
				this.ruleForm = res.data;
			}
			this.$forceUpdate()
			this.ruleForm.type = '个人'
			this.typeIndex = 1
			this.ruleForm.role = uni.getStorageSync("appRole")
			if (uni.getStorageSync('raffleType') && uni.getStorageSync('raffleType') != null) {
				uni.removeStorageSync('raffleType')
				setTimeout(() => {
					this.onSubmitTap(null)
				}, 300)
			}
		},
		methods: {
			numberChange(e) {
				e = Number(e)
			},
			contentChange(e) {
				this.ruleForm.content = e
			},

			// 多级联动参数


			// 日长控件选择日期时间
			remindTimeConfirm(val) {
				this.ruleForm.remindTime = val.result;
				this.$forceUpdate();
			},

			// 下拉变化
			typeChange(e) {
				this.typeIndex = e.target.value
				this.ruleForm.type = this.typeOptions[this.typeIndex]
			},


			getUUID () {
				return new Date().getTime();
			},
			async onSubmitTap(subMitType=null) {
				let that = this
				//跨表计算判断
				var obj;
				if((!this.ruleForm.userid)){
					this.$utils.msg(`发布人id不能为空`);
					return
				}
				if((!this.ruleForm.title)){
					this.$utils.msg(`标题不能为空`);
					return
				}
				if((!this.ruleForm.content)){
					this.$utils.msg(`内容不能为空`);
					return
				}
				//更新跨表属性
				var crossuserid;
				var crossrefId;
				var crossoptnum;
				if(this.cross){
					var statusColumnName = uni.getStorageSync('statusColumnName');
					var statusColumnValue = uni.getStorageSync('statusColumnValue');
					if(statusColumnName!='') {
						if(!obj) {
							obj = uni.getStorageSync('crossObj');
						}
						if(!statusColumnName.startsWith("[")) {
							for (var o in obj){
								if(o==statusColumnName){
									obj[o] = statusColumnValue;
								}
							}
							var table = uni.getStorageSync('crossTable');
							await this.$api.update(`${table}`, obj);
						} else {
								crossuserid=Number(uni.getStorageSync('appUserid'));
								crossrefId=obj['id'];
								crossoptnum=uni.getStorageSync('statusColumnName');
								crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
						}
					}
					if(crossrefId && crossuserid) {
						this.ruleForm.crossuserid=crossuserid;
						this.ruleForm.crossrefId=crossrefId;
						let params = {
							page: 1,
							limit:10,
							crossuserid:crossuserid,
							crossrefId:crossrefId,
						}
						let res = await this.$api.list(`popupRemind`, params);
						if (res.data.total >= crossoptnum) {
							this.$utils.msg(uni.getStorageSync('tips'));
							uni.removeStorageSync('crossCleanType');
							return false;
						}
					}
					//跨表计算
				}
				let oet = {}
				if(this.ruleForm.id){
					await this.$api.update(`popupRemind`, this.ruleForm);
				}else{
					oet = await this.$api.add(`popupRemind`, this.ruleForm);
				}
				if(this.cross){
					uni.setStorageSync('crossCleanType',true);
				}
				that.$utils.msgBack('提交成功');
			},
			optionsChange(e) {
				this.index = e.target.value
			},
			bindDateChange(e) {
				this.date = e.target.value
			},
			getDate(type) {
				const date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				if (type === 'start') {
					year = year - 60;
				} else if (type === 'end') {
					year = year + 2;
				}
				month = month > 9 ? month : '0' + month;;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
			},
			toggleTab(str) {
				if(this.ro[str]){
					return false
				}
				this.$refs[str].show();
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
