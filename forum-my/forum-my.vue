<template>
	<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback" @scroll="scrollChange">
		<!--header end-->
		<view class="list" :style='{"width":"100%","padding":"20rpx 20rpx 80rpx","flex":"1"}'>
			<view style="position: relative" v-for="(item,index) in list " v-bind:key="index" :style='{"boxShadow":"0 6rpx 10rpx #cccccc","padding":"30rpx","margin":"0 0 30rpx 0","borderRadius":"12rpx","background":"#fff","display":"flex","justifyContent":"space-between"}'>
				<view :style='{"margin":"0 0 0 -14rpx","position":"absolute","right":"20rpx","top":"20rpx","background":"#fff"}' v-if="item.isTop">
					<span class="icon iconfont icon-jiantou24" :style='{"color":"#000","fontSize":"28rpx"}'></span>
				</view>
				<image v-if="item.cover" :src="baseUrl + item.cover.split(',')[0]" mode="widthFix" :style='{"width":"176rpx","margin":"10rpx 0 0","borderRadius":"10rpx","height":"176rpx"}'></image>
				<view :style='{"width":"calc(100% - 216rpx)"}'>
					<view :style='{"color":"#000","margin":"0","fontWeight":"bold"}'>{{item.title}}</view>
					<view :style='{"width":"100%","margin":"0"}'>
						<button v-if="item.isDone=='开放'" :style='{"padding":"0 20rpx","margin":"10rpx 0","color":"#fff","borderRadius":"10rpx","background":"#F49843","display":"inline-flex","lineHeight":"50rpx","fontSize":"20rpx","height":"50rpx"}'>公开</button>
						<button v-if="item.isDone=='关闭'" :style='{"padding":"0 20rpx","margin":"10rpx 0","color":"#fff","borderRadius":"10rpx","background":"#F75433","display":"inline-flex","lineHeight":"50rpx","fontSize":"20rpx","height":"50rpx"}'>私人</button>
					</view>
					<view :style='{"width":"100%","margin":"0"}' v-if="item.delFlag==1" style="color: #f00;font-weight: bold;font-size: 12px">
						违规删除
					</view>
					<view :style='{"color":"#bbb","lineHeight":"2","fontSize":"26rpx"}'>
						发布时间：{{item.createTime.split(' ')[0]}}					</view>
					<view :style='{"width":"100%","margin":"0 0 16rpx","display":"flex"}'>
						<button @tap="onDetailTap(item.id)" :style='{"padding":"0 30rpx","boxShadow":"none","color":"#fff","borderRadius":"10rpx","background":"#F49843","lineHeight":"50rpx","fontSize":"28rpx","height":"50rpx"}'>查看</button>
						<button @tap="onUpdateTap(item.id)" :style='{"padding":"0 30rpx","boxShadow":"none","margin":"0 10rpx","color":"#fff","borderRadius":"10rpx","background":"#F75433","lineHeight":"50rpx","fontSize":"28rpx","height":"50rpx"}'>修改</button>
						<button @tap="onDeleteTap(item.id)" :style='{"padding":"0 30rpx","boxShadow":"none","margin":"0","color":"#999898","borderRadius":"10rpx","background":"#EDEDED","lineHeight":"50rpx","fontSize":"28rpx","height":"50rpx"}'>删除</button>
					</view>
				</view>
			</view>
		</view>
		<!--list end-->
		<button :style='{"border":"0","boxShadow":"0 2rpx 12rpx rgba(0,0,0,.3)","color":"rgb(255, 255, 255)","textAlign":"center","bottom":"160rpx","right":"20rpx","outline":"none","borderRadius":"100%","background":"#F49843","width":"80rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"fixed","height":"80rpx","zIndex":"999"}' @click="onUpdateTap()">新增</button>
		<view v-if="scrollTop>200" @tap="scrollTopClick" :style='{"boxShadow":"0 8rpx 16rpx rgba(0,0,0,.3)","borderRadius":"50%","textAlign":"center","bottom":"20%","background":"#ff000030","width":"60rpx","lineHeight":"60rpx","position":"fixed","right":"20rpx","height":"60rpx","zIndex":"999"}'>
			<span class="icon iconfont icon-jiantou07" :style='{"color":"#fff"}'></span>
		</view>
	</mescroll-uni>
</template>

<script>
	import '@/assets/css/global-restaurant.css'
	import uniIcons from "@/components/uni-ui/lib/uni-icons/uni-icons.vue"
	import uniNoticeBar from "@/components/uni-ui/lib/uni-notice-bar/uni-notice-bar.vue"
	export default {
		components: {
			uniIcons,
			uniNoticeBar
		},
		data() {
			return {
				//轮播
				swiperList: [],
				list: [],
				mescroll: null, //mescroll实例对象
				downOption: {
					auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
					page: {
						// num: 0, // 当前页码,默认0,回调之前会加1,即callback(page)会从1开始
						// size: 5 // 每页数据的数量
					},
					noMoreSize: 3, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
					textNoMore: '~ 没有更多了 ~',
					onScroll: true,
					toTop: false
				},
				hasNext: true,
				scrollTop: 0
			}
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			}
		},
		async onShow() {
			this.hasNext = true
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
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
			// mescroll组件初始化的回调,可获取到mescroll对象
			mescrollInit(mescroll) {
				this.mescroll = mescroll;
			},
			/*下拉刷新的回调 */
			downCallback(mescroll) {
				this.hasNext = true
				// 重置分页参数页数为1
				mescroll.resetUpScroll()
			},
			/*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
			async upCallback(mescroll) {
				let res = await this.$api.myForum(mescroll.num, mescroll.size);
				// 如果是第一页数据置空
				if (mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				if (res.data.list.length == 0) this.hasNext = false;
				mescroll.endSuccess(mescroll.size, this.hasNext);
			},
			// 详情
			onDetailTap(id) {
				this.$utils.jump(`../forum-detail/forum-detail?id=${id}`)
			},
			onUpdateTap(id) {
				if(id){
					this.$utils.jump(`../forum-add-or-update/forum-add-or-update?id=${id}`)
				}else{
					this.$utils.jump(`../forum-add-or-update/forum-add-or-update`)
				}
			},
			onDeleteTap(id) {
				let that = this;
				uni.showModal({
					title: '提示',
					content: '是否确认删除',
					success: async function(res) {
						if (res.confirm) {
							await that.$api.del('forum',JSON.stringify([id]));
							let rs2 = await that.$api.list('storeUp',{
								page: 1,
								limit: 100,
								tableName: 'forum',
								refId: id,
							})
							if(rs2.data.list.length){
								let arr = []
								for(let x in rs2.data.list){
									arr.push(rs2.data.list[x].id)
								}
								await that.$api.del('storeUp',JSON.stringify(arr))
							}
							that.$utils.msg('删除成功');
							that.hasNext = true
							// 重新加载数据
							if (that.mescroll) that.mescroll.resetUpScroll()
						}
					}
				});
			}
		}
	}
</script>

<style>
	view {
		font-family: '\5FAE\8F6F\96C5\9ED1';
		font-size: 30upx;
	}

	page {
		background: #ededed;
	}

	.header {
		background: #f39800;
		padding: 15upx 20upx 255upx;
		position: relative;
	}

	.ssbox {
		background: rgba(255, 255, 255, 0.35);
		width: 530upx;
		border-radius: 60rpx;
		padding: 10upx 15upx;
		box-sizing: border-box;
	}

	.ss_input {
		border: none;
		width: 450upx;
		font-size: 30upx;
		color: #fff;
		background: none;
		height: 45upx;
		line-break: 45upx;
	}

	.headerb {
		position: absolute;
		left: 0;
		top: 60upx;
		width: 100%;
		box-sizing: border-box;
		padding: 0 20upx;
	}

	.headerb image {
		width: 710upx;
		box-shadow: 0 10upx 10px rgba(0, 0, 0, 0.3);
		border-radius: 40upx;
		position: relative;
		z-index: 10;
	}

	.notice {
		position: relative;
		z-index: 5;
		padding: 0 50upx;
	}

	.noticem {
		background: #fff;
		padding: 55upx 30upx 15upx;
		border-radius: 10upx;
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
		margin-top: -45upx;
	}

	.noticer {
		width: 480upx;
		height: 50upx;
	}

	.noticer .swiper-item {
		white-space: nowrap;
		text-overflow: ellipsis;
		overflow: hidden;
		height: 50upx;
		line-height: 50upx;
		font-size: 24upx;
	}

	.list {
		padding: 60upx 20upx 20upx;
	}

	.listm {
		background: #fff;
		border-radius: 15upx;
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
		margin-bottom: 20upx;
		padding: 30upx;
	}

	.listmpic {
		border-radius: 10upx;
		width: 166upx;
	}

	.listmr {
		width: 460upx;
		display: inline-block;
	}

	/* #ifdef MP-WEIXIN */
	.noticer .swiper-item {
		margin-top: 5upx;
	}

	/* #endif */
	/* #ifdef MP-BAIDU */
	.noticer .swiper-item {
		margin-top: 3upx;
	}

	/* #endif */
	/* #ifdef MP-ALIPAY */
	.noticer .swiper-item {
		margin-top: 2upx;
	}

	/* #endif */
	/* #ifdef MP-QQ */
	.noticer .swiper-item {
		margin-top: 4upx;
	}

	/* #endif */
	/* #ifdef MP-TOUTIAO */
	.noticer .swiper-item {
		margin-top: 4upx;
	}

	.add-btn {
		position: fixed;
		left: 30upx;
		right: 30upx;
		bottom: 120upx;
		z-index: 95;
		display: flex;
		align-items: center;
		justify-content: center;
		width: 690upx;
		height: 80upx;
		font-size: 32upx;
		color: #fff;
		background-color: red;
		border-radius: 10upx;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
	}
	/* #endif */
</style>
