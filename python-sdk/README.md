# 点触验证码 Python SDK

## 开发环境

 - Python (推荐2.7.0以上版本）
 - tornado框架

## 使用指南

1. 安装SDK

  ```bash
  $ sudo python setup.py install
  ```

2. 初始化验证类

  ```python
  # pub_key 是你的公钥
  # pri_key 是你的私钥
  tc = TouclickLib(pub_key, pri_key)
  ```

3. 二次验证
  
  ```python
  tc = TouclickLib(pub_key, pri_key)
  check_code = self.get_argument(tc.CHECK_CODE, "")
  check_key = self.get_argument(tc.CHECK_KEY, "")
  token = self.get_argument(tc.TOKEN, "")
  code, msg = tc.check(check_code, check_key, token)
  if code == 0:
      #执行自己的程序逻辑
      pass
  ```
  
  `check`方法的返回值为元组`(code, message)`，可能的取值如下所示：
  
  ```python
  (0, "验证正确")
  (1, "该验证已过期")
  (2, "公钥不可为空")
  (3, "一次验证返回的token为必需参数,不可为空")
  (4, "公钥不正确")
  (5, "CheckCode有误,请确认CheckCode是否和一次验证传递一致"),
  (6, "sign加密错误,请检查参数是否正确")
  (7, "一次验证错误")
  (8, "点触服务器异常")
  (9, "http请求异常")
  (10, "json转换异常,可能是请求地址有误,请检查请求地址(http://[checkAddress].touclick.com/sverify.touclick?参数)")
  ```

## 激活SDK

进入activator文件夹，运行:

```bash
$ python start.py
```

在浏览器中访问http://127.0.0.1:8088,按照引导提示操作

## 运行demo

进入demo文件夹，运行:

```bash
$ python start.py
```

在浏览器中访问http://127.0.0.1:8088即可看到demo界面
