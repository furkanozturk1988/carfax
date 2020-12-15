package myAssignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

public class TeslaFinder {

	public static void main(String[] args) throws InterruptedException {
	
System.setProperty("webdriver.chrome.driver", "/Users/furkanozturk/Documents/SeleniumFiles/Drivers/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.carfax.com");
		driver.findElement(By.xpath("//a[@href='/cars-for-sale']")).click();
		Thread.sleep(1000);
		String usedCars="Used Cars";
		if(driver.getPageSource().contains(usedCars)) {System.out.println("LANDED ON USED CARS PAGE");}
		else {System.out.println("FAIL");}
		driver.findElement(By.xpath("//select[@name='make']")).sendKeys("Tesla");
		Thread.sleep(2000);
		WebElement model=driver.findElement(By.xpath("//select[@name='model']"));
        Select selectModel = new Select(model); 
		selectModel.selectByValue("Model S");
        smartWait(1000);
        
        List<WebElement> modelList= selectModel.getOptions();
        System.out.println("TOTAL RESULTS= " + modelList.size());
        List<String> actualModelList= new ArrayList<>();
        smartWait(1000);
        for(WebElement w: modelList){
            actualModelList.add(w.getText().trim());
        }
        System.out.println("THESE RESULTS WERE FOUND " + actualModelList);
        if(actualModelList.contains("Model 3") && actualModelList.contains("Model S") && actualModelList.contains("Model X")){
            System.out.println("RESULTS INCLUDE ALL MODELS");
        }else{
            System.out.println("Test step 5, FAILED");
        }
        smartWait(1000);
       
        
        driver.findElement(By.xpath("//input[@name='zip']")).sendKeys("22182");
        smartWait(1000);
        driver.findElement(By.xpath("//button[@id='make-model-form-submit']")).click();
        String showmecarsText = "Show me cars with";
        if(driver.getPageSource().contains(showmecarsText)) {System.out.println("LANDED ON PAGE WITH CHECKBOXES");}
        else {System.out.println("STEP 8 FAIL");}
        smartWait(1000);
        List<WebElement>checks = driver.findElements(By.xpath("//span[@class='checkbox-list-item--fancyCbx']"));
        for(WebElement w : checks) {
        	if(!w.isSelected()) {w.click();}
        }
        smartWait(1000);
        String result = driver.findElement(By.xpath("//span[@class='totalRecordsText']")).getText();
        //System.out.println(result);
        driver.findElement(By.xpath("//button[@class='button large primary-green']")).click();
       if( driver.getPageSource().contains(result) ) {System.out.println("6 RESULTS ARE DISPLAYED, PASS");}
       else {System.out.println("STEP 12 FAIL");}
       
       List<WebElement> teslaResults = driver.findElements(By.xpath("//h4[@class='srp-list-item-basic-info-model']"));
        //System.out.println(teslaResults.size());
       String flag="";
       for(WebElement w :teslaResults)  {
        	if(w.getText().contains("Tesla Model S")) {flag+=1;}	
        }
       if(flag.equalsIgnoreCase("111111")) {System.out.println("ALL RESULTS INCLUDE MODEL S");}
       else {System.out.println("STEP 14 FAIL");}
       
       List<WebElement>teslaPrices = driver.findElements(By.xpath("//span[@class='srp-list-item-price']"));
       List<String> str = new ArrayList<>();
       int i=0;
       for(WebElement w:teslaPrices) {
    	   str.add(teslaPrices.get(i).getText().trim().substring(8));
    	   i++;
       }
       //System.out.println(str);
       Collections.sort(str);
       //System.out.println(str);
       
       WebElement sortMenu = driver.findElement(By.xpath("//select[@class='srp-header-sort-select']"));
       smartWait(1000);
       Select fromMenu = new Select(sortMenu);
       fromMenu.selectByValue("PRICE_DESC");
       smartWait(1000);
       
       List<WebElement> sortedTesla = driver.findElements(By.xpath("//span[@class='srp-list-item-price']"));
       List<String> str1 = new ArrayList<>();
       int z=0;
       for(WebElement w:sortedTesla) {
    	   str1.add(sortedTesla.get(z).getText().substring(8));
    	   z++;
       }
       smartWait(1000);
       //System.out.println(str1);
       String flag2="";
       for(int f=0,s=sortedTesla.size()-1;f<sortedTesla.size();f++,s--) {
    	   if(str.get(f).equalsIgnoreCase(str1.get(s))) {flag2+=2;}
    	  
       }
       if(flag2.equalsIgnoreCase("222222")) {System.out.println("SORTED BY PRICE, PASS");}
       else {System.out.println("STEP 17 FAIL");}
       
       WebElement sortMenu1 = driver.findElement(By.xpath("//select[@class='srp-header-sort-select']"));
       smartWait(1000);
       Select fromMenu1 = new Select(sortMenu1);
       fromMenu1.selectByValue("MILEAGE_ASC");
       smartWait(1000);
       
       List<WebElement> spans = driver.findElements(By.xpath("//span"));
   	List<Integer>mileageFromSpans = new ArrayList<>();
   	for(int q=0;q<spans.size();q++) {
   		if(spans.get(q).getText().contains("Mileage")) {
   			mileageFromSpans.add(Integer.parseInt(spans.get(q).getText().substring(9, 15).replace(",", "") )   );
   		}
   	}
   	
   	for(int c=0;c<mileageFromSpans.size()-1;c++) {
   		if(mileageFromSpans.get(c)<mileageFromSpans.get(c+1)) {continue;}
   		else {System.out.println("MILEAGE SORTING FAIL");}
   	}
   	System.out.println("SORTED BY MILEAGE, PASS");
       
       
       smartWait(1000);
       WebElement sortMenu2 = driver.findElement(By.xpath("//select[@class='srp-header-sort-select']"));
       smartWait(1000);
       Select fromMenu2 = new Select(sortMenu2);
       fromMenu2.selectByValue("YEAR_DESC");
       smartWait(1000);
       
       List<WebElement> yearSort = driver.findElements(By.xpath("//h4[@class='srp-list-item-basic-info-model']"));
       List<Integer> years = new ArrayList<>();
       for(WebElement w:yearSort) {
    	   years.add(  Integer.parseInt(w.getText().substring(0, 4)) );
       }
       //System.out.println(years);
       smartWait(1000);
       
       for(int b=0;b<years.size()-1;b++) {
    	   if(years.get(b)>=years.get(b+1)) {continue;}
    	   else {System.out.println("FAIL : SORTED BY YEAR");}   
       }
       System.out.println("SORTED BY YEAR, PASS");
	}
       
	private static void smartWait(int time) {
		
		try{ Thread.sleep(time);}
		catch(Exception e){}
		
	}

	}


