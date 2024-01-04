// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id(Config.Plugins.android) version Versions.androidVersion apply false
    id(Config.Plugins.kotlinAndroid) version Versions.kotlinVersion apply false
    id(Config.Plugins.hiltAndroid) version Versions.daggerHilt apply false
}