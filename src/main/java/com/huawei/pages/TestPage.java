package com.huawei.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestPage extends BasePage{
    public TestPage(){
        PageFactory.initElements(getDriver(),this);
    }
    @FindBy(xpath = "//li[@data-id='testtask']/a[text() = '测试']")
    public WebElement testButton;

    @FindBy(xpath = "///a[@data-title = '添加区块']")
    public WebElement add_block;

    @FindBy(xpath = "//select[@name = 'moduleBlock']")
    public WebElement block_select;

    @FindBy(xpath = "//*[@id=\"title\"]")
    public WebElement block_name;

    @FindBy(xpath = "//*[@id=\"grid_chosen\"]/a")
    public WebElement block_locate;

    @FindBy(xpath = "//*[@id=\"paramstype_chosen\"]/a/span")
    public WebElement block_type;

    @FindBy(xpath = "//*[@id=\"paramsorderBy_chosen\"]/a/span")
    public WebElement block_orderby;

    @FindBy(xpath = "//button[@type = 'submit']")
    public WebElement block_save;

    @FindBy(xpath = "//button[@type = 'cancel']")
    public WebElement block_cancel;



}
