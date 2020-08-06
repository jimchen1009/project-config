package com.project.config.export.excel.convert;

import java.util.ArrayList;
import java.util.List;

public class ExcelListConverter extends ExcelCellConverter {

	private final ExcelCellConverter cellConverter;

	public ExcelListConverter(ExcelCellConverter cellConverter) {
		super(String.format("[%s]", cellConverter.getType()), null);
		this.cellConverter = cellConverter;
	}


	@Override
	protected Object string2ClassValue(String string) throws Exception {
		int indexOf = string.indexOf("[");
		int lastIndexOf = string.lastIndexOf("]");
		String trim = string.substring(indexOf + 1, lastIndexOf).trim();
		String[] strings = trim.split(",");
		List<Object> objectList = new ArrayList<>(strings.length);
		for (String s : strings) {
			objectList.add(cellConverter.string2ClassValue(s));
		}
		return objectList;
	}
}