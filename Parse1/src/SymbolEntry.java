public class SymbolEntry{
	private String name;
	int addr;
	int length;
	public SymbolEntry(String name1) {
		this.name = name1;
		
	}
	public SymbolEntry(String name,int addr) {
		this.name = name;
		this.addr = addr;
		this.length = 1;
	}
	public SymbolEntry(String name,int addr,int len) {
		this.name = name;
		this.addr = addr;
		this.length = len;
	}
	public String getName() {
		return name;
	}
	public void setAddr(int addr) {
		this.addr = addr;
	}
	public void setLength(int length) {
		this.length = length;
	}
}