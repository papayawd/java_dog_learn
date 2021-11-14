import encrypt.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


class Son extends JFrame
{
    JSplitPane split = new JSplitPane();
    JFileChooser chooser = new JFileChooser();
    JButton button = new JButton("校验");

    JPanel md5_panel = new JPanel();
    JPanel sha1_panel = new JPanel();
    JPanel right_panel = new JPanel();
    JPanel left_panel = new JPanel();

    JLabel md5_label = new JLabel("md5:");
    JLabel sha1_label = new JLabel("sha1:");

    JTextField md5_text = new JTextField(25);
    JTextField sha1_text = new JTextField(25);

    MD5Util md5 = new MD5Util();
    SHA1Util sha1 = new SHA1Util();


    void Debug(String s)
    {
        System.out.format("%s","[Debug] "+s+"\n");
    }

    void init()
    {
        setVisible(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    String readFileByBytes(String filePath)
    {
        File file = new File(filePath);
        String str = "";
        int cnt;
        InputStream in = null;
        try {
            // 一次读一个字节
            in = new FileInputStream(file);
            while ((cnt = in.read()) != -1) {
                //Debug(String.valueOf(cnt));
                str += String.valueOf((char)(cnt));
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return str;
        }
        //Debug(str);
        return str;
    }


    public Son()
    {


        button.addActionListener(new buttonActionListener());

        md5_panel.add(md5_label);
        md5_panel.add(md5_text);
        sha1_panel.add(sha1_label);
        sha1_panel.add(sha1_text);

        left_panel.add(chooser);
        left_panel.add(button);
        right_panel.add(md5_panel);
        right_panel.add(sha1_panel);


        split.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        split.setLeftComponent(button);
        split.setRightComponent(right_panel);
        setContentPane(split);


    }
    class buttonActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            FileSystemView fsv;
            int result;
            File file = null;
            String path = null;

            chooser.setSize(50,10);
            chooser.setDialogTitle("选择文件");
            fsv = FileSystemView.getFileSystemView();
            chooser.setCurrentDirectory(fsv.getHomeDirectory());
            chooser.setApproveButtonText("确定");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            result = chooser.showOpenDialog(null);
            if (JFileChooser.APPROVE_OPTION == result) {
                path=chooser.getSelectedFile().getPath();
                Debug("path : " + path);
            }
            md5_text.setText(md5.encode(readFileByBytes(path)));
            sha1_text.setText(sha1.encode(readFileByBytes(path)));
        }
    }
}

public class Main extends JFrame
{

    static Main main = new Main();
    static Son son = new Son();

    JSplitPane split = new JSplitPane();

    JRadioButton button_base64 = new JRadioButton("base64",true);
    JRadioButton button_md5 = new JRadioButton("md5");
    JRadioButton button_sha1 = new JRadioButton("sha1");
    JRadioButton button_rc4 = new JRadioButton("rc4");
    JRadioButton button_aes = new JRadioButton("Aes");
    JRadioButton button_des = new JRadioButton("Des");
    ButtonGroup group = new ButtonGroup();

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();

    String modle = "base64";

    TextArea area_encode = new TextArea("Hello world");
    TextArea area_decode = new TextArea("SGVsbG8gd29ybGQ=");
    JTextField area_key = new JTextField(10);

    JButton button_encode = new JButton("encode");
    JButton button_decode = new JButton("decode");

    BASE64Util b64 = new BASE64Util();
    MD5Util md5 = new MD5Util();
    SHA1Util sha1 = new SHA1Util();




    void Debug(String s)
    {
        System.out.format("%s","[Debug] "+s+"\n");
    }

    void init()
    {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar bar = new JMenuBar();
        JMenu tools = new JMenu("Tools");
        JMenuItem file_check = new JMenuItem("文件校验");
        file_check.addActionListener(new FileCheckActionListener());
        tools.add(file_check);
        bar.add(tools);
        setJMenuBar(bar);
    }
    void ChangeModle(String m)
    {
        modle = m;
    }


    public Main()
    {
        init();

        panel1.setSize(100,400);
        panel2.setSize(400,400);
        panel2.setLayout(new FlowLayout());
        split.setOrientation(JSplitPane.VERTICAL_SPLIT);
        split.setTopComponent(panel1);
        split.setBottomComponent(panel2);
        setContentPane(split);

        button_base64.addActionListener(new base64ActionListener());
        button_md5.addActionListener(new md5ActionListener());
        button_sha1.addActionListener(new sha1ActionListener());
        button_rc4.addActionListener(new rc4ActionListener());
        button_aes.addActionListener(new aesActionListener());
        button_des.addActionListener(new desActionListener());


        button_encode.addActionListener(new encodeActionListener());
        button_decode.addActionListener(new decodeActionListener());

        group.add(button_base64);
        group.add(button_md5);
        group.add(button_sha1);
        group.add(button_aes);
        group.add(button_des);
        group.add(button_rc4);

        panel1.add(button_base64);
        panel1.add(button_md5);
        panel1.add(button_sha1);
        panel1.add(button_aes);
        panel1.add(button_des);
        panel1.add(button_rc4);


        panel2.add(area_encode);
        panel2.add(button_encode);
        panel2.add(area_key);
        panel2.add(area_decode);
        panel2.add(button_decode);



    }

    class base64ActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            ChangeModle("base64");
        }
    }
    class md5ActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) { ChangeModle("md5"); }
    }
    class sha1ActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            ChangeModle("sha1");
        }
    }

    class rc4ActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            ChangeModle("rc4");
        }
    }
    class aesActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            ChangeModle("aes");
        }
    }
    class desActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            ChangeModle("des");
        }
    }

    class encodeActionListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            String encode_data = area_encode.getText();
            String decode_data;
            String key;
            switch(modle)
            {
                case "base64":
                    decode_data = b64.encode(encode_data);
                    break;
                case "md5":
                    decode_data = md5.encode(encode_data);
                    break;
                case "sha1":
                    decode_data = sha1.encode(encode_data);
                    break;
                case "rc4":

                    key = area_key.getText();
                    decode_data = RC4Util.encry_RC4_string(encode_data,key);
                    break;
                case "aes":
                    Debug("aes");
                    key = area_key.getText();
                    decode_data = AESUtil.encrypt(encode_data,key);
                    break;
                    /*
                    * AES加密之后为大写十六进制
                    * */
                case "des":
                    key = area_key.getText();
                    decode_data = DESUtil.encrypt(encode_data.getBytes(StandardCharsets.UTF_8),key.getBytes(StandardCharsets.UTF_8));
                    break;
                    /*
                     * DES加密之后为大写十六进制
                     * */
                default:
                    throw new IllegalStateException("Unexpected value: " + modle);
            }

            area_decode.setText(decode_data);
        }
    }
    class decodeActionListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            String decode_data = area_decode.getText();
            String encode_data = null;
            String key;
            switch(modle)
            {
                case "base64":
                    encode_data = b64.decode(decode_data);
                    break;
                case "md5":
                    //Debug("md5");
                    break;
                case "sha1":
                    //Debug("sha1");
                    break;
                case "rc4":
                    key = area_key.getText();
                    encode_data = RC4Util.decry_RC4(decode_data,key);
                    break;
                case "aes":
                    Debug("aes");
                    key = area_key.getText();
                    encode_data = AESUtil.decrypt(decode_data,key);
                    break;
                    /*
                     * AES解密之后为大写十六进制
                     * */
                case "des":
                    key = area_key.getText();
                    encode_data = DESUtil.decrypt(decode_data,key.getBytes(StandardCharsets.UTF_8));
                    break;
                    /*
                    * DES解密之后为大写十六进制
                    * */
                default:
                    throw new IllegalStateException("Unexpected value: " + modle);
            }
            area_encode.setText(encode_data);
        }
    }
    static class FileCheckActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            son.setVisible(true);
        }
    }



    public static void main(String[] args)
    {


        main.setTitle("En_Decrypt");
        main.setSize(630,530);
        son.setSize(570,130);

        //main.pack();

    }



}
