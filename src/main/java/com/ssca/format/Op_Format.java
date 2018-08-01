package com.ssca.format;

import java.util.List;


public class Op_Format {
	
	public static void init_ops(List<String> opList) {
		opList.add("10x"); // 00 nop
		opList.add("12x"); // 01 move vA, vB
		opList.add("22x"); // 02 move/from16 vAA, vBBBB
		opList.add("32x"); // 03 move/16 vAAAA, vBBBB
		opList.add("12x"); // 04 move-wide vA, vB
		opList.add("22x"); // 05 move-wide/from16 vAA, vBBBB
		opList.add("32x"); // 06 move-wide/16 vAAAA, vBBBB
		opList.add("12x"); // 07 move-object vA, vB
		opList.add("22x"); // 08 move-object/from16 vAA, vBBBB
		opList.add("32x"); // 09 move-object/16 vAAAA, vBBBB
		opList.add("11x"); // 0a move-result vAA
		opList.add("11x"); // 0b move-result-wide vAA
		opList.add("11x"); // 0c move-result-object vAA
		opList.add("11x"); // 0d move-exception vAA
		opList.add("10x"); // 0e return-void
		opList.add("11x"); // 0f return vAA
		opList.add("11x"); // 10 return-wide vAA
		opList.add("11x"); // 11 return-object vAA
		opList.add("11n"); // 12 const/4 vA, #+B
		opList.add("21s"); // 13 const/16 vAA, #+BBBB
		opList.add("31i"); // 14 const vAA, #+BBBBBBBB
		opList.add("21h"); // 15 const/high16 vAA, #+BBBB0000
		opList.add("21s"); // 16 const-wide/16 vAA, #+BBBB
		opList.add("31i"); // 17 const-wide/32 vAA, #+BBBBBBBB
		opList.add("51l"); // 18 const-wide vAA, #+BBBBBBBBBBBBBBBB
		opList.add("21h"); // 19 const-wide/high16 vAA, #+BBBB000000000000
		opList.add("21c"); // 1a const-string vAA, string@BBBB
		opList.add("31c"); // 1b const-string/jumbo vAA, string@BBBBBBBB
		opList.add("21c"); // 1c const-class vAA, type@BBBB
		opList.add("11x"); // 1d monitor-enter vAA
		opList.add("11x"); // 1e monitor-exit vAA	
		opList.add("21c"); // 1f check-cast vAA, type@BBBB
		opList.add("22c"); // 20 instance-of vA, vB, type@CCCC
		opList.add("12x"); // 21 array-length vA, vB
		opList.add("21c"); // 22 new-instance vAA, type@BBBB
		opList.add("22c"); // 23 new-array vA, vB, type@CCCC
		opList.add("35c"); // 24 filled-new-array {vC, vD, vE, vF, vG}, type@BBBB
		opList.add("3rc"); // 25 filled-new-array/range {vCCCC .. vNNNN}, type@BBBB
		opList.add("31t"); // 26 fill-array-data vAA, +BBBBBBBB (with supplemental data as specified below in "fill-array-data-payload Format")
		opList.add("11x"); // 27 throw vAA
		opList.add("10t"); // 28 goto +AA
		opList.add("20t"); // 29 goto/16 +AAAA
		opList.add("30t"); // 2a goto/32 +AAAAAAAA
		opList.add("31t"); // 2b packed-switch vAA, +BBBBBBBB (with supplemental data as specified below in "packed-switch-payload Format")
		opList.add("31t"); // 2c sparse-switch vAA, +BBBBBBBB (with supplemental data as specified below in "sparse-switch-payload Format")
		for(int i=0; i<5; i++) {
			opList.add("23x"); // cmpkind vAA, vBB, vCC
		}
		for(int i=0; i<6; i++) {
			opList.add("22t"); // if-test vA, vB, +CCCC
		}
		for(int i=0; i<6; i++) {
			opList.add("21t"); // if-testz vAA, +BBBB
		}
		for(int i=0; i<6; i++) {
			opList.add("10x"); // unused
		}
		for(int i=0; i<14; i++) {
			opList.add("23x"); // arrayop vAA, vBB, vCC
		}
		for(int i=0; i<14; i++) {
			opList.add("22c"); // iinstanceop vA, vB, field@CCCC
		}
		for(int i=0; i<14; i++) {
			opList.add("21c"); // sstaticop vAA, field@BBBB
		}
		for(int i=0; i<5; i++) {
			opList.add("35c"); // invoke-kind {vC, vD, vE, vF, vG}, meth@BBBB
		}
		opList.add("10x"); // 73 unused
		for(int i=0; i<5; i++) {
			opList.add("35c"); // invoke-kind/range {vCCCC .. vNNNN}, meth@BBBB
		}
		for(int i=0; i<2; i++) {
			opList.add("10x"); // unused
		}
		for(int i=0; i<21; i++) {
			opList.add("12x"); // unop vA, vB
		}
		for(int i=0; i<32; i++) {
			opList.add("23x"); // binop vAA, vBB, vCC
		}
		for(int i=0; i<32; i++) {
			opList.add("12x"); // binop/2addr vA, vB
		}
		for(int i=0; i<8; i++) {
			opList.add("22s"); // binop/lit16 vA, vB, #+CCCC
		}
		for(int i=0; i<11; i++) {
			opList.add("22b"); // binop/lit8 vAA, vBB, #+CC
		}
		for(int i=0; i<23; i++) {
			opList.add("10x"); // unused
		}
		
		opList.add("45cc"); // fa invoke-polymorphic {vC, vD, vE, vF, vG}, meth@BBBB, proto@HHHH
		opList.add("4rcc"); // fb invoke-polymorphic/range {vCCCC .. vNNNN}, meth@BBBB, proto@HHHH
		opList.add("35c"); // fc invoke-custom {vC, vD, vE, vF, vG}, call_site@BBBB
		opList.add("3rc"); // fd invoke-custom/range {vCCCC .. vNNNN}, call_site@BBBB
		
		for(int i=0; i<2; i++) {
			opList.add("10x"); // unused
		}
	}
	
}
