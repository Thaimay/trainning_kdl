package jp.co.world.storedevelopment.pc.controller.form;

import java.util.Arrays;
import java.util.List;

public class SalesDeleteForm {
	private List<Long> ids = Arrays.asList();

	public SalesDeleteForm() {
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
}
