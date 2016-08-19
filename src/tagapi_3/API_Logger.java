/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tagapi_3;

/**
 *
 * @author ammar
 */
class API_Logger {

    private static String downloadLogs;
    private static String runLogs;
    private static String lastErrorLogs;
    
    static void resetDownloadLogs()
    {
        downloadLogs = "";
    }
    
    static void resetRunLogs()
    {
        runLogs = "";
    }
    
    static void resetlastErrorLogs()
    {
        lastErrorLogs = "";
    }
    
    static String getDownloadLogs()
    {
        return "[dl] " + downloadLogs; //download logs
    }
    
    static String getRunLogs() 
    {
        return "[rl] " + runLogs; //run logs
    }
    
    static String getLastErrorLogs()
    {
        return "[el] " + lastErrorLogs; //error logs
    }
    
    static void setDownloadLogs(String downloadLogs)
    {
        API_Logger.downloadLogs = downloadLogs;
    }
    
    static void setRunLogs(String runLogs) 
    {
        API_Logger.runLogs = runLogs;
    }
    
    static void setLastErrorLogs(String lastErrorLogs)
    {
        API_Logger.lastErrorLogs = lastErrorLogs;
    }
    
    
}
