# JavaScript Lib 说明文档

验证码类：TouClick
	
	静态方法:
		void ready(fun) 类似于 jQuery.ready 的功能，其他验证码调用代码均应在参数函数体中实现 

			TouClick.ready(function(){
				...
				/*
				 * 在此处实现验证码调用逻辑
				 */
				...
			});

	构造函数:
		/*
		 *  构造函数，调用方式可以是 new TouClick()  也可以是  TouClick()
		 *  @param dom：验证码代码要嵌入的位置 default : document.body
		 */
		TouClick(dom) 

	对象方法:
		/*
		 * @param 初始化验证码的参数对象
		 *		modal:true
		 		position:"center"
		 *       
		 */
		start(obj)
		TouClick hidden()	隐藏验证码，返回当前TouClick对象
		TouClick show()		显示验证码，返回当前TouClick对象
		TouClick reload()	刷新验证码图片，返回当前TouClick对象

	其他:
		start(obj) 方法的参数说明
			var obj = {
				/*
				 * 是否为模态窗口出现
				 * @type: Boolean
				 * @default: true
				 */ 
				modal:true,
				
				/*
				 * 验证码框的位置
				 * @type: String/Function
				 * @default: "center" 
				 * 该项参数也可以是函数类型，例如：
				 *         position: function({width:验证码的宽,height:验证码的高}){
				 *             返回值应该是css属性组合成的对象
				 *             return {}; 
				 *         }
				 */ 
				position:"center",
				
				/*
				* 是否自适应屏幕大小，移动端建议设置为true
				* @type: Boolean
				* @default: true
				*/
				fit:true, 
				
				/*
				* 验证成功以后的回调函数
				* 需要调用者实现这个函数，并将参数 token 与 checkAddress 传到服务器端
				*
				*/
				onSuccess : function( {token:'',checkAddress:''} ) {
				
				}
			}
