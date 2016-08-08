
## TouClick Captcha JavaScript Lib (v5-2-x) 说明文档


#### 验证码类：TouClick
	
#### 静态方法:

```javascript
/* 类似于 jQuery.ready 的功能，其他验证码调用代码均应在参数函数体中实现 */
ready(function(){}) 
```

####### 调用实例:

```javascript
TouClick.ready(function(){
	...
	/*
	 * 在此处实现验证码调用逻辑
	 */
	...
});
```

#### 构造函数:
		
```javascript
/*
 *  构造函数，调用方式可以是 new TouClick()  也可以是  TouClick()
 *  @param dom：验证码代码要嵌入的位置 default : document.body
 *  @param options: 初始化参数
 */
TouClick(dom, options) 
```

####### OPTIONS

```javascript
var options = {
	/*
	 * 验证成功以后的回调函数
	 * 需要调用者实现这个函数，并将参数 token,checkAddress,sid 传到服务器端
	 *
	 */
	onSuccess: function( obj ) {
		/* 
		 * 点触 SDK会对 obj 赋值，obj 共有3个属性值分别是 token/checkAddress/sid 
		 * 均为字符串类型 ,请开发者将 3个属性值传输到后端服务，以进行二次验证
		 */
	},

}
```

#######调用示例:

example1:

```html
<div id="target1"></div>
<!--以下隐藏域为示例代码，开发者自行选择将这3个属性传输到后台的方式-->
<input id="token" type="hidden"/>
<input id="checkAddress" type="hidden"/>
<input id="sid" type="hidden"/>

<script type="text/javascript">
	var tc = new TouClick('target1',{
		onSuccess: function( obj){
			document.getElementById('token') = obj.token;
			document.getElementById('checkAddress') = obj.checkAddress;
			document.getElementById('sid') = obj.sid;
		}
	});
</script>
```

example2:

```html
<div id="target2"></div>
<!--以下隐藏域为示例代码，开发者自行选择将这3个属性传输到后台的方式-->
<input id="token" type="hidden"/>
<input id="checkAddress" type="hidden"/>
<input id="sid" type="hidden"/>

<script type="text/javascript">
	var tc = new TouClick(document.getElementById('target2'),{
		onSuccess: function( obj){
			document.getElementById('token') = obj.token;
			document.getElementById('checkAddress') = obj.checkAddress;
			document.getElementById('sid') = obj.sid;
		}
	});
</script>
```

####对象方法:

```javascript
destory()	/* 销毁验证码实例并且从页面中移除验证码相关html*/
```
