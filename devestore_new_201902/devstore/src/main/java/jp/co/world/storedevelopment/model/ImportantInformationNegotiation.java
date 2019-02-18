package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ImportantInformationNegotiationModelMapper;

public class ImportantInformationNegotiation extends ActiveModel<ImportantInformationNegotiation> {
    private Long importantInformationId;
    private Long negotiationId;

    public ImportantInformationNegotiation() {
    }

    public ImportantInformationNegotiation(ImportantInformation in, Negotiation n) {
        setImportantInformationId(in.getId());
        setNegotiationId(n.getId());
    }

    @Override
    protected ModelMapper<ImportantInformationNegotiation> modelMapper(SqlSession session) {
        return session.getMapper(ImportantInformationNegotiationModelMapper.class);
    }

    public Long getImportantInformationId() {
        return importantInformationId;
    }

    public void setImportantInformationId(Long importantInformationId) {
        this.importantInformationId = importantInformationId;
    }

    public Long getNegotiationId() {
        return negotiationId;
    }

    public void setNegotiationId(Long negotiationId) {
        this.negotiationId = negotiationId;
    }

}
