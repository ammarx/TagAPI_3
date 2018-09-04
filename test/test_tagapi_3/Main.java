/*
 *    MIT License

 *    Copyright (c) 2018 Ammar Ahmad

 *    Permission is hereby granted, free of charge, to any person obtaining a copy
 *    of this software and associated documentation files (the "Software"), to deal
 *    in the Software without restriction, including without limitation the rights
 *    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *    copies of the Software, and to permit persons to whom the Software is
 *    furnished to do so, subject to the following conditions:

 *    The above copyright notice and this permission notice shall be included in all
 *    copies or substantial portions of the Software.

 *    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *    SOFTWARE.
 */

package test_tagapi_3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ammar Ahmad
 */
public class Main {

    public static void main(String[] args) {
        tagapi_3.API_Interface API = new tagapi_3.API_Interface();
        final String serverIP = "dev.tagcraftmc.com";
        List ip = new ArrayList(API.getServersIPList());
        if (!ip.contains(serverIP) || ip.isEmpty()) {
            API.addServerToServersDat("TagcraftMC", serverIP);
        }
        
        
        System.out.println("Reading file system for versions installed:");
        for (Object installedVersionsList : API.getInstalledVersionsList()) {
            //read file system for versions installed.
            System.out.println(installedVersionsList);

        }
        for (Object installableVersionsList : API.getInstallableVersionsList()) {
            System.out.println(installableVersionsList); // can be split using " % "
        }
        
        
        System.out.println("\n\nSynicing file system with json...");
        API.syncVersions();

        String UsernameToUse = "Ammar_Ahmad";
        String VersionToUse = "1.13.1";
        int MemoryToUse = 1024;
        API.setMemory(MemoryToUse);
        
        //API.downloadVersionManifest(); //disabled
        API.downloadMinecraft(VersionToUse, false); //force download flag //disabled
        //API.injectNetty();
        API.runMinecraft(UsernameToUse, VersionToUse, false, false);
        
    }

}
