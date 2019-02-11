package jp.co.world.storedevelopment.model.mapper.repository.sql;

import com.tierline.mybatis.activemodel.SQL;

import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelatedFindFormDTO;

public class NegotiationRelatedFindSQLBuilder {
	public String build(final NegotiationRelatedFindFormDTO dto) {
		String sql = new SQL() {
			{
				SELECT_DISTINCT("N.*");
				FROM("NEGOTIATION N ");
				WHERE("N.is_deleted = false");
				WHERE("(N.id IN("
						+ "SELECT NIA.negotiation_id FROM NEGOTIATION_INTERVIEW_ACCOUNT NIA WHERE NIA.account_id IN ("
						+ "SELECT account_id FROM NEGOTIATION_INTERVIEW_ACCOUNT IA WHERE IA.negotiation_id = #{id}))"
						+ "OR N.id IN("
						+ "SELECT NIC.negotiation_id FROM NEGOTIATION_INTERVIEW_CORPORATION NIC WHERE NIC.corporation_id IN ("
						+ "SELECT corporation_id FROM NEGOTIATION_INTERVIEW_CORPORATION IC WHERE IC.negotiation_id = #{id}))"
						+ "OR N.id IN("
						+ "SELECT NIB.negotiation_id FROM NEGOTIATION_INTERVIEW_BUILDING NIB WHERE NIB.building_id IN ("
						+ "SELECT building_id FROM NEGOTIATION_INTERVIEW_BUILDING IB WHERE IB.negotiation_id = #{id})))");
				WHERE("0 < (SELECT COUNT(*) FROM NEGOTIATION_INTERVIEW_BUILDING NIB WHERE N.id = NIB.negotiation_id and NIB.building_id IS NOT NULL)");

				if (dto.getBuildingIds().size() > 0) {
					String buildingQuery = "N.id IN(SELECT negotiation_id FROM NEGOTIATION_INTERVIEW_BUILDING IB"
							+ " WHERE IB.building_id IN %s)";
					WHERE(format(buildingQuery, IN(dto.getBuildingIds())));
				}

				ORDER_BY("N.update_datetime desc");
			}

		}.toString();
		return sql + PagingSQLBuilder.build(dto);

	}
}
