package cn.android.installer.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 沙陌 qq2879897713
 *
 */
public class DevicesManager {
	public static String osName = System.getProperty("os.name");
	public List<String> devicesLists;//这个变量用来存放我们的设备的识别码(udid)

	public DevicesManager() {
		this.devicesLists = getAndroidDevices();
	}


	public List<String> getAndroidDevices() {
		String androidResult;
		try {
			androidResult = CommandLineExec.executor("adb devices");
			
		} catch (Exception e) {
			throw new RuntimeException("No android device-->" + e.getMessage());
		}
		String[] result = androidResult.split("\n");
		List<String> deviceRes = new ArrayList<String>();
		int total = 2;
		if (osName.toLowerCase().contains("mac")) {
			total = 1;
		}
		if (result.length >=2) {
			for (int i = 1; i < result.length - total + 1; i++) {
				String deviceInfo[] = result[i].split("\t");
				// System.out.println(deviceInfo[0]);127.0.0.1:62001,device
				if (deviceInfo[1].trim().equals("device")) {
					deviceRes.add(deviceInfo[0].trim());
				}
			}
		} else {
			throw new RuntimeException("No android device ");
		}
		return deviceRes;
	}

	public static void main(String[] args) throws Exception {
		// String commandResult
		// =CommandLineExec.executor(Command.getAndroidDevices());
		// System.out.println(commandResult.split("\n")[0]);
		DevicesManager dm = new DevicesManager();
		List<String> deviceList=dm.getAndroidDevices();
		for(String s:deviceList){
			System.out.println(s);
		}
		System.out.println(deviceList.size());
	}

}
