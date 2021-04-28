package com.proto.mm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class SaveImg {
	
	private String savedFileName;
	private String path;

	public String getSavedFileName() {
		return savedFileName;
	}

	public String getPath() {
		return path;
	}

	public int saveImgFromUrl(String imgUrl, String path, String fileName) throws IOException {
		URL url = null;
		InputStream in = null;
		OutputStream out = null;



		try {

			url = new URL(imgUrl);

			// 헤더에서 파일 확장자 가져오기(content-type)
			URLConnection urlConn = url.openConnection();
			String conType = urlConn.getContentType();
			String conT[] = conType.split("/");
			if (conT[1].equals("png")) {
				conT[1] = "png";
			}

			// 파일네임 정하기

			if (fileName.equals("")) {
				fileName = "images";
			}

			// 해당 디렉토리에 해당 파일명을 가진 파일이 존재하는 확인 후 파일네임에 넘버링
			File file = new File(path + "/" + fileName + "." + conT[1]);

			in = urlConn.getInputStream();

			if (file.exists()) {
				int i = 1;
				while (true) {
					File file2 = new File(path + "/" + fileName + "_" + i + "." + conT[1]);
					if (file2.exists()) {
						i++;
					} else {
						fileName = fileName + "_" + i;
						break;
					}
				}
			}

			// 여기부터 쓰기시작
			out = new FileOutputStream(path + "/" + fileName + "." + conT[1]);

			while (true) {
				int data = in.read();
				if (data == -1) {
					break;
				}
				out.write(data);
			}

			in.close();
			out.close();
			
			this.savedFileName = fileName + "." + conT[1];
			this.path = path;
			return 1;

		} catch (Exception e) {

			e.printStackTrace();
			return -1;

		} finally {

			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}

		}

	}
}