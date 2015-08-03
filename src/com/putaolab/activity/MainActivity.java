package com.putaolab.activity;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import com.putaolab.dex.utils.FileUtil;

import dalvik.system.DexClassLoader;

public class MainActivity extends Activity {

	private static final String tag = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*法一：把优化后的jar(testdex.jar)复制到SD卡下，通过反射来调用插件的方法*/
		FileUtil.copyAssetJarToFile(this, "testdex.jar", "testdex.jar");
		File file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "testdex.jar");
		File optimizedDexOutputPath = getDir("temp", Context.MODE_PRIVATE);
		/**
		 * DexClassLoader类加载器构造的四个参数
		 * String dexPath：the list of jar/apk files containing classes and resources, delimited by File.pathSeparator, which defaults to ":" on Android
		 * 					需要装在的apk或者jar文件的路径，包含多个路径用File.pathSeparator间隔开，在Android上默认是“：”	
		 * String optimizedDirectory：directory where optimized dex files should be written; must not be null
		 * 					优化后的dex文件存放目录，不能为null
		 * String libraryPath：the list of directories containing native libraries, delimited by File.pathSeparator; may be null
		 * 					目标类中使用的c/c++库的列表，每个目录用File.pathSeparator间隔开，可以为null
		 * ClassLoader parent：	the parent class loader
		 * 					该类装载器的父装载器，一般用当前执行类的装载器
		 */
		DexClassLoader dexClassLoader = new DexClassLoader(file.getAbsolutePath(),
				optimizedDexOutputPath.getAbsolutePath(),
				null,
				getClassLoader());
		
		try {
			Class<?> loadClass = dexClassLoader.loadClass("com.putaolab.dex.Iclass");
			Constructor<?> constructor = loadClass.getConstructor(new Class[]{Context.class});
			/*法一：反射调用方法*/
			Method method = loadClass.getMethod("getData", null);
			String data = (String) method.invoke(constructor.newInstance(this), null);
			Log.e(tag, "data---"+data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//---------------------------------------------------------------------------------------
		
		/*法二：把优化后的jar(testdex.jar)复制到data/data/包名/files/目录下，通过接口来*/
//		FileUtil.copyAssetJarToData(this, "testdex.jar", "testdex.jar");
//		File file = new File(this.getFilesDir().getAbsolutePath() + File.separator + "testdex.jar");
//		File optimizedDexOutputPath = getDir("temp", Context.MODE_PRIVATE);
//		DexClassLoader dexClassLoader = new DexClassLoader(file.getAbsolutePath(),
//				optimizedDexOutputPath.getAbsolutePath(),
//				null,
//				getClassLoader());
//		
//		try {
//			Class<?> loadClass = dexClassLoader.loadClass("com.putaolab.dex.Iclass");
//			Constructor<?> constructor = loadClass.getConstructor(new Class[]{Context.class});
//			/*法二：*/
//			Iinterface newInstance = (Iinterface) constructor.newInstance(this);
//			String data = newInstance.getData();
//			Log.e(tag, "data---"+data);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
