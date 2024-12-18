package assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FitPeoAssignment {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// 1. Navigate to the FitPeo HomePage
		driver.get("https://www.fitpeo.com/");

		// 2. Navigate to the Revenue Calculator Page
		driver.findElement(By.xpath("//a[contains(@href, 'revenue')]")).click();
		Thread.sleep(2000);

		// 3. Scroll Down to the Slider section:
		// Locate the target element
		WebElement element = driver.findElement(By.xpath("//h4[contains(text(),'Medicare Eligible')]"));

		// Scroll to the element
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);

		// 4. Adjust the Slider
		WebElement slider = driver.findElement(By.xpath("//input[@type='number']"));
		String sliderValue = slider.getAttribute("value");
		System.out.println("get input text " + sliderValue);
		int length = sliderValue.length();
		System.out.println("char length " + length);

		// Simulate clearing the field using a loop
		for (int i = 0; i < length; i++) { // 3 iterations for a 3-digit field
			slider.sendKeys(Keys.BACK_SPACE);
		}
		// Enter value
		slider.sendKeys("820");

		// 5. Update the Text Field:
		String sliderValue1 = slider.getAttribute("value");
		System.out.println("get input text " + sliderValue1);
		int length1 = sliderValue1.length();
		System.out.println("char length " + length1);

		// Simulate clearing the field using a loop
		for (int i = 0; i < length1; i++) {
			slider.sendKeys(Keys.BACK_SPACE);
		}
		// Enter new value
		slider.sendKeys("560");

		// Get the slider's value attribute
		String sliderValue2 = slider.getAttribute("value");
		System.out.println("get input text " + sliderValue2);

		// 6. Validate Slider Value: Validate if the slider value matches the entered
		// value
		if (sliderValue2.equals("560")) {
			System.out.println("Slider position is correctly updated to 560.");
		} else {
			System.out.println("Slider position does not match the expected value.");
		}

		// 7. Select CPT Codes CPT-99091
		Thread.sleep(1000);
		// Locate the target element
		WebElement element1 = driver.findElement(By.xpath("//p[contains(text(), 'CPT-99091')]"));

		// Scroll to the element
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].scrollIntoView();", element1);

		// select checkBoxe CPT-99091
		driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();

		// select checkBoxe CPT-99453
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();

		// select checkBoxe CPT-99454
		driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();

		// select checkBoxe CPT-99474
		driver.findElement(By.xpath("(//input[@type='checkbox'])[8]")).click();

		// 8. Validate Total Recurring Reimbursement 
		// 9. Verify that the header displaying
		Thread.sleep(1000);
		
		WebElement reimbursementAmounts = driver
				.findElement(By.xpath("(//p[contains(text(),'Total Recurring')]//..//p)[7]"));

		// Initialize total reimbursement variable
            double totalReimbursement = 75600;		           
           
            // Locate the element displaying the expected total
            String totalAmountText = reimbursementAmounts.getText().replaceAll("[^0-9.]", ""); // Remove non-numeric characters (if any)
            double displayedTotal = Double.parseDouble(totalAmountText);

            // Validate that the calculated total matches the displayed total
            if (totalReimbursement == displayedTotal) {
                System.out.println("The total recurring reimbursement is correct: " + displayedTotal);
            } else {
                System.out.println("Mismatch! Calculated total: " + totalReimbursement + ", Displayed total: " + displayedTotal);
            }
	}
}