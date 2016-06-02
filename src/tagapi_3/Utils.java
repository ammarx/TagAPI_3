/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tagapi_3;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author ammar
 */
public class Utils {

    //String versions_linux = getMineCraftLocation("Linux") + "/versions";
    public String getMineCraftLocation(String OS) {
        if (OS.equals("Windows")) {
            return (System.getenv("APPDATA") + "/.minecraft");

        }
        if (OS.equals("Linux")) {
            return (System.getProperty("user.home") + "/.minecraft");
        }

        return "N/A";
    }

    public String getMineCraftVersionsLocation(String OS) {
        if (OS.equals("Windows")) {
            return (getMineCraftLocation("Windows") + "/versions");

        }
        if (OS.equals("Linux")) {
            return (getMineCraftLocation("Linux") + "/versions");
        }

        return "N/A";
    }

    public String getMineCraftLibrariesLocation(String OS) {
        if (OS.equals("Windows")) {
            return (getMineCraftLocation("Windows") + "/libraries");

        }
        if (OS.equals("Linux")) {
            return (getMineCraftLocation("Linux") + "/libraries");
        }

        return "N/A";
    }

    public String getMineCraft_Version_Manifest_json(String OS) {
        if (OS.equals("Windows")) {
            return (getMineCraftLocation("Windows") + "/version_manifest.json");

        }
        if (OS.equals("Linux")) {
            return (getMineCraftLocation("Linux") + "/version_manifest.json");
        }

        return "N/A";
    }

    public String getMineCraft_Version_Json(String OS, String VersionNumber) {
        if (OS.equals("Windows")) {
            return (getMineCraftVersionsLocation("Windows") + "/" + VersionNumber + "/" + VersionNumber + ".json");

        }
        if (OS.equals("Linux")) {
            return (getMineCraftVersionsLocation("Linux") + "/" + VersionNumber + "/" + VersionNumber + ".json");

        }

        return "N/A";
    }

    public String getMineCraft_Versions_X_X_json(String OS, String VersionNumber) {
        if (OS.equals("Windows")) {
            //the commented line is not required
            //File dir = new File(getMineCraftVersionsLocation("Windows") + "/" + VersionNumber); dir.mkdirs();
            return (getMineCraftVersionsLocation("Windows") + "/" + VersionNumber + "/" + VersionNumber + ".json");

        }
        if (OS.equals("Linux")) {
            //the commented line is not required
            //File dir = new File(getMineCraftVersionsLocation("Linux") + "/" + VersionNumber); dir.mkdirs();
            return (getMineCraftVersionsLocation("Linux") + "/" + VersionNumber + "/" + VersionNumber + ".json");

        }

        return "N/A";
    }

    public String getMineCraft_Versions_X_X_jar(String OS, String VersionNumber) {
        if (OS.equals("Windows")) {
            //the commented line is not required
            //File dir = new File(getMineCraftVersionsLocation("Windows") + "/" + VersionNumber); dir.mkdirs();
            return (getMineCraftVersionsLocation("Windows") + "/" + VersionNumber + "/" + VersionNumber + ".jar");

        }
        if (OS.equals("Linux")) {
            //the commented line is not required
            //File dir = new File(getMineCraftVersionsLocation("Linux") + "/" + VersionNumber); dir.mkdirs();
            return (getMineCraftVersionsLocation("Linux") + "/" + VersionNumber + "/" + VersionNumber + ".jar");

        }

        return "N/A";
    }

    public String getMineCraft_Versions_X_Natives(String OS, String VersionNumber) {
        if (OS.equals("Windows")) {
            return (getMineCraftVersionsLocation("Windows") + "/" + VersionNumber + "/natives");

        }
        if (OS.equals("Linux")) {
            return (getMineCraftVersionsLocation("Linux") + "/" + VersionNumber + "/natives");

        }

        return "N/A";
    }

    public String getMineCraftAssetsIndexes_X_json(String OS, String VersionNumber) {
        if (OS.equals("Windows")) {
            return (getMineCraftAssetsIndexesLocation("Windows") + "/" + VersionNumber + ".json");

        }
        if (OS.equals("Linux")) {
            return (getMineCraftAssetsIndexesLocation("Linux") + "/" + VersionNumber + ".json");
        }

        return "N/A";
    }

    public String getMineCraftAssetsIndexesLocation(String OS) {
        if (OS.equals("Windows")) {
            return (getMineCraftAssetsLocation("Windows") + "/indexes");

        }
        if (OS.equals("Linux")) {
            return (getMineCraftAssetsLocation("Linux") + "/indexes");
        }

        return "N/A";
    }

    public String getMineCraftAssetsLocation(String OS) {
        if (OS.equals("Windows")) {
            return (getMineCraftLocation("Windows") + "/assets");

        }
        if (OS.equals("Linux")) {
            return (getMineCraftLocation("Linux") + "/assets");
        }

        return "N/A";
    }

    public String getMineCraftAssetsObjectsLocation(String OS) {
        if (OS.equals("Windows")) {
            return (getMineCraftAssetsLocation("Windows") + "/objects");

        }
        if (OS.equals("Linux")) {
            return (getMineCraftAssetsLocation("Linux") + "/objects");
        }

        return "N/A";
    }

    //CHANGE THIS FUNCTION!...
    public String setMineCraft_Versions_X_NativesLocation(String OS, String _path) {
        //Network network = new Network();
        Utils utils = new Utils();
        if (OS.equals("Windows")) {
            return (utils.getMineCraftLibrariesLocation(OS) + "/" + _path);

        }
        if (OS.equals("Linux")) {
            return (utils.getMineCraftLibrariesLocation(OS) + "/" + _path);

        }

        return "N/A";
    }

    //CHANGE THIS FUNCTION!...
    public String setMineCraft_librariesLocation(String OS, String _path) {
        //Network network = new Network();
        Utils utils = new Utils();
        if (OS.equals("Windows")) {
            return (utils.getMineCraftLibrariesLocation(OS) + "/" + _path);

        }
        if (OS.equals("Linux")) {
            return (utils.getMineCraftLibrariesLocation(OS) + "/" + _path);

        }

        return "N/A";
    }

    public String getArgsDiv(String OS) {
        if (OS.equals("Windows")) {
            return (";");
        }
        if (OS.equals("Linux")) {
            return (":");
        }

        return "N/A";
    }

    @SuppressWarnings("empty-statement")
    public String getSHA_1(String _path) {

        try {
            
            MessageDigest md = MessageDigest.getInstance("SHA1");
            FileInputStream fis = new FileInputStream(_path);
            byte[] dataBytes = new byte[1024];

            int nread;

            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            };

            byte[] mdbytes = md.digest();

            //convert the byte to hex format
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            return (sb.toString());

        } catch (NoSuchAlgorithmException | IOException ex) {
            System.out.println(ex);
            return "N/A";
        }

    }
}
