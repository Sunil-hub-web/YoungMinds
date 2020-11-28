package com.egk.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.egk.egk.R;
import com.egk.gettersetter.GkGetSet;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.MyViewHolder>
        implements Filterable  {


    private Context context;
    private List<GkGetSet> contactList;
    private List<GkGetSet> contactListFiltered;
    private ContactsAdapterListener listener;
//    ImageLoader imageLoader = AppSingleton.getInstance(context).getImageLoader();

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView name, phone;
//        public ImageView thumbnail;

        public NetworkImageView curryone;
        public TextView name, catname, price, date, to;

        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.title);
            catname = (TextView) view.findViewById(R.id.catname);
            date = (TextView) view.findViewById(R.id.date);



            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public PopularAdapter(Context context, List<GkGetSet> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.total_gk_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final GkGetSet contact = contactListFiltered.get(position);

//        holder.curryone.setImageUrl(contact.getMenu_img(),imageLoader);
//        holder.lemon_chicken.setText(contact.getMenu_name());
//        holder.dish_rate.setText("Rs. "+contact.getPrice());
//        holder.menutype.setText(contact.getMenu_type());

        holder.name.setText(contact.getGk_title());
        holder.catname.setText(contact.getCategory_name());

        String strCurrentDate = contact.getGk_date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = format.parse(strCurrentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("dd-MM-yyyy");
        String dt = format.format(newDate);

        holder.date.setText("  -  "+dt);
    }

    @Override
    public int getItemCount() {
//        try {
            return contactListFiltered.size();
//        }catch (Exception e){
//
//        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contactList;
                } else {
                    List<GkGetSet> filteredList = new ArrayList<>();
                    for (GkGetSet row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match



                        String string = row.getGk_date();
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        Date d = null;
                        try {
                            d = format.parse(string);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        String filtrval = charString;
                        Date start = null;
                        try {
                            start = format.parse(filtrval);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        String filtrval2 = "";
                        Date end = null;
                        try {
                            end = format.parse(filtrval2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        Log.d("dfgthggA", String.valueOf(d));
                        Log.d("dfgthggB", String.valueOf(start));
                        Log.d("dfgthggC", String.valueOf(end));
//                        if (row.get.toLowerCase().contains(charString.toLowerCase()) || row.getMenu_type().contains(charSequence)) {
                        if (d.after(start) && d.before(end)) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<GkGetSet>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(GkGetSet contact);
    }









//
//
//    private List<PopularList> accepetedJobLists;
//    private RequestQueue requestQueue;
//    private Context context;
//
//    private List<PopularList> contactListFiltered;
//    ImageLoader imageLoader = AppSingleton.getInstance(context).getImageLoader();
//
//    public PopularAdapter(List<PopularList> accepetedJobLists) {
//        this.accepetedJobLists = accepetedJobLists;
//
//        this.contactListFiltered = accepetedJobLists;
//
//    }
//
//    @NonNull
//    @Override
//    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
//        View listItem= layoutInflater.inflate(R.viewquiz.popular_page, viewGroup, false);
//        PopularAdapter.ViewHolder viewHolder = new PopularAdapter.ViewHolder(listItem);
//        return viewHolder;
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder viewHolder, int i) {
//        PopularList movie = accepetedJobLists.get(i);
//
//        viewHolder.curryone.setImageUrl(movie.getMenu_img(),imageLoader);
////        viewHolder.curryone.setImageResource(movie.getMenu_img());
//        viewHolder.lemon_chicken.setText(movie.getMenu_name());
////        viewHolder.text_tasty.setText(movie.get);
//        viewHolder.dish_rate.setText("Rs. "+movie.getPrice());
//
//        viewHolder.menutype.setText(movie.getMenu_type());
////        viewHolder.add_item.setText(tittle.Add_Item);
//    }
//
//    @Override
//    public int getItemCount() {
//        return contactListFiltered.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        NetworkImageView curryone;
//        TextView lemon_chicken,text_tasty,dish_rate,add_item, menutype;
//                //kadhai_panner,text_dish,dish,add_itemtwo;
//
//
//        @SuppressLint("WrongViewCast")
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            this.curryone = (NetworkImageView) itemView.findViewById(R.id.curryone);
//            this.lemon_chicken = (TextView) itemView.findViewById(R.id.item_name);
//            this.text_tasty = (TextView) itemView.findViewById(R.id.text_tasty);
//            this.dish_rate = (TextView) itemView.findViewById(R.id.dish_rate);
//            this.add_item = (TextView) itemView.findViewById(R.id.add_item);
//            this.menutype = (TextView) itemView.findViewById(R.id.menutype);
//        }
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public int getItemViewType(int position)
//    {
//        return position;
//    }
//
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String charString = charSequence.toString();
//                if (charString.isEmpty()) {
//                    contactListFiltered = accepetedJobLists;
//                } else {
//                    List<PopularList> filteredList = new ArrayList<>();
//                    for (PopularList row : accepetedJobLists) {
//
//                        // name match condition. this might differ depending on your requirement
//                        // here we are looking for name or phone number match
//                        if (row.getMenu_name().toLowerCase().contains(charString.toLowerCase())) {
//                            filteredList.add(row);
//                        }
//                    }
//
//                    contactListFiltered = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = contactListFiltered;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                contactListFiltered = (ArrayList<PopularList>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }
}