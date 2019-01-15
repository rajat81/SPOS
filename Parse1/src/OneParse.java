import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

// Output of parse one is written in answer.txt file


public class OneParse {
	HashMap<String,String> imperativeStatemnt = new HashMap<String,String>();
	HashMap<String,String> sysReg = new HashMap<String, String>();
	HashMap<String,String> dl = new HashMap<String, String>();
	Vector<SymbolEntry>symtab = new Vector<SymbolEntry>();
	
	int loc_cntr,pooltab_cntr,littab_cntr;
	int POOLTAB[];
	
	public OneParse() {
		int POOLTAB[] = new int[50];
		imperativeStatemnt.put("STOP", "00");
		imperativeStatemnt.put("ADD", "01");
		imperativeStatemnt.put("SUB", "02");
		imperativeStatemnt.put("MULT", "03");
		imperativeStatemnt.put("MOVER", "04");
		imperativeStatemnt.put("MOVEM", "05");
		imperativeStatemnt.put("COMP", "06");
		imperativeStatemnt.put("BC", "07");
		imperativeStatemnt.put("DIV", "08");
		imperativeStatemnt.put("READ", "09");
		imperativeStatemnt.put("PRINT", "10");
		
		sysReg.put("AREG", "(1)");
		sysReg.put("BREG", "(2)");
		sysReg.put("AREG", "(3)");
		sysReg.put("AREG", "(4)");
		
		loc_cntr = 0;
		pooltab_cntr = 1;
		littab_cntr = 1;
		POOLTAB[1]= 1;
	}
	
	String read_line() throws IOException{
		File f = new File("/home/TE/3168/SPOS/Parse1/src/demo.asm");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = "";
		System.out.println("Reading line...\n");
		for(int i=0;i<3;i++)
			line = br.readLine();
		return line;
	}
	
	boolean contains_label(String line){	// check if label is present
		if (line.charAt(0) == '\t') {
			return false;
		}
		return true;
	}
	
	void oper(String line,boolean label_present) throws IOException{
		String tab = "\t";
		String[] split = line.split(tab, 3);
		
		// System.out.println(split.length);
		/*
		for (int i = 0; i < split.length; i++) {
			System.out.println(split[i]);
		}
		*/
		
		if (label_present == true) {
			// insert into symbol table if not present already
			boolean flag=false;		// flag signifies whether symbol is in symtab or not
			for (Iterator<SymbolEntry> iterator = symtab.iterator(); iterator.hasNext();) {
				SymbolEntry type = (SymbolEntry) iterator.next();
				if (type.getName() == split[0]) {
					flag = true;
					break;
				}
			}
			
			if (flag == false) {
				symtab.add(new SymbolEntry(split[0],loc_cntr));
			}
		}
		
		// Declarative Statements
		if(split[1]=="DS")
		{
			SymbolEntry se,o;
			o = se = symtab.lastElement();
			se.setAddr(loc_cntr);
			int len = Integer.parseInt(split[2]);
			se.setLength(len);
			loc_cntr = loc_cntr + len;
			symtab.remove(o);
			symtab.add(se);
			write_output("(DL,02)\t");
			int i = 0;
			for (Iterator<SymbolEntry> iterator = symtab.iterator(); iterator.hasNext();) {
				SymbolEntry type = (SymbolEntry) iterator.next();
				if (type.getName() == split[0]) {
					write_output("(C,"+i+")\n");
					i++;
					break;
				}
			}
		}
		if (split[1] == "DC") {
			SymbolEntry se,o;
			se = o = symtab.lastElement();
			se.setAddr(loc_cntr);
			se.setLength(1);
			loc_cntr+=1;
			symtab.remove(o);
			symtab.add(se);
			try {
				write_output("(DL,01)\t");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			int i = 0;
			for (Iterator<SymbolEntry> iterator = symtab.iterator(); iterator.hasNext();) {
				SymbolEntry type = (SymbolEntry) iterator.next();
				if (type.getName() == split[0]) {
					try {
						write_output("(C,"+i+")\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
					i++;
					break;
				}
			}
		}
		
		// Imperative statement
		if (imperativeStatemnt.containsKey(split[1]) && split[1]!="BC") {
			String code = imperativeStatemnt.get(split[1]);
			loc_cntr++;
			try {
				write_output("(IS,"+code+")\t");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			loc_cntr  = loc_cntr + 1;
			 // check for comma separated operands
			String[] operands = split[2].split(",");
			if (isLiteral(operands[0])) {
				try {
					write_output(operands[0]+"\t");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			else if (operands[0].matches("[0-9]+") && operands[0].length()>2) { // String is a Constant
				try {
					write_output(operands[0]+"\t");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			else if (sysReg.containsKey(operands[0])) {		// String is a system Register
				try {
					write_output(sysReg.get(operands[0])+"\t");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			
			else 	// String is a symbol
			{
				if(!containsSymbol(operands[0])){
					SymbolEntry se = new SymbolEntry(operands[0]);
					symtab.add(se);
				}
				
				try {
					write_output("(S,"+symtab.size()+")\t");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	
	boolean containsSymbol(String s){
		boolean flag = false;
		for (Iterator<SymbolEntry> iterator = symtab.iterator(); iterator.hasNext();) {
			SymbolEntry type = (SymbolEntry) iterator.next();
			if (type.getName() == s) {
				flag = true;
				return flag;
			}
			
		}
		return false; 
		}
	// write separate condition for BC as codes are different
	
	boolean isLiteral(String op){
		if (op.startsWith("=\"")) {
			return true;
		}
		return false;
	}
	
	private void write_output(String s) throws IOException {
		File file = new File("test1.txt");
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(s);
		fileWriter.flush();
		fileWriter.close();

	}
	public static void main(String[] args) throws IOException {
		OneParse op = new OneParse();
		String a ;
		boolean label_present;
		System.out.println(a = op.read_line());
		System.out.println(label_present = op.contains_label(a));
		System.out.println("\n\n");
		op.oper(a, label_present);
	}
}
