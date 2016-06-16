/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tagapi_3;

import java.util.List;

/**
 *
 * @author ammar
 */
public class API_Interface {

    Utils utils = new Utils();
    Local local = new Local();
    Network network = new Network();

    public List getInstalledVersionsList() {
        String OperatingSystemToUse = utils.getOS();
        local.generateVersionJsonPathList(utils.getMineCraftVersionsLocation(OperatingSystemToUse));
        local.generateVersionList(utils.getMineCraftVersionsLocation(OperatingSystemToUse));

        return local.versions_list;
    }

    //runMinecraft - should only run minecraft (no verification of libraries... no downloads. just get args and run)
    public void runMinecraft(String UsernameToUse, String VersionToUse) {
        String OperatingSystemToUse = utils.getOS();
        //get list of all 
        local.readJson_versions_id(utils.getMineCraft_Version_Manifest_json(OperatingSystemToUse));
        local.readJson_versions_type(utils.getMineCraft_Version_Manifest_json(OperatingSystemToUse));
        local.readJson_versions_url(utils.getMineCraft_Version_Manifest_json(OperatingSystemToUse));

        //declaration for mods
        String MOD_inheritsFrom = null;
        String MOD_jar = null;
        String MOD_assets = null;
        String MOD_minecraftArguments;
        String MOD_mainClass = null;
        String MOD_id = null;
        //check if it is vanilla or not
        if (local.checkIfVanillaMC(VersionToUse).equals(true)) {
            System.out.println("Vanilla Minecraft found!");

        } else {
            System.out.println("Modded Minecraft found!");
            local.MOD_readJson_libraries_name_PLUS_url(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse));

            /*System.out.println("Fixing url using name.");*/
            for (int i = 0; i < local.version_name_list.size(); i++) {
                local.version_path_list.add(local.generateLibrariesPath(OperatingSystemToUse, local.version_name_list.get(i).toString()));

            }

            for (int i = 0; i < local.version_name_list.size(); i++) {
                local.version_url_list.add(local.HALF_URL_version_url_list.get(i) + "/" + local.version_path_list.get(i));
            }

            MOD_inheritsFrom = local.readJson_inheritsFrom(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse));
            System.out.println("inheritsFrom: " + MOD_inheritsFrom);

            MOD_jar = local.readJson_jar(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse));
            System.out.println("jar: " + MOD_jar);

            MOD_assets = local.readJson_assets(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse));
            System.out.println("assets: " + MOD_assets);

            MOD_minecraftArguments = local.readJson_minecraftArguments(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse));
            System.out.println("minecraftArguments: " + MOD_minecraftArguments);

            MOD_mainClass = local.readJson_mainClass(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse));
            System.out.println("mainClass: " + MOD_mainClass);

            MOD_id = local.readJson_id(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse));
            System.out.println("id: " + MOD_id);
        }

        if (MOD_inheritsFrom == null) {
            System.out.println("Using: " + VersionToUse);

        } else {
            VersionToUse = MOD_inheritsFrom;
            System.out.println("Using: " + VersionToUse);

        }

        local.generateVersionJsonPathList(utils.getMineCraftVersionsLocation(OperatingSystemToUse));
        local.generateVersionList(utils.getMineCraftVersionsLocation(OperatingSystemToUse));

        try {
            local.readJson_libraries_downloads_artifact_url(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse));

        } catch (Exception ex) {
            System.out.println("Unable to get libraries_downloads_artifact_url " + ex);
        }
        try {
            local.readJson_libraries_downloads_artifact_path(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse));

        } catch (Exception ex) {
            System.out.println("Unable to get libraries_downloads_artifact_path " + ex);
        }
        try {
            local.readJson_libraries_name(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse));

        } catch (Exception ex) {
            System.out.println("Unable to get libraries_name " + ex);
        }
        ///************************************************************
        for (int i = 0; i < local.version_name_list.size(); i++) {
            try {
                local.version_path_list.add(local.generateLibrariesPath(OperatingSystemToUse, local.version_name_list.get(i).toString()));

            } catch (Exception ex) {
                System.out.println("Something went wrong! " + ex);
            }
        }

        System.out.println("Getting NATIVES URL");
        local.readJson_libraries_downloads_classifiers_natives_X(utils.getMineCraft_Versions_X_X_json(OperatingSystemToUse, VersionToUse), OperatingSystemToUse);
        System.out.println("Getting NATIVES PATH");
        local.readJson_libraries_downloads_classifiers_natives_Y(utils.getMineCraft_Versions_X_X_json(OperatingSystemToUse, VersionToUse), OperatingSystemToUse);
        System.out.println("Getting NATIVES NAME");
        local.readJson_libraries_downloads_classifiers_natives_Z(utils.getMineCraft_Versions_X_X_json(OperatingSystemToUse, VersionToUse));
        
        for (int i = 0; i < local.version_name_list_natives.size(); i++) {
            local.version_path_list_natives.add(local.generateNativesPath(OperatingSystemToUse, local.version_name_list_natives.get(i).toString()));

        }
        /*
        for (int i = 0; i < local.version_url_list_natives.size(); i++) {
            System.out.println(local.version_url_list_natives.get(i).toString());
        }
        for (int i = 0; i < local.version_path_list_natives.size(); i++) {
            System.out.println(local.version_path_list_natives.get(i).toString());
        }
        for (int i = 0; i < local.version_name_list_natives.size(); i++) {
            System.out.println(local.version_name_list_natives.get(i).toString());
        }
        */
        
        for (int i = 0; i < local.version_name_list_natives.size(); i++) {
            //extract them here..
            try {
                System.out.println("Extracting... " + local.version_path_list_natives.get(i).toString());
                System.out.println(utils.getMineCraft_Versions_X_Natives(OperatingSystemToUse, VersionToUse));
                utils.jarExtract(OperatingSystemToUse, local.version_path_list_natives.get(i).toString(), utils.getMineCraft_Versions_X_Natives(OperatingSystemToUse, VersionToUse));

            } catch (Exception ex) {
                System.out.println(ex);
            }

        }

        String mainClass;
        if (MOD_mainClass == null) {
            mainClass = local.readJson_mainClass(utils.getMineCraft_Versions_X_X_json(OperatingSystemToUse, VersionToUse));

        } else {
            mainClass = MOD_mainClass;
        }

        String NativesDir = utils.getMineCraft_Versions_X_Natives(OperatingSystemToUse, VersionToUse);
        String assetsIdexId;
        if (MOD_assets == null) {
            assetsIdexId = local.readJson_assets(utils.getMineCraft_Versions_X_X_json(OperatingSystemToUse, VersionToUse));

        } else {
            assetsIdexId = MOD_assets;
        }
        if (assetsIdexId == null) {
            assetsIdexId = "NULL";
        }

        String gameDirectory = utils.getMineCraftLocation(OperatingSystemToUse);
        String AssetsRoot = utils.getMineCraftAssetsLocation(OperatingSystemToUse);

        String versionName;
        if (MOD_id == null) {
            versionName = local.readJson_id(utils.getMineCraft_Versions_X_X_json(OperatingSystemToUse, VersionToUse));
        } else {
            versionName = MOD_id;
        }

        String authuuid = local.readJson_id(utils.getMineCraft_X_json(OperatingSystemToUse, UsernameToUse));
        String Username = UsernameToUse;
        String MinecraftJar;
        if (MOD_jar == null) {
            MinecraftJar = utils.getMineCraft_Versions_X_X_jar(OperatingSystemToUse, VersionToUse);

        } else {
            MinecraftJar = utils.getMineCraft_Versions_X_X_jar(OperatingSystemToUse, MOD_jar);
        }

        String VersionType = "ammarbless";
        String AuthSession = "OFFLINE";
        String GameAssets = utils.getMineCraftAssetsVirtualLegacyLocation(OperatingSystemToUse);
        System.out.println("NativesPath: " + NativesDir);

        for (int i = 0; i < local.version_path_list.size(); i++) {
            local.libraries_path.add(utils.setMineCraft_librariesLocation(OperatingSystemToUse, local.version_path_list.get(i).toString()));
        }

        String FullLibraryArgument = local.generateLibrariesArguments(OperatingSystemToUse) + utils.getArgsDiv(OperatingSystemToUse) + MinecraftJar;

        String HalfArgument = local.generateMinecraftArguments(OperatingSystemToUse, Username, versionName, gameDirectory, AssetsRoot, assetsIdexId, authuuid, "aeef7bc935f9420eb6314dea7ad7e1e5", "{\"twitch_access_token\":[\"emoitqdugw2h8un7psy3uo84uwb8raq\"]}", "mojang", VersionType, GameAssets, AuthSession);
        System.out.println("Minecraft.jar: " + MinecraftJar);
        System.out.println("username: " + Username);
        System.out.println("version number: " + versionName);
        System.out.println("game directory: " + gameDirectory);
        System.out.println("assets root directory: " + AssetsRoot);
        System.out.println("assets Index Id: " + assetsIdexId);
        System.out.println("assets legacy directory: " + GameAssets);
        System.out.println("full arguments: " + local.generateRunnableArguments(NativesDir, FullLibraryArgument, mainClass, HalfArgument));

        try {

            String ArgsX = local.generateRunnableArguments(NativesDir, FullLibraryArgument, mainClass, HalfArgument);
            Runtime.getRuntime().exec("java " + ArgsX);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void downloadVersionManifest() {
        System.out.println("Downloading: version_manifest.json");
        String OperatingSystemToUse = utils.getOS();
        network.downloadVersionManifest(utils.getMineCraft_Version_Manifest_json(OperatingSystemToUse));

    }

    public void downloadProfile(String UsernameToUse) {
        String OperatingSystemToUse = utils.getOS();
        System.out.println("Downloading: " + UsernameToUse + ".json");
        network.downloadProfile(OperatingSystemToUse, UsernameToUse);

    }

}
