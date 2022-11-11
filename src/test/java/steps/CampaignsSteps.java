package steps;

import base.BaseSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static constants.CampaignsConstants.*;
import static helper.Helper.*;

public class CampaignsSteps extends BaseSteps {
    List<String> campaigns = new ArrayList<>();

    @Step
    @Given("^Campaigns page opens with a web browser$")
    public void campaignsPageOpensWithAWebBrowser() {
        geturl();
        String pageTitle = findElement(CAMPAIGNS_PAGE_TITLE).getText();
        Assertions.assertEquals(pageTitle,"Kampanyalar ve Promosyonlar"); // Sayfa başlığı Kampanyalar ve Promosyonlar ise herhangi bir sorun yok.
    }

    @Step
    @When("^Click on all categories on the campaigns page for promotion$")
    public void clickOnAllCategoriesOnTheCampaignsPageForPromotion() {
        String navButtonName;
        String sectionTitle;
        List<WebElement> campaignLinks;
        boolean elementControl;

        List<WebElement> menuElement = findElements(CATEGORY_MENU_TAB);

        int index = 1;
       for (WebElement categoryElement:menuElement) {
           navButtonName = categoryElement.getText();
           elementControl = elementIsDisabled(categoryElement);
           categoryElement.click();
           if (index <= menuElement.size() && !elementControl) {
               WebElement campaignsSection = findElement(By.xpath(String.format("//*[@id=\"contentCampaignPromotion\"]/div/div[2]/div/div[2]/div/section[%d]", index++)));
                sectionTitle = campaignsSection.getText();
                campaignLinks = campaignsSection.findElements(By.tagName("a"));
               if(Objects.equals(navButtonName, "Tümü")){
                   navButtonName = "Kampanyalar"; //Kampanya navbarının ilk butonunun başlığı "Tümü", butonun yönlendirdiği section'un başlığı Kampanyalar olduğu için eşitleme yaptık.
               } else if(Objects.equals(navButtonName, "pet11")){
                   navButtonName = "Yaşam & Etkinlik";//Kampanya navbarının ilk butonunun başlığı "Tümü", butonun yönlendirdiği section'un başlığı Kampanyalar olduğu için eşitleme yaptık.
               }
                //İlgili section'un linklerini csv için hazırladık.
                for (WebElement campaignLinkElement:campaignLinks){
                    String campaignContents = sectionTitle + " " + campaignLinkElement.getAttribute("href");
                    Assertions.assertEquals(sectionTitle,navButtonName); //section başlığı ve navbar buton başlığı eşit ise herhangi bir sorun yok.
                    campaigns.add(campaignContents);
                }
           }
       }
    }

    @Step
    @Then("^Promotion URLs under categories are saved in CSV format$")
    public void promotionURLsUnderCategoriesAreSavedInCSVFormat() throws IOException {
        boolean resultCampaignControl;
        CSVPrinter("campaigns",campaigns);
        resultCampaignControl = CSVAndTextIsMatch("campaigns",campaigns);
        Assertions.assertTrue(resultCampaignControl); //CSV dosyasına listedeki datalar kaydedilmiş mi? kontrolünü yaptık.
        DriverQuit();
    }
}
