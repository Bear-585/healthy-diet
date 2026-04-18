<template>
<view class="content">
	<view :style='{"width":"100%","padding":"0","position":"relative","background":"#fff","height":"auto"}'>
		<form :style='{"width":"100%","padding":"24rpx","background":"#fff","display":"block","height":"auto"}'>
			<view :style='{"padding":"0 10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
				<input :style='{"border":"0","padding":"0px 20rpx 0px 20rpx","margin":"0px","color":"#333","borderRadius":"8rpx","flex":"1","background":"none","fontSize":"28rpx","height":"80rpx"}' v-model="forum.title" placeholder="标题"></input>
			</view>
			<view :style='{"padding":"0 10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
				<picker :style='{"width":"100%","flex":"1","height":"auto"}' @change="setIsDoneTap" :value="index" :range="options">
					<view class="uni-picker-type" :style='{"width":"100%","padding":"0 20rpx","lineHeight":"80rpx","fontSize":"28rpx","color":"#F75433"}'>{{options[index]}}</view>
				</picker>
			</view>
			<view :style='{"padding":"0 10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
				<picker :style='{"width":"100%","flex":"1","height":"auto"}' @change="setIsAnonTap" :value="index2" :range="options2">
					<view class="uni-picker-type" :style='{"width":"100%","padding":"0 20rpx","lineHeight":"80rpx","fontSize":"28rpx","color":"#F75433"}'>{{options2[index2]}}</view>
				</picker>
			</view>
			<view style="display: flex;align-items: center;padding: 10px 0;">
				<image @click="imgView(item,index)" v-for="(item,index) in picList" :key="index" :src="baseUrl + item" style="width: 50px;margin: 0 5px 0 0;" mode="widthFix"></image>
				<view style="padding: 0 10px;line-height: 20px;background: #ccc;color: #333;" @click="uploadImg">上传图片</view>
			</view>
			<view :style='{"padding":"0 10rpx","margin":"0 0 24rpx 0","borderColor":"#FAC69680","alignItems":"center","borderRadius":"10rpx","borderWidth":"4rpx","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
				<xia-editor :style='{"minHeight":"300rpx","width":"100%","padding":"12rpx","margin":"0px","background":"none","height":"auto"}' v-model="forum.content" @editorChange="contentChange" placeholder="内容"></xia-editor>
			</view>
			<view :style='{"width":"100%","padding":"0","justifyContent":"center","display":"flex","height":"auto"}'>
				<button :style='{"border":"0","padding":"0px","margin":"0 0 30rpx 0","color":"#fff","borderRadius":"8rpx","background":"#F4BC43","width":"100%","lineHeight":"84rpx","fontSize":"28rpx","height":"84rpx"}' @tap="onSubmitTap" class="bg-red margin-tb-sm">确认提交</button>
			</view>
		</form>
	</view>
</view>
</template>

<script>
	import xiaEditor from '@/components/xia-editor/xia-editor';
	export default {
		data() {
			return {
				forum: {
					content: '',
					id: '',
					title: '',
					isDone: '开放',
					parentId: 0,
					isAnon: 0,
					cover: ''
				},
				picList: [],
				index: 0,
				options: ['开放', '关闭'],
				index2: 0,
				options2: ['实名', '匿名'],
				username: '',
				user: {},
			}
		},
		components: {
			xiaEditor
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			}
		},
		async onLoad(options) {
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			if(table == `consumer`){
				this.username = this.user.account
			}
			if (options.id) {
				this.id = options.id;
				let res = await this.$api.info('forum',this.id);
				if(res.data.cover) {
					this.picList = res.data.cover.split(',')
				}
				this.forum = res.data
			}
			this.styleChange()
		},
		methods: {
			imgView(url,index){
				let that = this
				uni.showActionSheet({
					itemList: ['预览','删除'],
					success(res) {
						if(res.tapIndex==0){
							let arr = []
							for(let x in that.picList){
								arr.push(that.baseUrl + that.picList[x])
							}
							uni.previewImage({
								current: that.baseUrl + url,
								urls: arr
							})
						}else if(res.tapIndex==1){
							that.picList.splice(index,1)
						}
					}
				})
			},
			async uploadImg(){
				let _this = this;
				await this.$api.upload(function(res) {
					_this.picList.push('upload/' + res.file)
					_this.$forceUpdate();
				});
			},
			contentChange(e) {
				this.forum.content = e
			},
			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('.my-publish-pv .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.myPublishForm.content.input.backgroundColor
					// })
				})
			},
			async onSubmitTap() {
				if(this.forum.title==''){
					this.$utils.msg('标题不能为空');
					return false
				}
				if(this.forum.content==''){
					this.$utils.msg('内容不能为空');
					return false
				}
				if(this.picList.length){
					this.forum.cover = this.picList.join(',')
				}else {
					this.$utils.msg('封面图不能为空');
					return false
				}
				this.forum.username = this.username;
				if (this.id) {
					await this.$api.update('forum',this.forum);
				} else {
					this.forum.topTime = this.$utils.getCurDateTime()
					await this.$api.save('forum',this.forum);
				}
				this.$utils.msgBack('操作成功');
			},
			setIsDoneTap(e) {
				// this.forum.isDone = e;
				this.index = e.target.value
				this.forum.isDone = this.options[this.index]
				this.$forceUpdate();
			},
			setIsAnonTap(e){
				this.index2 = e.target.value
				this.forum.isAnon = this.index2
				this.$forceUpdate();
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
