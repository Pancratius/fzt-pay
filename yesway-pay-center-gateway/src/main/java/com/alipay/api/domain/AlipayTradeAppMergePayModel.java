package com.alipay.api.domain;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

/**
 * app合并支付接口
 *
 * @author auto create
 * @since 1.0, 2016-08-24 15:19:08
 */
public class AlipayTradeAppMergePayModel extends AlipayObject {

	private static final long serialVersionUID = 6771193734982722816L;

	/**
	 * 如果预创建成功，支付宝返回该预下单号，后续商户使用该预下单号请求支付宝支付接口
	 */
	@ApiField("pre_order_no")
	private String preOrderNo;

	public String getPreOrderNo() {
		return this.preOrderNo;
	}
	public void setPreOrderNo(String preOrderNo) {
		this.preOrderNo = preOrderNo;
	}

}
