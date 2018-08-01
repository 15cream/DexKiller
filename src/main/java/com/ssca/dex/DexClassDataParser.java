package com.ssca.dex;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;

import com.ssca.format.Dex;
import com.ssca.format.DexClassData;
import com.ssca.format.DexClassDef;
import com.ssca.utils.ApkUnZip;
import com.ssca.utils.ByteUtils;
import com.ssca.utils.Uleb128;
import com.ssca.format.DexMethodInfo;

public class DexClassDataParser {
	
	public static void getClassData(JarFile jarFile, String dexName, Dex dex, String className) throws IOException{
		DataInputStream dis = ApkUnZip.getDexDataInputStreamWithBuffered(jarFile, dexName);
		if(dis!=null){
			DexClassData classData = dex.classDataList.get(className);
			int classDataOff = ByteUtils.byte2Int(classData.classDataOff);
			dis.skipBytes(classDataOff);
			classData.static_field_count = uleb128value(dis);
			classData.instance_field_count = uleb128value(dis);
			classData.direct_method_count = uleb128value(dis);
			classData.virtual_method_count = uleb128value(dis);
			
//			System.out.println("offset" + ByteUtils.bytes2HexString(classData.classDataOff));
			
			for(int i=0; i< classData.static_field_count;i++) {
				uleb128value(dis);
				uleb128value(dis);
			}
			for(int i=0; i< classData.instance_field_count;i++) {
				uleb128value(dis);
				uleb128value(dis);
			}
			for(int i=0, idx=0; i< classData.direct_method_count;i++) {
				DexMethodInfo method = new DexMethodInfo();
				method.type = "d";
				if(i == 0) {
					idx = uleb128value(dis);
				}else {
					idx += uleb128value(dis);
				}
				method.name = dex.methodList.get(idx).name;
				method.flag = uleb128value(dis);
				method.offset = uleb128value(dis);
				method.classname = className;
				classData.methodlist.add(method);
//				System.out.println(className + method.name);
			}
			
			for(int i=0, idx=0; i< classData.virtual_method_count;i++) {
				DexMethodInfo method = new DexMethodInfo();
				method.type = "v";
				if(i == 0) {
					idx = uleb128value(dis);
				}else {
					idx += uleb128value(dis);
				}
				method.name = dex.methodList.get(idx).name;
				method.flag = uleb128value(dis);
				method.offset = uleb128value(dis);
				method.classname = className;
				classData.methodlist.add(method);
//				System.out.println(className + method.name);
			}
		}else{
			System.err.println("dis is null");
		}
	}
	
	private static int uleb128value(DataInputStream dis) throws IOException {
		List<Byte> bytes = new ArrayList<Byte>();
		byte b;
		do {
			b = dis.readByte();
			bytes.add(b);
//			System.out.println(Integer.toHexString(b & 0xFF));
		}while((b & 0xFF)>0x7f);
		return Uleb128.readLeb128fromBytes(bytes);
	}

}