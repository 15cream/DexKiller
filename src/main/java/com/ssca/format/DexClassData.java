package com.ssca.format;

import java.util.ArrayList;
import java.util.List;

public class DexClassData {
	public byte[] classDataOff = new byte[4];
	public int static_field_count;
	public int instance_field_count;
	public int direct_method_count;
	public int virtual_method_count;
	public List<DexMethodInfo> methodlist = new ArrayList<DexMethodInfo>();
	
}
