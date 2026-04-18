<template>
	<view class="content">
		<view :style='{"width":"100%","flexWrap":"wrap","background":"#fff","display":"flex","height":"auto"}'>
			<view class="list-swiper-4" :style='{"padding":"0","margin":"40rpx auto 0","overflow":"hidden","borderRadius":"20rpx","width":"calc(100% - 80rpx)","position":"relative","height":"348rpx","order":"2"}' @touchstart="touchStart" @touchmove="touchMove" @touchend="touchEnd">
				<view :style='{"overflow":"hidden","top":"0%","borderRadius":"20rpx","left":"0%","background":"none","width":"100%","position":"absolute","height":"300rpx"}' class="item animate__animated" :class="prevNumList4 == index  ? 'animate__backOutRight' : (numList4 == index  ? 'animate__backInLeft' : '')" v-for="(swiper,index) in swiperList" :key="index" v-if="numList4 == index || prevNumList4 == index">
					<image :style='{"width":"100%","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"300rpx"}' mode="aspectFill" :src="baseUrl+swiper.img" @tap="onSwiperTap(swiper)"></image>
					<view :style='{"padding":"8rpx 20rpx","margin":"-20rpx 0 0 0","transform":"translate3d(-50%, -50%, 0)","top":"50%","color":"#000","left":"50%","background":"rgba(255, 255, 255, 0.3)","display":"none","lineHeight":"1.5","fontSize":"40rpx","position":"absolute"}'>{{ swiper.title }}</view>
				</view>
				<view class="animate__navigation" :style='{"alignItems":"center","borderRadius":"0 0 20rpx 20rpx","left":"0%","bottom":"48rpx","background":"rgba(255,255,255,.3)","display":"flex","width":"100%","position":"absolute","justifyContent":"center","height":"40rpx"}'>
					<block v-for="(swiper,index) in swiperList" :key="index">
						<text class="navigation-item" v-if="numList4 == index" :style='{"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#7A531A","height":"16rpx"}'></text>
						<text class="navigation-item" v-if="numList4 != index" :style='{"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","opacity":"0.3","background":"#7A531A","height":"16rpx"}'></text>
					</block>
				</view>
			</view>
			<!-- menu -->
			<view v-if="true" class="menu_view">
				<block v-for="(item,index1) in menuList" v-bind:key="item.roleName">
					<block v-if="index1==0" v-bind:key="index" v-for=" (menu,index) in item.frontMenu">
						<block v-bind:key="sort" v-for=" (child,sort) in menu.child">
							<block v-bind:key="sort2" v-for=" (button,sort2) in child.buttons">
								<view class="menu-item" v-if="button=='查看' && child.tableName!='yifahuodingdan' && child.tableName!='yituikuandingdan' &&child.tableName!='yiquxiaodingdan' && child.tableName!='weizhifudingdan' && child.tableName!='yizhifudingdan' && child.tableName!='yiwanchengdingdan' " @tap="onPageTap2(child.tableName)">
									<view class="iconarr" :class="child.appFrontIcon" :style="{'color':menuColor[index]}"></view>
									
									<view class="text">{{child.menu.split("列表")[0]}}</view>
								</view>
							</block>
						</block>
					</block>
				</block>
			</view>
			<!-- 商品推荐 -->
			<view class="listBox recommend" :style='{"width":"100%","padding":"20rpx","margin":"0","background":"#fff","order":"3"}'>
				<view class="title" :style='{"padding":"0 0 10rpx 0","margin":"0 0 40rpx 0","borderColor":"#F49843","background":"none","borderWidth":"0 0 4rpx 0","display":"flex","width":"100%","borderStyle":"solid","justifyContent":"space-between"}'>
					<view :style='{"fontSize":"32rpx","lineHeight":"1.8","color":"#7A531A","fontWeight":"bold"}'>美食食谱推荐</view>
					<view :style='{"alignItems":"center","justifyContent":"center","display":"flex"}' @tap="onPageTap('foodRecipe')">
						<text :style='{"color":"#666","fontSize":"28rpx"}'>更多</text>
						<text class="icon iconfont icon-jinru" :style='{"color":"#999","fontSize":"28rpx"}'></text>
					</view>
				</view>
				<!-- 样式3 -->
				<view class="list-box style3" :style='{"width":"100%","padding":"24rpx","margin":"0","borderRadius":"20rpx","background":"#fff","height":"auto"}'>
					<view @tap="onDetailTap('foodRecipe',product.id)" v-for="(product,index) in foodRecipelist" :key="index" class="list-item" :style='{"border":"2rpx solid #dddddd","boxShadow":"none","padding":"0 0 10rpx 0","margin":"0 0 20rpx 0","alignItems":"center","flexWrap":"wrap","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","height":"auto"}'>
						<image :style='{"width":"200rpx","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"200rpx"}' class="list-item-image" mode="aspectFill" v-if="product.foodCover.substring(0,4)=='http'" :src="product.foodCover"></image>
						<image :style='{"width":"200rpx","objectFit":"cover","borderRadius":"20rpx","display":"block","height":"200rpx"}' class="list-item-image" mode="aspectFill" v-else :src="product.foodCover?baseUrl+product.foodCover.split(',')[0]:''"></image>
						<view class="list-item-body" :style='{"width":"calc(100% - 200rpx)","padding":"0","margin":"0","flexWrap":"wrap","display":"flex","height":"auto"}'>
							<view :style='{"padding":"0 20rpx","margin":"0","color":"#7A531A","width":"100%","lineHeight":"2","fontSize":"28rpx","fontWeight":"bold","order":"1"}' class="list-item-title">{{product.foodName}}</view>
							<view :style='{"padding":"0 20rpx","order":"7"}'>
								<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"50rpx","fontSize":"24rpx","color":"#B88F86"}'></text>
								<text :style='{"color":"#B88F86","lineHeight":"50rpx","fontSize":"24rpx"}'>{{product.createTime.split(' ')[0].replace(/\-/g,'-')}}</text>
							</view>
							<view :style='{"border":"2rpx solid #7A531A","padding":"0 20rpx","margin":"0 20rpx","borderRadius":"4rpx","alignItems":"center","background":"#FEF4E4","display":"flex","order":"3"}'>
								<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#7A531A"}'></text>
								<text :style='{"color":"#7A531A","lineHeight":"1.5","fontSize":"24rpx"}'>{{product.storeUpNum}}</text>
							</view>
							<view :style='{"border":"2rpx solid #F75433","padding":"0 20rpx","margin":"0 20rpx","borderRadius":"4rpx","alignItems":"center","background":"#FEF4E4","display":"flex","order":"4"}'>
								<text class="icon iconfont icon-chakan9" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#F75433"}'></text>
								<text :style='{"color":"#F75433","lineHeight":"1.5","fontSize":"24rpx"}'>{{product.clickNum}}</text>
							</view>
						</view>
					</view>
				</view>
			</view>
			<!-- 商品推荐 -->
			
			<!-- 商品列表 -->
			<!-- 商品列表 -->
			<!-- 新闻资讯 -->
			<view class="listBox news" :style='{"width":"100%","padding":"20rpx","margin":"0","background":"#fff","order":"4"}'>
				<view class="title" :style='{"padding":"0 0 10rpx 0","margin":"0 0 40rpx 0","borderColor":"#F49843","background":"none","borderWidth":"0 0 4rpx 0","display":"flex","width":"100%","borderStyle":"solid","justifyContent":"space-between"}'>
					<view :style='{"fontSize":"32rpx","lineHeight":"1.8","color":"#7A531A","fontWeight":"bold"}'>系统公告</view>
					<view :style='{"alignItems":"center","justifyContent":"center","display":"flex"}' @tap="onPageTap('news')">
					  <text :style='{"color":"#666","fontSize":"28rpx","order":"2"}'>更多</text>
					  <text class="icon iconfont icon-jinru" :style='{"padding":"0 10rpx 0 0","fontSize":"28rpx","color":"#999","order":"2"}'></text>
					</view>
				</view>
				<!-- 样式9 -->
				<view class="news-box6" :style='{"width":"100%","padding":"24rpx 0","margin":"0","height":"auto"}'>
					<view @tap="onNewsDetailTap(item.id)" v-for="(item,index) in news" :key="index" class="list-item" :style='{"padding":"20rpx","margin":"0 0 30rpx","borderRadius":"20rpx","background":"linear-gradient(135deg, #ffffff 0%,#ffefd5 100%)","width":"100%","position":"relative","height":"auto"}'>
						<view :style='{"padding":"0","margin":"-36rpx 0 0 0","borderColor":"#C88353","color":"#C88353","textAlign":"center","display":"none","right":"40rpx","borderRadius":"100%","top":"50%","borderWidth":"2rpx","width":"72rpx","lineHeight":"68rpx","fontSize":"28rpx","position":"absolute","borderStyle":"solid","height":"72rpx"}' class="num">
							<view style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;" :style="{transform: 'rotate('+(-index*60)+'deg)'}" class="dian">
								<view :style='{"transform":"translateX(-50%)","borderRadius":"100%","top":"-8rpx","left":"50%","background":"#F75433","display":"none","width":"16rpx","position":"absolute","height":"16rpx"}'></view>
							</view>{{ index + 1 < 10 ? '0'+(index+1) : index+1 }}
						</view>
						<view class="item-list-body" :style='{"width":"100%","margin":"0","flexWrap":"wrap","display":"flex","height":"auto"}'>
							<view :style='{"padding":"0 20rpx","whiteSpace":"nowrap","overflow":"hidden","color":"#7A531A","width":"100%","lineHeight":"1.5","fontSize":"30rpx","textOverflow":"ellipsis","fontWeight":"bold","order":"1"}' class="title ">{{item.title}}</view>
							<view :style='{"padding":"8rpx 20rpx","margin":"0","whiteSpace":"nowrap","overflow":"hidden","color":"#F49843","width":"100%","lineHeight":"1.5","fontSize":"28rpx","textOverflow":"ellipsis","order":"2"}' class="text">{{item.introduction}}</view>
							<view :style='{"padding":"8rpx 10rpx 0","order":"7"}'>
								<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#8D8D8D"}'></text>
								<text :style='{"color":"#8D8D8D","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.createTime.split(' ')[0].replace(/\-/g,'-')}}</text>
							</view>
							<view :style='{"width":"40%","padding":"0 10rpx","order":"6"}'>
								<text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#D5863A"}'></text>
								<text :style='{"color":"#D5863A","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.name}}</text>
							</view>
						</view>
					</view>
				</view>
			</view>
			<!-- 新闻资讯 -->
			<view v-if="scrollTop>200" @tap="scrollTopClick" :style='{"boxShadow":"0 8rpx 16rpx rgba(0,0,0,.3)","borderRadius":"50%","textAlign":"center","bottom":"20%","background":"#ff000030","width":"60rpx","lineHeight":"60rpx","position":"fixed","right":"20rpx","height":"60rpx","zIndex":"999"}'>
				<span class="icon iconfont icon-jiantou07" :style='{"color":"#fff"}'></span>
			</view>
		</view>
	</view>
</template>

<script>
    import menu from '@/utils/menu'
	import '@/assets/css/global-restaurant.css'
	import uniIcons from "@/components/uni-ui/lib/uni-icons/uni-icons.vue"
	export default {
		components: {
			uniIcons
		},
		data() {
			return {
				startX: 0,
				prevNumList4: '',
				numList4: 0,
				timerList4: null,
				flagList4: false,
				navigationActive: {"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#7A531A","height":"16rpx"},
				navigationDefault: {"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","opacity":"0.3","background":"#7A531A","height":"16rpx"},
				options2: {
					effect: 'flip',
					loop : true
				},
				options3: {
					effect: 'cube',
					loop : true,
					cubeEffect: {
						shadow: true,
						slideShadows: true,
						shadowOffset: 20,
						shadowScale: 0.94,
					}
				},
				rows: 2,
				column: 4,
				iconArr: [
					'cuIcon-same',
					'cuIcon-deliver',
					'cuIcon-evaluate',
					'cuIcon-shop',
					'cuIcon-ticket',
					'cuIcon-cascades',
					'cuIcon-discover',
					'cuIcon-question',
					'cuIcon-pic',
					'cuIcon-filter',
					'cuIcon-footprint',
					'cuIcon-pulldown',
					'cuIcon-pullup',
					'cuIcon-moreandroid',
					'cuIcon-refund',
					'cuIcon-qrcode',
					'cuIcon-remind',
					'cuIcon-profile',
					'cuIcon-home',
					'cuIcon-message',
					'cuIcon-link',
					'cuIcon-lock',
					'cuIcon-unlock',
					'cuIcon-vip',
					'cuIcon-weibo',
					'cuIcon-activity',
					'cuIcon-friendadd',
					'cuIcon-friendfamous',
					'cuIcon-friend',
					'cuIcon-goods',
					'cuIcon-selection'
				],
				role : '',
				menuList: [],
				swiperMenuList:[],
				user: {},
				tableName:'',
				menuColor: '#6580a6,#548aff,#ffc431,#ff6161,#4f91ff,#ffbd65,#0fd38c,#52c9e1,#ff6161,#4f91ff,#ffbd65,#0fd38c,#52c9e1'.split(','),

				//轮播
				swiperList: [],
				foodRecipelist: [],
				news: [],
				scrollTop: 0,
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop
		},
		watch: {
		},
		mounted() {
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
			username() {
				return uni.getStorageSync("nickname")
			},
		},
		async onLoad(){
			this.menuColor = this.menuColor.sort(()=> {
				return (0.5-Math.random());
			});
		},
		async onShow() {
			if (this.timerList4&&this.timerList4!=null) clearInterval(this.timerList4)
			this.swiperMenuList = []
			this.role = uni.getStorageSync("appRole");
			let table = uni.getStorageSync("nowTable");
			let res = {}
			if(table) {
				res = await this.$api.session(table);
				this.user = res.data;
				this.tableName = table;
			}
			let menus = menu.list();
			this.menuList = menus;
			this.menuList.forEach((item,key) => {
				if(key==0) {
					item.frontMenu.forEach((item2,key2) => {
						if(item2.child[0].buttons.indexOf("查看")>-1) {
							this.swiperMenuList.push(item2);
						}
					})
				}
			})
			// let res;
			// 轮播图
			let swiperList = []
			res = await this.$api.list('config', {
				type: 1,
				limit: 100
			});
			for (let item of res.data.list) {
				if (item.name.indexOf('picture') >= 0 && item.value && item.value!="" && item.value!=null ) {
					swiperList.push({
						img: item.value,
						title: item.name,
						url: item.url
					});
				}
			}
			if (swiperList) {
				this.swiperList = swiperList;
			}
			
			this.prevNumList4 = this.swiperList.length - 1
			this.timerList4 = setInterval(this.autoPlayList4, 3000)

			// 推荐信息
			this.getRecommendList()
			this.getHomeList()
			this.getNewsList()
		},
		methods: {
			scrollTopClick(){
				uni.pageScrollTo({
					scrollTop: 0
				})
			},
			uGetRect(selector, all) {
				return new Promise(resolve => {
					uni.createSelectorQuery()
					.in(this)
					[all ? 'selectAll' : 'select'](selector)
					.boundingClientRect(rect => {
						if (all && Array.isArray(rect) && rect.length) {
							resolve(rect);
						}
						if (!all && rect) {
							resolve(rect);
						}
					})
					.exec();
				});
			},
			cloneData(data) {
				return JSON.parse(JSON.stringify(data));
			},
			async getNewsList(){
				let res;
				let params = {
					page: 1,
					limit: 6,
					sort: 'id',
					order: 'desc',
				}
				// 系统公告
				res = await this.$api.list('news', params)
				this.news = res.data.list
			},
			homeTabClick2(index,name){
				this['home' + name + 'Index2'] = index
				this.getHomeList()
			},
			async getHomeList(){
				let res;
				let params;
			},
			recommendTabClick2(index,name){
				this[name + 'Index2'] = index
				this.getRecommendList()
			},
			async getRecommendList(){
				let res;
				let params;
				// 推荐信息
				params = {
					page: 1,
					limit: 6,
				}
				if(uni.getStorageSync("appUserid")) {
					res = await this.$api.recommend2('foodRecipe', params);
				} else {
					res = await this.$api.recommend('foodRecipe', params);
				}
				this.foodRecipelist = res.data.list
				

			},
			autoPlayList4() {
				this.prevNumList4 = this.numList4
			
				this.numList4++
				if (this.numList4 == this.swiperList.length) this.numList4 = 0
			},
			touchStart(event) {
				this.startX = event.touches[0].clientX
				
				clearInterval(this.timerList4)
				this.flagList4 = true
			},
			touchMove(event) {
				const currentX = event.touches[0].clientX;
				const deltaX = currentX - this.startX;
				
				if (deltaX > 50) {
					// 向右滑动逻辑
					if (this.flagList4) {
						this.flagList4 = false
						
						this.prevNumList4 = this.numList4
						this.numList4++
						if (this.numList4 == this.swiperList.length) this.numList4 = 0
					}
					
				} else if (deltaX < -50) {
					// 向左滑动逻辑
					if (this.flagList4) {
						this.flagList4 = false
						
						this.prevNumList4 = this.numList4
						this.numList4--
						if (this.numList4 < 0) this.numList4 = this.swiperList.length - 1
					}
				}
			},
			touchEnd() {
				this.startX = 0
				this.timerList4 = setInterval(this.autoPlayList4, 3000)
				this.flagList4 = false
			},
			//轮播图跳转
			onSwiperTap(e) {
				if(e.url) {
					if (e.url.indexOf('https') != -1) {
						// #ifdef MP-WEIXIN
						uni.navigateTo({
						    url: '../../common/linkOthers/linkOthers?url=' + encodeURIComponent(e.url),
						});
						return false
						// #endif
						window.open(e.url)
					} else {
						this.$utils.jump(e.url)
					}
				}
			},
			// 新闻详情
			onNewsDetailTap(id) {
				this.$utils.jump(`../news-detail/news-detail?id=${id}`)
			},
			// 推荐列表点击详情
			onDetailTap(tableName, id) {
				this.$utils.jump(`../${tableName}/detail?id=${id}`)
			},
			onPageTap(tableName){
				uni.navigateTo({
					url: `../${tableName}/list`,
					fail: function(){
						uni.switchTab({
							url: `../${tableName}/list`
						});
					}
				});
				// this.$utils.jump(`../${tableName}/list`)
			},
			onPageTap2(index) {
				let url = '../' + index + '/list'
				if(index=='forum'){
					url = '../forum-index/forum-index'
				}
				if(index=='kefuguanli') {
					url = '../chat/chat'
				}
				uni.setStorageSync("useridTag",0);
				uni.navigateTo({
					url: url,
					fail: function() {
						uni.switchTab({
							url: url
						});
					}
				});
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
	.list-swiper-4 .animate__animated {
		--animate-delay: 300ms;
	}

	.menu_view {
		padding: 0;
		margin: 0;
		background: #fff;
		display: flex;
		width: 100%;
		justify-content: flex-start;
		flex-wrap: wrap;
		height: auto;
		order: 3;
		.menu-item {
			padding: 12rpx 0;
			margin: 10rpx 1%;
			width: 31%;
			height: auto;
			.iconarr {
				border: 2rpx solid #eeeeee;
				border-radius: 100%;
				padding: 0;
				margin: 0px auto;
				color: #000;
				background: #f8f8f8;
				display: block;
				width: 100rpx;
				font-size: 64rpx;
				line-height: 100rpx;
				text-align: center;
				height: 100rpx;
			}
			.text {
				padding: 0;
				margin: 12rpx auto 0;
				color: #000;
				width: 100%;
				font-size: 28rpx;
				line-height: 28rpx;
				text-align: center;
			}
		}
	}
</style>
