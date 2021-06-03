import javax.swing.JButton;
import javax.swing.JFileChooser;

public class FileChooser {

    public FileChooser()
    {
    JButton button = new JButton("Click me");
    JFileChooser fc = new JFileChooser();
    fc.setCurrentDirectory(new java.io.File("C://Desktop"));
    fc.setDialogTitle("Hello World");
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    if(fc.showOpenDialog(button)==JFileChooser.APPROVE_OPTION)
    {
        //
    }
    System.out.println(fc.getSelectedFile().getAbsolutePath());
    }
}
