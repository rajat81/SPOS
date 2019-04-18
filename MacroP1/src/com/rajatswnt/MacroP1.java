package com.rajatswnt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class MacroP1 {
	
	BufferedReader br;
	FileWriter mnt,mdt, kpdt, pnt, intermediate;
	LinkedHashMap<String, Integer> pntab = new LinkedHashMap<>();
	
	
	public MacroP1() throws IOException {
		br = new BufferedReader(new FileReader("macro_input.asm"));
		mnt = new FileWriter("mnt");
		mdt = new FileWriter("mdt");
		kpdt = new FileWriter("kpdt");
		pnt = new FileWriter("pnt");
		intermediate = new FileWriter("intermediate");
		
	}
	
	private void pass1() throws IOException {
		String line,macroName = null;
		int mdtp = 1, kpdtp = 0, paramNo = 1, pp=0,kp=0,flag = 0;
		
		while((line = br.readLine())!= null) {
			String parts[] = line.split("\\s+");
			if (parts[0].equalsIgnoreCase("MACRO")) {
				flag = 1; 	// inside macro
				line = br.readLine();
				parts = line.split("\\s+");
				macroName = parts[0];
				
				if(parts.length<=1) {
					mnt.write(parts[0]+"\t"+pp+"\t"+kp+"\t"+mdtp+"\t"+ ((kp==0)?kpdtp:(kpdtp+1)) +"\n" );
					continue;
				}
				for (int i = 0; i < parts.length; i++) {
					// processing parameters
					parts[i] = parts[i].replaceAll("[&,]", "");
					if (parts[i].contains("=")) {
						kp++;
						String[] keywordparam = parts[i].split("=");
						pntab.put(keywordparam[0], paramNo++);
						if(keywordparam.length==2) {
							kpdt.write(keywordparam[0] + "\t" + keywordparam[1]+"\n");
						}
						else
							kpdt.write(keywordparam[0] + "\t-\n" );
					}
					else {
						pntab.put(macroName, paramNo++);
						pp++;
					}
					mnt.write(parts[0]+"\t"+pp+"\t"+kp+"\t"+mdtp+"\t"+ ((kp==0)?kpdtp:(kpdtp+1)) +"\n" );
					kpdtp =  kpdtp + kp;
				}
				
			}
			else if (parts[0].equalsIgnoreCase("MEND")) {
				flag = kp = pp = 0;
				paramNo = 1;
				mdtp++;
				
				pnt.write(macroName+":\t");
				Iterator<String> iter = pntab.keySet().iterator();
				while(iter.hasNext()) {
					pnt.write(iter.next()+"\t");
				}
				pnt.write("\n");
				pntab.clear();
			}
			else if (flag == 1) {
				for (int i = 0; i < parts.length; i++) {
					if(parts[i].contains("&")) {
						parts[i] = parts[i].replaceAll("[&,]", "");
						mdt.write("(P,"+pntab.get(parts[i])+")\t");
					}
					else
					{
						mdt.write(parts[i]+"\t");
					}
				}
				mdtp++;
				mdt.write("\n");
			}
			
			else {
				intermediate.write(line+"\n\n");
				System.out.println("Writing");
			}
			
			
		}
		br.close();
		mdt.close();
		mnt.close();
		intermediate.close();
		pnt.close();
		kpdt.close();

	}
	
	public static void main(String[] args) throws IOException {
		MacroP1 mp1 = new MacroP1();
		mp1.pass1();
	}

}
