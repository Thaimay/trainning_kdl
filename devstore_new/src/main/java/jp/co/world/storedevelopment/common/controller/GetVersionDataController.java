package jp.co.world.storedevelopment.common.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.world.storedevelopment.sp.controller.AppController;

@RestController
@RequestMapping("/get/")
public class GetVersionDataController extends AppController {

	@RequestMapping("/dataVersionCheck")
	@ResponseBody
	public List<String> findgetDataVersionCheckAccount() {
		ClassLoader classLoader = getClass().getClassLoader();

		String file = classLoader.getResource("version.json").toString().replaceFirst("file:/", "/").replaceFirst("/C:",
				"C:");

		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
