package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.NegotiationVideoModelMapper;
import jp.co.world.storedevelopment.model.value.FileExtention;

public class NegotiationVideo extends File<NegotiationVideo> {
	private Long negotiationId;

	public NegotiationVideo() {
	}

	@Override
	public String filePrefix() {
		return "negotiation_";
	}

	public NegotiationVideo(MultipartFile file, Negotiation negotiation, Account account) {
		super(file, account);
		setNegotiationId(negotiation.getId());
	}

	@Override
	protected ModelMapper<NegotiationVideo> modelMapper(SqlSession session) {
		return session.getMapper(NegotiationVideoModelMapper.class);
	}

	@Override
	public String basePath() {
		return VIDEO_FILE_PATH;
	}

	@Override
	public String urlPath() {
		return "video/";
	}

	@Override
	public Boolean isImage() {
		return FileExtention.isImage(getName());
	}

	@Override
	public Boolean isDocument() {
		return FileExtention.isDocument(getName());
	}

	@Override
	public Boolean isVideo() {
		return FileExtention.isVideo(getName());
	}

	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

}
