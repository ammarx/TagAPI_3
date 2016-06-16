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
        for (Object installedVersionsList : API.getInstalledVersionsList()) {
            System.out.println(installedVersionsList);
            
        }

        String UsernameToUse = "Ammar_Ahmad";
        String VersionToUse = "1.7.2";
        API.runMinecraft(UsernameToUse, VersionToUse);
    }

}
