package br.com.nelioalves.cursomc.curso_mc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import br.com.nelioalves.cursomc.curso_mc.services.exception.FileException;

@Service
public class S3Service {

	@Value("${s3.bucket}")
	private String bucketName;

	@Autowired
	private AmazonS3 s3Client;

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	public URI uploadFile(MultipartFile multiparteFile) {
		try {
			String fileName = multiparteFile.getOriginalFilename();
			InputStream is = multiparteFile.getInputStream();
			String contentType = multiparteFile.getContentType();
			return uploadFile(is, fileName, contentType);

		} catch (IOException e) {

			throw new FileException("Erro de IO " + e.getMessage());
		}

	}

	public URI uploadFile(InputStream is, String fileName, String contetType) {
		try {
			LOG.info("Iniciando o upload...");
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contetType);
			s3Client.putObject(bucketName, fileName, is, meta);
			LOG.info("Upload finalizado...");

			return s3Client.getUrl(bucketName, fileName).toURI();

		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter a Url para Uri");
		}
	}

}
