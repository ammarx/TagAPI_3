/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tagapi_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ammar
 */
public class Local {

    List versions_json_path_list = new ArrayList(); //gets the path of all json files
    List versions_list = new ArrayList();           //just gets the versions available on the system
    List version_url_list = new ArrayList();        //gets url of all the libraries
    List HALF_URL_version_url_list = new ArrayList();// this is the half url. it needs to be fixed first in order to be used
    
    
    List version_path_list = new ArrayList();       //%new added... This is for direct paths
    List version_name_list = new ArrayList();       //%new added... This is for direct names

    List objects_hash = new ArrayList();            //gets objects hash
    List objects_KEY = new ArrayList();             //gets objects keys

    List version_url_list_natives = new ArrayList();    //gets url of all the natives
    List version_path_list_natives = new ArrayList();    //%gets url of all the natives

    List libraries_path = new ArrayList();          //gets path to all the libraries
    //List natives_path = new ArrayList();            //_NOT NEEDED_ gets path to all the natives

    List version_manifest_versions_id = new ArrayList();
    List version_manifest_versions_type = new ArrayList();
    List version_manifest_versions_url = new ArrayList();

    public void readJson_versions_id(String path) {
        JSONParser readMCJSONFiles = new JSONParser();
        try {
            Object object = readMCJSONFiles.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray versions = (JSONArray) jsonObject.get("versions");
            Iterator<JSONObject> iterator = versions.iterator();
            while (iterator.hasNext()) {
                JSONObject versions_ = (JSONObject) iterator.next();
                version_manifest_versions_id.add(versions_.get("id"));
                //System.out.println(versions_.get("id"));
            }
        } catch (IOException | ParseException e) {
            //System.out.print(e);
        }
    }

    public void readJson_versions_type(String path) {
        JSONParser readMCJSONFiles = new JSONParser();
        try {
            Object object = readMCJSONFiles.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray versions = (JSONArray) jsonObject.get("versions");
            Iterator<JSONObject> iterator = versions.iterator();
            while (iterator.hasNext()) {
                JSONObject versions_ = (JSONObject) iterator.next();
                version_manifest_versions_type.add(versions_.get("type"));
                //System.out.println(versions_.get("id"));
            }
        } catch (IOException | ParseException e) {
            //System.out.print(e);
        }
    }

    public void readJson_versions_url(String path) {
        JSONParser readMCJSONFiles = new JSONParser();
        try {
            Object object = readMCJSONFiles.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray versions = (JSONArray) jsonObject.get("versions");
            Iterator<JSONObject> iterator = versions.iterator();
            while (iterator.hasNext()) {
                JSONObject versions_ = (JSONObject) iterator.next();
                version_manifest_versions_url.add(versions_.get("url"));
                //System.out.println(versions_.get("id"));
            }
        } catch (IOException | ParseException e) {
            //System.out.print(e);
        }
    }

    public void readJson_libraries_name(String path) {
        JSONParser readMCJSONFiles = new JSONParser();
        try {
            Object object = readMCJSONFiles.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray versions = (JSONArray) jsonObject.get("libraries");
            Iterator<JSONObject> iterator = versions.iterator();
            while (iterator.hasNext()) {
                JSONObject name_ = (JSONObject) iterator.next();
                version_name_list.add(name_.get("name"));
            }
        } catch (IOException | ParseException e) {
            //System.out.print(e);
        }
    }

    public void readJson_libraries_downloads_artifact_url(String path) {

        JSONParser readMCJSONFiles = new JSONParser();
        try {
            Object object = readMCJSONFiles.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray msg = (JSONArray) jsonObject.get("libraries");
            Iterator<JSONObject> iterator = msg.iterator();
            while (iterator.hasNext()) {
                JSONObject lib = (JSONObject) iterator.next();
                JSONObject downloads = (JSONObject) lib.get("downloads");
                if (downloads.get("artifact") != null) {
                    JSONObject artifact = (JSONObject) downloads.get("artifact");
                    if (artifact.get("url") != null) {
                        String url = (String) artifact.get("url");
                        version_url_list.add(url);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            //System.out.print(e);
        }

    }

    //EXP CODE! 
    public void readJson_libraries_downloads_artifact_path(String path) {

        JSONParser readMCJSONFiles = new JSONParser();
        try {
            Object object = readMCJSONFiles.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray msg = (JSONArray) jsonObject.get("libraries");
            Iterator<JSONObject> iterator = msg.iterator();
            while (iterator.hasNext()) {
                JSONObject lib = (JSONObject) iterator.next();
                JSONObject downloads = (JSONObject) lib.get("downloads");
                if (downloads.get("artifact") != null) {
                    JSONObject artifact = (JSONObject) downloads.get("artifact");
                    if (artifact.get("path") != null) {
                        String _path = (String) artifact.get("path");
                        version_path_list.add(_path);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            //System.out.print(e);
        }

    }

    //edit this function to add more operating systems
    public void readJson_libraries_downloads_classifiers_natives_X(String path, String natives_OS) {

        try {
            if (natives_OS.equals("Linux")) {
                natives_OS = natives_OS.replace("Linux", "natives-linux");
            } else if (natives_OS.equals("Windows")) {
                natives_OS = natives_OS.replace("Windows", "natives-windows");
            } else if (natives_OS.equals("Mac")) {
                natives_OS = natives_OS.replace("Mac", "natives-osx");
            } else {
                System.out.print("N/A");
                //I DON'T KNOW THIS OS!
            }
            String content = new Scanner(new File(path)).useDelimiter("\\Z").next();
            //System.out.println(content);
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
            engine.eval(new FileReader("script.js"));

            Invocable invocable = (Invocable) engine;

            Object result = invocable.invokeFunction("getJsonLibrariesDownloadsClassifiersNativesX", content, natives_OS);

            for (String retval : result.toString().split("\n")) {
                version_url_list_natives.add(retval);
            }
        } catch (FileNotFoundException | ScriptException | NoSuchMethodException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void readJson_libraries_downloads_classifiers_natives_Y(String path, String natives_OS) {

        try {
            if (natives_OS.equals("Linux")) {
                natives_OS = natives_OS.replace("Linux", "natives-linux");
            } else if (natives_OS.equals("Windows")) {
                natives_OS = natives_OS.replace("Windows", "natives-windows");
            } else if (natives_OS.equals("Mac")) {
                natives_OS = natives_OS.replace("Mac", "natives-osx");
            } else {
                System.out.print("N/A");
                //I DON'T KNOW THIS OS!
            }
            String content = new Scanner(new File(path)).useDelimiter("\\Z").next();
            //System.out.println(content);
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
            engine.eval(new FileReader("script.js"));

            Invocable invocable = (Invocable) engine;

            Object result = invocable.invokeFunction("getJsonLibrariesDownloadsClassifiersNativesY", content, natives_OS);

            for (String retval : result.toString().split("\n")) {
                version_path_list_natives.add(retval);
            }
        } catch (FileNotFoundException | ScriptException | NoSuchMethodException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void readJson_objects_KEY(String path) {

        //ammars old code | THIS DOES WORK!
        /*
        try {

            String content = new Scanner(new File(path)).useDelimiter("\\Z").next();
            //System.out.println(content);
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
            engine.eval(new FileReader("script.js"));

            Invocable invocable = (Invocable) engine;
            Object result = invocable.invokeFunction("getJsonObjectsKEY", content);
            //System.out.println(result);
            //split here with /n
            for (String retval : result.toString().split("\n")) {
                objects_KEY.add(retval);
            }
        } catch (FileNotFoundException | ScriptException | NoSuchMethodException ex) {
            System.out.println(ex.getMessage());
        }*/
        JSONParser readMCJSONFiles = new JSONParser();
        try {
            Object jsonfile;

            jsonfile = readMCJSONFiles.parse(new FileReader(path));

            JSONObject jsonObject = (JSONObject) jsonfile;
            JSONObject objects = (JSONObject) jsonObject.get("objects");

            Set fileCheckObjects = objects.keySet();
            Iterator a = fileCheckObjects.iterator();
            while (a.hasNext()) {
                String fileName = (String) a.next();
                objects_KEY.add(fileName);
            }
        } catch (FileNotFoundException exception) {
            System.out.println(exception);
        } catch (IOException | ParseException ex) {
            System.out.println(ex);
        }

    }

    public void readJson_objects_KEY_hash(String path) {
        //ammars old code | THIS DOES WORK!
        /*
        try {

            String content = new Scanner(new File(path)).useDelimiter("\\Z").next();
            //System.out.println(content);
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
            engine.eval(new FileReader("script.js"));

            Invocable invocable = (Invocable) engine;

            int n = 0;
            Object result = invocable.invokeFunction("getJsonObjectsHash", content);
            //System.out.println(result);
            //split here with /n
            for (String retval : result.toString().split("\n")) {
                objects_hash.add(retval);
            }
        } catch (FileNotFoundException | ScriptException | NoSuchMethodException ex) {
            System.out.println(ex.getMessage());
        }*/

        JSONParser readMCJSONFiles = new JSONParser();
        try {
            Object jsonfile;

            jsonfile = readMCJSONFiles.parse(new FileReader(path));

            JSONObject jsonObject = (JSONObject) jsonfile;
            JSONObject objects = (JSONObject) jsonObject.get("objects");

            Set fileCheckObjects = objects.keySet();
            Iterator a = fileCheckObjects.iterator();
            while (a.hasNext()) {
                String fileName = (String) a.next();

                JSONObject fileNameObject = (JSONObject) objects.get(fileName);
                String fileHash = (String) fileNameObject.get("hash");
                objects_hash.add(fileHash);
            }
        } catch (FileNotFoundException exception) {
            System.out.println(exception);
        } catch (IOException | ParseException ex) {
            System.out.println(ex);
        }

    }

    public String readJson_assetIndex_url(String path) {
        try {
            FileReader reader = new FileReader(path);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONObject structure = (JSONObject) jsonObject.get("assetIndex");
            return (String) (structure.get("url"));

        } catch (IOException | ParseException e) {
            System.out.print(e);
        }
        return "N/A";
    }

    public String readJson_assetIndex_id(String path) {
        try {
            FileReader reader = new FileReader(path);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONObject structure = (JSONObject) jsonObject.get("assetIndex");
            return (String) (structure.get("id"));

        } catch (IOException | ParseException e) {
            System.out.print(e);
        }
        return "N/A";
    }

    public void generateVersionJsonPathList(String path) {
        File root = new File(path);
        String fileName = ".json";
        try {
            boolean recursive = true;

            Collection files = FileUtils.listFiles(root, null, recursive);

            for (Iterator iterator = files.iterator(); iterator.hasNext();) {
                File file = (File) iterator.next();
                if (file.getName().endsWith(fileName)) {
                    versions_json_path_list.add(file.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void generateVersionList(String path) {
        File root = new File(path);
        String fileName = ".json";
        try {
            boolean recursive = true;

            Collection files = FileUtils.listFiles(root, null, recursive);

            for (Iterator iterator = files.iterator(); iterator.hasNext();) {
                File file = (File) iterator.next();
                if (file.getName().endsWith(fileName)) {
                    versions_list.add(file.getName().replace(".json", ""));
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public String readJson_minecraftArguments(String path) {
        try {
            FileReader reader = new FileReader(path);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            return (String) (jsonObject.get("minecraftArguments"));

        } catch (IOException | ParseException e) {
            System.out.print(e);
        }
        return "N/A";
    }

    public String readJson_assets(String path) {
        try {
            FileReader reader = new FileReader(path);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            return (String) (jsonObject.get("assets"));

        } catch (IOException | ParseException e) {
            System.out.print(e);
        }
        return "N/A";
    }

    public String readJson_id(String path) {
        try {
            FileReader reader = new FileReader(path);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            return (String) (jsonObject.get("id"));

        } catch (IOException | ParseException e) {
            System.out.print(e);
        }
        return "N/A";
    }

    public String readJson_mainClass(String path) {
        try {
            FileReader reader = new FileReader(path);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            return (String) (jsonObject.get("mainClass"));

        } catch (IOException | ParseException e) {
            System.out.print(e);
        }
        return "N/A";
    }

    public String generateMinecraftArguments(String OS, String auth_player_name, String version_name, String game_directory, String assets_root, String assets_index_name, String auth_uuid, String auth_access_token, String user_properties, String user_type, String version_type, String game_assets) {

        Local local = new Local();
        Utils utils = new Utils();
        String cmdArgs = local.readJson_minecraftArguments(utils.getMineCraft_Versions_X_X_json(OS, version_name));
        cmdArgs = cmdArgs.replace("${auth_player_name}", auth_player_name);
        cmdArgs = cmdArgs.replace("${version_name}", version_name);
        cmdArgs = cmdArgs.replace("${game_directory}", game_directory);
        cmdArgs = cmdArgs.replace("${assets_root}", assets_root);
        cmdArgs = cmdArgs.replace("${assets_index_name}", assets_index_name);
        cmdArgs = cmdArgs.replace("${auth_uuid}", auth_uuid);
        cmdArgs = cmdArgs.replace("${auth_access_token}", auth_access_token);
        cmdArgs = cmdArgs.replace("${user_properties}", user_properties);
        cmdArgs = cmdArgs.replace("${user_type}", user_type);
        cmdArgs = cmdArgs.replace("${version_type}", version_type);
        cmdArgs = cmdArgs.replace("${game_assets}", game_assets);
        return cmdArgs;
    }

    public String generateLibrariesArguments(String OS) {
        String cp = "";
        Utils utils = new Utils();
        for (int i = 0; i < libraries_path.size(); i++) {
            if (i == libraries_path.size() - 1) {
                cp = cp + libraries_path.get(i).toString();
            } else {
                cp = cp + libraries_path.get(i).toString() + utils.getArgsDiv(OS);
            }
        }
        return cp;
    }

    public String generateRunnableArguments(String NativesDir, String FullLibraryArgument, String mainClass, String HalfArgument) {
        return ("-XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump -Djava.library.path=" + NativesDir + " -cp " + FullLibraryArgument + " " + mainClass + " " + HalfArgument);

    }

    public String generateLibrariesPath(String _OS, String _name) {
        try {
            Utils utils = new Utils();
            String fileName = _name;
            String[] colonSplit = fileName.split("\\:", 3);
            String[] folderSplit = colonSplit[0].split("\\.");

            String compileSplit = "";

            String compileFolder = "";

            for (int i = 0; i < folderSplit.length; i++) {
                compileFolder += folderSplit[i] + "/";
            }
            compileSplit = compileFolder + "/" + colonSplit[1] + "/" + colonSplit[2] + "/" + colonSplit[1] + "-" + colonSplit[2] + ".jar";
            compileSplit = compileSplit.replace("//", "/");
            /*
                Downloading: https://libraries.minecraft.net/org/ow2/asm/asm-all/4.1/asm-all-4.1.jar
                org/ow2/asm/asm-all/4.1/asm-all-4.1.jar
                
             */
            //compileSplit = utils.getMineCraftLibrariesLocation(_OS) + "/" + compileSplit;
            return (compileSplit);

        } catch (Exception e) {
            System.out.println(e);
        }
        return "N/A";
    }

    public Boolean checkIfVanillaMC(String version) {
        for (int i = 0; i < version_manifest_versions_id.size(); i++) {
            if (version_manifest_versions_id.get(i).equals(version)) {
                return true;
            }
        }
        //if nothing.. return false
        return false;
    }

    public void MOD_readJson_libraries_name_PLUS_url(String path) {
        JSONParser readMCJSONFiles = new JSONParser();
        try {
            Network network = new Network();
            Object object = readMCJSONFiles.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray versions = (JSONArray) jsonObject.get("libraries");
            Iterator<JSONObject> iterator = versions.iterator();
            while (iterator.hasNext()) {
                JSONObject name_ = (JSONObject) iterator.next();
                version_name_list.add(name_.get("name"));
                if (name_.get("url") == null) {
                    System.out.println("Can't resolve: url Attempting to fix!");
                    HALF_URL_version_url_list.add(network.https_libraries_minecraft_net);
                } else {
                    HALF_URL_version_url_list.add(name_.get("url"));

                }
            }
        } catch (IOException | ParseException e) {
            //System.out.print(e);
        }
    }
    
    public String readJson_inheritsFrom(String path) {
        try {
            FileReader reader = new FileReader(path);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            return (String) (jsonObject.get("inheritsFrom"));

        } catch (IOException | ParseException e) {
            System.out.print(e);
        }
        return "N/A";
    }
    
    public String readJson_jar(String path) {
        try {
            FileReader reader = new FileReader(path);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            return (String) (jsonObject.get("jar"));

        } catch (IOException | ParseException e) {
            System.out.print(e);
        }
        return "N/A";
    }
}
