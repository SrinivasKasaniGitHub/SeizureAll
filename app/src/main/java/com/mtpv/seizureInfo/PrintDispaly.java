package com.mtpv.seizureInfo;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.analogics.thermalAPI.Bluetooth_Printer_3inch_ThermalAPI;
import com.analogics.thermalprinter.AnalogicsThermalPrinter;
import com.mtpv.seizure.R;
import com.mtpv.seizureHelpers.DataBase;

public class PrintDispaly extends Activity {
    final int PROGRESS_DIALOG = 1;
    TextView Tv;
    Button back_Btn, print_Btn;
    String Pidcode, PidName, PsCode, PsName, cadreCD, cadre, password;
    String generateChallan;
    TextView officer_Name, officer_Cadre, officer_PS;
    ImageView img_logo;
    AnalogicsThermalPrinter printer = new AnalogicsThermalPrinter();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.printdisplay);

        back_Btn = (Button) findViewById(R.id.back_Btn);
        print_Btn = (Button) findViewById(R.id.print_Btn);

        Tv = (TextView) findViewById(R.id.Tv);
        img_logo = (ImageView) findViewById(R.id.img_logo);

        if (MainActivity.uintCode.equals("22")) {
            img_logo.setImageDrawable(getResources().getDrawable(R.drawable.cyb_logo));
        } else if (MainActivity.uintCode.equals("23")) {
            img_logo.setImageDrawable(getResources().getDrawable(R.drawable.htp_left));
        } else if (MainActivity.uintCode.equals("24")) {
            img_logo.setImageDrawable(getResources().getDrawable(R.drawable.rac_logo));
        } else {
            img_logo.setImageDrawable(getResources().getDrawable(R.drawable.htp_left));
        }

        officer_Name = (TextView) findViewById(R.id.officer_Name);
        officer_Cadre = (TextView) findViewById(R.id.officer_cadre);
        officer_PS = (TextView) findViewById(R.id.officer_PS);

        officer_Name.setText(MainActivity.PID_NAME1 + "(" + MainActivity.CADRE_NAME1 + ")");
        officer_Cadre.setText(MainActivity.CADRE_NAME1);
        officer_PS.setText(MainActivity.PS_NAME1);

        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String requestfrom = sharedPreference.getString("printFrom", "");


        back_Btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (requestfrom.equals("shop")) {
                    DisplayP.detItems = null;
                    DisplayP.ditendItem_TV.setText("");
                    DisplayP.detendItemsSelected = "";
                    DisplayP.detainedItems = "";

                    DisplayP.detendItems_Selected_toDisplay.setLength(0);
                    DisplayP.detendItems_Selected_toDisplay.delete(0, Shop_vendor.detendItemsA.length());

                    DisplayP.detendItems_Selected_tosend.setLength(0);
                    DisplayP.detendItems_Selected_tosend.delete(0, Shop_vendor.detendItemsA.length());

                    Shop_vendor.imgString = null;
                    Shop_vendor.imgString2 = null;

                    Shop_vendor.detendLinearLayout.removeAllViews();
                    Shop_vendor.imgv.setImageDrawable(getResources().getDrawable(R.drawable.photo));
                    Shop_vendor.imgv2.setImageDrawable(getResources().getDrawable(R.drawable.tin));

                    DisplayP.detItems = null;
                    DisplayP.ditendItem_TV.setText("");
                    DisplayP.detendItemsSelected = "";
                    DisplayP.detainedItems = "";

                    DisplayP.detendItems_Selected_toDisplay.setLength(0);
                    DisplayP.detendItems_Selected_toDisplay.delete(0, Shop_vendor.detendItemsA.length());

                    DisplayP.detendItems_Selected_tosend.setLength(0);
                    DisplayP.detendItems_Selected_tosend.delete(0, Shop_vendor.detendItemsA.length());

                    Shop_vendor.detItems.setLength(0);
                    Shop_vendor.Ditenditems.clear();
                    Shop_vendor.detendItemsA.setLength(0);
                    Shop_vendor.detendItemsA.delete(0, Shop_vendor.detendItemsA.length());

                    Shop_vendor.Itemname_ET.setText("");
                    Shop_vendor.qty_ET.setText("");
                    Shop_vendor.amount_ET.setText("");

                    Shop_vendor.items = "";

                    Shop_vendor.detendLinearLayout.removeAllViews();
                    Intent i = new Intent(PrintDispaly.this, Shop_vendor.class);
                    startActivity(i);

                } else if (requestfrom.equals("footpath")) {
                    FootPath_Vendor.detItems.setLength(0);

                    FootPath_Vendor.detItems.setLength(0);
                    FootPath_Vendor.Ditenditems.clear();
                    FootPath_Vendor.detendItemsA.setLength(0);
                    FootPath_Vendor.detendItemsA.delete(0, FootPath_Vendor.detendItemsA.length());

                    FootPath_Vendor.items = "";
                    FootPath_Vendor.imgv.setImageDrawable(getResources().getDrawable(R.drawable.photo));
                    FootPath_Vendor.imgv2.setImageDrawable(getResources().getDrawable(R.drawable.tin));

                    FootPath_Vendor.imgString = null;
                    FootPath_Vendor.imgString2 = null;

                    FootPath_Vendor.detendLinearLayout.removeAllViews();

                    FootPath_Preview.detendItemsA = "";

                    FootPath_Vendor.detItems.setLength(0);

                    FootPath_Preview.detendItems_Selected_toDisplay.setLength(0);
                    FootPath_Preview.detendItems_Selected_toDisplay.delete(0, Shop_vendor.detendItemsA.length());

                    FootPath_Preview.detendItems_Selected_tosend.setLength(0);
                    FootPath_Preview.detendItems_Selected_tosend.delete(0, Shop_vendor.detendItemsA.length());

                    FootPath_Vendor.detItems.setLength(0);
                    FootPath_Vendor.Ditenditems.clear();
                    FootPath_Vendor.detendItemsA.setLength(0);
                    FootPath_Vendor.detendItemsA.delete(0, FootPath_Vendor.detendItemsA.length());
                    //Log.i("FootPath_Vendor.detendItemsA   :::", ""+FootPath_Vendor.detendItemsA);

                    FootPath_Vendor.Itemname_ET.setText("");
                    FootPath_Vendor.qty_ET.setText("");
                    FootPath_Vendor.amount_ET.setText("");

                    FootPath_Vendor.items = "";

                    FootPath_Vendor.detendLinearLayout.removeAllViews();

                    Intent i2 = new Intent(PrintDispaly.this, Dashboard.class);
                    startActivity(i2);

                } else if (requestfrom.equals("duplicatprint")) {
                    Intent i3 = new Intent(PrintDispaly.this, DuplicatePrint.class);
                    startActivity(i3);

                } else {
                    Intent i4 = new Intent(PrintDispaly.this, Dashboard.class);
                    finish();

                }
            }
        });


        print_Btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Async_Task_PrintData().execute();

            }
        });

        Intent intent = getIntent();

        generateChallan = intent.getStringExtra("generateChallan");


        Tv.setText(generateChallan);

    }

    public class Async_Task_PrintData extends AsyncTask<Void, Void, String> {
        //ProgressDialog progress = ProgressDialog.show(Reports.this, "Loading...!", "Please wait......Processing!!!");

        @SuppressWarnings("deprecation")
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            showDialog(PROGRESS_DIALOG);
        }

        @SuppressWarnings("unused")
        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            String bt = "";
            DataBase helper = new DataBase(getApplicationContext());
            try {
                android.database.sqlite.SQLiteDatabase db = openOrCreateDatabase(DataBase.DATABASE_NAME, MODE_PRIVATE, null);
                String selectQuery = "SELECT  * FROM " + DataBase.Bluetooth;
                //SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);
                // looping through all rows and adding to list

                if (cursor.moveToFirst()) {
                    do {

                        Log.i("1 :", "" + cursor.getString(0));
                        bt = cursor.getString(0);

                        //et_bt_address.setText(BLT_Name);

                    } while (cursor.moveToNext());
                }
                db.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
            try {

                Bluetooth_Printer_3inch_ThermalAPI preparePrintData = new Bluetooth_Printer_3inch_ThermalAPI();
               /* generateChallan="";
                generateChallan="\t\t TELANGANA STATE POLICE  \n" +
						"\t\t HYDERABAD TRAFFIC POLICE \n" +
						"\t\t\t E-CHALLAN SYSTEM \n" +
						"CHALLAN & RECEIPT FOR COMPOUNDING\n" +
						"OFFENCE UNDER C.P ACT/RULES(TRAFFIC VIOLATIONS)\n" +
						"-----------------------------------\n" +
						"CHALLAN NO     :HYD003B200081207\n" +
						"BOOKED DATE    :2020-12-22\n" +
						"CHALLAN TYPE   : 39(B) C.P ACT\n" +
						"PS NAME        :Hyd Traffic Cell \n" +
						"POINT NAME     :COMPOUNDING BOOTH\n" +
						"-----------------------------------\n" +
						"-----------------------------------\n" +
						"       RESPONDANT  DETAILS\n" +
						"NAME       :CUGC\n" +
						"FNAME      :HHCGU\n" +
						"CONTACT NO :8074373917\n" +
						"-----------------------------------\n" +
						"      DETAINED ITEMS DETAILS \n" +
						" SNO     ITEM     QUANTITY \n" +
						" 1\tBGCGH\t3\n" +
						"-----------------------------------\n" +
						"TOTAL FINE  Rs : 200.00\n" +
						"-----------------------------------\n" +
						" \n" +
						"  \n" +
						"  \n" +
						" RESPONDENT SIGN \t OFFICER SIGN\n" +
						"SRINIVAS KASANI-(DEVP)\n" +
						"HYDERABAD-2300001044\n" +
						" -----------------------------------\n" +
						" PAYMENT CAN BE MADE AT COMPOUNDING BOOTH (Traffic Control Room, OPP. Public Garden, Nampally)  \n" +
						"PHONE NO: 4027852001\n" +
						"HELP LINE NO: 04027852721\n" +
						" PAYMENT SHOULD BE MADE WITHIN SEVEN\n" +
						" DAYS. IN CASE YOU FAILED TO MAKE\n" +
						" PAYMENT. LEGAL ACTION WILL BE TAKEN\n" +
						" ON YOUR DETAINED ITEMS. \n" +
						"\n" +
						" \n" +
						" \n" +
						" \n" +
						" \n" +
						" ";*/
                String print_data = preparePrintData.font_Courier_41(generateChallan);
                printer.openBT(bt);
                printer.printData(print_data);
                Thread.sleep(5000);
                printer.closeBT();


                //Toast.makeText(getApplicationContext(), "print ", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        showToast("Turn on Bluetooth and Configure Bluetooth Settings ");
                    }
                });
            }

            return null;
        }

        @SuppressWarnings("deprecation")
        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            removeDialog(PROGRESS_DIALOG);
        }
    }

    private void showToast(String msg) {
        // TODO Auto-generated method stub
        Toast toast = Toast.makeText(getApplicationContext(), "" + msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        View toastView = toast.getView();

        ViewGroup group = (ViewGroup) toast.getView();
        TextView messageTextView = (TextView) group.getChildAt(0);
        messageTextView.setTextSize(24);

        toastView.setBackgroundResource(R.drawable.toast_background);
        toast.show();
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case PROGRESS_DIALOG:
                ProgressDialog pd = ProgressDialog.show(this, "", "", true);
                pd.setContentView(R.layout.custom_progress_dialog);
                pd.setCancelable(false);

                return pd;

            default:
                break;
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        //super.onBackPressed();
        showToast("Please Click  on Back to go Back");
    }
}
