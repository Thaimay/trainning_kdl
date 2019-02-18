package jp.co.world.storedevelopment.sp.controller;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.world.storedevelopment.model.AccountData;
import jp.co.world.storedevelopment.model.mapper.repository.AccountDataRepository;

@RestController
@RequestMapping("/sp/account")
public class AccountRestController extends AppController {

	@RequestMapping("/find/data_user")
	@ResponseBody
	public String findDataUser() {
		try {
			logStartMethod("findDataUser");

			String dataUser = StringUtils.EMPTY;
			Optional<AccountData> data = new AccountDataRepository().findByAccountId(getAccount().getId());
			if (data.isPresent()) {
				dataUser = data.get().getDataUser();
			}

			logEndMethod("findDataUser");
			return dataUser;
		} catch (Exception ex) {
			logException("findDataUser", ex.getMessage());
			return null;
		}
	}
}
