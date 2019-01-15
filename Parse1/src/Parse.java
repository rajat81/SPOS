import java.io.*;
// TAB 9
// NEWLINE 10
public class Parse {
	public static void main(String[] args) throws IOException {
		FileInputStream in = new FileInputStream("demo.asm");
		FileOutputStream out = new FileOutputStream("demo1.asm");
		
		in.close();out.close();
		
	}
}
