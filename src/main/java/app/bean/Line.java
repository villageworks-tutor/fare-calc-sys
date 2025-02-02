package app.bean;

public class Line {
	
	/**
	 * フィールド
	 */
	private String code; // 路線コード
	private String name; // 路線名
	
	/**
	 * コンストラクタ
	 * @param code 路線コード
	 * @param name 路線名
	 */
	public Line(String code, String name) {
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
