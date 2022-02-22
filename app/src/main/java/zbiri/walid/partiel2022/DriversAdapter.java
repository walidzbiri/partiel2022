package zbiri.walid.partiel2022;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import zbiri.walid.partiel2022.api.Driver;


public class DriversAdapter extends RecyclerView.Adapter<DriversAdapter.ViewHolder> implements Filterable {

    private List<Driver> mDriverList;
    private List<Driver> mDriverListFull;

    private OnDriverListener onDriverListener;
    private Context myActivity;


    public DriversAdapter(Context context, List<Driver> mDriverList, OnDriverListener onDriverListener) {
        this.mDriverList = mDriverList;
        this.mDriverListFull=new ArrayList<>(mDriverList);

        this.myActivity=context;
        this.onDriverListener= onDriverListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameView;
        public TextView teamView;
        public TextView pointsView;

        public OnDriverListener onDriverListener;

        public ViewHolder(@NonNull View itemView, Context context, OnDriverListener onDriverListener) {
            super(itemView);
            this.nameView = (TextView) itemView.findViewById(R.id.textView_driverName);
            this.teamView = (TextView) itemView.findViewById(R.id.textView_driverTeam);
            this.pointsView = (TextView) itemView.findViewById(R.id.textView_points);
            this.onDriverListener = onDriverListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onDriverListener.OnDriverClick(getAdapterPosition());
        }

    }

    @NonNull
    @Override
    public DriversAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View repoView=inflater.inflate(R.layout.activity_driver_item,parent,false);
        return new ViewHolder(repoView,context,onDriverListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Driver driver =mDriverList.get(position);
        holder.nameView.setText(driver.getDriver().getName());
        holder.teamView.setText(driver.getTeam().getName());
        holder.pointsView.setText(driver.getPoints());


    }

    @Override
    public int getItemCount() {
        return mDriverList.size();
    }

    public interface OnDriverListener {
        void OnDriverClick(int position);
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Driver> filteredList=new ArrayList<>();
            if(charSequence==null || charSequence.length()==0){
                filteredList.addAll(mDriverListFull);
            }else{
                String filterPattern=charSequence.toString().toLowerCase().trim();
                for(Driver item:mDriverListFull){
                    if(item.getDriver().getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mDriverList.clear();
            mDriverList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
