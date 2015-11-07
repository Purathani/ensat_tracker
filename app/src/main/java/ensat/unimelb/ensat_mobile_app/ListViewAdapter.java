package ensat.unimelb.ensat_mobile_app;

/**
 * Created by purathani on 4/10/2015.
 */
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import ensat.unimelb.ensat_mobile_app.data.BioMaterialInfoTransfer;
import ensat.unimelb.ensat_mobile_app.data.BioMaterialTransferDetail;

public class ListViewAdapter extends ArrayAdapter<BioMaterialTransferDetail> {

    Context context;
    LayoutInflater inflater;
    List<BioMaterialTransferDetail> bioInfoList;
    private SparseBooleanArray mSelectedItemsIds;

    public ListViewAdapter(Context context, int resourceId,
                           List<BioMaterialTransferDetail> bioInfoList) {
        super(context, resourceId, bioInfoList);
        mSelectedItemsIds = new SparseBooleanArray();
        this.context = context;
        this.bioInfoList = bioInfoList;
        inflater = LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView textViewEnsatId, textViewBioId, textViewTransferGroupId, textViewMaterial, textViewMaterialDate, textViewCenterId,textViewDestinationCenterId, textViewTransferDate, textViewStatus;
    }

    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.textViewEnsatId = (TextView) view.findViewById(R.id.textViewEnsatId);
            holder.textViewBioId = (TextView) view.findViewById(R.id.textViewBioId);
            holder.textViewTransferGroupId = (TextView) view.findViewById(R.id.textViewTransferGroupId);
            holder.textViewMaterial = (TextView) view.findViewById(R.id.textViewMaterial);
            holder.textViewMaterialDate = (TextView) view.findViewById(R.id.textViewMaterialDate);
            holder.textViewCenterId = (TextView) view.findViewById(R.id.textViewCenterId);
            holder.textViewDestinationCenterId = (TextView) view.findViewById(R.id.textViewDestinationCenterId);
            holder.textViewTransferDate = (TextView) view.findViewById(R.id.textViewTransferDate);

            holder.textViewStatus = (TextView) view.findViewById(R.id.textViewStatus);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Capture position and set to the TextViews
      /*  holder.textViewEnsatId.setText(bioInfoList.get(position).getEensatId());
        holder.textViewTransferGroupId.setText(bioInfoList.get(position).getAccBiomaterialTransferGroupId());
        holder.textViewCenterId.setText(bioInfoList.get(position).getCenterId());
        holder.textViewDestinationCenterId.setText(bioInfoList.get(position).getDestinationCenterId());
        holder.textViewStatus.setText(bioInfoList.get(position).getStatus());

*/
        final BioMaterialTransferDetail bio = bioInfoList.get(position); // you can remove the final modifieer.

        holder.textViewEnsatId.setText(Integer.toString(bio.getEnsat_id()));
        holder.textViewBioId.setText(bio.getBio_id());
        holder.textViewTransferGroupId.setText(Integer.toString(bio.getTransfer_group_id()));
        holder.textViewMaterial.setText(bio.getMaterial());
       if (bio.getBiomaterial_date() != null) {
           holder.textViewMaterialDate.setText(bio.getBiomaterial_date().substring(0, 10));
       }
        holder.textViewCenterId.setText(bio.getCenter_id());
        holder.textViewDestinationCenterId.setText(bio.getDestination_center_id());
        holder.textViewStatus.setText(bio.getStatus());
        holder.textViewTransferDate.setText(bio.getTransfered_date().substring(0, 10));



        return view;
    }

    @Override
    public void remove(BioMaterialTransferDetail object) {
        bioInfoList.remove(object);
        notifyDataSetChanged();
    }

    public List<BioMaterialTransferDetail> getBioInfoList() {
        return bioInfoList;
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
}
