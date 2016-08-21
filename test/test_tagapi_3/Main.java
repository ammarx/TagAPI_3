/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_tagapi_3;

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
        System.out.println("\n\nSynicing file system with json...");
        API.syncVersions();

        String UsernameToUse = "Ammar_Ahmad";
        String VersionToUse = "1.8.9";
        String MemoryToUse = "2G";
        API.setMemory(MemoryToUse);
        API.downloadVersionManifest();
        API.downloadMinecraft(VersionToUse, false); //force download flag
        API.runMinecraft(UsernameToUse, VersionToUse);
        
    }

}
