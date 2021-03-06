package cn.yesway.bmw.manage.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * 获取properties配置文件配置的工具类<br>
 * 本类提供了加载properties配置文件的一系列工具方法<br>
 * 
 * @author Mr.Mol
 *
 */
public class PropUtils {
	private static Map<String, String> cache = new HashMap<String, String>();

	/**
	 * 初始化<br>
	 * 在static块中调用本方法来初始化类<br>
	 * 
	 * @param paths
	 *            配置文件的路径数组<br>
	 */
	public static void init(String... paths) {
		/* 清空map中的数据 */
		cache.clear();
		/* 装载配置文件中的数据 */
		for (String path : paths) {
			load(path);
		}
	}

	/**
	 * 将一个peoperties文件中的数据装入map<br>
	 * 
	 * @param filePath
	 *            要读取的配置文件的路径，如"/config.properties"<br>
	 */
	public static void load(String filePath) {
		/* 装载配置文件 */
		Properties global = new Properties();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(PropUtils.class.getResourceAsStream(filePath)));
			global.load(br);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/* 将配置文件中的数据读入map */
		Set keys = global.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = global.getProperty(key);
			cache.put(key, value);
		}
	}

	/**
	 * 获取配置信息<br>
	 * 
	 * @param key
	 *            配置信息对应的key。<br>
	 * @return
	 */
	public static String get(String key) {
		return cache.get(key);
	}

	public static Integer getInteger(String key){
		String value = cache.get(key);
		return StringUtils.isBlank(value)?null:Integer.valueOf(value);
	}
	
	/**
	 * 获取配置信息<br>
	 * 
	 * @param key
	 *            配置信息对应的key。<br>
	 * @return
	 */
	public static String get(URL url) {
		String urlName = url.toString();
		String urlStrategy = cache.get("urlStrategy");
		return cache.get(urlName+urlStrategy);
	}

	/**
	 * 配置文件中的key
	 * 
	 * @author Mr.Mol
	 *
	 */
	public static enum URL {
		/** 基础平台的地址 */
		GET_VEHICLE_STATE("getVehicleStateUrl_"),
		SEND_CONTROL_COMMAND("sendControlCommand_"),
		CHECK_STATE("checkState_");
		
		private String key;

		private URL(String key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return this.key;
		}
	}
}