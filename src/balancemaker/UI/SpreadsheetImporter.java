/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancemaker.ui;
// Will work with google apis plugged in.
/*import balancemaker.Manager;
import balancemaker.TransactionBuilder;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;*/

/**
 *
 * @author Red
 */
public class SpreadsheetImporter {
    // Not currently working.
/*
    private final String USERNAME;
    private final String PASSWORD;
    private final String SPREADSHEETS_FEED;
    private final String SPREADSHEET_CODE;
    private ListFeed list;

    private final SpreadsheetService service =
            new SpreadsheetService("redcat-balancemaker-v0.0.1");

    public SpreadsheetImporter() {
        this.SPREADSHEET_CODE = "";
        this.SPREADSHEETS_FEED = "https://spreadsheets.google.com/feeds/spreadsheets/private/full/";
        this.PASSWORD = "";
        this.USERNAME = "";
    }

    private void authenticate() throws AuthenticationException, MalformedURLException {
        service.setUserCredentials(USERNAME, PASSWORD);
    }
    
    private void loadSheet() throws IOException, ServiceException {
        URL metaFeed = new URL(SPREADSHEETS_FEED + SPREADSHEET_CODE);
        SpreadsheetEntry spread = service.getEntry(metaFeed, SpreadsheetEntry.class);
        URL listFeed = spread.getDefaultWorksheet().getListFeedUrl();
        list = service.getFeed(listFeed, ListFeed.class);
    }

    private void printSheet() {
        for (ListEntry e : list.getEntries()) {
            if (e.getCustomElements().getValue("magazin") == null) continue;
            for (String tag : e.getCustomElements().getTags()) {
                System.out.println("     " + tag + ": " + e.getCustomElements().getValue(tag));
            }
        }
    }
    
    private void populateFromSheet(Manager m) {
        for (ListEntry e : list.getEntries()) {
            CustomElementCollection ec = e.getCustomElements();
            if (ec.getValue("magazin") == null) continue;
            
            TransactionBuilder bld = new TransactionBuilder();
            
            for (String tag : ec.getTags()) {
                String val = ec.getValue(tag);
                switch (tag) {
                    case "nr.crt": break;
                    case "magazin":
                        bld.Store(val);
                        if (val.equals("Plata datorie"))
                            bld.Payback(true);
                        break;
                    case "data": 
                        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        bld.Date(Date.valueOf(LocalDate.parse(val, f)));
                        break;
                    case "nr.bon": if (val != null) bld.Receipt(val); break;
                    case "suma": bld.Amount(Float.parseFloat(val.substring(1))); break;
                    case "aplatit": bld.Buyer(m.getBuyer(val)); break;
                    default:
                        if (val == null) break;
                        Float fl = Float.parseFloat(val.substring(1));
                        bld.addDebt(fl, m.getBuyer(tag));
                        break;
                }
            }
            
            m.transactions.add(bld.createTransaction());
        }
    }

    public void run(Manager manager) {
        try {
            authenticate();
            loadSheet();
//            printSheet();
            populateFromSheet(manager);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            Logger.getLogger(SpreadsheetImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
