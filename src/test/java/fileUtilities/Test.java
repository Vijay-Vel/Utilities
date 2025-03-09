package fileUtilities;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileUpdation fileUpdate = new FileUpdation();
		fileUpdate.createFile("JSON");
		fileUpdate.updateFile("JSON");

	}

}
