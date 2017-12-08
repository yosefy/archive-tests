package helper.Class;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static PageObjects.ProgramsGenre.ALL_PROGRAMS_ITEMS;
import static helper.Class.InitClass.driver;

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



    /**
     * Get file and calculate checksum
     */
    public String[] getCheckSum(File file) throws NoSuchAlgorithmException, IOException {
        String[] names = new String[2];
        MessageDigest md = MessageDigest.getInstance("MD5");
        // Change MD5 to SHA1 to get SHA checksum
        // MessageDigest md = MessageDigest.getInstance("SHA1");
        FileInputStream fis = new FileInputStream(file);
        byte[] dataBytes = new byte[1024];
        int nRead = 0;

        while ((nRead = fis.read(dataBytes)) != -1) md.update(dataBytes, 0, nRead);

        byte[] mdbytes = md.digest();

        //convert the byte to hex format
        StringBuilder sb = new StringBuilder("");
        for (byte mdbyte : mdbytes) {
            sb.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
        }
        names[0] = file.toString();
        names[1] = sb.toString();

        return names;
    }

    public Map<String, String> returnHashMapWithChecksumAndFileName (String absolutePath)
            throws IOException, NoSuchAlgorithmException {

        File[] listOfFilesRav = new File(absolutePath).listFiles();
        Map<String, String> myMap = new HashMap<>();
        String key, value;
        String[] listOfResult;
        assert listOfFilesRav != null;

        for (File file : listOfFilesRav) {
            listOfResult = this.getCheckSum(file);
            key = listOfResult[1];
            System.out.println("key - " + key);
            value = listOfResult[0];
            System.out.println("value - " + value);
            myMap.put(key, value);
        }
        return myMap;
    }
}
