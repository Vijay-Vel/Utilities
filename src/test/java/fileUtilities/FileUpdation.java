package fileUtilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUpdation {
	public String str1;
	public String str2;
	public String str3;
	public String str4;

	public String BaseXML = System.getProperty("user.dir") + "/TestData/XML/BaseXML.xml";
	public String BaseJSON = System.getProperty("user.dir") + "/TestData/JSON/BaseJSON.json";

	public String UpdatedDataXML = System.getProperty("user.dir") + "/TestData/XML/UpdatedDataXML.xml";
	public String UpdatedDataJSON = System.getProperty("user.dir") + "/TestData/JSON/UpdatedDataJSON.json";

	public static void copyContentsToFile(String sourcePath, String DestinationPath) throws IOException {

		@SuppressWarnings("resource")
		FileChannel src = new FileInputStream(sourcePath).getChannel();

		@SuppressWarnings("resource")
		FileChannel dest = new FileOutputStream(DestinationPath).getChannel();

		try {
			dest.transferFrom(src, 0, src.size());
		} finally {
			src.close();
			dest.close();
		}
	}

	public static void UpdateDataInFile(String path, String oldData, String newData) throws IOException {
		File ModifiedFile = new File(path);

		StringBuilder oldContent = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(ModifiedFile))) {
			String line = reader.readLine();
			while (line != null) {
				oldContent.append(line).append(System.lineSeparator());
				line = reader.readLine();
			}
			String content = oldContent.toString();
			String newContent = content.replace(oldData, newData);

			try (FileWriter filewriter = new FileWriter(ModifiedFile)) {
				filewriter.write(newContent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void createFile(String fileType) throws IOException {
		if (fileType.equalsIgnoreCase("XML")) {
			copyContentsToFile(BaseXML, UpdatedDataXML);
		} else if (fileType.equalsIgnoreCase("JSON")) {
			copyContentsToFile(BaseJSON, UpdatedDataJSON);
		}
	}

	public void updateFile(String fileType) throws IOException {
		Date date = new Date();
		String name = "Vijay";
		String fileName = "UpdateFile";

		SimpleDateFormat format1 = new SimpleDateFormat("dd");
		str1 = format1.format(date);
		SimpleDateFormat format2 = new SimpleDateFormat("MM");
		str2 = format2.format(date);
		SimpleDateFormat format3 = new SimpleDateFormat("yyyy");
		str3 = format3.format(date);

		String finalDate = str1 + "/" + str2 + "/" + str3;

		if (fileType.equalsIgnoreCase("XML")) {
			UpdateDataInFile(UpdatedDataXML, "updateName", name);
			UpdateDataInFile(UpdatedDataXML, "updateFileName", fileName);
			UpdateDataInFile(UpdatedDataXML, "updateDate", finalDate);
			System.out.println("XML file has been updated...");
		} else if (fileType.equalsIgnoreCase("JSON")) {
			UpdateDataInFile(UpdatedDataJSON, "updateName", name);
			UpdateDataInFile(UpdatedDataJSON, "updateFileName", fileName);
			UpdateDataInFile(UpdatedDataJSON, "updateDate", finalDate);
			System.out.println("JSON file has been updated...");
		}
	}

}
