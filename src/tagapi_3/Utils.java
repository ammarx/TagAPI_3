/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tagapi_3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author ammar
 */
class Utils {

    //String versions_linux = getMineCraftLocation("Linux") + "/versions";
    public String getMineCraftLocation(String OS) {
        if (OS.equals("Windows")) {
            return (System.getenv("APPDATA") + "/.minecraft");
        }
        if (OS.equals("Linux")) {
            return (System.getProperty("user.home") + "/.minecraft");
        }
        if (OS.equals("Mac")) {
            return (System.getProperty("user.home") + "/Library/Application Support/minecraft");
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

    public String getMineCraft_X_json(String OS, String Username) {

        return (getMineCraftLocation(OS) + "/" + Username + ".json");

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

    public void jarExtract(String OS, String _jarFile, String destDir) {
        try {
            Utils utils = new Utils();
            _jarFile = utils.setMineCraft_Versions_X_NativesLocation(OS, _jarFile);
            //_jarFile = _jarFile.replace("https://libraries.minecraft.net", "/home/ammar/NetBeansProjects/TagAPI_3/testx/libraries");
            File dir = new File(destDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File jarFile = new File(_jarFile);

            java.util.jar.JarFile jar = new java.util.jar.JarFile(jarFile);
            java.util.Enumeration enumEntries = jar.entries();
            while (enumEntries.hasMoreElements()) {
                java.util.jar.JarEntry file = (java.util.jar.JarEntry) enumEntries.nextElement();
                java.io.File f = new java.io.File(destDir + java.io.File.separator + file.getName());
                if (file.isDirectory()) { // if its a directory, create it
                    f.mkdirs();
                    continue;
                }
                if (!f.exists()) {
                    java.io.InputStream is = jar.getInputStream(file); // get the input stream
                    java.io.FileOutputStream fos = new java.io.FileOutputStream(f);
                    while (is.available() > 0) {  // write contents of 'is' to 'fos'
                        fos.write(is.read());
                    }
                    fos.close();
                    is.close();

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getMineCraftAssetsVirtualLocation(String OS) {
        return (getMineCraftAssetsLocation(OS) + "/virtual");
    }

    public String getMineCraftAssetsVirtualLegacyLocation(String OS) {
        return (getMineCraftAssetsVirtualLocation(OS) + "/legacy");
    }

    public void copyToVirtual(String OS, String folder, String _hash, String virtualfolder) {
        try {
            Utils utils = new Utils();
            File source = new File(utils.getMineCraftAssetsObjectsLocation(OS) + "/" + folder + "/" + _hash);
            File dest = new File(utils.getMineCraftAssetsVirtualLegacyLocation(OS) + "/" + virtualfolder.replaceFirst("minecraft/", ""));
            //Files.copy(source.toPath(), dest.toPath());
            FileUtils.copyFile(source, dest);

        } catch (Exception ex) {
            System.out.println("File Exists! " + ex.getMessage());
        }

    }

    public String getOS() {
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        
        if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
            return ("Mac");
        } else if (OS.indexOf("win") >= 0) {
            return ("Windows");
        } else if (OS.indexOf("nux") >= 0) {
            return ("Linux");
        } else {
            //bring support to other OS.
            //we will assume that the OS is based on linux.
            return ("Linux");
        }
    }
    
    public String memory = null;
    
    public void setMemory(String memory_) {
        memory = memory_;
    }

    public String getMemory()
    {
        return memory;
    }
}
