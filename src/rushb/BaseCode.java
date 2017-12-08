package rushb;

import java.util.HashMap;
import java.util.Map;

public enum BaseCode {
	
	SUCCESS(200, "Opeartion success"),
	REQUEST_PARAM_VALIDATE_ERROR(20000, "�����������"),
	REDIS_CHANNEL_TIMEOUR_ERROR(12001, "Rabbitmq channel ����ʱ"),
	
	FAIL_TO_INVOKE_REMOTE_SERVICE(9005, "����������"),
	
	COMMUNITY_POS_BIND_FAIL(9801, "����ȦPOS����ʧ��"),
	COMMUNITY_POS_MEMBER_ALREADY_BIND_SHOP(9802, "��Ա��ά���Ѱ��ŵ�"),
	COMMUNITY_POS_ALREADY_BIND_COMMUNITY(9803, "�ŵ��Ѱ�����Ȧ"),
	COMMUNITY_POS_USER_NOT_BIND_COMMUNITY(9805, "�û�δ������Ȧ"),
	
	COMMUNITY_POS_PRE_BIND_FAIL(9808, "����ȦPOS���󶨻�ȡ����Ȧ��Ϣʧ��"),
	COMMUNITY_POS_GET_COMMUNITY_FAIL(9813, "��ȡ������Ȧ�б���Ϣʧ��"),
	COMMUNITY_EXIST_PROMOTIONS(9814, "����Ȧ������صĻ��Ϣ "),
	COMMUNITY_POS_GET_SHOPVENDOR_FAIL(9819, "��ȡPOS�����̻��ŵ���Ϣʧ��"),
	VERIFICATION_FAILED_VERIFICATED(5209, "����ȯ�Ѿ�����"),
	VERIFICATION_FAILED_ERROR(5210, "�޷���ѯ������ȯ�����Ϣ"),
	REBATE_CARD_VERIFICATE_ERROR_USED(20300,"��ȯ�ѱ�����"),
    REBATE_CARD_VERIFICATE_ERROR_NUM_EXCEEDED(20301,"����������Ϊ����Ԥ��ֵ"),
    REBATE_CARD_VERIFICATE_ERROR_STORE_NOT_PARTICIPATE(20302,"�����ŵ겢δ�μӷ��ֻ"),
    REBATE_CARD_VERIFICATE_ERROR_NOT_ACTIVE(20303,"��ȯ����ʱ��δ��ʼ"),
    REBATE_CARD_VERIFICATE_ERROR_END(20304,"��ȯ�ѹ���"),
    REBATE_CARD_VERIFICATE_ERROR_CARD_NOT_FOUND(20305,"δ��ѯ��Ҫ�����Ŀ�ȯ��Ϣ"),
    REBATE_CARD_VERIFICATE_ERROR_INVALID(20306,"��ȯ���ڲ��ɺ���״̬"),
	REBATE_CARD_VERIFICATE_ERROR_PROMOTION_INVALID(20307,"�����״̬�쳣"),
	REBATE_CARD_VERIFICATE_ERROR_CARD_RELATED_INFO_ERROR(20308,"��ȯ��������ȡ����"),
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
