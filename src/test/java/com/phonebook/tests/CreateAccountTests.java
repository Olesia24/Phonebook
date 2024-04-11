package com.phonebook.tests;

import com.phonebook.data.UserData;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase{
    @BeforeMethod
    public void ensurePrecondition(){
        if (!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSognOutButton();
        }
    }

    @Test(enabled = false)
    public void createNewAccountPositiveTest(){
        //Random random = new Random();
        //int i = random.nextInt(1000)+1000;
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnRegistrationButton();
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void createNewAccountWithExistedEmailNegativeTest(){
        //click on Login link [href='/login']
        app.getUser().clickOnLoginLink();

        //enter email (always 3)
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("olesia@yahoo.com")
                .setPassword("Olesia$2024"));

        //click on registration button
        app.getUser().clickOnRegistrationButton();

        //assert Alert is present
        Assert.assertTrue(app.getUser().isAlertPresent());
    }
}
//1. - ID
//2. - className(бывает редко уникальным)
//2. - css (баботает быстрее чем xPath)
//3. - xPath
