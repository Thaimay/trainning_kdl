package jp.co.world.storedevelopment.sp.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.world.storedevelopment.model.PushNotificationPermit;
import jp.co.world.storedevelopment.sp.controller.dto.PushNotificationPermitCreateDTO;

@RestController
@RequestMapping("/sp/push/notification")
public class PushNotificationController extends AppController {
	@RequestMapping(value = "/permit/create")
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody @Valid PushNotificationPermitCreateDTO dto) {
		try {
			logStartMethod("create", dto);
			String employeCode = getAccount().getEmployeeCd();
			new PushNotificationPermit(employeCode, dto.getDeviceToken(), dto.getDeviceType()).create();
			logEndMethod("create");
		} catch (Exception ex) {
			logException("create", ex.getMessage());
		}
	}

}
