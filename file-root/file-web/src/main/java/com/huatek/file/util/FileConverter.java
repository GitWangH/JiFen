package com.huatek.file.util;

import java.io.File;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.util.PropertyUtil;

public class FileConverter {
	private final static String host = PropertyUtil
			.getConfigValue("file_converter_host");
	private final static String port = PropertyUtil
			.getConfigValue("file_converter_port");
	private static OpenOfficeConnection connection = null;

	public static void main(String[] args) throws ConnectException {

		FileConverter.converter("C:/Users/Administrator/Desktop/x.docx",
				"C:/Users/Administrator/Desktop/x.pdf");
	}

	public static void converter(String inputFilePath, String outputFilePath) {
		try {
			if (connection == null ||!connection.isConnected()) {
				connection = new SocketOpenOfficeConnection(host,
						Integer.valueOf(port));
				connection.connect();
			}

			DocumentConverter converter = new StreamOpenOfficeDocumentConverter(
					connection);
			converter.convert(new File(inputFilePath), new File(outputFilePath));
		} catch (ConnectException e) {
			throw new BusinessRuntimeException("文件转换错误", "-1", e);

		}

	}

}
