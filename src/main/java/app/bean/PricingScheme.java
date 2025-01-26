package app.bean;

public class PricingScheme {
	
	/**
	 * フィールド
	 */
	private String code; // 運賃体系コード
	private String name; // 運賃体系名
	
	/**
	 * コンストラクタ
	 * @param code 運賃体系コード
	 * @param name 運賃体系名
	 */
	public PricingScheme(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Line [code=" + code + ", name=" + name + "]";
	}
	
}
