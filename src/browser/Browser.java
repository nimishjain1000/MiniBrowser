
package browser;

import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Authenticator.*;
import java.net.URL;
public class Browser extends JFrame implements HyperlinkListener,ActionListener{

    public static void main(String[] args) {
        if(args.length==0)
        {
Browser b=            new Browser("http://google.com");
            
        }
        else
            
        {
         Browser c=   new Browser(args[0]);
        }
    }
    
    private JTextField urlField;
    private JEditorPane htmlpane;
    private String initialURL;
    JFrame f;
    
    public Browser(String initialURL)
    {
        super("Swing Browser");
                this.initialURL=initialURL;
//               addWindowListener(new ExitListener());//*********************************************
                //f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
               // WindowUtilities.setNativeLookAndFeel();
                
                
                JPanel topPanel=new JPanel();
                topPanel.setBackground(Color.red);
                JLabel urlLabel=new JLabel("URl");
                urlField=new JTextField(30);
                urlField.setText(initialURL);
                urlField.addActionListener(this);
                topPanel.add(urlLabel);
                topPanel.add(urlField);
                getContentPane().add(topPanel,BorderLayout.NORTH);
                
                try{
                    htmlpane=new JEditorPane(initialURL);
                    htmlpane.setEditable(false);
                    htmlpane.addHyperlinkListener(this);
                    JScrollPane scrollpane=new JScrollPane(htmlpane);
                    getContentPane().add(scrollpane,BorderLayout.CENTER);
                    
                    
                }catch(IOException e)
                {
                   warnUser("cant follow link for"+initialURL+","+e);
                }
                
                Toolkit t=getToolkit();
                Dimension screensize=t.getScreenSize();
                int w=screensize.width*8/10;
                int h=screensize.height*8/10;
                setBounds(w/8,h/8,w,h);
                setVisible(true);
                
                
    }
    public void actionPerformed(ActionEvent event)
    {
        String url;
        if(event.getSource()==urlField)
        {url=urlField.getText();}
        
        else
        url=initialURL;
        
        try
        { URL a=new URL(url);
            htmlpane.setPage(a);//****************************************
            urlField.setText(url);
            
        }
        catch(IOException e)
        {
            warnUser("cant follow link to"+url+","+e);
        }
    }
    public void hyperlinkUpdate(HyperlinkEvent event)
    {
        if(event.getEventType()==HyperlinkEvent.EventType.ACTIVATED)
        {
            try{
                htmlpane.setPage(event.getURL());
                urlField.setText(event.getURL().toExternalForm());
                
            }
            catch(IOException e)
            {
                warnUser("cant follow link to"+event.getURL().toExternalForm()+","+e);
            }
        }
    }
    public void warnUser(String message)
    {
        JOptionPane.showMessageDialog(this,message,"Error",JOptionPane.ERROR_MESSAGE);
    }
    
}
