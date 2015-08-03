package com.putaolab.dex.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.os.Environment;

public class FileUtil {
	
	public static void copyAssetJarToFile(Context context,String fileName,String des){
		InputStream inputStream = null;
		FileOutputStream fos = null;
		
		try {
//			File file = new File(context.getFilesDir().getAbsolutePath() +"/putao_plugin" + File.separator + des);
			File file = new File(Environment.getExternalStorageDirectory().toString()+ File.separator + des);
			if(file.exists())
				return;
			inputStream = context.getAssets().open(fileName);
			file.createNewFile();
			fos = new FileOutputStream(file);
			int len = 0;
			byte buffer[] = new byte[1024];
			while((len = inputStream.read(buffer))!= -1){
				fos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(inputStream != null){
					inputStream.close();
				}
				if(fos != null){
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void copyAssetJarToData(Context context,String fileName,String des){
		InputStream inputStream = null;
		FileOutputStream fos = null;
		
		try {
			File file = new File(context.getFilesDir().getAbsolutePath() + File.separator + des);
//			File file = new File(Environment.getExternalStorageDirectory().toString()+ File.separator + des);
			if(file.exists())
				return;
			inputStream = context.getAssets().open(fileName);
			file.createNewFile();
			fos = new FileOutputStream(file);
			int len = 0;
			byte buffer[] = new byte[1024];
			while((len = inputStream.read(buffer))!= -1){
				fos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(inputStream != null){
					inputStream.close();
				}
				if(fos != null){
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
