package HurriyetEmlakWebMobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CaseMethods extends BasePage{

    Constant constant = new Constant();

    enum Filter{
        city,
        county,
        category,
        priceRange,
        buildAge
    }

    public CaseMethods(WebDriver driver){
        super(driver);
    }

    public void caseFirst(){
        driver.get(constant.getProperty("homePage"));
        waitPageLoad();

        if(driver.getCurrentUrl().equals(constant.getProperty("homePage"))){
            Assert.assertTrue(true);
        }

        clickElementByXpath("//*[@class='btn btn-red btn-large']");
        clickElementByXpath("//*[@class='wrapper']/following-sibling::div/div/div/div//*[@class='hemlak-icon left-icon']");
        clickElementByXpath("//div[@class='list-basetab']/div/button[2]");
        selectByXpathByValue("//*[@class='city']/select", "izmir");
        sleep(1);
        clickElementByXpath("//*[@class='location']//*[@class='select-content']");
        fillInTheBlankByXpath("//*[@class='dialog-search-filter']/div/following-sibling::input[@class='district-search']", "Bornova");
        sleep(1);
        clickElementByXpath("//div[@class='district-list-dialog']//*[@class='label-to-bold-if-checked']");
        clickElementByXpath("//*[@id='dropdown-lightbox-district']/button");
        sleep(1);
        fillInTheBlankByXpath("//*[@class='price']/div/div[1]/input", "100.000");
        sleep(1);
        fillInTheBlankByXpath("//*[@class='price']/div/div[2]/input", "2.000.000");
        sleep(1);
        clickElementByXpath("//*[@class='building-age']/section");
        sleep(1);

        // Select Build Age
        for(int i=1; i<4; ++i) {
            clickElementByXpath("//*[@class='dialog-list-title']/following-sibling::li["+ i +"]/label/input");
        }
        clickElementByXpath("//*[@id='dropdown-lightbox-filter']/button");
        clickElementByXpath("//*[@class='btn btn-red cardShadow3 get-result']");
        waitPageLoad();
        clickElementByXpath("//*[@class='list-basetab']/div/button[2]");

        // Asserts of Filters
        for(Filter myCase : Filter.values()) {

            switch (myCase){
                case city:
                    String city = selectByXpathBySelectedText("//*[@class='city']/select");
                    Assert.assertEquals(city, "İzmir" );
                    break;
                case county:
                    Assert.assertEquals(driver.findElement(By.xpath("//*[@class='location']//*[@class='select-content']/div")).getText(), "Bornova" );
                    break;
                case category:
                    Assert.assertEquals(driver.findElement(By.xpath("//*[@class='list']/li[2]/div/input/following-sibling::label")).getText(), "İşyeri" );
                    break;
                case priceRange:
                    Assert.assertEquals(driver.findElement(By.xpath("//*[@class='tag-swipe']/div/div/div/div[5]//following-sibling::p")).getText(), "100.000 TL" );
                    Assert.assertEquals(driver.findElement(By.xpath("//*[@class='tag-swipe']/div/div/div/div[4]//following-sibling::p")).getText(), "2.000.000 TL" );
                    break;
                case buildAge:
                    Assert.assertEquals(driver.findElement(By.xpath("//*[@class='tag-swipe']/div/div/div/div[3]//following-sibling::p")).getText(), "Sıfır Bina" );
                    Assert.assertEquals(driver.findElement(By.xpath("//*[@class='tag-swipe']/div/div/div/div[2]//following-sibling::p")).getText(), "1-5" );
                    Assert.assertEquals(driver.findElement(By.xpath("//*[@class='tag-swipe']/div/div/div/div[1]//following-sibling::p")).getText(), "6-10" );
                    break;

            }

        }

    }




    public void caseSecond(){
        driver.get(constant.getProperty("homePage"));
        waitPageLoad();

        if(driver.getCurrentUrl().equals(constant.getProperty("homePage"))){
            Assert.assertTrue(true);
        }

//        clickElementByXpath("//*[@class='btn btn-red btn-large']");
        clickElementByXpath("//*[@class='wrapper']/following-sibling::div/div/div[2]//*[@class='hemlak-icon left-icon']");
        clickElementByXpath("//div[@class='list-basetab']/div/button[2]");

        // Select Ankara
        selectByXpathByValue("//*[@class='city']/select", "ankara");
        clickElementByXpath("//*[@class='location']//*[@class='select-content']");

        //Select Çankaya
        fillInTheBlankByXpath("//*[@class='dialog-search-filter']/div/following-sibling::input[@class='district-search']", "Çankaya");
        clickElementByXpath("//div[@class='district-list-dialog']//*[@class='label-to-bold-if-checked']");
        clickElementByXpath("//*[@id='dropdown-lightbox-district']/button");
        clickElementByXpath("//*[@class='room-type']/div/following-sibling::section");
        clickElementByXpath("//*[@class='dialog-list-title']/following-sibling::li[3]/label/input");
        clickElementByXpath("//*[@id='dropdown-lightbox-filter']/button");

        // Select inside the site
        clickElementByXpath("//*[@class='within-site d-flex']/label/input");
        clickElementByXpath("//*[@class='btn btn-red cardShadow3 get-result']");
        sleep(2);
        String ownerPhone = driver.findElement(By.xpath("//div[3]//div[@class='card-nav']//*[@href='tel:+905325045618']")).getAttribute("href");
        System.out.println(ownerPhone);
        Assert.assertEquals(ownerPhone, "tel:+905325045618");

    }

}
