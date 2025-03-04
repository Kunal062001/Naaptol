package utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Parameterization {
	public static String excel(int row,int cell,String sheet) throws EncryptedDocumentException, IOException {
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"//testdata//test.xlsx");
		String value=WorkbookFactory.create(file).getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();
		return value;
	}
}
