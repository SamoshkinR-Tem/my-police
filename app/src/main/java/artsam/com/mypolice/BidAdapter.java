package artsam.com.mypolice;

import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import artsam.com.mypolice.models.BidFromServer;
import artsam.com.mypolice.models.LostChildBid;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Artem on 11.10.17.
 */

public class BidAdapter extends RecyclerView.Adapter<BidAdapter.ViewHolder> {
    private List<BidFromServer> bids;

    public BidAdapter(List<BidFromServer> bidsFromServer) {
        this.bids = bidsFromServer;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bid_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.personName.setText(bids.get(position).getName());
        holder.personAge.setText(bids.get(position).getDateOfBirth().toString());
        if (bids.get(position).getGender() == LostChildBid.Gender.MALE) {
            holder.genderImage.setImageResource(R.mipmap.ic_boy);
        } else {
            holder.genderImage.setImageResource(R.mipmap.ic_girl);
        }
        holder.region.setText(bids.get(position).getRegion());
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public int getItemCount() {
        return bids.size();
    }

    public void update(List<BidFromServer> bidsFromServer){
        for (BidFromServer bid: bidsFromServer) {
            bids.add(bid);
        }
        this.notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_view)
        CardView cv;
        @Bind(R.id.gender_image)
        ImageView genderImage;
        @Bind(R.id.person_name)
        TextView personName;
        @Bind(R.id.person_age)
        TextView personAge;
        @Bind(R.id.region)
        TextView region;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            genderImage = (ImageView)itemView.findViewById(R.id.gender_image);
            region =(TextView)itemView.findViewById(R.id.region);
        }
    }
}
