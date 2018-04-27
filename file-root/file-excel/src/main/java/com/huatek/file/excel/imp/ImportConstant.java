package com.huatek.file.excel.imp;

public class ImportConstant {
	public static class ImportStatus {
		public static final String SUBMIT ="excel_import_status_submit";
		public static final String VALIDATE_SUCCESS ="excel_import_status_validateSuccess";
		public static final String VALIDATE_FAIL="excel_import_status_validateFail";
		public static final String PERSIST_SUCCESS= "excel_import_status_persistSuccess";
		public static final String PERSIST_FAIL= "excel_import_status_persistFail";
	}
	public static class NotNull {
		public static final int TRUE = 1;
		public static final int FALSE = 2;
	}
	public static class FieldType {
		public static final String NUMBER = "excel_import_field_type_number";
		public static final String DATE="excel_import_field_type_date";
		public static final String STRING="excel_import_field_type_string";
	}
	public static class ValidateType {
		public static final String COLLECTION = "excel_import_validate_type_collection";
		public static final String DICTIONARY="excel_import_validate_type_dictionary";
	}
	public static class TransformType {
		public static final String MAP = "excel_import_transform_type_map";
		public static final String DICTIONARY="excel_import_transform_type_dictionary";
	}
	public static class PersisType {
		public static final String  MODEL= "excel_import_persist_type_model";
		public static final String SERVICE="excel_import_persist_type_service";
	}
}
