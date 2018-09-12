package cn.android.installer.test;

import java.util.List;

/**
 * @author 沙陌  qq2879897713
 *
 */
public class InstallMain {
	
	
	public static void main(String[] args) throws Exception {
		if(args.length==3){
			DevicesManager	devicesManager=new DevicesManager();
			List<String> deviceUdids=devicesManager.devicesLists;
			
			for(int i=0;i<deviceUdids.size();i++){
				//第一个参数是要安装的apk路径，写绝对路径
				System.out.println("==========开始安装："+deviceUdids.get(i)+"==========");
				CommandLineExec.executor("adb -s "+deviceUdids.get(i)+" install -r "+args[0]);
				System.out.println("==========开始启动App,5秒后开始截图==========");
				//第二个参数是应用的起始activity，com.zhihu.android/起始activity
				CommandLineExec.executor("adb -s "+deviceUdids.get(i)+"  shell am start -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -n "+args[1]);
				Thread.sleep(5000);
				System.out.println("==========开始截图==========");
				
				CommandLineExec.executor("adb -s "+deviceUdids.get(i)+" shell screencap -p /data/local/tmp/"+deviceUdids.get(i).split(":")[0]+i+".png");
				System.out.println("==========开始将图片pull至PC==========");
				//第三个参数是图片要存放的路径，绝对路径
				CommandLineExec.executor("adb -s "+deviceUdids.get(i)+" pull /data/local/tmp/"+deviceUdids.get(i).split(":")[0]+i+".png "+args[2]);
				System.out.println("==========执行完成:"+deviceUdids.get(i)+"==========");
			}
			System.out.println("==========所有设备执行完成==========");
		}else{
			System.out.println("参数不足");
		}

	}
}
