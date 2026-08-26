Pre-requisites
==============
To run the suites, 3 things are primarily required
  1. Inventory information (inv file) - it is a java properties file contains information like AGM IP, Sky, Host and other
   inventory information on which the suite will run
   path location - agm-selenium/src/test/java/com.lazerycode.selenium/inv/
  2. Suites to run (xml file) - it is xml file with testng format contains suites to run
   path location - agm-selenium/src/test/resources/
  3. Browser(Default - chrome) - chrome/firefox/edge/ie

Command to run the suite
========================
  - There are various ways to trigger the suite. Any how here is one of the ways:
        - You need to provide inv file and xml suite name to run the tests which you created above. 
        - Here is the sample command, this can be triggered from editor, command prompt, bat file or jenkins
          -mvn install -Dmyproperty=trunk_jenkins_config.properties 
                -Dsurefire.suiteXmlFiles=./src/test/resources/unitTest.xml -Dbrowser=chrome
           
Where to find the logs ?
=======================
    - agm-selenium/target/log.html           
           
                                