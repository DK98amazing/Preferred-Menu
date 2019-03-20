package commoncom.pmenu.comon.exception;

import commoncom.pmenu.comon.api.Entity;
import lombok.Data;

@Data
public class ExceptionMessage implements Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 666565533462138482L;
	private Integer code;
	private String cn;
	private String en;

	public ExceptionMessage(Integer code, String en, String cn) {
		this.cn = cn;
		this.code = code;
		this.en = en;
	}

	
	
	
	
	
	public Integer getCode() {
		return code;
	}






	public void setCode(Integer code) {
		this.code = code;
	}






	public String getCn() {
		return cn;
	}






	public void setCn(String cn) {
		this.cn = cn;
	}






	public String getEn() {
		return en;
	}






	public void setEn(String en) {
		this.en = en;
	}






	public static long getSerialversionuid() {
		return serialVersionUID;
	}






	public String toString() {
		StringBuffer buffer = new StringBuffer();
		return buffer.append("[code:").append(code).append(",cn").append(cn).append(",en").append(en).append("]")
				.toString();
	}
}
