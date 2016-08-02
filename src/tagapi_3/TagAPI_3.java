/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tagapi_3;

/**
 *
 * @author ammar
 */
class TagAPI_3 {

    /**
     * @param args the command line arguments
     */
    //we will be using this for testing only...
    public static void main(String[] args) {
        // TODO code application logic here
        //*** Remove this as this is required for it to work fully...
        //its just here to save time.
        Utils utils = new Utils();
        Local local = new Local();
        Network network = new Network();
        
        String UsernameToUse = args[0];
        String VersionToUse = args[1];
        String OperatingSystemToUse = utils.getOS();
        System.out.println("OS: " + OperatingSystemToUse);

        //Example:
        //String VersionToUse = "1.8.9";
        //String OperatingSystemToUse = "Linux";
        //String UsernameToUse = "Ammar_Ahmad";
       
        //get profile
        System.out.println("Getting profile details");
        network.downloadProfile(OperatingSystemToUse, UsernameToUse);
        network.downloadVersionManifest(utils.getMineCraft_Version_Manifest_json(OperatingSystemToUse));

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
            for (int i = 0; i < local.version_name_list.size(); i++) {
                System.out.println(local.version_name_list.get(i));
                System.out.println(local.HALF_URL_version_url_list.get(i));
            }

            System.out.println("Fixing url using name.");
            for (int i = 0; i < local.version_name_list.size(); i++) {
                local.version_path_list.add(local.generateLibrariesPath(OperatingSystemToUse, local.version_name_list.get(i).toString()));

            }

            for (int i = 0; i < local.version_name_list.size(); i++) {
                local.version_url_list.add(local.HALF_URL_version_url_list.get(i) + "/" + local.version_path_list.get(i));
            }
            for (int i = 0; i < local.version_name_list.size(); i++) {
                System.out.println("Downloading: " + local.version_url_list.get(i));
                network.downloadLibraries(OperatingSystemToUse, local.version_url_list.get(i).toString(), local.version_path_list.get(i).toString());

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
        
        //incase the url is empty.. we have to assume that the user has old path system.
        for (int i = 0; i < local.version_manifest_versions_id.size(); i++) {
            System.out.println(local.version_manifest_versions_id.get(i));
            System.out.println(local.version_manifest_versions_type.get(i));
            System.out.println(local.version_manifest_versions_url.get(i));
        }

        //download 1.7.10.json_libs
        for (int i = 0; i < local.version_manifest_versions_id.size(); i++) {
            if (local.version_manifest_versions_id.get(i).equals(VersionToUse)) {
                network.downloadVersionJson(OperatingSystemToUse, local.version_manifest_versions_url.get(i).toString(), local.version_manifest_versions_id.get(i).toString());
                break;
            } else {
                //do nothing...
            }
        }

        System.out.println(utils.getMineCraftLocation(OperatingSystemToUse));

        local.generateVersionJsonPathList(utils.getMineCraftVersionsLocation(OperatingSystemToUse));
        local.generateVersionList(utils.getMineCraftVersionsLocation(OperatingSystemToUse));

        for (int i = 0; i < local.versions_json_path_list.size(); i++) {
            System.out.println(local.versions_json_path_list.get(i));
        }

        for (int i = 0; i < local.versions_list.size(); i++) {
            System.out.println(local.versions_list.get(i));
        }

        System.out.print(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse));
        System.out.print("\n\n");

        local.readJson_libraries_downloads_artifact_url(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse));
        local.readJson_libraries_downloads_artifact_path(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse));
        local.readJson_libraries_name(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse));
        ///************************************************************
        for (int i = 0; i < local.version_url_list.size(); i++) {
            System.out.println("Downloading: " + local.version_url_list.get(i));
            try {
                network.downloadLibraries(OperatingSystemToUse, local.version_url_list.get(i).toString(), local.version_path_list.get(i).toString());

            } catch (Exception ex) {
                System.out.println("Due to: " + ex + " " + local.generateLibrariesPath(OperatingSystemToUse, local.version_name_list.get(i).toString()));
                local.version_path_list.add(local.generateLibrariesPath(OperatingSystemToUse, local.version_name_list.get(i).toString()));
                network.downloadLibraries(OperatingSystemToUse, local.version_url_list.get(i).toString(), local.generateLibrariesPath(OperatingSystemToUse, local.version_name_list.get(i).toString()));

            }
        }

        //this may need to be edited!*************//
        System.out.println(local.readJson_assetIndex_url(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse)));
        System.out.println(local.readJson_assetIndex_id(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse)));
        //get assets index id!
        network.downloadLaunchermeta(OperatingSystemToUse, local.readJson_assetIndex_url(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse)), local.readJson_assetIndex_id(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse)));

        System.out.println(utils.getMineCraftAssetsIndexes_X_json(OperatingSystemToUse, VersionToUse));

        local.readJson_objects_KEY(utils.getMineCraftAssetsIndexes_X_json(OperatingSystemToUse, local.readJson_assetIndex_id(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse))));
        local.readJson_objects_KEY_hash(utils.getMineCraftAssetsIndexes_X_json(OperatingSystemToUse, local.readJson_assetIndex_id(utils.getMineCraft_Version_Json(OperatingSystemToUse, VersionToUse))));

        for (int i = 0; i < local.objects_hash.size(); i++) {
            System.out.println("HASH: " + local.objects_hash.get(i));
            System.out.println("FOLDER: " + local.objects_hash.get(i).toString().substring(0, 2));
            System.out.println("KEY: " + local.objects_KEY.get(i));

            System.out.println("DOWNLOADING...");
            network.downloadAssetsObjects(OperatingSystemToUse, local.objects_hash.get(i).toString().substring(0, 2), local.objects_hash.get(i).toString());
            utils.copyToVirtual(OperatingSystemToUse, local.objects_hash.get(i).toString().substring(0, 2), local.objects_hash.get(i).toString(), local.objects_KEY.get(i).toString());
            //generate virtual folder as well.

        }

        System.out.println("DOWNLOADING MINECRAFT JAR");
        if (MOD_jar == null) {
            network.downloadMinecraftJar(OperatingSystemToUse, VersionToUse);

        } else {
            network.downloadMinecraftJar(OperatingSystemToUse, MOD_jar);

        }

        //would have tp edit this line as we also need natives paths!
        System.out.println("Getting NATIVES URL");
        local.readJson_libraries_downloads_classifiers_natives_X(utils.getMineCraft_Versions_X_X_json(OperatingSystemToUse, VersionToUse), OperatingSystemToUse);
        System.out.println("Getting NATIVES PATH");
        local.readJson_libraries_downloads_classifiers_natives_Y(utils.getMineCraft_Versions_X_X_json(OperatingSystemToUse, VersionToUse), OperatingSystemToUse);

        for (int i = 0; i < local.version_url_list_natives.size(); i++) {
            System.out.println("NATIVE URL: " + local.version_url_list_natives.get(i));
            network.downloadLibraries(OperatingSystemToUse, local.version_url_list_natives.get(i).toString(), local.version_path_list_natives.get(i).toString());
            //extract them here..
            System.out.println("Extracting...");
            System.out.println(local.version_url_list_natives.get(i).toString());
            System.out.println(utils.getMineCraft_Versions_X_Natives(OperatingSystemToUse, VersionToUse));

            utils.jarExtract(OperatingSystemToUse, local.version_path_list_natives.get(i).toString(), utils.getMineCraft_Versions_X_Natives(OperatingSystemToUse, VersionToUse));

        }

        System.out.println("\n\n");

        //String HalfArgumentTemplate = local.readJson_minecraftArguments(utils.getMineCraft_Versions_X_X_json(OperatingSystemToUse, VersionToUse));
        String mainClass;
        if (MOD_mainClass == null){
            mainClass = local.readJson_mainClass(utils.getMineCraft_Versions_X_X_json(OperatingSystemToUse, VersionToUse));
            
        } else {
            mainClass =  MOD_mainClass;
        }

        String NativesDir = utils.getMineCraft_Versions_X_Natives(OperatingSystemToUse, VersionToUse);
        String assetsIdexId;
        if (MOD_assets == null){
            assetsIdexId = local.readJson_assets(utils.getMineCraft_Versions_X_X_json(OperatingSystemToUse, VersionToUse));
        
        } else {
            assetsIdexId = MOD_assets;
        }

        String gameDirectory = utils.getMineCraftLocation(OperatingSystemToUse);
        String AssetsRoot = utils.getMineCraftAssetsLocation(OperatingSystemToUse);
       
        String versionName;
        if (MOD_id == null){
            versionName = local.readJson_id(utils.getMineCraft_Versions_X_X_json(OperatingSystemToUse, VersionToUse));
        } else {
            versionName = MOD_id;
        }
        
        
        String authuuid = local.readJson_id(utils.getMineCraft_X_json(OperatingSystemToUse, UsernameToUse));
        String Username = UsernameToUse;
        String MinecraftJar;
        if (MOD_jar == null){
            MinecraftJar = utils.getMineCraft_Versions_X_X_jar(OperatingSystemToUse, VersionToUse);
 
        } else {
            MinecraftJar = utils.getMineCraft_Versions_X_X_jar(OperatingSystemToUse, MOD_jar);
        }
         

        String VersionType = "ammarbless";
        String AuthSession = "OFFLINE";
        String Xmx;
        Xmx = "1G";
        String GameAssets = utils.getMineCraftAssetsVirtualLegacyLocation(OperatingSystemToUse);
        System.out.println("NativesPath: " + NativesDir);

        for (int i = 0; i < local.version_path_list.size(); i++) {
            local.libraries_path.add(utils.setMineCraft_librariesLocation(OperatingSystemToUse, local.version_path_list.get(i).toString()));
            System.out.println(local.libraries_path.get(i));
        }

        String HalfLibraryArgument = local.generateLibrariesArguments(OperatingSystemToUse);
        String FullLibraryArgument = local.generateLibrariesArguments(OperatingSystemToUse) + utils.getArgsDiv(OperatingSystemToUse) + MinecraftJar;
        System.out.println("HalfLibraryArgument: " + HalfLibraryArgument);
        System.out.println("FullLibraryArgument: " + FullLibraryArgument);

        String HalfArgument = local.generateMinecraftArguments(OperatingSystemToUse, Username, versionName, gameDirectory, AssetsRoot, assetsIdexId, authuuid, "aeef7bc935f9420eb6314dea7ad7e1e5", "{\"twitch_access_token\":[\"emoitqdugw2h8un7psy3uo84uwb8raq\"]}", "mojang", VersionType, GameAssets, AuthSession);
        System.out.println("HalfArgument: " + HalfArgument);
        System.out.println("Minecraft.jar: " + MinecraftJar);

        System.out.println("username: " + Username);
        System.out.println("version number: " + versionName);
        System.out.println("game directory: " + gameDirectory);
        System.out.println("assets root directory: " + AssetsRoot);
        System.out.println("assets Index Id: " + assetsIdexId);
        System.out.println("assets legacy directory: " + GameAssets);
        System.out.println(local.generateRunnableArguments(Xmx, NativesDir, FullLibraryArgument, mainClass, HalfArgument));

        try {

            String ArgsX = local.generateRunnableArguments(Xmx, NativesDir, FullLibraryArgument, mainClass, HalfArgument);
            Runtime.getRuntime().exec("java " + ArgsX);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

}
