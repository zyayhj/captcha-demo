# 点触验证码 PHP-SDK

##开发环境

* PHP环境(PHP 5 >= 5.2.0, PECL json >= 1.2.0, PHP 7)
* CURL扩展

## 文件说明

* activate/ 点触验证码公钥激活。请访问 activate/activate.html，并按照要求输入公钥和私钥，要求必须和 activate/check.php放在同一个目录下
* captcha-demo/ web项目调用示例
* captcha-php-sdk/ SDK源码

##公钥激活
	
`为了公钥能正常使用，请务必进行激活，如更换SDK，则需要使用新SDK的激活程序重新进行激活`

`激活过后，建议删除 activate/ 文件夹`

`请访问 activate/activate.html`

## 如何使用

1. 