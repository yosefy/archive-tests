package helpers;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

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
        } else {
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
        int nRead;

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

    public Map<String, String> returnHashMapWithChecksumAndFileName(String absolutePath)
            throws IOException, NoSuchAlgorithmException {

        File[] listOfFilesRav = new File(absolutePath).listFiles();
        Map<String, String> myMap = new HashMap<>();
        String key, value;
        String[] listOfResult;
        assert listOfFilesRav != null;

        String ext2;

        for (File file : listOfFilesRav) {
            ext2 = FilenameUtils.getExtension(file.toString());
            if (ext2.contains("mp3")) {
                listOfResult = this.getCheckSum(file);
                key = listOfResult[1];
//            System.out.println("key - " + key);
                value = listOfResult[0];
//            System.out.println("value - " + value);
                myMap.put(key, value);
            }
        }
        return myMap;
    }


    public void createStringArray(String absolutePath) throws IOException, NoSuchAlgorithmException {

        File[] listOfFilesRav = new File(absolutePath).listFiles();
        assert listOfFilesRav != null;

        String key, value;

        System.out.println("Size: " + listOfFilesRav.length);
        String[][] mysize = new String[listOfFilesRav.length][2];

        String[] answer;

        int index = 0;
        for (File file : listOfFilesRav) {
            answer = this.getCheckSum(file);
            key = answer[1];
//            System.out.println("key - " + key);
            value = answer[0];
//            System.out.println("value - " + value);

            mysize[index][1] = key;
            mysize[index][0] = value;
            index++;
        }

        int count = 0;
        for (int j = 0; j < mysize.length; j++) {
            for (int k = j + 1; k < mysize.length; k++) {
                if (mysize[k][1].equals(mysize[j][1])) {
                    System.out.println("Dup: " + mysize[j][0]);
                    count++;
                }
            }
        }
        System.out.println("Dup count: " + count);
    }

    public void get2MapsAndCompare(Map<String, String> smallMap, Map<String, String> bigMap) {
        int count = 0;
        String keyFromSmall;
        String valueFromSmall;
        for (Map.Entry<String, String> entry : smallMap.entrySet()) {
            keyFromSmall = entry.getKey();
            valueFromSmall = entry.getValue();
//            System.out.println(keyFromSmall);
            if (!bigMap.containsKey(keyFromSmall)) {
                System.out.println(valueFromSmall);
                count++;
            }
        }
        System.out.println("count " + count);
    }
}
