import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class Exercise0132 {
	public static void main(String[] args) {
		final String FILE_NAME = "vehicles.dat";
		
		try {
			InputStream inputFile = new FileInputStream(new File(FILE_NAME));
			final int FILE_SIZE = (int) new File(FILE_NAME).length();
			byte[] buffer = new byte[FILE_SIZE];
			inputFile.read(buffer,0,FILE_SIZE);
			inputFile.close();
			
			PrintWriter writer = new PrintWriter("vehicles.txt");
			for(int i=0;i < buffer.length;i++) {
				char character = (char)buffer[i];
				if((character >= 'a' && character <= 'z')
					|| (character >= 'A' && character <= 'Z')
					|| (character >= '0' && character <= '9')
					|| character == ' ')
					writer.write(character);
			}
			writer.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
