package jp.co.world.storedevelopment.model.value;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public enum FileDivision {
	MANUAL, FI, EXT, WLC, FCR, OT, CS, SF, AI, INL, ADB, HRK, INT, PLT, WSP, CASEWAY, ASPUL, OTHER;

	private final static EnumMap<FileDivision, String> divisions;

	static {
		divisions = new EnumMap<FileDivision, String>(FileDivision.class);
		divisions.put(FileDivision.MANUAL, "0.マニュアル");
		divisions.put(FileDivision.FI, "1.FI(百貨店）");
		divisions.put(FileDivision.EXT, "2.EXT(Mens)");
		divisions.put(FileDivision.WLC, "3.WLC(雑貨HDその他）");
		divisions.put(FileDivision.FCR, "4.FCR(DEMO)");
		divisions.put(FileDivision.OT, "5.OT(WLS)");
		divisions.put(FileDivision.CS, "6.CS(ｺｺｼｭﾆｯｸ)");
		divisions.put(FileDivision.SF, "7.SF");
		divisions.put(FileDivision.AI, "8.AI");
		divisions.put(FileDivision.INL, "9.INL(SC)");
		divisions.put(FileDivision.ADB, "10.ADB(ｱﾀﾞﾊﾞｯﾄ)");
		divisions.put(FileDivision.HRK, "11.HRK(ﾋﾛｺ)");
		divisions.put(FileDivision.INT, "12.INT(ｾﾚｸﾄ)");
		divisions.put(FileDivision.PLT, "13.PLT(ﾋﾟﾝｸﾗﾃ)");
		divisions.put(FileDivision.WSP, "14.WSP(ND)");
		divisions.put(FileDivision.CASEWAY, "15.ｹｰｽﾞｳｪｨ");
		divisions.put(FileDivision.ASPUL, "16.ｱｽﾌﾟﾙﾝﾄﾞ");
		divisions.put(FileDivision.OTHER, "99.その他");
	}

	public static String toString(FileDivision division) {
		return divisions.get(division);
	}

	public static String toLavel(String division) {
		for (FileDivision f : FileDivision.values()) {
			if (f.toString().equals(division)) {
				return toString(f);
			}
		}
		throw new IllegalArgumentException("存在しない区分です:" + division);
	}

	public static List<String> all() {
		List<String> list = new ArrayList<>();
		for (FileDivision f : FileDivision.values()) {
			list.add(FileDivision.toString(f));
		}
		return list;
	}

}