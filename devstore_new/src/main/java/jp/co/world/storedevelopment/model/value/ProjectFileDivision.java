package jp.co.world.storedevelopment.model.value;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public enum ProjectFileDivision {
	EXTERIOR_IMAGE_LIST, INTERIOR_IMAGE_LIST, OTHER_IMAGE_LIST, FLOOR_MAP_IMAGE_LIST, NEGOTIATION_RELATED_MATERIALS, PROJECT_DISCUSSION_RELATED_MATERIALS;

	private final static EnumMap<ProjectFileDivision, String> divisions;

	static {
		divisions = new EnumMap<ProjectFileDivision, String>(ProjectFileDivision.class);
		divisions.put(ProjectFileDivision.EXTERIOR_IMAGE_LIST, "外観 画像一覧");
		divisions.put(ProjectFileDivision.INTERIOR_IMAGE_LIST, "内観 画像一覧");
		divisions.put(ProjectFileDivision.OTHER_IMAGE_LIST, "その他 画像一覧");
		divisions.put(ProjectFileDivision.FLOOR_MAP_IMAGE_LIST, "フロアマップ 画像一覧");
		divisions.put(ProjectFileDivision.NEGOTIATION_RELATED_MATERIALS, "商談 関連資料");
		divisions.put(ProjectFileDivision.PROJECT_DISCUSSION_RELATED_MATERIALS, "案件検討会 関連資料");
	}

	public static String toString(ProjectFileDivision division) {
		return divisions.get(division);
	}

	public static String toLavel(String division) {
		for (ProjectFileDivision f : ProjectFileDivision.values()) {
			if (f.toString().equals(division)) {
				return toString(f);
			}
		}
		throw new IllegalArgumentException("存在しない区分です:" + division);
	}

	public static List<String> all() {
		List<String> list = new ArrayList<>();
		for (ProjectFileDivision f : ProjectFileDivision.values()) {
			list.add(ProjectFileDivision.toString(f));
		}
		return list;
	}

}