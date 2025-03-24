package com.swaglabs.utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {

    public static final String Allure_Result_Bath = "test-outputs/allure-results";

    private AllureUtils(){
        super();
    }
    public static void AttachLogsToAllureReport(){
        try {
             File logfile = FilesUtils.getLatestFile(LogsUtils.logsPath);
            assert logfile != null;
            if(!logfile.exists()){
                 LogsUtils.warn("Log file does not exist :" + LogsUtils.logsPath );
                 return;
             }
            Allure.addAttachment("Logs.log" , Files.readString(Path.of(logfile.getPath())));
        } catch (Exception e) {
            LogsUtils.error("Failed to attach log to Allure report :" + e.getMessage());
        }


    }
    public static void AttachScreenshotToAllureReport(String screenshotName , String screenshotPath){
        try {
            Allure.addAttachment(screenshotName , Files.newInputStream(Path.of(screenshotPath)));

        } catch (Exception e) {
            LogsUtils.error("Failed to attach screenshot to Allure report :" + e.getMessage());
        }

    }

}
