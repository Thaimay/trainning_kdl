package jp.co.world.storedevelopment.batch.todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.batch.constant.TodoContent;
import jp.co.world.storedevelopment.batch.constant.TodoList;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationInterviewAccount;
import jp.co.world.storedevelopment.model.Todo;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TodoRepository;

public class NegotiationReport {

	public static void main(String[] args) {
		try {

			new TodoRepository().deleteByDetailsDivisionId(TodoList.NEGOTIATION_REPORT.getTodoDetailsDivisionId());

			// 現在日時
			LocalDateTime currentDateTime = LocalDateTime.now();

			// 現在日時から商談リストを取得
			List<Negotiation> negotiationList = new NegotiationRepository()
					.findByScheduleStartDatetime(currentDateTime);

			for (Negotiation negotiation : negotiationList) {
				// 登録者CDからアカウントマスタのidを取得
				Account account = new AccountRepository().findByCode(negotiation.getCreatedAccountCode())
						.orElseThrow(() -> new IllegalStateException("存在しないアカウントコードです:"));
				// 商談IDから商談_面談先_訪問者を取得
				List<NegotiationInterviewAccount> negotiationInterviewAccountList = new NegotiationInterviewAccountRepository()
						.findByNegotiationId(negotiation.getId());

				// TODO に登録するアカウントIDをセット
				List<Long> accountIdList = selectAccountId(account.getId(), negotiationInterviewAccountList);

				for (Long accountId : accountIdList) {
					createTodo(negotiation, accountId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(String.format("TODO(NegotiationReport)登録バッチ実行中にエラーが発生しました"));
		}
	}

	private static void createTodo(Negotiation negotiation, Long accountId) {
		Todo todo = new Todo();
		todo.setAccountId(accountId);
		todo.setNegotiationId(negotiation.getId());
		todo.setDetailsDivisionId(TodoList.NEGOTIATION_REPORT.getTodoDetailsDivisionId());
		todo.setContent(String.join("\n",
				Arrays.asList(negotiation.getDivisionValue() + TodoContent.NEGOTIATION_REPORT.toString(),
						negotiation.getTitle(), negotiation.getScheduleDateValue()).stream().filter(x -> !x.isEmpty())
						.collect(Collectors.toList())));
		todo.setContentSub(negotiation.getTitle());
		todo.setShowStartDatetime(LocalDateTime.now());
		todo.setShowEndDatetime(LocalDateTime.of(2099, 12, 31, 23, 59, 59, 999999999));
		todo.create();
	}

	private static List<Long> selectAccountId(Long accountId,
			List<NegotiationInterviewAccount> negotiationInterviewAccountList) {
		List<Long> accountIdList = new ArrayList<Long>();
		accountIdList.add(accountId);
		for (NegotiationInterviewAccount negotiationInterviewAccount : negotiationInterviewAccountList) {
			accountIdList.add(negotiationInterviewAccount.getAccountId());
		}

		// 重複しているaccountIdを削除
		List<Long> accountIdList2 = new ArrayList<Long>(new HashSet<>(accountIdList));

		return accountIdList2;
	}
}
