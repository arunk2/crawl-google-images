package com.ppltech.crawl;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 
 * Class for crawling given set of celebrities from google images.
 * This will simulate a user search in google chrome browser and 
 * saves the image files in default download directory.
 * 
 * @author arun
 *
 */
public class ImageCrawl {
	Robot robot;
	public ImageCrawl() throws AWTException {
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
		robot = new Robot();
	}
	
	public static void main(String[] args) throws AWTException {

		ImageCrawl test = new ImageCrawl();
		String celebrity;

		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"resources/search-keywords.txt"));
			while ((celebrity = br.readLine()) != null) {
				System.out.println(celebrity);
				test.getCelebrity(celebrity);
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// exit the program explicitly
		System.exit(0);

	}
    
    public void getCelebrity(String celebrity) throws AWTException  {
    	
    	WebDriver driver = new ChromeDriver();
        String googleImageSearch = "https://www.google.co.in/search?q="+celebrity+"&biw=1301&bih=678&source=lnms&tbm=isch&sa=X&sqi=2&ved=0ahUKEwiT4461htjPAhUMuo8KHQv6D3wQ_AUIBygC#q="+celebrity+"&tbm=isch&tbs=itp:face";
        
        // launch Chrome browser and direct it to the Base URL
        driver.get(googleImageSearch);
        
		try {
			//Give time for response
			Thread.sleep(3000);

			//Scroll 20 times
			for (int i = 0; i < 10; i++) {
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
				Thread.sleep(1000);
			}
			
			//Save the file
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_S);
			Thread.sleep(1000);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			//Give enough time to saving files
			Thread.sleep(20000);
	    			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
       
        //close browser
        driver.close();
        
    }

}