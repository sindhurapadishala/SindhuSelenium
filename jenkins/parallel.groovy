@Library('JenkinsFileCommon')
import groovy.json.*

def actCommonLib = new com.actifio.JenkinsfileCommon()
actCommonLib.RunPrint()

def reposToTag = [:]

// Below are the Stages for this Jenkins Job, each operation is wrapped in a stage()

timestamps {
    CURRENT_JOB = env.JOB_NAME
    JOBSTATUS = "SUCCESS"
    actifio_build = "unknown"

    try {
        node(selenium_controller) {

            // get the controller all set up
            stage("Controller_prep") {

                print "Workspace on slave is ${env.WORKSPACE}"
                try {
                    bat("if exist agm-selenium rmdir /Q /S agm-selenium")
                }
                catch (err) {
                    print("Command to delete directory failed")
                }

                bat("git clone -b master https://git.build.actifio.com/testing_tools/agm-selenium.git")
            } // end Prep stage


            stage('Build') {

                dir("${env.WORKSPACE}\\agm-selenium") {
                    def status
//                    def ip = "${Dagm_ip}"
//                    print "AGM IP Is " + ip
//                    before_upgrade = getBuildNumber(ip)
                    try {
                        if (Dupgrade_path != null) {
                            print "Upgrade path is not null"
                            status = bat(returnStatus: true, script: "mvn install -Dmyproperty=${Dmyproperty} -Dsurefire.suiteXmlFiles=./src/test/resources/${Select_Suite}.xml " +
                                    "-Dupgrade_path=${Dupgrade_path} -Dagm_ip=${Dagm_ip} -Dbrowser=${Dbrowser}")
                        } else {
                            print "Upgrade path is null"
                            status = bat(returnStatus: true, script: "mvn install -Dmyproperty=${Dmyproperty} -Dsurefire.suiteXmlFiles=./src/test/resources/${Select_Suite}.xml " +
                                    "-Dbrowser=${Dbrowser}")
                        }
                    } catch (Exception e) {
                        print "Exception occured"
                        status = bat(returnStatus: true, script: "mvn install -Dmyproperty=${Dmyproperty} -Dsurefire.suiteXmlFiles=./src/test/resources/${Select_Suite}.xml " +
                                "-Dbrowser=${Dbrowser}")
                    } finally {
                        print "In finally ${JOBSTATUS}"
                        print "Exit Code: " + status
                        if (status == 1) {
                            JOBSTATUS = "FAILED"
                        }
                        print "After If status ${JOBSTATUS}"
                        print "After code status 1 "
                        bat("powershell -file ${env.WORKSPACE}\\agm-selenium\\src\\test\\resources\\agm_job_stats.ps1 ${env.JOB_NAME} ${env.BUILD_NUMBER} ${ROBOT_CONTROLLER} ${env.WORKSPACE}")
                        // publishHTMLReports(ExtentReport)
                        publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'target/', reportFiles: 'log.html', reportName: 'Extent Report', reportTitles: ''])

                        //SendStatusEmail()
                      //  after_upgrade = getBuildNumber(ip)
                        println "Recipients of job are ${EMAIL_TO}"
                        emailext(body: "AGM Smokes Result is ${JOBSTATUS} " +
                                "and find the details here https://derivative.build.actifio.com/job/agm/job/sp_10.0.2/job/smokes_upgrade_build2build/Extent_20Report/",
                                mimeType: 'text/html',
                                replyTo: 'gv.chaitanya@actifio.com',
                                //subject: ' ${DEFAULT_SUBJECT}',
                                //subject: "AGM Result from Build ${before_upgrade} to ${after_upgrade} is ${JOBSTATUS}",
                                subject: "AGM Result from Build is ${JOBSTATUS}",
                                to: "${EMAIL_TO}",
                                //the from: doesn't work, emailext doesn't support it
                                from: "The Elder <wtf@actifio.com>")
                    }
                    // create log directory and move testNg xml results to the robot controller
                    bat("powershell -file ${env.WORKSPACE}\\agm-selenium\\src\\test\\resources\\agm_job_stats.ps1 ${env.JOB_NAME} ${env.BUILD_NUMBER} ${ROBOT_CONTROLLER} ${env.WORKSPACE}")

                    //parse_xml_file and get job id
                    jobid = restGet("http://${ROBOT_CONTROLLER}:5003/agm/parseresults?jobname=${env.JOB_NAME}&buildnumber=${env.BUILD_NUMBER}");
                    failed_status = restGet("http://${ROBOT_CONTROLLER}:5003/jenkins/isfailed?jobid=${jobid}");
                    if(failed_status == 'true') {
                        print("job status : ${failed_status}")
                        currentBuild.result = "UNSTABLE"
                    }
                    // publishHTMLReports(ExtentReport)
                    // publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'target/', reportFiles: 'log.html', reportName: 'Extent Report', reportTitles: ''])
                }

                step([$class   : 'LinkStats',
                      job_stats: "http://${ROBOT_CONTROLLER}:5003/job_stats?buildname=${env.JOB_NAME}&buildnumber=${env.BUILD_NUMBER}",
                      rerun    : '']);
                print "After maven script execution"


            }
            // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
        }

    }  /// close catch
    catch (Exception e) {
        print "In Catch result failed ${e}"
        currentBuild.result = "FAILURE"

    } // close catch


}

def getBuildNumber(agmip){
    node{
        def remote = [:]
        remote.name = 'agm_name'
        remote.host = agmip
        remote.user = 'root'
        remote.password = 'actifio2'
        remote.port = 26
        remote.allowAnyHosts = true
        op = ""
        result = sshCommand remote: remote, command: "/act/bin/udsinfo lsversion"
        println result
        op_split = result.split('\n')
        op = op_split[1].split(" ")[0]

        println "Build Number : " + op
        //sshCommand remote: remote, command: "for i in {1..5}; do echo -n \"Loop \$i \"; date ; sleep 1; done"

    }
    return op
}

def SendStatusEmail() {
    def actCommonLib = new com.actifio.JenkinsfileCommon()
    def emailResult = actCommonLib.GetEmailResult()
    def emailRecipients = "gv.chaitanya@actifio.com, kesaribai.uttarkar@actifio.com, sindhura.padishala@actifio.com, " +
            "sowjanya.bibinagar@actifio.com, vasanthi.dadi@actifio.com, venkat.parvathina@actifio.com, " +
            "rahul.agrawal@actifio.com"
    print "Current email recipients: "
    print emailRecipients
    actCommonLib.SendDefaultEmail(emailRecipients, emailResult)

} // close SendStatusEmail


def PowerShell(psCmd) {
    psCmd = psCmd.replaceAll("%", "%%")
    bat "powershell.exe -NonInteractive -ExecutionPolicy Bypass -Command \"\$ErrorActionPreference='Stop';[Console]::OutputEncoding=[System.Text.Encoding]::UTF8;$psCmd;EXIT \$global:LastExitCode\""
}

def publishHTMLReports(reportName) {
    // Publish HTML reports (HTML Publisher plugin)
    publishHTML([allowMissing         : false,
                 alwaysLinkToLastBuild: true,
                 keepAll              : true,
                 reportDir            : 'target\\',
                 reportFiles          : 'log.html',
                 reportName           : reportName])
}


// Functions for HTTP Requests

def restGet(endpoint)
{
    // Returns raw response from a get call
    // param - endpoint : full url of api call, e.g. 'http://172.27.36.205:5003/jenkins/test?username=joe'
    print "GET: ${endpoint}";

    def string = endpoint.toURL().getText();

    // Remove quotes around string, and whitespace at end and return
    string = string.replaceAll("^\"|\"\$", "");
    return string.trim()
} // close restGet