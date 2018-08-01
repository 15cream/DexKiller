package com.ssca.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dex {
	public String dexName;
	public List<String> ops = new ArrayList<String>();
	public DexHeader dexHeader = new DexHeader();
	public List<Integer> stringOffList = new ArrayList<Integer>();
	public List<String> stringList = new ArrayList<String>();
	public List<String> typeList = new ArrayList<String>();
	public List<String> protoList = new ArrayList<String>();
	public List<String> classList = new ArrayList<String>();
//	public List<DexClassDef> classDefList = new ArrayList<DexClassDef>();
	public List<DexMethod> methodList = new ArrayList<DexMethod>();
	public Map<String, DexClassData> classDataList = new HashMap<String, DexClassData>();

	public Dex(String dexName) {
		this.dexName = dexName;
	}
}
