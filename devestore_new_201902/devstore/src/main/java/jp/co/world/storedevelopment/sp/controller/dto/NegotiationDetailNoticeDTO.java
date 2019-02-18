package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationNoticeAccount;

public class NegotiationDetailNoticeDTO {

	private int openRates = 0;

	private List<NegotiationDetailNoticeAccount> accounts = new ArrayList<NegotiationDetailNoticeAccount>();

	public NegotiationDetailNoticeDTO() {
		//
	}

	public NegotiationDetailNoticeDTO(Negotiation n) {
		// setOpenRates(n.getOpenRates());
		setAccoutns(n);
	}

	private void setAccoutns(Negotiation n) {
		List<NegotiationNoticeAccount> list = n.getNoticeAccounts();
		setAccounts(list.stream().map(na -> new NegotiationDetailNoticeAccount(na, n.getIsOpend(na.getAccount()),
				na.getAccount().hasRole())).collect(Collectors.toList()));

		int number = list.size();
		int opendNumber = 0;
		// list.stream().filter(a ->
		// a.getAccessRecord().getIsOpened()).collect(Collectors.toList())
		// .size();
		setOpenRates((int) Math.ceil(opendNumber / (double) number) * 100);
	}

	public int getOpenRates() {
		return openRates;
	}

	public void setOpenRates(int openRates) {
		this.openRates = openRates;
	}

	public List<NegotiationDetailNoticeAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<NegotiationDetailNoticeAccount> accounts) {
		this.accounts = accounts;
	}

	public List<NegotiationDetailNoticeAccount> getToAccounts() {
		if (accounts.size() > 0) {
			return accounts.stream().filter(x -> x.getSendType().equals("TO")).collect(Collectors.toList());
		} else {
			return new ArrayList<>();
		}
	}

	public List<NegotiationDetailNoticeAccount> getCcAccounts() {
		if (accounts.size() > 0) {
			return accounts.stream().filter(x -> x.getSendType().equals("CC")).collect(Collectors.toList());
		} else {
			return new ArrayList<>();
		}
	}
}
