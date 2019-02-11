package jp.co.world.storedevelopment.model.value;

import java.util.Arrays;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public enum FileExtention {
	JPEG, JPG, PNG, PDF, GIF, XLSX, PPTX, DOCS, DOCX, DOC, XLS, PPT, MP4, MOV;

	public static String getExtentionType(String fileName) {
		String ext = StringUtils.lowerCase(FilenameUtils.getExtension(fileName));
		for (FileExtention t : values()) {
			if (StringUtils.equals(t.name().toLowerCase(), ext)) {
				return t.name();
			}
		}
		return null;
	}

	public static Boolean isDocument(String fileName) {
		return Arrays.asList(DOC.toString(), DOCX.toString(), XLS.toString(), XLSX.toString(), PDF.toString(), PPT.toString(), PPTX.toString())
				.contains(getExtentionType(fileName));
	}

	public static Boolean isImage(String fileName) {
		return Arrays.asList(JPEG.toString(), JPG.toString(), PNG.toString(), GIF.toString()).contains(getExtentionType(fileName));
	}
	
	public static Boolean isVideo(String fileName) {
		return Arrays.asList(MP4.toString(), MOV.toString()).contains(getExtentionType(fileName));
	}

}
