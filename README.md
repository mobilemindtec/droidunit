Droid Unit
==========

The project Droid Unit is designed to provide a simple and fast way to build unit tests in Android development environment. Currently, it is an extremely simple design, which has the unique purpose of unit testing the level of business rule, or consistency of code, without giving importance to tests with graphical interface.

Usage
Maven

        
       <repositories>
        <repository>
            <id>mobile-mind-droid-nexus</id>
            <name>vineetmanohar</name>
            <url>http://www.mobilemind.com.br/nexus/content/repositories/mobile-mind-droid</url>
            <releases>
                <enabled>true</enabled>
            </releases>

            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
       </repositories>

        <dependencies>
        <dependency>
            <groupId>br.com.mobilemind.api</groupId>
            <artifactId>mobilemind-droid-unit</artifactId>
            <version>1.3</version>
        </dependency>
        </dependencies>

Create Test Case

The test method should start with 'test'.

    package br.com.mobilemind.test
    
    import br.com.mobilemind.api.droidunit.Assert;
    import br.com.mobilemind.api.droidunit.TestBehaviorImpl;

    /**
     * Mobile Mind
     * 
     * 
     * @author Ricardo Bocchi
     */
    public class GoogleCodeTestCase extends TestBehaviorImpl{
        
        //start with test
        public void testEquals(){
            String wordOne = "Hi!";
            String wordTwo = "Oh!";
            
            Assert.areEqual(wordOne, wordTwo, "words must be equals?!");        
        }
    
        //start with test
        public void testGreater(){
            
            say("god test! very easy");
            
            Assert.isGreater(10, 5, "bad behaviour!");  
        }
    }

Mapping Expected Exceptions

    Assert.shouldThrow(IOException.class, new ExceptionDelegate() {
    
          public void execute() throws Exception {
             //code that throws exception expected
          }
      });

Configure in main Activity

    boolean testUniteEnable = true; 
    if(testUniteEnable){
      TestSuite.addTestCase(br.com.mobilemind.test.GoogleCodeTestCase.class);
      final Intent it = new Intent(this, TestResultWebView.class);
      startActivity(it);
    }

Configure in AndroidManifest

        
    <activity android:name="br.com.mobilemind.api.droidunit.TestResultWebView" 
              android:configChanges="keyboardHidden|orientation"/>
