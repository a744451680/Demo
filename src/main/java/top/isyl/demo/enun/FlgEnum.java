package top.isyl.demo.enun;

/**
 * Flg 枚举
 * @author Ylong
 *
 */
public enum FlgEnum {

	
	FLG_SKY("10001","天气查询"),
	FLG_OPPEN("10002","这个还没用");
	
	private  String code;
    private  String message;
    
    FlgEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
