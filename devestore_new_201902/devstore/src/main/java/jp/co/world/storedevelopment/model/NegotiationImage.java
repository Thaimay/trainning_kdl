package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.NegotiationImageModelMapper;
import jp.co.world.storedevelopment.model.value.FileExtention;

public class NegotiationImage extends File<NegotiationImage> {
	private Long negotiationId;

	public NegotiationImage() {
	}

	@Override
	public String filePrefix() {
		return "negotiation_";
	}

	public NegotiationImage(MultipartFile file, Negotiation negotiation, Account account) {
		super(file, account);
		setNegotiationId(negotiation.getId());
	}

	@Override
	protected ModelMapper<NegotiationImage> modelMapper(SqlSession session) {
		return session.getMapper(NegotiationImageModelMapper.class);
	}

	@Override
	public String basePath() {
		return IMAGE_FILE_PATH;
	}

	@Override
	public String urlPath() {
		return "image/";
	}

	@Override
	public Boolean isImage() {
		return FileExtention.isImage(getName());
	}

	@Override
	public Boolean isDocument() {
		return FileExtention.isDocument(getName());
	}

	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

}
