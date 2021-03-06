/**
 * @author andre freller
 * Print-it library
 */

package nl.kennisnet.arena.client.util;

import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.Timer;

public class PrintUtil {

    /**
     * If true, use a Timer instead of DeferredCommand to print the internal fram
     */
    private static boolean useTimer     = false;

    /**
     * Time in seconds to wait before printing the internal frame when using Timer
     */
    private static int timerDelay       = 2;


    public static native void it() /*-{
        $wnd.print();
    }-*/;

    public static void it(UIObject obj) {
        it("", obj);
    }

    public static void it(Element element) {
        it("", element);
    }

    public static void it(String style, UIObject obj) {
        it(style, obj.getElement());
    }

    public static void it(String docType, String style, UIObject obj) {
        it(docType, style, obj.getElement());
    }

    public static void it(String style, Element element) {
        it("", style, element);
    }

    public static void it(String docType, String style, Element element) {
        updateFieldsDOM(element);
        it(docType, style, DOM.toString(element));
    }

    public static void it(String docType, String style, String it) {
        it(docType
           +"<html>"
           +"<head>"
           +"<meta http-equiv=\"Content-Type\"          content=\"text/html; charset=utf-8\">"
           +"<meta http-equiv=\"Content-Style-Type\"    content=\"text/css\">"
           +    style
           +"</head>"+"<body>"
           +    it
           +"</body>"+
           "</html>");
    }

    public static void it(String html) {
        try {
            buildFrame(html);
            if (PrintUtil.useTimer) {
                Timer timer     = new Timer() {
                        public void run() {
                            printFrame();
                        }
                    };
                timer.schedule(PrintUtil.timerDelay * 1000);
            } else {
                DeferredCommand.addCommand(new Command() {
                        public void execute() {
                            printFrame();
                        }
                    });
            }

        } catch (Exception exc) {
            Window.alert(exc.getMessage());
        }
    }

    public static native void buildFrame(String html) /*-{
        var frame = $doc.getElementById('__printingFrame');
        if (!frame) {
            $wnd.alert("Error: Can't find printing frame.");
            return;
        }
        var doc = frame.contentWindow.document;
        doc.open();
        doc.write(html);
        doc.close();

    }-*/;

    public static native void printFrame() /*-{
        var frame = $doc.getElementById('__printingFrame');
        frame = frame.contentWindow;
        frame.focus();
        frame.print();
    }-*/;

    // Great contribution from mgrushinskiy to print form element
    public static void updateFieldsDOM(Element dom) {
        NodeList<com.google.gwt.dom.client.Element> textareas   = dom.getElementsByTagName("textarea");
        NodeList<com.google.gwt.dom.client.Element> inputs      = dom.getElementsByTagName("input");
        NodeList<com.google.gwt.dom.client.Element> options     = dom.getElementsByTagName("option");


        if (textareas != null) {
            for (int cii = 0;  cii < textareas.getLength();  cii++) {
                updateDOM(TextAreaElement.as(textareas.getItem(cii)));
            }
        }
        if (inputs != null) {
            for (int cii = 0;  cii < inputs.getLength();  cii++) {
                updateDOM(InputElement.as(inputs.getItem(cii)));
            }
        }
        if (options != null) {
            for (int cii = 0;  cii < options.getLength();  cii++) {
                updateDOM(OptionElement.as(options.getItem(cii)));
            }
        }
    }

    public static void updateDOM(InputElement item) {
        item.setDefaultValue(item.getValue());
        item.setDefaultChecked(item.isDefaultChecked());
    }

    public static void updateDOM(TextAreaElement item) {
        item.setDefaultValue(                   item.getValue());
        item.setInnerText(item.getValue());
    }

    public static void updateDOM(OptionElement item) {
        item.setDefaultSelected(                item.isSelected());
    }

} // end of class Print