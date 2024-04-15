package com.phonebook.tests;

import com.phonebook.data.ContactData;
import com.phonebook.data.UserData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import com.phonebook.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTests extends TestBase{
    @BeforeMethod
    public void ensurePrecondition(){
        if (!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSognOutButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnLoginButton();
    }
    @Test
    public void addContactPositiveTest(){
        app.getContact().clickOnAddLink();
        app.getContact().fillAddContactForm(new Contact()
                .setName(ContactData.NAME)
                .setLastName(ContactData.LAST_NAME)
                .setPhone(ContactData.PHONE)
                .setEmail(ContactData.CONTACT_EMAIL)
                .setAddress(ContactData.ADDRESS)
                .setDescription(ContactData.DESC));
        app.getContact().clickOnSafeButton();
        Assert.assertTrue(app.getContact().isContactCreated("Olesia"));
    }
    @AfterMethod
    public void postCondition(){
        app.getContact().removeContact();
    }

    @Test(dataProvider = "addNewContact", dataProviderClass = DataProviders.class)
    public void addContactPositiveTestFromDataProvider(String name, String lastName,
                                                       String phone, String email,
                                                       String address, String description){
        app.getContact().clickOnAddLink();
        app.getContact().fillAddContactForm(new Contact()
                .setName(name)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description));
        app.getContact().clickOnSafeButton();
        Assert.assertTrue(app.getContact().isContactCreated(name));
    }

    @Test(dataProvider = "addNewContactFromCsvFile", dataProviderClass = DataProviders.class)//это в дз
    public void addContactPositiveTestFromDataProviderWithCsvFile
            (Contact contact){
        app.getContact().clickOnAddLink();
        app.getContact().fillAddContactForm(contact);
        app.getContact().clickOnSafeButton();
        Assert.assertTrue(app.getContact().isContactCreated(contact.getName()));
    }
}

