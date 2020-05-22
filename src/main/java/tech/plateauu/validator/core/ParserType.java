package tech.plateauu.validator.core;

public enum ParserType {
	JSON(0),
	CSV(1);

	private final int order;

	ParserType(int order) {
		this.order = order;
	}

	public int getOrder() {
		return order;
	}
}
