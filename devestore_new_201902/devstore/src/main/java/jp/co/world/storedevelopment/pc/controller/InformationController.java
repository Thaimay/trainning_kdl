package jp.co.world.storedevelopment.pc.controller;


import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.world.storedevelopment.common.controller.ResourceController;
import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.service.ImportantInformationService;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationCreateFormDTO;

@Controller
@RequestMapping("/pc/information")
public class InformationController extends ResourceController {
    private static final String BASE_DIR = "pc/information/";
    
    @RequestMapping(value = "/create")
    public String create(Model model) {
    	return BASE_DIR + "create";
    }


    @RequestMapping(value = "/doCreate")
    @ResponseBody
	public void doCreate(Model model, @Valid ImportantInformationCreateFormDTO dto) {
        try {
            logStartMethod("create");
            ImportantInformation in = dto.toModel();
            new ImportantInformationService().createAll(in, dto, getAccount());
            logEndMethod("create");
        } catch (Exception ex) {
            logException("create", ex.getMessage());
        }
    }
}
