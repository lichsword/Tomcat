/***********************************************************************
 *
 *     Copyright: 2011, BAINA Technologies Co. Ltd.
 *     Classname: Main.java
 *     Author:    yuewang
 *     Description:    TODO
 *     History:
 *         1.  Date:   下午02:55:42
 *             Author:    yuewang
 *             Modifycation:    create the class.       
 *
 ***********************************************************************/

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;


/**
 * @author yuewang
 */
public class Main {
    /**
     *
     */
    private static final long serialVersionUID = -2561591521749339934L;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        /**
         * Problem: "-XstartOnFirstThread" in VM option, make sure launch in Cocoa UI thread.
         * Link: http://www.eclipse.org/swt/faq.php#carbonapp.
         */
        Display display = new Display();
        Shell shell = new Shell(display, SWT.SHELL_TRIM);
        shell.setLayout(new FillLayout());
        Main instance = new Main(shell);
        shell.setText("window.title");
        setShellSize(instance, shell);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        instance.dispose();
        display.dispose();
    }

    /**
     * Create the frame.
     */
    public Main(Composite parent) {
        initContentView(parent);
    }

    /**
     * @param instance
     * @param shell
     */
    static void setShellSize(Main instance, Shell shell) {
        Point size = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        Rectangle monitorArea = shell.getMonitor().getClientArea();
        shell.setSize(Math.min(size.x, monitorArea.width), Math.min(size.y, monitorArea.height));
    }

    public void dispose() {
        /*
         * Destroy any shells that may have been created
		 * by the Shells tab.  When a shell is disposed,
		 * all child shells are also disposed.  Therefore
		 * it is necessary to check for disposed shells
		 * in the shells list to avoid disposing a shell
		 * twice.
		 */
        // TODO
//        if (shellTab != null) shellTab.closeAllShells ();
//        shellTab = null;
//        tabFolder = null;
//        freeResources();
    }

    /* Constants */
    final int SASH_WIDTH = 3;
    final int SASH_LIMIT = 20;

    private void initContentView(Composite parent) {

        // group.
        Group sashGroup = new Group(parent, SWT.NONE);
        FillLayout layout = new FillLayout();
        layout.marginHeight = layout.marginWidth = 5;
        sashGroup.setLayout(layout);
        sashGroup.setText("Sash group");

        // comp in group.
        final Composite sashComp = new Composite(sashGroup, SWT.BORDER);

        // create each text widgets
        final Text text1 = new Text(sashComp, SWT.MULTI);
        text1.setText("text 11111");

        final Text text2 = new Text(sashComp, SWT.MULTI);
        text2.setText("text 2222");

        final Text text3 = new Text(sashComp, SWT.MULTI);
        text3.setText("text 3333");

        // create sashes
        final Sash vSash = new Sash(sashComp, SWT.VERTICAL);
        final Sash hSash = new Sash(sashComp, SWT.HORIZONTAL);



           /* Add the listeners */
        hSash.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                Rectangle rect = vSash.getParent().getClientArea();
                event.y = Math.min(Math.max(event.y, SASH_LIMIT), rect.height - SASH_LIMIT);
                if (event.detail != SWT.DRAG) {
                    hSash.setBounds(event.x, event.y, event.width, event.height);
                    layout(sashComp, hSash, vSash, text1, text2, text3);
                }
            }
        });
        vSash.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                Rectangle rect = vSash.getParent().getClientArea();
                event.x = Math.min(Math.max(event.x, SASH_LIMIT), rect.width - SASH_LIMIT);
                if (event.detail != SWT.DRAG) {
                    vSash.setBounds(event.x, event.y, event.width, event.height);
                    layout(sashComp, hSash, vSash, text1, text2, text3);
                }
            }
        });
        sashComp.addControlListener(new ControlAdapter() {
            public void controlResized(ControlEvent event) {
                resized(sashComp, hSash, vSash, text1, text2, text3);
            }
        });
    }

    /**
     * Layout the list and text widgets according to the new positions of the
     * sashes..events.SelectionEvent
     */
    void layout(Composite sashComp, Sash hSash, Sash vSash, Text text1, Text text2, Text text3) {

        Rectangle clientArea = sashComp.getClientArea();
        Rectangle hSashBounds = hSash.getBounds();
        Rectangle vSashBounds = vSash.getBounds();

        text1.setBounds(0, 0, vSashBounds.x, hSashBounds.y);
        text2.setBounds(vSashBounds.x + vSashBounds.width, 0, clientArea.width - (vSashBounds.x + vSashBounds.width), hSashBounds.y);
        text3.setBounds(0, hSashBounds.y + hSashBounds.height, clientArea.width, clientArea.height - (hSashBounds.y + hSashBounds.height));

        /**
         * If the horizontal sash has been moved then the vertical sash is
         * either too long or too short and its size must be adjusted.
         */
        vSashBounds.height = hSashBounds.y;
        vSash.setBounds(vSashBounds);
    }

    /**
     * Handle the shell resized event.
     */
    void resized(Composite sashComp, Sash hSash, Sash vSash, Text text1, Text text2, Text text3) {

        /* Get the client area for the shell */
        Rectangle clientArea = sashComp.getClientArea();

        /*
        * Make list 1 half the width and half the height of the tab leaving room for the sash.
        * Place list 1 in the top left quadrant of the tab.
        */
        Rectangle text1Bounds = new Rectangle(0, 0, (clientArea.width - SASH_WIDTH) / 2, (clientArea.height - SASH_WIDTH) / 2);
        text1.setBounds(text1Bounds);

        /*
        * Make list 2 half the width and half the height of the tab leaving room for the sash.
        * Place list 2 in the top right quadrant of the tab.
        */
        text2.setBounds(text1Bounds.width + SASH_WIDTH, 0, clientArea.width - (text1Bounds.width + SASH_WIDTH), text1Bounds.height);

        /*
        * Make the text area the full width and half the height of the tab leaving room for the sash.
        * Place the text area in the bottom half of the tab.
        */
        text3.setBounds(0, text1Bounds.height + SASH_WIDTH, clientArea.width, clientArea.height - (text1Bounds.height + SASH_WIDTH));

        /* Position the sashes */
        vSash.setBounds(text1Bounds.width, 0, SASH_WIDTH, text1Bounds.height);
        hSash.setBounds(0, text1Bounds.height, clientArea.width, SASH_WIDTH);
    }
}
