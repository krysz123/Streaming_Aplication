import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Flusso extends JFrame {
    private JPanel mainPanel;
    private JList<Stream> listaStreamow;
    private JTextField textFieldMessage;
    private JList<StreamChatMessage> MessageList;
    private JButton wyślijButton;
    private JButton zablokujStreamButton;
    private JButton usuńWiadomośćButton;
    private JButton likeButton;
    private JButton shareButton;
    private JLabel titleLabel;
    private JLabel viewersCountLabel;
    private JPanel description;
    private JLabel descriptionLabel;
    private JLabel wyslijWidaomoscLabel;
    private JPanel picturePanel;
    private JPanel picture;
    private JLabel picture2;

    ArrayList<Stream> likedStreams;
    Stream selectedStream;

    ArrayList<Stream> sharedStreams;
    ArrayList<Stream> streams;

    User currentUser;
    StreamChatMessage selectedMessage;


    public Flusso() {
        super("Flusso");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setBounds(0, 0, 1200, 700);

        Random rand = new Random();
        streams = new ArrayList<>();
        likedStreams=new ArrayList<>();
        sharedStreams= new ArrayList<>();
        hideStreamPanel();
        currentUser = new User("admin");
        currentUser.setUserType(UserType.ADMIN);

        User user1 = new User("PixelPenguin");
        User user2 = new User("CodingCouch");
        User user3 = new User("SketchyStuff");
        User user4 = new User("SciFiFanatic");
        User user5 = new User("BitcoinBob");

        int viewers=getRandomViewers(1000);
        StreamInteraction interactions = new StreamInteraction(getRandomLikes(viewers), getRandomShares(viewers/2));

        streams.add(new Stream("Pixelowe Przygody", "Stream w tematyce gier komputerowych", user1, viewers , interactions, "img/games2.jpg"));
        viewers=getRandomViewers(1000);
        interactions = new StreamInteraction(getRandomLikes(viewers), getRandomShares(viewers/2));
        streams.add(new Stream("Koduj i relaksuj się", "Naucz się programowania", user2, viewers, interactions, "img/code.jpg"));
        viewers=getRandomViewers(1000);
        interactions = new StreamInteraction(getRandomLikes(viewers), getRandomShares(viewers/2));
        streams.add(new Stream("Ewolucja sztuki", "Zrozumieć sztukę", user3, viewers, interactions, "img/art.jpg"));
        viewers=getRandomViewers(1000);
        interactions = new StreamInteraction(getRandomLikes(viewers), getRandomShares(viewers/2));
        streams.add(new Stream("Naukowa fikcja w soboty", "Stream poświęcony dyskusji o filmach Sci-fi", user4, viewers, interactions, "img/scifi.jpg"));
        viewers=getRandomViewers(1000);
        interactions = new StreamInteraction(getRandomLikes(viewers), getRandomShares(viewers/2));
        streams.add(new Stream("Porozmawiajmy o kryptowalutach", "Porady inwestycyjne", user5,viewers , interactions, "img/crypto.jpg"));



        for (int i = 0; i < streams.size(); i++) {
            ArrayList<StreamChatMessage> messages = new ArrayList<>();
            int hours = rand.nextInt(24);
            int minutes = rand.nextInt(60);
            for (int j = 0; j < 10; j++) {
                User user = new User("User" + j);
                String message = "This is a message from User " + j;

                int seconds = j*3;

                Time time = new Time(hours, minutes, seconds);
                StreamChatMessage chatMessage = new StreamChatMessage(user, message, time);
                messages.add(chatMessage);
            }
            streams.get(i).setMessages(messages);
        }

        updateStreamsList();

        listaStreamow.setCellRenderer(new StreamListCellRenderer());
        MessageList.setCellRenderer(new MessageListCellRender());
        listaStreamow.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                    try {
                    likeButton.setVisible(true);
                    shareButton.setVisible(true);
                    viewersCountLabel.setVisible(true);
                    descriptionLabel.setVisible(true);
                    MessageList.setVisible(true);
                    wyslijWidaomoscLabel.setVisible(true);
                    textFieldMessage.setVisible(true);
                    wyślijButton.setVisible(true);
                    picturePanel.setVisible(true);
                    selectedStream = listaStreamow.getSelectedValue();
                    titleLabel.setText(selectedStream.getTitle());
                    viewersCountLabel.setText("Liczba widzów: " + selectedStream.getViewerCount());
                    descriptionLabel.setText("Opis: " + selectedStream.getDescription());
                    likeButton.setText("\uD83D\uDC4D " + selectedStream.getInteractions().getLikes());
                    shareButton.setText("⤤" + selectedStream.getInteractions().getShares());
                    updateMessageList();
                    if (!likedStreams.contains(selectedStream)) {
                        likeButton.setBackground(new JButton().getBackground());
                    } else {
                        likeButton.setBackground(Color.green);
                    }

                BufferedImage img = null;
                try {
                    img = ImageIO.read(getClass().getResourceAsStream(selectedStream.getImageURl()));
                    picture2.setIcon(new ImageIcon(img));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                }catch (Exception err){

               }
            }
        });
        MessageList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    selectedMessage  = MessageList.getSelectedValue();
                } catch (Exception err) {

                }
            }
        });



        likeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(likedStreams.contains(selectedStream)) {
                    likedStreams.remove(selectedStream);
                    likeButton.setText("\uD83D\uDC4D "+selectedStream.unlikeStream());
                    likeButton.setBackground(new JButton().getBackground());
                } else {
                    likedStreams.add(selectedStream);
                    likeButton.setText("\uD83D\uDC4D "+selectedStream.likeStream());
                    likeButton.setBackground(Color.green);
                }
            }
        });

        shareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!sharedStreams.contains(selectedStream)) {
                    sharedStreams.add(selectedStream);
                    shareButton.setText("⤤"+selectedStream.shareStream());
                }
            }
        });

        zablokujStreamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Tak","Nie"};

                 if(selectedStream==null) {
                    JOptionPane.showMessageDialog(null,"Nie wybrano transmisji");
                    return;
                }
                int choice = JOptionPane.showOptionDialog(null, "Czy napewno chesz zablokować transmisję?",
                        "Potwierdzenie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);

                if(choice == JOptionPane.YES_OPTION) {
                    System.out.println(selectedStream!=null);
                    streams.remove(selectedStream);
                    updateStreamsList();
                    hideStreamPanel();
                }
            }
        });

        wyślijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String message = textFieldMessage.getText();
            if(!message.equals("")) {
                selectedStream.getMessages().add(new StreamChatMessage(currentUser, message, new Time()));
                updateMessageList();
                textFieldMessage.setText("");
            }
            }
        });

        usuńWiadomośćButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(selectedMessage==null) {
                    JOptionPane.showMessageDialog(null,"Nie wybrano wiadomości");
                    return;
                }
                selectedStream.getMessages().remove(selectedMessage);
                updateMessageList();
            }
        });

    }
    public void hideStreamPanel(){
        likeButton.setVisible(false);
        shareButton.setVisible(false);
        viewersCountLabel.setVisible(false);
        descriptionLabel.setVisible(false);
        MessageList.setVisible(false);
        wyslijWidaomoscLabel.setVisible(false);
        textFieldMessage.setVisible(false);
        wyślijButton.setVisible(false);
        picturePanel.setVisible(false);
    }


    private void updateStreamsList(){
        DefaultListModel<Stream> listModel = new DefaultListModel<>();

        for (Stream stream : streams) {
            listModel.addElement(stream);
        }
        listaStreamow.setModel(listModel);
    }

    private void updateMessageList(){
        DefaultListModel<StreamChatMessage> listModel = new DefaultListModel<>();

        for (StreamChatMessage message : selectedStream.getMessages()) {
            listModel.addElement(message);
        }
        MessageList.setModel(listModel);
    }


    private int getRandomViewers(int max){
        Random rand = new Random();
        return  rand.nextInt(max)+10;
    }

    private int getRandomLikes(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }

    private int getRandomShares(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }



    private class StreamListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Stream stream = (Stream) value;
            label.setText(stream.getAuthor().getUsername());

            return label;
        }
    }

    private class MessageListCellRender extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            StreamChatMessage message = (StreamChatMessage) value;
            if(message.getUser().getUserType()==UserType.ADMIN)
                label.setBackground(Color.RED);
            label.setText(message.getTime()+" "+message.getUser().getUsername()+":\n "+message.getMessage());
            return label;
        }
    }


    public static void main(String[] args) {
        Flusso project = new Flusso();
        project.setVisible(true);
    }

}
