package jp.co.world.storedevelopment.model.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileNotEmptyValidator implements ConstraintValidator<UploadFileNotEmpty, MultipartFile> {

	@Override
	public void initialize(UploadFileNotEmpty constraint) {
	}

	@Override
	public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
		if (multipartFile == null || !StringUtils.hasLength(multipartFile.getOriginalFilename())) {
			return false;
		}
		return !multipartFile.isEmpty();
	}

}