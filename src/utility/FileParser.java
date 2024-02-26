package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileParser
{
    
    
    private final File file;
    
    
    private ArrayList<String> fileContent;
    
    
    public FileParser(File file) throws FileNotFoundException
    {
        this.file = file;
        checkFile();
    }
    
    
    public FileParser(String path) throws FileNotFoundException
    {
        this.file = new File(path);
        checkFile();
    }
    
    
    public FileParser(Path path) throws FileNotFoundException
    {
        this.file = path.toFile();
        checkFile();
    }
    
    
    private void checkFile() throws FileNotFoundException
    {
        File file = getFile();
        if (!file.canRead())
        {
            throw new FileNotFoundException("Can't read file: " + file.getAbsolutePath()); //todo Message string
        }
    }
    
    
    public File getFile()
    {
        return file;
    }
    
    
    public ArrayList<String> getFileContent()
    {
        return fileContent;
    }
    
    
    public void setFileContent(ArrayList<String> fileContent)
    {
        this.fileContent = fileContent;
    }
    
    
    public ArrayList<String> parse()
    {
        try
        {
            setFileContent(new ArrayList<>(Files.readAllLines(getFile().toPath())));
        }
        catch (IOException e)
        {
            throw new RuntimeException();
        }
        
        
        return getFileContent();
    }
    
    
    public BufferedReader getBufferedReader()
    {
        try
        {
            return Files.newBufferedReader(getFile().toPath());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    
}
