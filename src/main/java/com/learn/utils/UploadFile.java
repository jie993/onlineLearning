package com.learn.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
	String imagePath = System.getProperty("catalina.base") + File.separatorChar + "webapps" + File.separatorChar
			+ "learning" + File.separatorChar + "uploadFile" + File.separatorChar + "image"+ File.separatorChar;
	String avatarPath = System.getProperty("catalina.base") + File.separatorChar + "webapps" + File.separatorChar
			+ "learning" + File.separatorChar + "uploadFile" + File.separatorChar + "avatar"+ File.separatorChar;
	String videoPath = System.getProperty("catalina.base") + File.separatorChar + "webapps" + File.separatorChar
			+ "learning" + File.separatorChar + "uploadFile" + File.separatorChar + "video"+ File.separatorChar;
	String filePath = System.getProperty("catalina.base") + File.separatorChar + "webapps" + File.separatorChar
			+ "learning" + File.separatorChar + "uploadFile" + File.separatorChar + "file"+ File.separatorChar;
	String fileSrc = "uploadFile" + File.separatorChar + "file" + File.separatorChar;
	String videoSrc = "uploadFile" + File.separatorChar + "video" + File.separatorChar;
	String avatarSrc = "uploadFile" + File.separatorChar + "avatar" + File.separatorChar;
	String imageSrc = "uploadFile" + File.separatorChar + "image" + File.separatorChar;

	public String uploadFile(MultipartFile upload) {
		String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 15);
		String trueName = upload.getOriginalFilename();
		String fileType = trueName.substring(trueName.lastIndexOf("."));
		// �ļ���������ž���·��
		String newfilepath = filePath+uuid+fileType;
		
		// ���ظ�ǰ�˺����ݿ��·��
		fileSrc = fileSrc + uuid + fileType;
		File file = new File(fileSrc);
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			upload.transferTo(new File(newfilepath)); // ת���ļ�
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return fileSrc.toString().replaceAll("\\\\", "/");
	}

	public String uploadImage(MultipartFile upload) {
		String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 15);
		String trueName = upload.getOriginalFilename();
		String fileType = trueName.substring(trueName.lastIndexOf("."));
		
		String newfilepath = imagePath+uuid+fileType;
		
		// ���ظ�ǰ�˺����ݿ��·��
		imageSrc = imageSrc + uuid + fileType;
		File file = new File(imagePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			upload.transferTo(new File(newfilepath)); // ת���ļ�
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return imageSrc.toString().replaceAll("\\\\", "/");
	}

	public String uploadAvatar(MultipartFile upload) {
		String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 15);
		String trueName = upload.getOriginalFilename();
		String fileType = trueName.substring(trueName.lastIndexOf("."));
		// �ļ���������ž���·��
		avatarPath = avatarPath + uuid + fileType;

		// ���ظ�ǰ�˺����ݿ��·��
		avatarSrc = avatarSrc + uuid + fileType;

		File file = new File(avatarPath);

		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			FileCopyUtils.copy(upload.getBytes(), file); // ���������ļ�д���½����ļ�

		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return avatarSrc;
	}

	public String uploadVedio(MultipartFile upload) {
		String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 15);
		String trueName = upload.getOriginalFilename();
		String fileType = trueName.substring(trueName.lastIndexOf("."));
		
		String newfilepath = videoPath+uuid+fileType;
		
		// ���ظ�ǰ�˺����ݿ��·��
		videoSrc = videoSrc + uuid + fileType;
		File file = new File(videoPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			upload.transferTo(new File(newfilepath)); // ת���ļ�
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return videoSrc.toString().replaceAll("\\\\", "/");
	}
}
