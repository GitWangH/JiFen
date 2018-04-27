package com.huatek.file.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	/***
	 * 文件操作的字节大小1M.
	 */
	private static final int FILE_OPERATE_BUFFER_SIZE = 1048576;
	
	private static final String IMAGE_TYPE = "JPG,PNG,GIF,BMP";
	private static final String OFFICE_TYPE = "DOC,DOCX,,XLS,XLSX,PPT,PPTX,TXT,CVS";
	private static final String VIDEO_TYPE = "MP4";
	/**
	 * 保存上传的文件.
	 *
	 * @param stream
	 *            上传文件的输入流.
	 * @param fileFullPath
	 *            上传文件路径.
	 * @return 返回保存的文件.
	 * @throws IOException
	 *             抛出文件读写异常.
	 */
	public static File saveFileFromInputStream(final InputStream stream,
			final String fileFullPath) throws IOException {
		File file = new File(fileFullPath);
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(file);
			byte[] buffer = new byte[FILE_OPERATE_BUFFER_SIZE];
			int byteread = 0;
			while ((byteread = stream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
		} finally {
			if (fs != null) {
				fs.close();
			}
			if (stream != null) {
				stream.close();
			}
		}
		return file;
	}
	
	public static boolean isImage(String fileType){
		if(IMAGE_TYPE.contains(fileType.toUpperCase())){
			return true;
		}
		return false;
	}
	public static boolean isOffice(String fileType){
		if(OFFICE_TYPE.contains(fileType.toUpperCase())){
			return true;
		}
		return false;
	}
	public static boolean isVideo(String fileType){
		if(VIDEO_TYPE.contains(fileType.toUpperCase())){
			return true;
		}
		return false;
	}
	public static boolean checkImageSize(MultipartFile imgFile){
		if(imgFile != null && imgFile.getSize() > 50000){
			return true;
		}
		return false;
	}
	
}
