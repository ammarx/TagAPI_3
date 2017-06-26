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
        for (Object installableVersionsList : API.getInstallableVersionsList()) {
            System.out.println(installableVersionsList); // can be split using " % "
        }
        */
        
        
        /*
        System.out.println("\n\nSynicing file system with json...");
        API.syncVersions();

        String UsernameToUse = "Ammar_Ahmad";
        String VersionToUse = "1.8.9";
        int MemoryToUse = 1024;
        API.setMemory(MemoryToUse);
        API.downloadVersionManifest();
        API.downloadMinecraft(VersionToUse, false); //force download flag
        API.runMinecraft(UsernameToUse, VersionToUse);
        */
        List ip = new ArrayList(API.getServersIPList());
        List name = new ArrayList(API.getServersNameList());
        if (ip.isEmpty()) {
            //add server
        }
        for (Object obj : ip) {
            System.out.println(obj);
        }
        for (Object obj : name) {
            System.out.println(obj);
        }
        
        API.addServerToServersDat("ammar1", "ammarx.tagcraftmc.com");
        
    }

}
