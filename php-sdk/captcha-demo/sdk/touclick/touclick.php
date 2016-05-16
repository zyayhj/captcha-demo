<?php
require_once '/config/config.php';

/**
 * @date: 2016-3-23 上午10:20:04
 * 
 * @author : rainbow
 * @return :
 */
function check($checkCode, $checkKey, $token, $userName = '', $userId = 0) {
	if (empty ( $checkCode ) || empty ( $checkKey ) || empty ( $token )) {
		exit ( '参数为空' );
	}
	
	$params ['ckcode'] = $checkCode;
	$params ['un'] = $userName;
	$params ['ud'] = $userId;
	$params ['ip'] = @gethostbyname ( $_ENV ['COMPUTERNAME'] );
	$params ['i'] = $token;
	$params ['b'] = PUBKEY;
	$sign = getSign ( $params, PRIKEY );
	$params ['sign'] = $sign;
	$paramStr = getStr ( $params );
	$url = HTTP . $checkKey . POSTFIX . '?' . $paramStr;
	
	// use curl
	$ch = curl_init ();
	curl_setopt ( $ch, CURLOPT_URL, $url );
	curl_setopt ( $ch, CURLOPT_RETURNTRANSFER, 1 );
	curl_setopt ( $ch, CURLOPT_HEADER, 0 );
	$result = curl_exec ( $ch );
	curl_close ( $ch );
	
	$res = json_decode ( $result, true );
	if ($res ['code'] === 0) {
		return 0;
	} else {
		return $res ['message'];
	}
}

/**
 * @date: 2016-3-23 上午10:30:15
 * 
 * @author : rainbow
 * @param : $params        	
 * @param : $prikey        	
 * @return :md5($sign)
 */
function getSign($params, $prikey) {
	ksort ( $params ) ? $paramsStr = getStr ( $params ) : exit ( '排序失败' );
	$sign = $paramsStr . $prikey;
	return md5 ( $sign );
}

/**
 * 数组=》字符串 key=value&key=value
 * 
 * @author : rainbow
 * @param : $params        	
 * @return : $paramsStr
 */
function getStr($params) {
	$paramsStr = '';
	if (! $params || ! is_array ( $params )) {
		exit ( '参数错误' );
	}
	$keys = array_keys ( $params );
	foreach ( $params as $key => $value ) {
		if ($key == end ( $keys )) {
			$paramsStr .= $key . '=' . $value;
		} else {
			$paramsStr .= $key . '=' . $value . '&';
		}
	}
	return $paramsStr;
}

?>