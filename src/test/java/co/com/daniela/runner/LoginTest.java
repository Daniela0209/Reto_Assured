package co.com.daniela.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = true,
        features = {"src/test/resources/features/login.feature"},
        glue = {"co.com.daniela.stepdefinition.LoginStepDefinitions"},
        tags = " "
)
public class LoginTest {
}
