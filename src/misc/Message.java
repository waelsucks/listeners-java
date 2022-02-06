package misc;
import java.io.Serializable;
import javax.swing.Icon;

public class Message implements Serializable {
    
    String text;
    Icon icon;

    public Message(String text, Icon icon) {

        this.text = text;
        this.icon = icon;

    }

    public String getText() {
        return this.text;
    }

    public Icon getIcon() {
        return this.icon;
    }

}
