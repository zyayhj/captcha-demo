<?php
require_once '../captcha-demo/touclick.php';

header("Content-type: text/html; charset=utf-8");
if (empty($_POST['checkCode']) ||empty($_POST['checkAddress'])||empty($_POST['token'])) {
	exit('提交数据为空');
}
$checkCode = strip_tags($_POST['checkCode']);
$checkKey = strip_tags($_POST['checkAddress']);
$token = strip_tags($_POST['token']);

$PUBKEY = "";//从admin.touclick.com注册获取
$PRIKEY = "";

var touclick = new TouClick($PUBKEY,$PRIKEY);
$res = touclick->check($checkCode, $checkKey, $token);
//$res['code'] 详细说明请看README.md
if ($res ['code'] === 0) {
	exit('验证成功');
} else {
	exit($res ['message']);
}
?>