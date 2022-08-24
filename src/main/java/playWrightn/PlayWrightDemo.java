package playWrightn;

import java.nio.file.Paths;
import org.apache.commons.codec.binary.Base64;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

public class PlayWrightDemo {
	
	    //To create playWright
		Playwright playwright = Playwright.create();
        //To launch Cromium Browser
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		//Initiating BrowserContext
		BrowserContext context = browser.newContext();
		
private void page1Setup(){	
			
		//Initiating page1	
		Page page1 =context.newPage();
		//To Open Oracle URL
		page1.navigate("https://www.oracle.com/");

		//To click View account
		page1.click("id=acctBtnLabel");
		//To Click Sign-In Button
		page1.click("text=Sign-In");

		//To Login valid User name
		page1.locator("id=sso_username").fill("sudhayabalan7@gmail.com");
		//To Login Valid  password
		
		Locator password = page1.locator("id=ssopassword");
		password.fill(decodeString("diNmOWtGNXErLV83RUVa"));
		//To Click Submit Button
		page1.click("id=signin_button");

		//To Save Cookies For Json file
		context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("oraclelogin.json")));

		//To close page 1 Browser
		page1.close();
	    }

static String decodeString(String password)
{
 byte[] decodedString = Base64.decodeBase64(password);
 return(new String(decodedString));
}

private void page2Setup() {
			
		//To Using Already Saved Json File For  Continuous Browser Actions
		BrowserContext context2 = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("oraclelogin.json")));
		//Initiating page2
		Page page2 = context2.newPage();
		//To Open Oracle URL
		page2.navigate("https://www.oracle.com/");
		//To click View My account Button for Log in details will be Correct or Not
		page2.click("//*[@id=\"acctBtnLabel\"]");
		page2.waitForLoadState(LoadState.LOAD);
		}

public static void main(String[] args) {
		//object create for two methods
		PlayWrightDemo play = new PlayWrightDemo();
		play.page1Setup();
		play.page2Setup();
		
		}
		}
