# Seapal in Scala

Prerequisites
-------------
* sbt 0.13.0 or above
* Android SDK
  - install SDK Level 19.
  - Install Google Play Services, Google Reporsitory and Android Support-Library and -Repository.

Build
-----
You can build using sbt:

    $ sbt run

This will compile the project and generate an APK.

For more command, refer to [Android SDK plugin for sbt](https://github.com/pfn/android-sdk-plugin).



Troubleshooting
---------------

### Build error `Android SDK build-tools not available`
[The most likely cause of this error is that your SDK build-tools are old.](https://github.com/pfn/android-sdk-plugin/issues/13) Update the Android SDK and retry.

Further Reading
---------------
- [Scaloid](https://github.com/pocorall/scaloid)
- [Android SDK plugin for sbt](https://github.com/pfn/android-sdk-plugin)

