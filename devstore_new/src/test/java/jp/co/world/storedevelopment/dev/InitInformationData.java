package jp.co.world.storedevelopment.dev;

import static jp.co.world.storedevelopment.dev.GlobalVariable.importantNoticeSize;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.IntStream;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.ImportantInformationAccount;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationAccountRepository;
import jp.co.world.storedevelopment.model.service.NegotiationService;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationCreateFormDTO;

public class InitInformationData extends InitTestDataSupport {

	public Account MAIN_ACCOUNT = new AccountRepository().getHead().get();

	@Override
	public void init(InitTestData main) {
		IntStream.range(0, importantNoticeSize).forEach(i -> createImportantInformation(i));
		IntStream.range(0, importantNoticeSize).forEach(i -> createOtherInformation(i));
		IntStream.range(0, importantNoticeSize).forEach(i -> createNegotiationInformation(i));

	}

	private void createNegotiationInformation(int i) {
		Negotiation n = new Negotiation("おしらせ商談" + i);
		ImportantInformation in = new ImportantInformation();

		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = LocalDateTime.now();
		start.minus(Period.ofDays(3));
		end.plus(Period.ofDays(3));
		n.setScheduleStartDatetime(LocalDateTime.now());
		n.setScheduleEndDatetime(LocalDateTime.now());
		n.setImplementationStartDatetime(LocalDateTime.now());
		n.setImplementationEndDatetime(LocalDateTime.now());
		n.setContent("おしらせ商談の内容");
		NegotiationCreateFormDTO dto = new NegotiationCreateFormDTO();

		new NegotiationService().createAll(n, dto, MAIN_ACCOUNT);

		in.setDivision("NEGOTIATION");
		in.setCreateAccount(MAIN_ACCOUNT);
		in.setShowStartDatetime(start);
		in.setShowEndDatetime(end);
		in.setContent("○○館がリニューアルします2019年3月");
		in.create();

		List<Account> accounts = new AccountRepository().findAll();
		accounts.stream().forEach(a -> {
			in.addAccessRecord(a);
		});

		ImportantInformationAccount ina = new ImportantInformationAccountRepository().getHead().get();
		ina.setNice(true);
		ina.update();
	}

	private void createOtherInformation(int i) {
		Account a = MAIN_ACCOUNT;
		ImportantInformation in = new ImportantInformation();

		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = LocalDateTime.now();
		start.minus(Period.ofDays(3));
		end.plus(Period.ofDays(3));

		in.setDivision("OTHER");
		in.setCreateAccount(a);
		in.setShowStartDatetime(start);
		in.setShowEndDatetime(end);
		in.setContent("緊急に○○○ディベロッパーの調査をお願いします。");

		in.create();

		List<Account> accounts = new AccountRepository().findAll();
		accounts.stream().forEach(account -> {
			in.addAccessRecord(account);
		});
	}

	private void createImportantInformation(int i) {
		ImportantInformation in = new ImportantInformation();
		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = LocalDateTime.now();

		start.minus(Period.ofDays(3));
		end.plus(Period.ofDays(3));
		in.setCreateAccount(MAIN_ACCOUNT);
		in.setShowStartDatetime(start);
		in.setShowEndDatetime(end);
		in.setContent("内容です");
		in.setDivision("OTHER");
		in.create();

		List<Account> accounts = new AccountRepository().findAll();
		accounts.stream().forEach(a -> {
			in.addAccessRecord(a);
		});
	}
}
