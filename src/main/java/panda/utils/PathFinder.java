package panda.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

public class PathFinder {

    private String moduleResourcesPath = "";

    public PathFinder(String moduleResourcesPath) {
        this.moduleResourcesPath = moduleResourcesPath;
    }

    public String findPath(String fileName) throws Exception  {
        String resourcePath = System.getProperty("user.dir") + moduleResourcesPath;
        File root = new File(resourcePath);
        String result = "";
            boolean recursive = true;

            Collection<File> files = FileUtils.listFiles(root, null, recursive);

            for (Iterator<File> iterator = files.iterator(); iterator.hasNext(); ) {
                File file = iterator.next();
                if (file.getName().equals(fileName))
                    result = file.getAbsolutePath();
            }
        return result;
    }
}
