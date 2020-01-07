package br.com.nelioalves.cursomc.curso_mc.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	@Value("${s3.bucket}")
	private String bucketName;

	@Autowired
	private AmazonS3 s3Client;

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	public void upload(String localFilePath) {
		try {
			File file = new File(localFilePath);
			
			LOG.info("Iniciando o upload...");
			
			s3Client.putObject(new PutObjectRequest(bucketName, "teste.jpg", file));
			
			LOG.info("Upload finalizado...");
			
		} catch (AmazonServiceException e) {
			LOG.info("AmazonServiceException: " + e.getMessage());
			LOG.info("Status code:" +  e.getErrorCode()) ;
		}catch(AmazonClientException e) {
			LOG.info("AmazonClientException: " + e.getMessage());
		}

	}
}
