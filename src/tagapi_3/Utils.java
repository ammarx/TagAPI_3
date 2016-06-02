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
        if (OS.equals("Mac")) {
            return (System.getProperty("user.home") + "/.minecraft");
        }
        return "N/A";
    }

    public String getMineCraftVersionsLocation(String OS) {
        return (getMineCraftLocation(OS) + "/versions");

    }

    public String getMineCraftLibrariesLocation(String OS) {
        return (getMineCraftLocation(OS) + "/libraries");

    }

    public String getMineCraft_Version_Manifest_json(String OS) {
        return (getMineCraftLocation(OS) + "/version_manifest.json");

    }

    public String getMineCraft_Version_Json(String OS, String VersionNumber) {
        return (getMineCraftVersionsLocation(OS) + "/" + VersionNumber + "/" + VersionNumber + ".json");

    }

    public String getMineCraft_Versions_X_X_json(String OS, String VersionNumber) {
        return (getMineCraftVersionsLocation(OS) + "/" + VersionNumber + "/" + VersionNumber + ".json");

    }

    public String getMineCraft_Versions_X_X_jar(String OS, String VersionNumber) {
        return (getMineCraftVersionsLocation(OS) + "/" + VersionNumber + "/" + VersionNumber + ".jar");

    }

    public String getMineCraft_Versions_X_Natives(String OS, String VersionNumber) {
        return (getMineCraftVersionsLocation(OS) + "/" + VersionNumber + "/natives");

    }

    public String getMineCraftAssetsIndexes_X_json(String OS, String VersionNumber) {

        return (getMineCraftAssetsIndexesLocation(OS) + "/" + VersionNumber + ".json");
    }

    public String getMineCraftAssetsIndexesLocation(String OS) {
        return (getMineCraftAssetsLocation(OS) + "/indexes");

    }

    public String getMineCraftAssetsLocation(String OS) {
        return (getMineCraftLocation(OS) + "/assets");

    }

    public String getMineCraftAssetsObjectsLocation(String OS) {
        return (getMineCraftAssetsLocation(OS) + "/objects");
    }

    public String setMineCraft_Versions_X_NativesLocation(String OS, String _path) {
        Utils utils = new Utils();
        return (utils.getMineCraftLibrariesLocation(OS) + "/" + _path);

    }

    public String setMineCraft_librariesLocation(String OS, String _path) {
        Utils utils = new Utils();
        return (utils.getMineCraftLibrariesLocation(OS) + "/" + _path);
    }

    public String getArgsDiv(String OS) {
        if (OS.equals("Windows")) {
            return (";");
        }
        if (OS.equals("Linux")) {
            return (":");
        }
        if (OS.equals("Mac")) {
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
