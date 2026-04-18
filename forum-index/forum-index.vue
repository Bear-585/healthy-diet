<template>
	<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback" @scroll="scrollChange">

		<view :style='{"width":"100%","padding":"0 0 200rpx 0","flexWrap":"wrap","background":"#fff","display":"flex"}'>
			<!--header end-->
			<view class="cu-bar bg-white search" :style='{"width":"100%","padding":"20rpx 24rpx","background":"#F8E2BE","display":"flex","height":"auto"}'>
				<view class="search-form round" :style='{"margin":"0 12rpx 0 0","flex":"1","position":"relative"}'>
					<text class="icon iconfont icon-fangdajing07" :style='{"color":"#7A531A","left":"0px","textAlign":"center","width":"80rpx","fontSize":"40rpx","lineHeight":"80rpx","position":"absolute","right":"0px"}'></text>
					<input :style='{"border":"2rpx solid #FAC696","padding":"12rpx 20rpx 12rpx 80rpx","boxShadow":"none","color":"#7A531A","borderRadius":"10rpx","background":"#fff","width":"100%","lineHeight":"56rpx","fontSize":"28rpx","height":"80rpx"}' v-model="searchForm.title" type="text" placeholder="标题" ></input>
				</view>
				<button :style='{"border":"0","padding":"0px","boxShadow":"none","margin":"0","color":"#fff","borderRadius":"10rpx","background":"#F49843","width":"136rpx","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}' @tap="search" class="cu-btn shadow-blur round">搜索</button>
			</view>
			<button :style='{"border":"0","boxShadow":"0 2rpx 12rpx rgba(0,0,0,.3)","color":"rgb(255, 255, 255)","textAlign":"center","bottom":"160rpx","right":"20rpx","outline":"none","borderRadius":"100%","background":"#F49843","width":"80rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"fixed","height":"80rpx","zIndex":"999"}' style="z-index: 11" @click="onUpdateTap()">新增</button>
			<view class="list" :style='{"width":"100%","padding":"20rpx 20rpx 80rpx","flex":"1"}'>
				<view :style='{"color":"#000","width":"100%","margin":"0 0 16rpx"}'>
					交流论坛
				</view>
				<view style="position: relative" @tap="onDetailTap(item.id)" v-for="(item,index) in list " v-bind:key="index" :style='{"boxShadow":"0 6rpx 10rpx #cccccc","padding":"30rpx","margin":"0 0 30rpx 0","borderRadius":"12rpx","background":"#fff","display":"flex","justifyContent":"space-between"}'>
					<view :style='{"margin":"0 0 0 -14rpx","position":"absolute","right":"20rpx","top":"20rpx","background":"#fff"}' v-if="item.isTop">
						<span class="icon iconfont icon-jiantou24" :style='{"color":"#000","fontSize":"28rpx"}'></span>
					</view>
					<image v-if="item.cover" :src="baseUrl + item.cover.split(',')[0]" mode="widthFix" :style='{"width":"176rpx","margin":"10rpx 0 0","borderRadius":"10rpx","height":"176rpx"}'></image>
					<view :style='{"width":"calc(100% - 216rpx)"}'>
						<view :style='{"color":"#000","margin":"0","fontWeight":"bold"}'>{{item.title}}</view>
						<view :style='{"color":"#bbb","lineHeight":"2","fontSize":"26rpx"}'>
							{{item.typename}}
						</view>
						<view :style='{"color":"#bbb","lineHeight":"2","fontSize":"26rpx"}'>
							发布人：{{item.userid!=userid&&item.isAnon==1?'匿名':item.username}}
						</view>
						<view :style='{"color":"#bbb","lineHeight":"2","fontSize":"26rpx"}'>
							发布时间：{{item.createTime.split(' ')[0]}}						</view>
					</view>
				</view>
			</view>
			<!--list end-->
		</view>
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
				searchForm:{
					title: ''
				},
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
		async onShow() {
			let res = {};
			let userid = uni.getStorageSync('appUserid')
			this.hasNext = true
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			}
		},
		methods: {
			onUpdateTap() {
				this.$utils.jump(`../forum-add-or-update/forum-add-or-update`)
			},
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
				let params = {
					page: this.mescroll.num,
					limit: this.mescroll.size,
					delFlag: 0,
				}
				let res = await this.$api.allPublicForm(params);
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
			//轮播图跳转
			onSwiperTap(e) {

			},
			// 搜索
			async search(){
				this.mescroll.num = 1
				// 如果是第一页数据置空
				if (this.mescroll.num == 1) this.list = [];
				let params = {
					page: this.mescroll.num,
					limit: this.mescroll.size,
					delFlag: 0,
				}
				if(this.searchForm.title){
					params.title = '%' + this.searchForm.title + '%'
				}
				let res = await this.$api.allPublicForm(params);
				this.list = this.list.concat(res.data.list);
				if (res.data.list.length == 0) this.hasNext = false;
				this.mescroll.endSuccess(this.mescroll.size, this.hasNext);
			}
		}
	}
</script>

<style>
	view {
		font-family: '\5FAE\8F6F\96C5\9ED1';
		font-size: 30upx;
	}

	.category-two .tab {
		cursor: pointer;
		padding: 0 0 0 20rpx;
		color: #7D5728;
		background: none;
		display: inline-block;
		width: 100%;
		font-size: 28rpx;
		line-height: 80rpx;
		text-align: left;
	}
	
	.category-two .tab.active {
		cursor: pointer;
		padding: 0 0 0 20rpx;
		color: #7D5728;
		background: #F6F6F6;
		display: inline-block;
		width: 100%;
		font-size: 28rpx;
		border-color: #F49843;
		border-width: 0 0 0 4rpx;
		line-height: 80rpx;
		border-style: solid;
		text-align: left;
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
		padding: 50upx 20upx 20upx;
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

	/* #endif */
</style>
