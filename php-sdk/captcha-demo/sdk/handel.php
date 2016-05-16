<?php
require_once '/touclick/touclick.php';

header("Content-type: text/html; charset=utf-8");
if (empty($_POST['checkAddress'])||empty($_POST['token'])) {
	exit('提交数据为空');
}
$checkCode = strip_tags($_POST['checkCode']);
$checkKey = strip_tags($_POST['checkAddress']);
$token = strip_tags($_POST['token']);
// if (!empty($_POST['username']) ) {
// 	$userName = strip_tags($_POST['username']);
// }
$res = check($checkCode, $checkKey, $token);
if ($res) {
	exit($res);
} else {
	exit('验证成功');
}




?>