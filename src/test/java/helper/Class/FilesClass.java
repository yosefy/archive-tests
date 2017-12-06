package helper.Class;

import java.io.File;

public class FilesClass {


    /**
     * Deletes the files in folder if exist or create new folder
     */
    public boolean replaceFilesOrCreateNewFolder(String target) {
        File dir = new File(target);
        boolean exists = dir.exists();
        if (exists) {
            File[] contents = dir.listFiles();
            if (contents != null) {
                for (File f : contents)
                    f.delete();
                return true;
            }
        }
        else {
            dir.mkdir();
            return true;
        }
        return false;
    }
}
