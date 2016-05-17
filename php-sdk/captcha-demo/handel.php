<?php
require_once '../captcha-demo/touclick.php';

header("Content-type: text/html; charset=utf-8");
if (empty($_POST['checkCode']) ||empty($_POST['checkAddress'])||empty($_POST['token'])) {
	exit('提交数据为空');
}
$checkCode = strip_tags($_POST['checkCode']);
$checkAddress = strip_tags($_POST['checkAddress']);
$token = strip_tags($_POST['token']);

/*$PUBKEY 、 $PRIKEY 从http://admin.touclick.com注册获取 */
$PUBKEY = "";
$PRIKEY = "";

var touclick = new TouClick($PUBKEY,$PRIKEY);
$res = touclick->check($checkCode, $checkAddress, $token);
//$res['code'] 的详细说明请看README.md
if ($res ['code'] === 0) {
	exit('验证成功');
} else {
	exit($res ['message']);
}
?>