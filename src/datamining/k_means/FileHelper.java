package datamining.k_means;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileHelper {
	
	public String userDataPath; 
	
	public FileHelper() {
	}
	
	public FileHelper(String username) {
		userDataPath = "C:\\Users\\Administrator\\Desktop\\lcontact" + username + ".lc";
	}
	
	public boolean write(String data) {
		return write(data, userDataPath);
	}
	

	public static boolean write(String data, String filePath) {
		return write(data, filePath, false);
	}

	private static boolean write(String data, String filePath, boolean append) {
		// DirInitial();
		if (data == null) {
			return false;
		}
		File file = new File(filePath);
		
		Writer writer = null;
		try {
			file.createNewFile();

			// 这样写的话可以指定文本的编码格式 ***** 默认为utf-8
			writer = new OutputStreamWriter(new FileOutputStream(file, append));

			BufferedWriter bufferedWriter = new BufferedWriter(writer);

			bufferedWriter.write(data);
			bufferedWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return true;
	}

	public static boolean append(String data, String filePath){
		return write(data, filePath, true);
	}

	public String load() {
		return load(userDataPath);
	}

	public static String load(String filePath) {

		if (filePath == null) {
			return null;
		}
		File file = new File(filePath);

		if (!file.exists()) {
			return null;
		}
		StringBuilder result = new StringBuilder();

		BufferedReader in = null;
		try {
			// 当该文件不存在时再创建一个新的文件
			file.createNewFile();

			FileInputStream fis = new FileInputStream(file);

			// 这样写的话可以指定文本的编码格式 *****
			InputStreamReader isr = new InputStreamReader(fis);

			in = new BufferedReader(isr);

			// 这里是按照字符流进行的读取

			String str = null;
			while ((str = in.readLine()) != null) {
				result.append(str);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result.toString();

	}
	
	public static String readSpecificEncodingFile(String filePath, String encoding) {

		if (filePath == null) {
			return null;
		}
		File file = new File(filePath);

		if (!file.exists()) {
			return null;
		}
		StringBuilder result = new StringBuilder();

		BufferedReader in = null;
		try {
			// 当该文件不存在时再创建一个新的文件
			file.createNewFile();

			FileInputStream fis = new FileInputStream(file);

			// 这样写的话可以指定文本的编码格式 *****
			InputStreamReader isr = new InputStreamReader(fis, encoding);

			in = new BufferedReader(isr);

			// 这里是按照字符流进行的读取

			String str = null;
			while ((str = in.readLine()) != null) {
				result.append(str).append("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result.toString();

	}

	public static boolean delete(String filePath) {
		return new File(filePath).delete();
		// Java无法直接删除一个非空目录
	}

}
