package commoncom.pmenu.comon.constant;

public enum ErrorCode {
	SUCCESS(1, "success", "成功"), FAILURE(-1, "failure", "失败");

	private Integer code;
	private String en;
	private String cn;

	ErrorCode(Integer code, String en, String cn) {
		this.code = code;
		this.en = en;
		this.cn = cn;
	}

	public Integer getCode() {
		return this.code;
	}

	public String getEn() {
		return this.en;
	}

	public String getCn() {
		return this.cn;
	}
}
