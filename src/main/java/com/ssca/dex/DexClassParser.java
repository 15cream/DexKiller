package com.ssca.dex;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.jar.JarFile;

import com.ssca.format.Dex;
import com.ssca.format.DexClassData;
import com.ssca.utils.ApkUnZip;
import com.ssca.utils.ByteUtils;

public class DexClassParser {
	
	public static void getClassInfo(JarFile jarFile, String dexName, Dex dex) throws IOException{
		DataInputStream dis = ApkUnZip.getDexDataInputStreamWithBuffered(jarFile, dexName);
		if(dis!=null){
			int classSize = ByteUtils.byte2Int(dex.dexHeader.class_defs_size);
			int classOff  = ByteUtils.byte2Int(dex.dexHeader.class_defs_off);
			List<String> typeList = dex.typeList;
//			byte[]eachClass = new byte[32];
			byte[]eachClassIdx = new byte[4];
			byte[]zero = {0x00,0x00,0x00,0x00};
			dis.skipBytes(classOff);
			for(int i=0;i<classSize;i++){
				DexClassData classData = new DexClassData();
				dis.read(eachClassIdx);
				dis.skipBytes(20);
				dis.read(classData.classDataOff);
				dis.skipBytes(4);
//				System.out.println("get class : " +typeList.get(ByteUtils.byte2Int(eachClassIdx)));
//				System.out.println("get classDataOff : " + classDef.classDataOff);
				dex.classList.add(typeList.get(ByteUtils.byte2Int(eachClassIdx)));
//				if(classData.classDataOff != zero && ByteUtils.bytes2HexString(classData.classDataOff) != "00000000") {
				if(new BigInteger(classData.classDataOff).intValue() != 0) {
					dex.classDataList.put(typeList.get(ByteUtils.byte2Int(eachClassIdx)), classData);
				}
//				dex.classDefList.add(classDef);
			}
		}else{
			System.err.println("dis is null");
		}
	}

}
