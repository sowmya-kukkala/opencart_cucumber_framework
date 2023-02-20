package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(

        features= {".//Features//Login.feature"},
		//features= {".//Features//LoginDDT.feature"},
	    //features= {".//Features//LoginDDTExcel.feature"},
	    //features = {".//Features//Login.feature", ".//Features//AccountRegistration.feature"},
	    //features = "@target/rerun.txt", // Runs only Failures
        glue="stepDefinitions",
        plugin= {
        		"pretty", "html:reports/myreport.html", 
        		"json:reports/myreport.json",
        		"rerun:target/rerun.txt"},    //Mandatory to capture failures
        dryRun=false, 			// set to true - To see any unimplemented method and to run the test in the background 
        monochrome=true,
        tags = "@regression"	//Scenarios tagged with @sanity,
        //tags = "@sanity and @regression", // Scenarios tagged with @sanity and @regression
        //tags = "@sanity or @regression", // Scenarios tagged with @sanity or @regression
        //tags = "@sanity" and not @regression",  // Scenarios tagged with @sanity but not tagged with @regression
      )

public class TestRunner {

}
