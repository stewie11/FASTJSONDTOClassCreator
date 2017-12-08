package rushb;

import java.util.HashMap;
import java.util.Map;

public enum BaseCode {
	
	SUCCESS(200, "Opeartion success"),
	REQUEST_PARAM_VALIDATE_ERROR(20000, "请求参数错误"),
	REDIS_CHANNEL_TIMEOUR_ERROR(12001, "Rabbitmq channel 请求超时"),
	
	FAIL_TO_INVOKE_REMOTE_SERVICE(9005, "请求服务出错"),
	
	COMMUNITY_POS_BIND_FAIL(9801, "联盟圈POS机绑定失败"),
	COMMUNITY_POS_MEMBER_ALREADY_BIND_SHOP(9802, "成员二维码已绑定门店"),
	COMMUNITY_POS_ALREADY_BIND_COMMUNITY(9803, "门店已绑定联盟圈"),
	COMMUNITY_POS_USER_NOT_BIND_COMMUNITY(9805, "用户未绑定联盟圈"),
	
	COMMUNITY_POS_PRE_BIND_FAIL(9808, "联盟圈POS机绑定获取联盟圈信息失败"),
	COMMUNITY_POS_GET_COMMUNITY_FAIL(9813, "获取绑定联盟圈列表信息失败"),
	COMMUNITY_EXIST_PROMOTIONS(9814, "联盟圈存在相关的活动信息 "),
	COMMUNITY_POS_GET_SHOPVENDOR_FAIL(9819, "获取POS机绑定商户门店信息失败"),
	VERIFICATION_FAILED_VERIFICATED(5209, "返现券已经核销"),
	VERIFICATION_FAILED_ERROR(5210, "无法查询到返现券相关信息"),
	REBATE_CARD_VERIFICATE_ERROR_USED(20300,"卡券已被核销"),
    REBATE_CARD_VERIFICATE_ERROR_NUM_EXCEEDED(20301,"核销数量不为超过预设值"),
    REBATE_CARD_VERIFICATE_ERROR_STORE_NOT_PARTICIPATE(20302,"核销门店并未参加返现活动"),
    REBATE_CARD_VERIFICATE_ERROR_NOT_ACTIVE(20303,"卡券核销时间未开始"),
    REBATE_CARD_VERIFICATE_ERROR_END(20304,"卡券已过期"),
    REBATE_CARD_VERIFICATE_ERROR_CARD_NOT_FOUND(20305,"未查询到要核销的卡券信息"),
    REBATE_CARD_VERIFICATE_ERROR_INVALID(20306,"卡券处于不可核销状态"),
	REBATE_CARD_VERIFICATE_ERROR_PROMOTION_INVALID(20307,"订单活动状态异常"),
	REBATE_CARD_VERIFICATE_ERROR_CARD_RELATED_INFO_ERROR(20308,"卡券相关详情获取错误"),
	UNKNOWN(-1, "Unknow issue");
	
	private int code;
	private String message;

	BaseCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public static BaseCode valueOf(int code) {
		BaseCode ec = values.get(code);
		if (ec != null)
			return ec;
		return UNKNOWN;
	}
	
	public static BaseCode valueOfCodeStr(String codeStr) {
		try{
			int code = Integer.valueOf(codeStr);
			BaseCode ec = values.get(code);
			if (ec != null)
				return ec;
			return UNKNOWN;
		} catch(Exception e){
			return UNKNOWN;
		}
		
	}
	

	private static final Map<Integer, BaseCode> values = new HashMap<Integer, BaseCode>();
	static {
		for (BaseCode ec : BaseCode.values()) {
			values.put(ec.code, ec);
		}
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
