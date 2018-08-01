package com.ssca.utils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import com.ssca.format.Dex;

public class CodeParser {
	private static int[] invoke_opcode = {0x6e, 0x6f, 0x70, 0x71, 0x72, 0x74, 0x75, 0x76, 0x77, 0x78, 0xfa, 0xfb, 0xfc, 0xfd};
	
	public static void method_parser(int code_offset, byte[] code_bytes, Dex dex) throws IOException {
		InputStream inputstream = new ByteArrayInputStream(code_bytes); 
		DataInputStream code = new DataInputStream(inputstream);  
		int offset = code_offset;
		while(code.available() > 0) {
			byte opcode = code.readByte();
			int instruction_len = Character.getNumericValue(dex.ops.get(opcode & 0xFF).charAt(0));
//			System.out.println("Instruction_length: " + instruction_len);
//			System.out.println("OPCODE: " + Integer.toHexString(opcode & 0xFF));
			byte[] instruction = new byte[instruction_len * 2];
			instruction[0] = opcode;
			code.read(instruction, 1, instruction_len * 2 - 1);
			instruction_parser(offset, instruction, dex);
			offset += instruction_len * 2;
		}
		System.out.println("");
	}
	
	public static void instruction_parser(Integer offset, byte[] instruction, Dex dex) {
		byte opcode = instruction[0];
		if(opcode >= 0x6e && opcode <= 0x72) {
			byte[] idx = new byte[2];
			idx[0] = instruction[2];
			idx[1] = instruction[3];
			int method_idx = ByteUtils.twoBytes2Int(idx);
			try {
				System.out.println(Integer.toHexString(offset) + "  Invoke = > " + dex.methodList.get(method_idx));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
		}
	}
}
