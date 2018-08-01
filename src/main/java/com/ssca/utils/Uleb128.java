package com.ssca.utils;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class Uleb128 {
	public static void main(String[] args) throws IOException {
		byte[] addr = {(byte) 0x01, (byte) 0x01, (byte) 0xD0, (byte) 0x89, (byte) 0x3f};
		InputStream input = new ByteArrayInputStream(addr); 
		DataInputStream inst = new DataInputStream(input);  
		System.out.println(readLeb128(inst));
	}
	public static int readLeb128(DataInputStream input) throws IOException{
		int result=0; int cur=0;
		result=input.read();
		if(result>0x7f){
			cur=input.read();
			result=(result& 0x7f)|((cur&0x7f)<<7);
			if(cur>0x7f){
				cur=input.read();
				result|=(cur&0x7f)<<14;
			//	result=result|((cur&0x7f)<<14);
				if(cur>0x7f){
					cur=input.read();
					result|=(cur&0x7f)<<21;
					if(cur>0x7f){
						cur=input.read();
						result|=cur<<28;
					}
				}
			}
		}
		return result;
	}
	public static int readLeb128fromBytes(List<Byte> bytes) throws IOException{
		byte[] byteArray = new byte[bytes.size()];
		int index = 0;
		for (byte b : bytes) {
		    byteArray[index++] = b;
		}
		InputStream inputstream = new ByteArrayInputStream(byteArray); 
		DataInputStream input = new DataInputStream(inputstream);  
		int result=0; int cur=0;
		result=input.read();
		if(result>0x7f){
			cur=input.read();
			result=(result& 0x7f)|((cur&0x7f)<<7);
			if(cur>0x7f){
				cur=input.read();
				result|=(cur&0x7f)<<14;
			//	result=result|((cur&0x7f)<<14);
				if(cur>0x7f){
					cur=input.read();
					result|=(cur&0x7f)<<21;
					if(cur>0x7f){
						cur=input.read();
						result|=cur<<28;
					}
				}
			}
		}
		return result;
	}
}
