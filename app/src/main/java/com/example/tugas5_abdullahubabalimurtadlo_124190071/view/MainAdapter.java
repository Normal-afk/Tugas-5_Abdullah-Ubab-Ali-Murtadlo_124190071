package com.example.tugas5_abdullahubabalimurtadlo_124190071.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas5_abdullahubabalimurtadlo_124190071.R;
import com.example.tugas5_abdullahubabalimurtadlo_124190071.entity.DataMahasiswa;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<DataMahasiswa> list;
    MainContact.mainView mainView;

    public MainAdapter(Context context, List<DataMahasiswa> list, MainContact.mainView mainView) {
        this.context = context;
        this.list = list;
        this.mainView = mainView;
    }

    @NonNull
    @Override
    public MainAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mahasiswa,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final DataMahasiswa item = list.get(position);
        holder.tvNIM.setText(item.getNim());
        holder.tvNama.setText(item.getNama());
        holder.tvProdi.setText(item.getProdi());
        holder.tvJurusan.setText(item.getJurusan());
        holder.tvIPK.setText(Float.toString(item.getIpk()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // membuat pop up menu
                PopupMenu popupMenu = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                    popupMenu = new PopupMenu(context, v, Gravity.RIGHT, R.attr.actionOverflowMenuStyle, 0);
                }
                else {
                    popupMenu = new PopupMenu(context,v);
                }
                popupMenu.getMenuInflater().inflate(R.menu.option_menu, popupMenu.getMenu());
                // mengatur on item click pada pop up menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu_edit :   // menu edit dipilih
                                mainView.editData(item);
                                break;
                            case R.id.menu_delete : // menu delete dipilih
                                mainView.deleteData(item);
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvNIM, tvNama, tvProdi, tvJurusan, tvIPK;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvNIM = itemView.findViewById(R.id.tv_item_nim);
            tvNama = itemView.findViewById(R.id.tv_item_nama);
            tvProdi = itemView.findViewById(R.id.tv_item_prodi);
            tvJurusan = itemView.findViewById(R.id.tv_item_jurusan);
            tvIPK = itemView.findViewById(R.id.tv_item_ipk);
            cardView = itemView.findViewById(R.id.cv_item);
        }
    }
}
