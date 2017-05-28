# Open-source Minecraft API - TagAPI
The purpose of the API is to provide developers the ability to download / run Minecraft.

The accessible class which is the API_Interface, consists of the following functions:

- Local Data:
  - ```List getInstallableVersionsList()``` - *Returns list of all versions available to download*
  - ```List getInstalledVersionsList() ```- *Returns list of all versions installed on users machine*

- Miscellaneous:
  - ```void syncVersions()``` - *Syncs game profiles with premium launcher*
  - ```void setMinMemory(int MemoryToUse)``` - *Set amount of minimum memory allocated for the game;* _**default is 1024**_
  - ```void setMemory(int MemoryToUse)``` - *Set amount of maximum memory allocated for the game;* _**default is 1024**_
  - ```void setJavaPath(String JavaPathToUse)``` - *Set external Java path to use for the game;* _**default path is JAVA path on the system**_
  - ```void setWidth(int WidthToUse)``` - *Set screen size width for the game;* _**default is 854**_
  - ```void setHeight(int HeightToUse)``` - *Set screen size height for the game;* _**default is 480**_
  - ```void setJVMArgument(String JVMArgumentsToUse)``` - *Set extra JVM Arguments to use for the game;* _**default arguments are none**_
  - ```void injectNetty(String OperatingSystemToUse)``` - *Injects netty with invalid url. This can be used to bypass server blacklist*

- Downloads:
  - ```void downloadMinecraft(String VersionToUse, Boolean ForceDownload)``` - *Accepts (Version to download, Force Download); if force download is True, it will re-download all the files, vice-versa if it is set to False, it will only download the missing files.*
  - ```void downloadVersionManifest()``` - *Downloads the list of all versions available on the server*
  - ```void downloadProfile(String UsernameToUse)``` - *Downloads the profile data in order to set UUID; Accepts (Username)*

- Launch:
  - ```void runMinecraft(String UsernameToUse, String VersionToUse)``` - *Accepts (Username, Version to run) and launches Minecraft*

- Version Info:
 -  ```String getAPIVersion()``` - *Returns the version number of the API library*

- Logs:
  - ```String getLog()``` - *Returns last logged log*
  - ```List getLogs()``` - *Returns a list of all logged logs*
  - ```void dumpLogs()``` - *Writes all logged logs to* _**/.minecraft/Launcherlogs.txt**_

| Key                             | Log Type      | Purpose                                           |
| :-----------------------------: |:-------------:| :------------------------------------------------:|
| [rl] ```{prefix}```             | run logs      | tells when local file processing is being done    |
| [dl] ```{prefix}```             | download logs | tells when API is downloading files               |
| [el] ```{prefix}```             | error logs    | tells when an error has occured in the API        |
| [rl] Minecraft Corruption found!| run logs      | tells when the API failed to run minecraft        |
| [rl] Minecraft Initialized!     | run logs      | tells when the API has successfully run minecraft |
| [dl] Download Complete!         | download logs | tells when the API has completed downloading files|

# Example Code
- Download Minecraft (Force Download: False):
```java
    tagapi_3.API_Interface API = new tagapi_3.API_Interface();
    API.downloadMinecraft("1.8.9", false); //download version 1.8.9, without replacing the files
```

- Launch Minecraft:
```java
    tagapi_3.API_Interface API = new tagapi_3.API_Interface();
    API.runMinecraft("Ammar_Ahmad", "1.8.9"); //run minecraft with username: Ammar_Ahmad, and version: 1.8.9
```
