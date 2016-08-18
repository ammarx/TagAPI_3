/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_tagapi_3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ammar
 */
public class Main {

    public static void main(String[] args) {
        tagapi_3.API_Interface API = new tagapi_3.API_Interface();
        
        System.out.println("Reading file system for versions installed:");
        for (Object installedVersionsList : API.getInstalledVersionsList()) {
            //read file system for versions installed.
            System.out.println(installedVersionsList);
            
        }
        
        /*
        System.out.println("\n\nReading json for versions installed:");
        for(Object installedVersionsList : API.getProfileInstalledVersionsList()) {
            //read json for versions inalled.
            System.out.println(installedVersionsList);
        
        }
        */
        
        System.out.println("\n\nSynicing file system with json...");
        API.syncVersions();
        
        String UsernameToUse = "Ammar_Ahmad";
        String VersionToUse = "1.7.2";
        String MemoryToUse = "2G";
        API.setMemory(MemoryToUse);
        API.runMinecraft(UsernameToUse, VersionToUse);
       
        
        //so the downloads is fine... that means running has issies..
        //API.downloadVersionManifest();
        //API.downloadMinecraft("1.10.2-forge1.10.2-12.18.1.2011");
    }

}
