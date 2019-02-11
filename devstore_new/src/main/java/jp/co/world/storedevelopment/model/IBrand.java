package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IBrandModelMapper;

/**
 * @author hungdh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IBrand extends IActiveModel<IBrand> {

	private String brandCd;
	private String brandName;
	private String incomeUnitCd;
	private String gpCd;
	private String brbu;
	private String daiBr;
	private String uriBu;
	private String kanriBu;
	private String baikanK;
	private String systemK;
	private String startDt;
	private String endDt;
	private String creDt;
	private String creTm;
	private String updDt;
	private String updTm;
	private String division1;
	private String division2;
	private String division3;
	private String division4;
	private String division5;
	private String division6;
	private String division7;
	private String division8;
	private String division9;
	private String division10;
	private String division11;
	private String division12;
	private String division13;
	private String division14;
	private String division15;
	private String division16;
	private String division17;
	private String division18;
	private String division19;
	private String division20;
	private String divisionUpdateDatetime1;
	private String divisionUpdateDatetime2;
	private String divisionUpdateDatetime3;
	private String divisionUpdateDatetime4;
	private String divisionUpdateDatetime5;
	private String divisionUpdateDatetime6;
	private String divisionUpdateDatetime7;
	private String divisionUpdateDatetime8;
	private String divisionUpdateDatetime9;
	private String divisionUpdateDatetime10;
	private String divisionUpdateDatetime11;
	private String divisionUpdateDatetime12;
	private String divisionUpdateDatetime13;
	private String divisionUpdateDatetime14;
	private String divisionUpdateDatetime15;
	private String divisionUpdateDatetime16;
	private String divisionUpdateDatetime17;
	private String divisionUpdateDatetime18;
	private String divisionUpdateDatetime19;
	private String divisionUpdateDatetime20;
	private String division21;
	private String division22;
	private String division23;
	private String division24;
	private String division25;
	private String division26;
	private String division27;
	private String division28;
	private String division29;
	private String division30;
	private String division31;
	private String division32;
	private String division33;
	private String division34;
	private String division35;
	private String division36;
	private String division37;
	private String division38;
	private String division39;
	private String division40;
	private String divisionUpdateDatetime21;
	private String divisionUpdateDatetime22;
	private String divisionUpdateDatetime23;
	private String divisionUpdateDatetime24;
	private String divisionUpdateDatetime25;
	private String divisionUpdateDatetime26;
	private String divisionUpdateDatetime27;
	private String divisionUpdateDatetime28;
	private String divisionUpdateDatetime29;
	private String divisionUpdateDatetime30;
	private String divisionUpdateDatetime31;
	private String divisionUpdateDatetime32;
	private String divisionUpdateDatetime33;
	private String divisionUpdateDatetime34;
	private String divisionUpdateDatetime35;
	private String divisionUpdateDatetime36;
	private String divisionUpdateDatetime37;
	private String divisionUpdateDatetime38;
	private String divisionUpdateDatetime39;
	private String divisionUpdateDatetime40;
	private String brandNameSub1;
	private String brandNameSub2;
	private String brandNameSub3;
	private String brandNameSub4;
	private String brandNameSub5;
	private String divName1;
	private String divName2;
	private String divName3;
	private String divName4;
	private String divName5;

	public IBrand() {
	}

	public String getBrandCd() {
		return brandCd;
	}

	public void setBrandCd(String brandCd) {
		this.brandCd = brandCd;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getIncomeUnitCd() {
		return incomeUnitCd;
	}

	public void setIncomeUnitCd(String incomeUnitCd) {
		this.incomeUnitCd = incomeUnitCd;
	}

	public String getGpCd() {
		return gpCd;
	}

	public void setGpCd(String gpCd) {
		this.gpCd = gpCd;
	}

	public String getBrbu() {
		return brbu;
	}

	public void setBrbu(String brbu) {
		this.brbu = brbu;
	}

	public String getDaiBr() {
		return daiBr;
	}

	public void setDaiBr(String daiBr) {
		this.daiBr = daiBr;
	}

	public String getUriBu() {
		return uriBu;
	}

	public void setUriBu(String uriBu) {
		this.uriBu = uriBu;
	}

	public String getKanriBu() {
		return kanriBu;
	}

	public void setKanriBu(String kanriBu) {
		this.kanriBu = kanriBu;
	}

	public String getBaikanK() {
		return baikanK;
	}

	public void setBaikanK(String baikanK) {
		this.baikanK = baikanK;
	}

	public String getSystemK() {
		return systemK;
	}

	public void setSystemK(String systemK) {
		this.systemK = systemK;
	}

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getCreDt() {
		return creDt;
	}

	public void setCreDt(String creDt) {
		this.creDt = creDt;
	}

	public String getCreTm() {
		return creTm;
	}

	public void setCreTm(String creTm) {
		this.creTm = creTm;
	}

	public String getUpdDt() {
		return updDt;
	}

	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}

	public String getUpdTm() {
		return updTm;
	}

	public void setUpdTm(String updTm) {
		this.updTm = updTm;
	}

	public String getDivision1() {
		return division1;
	}

	public void setDivision1(String division1) {
		this.division1 = division1;
	}

	public String getDivision2() {
		return division2;
	}

	public void setDivision2(String division2) {
		this.division2 = division2;
	}

	public String getDivision3() {
		return division3;
	}

	public void setDivision3(String division3) {
		this.division3 = division3;
	}

	public String getDivision4() {
		return division4;
	}

	public void setDivision4(String division4) {
		this.division4 = division4;
	}

	public String getDivision5() {
		return division5;
	}

	public void setDivision5(String division5) {
		this.division5 = division5;
	}

	public String getDivision6() {
		return division6;
	}

	public void setDivision6(String division6) {
		this.division6 = division6;
	}

	public String getDivision7() {
		return division7;
	}

	public void setDivision7(String division7) {
		this.division7 = division7;
	}

	public String getDivision8() {
		return division8;
	}

	public void setDivision8(String division8) {
		this.division8 = division8;
	}

	public String getDivision9() {
		return division9;
	}

	public void setDivision9(String division9) {
		this.division9 = division9;
	}

	public String getDivision10() {
		return division10;
	}

	public void setDivision10(String division10) {
		this.division10 = division10;
	}

	public String getDivision11() {
		return division11;
	}

	public void setDivision11(String division11) {
		this.division11 = division11;
	}

	public String getDivision12() {
		return division12;
	}

	public void setDivision12(String division12) {
		this.division12 = division12;
	}

	public String getDivision13() {
		return division13;
	}

	public void setDivision13(String division13) {
		this.division13 = division13;
	}

	public String getDivision14() {
		return division14;
	}

	public void setDivision14(String division14) {
		this.division14 = division14;
	}

	public String getDivision15() {
		return division15;
	}

	public void setDivision15(String division15) {
		this.division15 = division15;
	}

	public String getDivision16() {
		return division16;
	}

	public void setDivision16(String division16) {
		this.division16 = division16;
	}

	public String getDivision17() {
		return division17;
	}

	public void setDivision17(String division17) {
		this.division17 = division17;
	}

	public String getDivision18() {
		return division18;
	}

	public void setDivision18(String division18) {
		this.division18 = division18;
	}

	public String getDivision19() {
		return division19;
	}

	public void setDivision19(String division19) {
		this.division19 = division19;
	}

	public String getDivision20() {
		return division20;
	}

	public void setDivision20(String division20) {
		this.division20 = division20;
	}

	public String getDivisionUpdateDatetime1() {
		return divisionUpdateDatetime1;
	}

	public void setDivisionUpdateDatetime1(String divisionUpdateDatetime1) {
		this.divisionUpdateDatetime1 = divisionUpdateDatetime1;
	}

	public String getDivisionUpdateDatetime2() {
		return divisionUpdateDatetime2;
	}

	public void setDivisionUpdateDatetime2(String divisionUpdateDatetime2) {
		this.divisionUpdateDatetime2 = divisionUpdateDatetime2;
	}

	public String getDivisionUpdateDatetime3() {
		return divisionUpdateDatetime3;
	}

	public void setDivisionUpdateDatetime3(String divisionUpdateDatetime3) {
		this.divisionUpdateDatetime3 = divisionUpdateDatetime3;
	}

	public String getDivisionUpdateDatetime4() {
		return divisionUpdateDatetime4;
	}

	public void setDivisionUpdateDatetime4(String divisionUpdateDatetime4) {
		this.divisionUpdateDatetime4 = divisionUpdateDatetime4;
	}

	public String getDivisionUpdateDatetime5() {
		return divisionUpdateDatetime5;
	}

	public void setDivisionUpdateDatetime5(String divisionUpdateDatetime5) {
		this.divisionUpdateDatetime5 = divisionUpdateDatetime5;
	}

	public String getDivisionUpdateDatetime6() {
		return divisionUpdateDatetime6;
	}

	public void setDivisionUpdateDatetime6(String divisionUpdateDatetime6) {
		this.divisionUpdateDatetime6 = divisionUpdateDatetime6;
	}

	public String getDivisionUpdateDatetime7() {
		return divisionUpdateDatetime7;
	}

	public void setDivisionUpdateDatetime7(String divisionUpdateDatetime7) {
		this.divisionUpdateDatetime7 = divisionUpdateDatetime7;
	}

	public String getDivisionUpdateDatetime8() {
		return divisionUpdateDatetime8;
	}

	public void setDivisionUpdateDatetime8(String divisionUpdateDatetime8) {
		this.divisionUpdateDatetime8 = divisionUpdateDatetime8;
	}

	public String getDivisionUpdateDatetime9() {
		return divisionUpdateDatetime9;
	}

	public void setDivisionUpdateDatetime9(String divisionUpdateDatetime9) {
		this.divisionUpdateDatetime9 = divisionUpdateDatetime9;
	}

	public String getDivisionUpdateDatetime10() {
		return divisionUpdateDatetime10;
	}

	public void setDivisionUpdateDatetime10(String divisionUpdateDatetime10) {
		this.divisionUpdateDatetime10 = divisionUpdateDatetime10;
	}

	public String getDivisionUpdateDatetime11() {
		return divisionUpdateDatetime11;
	}

	public void setDivisionUpdateDatetime11(String divisionUpdateDatetime11) {
		this.divisionUpdateDatetime11 = divisionUpdateDatetime11;
	}

	public String getDivisionUpdateDatetime12() {
		return divisionUpdateDatetime12;
	}

	public void setDivisionUpdateDatetime12(String divisionUpdateDatetime12) {
		this.divisionUpdateDatetime12 = divisionUpdateDatetime12;
	}

	public String getDivisionUpdateDatetime13() {
		return divisionUpdateDatetime13;
	}

	public void setDivisionUpdateDatetime13(String divisionUpdateDatetime13) {
		this.divisionUpdateDatetime13 = divisionUpdateDatetime13;
	}

	public String getDivisionUpdateDatetime14() {
		return divisionUpdateDatetime14;
	}

	public void setDivisionUpdateDatetime14(String divisionUpdateDatetime14) {
		this.divisionUpdateDatetime14 = divisionUpdateDatetime14;
	}

	public String getDivisionUpdateDatetime15() {
		return divisionUpdateDatetime15;
	}

	public void setDivisionUpdateDatetime15(String divisionUpdateDatetime15) {
		this.divisionUpdateDatetime15 = divisionUpdateDatetime15;
	}

	public String getDivisionUpdateDatetime16() {
		return divisionUpdateDatetime16;
	}

	public void setDivisionUpdateDatetime16(String divisionUpdateDatetime16) {
		this.divisionUpdateDatetime16 = divisionUpdateDatetime16;
	}

	public String getDivisionUpdateDatetime17() {
		return divisionUpdateDatetime17;
	}

	public void setDivisionUpdateDatetime17(String divisionUpdateDatetime17) {
		this.divisionUpdateDatetime17 = divisionUpdateDatetime17;
	}

	public String getDivisionUpdateDatetime18() {
		return divisionUpdateDatetime18;
	}

	public void setDivisionUpdateDatetime18(String divisionUpdateDatetime18) {
		this.divisionUpdateDatetime18 = divisionUpdateDatetime18;
	}

	public String getDivisionUpdateDatetime19() {
		return divisionUpdateDatetime19;
	}

	public void setDivisionUpdateDatetime19(String divisionUpdateDatetime19) {
		this.divisionUpdateDatetime19 = divisionUpdateDatetime19;
	}

	public String getDivisionUpdateDatetime20() {
		return divisionUpdateDatetime20;
	}

	public void setDivisionUpdateDatetime20(String divisionUpdateDatetime20) {
		this.divisionUpdateDatetime20 = divisionUpdateDatetime20;
	}

	public String getDivision21() {
		return division21;
	}

	public void setDivision21(String division21) {
		this.division21 = division21;
	}

	public String getDivision22() {
		return division22;
	}

	public void setDivision22(String division22) {
		this.division22 = division22;
	}

	public String getDivision23() {
		return division23;
	}

	public void setDivision23(String division23) {
		this.division23 = division23;
	}

	public String getDivision24() {
		return division24;
	}

	public void setDivision24(String division24) {
		this.division24 = division24;
	}

	public String getDivision25() {
		return division25;
	}

	public void setDivision25(String division25) {
		this.division25 = division25;
	}

	public String getDivision26() {
		return division26;
	}

	public void setDivision26(String division26) {
		this.division26 = division26;
	}

	public String getDivision27() {
		return division27;
	}

	public void setDivision27(String division27) {
		this.division27 = division27;
	}

	public String getDivision28() {
		return division28;
	}

	public void setDivision28(String division28) {
		this.division28 = division28;
	}

	public String getDivision29() {
		return division29;
	}

	public void setDivision29(String division29) {
		this.division29 = division29;
	}

	public String getDivision30() {
		return division30;
	}

	public void setDivision30(String division30) {
		this.division30 = division30;
	}

	public String getDivision31() {
		return division31;
	}

	public void setDivision31(String division31) {
		this.division31 = division31;
	}

	public String getDivision32() {
		return division32;
	}

	public void setDivision32(String division32) {
		this.division32 = division32;
	}

	public String getDivision33() {
		return division33;
	}

	public void setDivision33(String division33) {
		this.division33 = division33;
	}

	public String getDivision34() {
		return division34;
	}

	public void setDivision34(String division34) {
		this.division34 = division34;
	}

	public String getDivision35() {
		return division35;
	}

	public void setDivision35(String division35) {
		this.division35 = division35;
	}

	public String getDivision36() {
		return division36;
	}

	public void setDivision36(String division36) {
		this.division36 = division36;
	}

	public String getDivision37() {
		return division37;
	}

	public void setDivision37(String division37) {
		this.division37 = division37;
	}

	public String getDivision38() {
		return division38;
	}

	public void setDivision38(String division38) {
		this.division38 = division38;
	}

	public String getDivision39() {
		return division39;
	}

	public void setDivision39(String division39) {
		this.division39 = division39;
	}

	public String getDivision40() {
		return division40;
	}

	public void setDivision40(String division40) {
		this.division40 = division40;
	}

	public String getDivisionUpdateDatetime21() {
		return divisionUpdateDatetime21;
	}

	public void setDivisionUpdateDatetime21(String divisionUpdateDatetime21) {
		this.divisionUpdateDatetime21 = divisionUpdateDatetime21;
	}

	public String getDivisionUpdateDatetime22() {
		return divisionUpdateDatetime22;
	}

	public void setDivisionUpdateDatetime22(String divisionUpdateDatetime22) {
		this.divisionUpdateDatetime22 = divisionUpdateDatetime22;
	}

	public String getDivisionUpdateDatetime23() {
		return divisionUpdateDatetime23;
	}

	public void setDivisionUpdateDatetime23(String divisionUpdateDatetime23) {
		this.divisionUpdateDatetime23 = divisionUpdateDatetime23;
	}

	public String getDivisionUpdateDatetime24() {
		return divisionUpdateDatetime24;
	}

	public void setDivisionUpdateDatetime24(String divisionUpdateDatetime24) {
		this.divisionUpdateDatetime24 = divisionUpdateDatetime24;
	}

	public String getDivisionUpdateDatetime25() {
		return divisionUpdateDatetime25;
	}

	public void setDivisionUpdateDatetime25(String divisionUpdateDatetime25) {
		this.divisionUpdateDatetime25 = divisionUpdateDatetime25;
	}

	public String getDivisionUpdateDatetime26() {
		return divisionUpdateDatetime26;
	}

	public void setDivisionUpdateDatetime26(String divisionUpdateDatetime26) {
		this.divisionUpdateDatetime26 = divisionUpdateDatetime26;
	}

	public String getDivisionUpdateDatetime27() {
		return divisionUpdateDatetime27;
	}

	public void setDivisionUpdateDatetime27(String divisionUpdateDatetime27) {
		this.divisionUpdateDatetime27 = divisionUpdateDatetime27;
	}

	public String getDivisionUpdateDatetime28() {
		return divisionUpdateDatetime28;
	}

	public void setDivisionUpdateDatetime28(String divisionUpdateDatetime28) {
		this.divisionUpdateDatetime28 = divisionUpdateDatetime28;
	}

	public String getDivisionUpdateDatetime29() {
		return divisionUpdateDatetime29;
	}

	public void setDivisionUpdateDatetime29(String divisionUpdateDatetime29) {
		this.divisionUpdateDatetime29 = divisionUpdateDatetime29;
	}

	public String getDivisionUpdateDatetime30() {
		return divisionUpdateDatetime30;
	}

	public void setDivisionUpdateDatetime30(String divisionUpdateDatetime30) {
		this.divisionUpdateDatetime30 = divisionUpdateDatetime30;
	}

	public String getDivisionUpdateDatetime31() {
		return divisionUpdateDatetime31;
	}

	public void setDivisionUpdateDatetime31(String divisionUpdateDatetime31) {
		this.divisionUpdateDatetime31 = divisionUpdateDatetime31;
	}

	public String getDivisionUpdateDatetime32() {
		return divisionUpdateDatetime32;
	}

	public void setDivisionUpdateDatetime32(String divisionUpdateDatetime32) {
		this.divisionUpdateDatetime32 = divisionUpdateDatetime32;
	}

	public String getDivisionUpdateDatetime33() {
		return divisionUpdateDatetime33;
	}

	public void setDivisionUpdateDatetime33(String divisionUpdateDatetime33) {
		this.divisionUpdateDatetime33 = divisionUpdateDatetime33;
	}

	public String getDivisionUpdateDatetime34() {
		return divisionUpdateDatetime34;
	}

	public void setDivisionUpdateDatetime34(String divisionUpdateDatetime34) {
		this.divisionUpdateDatetime34 = divisionUpdateDatetime34;
	}

	public String getDivisionUpdateDatetime35() {
		return divisionUpdateDatetime35;
	}

	public void setDivisionUpdateDatetime35(String divisionUpdateDatetime35) {
		this.divisionUpdateDatetime35 = divisionUpdateDatetime35;
	}

	public String getDivisionUpdateDatetime36() {
		return divisionUpdateDatetime36;
	}

	public void setDivisionUpdateDatetime36(String divisionUpdateDatetime36) {
		this.divisionUpdateDatetime36 = divisionUpdateDatetime36;
	}

	public String getDivisionUpdateDatetime37() {
		return divisionUpdateDatetime37;
	}

	public void setDivisionUpdateDatetime37(String divisionUpdateDatetime37) {
		this.divisionUpdateDatetime37 = divisionUpdateDatetime37;
	}

	public String getDivisionUpdateDatetime38() {
		return divisionUpdateDatetime38;
	}

	public void setDivisionUpdateDatetime38(String divisionUpdateDatetime38) {
		this.divisionUpdateDatetime38 = divisionUpdateDatetime38;
	}

	public String getDivisionUpdateDatetime39() {
		return divisionUpdateDatetime39;
	}

	public void setDivisionUpdateDatetime39(String divisionUpdateDatetime39) {
		this.divisionUpdateDatetime39 = divisionUpdateDatetime39;
	}

	public String getDivisionUpdateDatetime40() {
		return divisionUpdateDatetime40;
	}

	public void setDivisionUpdateDatetime40(String divisionUpdateDatetime40) {
		this.divisionUpdateDatetime40 = divisionUpdateDatetime40;
	}

	public String getBrandNameSub1() {
		return brandNameSub1;
	}

	public void setBrandNameSub1(String brandNameSub1) {
		this.brandNameSub1 = brandNameSub1;
	}

	public String getBrandNameSub2() {
		return brandNameSub2;
	}

	public void setBrandNameSub2(String brandNameSub2) {
		this.brandNameSub2 = brandNameSub2;
	}

	public String getBrandNameSub3() {
		return brandNameSub3;
	}

	public void setBrandNameSub3(String brandNameSub3) {
		this.brandNameSub3 = brandNameSub3;
	}

	public String getBrandNameSub4() {
		return brandNameSub4;
	}

	public void setBrandNameSub4(String brandNameSub4) {
		this.brandNameSub4 = brandNameSub4;
	}

	public String getBrandNameSub5() {
		return brandNameSub5;
	}

	public void setBrandNameSub5(String brandNameSub5) {
		this.brandNameSub5 = brandNameSub5;
	}

	public String getDivName1() {
		return divName1;
	}

	public void setDivName1(String divName1) {
		this.divName1 = divName1;
	}

	public String getDivName2() {
		return divName2;
	}

	public void setDivName2(String divName2) {
		this.divName2 = divName2;
	}

	public String getDivName3() {
		return divName3;
	}

	public void setDivName3(String divName3) {
		this.divName3 = divName3;
	}

	public String getDivName4() {
		return divName4;
	}

	public void setDivName4(String divName4) {
		this.divName4 = divName4;
	}

	public String getDivName5() {
		return divName5;
	}

	public void setDivName5(String divName5) {
		this.divName5 = divName5;
	}

	@Override
	protected ModelMapper<IBrand> modelMapper(SqlSession session) {
		return session.getMapper(IBrandModelMapper.class);
	}
}
