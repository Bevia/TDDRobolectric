# TDDRobolectric
Android - TDD Robolectric

        apply plugin: 'com.android.application'
        
        android {
            compileSdkVersion 24
            buildToolsVersion "24.0.0"
        
            defaultConfig {
                applicationId "com.corebaseit.tddrobolectric"
                minSdkVersion 21
                targetSdkVersion 24
                versionCode 1
                versionName "1.0"
            }
            buildTypes {
                release {
                    minifyEnabled false
                    proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
                }
            }
        }
        
        dependencies {
            compile fileTree(dir: 'libs', include: ['*.jar'])
            testCompile 'junit:junit:4.12'
            //you need this for Robolectric:
            testCompile "org.robolectric:robolectric:3.1.2"
            /*testCompile "org.robolectric:robolectric:3.1-SNAPSHOT" */ //so that you can target API 23
            //you need need this for assertions:
            testCompile "org.assertj:assertj-core:1.7.0"
            compile 'com.android.support:appcompat-v7:24.1.1'
        }
