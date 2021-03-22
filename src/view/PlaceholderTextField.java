package view;

import java.awt.*;
import javax.swing.*;

public class PlaceholderTextField extends JTextField {

	private static final long serialVersionUID = 1L;
    private String placeholder;  

    public PlaceholderTextField(final String pText) {super(pText);}

    public String getPlaceholder() {return placeholder;}

    public void setPlaceholder(final String s) {placeholder = s;}
    
    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);
        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {return;}
        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics().getMaxAscent() + getInsets().top);
    }    
}