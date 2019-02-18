package jp.co.world.storedevelopment.sp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.world.storedevelopment.utils.PathUtils;

@RestController
@RequestMapping("/sp/pdf")
public class ImageStreamMockController {
	@RequestMapping(path = "/download", method = RequestMethod.GET)
	public ResponseEntity<Resource> download() throws IOException {
		Path path = Paths.get(PathUtils.packagePath("static").replace("/C:", "") + "document/sample.pdf");
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(resource.getFile().length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}
	
}
