package jp.co.world.storedevelopment.pc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pc")
public class LoginController {
	private static final String BASE_DIR = "pc/login";

	@RequestMapping(value = { "/login" })
	public String list(Model model) {
		return BASE_DIR + "/login";
	}

	@RequestMapping(value = { "/login/error" })
	public String error(Model model) {
		model.addAttribute("error", true);
		return BASE_DIR + "/login_error";
	}

	@RequestMapping(value = { "/login/roleError" })
	public String roleError(Model model) {
		model.addAttribute("roleError", true);
		return BASE_DIR + "/login_error";
	}
}
