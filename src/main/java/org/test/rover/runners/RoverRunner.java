package org.test.rover.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(
		tags = {"@MarsRoverTest"},
        features = "src/test/resources/FeatureFiles/MarsRoverTest.feature",
        glue={"org.test.rover.stepDefinitions"}
        )
 
public class RoverRunner {
 
}