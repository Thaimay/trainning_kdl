package jp.co.world.storedevelopment.common.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.sp.controller.AppController;
import jp.co.world.storedevelopment.utils.PathUtils;

public abstract class ResourceController extends AppController {

	@ResponseBody
	@RequestMapping(value = "/image/{fileName:.+}", method = RequestMethod.GET)
	public byte[] image(@PathVariable String fileName, HttpServletResponse response) {
		try {
			logStartMethod("image");
			response.setContentType("image/gif");
			response.setContentType("image/png");
			response.setContentType("image/jpeg");
			byte[] resource = resource("/image/" + fileName);
			logEndMethod("image");
			return resource;
		} catch (Exception ex) {
			logException("image", ex.getMessage());
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/document/{fileName:.+}", headers = "Accept=*/*", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] document(@PathVariable String fileName, HttpServletResponse response) {
		try {
			logStartMethod("document");
			response.setHeader("Content-Disposition", "attachment");
			byte[] resource = resource("/document/" + fileName);
			logEndMethod("document");
			return resource;
		} catch (Exception ex) {
			logException("document", ex.getMessage());
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/video/{fileName:.+}", method = RequestMethod.GET)
	public byte[] video(@PathVariable String fileName, HttpServletResponse response) {
		try {
			logStartMethod("video");
			response.setContentType("video/mp4");
			response.setContentType("video/quicktime");
			byte[] resource = resource("/video/" + fileName);
			logEndMethod("video");
			return resource;
		} catch (Exception ex) {
			logException("video", ex.getMessage());
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/sales/{fileName:.+}", headers = "Accept=*/*", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] sales(@PathVariable String fileName, HttpServletResponse response) {
		try {
			logStartMethod("sales");
			response.setHeader("Content-Disposition", "attachment");
			byte[] resource = resource("/sales/" + fileName);
			logEndMethod("sales");
			return resource;
		} catch (Exception ex) {
			logException("sales", ex.getMessage());
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/img/{fileName:.+}", method = RequestMethod.GET)
	public byte[] noImage(@PathVariable String fileName, HttpServletResponse response) {
		try {
			logStartMethod("noImage");
			response.setContentType("image/gif");
			response.setContentType("image/png");
			response.setContentType("image/jpeg");
			byte[] resource = resource("/img/" + fileName);
			logEndMethod("noImage");
			return resource;
		} catch (Exception ex) {
			logException("noImage", ex.getMessage());
			return null;
		}
	}

	protected byte[] noImage() throws IOException {
		String path = PathUtils.packagePath("static") + "/img/no_img.jpg";
		System.out.println(path);
		return FileUtils.readFileToByteArray(new File(path));
	}

	protected byte[] resource(String path) throws IOException {
		List<String> list = Arrays.asList("/img/docx.jpg", "/img/xlsx.jpg", "/img/pptx.jpg", "/img/pdf.jpg");
		String fullPath = Application.resourcePath() + path;
		
		if (list.contains(path)) {
			fullPath = PathUtils.packagePath("static") + path;
		}

		try {
			byte[] result = FileUtils.readFileToByteArray(new File(fullPath));
			return result;
		} catch (IOException e) {
			return noImage();
		}
	}

}
