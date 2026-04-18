  <template>
	<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback" @scroll="scrollChange">
		<view class="content">
			<view :style='{"padding":"0 0 220rpx 0","flexWrap":"wrap","background":"#fff","display":"flex","width":"100%","position":"relative","height":"auto"}'>
				<view class="cu-bar bg-white search" :style='{"width":"100%","padding":"20rpx 24rpx","background":"#F8E2BE","display":"flex","height":"auto"}'>
					<view  :style='{"margin":"0 12rpx","flex":"1","position":"relative"}' class="search-form round">
						<text class="icon iconfont icon-fangdajing07" :style='{"color":"#7A531A","left":"0px","textAlign":"center","width":"80rpx","fontSize":"40rpx","lineHeight":"80rpx","position":"absolute","right":"0px"}'></text>
						<input placeholder-style="color: #999" :style='{"border":"2rpx solid #FAC696","padding":"12rpx 20rpx 12rpx 80rpx","boxShadow":"none","color":"#7A531A","borderRadius":"10rpx","background":"#fff","width":"100%","lineHeight":"56rpx","fontSize":"28rpx","height":"80rpx"}' v-model="searchForm.foodName" type="text" placeholder="食品名称" ></input>
					</view>
					<button :style='{"border":"0","padding":"0px","boxShadow":"none","margin":"0","color":"#fff","borderRadius":"10rpx","background":"#F49843","width":"136rpx","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}' @tap="search" class="cu-btn shadow-blur round">搜索</button>
				</view>
			

				<view :style='{"width":"100%","margin":"120rpx 14rpx 40rpx 14rpx","flexWrap":"wrap","justifyContent":"center","display":"flex"}'>
					<view @click="sortClick('click_num')" :style='{"border":"0","padding":"0 12rpx","margin":"0 8rpx 0 0","outline":"0","borderRadius":"8rpx","display":"flex"}'>
						<text :style='{"color":"#999898","lineHeight":"40rpx","fontSize":"28rpx"}'>点击量</text>
						<text v-if="listSort!='click_num'" class="icon iconfont icon-xiaoliang13" :style='{"margin":"0 0 0 4rpx","lineHeight":"40rpx","fontSize":"28rpx","color":"#999898"}'></text>
						<text v-else-if="listSort=='click_num'&&listOrder=='asc'" class="icon iconfont icon-xiaoliang13" :style='{"margin":"0 0 0 4rpx","lineHeight":"40rpx","fontSize":"28rpx","color":"#999898"}'></text>
						<text v-else-if="listSort=='click_num'&&listOrder=='desc'" class="icon iconfont icon-xiaoliang13" :style='{"margin":"0 0 0 4rpx","lineHeight":"40rpx","fontSize":"28rpx","color":"#999898"}'></text>
					</view>
					<view @click="sortClick('create_time')" :style='{"border":"0","padding":"0 12rpx","margin":"0 8rpx 0 0","outline":"0","borderRadius":"8rpx","display":"flex"}'>
						<text :style='{"color":"#999898","lineHeight":"40rpx","fontSize":"28rpx"}'>按日期</text>
						<text v-if="listSort!='create_time'" class="icon iconfont icon-shijian18" :style='{"margin":"0 0 0 4rpx","lineHeight":"40rpx","fontSize":"28rpx","color":"#999898"}'></text>
						<text v-else-if="listSort=='create_time'&&listOrder=='asc'" class="icon iconfont icon-shijian18" :style='{"margin":"0 0 0 4rpx","lineHeight":"40rpx","fontSize":"28rpx","color":"#999898"}'></text>
						<text v-else-if="listSort=='create_time'&&listOrder=='desc'" class="icon iconfont icon-shijian18" :style='{"margin":"0 0 0 4rpx","lineHeight":"40rpx","fontSize":"28rpx","color":"#999898"}'></text>
					</view>
				</view>
				<view :style='{"alignContent":"flex-start","alignItems":"flex-start","flexWrap":"wrap","background":"none","display":"flex","width":"100%","height":"auto"}'>
					<scroll-view scroll-y="true" class="category-two" :style='{"width":"200rpx","background":"#fff","height":"auto"}'>
						<view :class='categoryName === item.foodCategory ? "active" : ""' class="tab" v-for="(item,index) in categoryList" :key="index" @tap="categoryClick(item.foodCategory)">{{item.foodCategory}}</view>
					</scroll-view>
					<!-- 样式2 -->
					<view class="list" :style='{"width":"calc(100% - 200rpx)","padding":"0 20rpx","margin":"0","flex":"1","height":"auto"}'>
						<view @tap="onDetailTap(product)" class="listm flex flex-between" :style='{"boxShadow":"0 0px 0px rgba(0,0,0,.3)","padding":"8rpx","margin":"0 0 20rpx","borderRadius":"20rpx","flexWrap":"wrap","background":"#fff","display":"flex","width":"100%","position":"relative","height":"auto"}' v-for="(product,index) in list" :key="index">
							<image :style='{"width":"200rpx","margin":"0 10rpx 0 0","objectFit":"cover","borderRadius":"10rpx","display":"block","height":"200rpx"}' mode="aspectFill" class="listmpic" v-if="preHttp(product.foodCover)" :src="product.foodCover.split(',')[0]"></image>
							<image :style='{"width":"200rpx","margin":"0 10rpx 0 0","objectFit":"cover","borderRadius":"10rpx","display":"block","height":"200rpx"}' mode="aspectFill" class="listmpic" v-else :src="product.foodCover?baseUrl+product.foodCover.split(',')[0]:''"></image>

							<view class="listmr" :style='{"width":"calc(100% - 210rpx)","padding":"0","margin":"0","flexWrap":"wrap","display":"flex","height":"auto"}'>
								<view class="col3 f30 elip mb15" :style='{"padding":"0 0px","margin":"0","color":"#7D5728","width":"100%","lineHeight":"48rpx","fontSize":"28rpx","fontWeight":"bold","order":"1"}'>{{product.foodName}}</view>
								<view :style='{"width":"auto","padding":"0 0px","margin":"0 10rpx 0 0","textAlign":"left","order":"6"}'>
									<text class="icon iconfont icon-shijian21" :style='{"margin":"0 0px 0 0","lineHeight":"48rpx","fontSize":"24rpx","color":"#B88F86"}'></text>
									<text :style='{"color":"#B88F86","lineHeight":"48rpx","fontSize":"24rpx"}'>{{product.createTime.split(' ')[0].replace(/\-/g,'-')}}</text>
								</view>
								<view :style='{"width":"auto","padding":"0 0px","margin":"0 10rpx 0 0","textAlign":"center","display":"inline-block","order":"3"}'>
									<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 0px 0 0","lineHeight":"48rpx","fontSize":"24rpx","color":"#7A531A"}'></text>
									<text :style='{"color":"#7A531A","lineHeight":"48rpx","fontSize":"24rpx"}'>{{product.storeUpNum}}</text>
								</view>
								<view :style='{"width":"auto","padding":"0 0px","margin":"0 10rpx 0 0","textAlign":"center","display":"inline-block","order":"4"}'>
									<text class="icon iconfont icon-chakan9" :style='{"margin":"0 0px 0 0","lineHeight":"48rpx","fontSize":"24rpx","color":"#F75433"}'></text>
									<text :style='{"color":"#F75433","lineHeight":"48rpx","fontSize":"24rpx"}'>{{product.clickNum}}</text>
								</view>
							</view>
							<!-- #ifdef MP-WEIXIN -->
							<view :style='{"width":"calc(100% - 0px)","padding":"0","margin":"20rpx 0 0","justifyContent":"center","display":"flex","height":"auto"}'>
								<view :style='{"border":"2rpx solid #F49843","margin":"0 5% 10rpx 0","borderRadius":"8rpx","background":"#FEF4E4","display":"flex","width":"40%","justifyContent":"center"}' v-if="(userid && isAuth('foodRecipe','修改')) || (!userid && isAuthFront('foodRecipe','修改'))" @tap.stop.proevent="onUpdate" :data-row="product">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"26rpx","lineHeight":"50rpx","color":"#F49843","display":"inline-block"}' class="cuIcon-edit"></text>
									<text :style='{"fontSize":"26rpx","lineHeight":"50rpx","color":"#F49843","display":"inline-block"}'>修改</text>
								</view>
								<view :style='{"border":"2rpx solid #999898","margin":"0 0 10rpx 5%","borderRadius":"8rpx","background":"#EDEDED","display":"flex","width":"40%","justifyContent":"center"}' v-if="(userid && isAuth('foodRecipe','删除')) || (!userid && isAuthFront('foodRecipe','删除'))" @tap.stop.proevent="onDelete" :data-row="product">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"26rpx","lineHeight":"50rpx","color":"#999898","display":"inline-block"}' class="cuIcon-delete"></text>
									<text :style='{"fontSize":"26rpx","lineHeight":"50rpx","color":"#999898","display":"inline-block"}'>删除</text>
								</view>
							</view>
							<!-- #endif -->
							<!-- #ifndef MP-WEIXIN -->
							<view :style='{"width":"calc(100% - 0px)","padding":"0","margin":"20rpx 0 0","justifyContent":"center","display":"flex","height":"auto"}'>
								<view :style='{"border":"2rpx solid #F49843","margin":"0 5% 10rpx 0","borderRadius":"8rpx","background":"#FEF4E4","display":"flex","width":"40%","justifyContent":"center"}' v-if="(userid && isAuth('foodRecipe','修改')) || (!userid && isAuthFront('foodRecipe','修改'))" @tap.stop.proevent="onUpdateTap(product)">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"26rpx","lineHeight":"50rpx","color":"#F49843","display":"inline-block"}' class="cuIcon-edit"></text>
									<text :style='{"fontSize":"26rpx","lineHeight":"50rpx","color":"#F49843","display":"inline-block"}'>修改</text>
								</view>
								<view :style='{"border":"2rpx solid #999898","margin":"0 0 10rpx 5%","borderRadius":"8rpx","background":"#EDEDED","display":"flex","width":"40%","justifyContent":"center"}' v-if="(userid && isAuth('foodRecipe','删除')) || (!userid && isAuthFront('foodRecipe','删除'))" @tap.stop.proevent="onDeleteTap(product.id)">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"26rpx","lineHeight":"50rpx","color":"#999898","display":"inline-block"}' class="cuIcon-delete"></text>
									<text :style='{"fontSize":"26rpx","lineHeight":"50rpx","color":"#999898","display":"inline-block"}'>删除</text>
								</view>
							</view>
							<!-- #endif -->
						</view>
					</view>
			
			
			


			
			
			
			
				</view>
				<button :style='{"border":"0","margin":"20rpx 0 0 10rpx","whiteSpace":"nowrap","color":"#fff","alignItems":"center","display":"flex","right":"10rpx","justifyContent":"center","outline":"none","borderRadius":"80rpx","top":"120rpx","background":"#F49843","width":"80rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"absolute","height":"80rpx","zIndex":"99"}' v-if="userid && isAuth('foodRecipe','新增')" class="add-btn" @click="onAddTap()">新增</button>
				<button :style='{"border":"0","margin":"20rpx 0 0 10rpx","whiteSpace":"nowrap","color":"#fff","alignItems":"center","display":"flex","right":"10rpx","justifyContent":"center","outline":"none","borderRadius":"80rpx","top":"120rpx","background":"#F49843","width":"80rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"absolute","height":"80rpx","zIndex":"99"}' v-if="!userid && isAuthFront('foodRecipe','新增')" class="add-btn" @click="onAddTap()">新增</button>
				<button :style='{"border":"0","margin":"20rpx 0 0 10rpx","whiteSpace":"nowrap","color":"#ffffff","alignItems":"center","display":"flex","justifyContent":"center","outline":"none","borderRadius":"80rpx","top":"120rpx","left":"10rpx","background":"#ffaa33","width":"80rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"absolute","height":"80rpx","zIndex":"99"}' v-if="staticType" class="statics-btn" @click="staticClick()">
					统
				</button>
			</view>
		</view>
		<!-- <view v-if="scrollTop>200" @tap="scrollTopClick" :style='{"boxShadow":"0 8rpx 16rpx rgba(0,0,0,.3)","borderRadius":"50%","textAlign":"center","bottom":"20%","background":"#ff000030","width":"60rpx","lineHeight":"60rpx","position":"fixed","right":"20rpx","height":"60rpx","zIndex":"999"}'>
			<span class="icon iconfont icon-jiantou07" :style='{"color":"#fff"}'></span>
		</view> -->
	</mescroll-uni>
</template>

<script>
	export default {
		data() {
			return {
				btnColor: ['#409eff','#67c23a','#909399','#e6a23c','#f56c6c','#356c6c','#351c6c','#f093a9','#a7c23a','#104eff','#10441f','#a21233','#503319'],
				clickNumColor: ['#409eff','#67c23a','#909399','#e6a23c','#f56c6c','#356c6c','#351c6c','#f093a9','#a7c23a','#104eff','#10441f','#a21233','#503319'],
				list: [],
				lists: [],
                userid: '',
				mescroll: null, //mescroll实例对象
				downOption: {
					auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
					noMoreSize: 5, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
					textNoMore: '~ 没有更多了 ~',
					onScroll: true,
					toTop: true
				},
				hasNext: true,
				searchForm:{
					foodName: '',
				},
				categoryList:[],
				categoryName :'全部',
				CustomBar: '0',
				listSort: 'id',
				listOrder: 'desc',
				screenBoxShow: false,
				scrollTop: 0,
				staticType: false,
				chartData1: {},
				opts1: {},
				chartData2: {},
				opts2: {},
				chartData3: {},
				opts3: {},
			};
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
			role() {
				return uni.getStorageSync("appRole")
			},
		},
		onUnload() {
			uni.removeStorageSync("useridTag");
		},
		async onShow() {
			if(uni.getStorageSync("useridTag")==1){
				this.userid=uni.getStorageSync("useridTag");
				if(
					this.isAuth('foodRecipe','食谱浏览量')
					||this.isAuth('foodRecipe','食谱收藏量')
					||this.isAuth('foodRecipe','食品分类')
				) {
					this.staticType = true
				}else {
					this.staticType = false
				}
				let remindRes = null
			} else {
				this.userid = "";
				if(
					this.isAuthFront('foodRecipe','食谱浏览量')
					||this.isAuthFront('foodRecipe','食谱收藏量')
					||this.isAuthFront('foodRecipe','食品分类')
				) {
					this.staticType = true
				}else {
					this.staticType = false
				}
			}
			this.btnColor = this.btnColor.sort(()=> {
				return (0.5-Math.random());
			});
			this.clickNumColor = this.clickNumColor.sort(()=> {
				return (0.5-Math.random());
			});
			let res = {};
			if(this.userid) {
				res = await this.$api.page('foodCategory', {page:1,limit:100});
			} else {
				res = await this.$api.list('foodCategory', {page:1,limit:100});
			}
			res.data.list.splice(0,0,{id:0,foodCategory:'全部'})
			this.categoryList = res.data.list;
			this.hasNext = true
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
			this.$forceUpdate()
		},
		async onLoad(options) {
			this.hasNext = true
			// 重新加载数据
			// if (this.mescroll) this.mescroll.resetUpScroll()
		},
		components: {
		},
		methods: {
			queryChange(arr){
				for(let x in arr) {
					if(arr[x] == this.role) {
						return true
					}
				}
				return false
			},
			scrollChange(e){
				this.scrollTop = e.scrollTop
			},
			scrollTopClick(){
				uni.pageScrollTo({
					scrollTop: 0
				})
			},
			screenReset(){
				this.searchForm = {}
				this.search()
				this.$forceUpdate()
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
			sortClick(type){
				if(this.listSort==type){
					if(this.listOrder == 'desc'){
						this.listOrder = 'asc'
					}else{
						this.listOrder = 'desc'
					}
				}else{
					this.listSort = type
					this.listOrder = 'desc'
				}
				this.search()
			},
            priceChange(price) {
                return Number(price).toFixed(2);
            },
            preHttp(str) {
                return str && str.substr(0,4)=='http';
            },
			//类别搜索
			categoryClick(categoryName){
				this.categoryName = categoryName;
				this.mescroll.resetUpScroll();
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
					page: mescroll.num,
					limit: mescroll.size,
				}
				params['sort'] = this.listSort;
				params['order'] = this.listOrder;

				if(this.categoryName!='全部'){
					params.foodCategory = '%' + this.categoryName + '%'
				}
				if(this.searchForm.foodName){
					params['foodName'] = '%' + this.searchForm.foodName + '%'
				}
				let user = uni.getStorageSync("appUserid")?JSON.parse(uni.getStorageSync('userSession')):{}
                let res = {}
                if(this.userid) {
                    res = await this.$api.page(`foodRecipe`, params);
                } else {
                    res = await this.$api.list(`foodRecipe`, params);
                }

				// 如果是第一页数据置空
				if (mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				this.$forceUpdate()
				
				let length = Math.ceil(this.list.length/6)
				let arr = [];
				for (let i = 0; i<length; i++){
					arr[i] = this.list.slice(i*6, (i+1)*6)
				}
				this.lists = arr
				if (res.data.list.length == 0) this.hasNext = false;
				mescroll.endSuccess(mescroll.size, this.hasNext);
			},
			// 详情
			onDetailTap(item) {
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./detail?id=${item.id}&userid=`+this.userid)
			},
			onUpdate(e){
				this.onUpdateTap(e.currentTarget.dataset.row)
			},
			// 修改
			onUpdateTap(row){
				uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./add-or-update?id=${row.id}`)
			},
			// 添加
			onAddTap(){
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./add-or-update`)
			},
			onDelete(e){
				this.onDeleteTap(e.currentTarget.dataset.row.id)
			},
			onDeleteTap(id){
				var that = this;
				uni.showModal({
					title: '提示',
					content: '是否确认删除',
					success: async function(res) {
						if (res.confirm) {
							await that.$api.del('foodRecipe', JSON.stringify([id]));
							let obj = await that.$api.list('storeUp',{
								page: 1,
								limit: 100,
								tableName: 'foodRecipe',
								refId: id,
							})
							if(obj.data.list.length){
								let arr = []
								for(let x in obj.data.list){
									arr.push(obj.data.list[x].id)
								}
								await that.$api.del('storeUp',JSON.stringify(arr))
							}
							that.$utils.msg('删除成功');
							that.hasNext = true
							// 重置分页参数页数为1
							that.search()
						}
					}
				});
			},
			// 搜索
			async search(){
				this.mescroll.num = 1
				let searchForm = {
					page: this.mescroll.num,
					limit: this.mescroll.size,
				}
				searchForm['sort'] = this.listSort;
				searchForm['order'] = this.listOrder;

				if(this.searchForm.foodName){
					searchForm['foodName'] = '%' + this.searchForm.foodName + '%'
				}
				if(this.categoryName!='全部'){
					searchForm.foodCategory = '%' + this.categoryName + '%'
				}
				let res = {};
				if(this.userid) {
					res = await this.$api.page(`foodRecipe`, searchForm);
				} else {
					res = await this.$api.list(`foodRecipe`, searchForm);
				}
				// 如果是第一页数据置空
				if (this.mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				
				let length = Math.ceil(this.list.length/6)
				let arr = [];
				for (let i = 0; i<length; i++){
					arr[i] = this.list.slice(i*6, (i+1)*6)
				}
				this.lists = arr
				if (res.data.list.length == 0) this.hasNext = false;
				this.mescroll.endSuccess(this.mescroll.size, this.hasNext);
				this.screenBoxShow = false
			},
			staticClick() {
				this.$utils.jump(`./add-or-update?showType=2&userid=` + this.userid)
			},
		}
	};
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
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
</style>
