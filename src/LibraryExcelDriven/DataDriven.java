package LibraryExcelDriven;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public ArrayList<String> getData(String testcaseName,String sheetName) throws IOException
	{
		ArrayList<String> al = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream("F:\\15dec22_laptop\\REST Assured API\\Section 21- Excel Integration with REST Assured test\\TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook (fis);
		
		int sheets = workbook.getNumberOfSheets();
		for (int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase(sheetName))
					{			           
				      XSSFSheet sheet  = workbook.getSheetAt(i);	
				      Iterator<Row> rows = sheet.rowIterator();
				      Row firstRow = rows.next();
				      Iterator<Cell> cel =  firstRow.cellIterator();
				      int k=0;
				      int column = 0;
				      while(cel.hasNext())
				      {
				    	  Cell value = cel.next();
				    	  if(value.getStringCellValue().equalsIgnoreCase("TestCases"))
				    	  {
				    		  column = k;
				    	  }
				    	  k++;
				      }				      
				      System.out.println(column);				      
				      while(rows.hasNext())
				      {
				    	  Row r = rows.next();
				    	  if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName))
				    	  {
				    		  Iterator <Cell> cv = r.cellIterator();
				    		  while(cv.hasNext())
				    		  {				    			  
				    			  Cell c = cv.next();
				    			  if(c.getCellType()==CellType.STRING)
				    			  {
				    			al.add(c.getStringCellValue());
				    			  }
				    			  else
				    			  {
				    				  al.add(NumberToTextConverter.toText(c.getNumericCellValue()));
				     			  }
				    		  }
				    	  }		    	  
				       }    
				      				      
					}			
		}
		return al;
	}
	
	
	
	public static void main(String[] args) throws IOException   {
		
		

	}

}
