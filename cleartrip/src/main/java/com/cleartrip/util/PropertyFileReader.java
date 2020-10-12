package com.cleartrip.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;

import com.cleartrip.base.CleartripBase;

public class PropertyFileReader {

	public static void loadProperty() {
		FileInputStream fis=null;
		try {
			CleartripBase.prop= new Properties();
			fis = new FileInputStream("src/main/resources/app.properties");
			CleartripBase.prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
