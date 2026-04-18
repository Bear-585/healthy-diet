  

<template>
<view class="content">
	<view :style='{"width":"100%","padding":"0","position":"relative","background":"#fff","height":"auto"}' v-if="showType==1">
		<form :style='{"width":"100%","padding":"24rpx","background":"#fff","display":"block","height":"auto"}' class="app-update-pv">






















           
			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","flexWrap":"wrap","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">评论内容</view>
				<xia-editor ref="content" :style='{"minHeight":"300rpx","border":"0px solid #efefef","width":"100%","padding":"0 24rpx","height":"auto"}' v-model="ruleForm.content" placeholder="评论内容" @editorChange="contentChange"></xia-editor>
			</view>
			
			<view :style='{"width":"100%","padding":"0","flexWrap":"wrap","justifyContent":"space-between","display":"flex","height":"auto"}' class="btn" >
				<button :style='{"border":"0","padding":"0px","margin":"0 0 30rpx","color":"#fff","borderRadius":"8rpx","background":"#F4BC43","width":"100%","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}' @tap="onSubmitTap(null)" class="bg-red">提交</button>
			</view>
		</form>
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
				refId: '',
				userid: '',
				avatar: '',
				nickname: '',
				content: '',
				reply: '',
				isTop: '',
				tUserIds: '',
				cUserIds: '',
				},
				// 登录用户信息
				user: {},
				ro:{
				   refId : false,
				   userid : false,
				   avatar : false,
				   nickname : false,
				   content : false,
				   reply : false,
				   thumbsUpNum : false,
				   crazilyNum : false,
				   isTop : false,
				   tUserIds : false,
				   cUserIds : false,
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
					if(o=='refId'){
						this.ruleForm.refId = obj[o];
						this.ro.refId = true;
						continue;
					}
					if(o=='userid'){
						this.ruleForm.userid = obj[o];
						this.ro.userid = true;
						continue;
					}
					if(o=='avatar'){
						this.ruleForm.avatar = obj[o].split(",")[0];
						this.ro.avatar = true;
						continue;
					}
					if(o=='nickname'){
						this.ruleForm.nickname = obj[o];
						this.ro.nickname = true;
						continue;
					}
					if(o=='content'){
						this.ruleForm.content = obj[o];
						this.ro.content = true;
						continue;
					}
					if(o=='reply'){
						this.ruleForm.reply = obj[o];
						this.ro.reply = true;
						continue;
					}
					if(o=='thumbsUpNum'){
						this.ruleForm.thumbsUpNum = obj[o];
						this.ro.thumbsUpNum = true;
						continue;
					}
					if(o=='crazilyNum'){
						this.ruleForm.crazilyNum = obj[o];
						this.ro.crazilyNum = true;
						continue;
					}
					if(o=='isTop'){
						this.ruleForm.isTop = obj[o];
						this.ro.isTop = true;
						continue;
					}
					if(o=='tUserIds'){
						this.ruleForm.tUserIds = obj[o];
						this.ro.tUserIds = true;
						continue;
					}
					if(o=='cUserIds'){
						this.ruleForm.cUserIds = obj[o];
						this.ro.cUserIds = true;
						continue;
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
				res = await this.$api.info(`foodRecipeComment`, this.ruleForm.id);
				this.ruleForm = res.data;
			}
			this.$forceUpdate()
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




			avatarTap() {
				if(this.ro.avatar){
					return false
				}
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.avatar = 'upload/' + res.file;
					_this.$forceUpdate();
				});
			},

			getUUID () {
				return new Date().getTime();
			},
			async onSubmitTap(subMitType=null) {
				let that = this
				this.ruleForm.avatar = uni.getStorageSync('frontAvatar')?uni.getStorageSync('frontAvatar'):'';
				//跨表计算判断
				var obj;
				if((!this.ruleForm.refId)){
					this.$utils.msg(`关联表id不能为空`);
					return
				}
				if((!this.ruleForm.userid)){
					this.$utils.msg(`用户id不能为空`);
					return
				}
				if((!this.ruleForm.content)){
					this.$utils.msg(`评论内容不能为空`);
					return
				}
				if(this.ruleForm.thumbsUpNum&&(!this.$validate.isIntNumer(this.ruleForm.thumbsUpNum))){
					this.$utils.msg(`赞应输入整数`);
					return
				}
				if(this.ruleForm.crazilyNum&&(!this.$validate.isIntNumer(this.ruleForm.crazilyNum))){
					this.$utils.msg(`踩应输入整数`);
					return
				}
				if(this.ruleForm.isTop&&(!this.$validate.isIntNumer(this.ruleForm.isTop))){
					this.$utils.msg(`置顶(1:置顶,0:非置顶)应输入整数`);
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
						let res = await this.$api.list(`foodRecipeComment`, params);
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
					await this.$api.update(`foodRecipeComment`, this.ruleForm);
				}else{
					oet = await this.$api.add(`foodRecipeComment`, this.ruleForm);
				}
				uni.setStorageSync('foodRecipeCommentCleanType',true);
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
