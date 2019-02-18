package jp.co.world.storedevelopment.utils;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImageResizeUtils {
	public static byte[] resize(MultipartFile file, final int height, final int width, final float quality)
			throws IOException {
		try (ByteArrayInputStream is = new ByteArrayInputStream(file.getBytes());
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageOutputStream ios = ImageIO.createImageOutputStream(os)) {

			BufferedImage srcImage = ImageIO.read(is);
			BufferedImage destImage = resizeImage(srcImage, height, width);

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			ImageWriter writer = ImageIO.getImageWritersByFormatName(extension).next();
			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(quality);
			writer.setOutput(ios);
			writer.write(null, new IIOImage(destImage, null, null), param);
			writer.dispose();

			return os.toByteArray();
		}
	}

	public static BufferedImage resizeImage(final BufferedImage image, final int height, final int width)
			throws IOException {
		double widthScale = (double) width / image.getWidth();
		double heightScale = (double) height / image.getHeight();
		BufferedImage resizedImage = new BufferedImage(width, height, image.getType());

		AffineTransform transform = AffineTransform.getScaleInstance(widthScale, heightScale);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
		op.filter(image, resizedImage);

		return resizedImage;
	}

}
