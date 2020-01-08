package br.com.nelioalves.cursomc.curso_mc.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.nelioalves.cursomc.curso_mc.services.exception.FileException;

@Service
public class ImageService {

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		try {
			String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());// extraindo a extensao do a
																						// MultipartFile
			if (!"png".equals(ext) && !"jpg".equals(ext)) {
				throw new FileException("Somente imagens PNG e JPG são permitidas");
			}
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equals(ext)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {

			throw new FileException("Erro ao ler arquivo");
		}
	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}

	public InputStream getInputStream(BufferedImage img, String extension) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());

		} catch (IOException e) {
			throw new FileException("Erro ao ler aquivo");
		}
	}
	/*recorta a imgem para um formato quadrado, para se ajustar a uma imagen de prefil*/
	public BufferedImage cropSquare(BufferedImage sourceImg) {
		int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
		return Scalr.crop(sourceImg, (sourceImg.getWidth() / 2) - (min / 2), (sourceImg.getHeight() / 2) - (min / 2),
				min, min);
	}
	
	public BufferedImage resize(BufferedImage sourceImage, int size) {
		return Scalr.resize(sourceImage, Scalr.Method.ULTRA_QUALITY, size);
	}
	
}
