package jp.co.world.storedevelopment.batch.masterimport;

import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;

public class ProjectShopCopyBatch {
	public static void main(String[] args) {
		new ProjectRepository().findShopCopyTarget().forEach(p -> {
			p.iShop().ifPresent(s -> {
				p.copyShop(s);
			});
		});
	}
	
	
}
