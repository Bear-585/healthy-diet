
<template>
<view>
<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback" @scroll="scrollChange">
	<view class="content">
		<view class="container" :style='{"width":"100%","padding":"0","position":"relative","background":"#fff","height":"auto"}'>
			<view :style='{"width":"100%","padding":"20rpx","background":"#fff","height":"auto"}' class="detail-content">


				<view class="detail-list-item" :style='{"width":"100%","margin":"0","background":"#fff","display":"flex","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx","whiteSpace":"nowrap","color":"#000","textAlign":"center","width":"auto","lineHeight":"80rpx","fontSize":"26rpx"}'>关联表id：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"26rpx","color":"rgb(0, 0, 0)"}' >{{detail.refId}}</view>
				</view>
				<view class="detail-list-item" :style='{"width":"100%","margin":"0","background":"#fff","display":"flex","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx","whiteSpace":"nowrap","color":"#000","textAlign":"center","width":"auto","lineHeight":"80rpx","fontSize":"26rpx"}'>置顶(1:置顶,0:非置顶)：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"26rpx","color":"rgb(0, 0, 0)"}' >{{detail.isTop}}</view>
				</view>



				<view class="detail-list-item" :style='{"width":"100%","margin":"0","background":"#fff","display":"flex","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx","whiteSpace":"nowrap","color":"#000","textAlign":"center","width":"auto","lineHeight":"80rpx","fontSize":"26rpx"}'>评论内容：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"26rpx","color":"rgb(0, 0, 0)"}'>{{detail.content}}</view>
				</view>
				<view class="detail-list-item" :style='{"width":"100%","margin":"0","background":"#fff","display":"flex","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx","whiteSpace":"nowrap","color":"#000","textAlign":"center","width":"auto","lineHeight":"80rpx","fontSize":"26rpx"}'>回复内容：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"26rpx","color":"rgb(0, 0, 0)"}'>{{detail.reply}}</view>
				</view>
				<view class="detail-list-item" :style='{"width":"100%","margin":"0","background":"#fff","display":"flex","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx","whiteSpace":"nowrap","color":"#000","textAlign":"center","width":"auto","lineHeight":"80rpx","fontSize":"26rpx"}'>赞用户ids：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"26rpx","color":"rgb(0, 0, 0)"}'>{{detail.tUserIds}}</view>
				</view>
				<view class="detail-list-item" :style='{"width":"100%","margin":"0","background":"#fff","display":"flex","height":"auto"}'>
					<view class="lable" :style='{"padding":"0 20rpx","whiteSpace":"nowrap","color":"#000","textAlign":"center","width":"auto","lineHeight":"80rpx","fontSize":"26rpx"}'>踩用户ids：</view>
					<view class="text" :style='{"padding":"0px","margin":"0px","lineHeight":"80rpx","fontSize":"26rpx","color":"rgb(0, 0, 0)"}'>{{detail.cUserIds}}</view>
				</view>




				<view class="time-content" :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}'>
					<view class="comoment-header" :style='{"width":"100%","justifyContent":"space-between","display":"flex","height":"auto"}'>
						<view :style='{"border":"2rpx solid #FAC696","padding":"0 24rpx","boxShadow":"none","margin":"0 10rpx 0 0","color":"#666","borderRadius":"10rpx","flex":"1","background":"#fff","lineHeight":"80rpx","fontSize":"30rpx"}'>评论</view>
						<view :style='{"border":"0px solid #7D5728","padding":"0 20rpx","boxShadow":"none","borderRadius":"10rpx","background":"#F49843","display":"flex"}' @click="onCommentTap" class="btn-comment-content" style="display: flex;align-items: center;">
							<view :style='{"margin":"0 8rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#fff"}' class="cuIcon-add"></view>
							<view :style='{"color":"#fff","lineHeight":"80rpx","fontSize":"28rpx"}'>添加评论</view>
						</view>
					</view>
				
					<view :style='{"border":"2rpx solid #dddddd","padding":"24rpx","margin":"24rpx 0 0 0","borderRadius":"10rpx","background":"#fff","width":"100%","position":"relative","height":"auto"}' v-for="(item,index) in commentList" v-bind:key="index" class="cu-item comment-item">
						<view :style='{"margin":"0 0 0 -14rpx","position":"absolute","right":"20rpx","top":"20rpx","background":"#fff"}' v-if="item.isTop">
							<span class="icon iconfont icon-jiantou24" :style='{"color":"#000","fontSize":"28rpx"}'></span>
						</view>
						<view :style='{"width":"100%","display":"flex","height":"auto"}'>
							<image :style='{"width":"60rpx","margin":"0 8rpx 0 0","borderRadius":"100%","display":"block","height":"60rpx"}' v-if="item.avatar" mode="aspectFill" :src="baseUrl+item.avatar"></image>
							<view :style='{"color":"#000","lineHeight":"60rpx","fontSize":"28rpx"}' class="text-grey">{{item.nickname}}</view>
						</view>
						<view :style='{"margin":"8rpx 0","lineHeight":"1.5","fontSize":"28rpx","color":"#666"}' class="text-gray text-content text-df">
							<rich-text :nodes="item.content"></rich-text>
						</view>
						<view :style='{"width":"100%","margin":"16rpx 0 0 0","alignItems":"center","justifyContent":"flex-end","display":"flex","height":"60rpx"}'>
							<view v-if="!comzanChange(item)&&!comcaiChange(item)" :style='{"alignItems":"center","padding":"0 10rpx 0 0","display":"flex"}' @click="comzanClick(item)">
								<span class="icon iconfont icon-zan07" :style='{"color":"#999","fontSize":"28rpx"}'></span>
								<span :style='{"fontSize":"28rpx","display":"none"}'>赞</span>
								<span :style='{"color":"#999","fontSize":"28rpx"}'>({{item.thumbsUpNum}})</span>
							</view>
							<view v-if="comzanChange(item)" :style='{"padding":"0 10rpx 0 0","background":"none"}' @click="comzanClick(item)">
								<span class="icon iconfont icon-zan11" :style='{"color":"#F49843","fontSize":"28rpx"}'></span>
								<span :style='{"color":"#F49843","fontSize":"28rpx","display":"none"}'>已赞</span>
								<span :style='{"color":"#F49843","fontSize":"28rpx"}'>({{item.thumbsUpNum}})</span>
							</view>
							<view v-if="!comzanChange(item)&&!comcaiChange(item)" :style='{"alignItems":"center","display":"flex"}' @click="comcaiClick(item)">
								<span class="icon iconfont icon-cai01" :style='{"color":"#999","fontSize":"28rpx"}'></span>
								<span :style='{"fontSize":"28rpx","display":"none"}'>踩</span>
								<span :style='{"color":"#999","fontSize":"28rpx"}'>({{item.crazilyNum}})</span>
							</view>
							<view v-if="comcaiChange(item)" :style='{"background":"none"}' @click="comcaiClick(item)">
								<span class="icon iconfont icon-cai16" :style='{"color":"#797979","fontSize":"28rpx"}'></span>
								<span :style='{"color":"#ff0000","fontSize":"28rpx","display":"none"}'>已踩</span>
								<span :style='{"color":"#797979","fontSize":"28rpx"}'>({{item.crazilyNum}})</span>
							</view>
						</view>
						<view :style='{"color":"#8F8F8F","lineHeight":"24rpx","fontSize":"24rpx"}' class="margin-top-sm text-gray text-df">{{item.createTime}}</view>
						<view v-if="item.reply" :style='{"margin":"8rpx 0","lineHeight":"1.5","fontSize":"28rpx","color":"#666"}' class="text-gray text-content text-df">
							回复:<rich-text :nodes="item.reply"></rich-text>
						</view>
						<view style="display: flex;display: flex;justify-content: flex-end;padding: 6rpx 0;" v-if="user&&user.id==item.userid">
							<view style="color: #999;font-size: 16rpx;" @click="delClick(item.id)">删除</view>
						</view>
					</view>
				</view>

				<view class="bottom-content bg-white tabbar border shop" :style='{"border":"0","padding":"0","borderRadius":"10rpx","flexWrap":"wrap","background":"none","display":"flex","width":"100%","height":"auto"}'>

				</view>
			</view>
		</view>
	</view>
</mescroll-uni>
</view>
</template>

<script>
	export default {
		data() {
			return {
				btnColor: ['#409eff','#67c23a','#909399','#e6a23c','#f56c6c','#356c6c','#351c6c','#f093a9','#a7c23a','#104eff','#10441f','#a21233','#503319'],
				id: '',
				userid: '',
				detail: {},
				swiperList: [],
				commentList: [],
				mescroll: null, //mescroll实例对象
				downOption: {
					auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
					noMoreSize: 3, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
					textNoMore: '~ 没有更多了 ~',
					onScroll: true,
					toTop: true
				},
				hasNext: true,
				user: {},
				count: 0,
				timer: null,
				title:'',
				scrollTop: 0,
			}
		},
		components: {
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
			username() {
				return uni.getStorageSync("nickname")
			},
		},
		async onLoad(options) {
			// #ifdef APP-PLUS
			let page = getCurrentPages()
			this.href = this.baseUrl + 'front/h5/#/' + page[page.length - 1].route
			// #endif
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			this.id = options.id;
			if(options.userid) {
				this.userid = options.userid;
			}
			// 渲染数据
			this.init();
			this.hasNext = true
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
		},
		// #ifdef MP-WEIXIN
		onShareAppMessage(){
			var obj = {
				title: this.title,
				imageUrl: this.swiperList[0]?this.baseUrl + this.swiperList[0]: ''
			}
			return obj
		},
		// #endif
		onUnload() {
			if(this.timer) {
				clearInterval(this.timer);
			}
		},
		async onShow(options) {
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			this.btnColor = this.btnColor.sort(()=> {
				return (0.5-Math.random());
			});
			let cleanType = uni.getStorageSync('discussfoodRecipeCommentCleanType')
			if(cleanType){
				uni.removeStorageSync('discussfoodRecipeCommentCleanType')
				this.mescroll.num = 1
				this.upCallback(this.mescroll)
				this.init(2);
			}
			let crossCleanType = uni.getStorageSync('crossCleanType')
			if(crossCleanType) {
				uni.removeStorageSync('crossCleanType')
				res = await this.$api.info('foodRecipeComment', this.id);
				let reg=new RegExp('http://localhost:8080/healthy_diet/upload','g')//g代表全部
				this.detail = res.data;
			}
		},
		destroyed: function() {
			//window.clearInterval(this.inter);
		},
		methods: {
			scrollChange(e){
				this.scrollTop = e.scrollTop
			},
			scrollTopClick(){
				uni.pageScrollTo({
					scrollTop: 0
				})
			},
			imgView(url){
				let arr = []
				for(let x in this.swiperList){
					arr.push(this.swiperList[x].substr(0,4)=='http'?this.swiperList[x]:this.baseUrl + this.swiperList[x])
				}
				uni.previewImage({
					current: url,
					urls: arr
				})
			},
			// 拨打电话
			callClick(row){
				uni.makePhoneCall({
					phoneNumber: row
				})
			},
			// 支付
			onPayTap(){
				let that = this
				if(!this.user){
					this.$utils.msg("请先登录")
					setTimeout(()=>{
						that.$utils.jump('../login/login')
					},1500)
					return false
				}
				uni.setStorageSync('paytable','foodRecipeComment');
				uni.setStorageSync('payObject',this.detail);
				this.$utils.jump('../pay-confirm/pay-confirm?type=1')
			},
			onDetailTap(item) {
				uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./detail?id=${item.id}&userid=`+this.userid)
			},
			// 跨表
			async onAcrossTap(tableName,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue,type=1){
				let that = this
				if(!this.user){
					this.$utils.msg("请先登录")
					setTimeout(()=>{
						that.$utils.jump('../login/login')
					},1500)
					return false
				}
				uni.setStorageSync('crossTable','foodRecipeComment');
				uni.setStorageSync(`crossObj`, this.detail);
				uni.setStorageSync(`statusColumnName`, statusColumnName);
				uni.setStorageSync(`statusColumnValue`, statusColumnValue);
				uni.setStorageSync(`tips`, tips);
				if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
					var obj = uni.getStorageSync('crossObj');
					for (var o in obj){
						if(o==statusColumnName && obj[o]==statusColumnValue){
							this.$utils.msg(tips);
							return
						}
					}
				}
				this.$utils.jump(`../${tableName}/add-or-update?cross=true`);
			},
			// 获取详情
			async init(type=1){
				if(this.timer) {
					clearInterval(this.timer);
				}
				let res = await this.$api.info('foodRecipeComment', this.id);
				let reg=new RegExp('http://localhost:8080/healthy_diet/upload','g')//g代表全部
				this.detail = res.data;












				if(type==2){
					this.detail.discussNum++
					await this.$api.update('foodRecipeComment',this.detail)
				}
			},
			// mescroll组件初始化的回调,可获取到mescroll对象
			mescrollInit(mescroll) {
				this.mescroll = mescroll;
			},

			/*下拉刷新的回调 */
			downCallback(mescroll) {
				this.hasNext = true
				mescroll.resetUpScroll()
			},

			/*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
			async upCallback(mescroll) {
				if(uni.getStorageSync("appUserid")){
					let res = await this.$api.list('foodRecipeComment', {
						page: mescroll.num,
						limit: 10,
						refId: Number(this.id),
						sort: 'is_top',
						order: 'desc',
					});
					// 如果是第一页数据置空
					if (mescroll.num == 1) this.commentList = [];
					for(let x in res.data.list){
						if(res.data.list[x].content){
							res.data.list[x].content = res.data.list[x].content.replace(/img src/gi,"img style=\"width:100%;\" src")
						}
					}
					this.commentList = this.commentList.concat(res.data.list);
					if (res.data.list.length == 0) this.hasNext = false;
				}
				mescroll.endSuccess(mescroll.size, this.hasNext);

			},
			comzanChange(row){
				if(row.tUserIds){
					let arr = String(row.tUserIds).split(',')
					for(let x in arr){
						if(arr[x] == this.user.id){
							return true
						}
					}
				}
				return false
			},
			async comzanClick(row){
				if(!this.user){
					return false
				}
				if(!this.comzanChange(row)){
					row.thumbsUpNum++
					if(row.tUserIds){
						row.tUserIds = row.tUserIds + ',' + this.user.id
					}else {
						row.tUserIds = this.user.id
					}
					await this.$api.update('foodRecipeComment',row)
					this.$utils.msg('点赞成功');
				}else {
					row.thumbsUpNum--
					let arr = String(row.tUserIds).split(',')
					for(let x in arr){
						if(arr[x] == this.user.id){
							arr.splice(x,1)
						}
					}
					row.tUserIds = arr.join(',')
					await this.$api.update('foodRecipeComment',row)
					this.$utils.msg('取消成功');
				}
				this.$forceUpdate()
			},
			comcaiChange(row){
				if(row.cUserIds){
					let arr = String(row.cUserIds).split(',')
					for(let x in arr){
						if(arr[x] == this.user.id){
							return true
						}
					}
				}
				return false
			},
			async comcaiClick(row){
				if(!this.user){
					return false
				}
				if(!this.comcaiChange(row)){
					row.crazilyNum++
					if(row.cUserIds){
						row.cUserIds = row.cUserIds + ',' + this.user.id
					}else {
						row.cUserIds = this.user.id
					}
					await this.$api.update('foodRecipeComment',row)
					this.$utils.msg('点踩成功');
				}else {
					row.crazilyNum--
					let arr = String(row.cUserIds).split(',')
					for(let x in arr){
						if(arr[x] == this.user.id){
							arr.splice(x,1)
						}
					}
					row.cUserIds = arr.join(',')
					await this.$api.update('foodRecipeComment',row)
					this.$utils.msg('取消成功');
				}
				this.$forceUpdate()
			},



			onChatTap() {
				this.$utils.jump('../chat/chat')
			},
			// 下载
			download(url ){
				if(!url){
					return false
				}
				let _this = this;
				url=_this.$base.url +  url;
				uni.downloadFile({
					url: url,
					success: (res) => {
						if (res.statusCode === 200) {
							_this.$utils.msg('下载成功');
							// #ifdef H5
							 window.open(url);
							// #endif
							// #ifndef H5
							uni.saveFile({
								tempFilePath: res.tempFilePath, //临时路径
								success: function(obj) {
									uni.showToast({
										icon: 'success',
										mask: true,
										title: '下载成功' , 
										duration: 2000,
									});
									setTimeout(() => {
										console.log('obj.savedFilePath',obj.savedFilePath);
										var filePath = obj.savedFilePath;
										uni.openDocument({ //新开页面打开文档，支持格式：doc, xls, ppt, pdf, docx, xlsx, pptx。
											filePath: filePath,
											showMenu: true,
											success: function(res) {
												console.log('打开文档成功');
											}
										});
									}, 2000)
								}
							});
							// #endif
						}
					}
				});
			},
			//
			onCartTabTap() {
				this.$utils.tab('../shop-cart/shop-cart')
			},
			// 添加评论
			async onCommentTap() {
				let that = this
				if(!this.user){
					this.$utils.msg("请先登录")
					setTimeout(()=>{
						that.$utils.jump('../login/login')
					},1500)
					return false
				}
				let res = {}
				this.$utils.jump(`../foodRecipeComment/add-or-update?refId=${this.id}` )
			},
			delClick(id){
				let that = this
				uni.showModal({
					title: '提示',
					content: '是否删除此评论？',
					async success(res) {
						if(res.confirm){
							await that.$api.del('foodRecipeComment',JSON.stringify([id]))
							that.$utils.msg('删除成功')
							that.detail.discussNum--
							await that.$api.update('foodRecipeComment',that.detail)
							setTimeout(()=>{
								that.mescroll.num = 1
								that.upCallback(that.mescroll)
							},1500)
						}
					}
				})
			},

		}
	}
</script>

<style lang="scss">
	page {
	  --animate-duration: 1s;
	  --animate-delay: 1s;
	  --animate-repeat: 1;
	}
	
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
	
	.seat-list {
		display: flex;
		align-items: center;
		flex-wrap: wrap;
		background: #FFFFFF;
		margin: 20upx;
		border-radius: 20upx;
		padding: 20upx;
		font-size: 30upx;
		.seat-item {
			width: 33.33%;
			display: flex;
			align-items: center;
			flex-direction: column;
			margin-bottom: 20upx;
	
			.seat-icon {
				width: 50upx;
				height: 50upx;
				margin-bottom: 10upx;
			}
		}
	}
	
	audio {
		display: flex;
		flex-direction: column;
	}
	
	.audio .uni-audio-default {
		width: inherit !important;
	}
	

</style>
