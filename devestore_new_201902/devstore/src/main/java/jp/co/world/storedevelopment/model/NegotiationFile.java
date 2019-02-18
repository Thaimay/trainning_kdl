package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.NegotiationFileModelMapper;
import jp.co.world.storedevelopment.model.value.FileExtention;

public class NegotiationFile extends File<NegotiationFile> {

	public NegotiationFile() {
	}

	@Override
	public String filePrefix() {
		return "negotiation_";
	}

	public NegotiationFile(MultipartFile file, Negotiation negotiation, Account account) {
		super(file, account);
		setNegotiationId(negotiation.getId());
	}

	@Override
	protected ModelMapper<NegotiationFile> modelMapper(SqlSession session) {
		return session.getMapper(NegotiationFileModelMapper.class);
	}

	@Override
	public String basePath() {
		return DOCUMENT_FILE_PATH;
	}

	@Override
	public String urlPath() {
		return "document/";
	}

	@Override
	public Boolean isImage() {
		return FileExtention.isImage(getName());
	}

	@Override
	public Boolean isDocument() {
		return FileExtention.isDocument(getName());
	}

}
