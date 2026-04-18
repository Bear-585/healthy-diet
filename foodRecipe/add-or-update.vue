  

<template>
<view class="content">
	<view :style='{"width":"100%","padding":"0","position":"relative","background":"#fff","height":"auto"}' v-if="showType==1">
		<form :style='{"width":"100%","padding":"24rpx","background":"#fff","display":"block","height":"auto"}' class="app-update-pv">






























			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">食品名称</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.foodName" v-model="ruleForm.foodName" placeholder="食品名称"  type="text"></input>
			</view>
			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="" @tap="foodCoverTap">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">食品封面</view>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-if="ruleForm.foodCover" :src="baseUrl+ruleForm.foodCover.split(',')[0]" mode="aspectFill"></image>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
			</view>
			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">食品分类</view>
				<picker :disabled="ro.foodCategory" :style='{"width":"100%","flex":"1","height":"auto"}' @change="foodCategoryChange" :value="foodCategoryIndex" :range="foodCategoryOptions">
					<view :style='{"width":"100%","padding":"0 20rpx","lineHeight":"80rpx","fontSize":"28rpx","color":"#F75433"}' class="uni-input">{{ruleForm.foodCategory?ruleForm.foodCategory:"请选择食品分类"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">热量</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.calories" v-model="ruleForm.calories" placeholder="热量"  type="text"></input>
			</view>
			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">蛋白质</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.protein" v-model="ruleForm.protein" placeholder="蛋白质"  type="text"></input>
			</view>
			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">脂肪量</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.fat" v-model="ruleForm.fat" placeholder="脂肪量"  type="text"></input>
			</view>
			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">营养价值</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.nutritionalValue" v-model="ruleForm.nutritionalValue" placeholder="营养价值"  type="text"></input>
			</view>
			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">所需食材</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.requiredIngredients" v-model="ruleForm.requiredIngredients" placeholder="所需食材"  type="text"></input>
			</view>
			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="" @tap="cookingGuideTap">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">美食教学</view>
				<input :style='{"border":"0","padding":"0px 20rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' v-if="ruleForm.cookingGuide"  v-model="baseUrl+ruleForm.cookingGuide" placeholder="美食教学"></input>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
			</view>
               
			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">制作方法</view>
				<textarea :style='{"border":"0","padding":"24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"300rpx"}' v-model="ruleForm.preparationMethod" placeholder="制作方法" :maxlength="-1" :disabled="ro.preparationMethod"></textarea>
			</view>
			<view :style='{"padding":"10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","flexWrap":"wrap","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"26rpx","color":"#727272","textAlign":"center"}' class="title">菜品详情</view>
				<xia-editor :AIType="true" ref="dishDetails" :style='{"minHeight":"300rpx","border":"0px solid #efefef","width":"100%","padding":"0 24rpx","height":"auto"}' v-model="ruleForm.dishDetails" placeholder="菜品详情" @editorChange="dishDetailsChange"></xia-editor>
			</view>
			
			<view :style='{"width":"100%","padding":"0","flexWrap":"wrap","justifyContent":"space-between","display":"flex","height":"auto"}' class="btn" >
				<button :style='{"border":"0","padding":"0px","margin":"0 0 30rpx","color":"#fff","borderRadius":"8rpx","background":"#F4BC43","width":"100%","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}' @tap="onSubmitTap(null)" class="bg-red">提交</button>
			</view>
		</form>
		<w-picker  mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="lastClickTimeConfirm" ref="lastClickTime" themeColor="#333333"></w-picker>
	</view>
	<view v-else-if="showType==2">
		<view style="width: 100%;display: flex;flex-wrap: wrap;padding: 10px;">
			<button style="margin: 0 10px 0 0" v-if="(userid&&isAuth('foodRecipe','食谱浏览量'))||(!userid&&isAuthFront('foodRecipe','食谱浏览量'))" size="mini" type="primary" @click="chartClick1()">
				食谱浏览量
			</button>
			<button style="margin: 0 10px 0 0" v-if="(userid&&isAuth('foodRecipe','食谱收藏量'))||(!userid&&isAuthFront('foodRecipe','食谱收藏量'))" size="mini" type="primary" @click="chartClick2()">
				食谱收藏量
			</button>
			<button style="margin: 0 10px 0 0" v-if="(userid&&isAuth('foodRecipe','食品分类'))||(!userid&&isAuthFront('foodRecipe','食品分类'))" size="mini" type="primary" @click="chartClick3()">
				食品分类
			</button>
		</view>
		<view style="width: 100%;">
			<view v-if="chartType1" style="width: 100%;text-align: center;line-height: 2;font-size: 12px;color: #000">
				食谱浏览量
			</view>
			<qiun-data-charts
				v-if="chartType1"
				style="width: 100%;height:"
				type="line"
				:opts="opts1"
				:chartData="chartData1">
			</qiun-data-charts>
			<view v-if="chartType2" style="width: 100%;text-align: center;line-height: 2;font-size: 12px;color: #000">
				食谱收藏量
			</view>
			<qiun-data-charts
				v-if="chartType2"
				style="width: 100%;height:"
				type="column"
				:opts="opts2"
				:chartData="chartData2">
			</qiun-data-charts>
			<view v-if="chartType3" style="width: 100%;text-align: center;line-height: 2;font-size: 12px;color: #000">
				食品分类
			</view>
			<qiun-data-charts
				v-if="chartType3"
				style="width: 100%;height:"
				type="pie"
				:opts="opts3"
				:chartData="chartData3">
			</qiun-data-charts>
		</view>
	</view>
</view>
</template>

<script>
	import wPicker from "@/components/w-picker/w-picker.vue";
	import xiaEditor from '@/components/xia-editor/xia-editor';
	import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	import qiunDataCharts from '@/components/qiun-data-charts/components/qiun-data-charts/qiun-data-charts.vue';
	export default {
		data() {
			return {
				cross:'',
				ruleForm: {
				foodName: '',
				foodCover: '',
				foodCategory: '',
				calories: '',
				protein: '',
				fat: '',
				nutritionalValue: '',
				requiredIngredients: '',
				cookingGuide: '',
				preparationMethod: '',
				dishDetails: '',
				discussNum: '',
				storeUpNum: '',
				},
				foodCategoryOptions: [],
				foodCategoryIndex: 0,
				// 登录用户信息
				user: {},
				ro:{
				   foodName : false,
				   foodCover : false,
				   foodCategory : false,
				   calories : false,
				   protein : false,
				   fat : false,
				   nutritionalValue : false,
				   requiredIngredients : false,
				   cookingGuide : false,
				   preparationMethod : false,
				   dishDetails : false,
				   lastClickTime : false,
				   clickNum : false,
				   discussNum : false,
				   storeUpNum : false,
				},
				virtualPay: false,
				showType: 1,
				userid: '',
				chartData1: {},
				opts1: {},
				chartType1: false,
				chartData2: {},
				opts2: {},
				chartType2: false,
				chartData3: {},
				opts3: {},
				chartType3: false,
			}
		},
		components: {
			wPicker,
			xiaEditor,
			multipleSelect,
			qiunDataCharts,
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
			if(options.showType) {
				this.showType = 2
				if(options.userid&&options.userid!='') {
					this.userid = options.userid
				}
				return false
			}else {
				this.showType = 1
			}
			if(options.virtualPay){
				this.virtualPay = true
			}
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			
			// ss读取

			// 跨表
			this.cross = options.cross;
			if(options.cross){
				var obj = uni.getStorageSync('crossObj');
				for (var o in obj){
					if(o=='foodName'){
						this.ruleForm.foodName = obj[o];
						this.ro.foodName = true;
						continue;
					}
					if(o=='foodCover'){
						this.ruleForm.foodCover = obj[o].split(",")[0];
						this.ro.foodCover = true;
						continue;
					}
					if(o=='foodCategory'){
						this.ruleForm.foodCategory = obj[o];
						for(let x in this.foodCategoryOptions) {
							if(this.foodCategoryOptions[x] == this.ruleForm.foodCategory) {
								this.foodCategoryIndex = Number(x)
							}
						}
						this.ro.foodCategory = true;
						continue;
					}
					if(o=='calories'){
						this.ruleForm.calories = obj[o];
						this.ro.calories = true;
						continue;
					}
					if(o=='protein'){
						this.ruleForm.protein = obj[o];
						this.ro.protein = true;
						continue;
					}
					if(o=='fat'){
						this.ruleForm.fat = obj[o];
						this.ro.fat = true;
						continue;
					}
					if(o=='nutritionalValue'){
						this.ruleForm.nutritionalValue = obj[o];
						this.ro.nutritionalValue = true;
						continue;
					}
					if(o=='requiredIngredients'){
						this.ruleForm.requiredIngredients = obj[o];
						this.ro.requiredIngredients = true;
						continue;
					}
					if(o=='cookingGuide'){
						this.ruleForm.cookingGuide = obj[o];
						this.ro.cookingGuide = true;
						continue;
					}
					if(o=='preparationMethod'){
						this.ruleForm.preparationMethod = obj[o];
						this.ro.preparationMethod = true;
						continue;
					}
					if(o=='dishDetails'){
						this.ruleForm.dishDetails = obj[o];
						this.ro.dishDetails = true;
						continue;
					}
					if(o=='lastClickTime'){
						this.ruleForm.lastClickTime = obj[o];
						this.ro.lastClickTime = true;
						continue;
					}
					if(o=='clickNum'){
						this.ruleForm.clickNum = obj[o];
						this.ro.clickNum = true;
						continue;
					}
					if(o=='discussNum'){
						this.ruleForm.discussNum = obj[o];
						this.ro.discussNum = true;
						continue;
					}
					if(o=='storeUpNum'){
						this.ruleForm.storeUpNum = obj[o];
						this.ro.storeUpNum = true;
						continue;
					}
				}
			}

			var foodCategoryRefParams = {}
			// 下拉框
			res = await this.$api.option(`foodCategory`,`foodCategory`,foodCategoryRefParams);
			this.foodCategoryOptions = res.data;
			this.foodCategoryOptions.unshift("请选择食品分类");
			if(this.ruleForm.foodCategory) {
				for(let x in this.foodCategoryOptions) {
					if(this.foodCategoryOptions[x] == this.ruleForm.foodCategory) {
						this.foodCategoryIndex = Number(x)
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
				res = await this.$api.info(`foodRecipe`, this.ruleForm.id);
				if(res.data.foodCategory) {
					for(let x in this.foodCategoryOptions) {
						if(this.foodCategoryOptions[x] == res.data.foodCategory) {
							this.foodCategoryIndex = Number(x)
						}
					}
				}
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

			// 多级联动参数


			// 日长控件选择日期时间
			lastClickTimeConfirm(val) {
				this.ruleForm.lastClickTime = val.result;
				this.$forceUpdate();
			},

			// 下拉变化
			foodCategoryChange(e) {
				this.foodCategoryIndex = e.target.value
				this.ruleForm.foodCategory = this.foodCategoryOptions[this.foodCategoryIndex]
			},

			foodCoverTap() {
				if(this.ro.foodCover){
					return false
				}
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.foodCover = 'upload/' + res.file;
					_this.$forceUpdate();
				});
			},
			cookingGuideTap () {
				let _this = this;
				if(this.ro.cookingGuide){
					return false
				}
				this.$api.uploadMedia(function(res) {
					_this.ruleForm.cookingGuide = 'upload/' + res.file;
					_this.$forceUpdate();
				});
			},

			getUUID () {
				return new Date().getTime();
			},
			async onSubmitTap(subMitType=null) {
				let that = this
				//跨表计算判断
				var obj;
				if((!this.ruleForm.foodName)){
					this.$utils.msg(`食品名称不能为空`);
					return
				}
				if((!this.ruleForm.foodCategory)){
					this.$utils.msg(`食品分类不能为空`);
					return
				}
				if(this.ruleForm.clickNum&&(!this.$validate.isIntNumer(this.ruleForm.clickNum))){
					this.$utils.msg(`点击次数应输入整数`);
					return
				}
				if(this.ruleForm.discussNum&&(!this.$validate.isIntNumer(this.ruleForm.discussNum))){
					this.$utils.msg(`评论数应输入整数`);
					return
				}
				if(this.ruleForm.storeUpNum&&(!this.$validate.isIntNumer(this.ruleForm.storeUpNum))){
					this.$utils.msg(`收藏数应输入整数`);
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
						let res = await this.$api.list(`foodRecipe`, params);
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
					await this.$api.update(`foodRecipe`, this.ruleForm);
				}else{
					oet = await this.$api.add(`foodRecipe`, this.ruleForm);
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
			async chartClick1() {
				let res = await this.$api.getPublic('foodRecipe/value/foodName/clickNum')
				res = res.data
				let xAxis = [];
				let yAxis = [];
				let pArray = []
				for(let i=0;i<res.length;i++){
					xAxis.push(res[i]['foodName']);
					yAxis.push(parseFloat((res[i].total)));
					pArray.push({
						value: parseFloat((res[i].total)),
						name: res[i]['foodName']
					})
				}
				this.opts1 = {
					color: ["#1890FF","#91CB74","#FAC858","#EE6666","#73C0DE","#3CA272","#FC8452","#9A60B4","#ea7ccc"],
					padding: [15,10,0,15],
					enableScroll: false,
					legend: {
						show: false
					},
					xAxis: {
						disableGrid: true
					},
					yAxis: {
						gridType: "dash",
						dashLength: 2,
						showTitle: false
					},
					extra: {
						line: {
							type: "straight",
							width: 2,
							activeType: "hollow"
						}
					}
				}
				this.chartData1 = {
					categories: xAxis,
					series: [
						{
							data: yAxis
						}
					]
				};
				this.chartType1 = true
			},
			async chartClick2() {
				let res = await this.$api.getPublic('foodRecipe/value/foodName/storeUpNum')
				res = res.data
				let xAxis = [];
				let yAxis = [];
				let pArray = []
				for(let i=0;i<res.length;i++){
					xAxis.push(res[i]['foodName']);
					yAxis.push(parseFloat((res[i].total)));
					pArray.push({
						value: parseFloat((res[i].total)),
						name: res[i]['foodName']
					})
				}
				this.opts2 = {
					color: ["#1890FF","#91CB74","#FAC858","#EE6666","#73C0DE","#3CA272","#FC8452","#9A60B4","#ea7ccc"],
					padding: [15,15,10,5],
					enableScroll: false,
					xAxis: {
						disableGrid: true,
						rotateLabel: true,
						rotateAngle: 30
					},
					legend: {
						show: false
					},
					yAxis: {},
					extra: {
						column: {
							type: "group",
							width: 30,
							activeBgColor: "#000000",
							activeBgOpacity: 0.08
						}
					}
				}
				this.chartData2 = {
					categories: xAxis,
					series: [
						{
							data: yAxis
						}
					]
				};
				this.chartType2 = true
			},
			async chartClick3() {
				let res = await this.$api.getPublic('foodRecipe/group/foodCategory')
				res = res.data
				let xAxis = [];
				let yAxis = [];
				let pArray = []
				for(let i=0;i<res.length;i++){
					xAxis.push(res[i]['foodCategory']);
					yAxis.push(parseFloat((res[i].total)));
					pArray.push({
						value: parseFloat((res[i].total)),
						name: res[i]['foodCategory']
					})
				}
				this.opts3 = {
					color: ["#1890FF","#91CB74","#FAC858","#EE6666","#73C0DE","#3CA272","#FC8452","#9A60B4","#ea7ccc"],
					padding: [5,5,5,5],
					enableScroll: false,
					extra: {
						pie: {
							activeOpacity: 0.5,
							activeRadius: 10,
							offsetAngle: 0,
							labelWidth: 15,
							border: false,
							borderWidth: 3,
							borderColor: "#FFFFFF"
						}
					}
				}
				this.chartData3 = {
					series: [
						{
							data: pArray
						}
					]
				};
				this.chartType3 = true
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
