package com.mxf.demoTest;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by 闵晓凤 on 2019/2/24.
 */
@CucumberOptions(
        features = {"D:\\Worplace\\Ideaworkplace\\IdeaProjects\\demo01\\src\\test\\resources\\features\\test.feature"},
        plugin = {"pretty",
                "html:target/site/cucumber-pretty",
                "rerun:target/rerun.txt",
                "json:target/cucumber1.json"
        },
        tags = {"@Automation"},
        glue = {"com.mxf.demoTest.cucumberDemo"})
public class RunTest extends AbstractTestNGCucumberTests{
}
