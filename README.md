# Open-source Minecraft API - TagAPI
The purpose of the API is to provide developers the ability to download / run Minecraft.

The accessible class which is the API_Interface, consists of the following functions:

- Logs:
  - ~~```String getRunLogs()``` - *Returns logs (Should be used when running the game);* _**Denoted by [rl]**_~~
  - ~~```String getDownloadLogs()``` - *Returns logs (Should be used when downloading a new version of the game);* _**Denoted by [dl]**_~~
  - ~~```String getLastErrorLogs()``` - *Returns logs (Should be used when there is an error in downloading or launching the game);* _**Denoted by [el]**_~~
  - ```String getLog()``` - *Returns last logged log*
  - ```List getLogs()``` - *Returns a list of all logged logs*

- Local Data:
  - ```List getInstallableVersionsList()``` - *Returns list of all versions available to download*
  - ```List getInstalledVersionsList() ```- *Returns list of all versions installed on users machine*

- Miscellaneous:
  - ```void syncVersions()``` - *Syncs game profiles with premium launcher*
  - ```void setMemory(String MemoryToUse)``` - *Set amount of memory allocated for the game;* _**default is 1G**_

- Downloads:
  - ```void downloadMinecraft(String VersionToUse, Boolean ForceDownload)``` - *Accepts (Version to download, Force Download); if force download is True, it will re-download all the files, vice-versa if it is set to False, it will only download the missing files.*
  - ```void downloadVersionManifest()``` - *Downloads the list of all versions available on the server*
  - ```void downloadProfile(String UsernameToUse)``` - *Downloads the profile data in order to set UUID; Accepts (Username)*

- Launch:
  - ```void runMinecraft(String UsernameToUse, String VersionToUse)``` - *Accepts (Username, Version to run) and launches Minecraft*

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

# Release
TagAPI - v0.1-alpha - https://github.com/ammarx/TagAPI_3/releases/tag/v0.1-alpha
