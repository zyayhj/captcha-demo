<?php
class TouClick{
	var $pubkey;
	var $prikey;
	
	function __construct($pubkey='',$prikey=''){
		$this->pubkey = $pubkey;
		$this->prikey = $prikey;
	}
	
	/**
	 * @date: 2016-3-23 上午10:20:04
	 * @author : rainbow
	 * @param token 二次验证口令，单次有效
	 * @param checkAddress 二次验证地址，二级域名
	 * @param checkCode 校验码，开发者自定义，一般采用手机号或者用户ID，用来更细致的频次控制
	 * @return :
	 */
	public function check($checkCode, $checkAddress, $token, $userName = '', $userId = 0) {
		if (empty ( $checkAddress ) || empty ( $token )) {
			exit ( '参数为空' );
		}
		if (!preg_match('/^[\w\-]/',$checkAddress)){
			exit('参数格式不正确');
		}
		$params ['ckcode'] = $checkCode;
		$params ['un'] = $userName;
		$params ['ud'] = $userId;
		$params ['ip'] = @gethostbyname ( $_ENV ['COMPUTERNAME'] );
		$params ['i'] = $token;
		$params ['b'] = $this->pubkey;
		$sign = $this->getSign ( $params, $this->prikey );
		$params ['sign'] = $sign;
		$paramStr = $this->getStr ( $params );
		$url = "http://" . $checkAddress . ".touclick.com/sverify.touclick" . '?' . $paramStr;
		
		// use curl
		$ch = curl_init ();
		curl_setopt ( $ch, CURLOPT_URL, $url );
		curl_setopt ( $ch, CURLOPT_RETURNTRANSFER, 1 );
		curl_setopt ( $ch, CURLOPT_HEADER, 0 );
		$result = curl_exec ( $ch );
		curl_close ( $ch );
		
		return json_decode ( $result, true );//{code:0,message:''}
	}

	/**
	 * @date: 2016-3-23 上午10:30:15
	 * 
	 * @author : rainbow
	 * @param : $params        	
	 * @param : $prikey        	
	 * @return :md5($sign)
	 */
	private function getSign($params, $prikey) {
		ksort ( $params ) ? $paramsStr = $this->getStr ( $params ) : exit ( '排序失败' );
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
	private function getStr($params) {
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

}

?>