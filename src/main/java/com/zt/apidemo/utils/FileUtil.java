package com.zt.apidemo.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.UUID;


/**
 * @author wolves
 * @version V1.0
 */
public class FileUtil {

	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		out.write(file);
		out.flush();
		out.close();
	}

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static String renameToUUID(String fileName) {
		return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	/**
	 * 获取文件名后缀（扩展名）
	* @author zting 
	* @date 2019年12月30日 下午2:31:18
	* @Description: TODO     
	* @return String   
	*
	 */
	public static String getSuffix(String fileName) {
		
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	
	  /**
   * 文件存储
   * 
   * @param content
   *            内容
   * @param saveFileName
   *            文件名
   */
  public static Boolean saveFile(String content, String fileName) {
      Boolean retBL = false;
      Writer writer = null;
      try {
          writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
          writer.write(content);
          retBL = true;
      } catch (IOException ex) {
          retBL = false;
          ex.printStackTrace();
      } finally {
          try {
              writer.flush();
              writer.close();
          } catch (IOException e) {
              retBL = false;
              e.printStackTrace();
          }

      }
      return retBL;

  }
}
