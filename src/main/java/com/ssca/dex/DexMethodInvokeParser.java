package com.ssca.dex;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.jar.JarFile;

import com.ssca.format.Dex;
import com.ssca.format.DexMethodInfo;
import com.ssca.utils.ApkUnZip;
import com.ssca.utils.ByteUtils;
import com.ssca.utils.CodeParser;

public class DexMethodInvokeParser {
	
	public static void getMethodInvoke(JarFile jarFile, String dexName, Dex dex, DexMethodInfo method) throws IOException {
		System.out.println("Parsing method :" + method.classname + method.name);
		DataInputStream dis = ApkUnZip.getDexDataInputStreamWithBuffered(jarFile, dexName);
		if(dis!=null){
			System.out.println("method_offset: 0x" + Integer.toHexString(method.offset));
			dis.skipBytes(method.offset);
			dis.skipBytes(12);
			byte[] inst_count = new byte[4];
			dis.read(inst_count);
//			int method_end = method.offset + 16 + ByteUtils.byte2Int(inst_count) * 2; // actually this is next method's start_address
//			System.out.println("end: " + method_end);
			byte[] method_code = new byte[ByteUtils.byte2Int(inst_count) * 2];
			dis.read(method_code);
			CodeParser.method_parser(method.offset+16, method_code, dex);
		}
	}
}
