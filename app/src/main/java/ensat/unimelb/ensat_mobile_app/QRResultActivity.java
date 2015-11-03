package ensat.unimelb.ensat_mobile_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.MultiChoiceModeListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ensat.unimelb.ensat_mobile_app.data.AppConstants;
import ensat.unimelb.ensat_mobile_app.data.BioMaterialInfoTransfer;
import ensat.unimelb.ensat_mobile_app.data.BioMaterialTransferDetail;
import ensat.unimelb.ensat_mobile_app.data.WebserviceUtil;


public class QRResultActivity extends AppCompatActivity {
    TextView txtResult;
    // Declare Variables
    ListView list;
    ListViewAdapter listviewadapter;
    List<BioMaterialTransferDetail> bioInfoList = new ArrayList<BioMaterialTransferDetail>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);

        //   getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.app_theme)));
        // txtResult=(TextView)findViewById(R.id.txt_result);
        String group_id = getIntent().getStringExtra("SCAN_RESULT");
        //  String format = getIntent().getStringExtra("SCAN_RESULT_FORMAT");
        //  txtResult.setText(group_id);

        WebserviceUtil wUtil = new WebserviceUtil();
        String jsonStr = wUtil.requestContent(AppConstants.WEBSERVICE_URL + "/" + group_id);
        bioInfoList = parseJSONString(jsonStr);

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        // Pass results to ListViewAdapter Class
        listviewadapter = new ListViewAdapter(this, R.layout.listview_item, bioInfoList);

        // Binds the Adapter to the ListView
        list.setAdapter(listviewadapter);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);


        // Capture ListView item click
        list.setMultiChoiceModeListener(new MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                // Capture total checked items
                final int checkedCount = list.getCheckedItemCount();
                // Set the CAB title according to total checked items
                mode.setTitle(checkedCount + " Selected");
                // Calls toggleSelection method from ListViewAdapter Class
                listviewadapter.toggleSelection(position);
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.receive:
                        // Calls getSelectedIds method from ListViewAdapter Class
                        SparseBooleanArray selected = listviewadapter.getSelectedIds();
                        WebserviceUtil wUtil = new WebserviceUtil();

                        int receivedId = 0;
                        // Captures all selected ids with a loop
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                BioMaterialTransferDetail selecteditem = listviewadapter.getItem(selected.keyAt(i));
                                // Remove selected items following the ids
                                receivedId = selecteditem.getAcc_biomaterial_transfer_id();
                                String responseReceive = wUtil.requestContentPut("http://10.9.138.169:8080/ENSAT_WS/api/accbiomaterialaliquotstransfer" + "/" + receivedId);
                                listviewadapter.remove(selecteditem);
                            }
                        }
                        // Close CAB
                        showDialog("Material Received", "Bio Materials Successfully Received", QRResultActivity.this);
                        mode.finish();
                        return true;

                    case R.id.selectAll:

                        onClickSelectAll();
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_qrresult, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // TODO Auto-generated method stub
                listviewadapter.removeSelection();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }

    public void onClickSelectAll()
    {
        for ( int i=0; i < listviewadapter.getCount(); i++) {
            list.setItemChecked(i, true);
        }
    }

    public Dialog showDialog(String title, String msg, final Activity activity) {

        final AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
        return alertDialog;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_qrresult, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.selectAll) {
            onClickSelectAll();
            return true;
        }
        else if (id == R.id.goHome){

            Intent mainActivityIntent = new Intent(QRResultActivity.this, NavigationMainActivity.class);
            QRResultActivity.this.startActivity(mainActivityIntent);
            QRResultActivity.this.finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public ArrayList<BioMaterialInfoTransfer> parseJSON(String result) {
            ArrayList<BioMaterialInfoTransfer> bioMaterialInfoList = new ArrayList<BioMaterialInfoTransfer>();
            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    BioMaterialInfoTransfer bioMaterialInfo = new BioMaterialInfoTransfer();
                    bioMaterialInfo.setAccBiomaterialId(json_data.getInt("accBiomaterialId"));
                    bioMaterialInfo.setAccBiomaterialLocationId(json_data.getInt("accBiomaterialLocationId"));
                    bioMaterialInfo.setAccBiomaterialTransferGroupId(json_data.getInt("accBiomaterialTransferGroupId"));
                    bioMaterialInfo.setAccBiomaterialTransferId(json_data.getInt("accBiomaterialTransferId"));
                    bioMaterialInfo.setEensatId(json_data.getInt("ensatId"));
                    bioMaterialInfo.setDestinationCenterId(json_data.getString("destinationCenterId"));
                    bioMaterialInfo.setCenterId(json_data.getString("centerId"));
                    bioMaterialInfo.setStatus(json_data.getString("status"));
                    bioMaterialInfo.setTransferedDate(json_data.getString("transferedDate"));
                    bioMaterialInfoList.add(bioMaterialInfo);
                }

            } catch (JSONException e) {
                Log.e("log_tag", "Error parsing data " + e.toString());
            }
            return bioMaterialInfoList;
        }

    public ArrayList<BioMaterialTransferDetail> parseJSONString(String result) {
        ArrayList<BioMaterialTransferDetail> bioMaterialDetail = new ArrayList<BioMaterialTransferDetail>();
        try {
            JSONArray jArray = new JSONArray(result);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                BioMaterialTransferDetail bioMaterialInfo = new BioMaterialTransferDetail();
                bioMaterialInfo.setEnsat_id(json_data.getInt("ensat_id"));
                bioMaterialInfo.setBio_id(json_data.getString("bio_id"));
                if(!json_data.isNull("biomaterial_date"))
                {
                bioMaterialInfo.setBiomaterial_date((json_data.getString("biomaterial_date")));
                }
                bioMaterialInfo.setMaterial(json_data.getString("material"));
                bioMaterialInfo.setAliquot_sequence_id(json_data.getInt("aliquot_sequence_id"));
                bioMaterialInfo.setFreezer_number(json_data.getString("freezer_number"));
                bioMaterialInfo.setFreezershelf_number(json_data.getString("freezershelf_number"));
                bioMaterialInfo.setRack_number(json_data.getString("rack_number"));
                bioMaterialInfo.setShelf_number(json_data.getString("shelf_number"));
                bioMaterialInfo.setBox_number(json_data.getString("box_number"));
                bioMaterialInfo.setPosition_number(json_data.getString("position_number"));
                bioMaterialInfo.setCenter_id(json_data.getString("center_id"));
                bioMaterialInfo.setDestination_center_id(json_data.getString("destination_center_id"));
                bioMaterialInfo.setTransfered_date(json_data.getString("transfered_date"));
                bioMaterialInfo.setStatus(json_data.getString("status"));
                bioMaterialInfo.setTransfer_group_id(json_data.getInt("transfer_group_id"));
                bioMaterialInfo.setAcc_biomaterial_transfer_id(json_data.getInt("acc_biomaterial_transfer_id"));

                bioMaterialDetail.add(bioMaterialInfo);
            }

        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
        return bioMaterialDetail;
    }

    }
