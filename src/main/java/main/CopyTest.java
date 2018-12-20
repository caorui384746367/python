package main;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CopyTest {
	
	public static void main(String[] args) {
		String oldPath = "C:\\Users\\Administrator\\Desktop\\test\\hibernate";
		File oldFile = new File(oldPath);
		String nowPath = "C:\\Users\\Administrator\\Desktop\\test\\rainey";
		File nowFile = new File(nowPath);
				try {
			copyAction(oldFile,nowFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	public static void copyAction(File oldFile,File nowFile) throws Exception {
		PrintWriter pw = null;
		BufferedReader br = null;
		if(!oldFile.isDirectory()) {
			FileInputStream fis = new FileInputStream(oldFile);
			FileOutputStream fos = new FileOutputStream(nowFile);
			pw = new PrintWriter(fos,true);
			br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
			String str = null;
			while((str=br.readLine())!=null) {
				pw.println(str);
			}
		
		}
		File [] files = oldFile.listFiles();
		for(File f:files) {
			if(f.isDirectory()) {
				String dirPath = nowFile.getPath()+ File.separator+f.getName();
				if(!new File(dirPath).exists()) {
					File dirFile = new File(dirPath);
					dirFile.mkdirs();
					copyAction(f,dirFile);
				}
				continue;
			}
			if(!f.isDirectory()) {
				FileInputStream fis = new FileInputStream(f);
				String nowPath =nowFile.getPath()+File.separator+f.getName();
				File nf = new File(nowPath);
				FileOutputStream fos = new FileOutputStream(nf);
				pw = new PrintWriter(fos,true);
				br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
				String str = null;
				while((str=br.readLine())!=null) {
					pw.println(str);
				}
			
			}
			
		}
		
	}
}
