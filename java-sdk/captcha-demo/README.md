#Touclick Java SDK


##开发环境
  
  - jdk1.5及以上
  - tomcat
  
##演示demo
   1.在resources下config.properties中填写从点触官网注册获得的公钥和私钥
   2.在index.html中填写
      <script src="http://js.touclick.com/js.touclick?b=公钥(从点触官网获得)" ></script>
   3.运行demo   


##使用指南

1.激活:
   a.将ActivateServlet.java复制到自己的项目中
   b.在web.xml中添加如下配置
   <servlet>
      <servlet-name>activateServlet</servlet-name>
      <servlet-class>com.touclick.captcha.ActivateServlet</servlet-class>
   </servlet>
   <servlet-mapping>
      <servlet-name>activateServlet</servlet-name>
      <url-pattern>/activate.do</url-pattern>
   </servlet-mapping>
   c.启动自动的项目访问http://localhost:8080/项目名/activate.do
   		说明:端口号不一定是8080,依自己项目所使用的服务器为准;路径依自己放置的路径为准
   d.在打开的页面中按照引导提示操作激活

激活成功后再进行如果操作:

2.导包:
   导入java-sdk-1.0.0.jar以及其他相关jar包 (lib目录下)

3.新建config.properties文件:
   在自已的项目classpath下新建config.properties文件(如果本身项目中有,则不需要建立),
      填写公钥和私钥供sdk使用.具体参照captcha-demo中的使用方法.
	#公钥
	PUB_KEY= 公钥(从点触官网注册)
	#私钥
	PRI_KEY= 私钥(从点触官网注册)

    	

