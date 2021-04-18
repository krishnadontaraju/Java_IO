import java.io.File;

public class FileUtilities {
    public static boolean deleteFile(File fileToBeDeleted){
        File[] allContent = fileToBeDeleted.listFiles();
        if(allContent != null) {
            for (File file : allContent) {
                deleteFile(file);
            }
        }
        return fileToBeDeleted.delete();
    }
}
