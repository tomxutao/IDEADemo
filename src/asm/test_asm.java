package asm;

import java.io.File;
import java.io.IOException;

public class test_asm {

    public static void main(String[] args) {

        File jarFile = new File("/Users/xutao/.gradle/caches/transforms-1/files-1.1/appcompat-v7-27.1.1.aar/0bdfd8b5009a321fd6ecf43fae2538bb/jars/classes.jar");
        File tempDir = new File("/Users/xutao/Desktop/xutao/code/gradle_demo/ASMTest-master/app/build/tmp/transformClassesWithCarlTrackForDebug");
        try {
            CarlModify.modifyJar(jarFile, tempDir, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
