package utility.IO;

import resources.Messages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


/**
 * @Summary: This class and its instance methods are used to read and write files by line.
 * @Author: Finn Lindig
 * @Since: 26.02.2024
 */
public class FileParser
{
    
    
    /**
     * @Summary: The file to read from.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private final File file;
    
    
    /**
     * @Summary: The content of {@link #file}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private ArrayList<String> fileContent;
    
    
    /**
     * @param file the {@link File} to set {@link #file} to.
     * @throws FileNotFoundException if file cannot be read.
     * @Precondition: file can be read.
     * @Postcondition: {@link #file} is set to file and the class is initialized. All methods will function correctly.
     * @Summary: Sets {@link #file} to file and checks if file can be read.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public FileParser(File file) throws FileNotFoundException
    {
        this.file = file;
        checkFile();
    }
    
    
    /**
     * @param filepath the filepath to set {@link #file} to.
     * @throws FileNotFoundException if file cannot be read.
     * @Precondition: The {@link File} under path can be read.
     * @Postcondition: {@link #file} is set and the class is initialized. All methods will function correctly.
     * @Summary: Sets {@link #file} to filepath param and checks if {@link #file} can be read.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public FileParser(String filepath) throws FileNotFoundException
    {
        this.file = new File(filepath);
        checkFile();
    }
    
    
    /**
     * @param path the {@link Path} to set {@link #file} to.
     * @throws FileNotFoundException if file cannot be read.
     * @Precondition: The {@link File} under filepath can be read.
     * @Postcondition: {@link #file} is set and the class is initialized. All methods will function correctly.
     * @Summary: Sets {@link #file} to path param and checks if {@link #file} can be read.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public FileParser(Path path) throws FileNotFoundException
    {
        this.file = path.toFile();
        checkFile();
    }
    
    
    /**
     * @throws FileNotFoundException if {@link #file} cannot be read.
     * @Precondition: {@link #file} is not null.
     * @Postcondition: {@link #file} can be read or a {@link FileNotFoundException} is thrown.
     * @Summary: Checks if {@link #file} can be read and throws an Exception if it cannot.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private void checkFile() throws FileNotFoundException
    {
        File file = getFile();
        if (!file.canRead())
        {
            throw new FileNotFoundException(Messages.AUSGABE_KANN_DATEI_NICHT_LESEN + file.getAbsolutePath());
        }
    }
    
    /**
     * @return {@link #file}
     * @Summary: Getter for {@link #file}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public File getFile()
    {
        return file;
    }
    
    /**
     * @return {@link #fileContent}
     * @Precondition: The method {@link #parse()} has been called.
     * @Postcondition: The return {@link #fileContent} is not null.
     * @Summary: Getter for {@link #fileContent}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public ArrayList<String> getFileContent()
    {
        return fileContent;
    }
    
    
    /**
     * @param fileContent the {@link ArrayList<String>} to set {@link #fileContent} to.
     * @Summary: Setter for {@link #fileContent}
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    private void setFileContent(ArrayList<String> fileContent)
    {
        this.fileContent = fileContent;
    }
    
    
    /**
     * @return {@link #fileContent}
     * @Precondition: {@link #file} is not null.
     * @Postcondition: The return {@link #fileContent} is not null.
     * @Summary: Sets {@link #fileContent} to the content of {@link #file} and returns same.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public ArrayList<String> parse()
    {
        try
        {
            setFileContent(new ArrayList<>(Files.readAllLines(getFile().toPath())));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        
        
        return getFileContent();
    }
    
    
    /**
     * @return a {@link BufferedReader} of {@link #file}.
     * @Precondition: {@link #file} is not null and can be read.
     * @Postcondition: The return {@link BufferedReader} is not null and can read from {@link #file}.
     * @Summary: Returns a {@link BufferedReader} of {@link #file}.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
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
    
    
    /**
     * @param filePath The path to save this {@link FileParser#parse()} to.
     * @throws IOException if filePath cannot be written to.
     * @Precondition: filePath is not null and filePath exists and canWrite.
     * @Postcondition: content is written to filePath.
     * @Summary: Writes the contents of this {@link FileParser#parse()} to filePath.
     * @Author: Finn Lindig
     * @Since: 26.02.2024
     */
    public void save(String filePath) throws IOException
    {
        File file = new File(filePath);
        file.createNewFile();
        Files.write(file.toPath(), parse());
    }
    
}
