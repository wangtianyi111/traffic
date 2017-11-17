package cqupt.wty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cqupt.wty.FileUtil;

public class Name2Id {
	
	public static void main(String[] args) {
		Name2Id.name2Id();
	}
	
	public static String stringProce(String str){
		str = str.replaceAll("\\d", "");
		str = str.replaceAll("号线", "");
		return str;
	}
	
	public static void name2Id(){
		String[] s1 = FileUtil.read("C:\\Users\\wty\\Desktop\\1_SPTCC-20150401_.csv", null);
		String[] s2 = FileUtil.read("C:\\Users\\wty\\Desktop\\station_num.txt", null);
		Map<String, String> map = new HashMap<String, String>();
		for(int i = 1;i < s2.length;i++){
			String[] s = s2[i].split(",");
			map.put(s[0], s[1]);//key:id   value:name 
		}
		//System.out.println(map.size());
		//System.out.println(map.values());
		
		String[] s3 = new String[s1.length];
		int k = 0 ;
		for(int j = 1;j < s1.length;j++){
			String[] s1_1 = s1[j].split(",");
			for(String key : map.keySet()){
				//System.out.println("key:" + key + "|| value:" + map.get(key) );
				//System.out.println(s1_1[3]);
				if (Name2Id.stringProce(s1_1[3]).equals(map.get(key))) {
					s3[k++] = s1_1[2] + "," + key;
					break;
				}
			}
		}
		//FileUtil.write("C:\\Users\\wty\\Desktop\\name2Id.csv", s3, true);
		List<String> list = new ArrayList<>();
		
		
		int i = 0;
		try {
			for(;i< s3.length-1;i++) {
				String[] s4 = s3[i].split(",");
				list.add(TimeProc.timeDivided(s4[0]) + "");
				if (s3[i+1] == null) {
					TimeProc.timeMap(list, "C:\\Users\\wty\\Desktop\\name2Id_result.csv", s4[1]);
					list.clear();
					return;
				}
				String[] s5 = s3[i+1].split(",");
				if (s5[1].equals(s4[1]) == false) {
					TimeProc.timeMap(list, "C:\\Users\\wty\\Desktop\\name2Id_result.csv", s4[1]);
					list.clear();
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("i=" + i);
		}
		
	}
}

