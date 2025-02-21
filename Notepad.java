package notepad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.filechooser.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

public class Notepad extends JFrame implements ActionListener {

    private JTextPane area;
    private JScrollPane scpane;
    String text = "";
    private boolean isRecording = false;
    private TargetDataLine targetDataLine;

    public Notepad() {
        super("Notepad");
        setSize(1950, 1050);
        
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar(); //menubar
        
        JMenu file = new JMenu("File"); //file menu
        
        JMenuItem newdoc = new JMenuItem("New");
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newdoc.addActionListener(this);
        
        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);
        
        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);
        
        JMenuItem saveAs = new JMenuItem("Save As");
        saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        saveAs.addActionListener(this);
        
        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);
        
        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        exit.addActionListener(this);
        
        JMenu edit = new JMenu("Edit");
        
        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);
        
        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);
        
        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);
        
        JMenuItem selectall = new JMenuItem("Select All");
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectall.addActionListener(this);
        
        JMenu about = new JMenu("Help");
        
        JMenuItem notepad = new JMenuItem("About Notepad");
        notepad.addActionListener(this);
        
        JMenu settings = new JMenu("Settings");

        JMenu theme = new JMenu("Theme");
        
        JMenuItem lightTheme = new JMenuItem("Light Theme");
        lightTheme.addActionListener(this);
        
        JMenuItem darkTheme = new JMenuItem("Dark Theme");
        darkTheme.addActionListener(this);

        JMenuItem blueTheme = new JMenuItem("Blue Theme");
        blueTheme.addActionListener(this);

        JMenuItem greenTheme = new JMenuItem("Green Theme");
        greenTheme.addActionListener(this);

        JMenuItem redTheme = new JMenuItem("Red Theme");
        redTheme.addActionListener(this);

        JMenuItem purpleTheme = new JMenuItem("Purple Theme");
        purpleTheme.addActionListener(this);

        JMenuItem yellowTheme = new JMenuItem("Yellow Theme");
        yellowTheme.addActionListener(this);

        JMenu fontMenu = new JMenu("Font");

        JMenuItem setFontSize = new JMenuItem("Set Font Size");
        setFontSize.addActionListener(this);

        JMenuItem plainFont = new JMenuItem("Plain Font");
        plainFont.addActionListener(this);

        JMenuItem boldFont = new JMenuItem("Bold Font");
        boldFont.addActionListener(this);

        JMenuItem italicFont = new JMenuItem("Italic Font");
        italicFont.addActionListener(this);

        JMenuItem serifFont = new JMenuItem("Serif Font");
        serifFont.addActionListener(this);

        JMenuItem sansSerifFont = new JMenuItem("Sans Serif Font");
        sansSerifFont.addActionListener(this);

        JMenuItem monospacedFont = new JMenuItem("Monospaced Font");
        monospacedFont.addActionListener(this);

        JMenuItem fontColor = new JMenuItem("Change Font Color");
        fontColor.addActionListener(this);

        JMenu insert = new JMenu("Insert");

        JMenuItem stickyNote = new JMenuItem("Add Sticky Note");
        stickyNote.addActionListener(this);

        JMenuItem insertPhoto = new JMenuItem("Insert Photo");
        insertPhoto.addActionListener(this);

        JMenuItem insertVideo = new JMenuItem("Insert Video");
        insertVideo.addActionListener(this);

        JMenuItem insertAudio = new JMenuItem("Insert Audio");
        insertAudio.addActionListener(this);

        JMenuItem recordAudio = new JMenuItem("Record Audio");
        recordAudio.addActionListener(this);
        
        area = new JTextPane();
        area.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        
        scpane = new JScrollPane(area); 
        scpane.setBorder(BorderFactory.createEmptyBorder());
        
        setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(about);
        menuBar.add(settings);
        menuBar.add(insert);

        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(print);
        file.add(exit);
        
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectall);
        
        about.add(notepad);

        settings.add(theme);
        settings.add(fontMenu);

        theme.add(lightTheme);
        theme.add(darkTheme);
        theme.add(blueTheme);
        theme.add(greenTheme);
        theme.add(redTheme);
        theme.add(purpleTheme);
        theme.add(yellowTheme);

        fontMenu.add(setFontSize);
        fontMenu.add(plainFont);
        fontMenu.add(boldFont);
        fontMenu.add(italicFont);
        fontMenu.add(serifFont);
        fontMenu.add(sansSerifFont);
        fontMenu.add(monospacedFont);
        fontMenu.add(fontColor);

        insert.add(stickyNote);
        insert.add(insertPhoto);
        insert.add(insertVideo);
        insert.add(insertAudio);
        insert.add(recordAudio);

        add(scpane, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        switch (command) {
            case "New":
                area.setText("");
                break;
            case "Open":
                openFile();
                break;
            case "Save":
                saveFile(false);
                break;
            case "Save As":
                saveFile(true);
                break;
            case "Print":
                printFile();
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Copy":
                text = area.getSelectedText();
                break;
            case "Paste":
                area.replaceSelection(text);
                break;
            case "Cut":
                text = area.getSelectedText();
                area.replaceSelection("");
                break;
            case "Select All":
                area.selectAll();
                break;
            case "About Notepad":
                JOptionPane.showMessageDialog(this, "Notepad Application\nVersion 1.0\nLast Update: 21 Feb 2025", "About Notepad", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Light Theme":
                area.setBackground(Color.WHITE);
                area.setForeground(Color.BLACK);
                break;
            case "Dark Theme":
                area.setBackground(Color.BLACK);
                area.setForeground(Color.WHITE);
                break;
            case "Blue Theme":
                area.setBackground(Color.BLUE);
                area.setForeground(Color.WHITE);
                break;
            case "Green Theme":
                area.setBackground(Color.GREEN);
                area.setForeground(Color.BLACK);
                break;
            case "Red Theme":
                area.setBackground(Color.RED);
                area.setForeground(Color.WHITE);
                break;
            case "Purple Theme":
                area.setBackground(new Color(128, 0, 128)); // Purple color
                area.setForeground(Color.WHITE);
                break;
            case "Yellow Theme":
                area.setBackground(Color.YELLOW);
                area.setForeground(Color.BLACK);
                break;
            case "Set Font Size":
                setFontSize();
                break;
            case "Plain Font":
                changeFontStyle(Font.PLAIN);
                break;
            case "Bold Font":
                changeFontStyle(Font.BOLD);
                break;
            case "Italic Font":
                changeFontStyle(Font.ITALIC);
                break;
            case "Serif Font":
                changeFontFamily("Serif");
                break;
            case "Sans Serif Font":
                changeFontFamily("SansSerif");
                break;
            case "Monospaced Font":
                changeFontFamily("Monospaced");
                break;
            case "Change Font Color":
                changeFontColor();
                break;
            case "Add Sticky Note":
                createStickyNote();
                break;
            case "Insert Photo":
                insertPhoto();
                break;
            case "Insert Video":
                insertVideo();
                break;
            case "Insert Audio":
                insertAudio();
                break;
            case "Record Audio":
                if (!isRecording) {
                    startRecording();
                } else {
                    stopRecording();
                }
                break;
        }
    }

    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
        chooser.addChoosableFileFilter(restrict);

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            try {
                FileReader reader = new FileReader(file);
                BufferedReader br = new BufferedReader(reader);
                area.read(br, null);
                br.close();
                area.requestFocus();
            } catch (Exception e) {
                System.out.print(e);
            }
        }
    }

    private void saveFile(boolean saveAs) {
        JFileChooser SaveAs = new JFileChooser();
        SaveAs.setApproveButtonText(saveAs ? "Save As" : "Save");
        int actionDialog = SaveAs.showSaveDialog(this);
        if (actionDialog != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File fileName = new File(SaveAs.getSelectedFile() + ".txt");
        BufferedWriter outFile = null;
        try {
            outFile = new BufferedWriter(new FileWriter(fileName));
            area.write(outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printFile() {
        try {
            area.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFontSize() {
        String sizeStr = JOptionPane.showInputDialog(this, "Enter Font Size:", area.getFont().getSize());
        if (sizeStr != null) {
            try {
                int newSize = Integer.parseInt(sizeStr);
                Font currentFont = area.getFont();
                area.setFont(new Font(currentFont.getName(), currentFont.getStyle(), newSize));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid font size entered.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void changeFontStyle(int newStyle) {
        Font currentFont = area.getFont();
        area.setFont(new Font(currentFont.getName(), newStyle, currentFont.getSize()));
    }

    private void changeFontFamily(String newFamily) {
        Font currentFont = area.getFont();
        area.setFont(new Font(newFamily, currentFont.getStyle(), currentFont.getSize()));
    }

    private void changeFontColor() {
        Color newColor = JColorChooser.showDialog(this, "Choose Font Color", area.getForeground());
        if (newColor != null) {
            area.setForeground(newColor);
        }
    }

    private void createStickyNote() {
        JFrame stickyNoteFrame = new JFrame("Sticky Note");
        stickyNoteFrame.setSize(300, 300);
        stickyNoteFrame.setLayout(new BorderLayout());

        JTextArea stickyNoteArea = new JTextArea();
        stickyNoteArea.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        stickyNoteArea.setLineWrap(true);
        stickyNoteArea.setWrapStyleWord(true);

        JScrollPane stickyScpane = new JScrollPane(stickyNoteArea);
        stickyScpane.setBorder(BorderFactory.createEmptyBorder());

        stickyNoteFrame.add(stickyScpane, BorderLayout.CENTER);
        stickyNoteFrame.setVisible(true);
    }

    private void insertPhoto() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                String imagePath = file.toURI().toURL().toString();
                HTMLEditorKit kit = (HTMLEditorKit) area.getEditorKit();
                HTMLDocument doc = (HTMLDocument) area.getDocument();
                kit.insertHTML(doc, area.getCaretPosition(), "<img src='" + imagePath + "'>", 0, 0, HTML.Tag.IMG);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void insertVideo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Video files", "mp4", "avi", "mov"));
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                String videoPath = file.toURI().toURL().toString();
                HTMLEditorKit kit = (HTMLEditorKit) area.getEditorKit();
                HTMLDocument doc = (HTMLDocument) area.getDocument();
                kit.insertHTML(doc, area.getCaretPosition(), "<video controls><source src='" + videoPath + "' type='video/mp4'></video>", 0, 0, HTML.Tag.OBJECT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void insertAudio() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Audio files", "mp3", "wav", "ogg"));
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                String audioPath = file.toURI().toURL().toString();
                HTMLEditorKit kit = (HTMLEditorKit) area.getEditorKit();
                HTMLDocument doc = (HTMLDocument) area.getDocument();
                kit.insertHTML(doc, area.getCaretPosition(), "<audio controls><source src='" + audioPath + "' type='audio/mpeg'></audio>", 0, 0, HTML.Tag.OBJECT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startRecording() {
        isRecording = true;
        new Thread(() -> {
            try {
                AudioFormat format = new AudioFormat(16000, 8, 2, true, true);
                DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
                if (!AudioSystem.isLineSupported(info)) {
                    JOptionPane.showMessageDialog(this, "Line not supported", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
                targetDataLine.open(format);
                targetDataLine.start();

                AudioInputStream audioStream = new AudioInputStream(targetDataLine);
                File audioFile = new File("recorded_audio.wav");
                AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, audioFile);

                // Insert the recorded audio into the JTextPane
                SwingUtilities.invokeLater(() -> {
                    try {
                        String audioPath = audioFile.toURI().toURL().toString();
                        HTMLEditorKit kit = (HTMLEditorKit) area.getEditorKit();
                        HTMLDocument doc = (HTMLDocument) area.getDocument();
                        kit.insertHTML(doc, area.getCaretPosition(), "<audio controls><source src='" + audioPath + "' type='audio/wav'></audio>", 0, 0, HTML.Tag.OBJECT);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void stopRecording() {
        isRecording = false;
        targetDataLine.stop();
        targetDataLine.close();
        JOptionPane.showMessageDialog(this, "Recording stopped and saved as recorded_audio.wav", "Recording Stopped", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new Notepad();
    }
}