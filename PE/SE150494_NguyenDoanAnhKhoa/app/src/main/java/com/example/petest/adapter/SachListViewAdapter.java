package com.example.petest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petest.R;
import com.example.petest.activity.SachActivity;
import com.example.petest.model.Sach;
import com.example.petest.model.Tacgia;

import java.util.List;

public class SachListViewAdapter extends BaseAdapter {
    private final SachActivity context;
    private final int rowSachLayout;
    private List<Sach> sachList;
    private List<Tacgia> tacgiaList;

    public SachListViewAdapter(SachActivity context, List<Sach> sachList, List<Tacgia> tacgiaList, int rowSachLayout) {
        this.context = context;
        this.sachList = sachList;
        this.tacgiaList = tacgiaList;
        this.rowSachLayout = rowSachLayout;
    }

    @Override
    public int getCount() {
        return sachList.size();
    }

    @Override
    public Object getItem(int i) {
        return sachList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return sachList.get(i).getMasach();
    }

    public void setSachList(List<Sach> sachList) {
        this.sachList = sachList;
    }

    public void setTacgiaList(List<Tacgia> tacgiaList) {
        this.tacgiaList = tacgiaList;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SachListViewAdapter.ViewHolder holder;
        if (view == null) {
            holder = new SachListViewAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(rowSachLayout, null);
            holder.txtTacgia = view.findViewById(R.id.txtTacgia);
            holder.txtNgayxb = view.findViewById(R.id.txtNgayxb);
            holder.txtTensach = view.findViewById(R.id.txtTensach);
            holder.txtTheloai = view.findViewById(R.id.txtTheloai);
            holder.imgDelete = view.findViewById(R.id.imgDelete);
            holder.imgUpdate = view.findViewById(R.id.imgUpdate);
            view.setTag(holder);
        } else {
            holder = (SachListViewAdapter.ViewHolder) view.getTag();
        }

        Sach sach;
        if (getCount() > i) {
            sach = (Sach) getItem(i);
            if (sach != null) {
                for (Tacgia tg : tacgiaList) {
                    if (sach.getIdTacgia() == tg.getIdTacgia()) {
                        holder.txtTensach.setText(sach.getTensach());
                        holder.txtNgayxb.setText(sach.getNgayXB());
                        holder.txtTheloai.setText(sach.getTheloai());
                        holder.txtTacgia.setText(tg.getTenTacgia());
                    }
                }

                holder.imgDelete.setOnClickListener(v -> context.deleteSach(sach));
                holder.imgUpdate.setOnClickListener(v -> context.showDialog(SachActivity.DialogType.UPDATE, sach));
            }
        }
        return view;
    }

    private static class ViewHolder {
        TextView txtTensach, txtNgayxb, txtTheloai, txtTacgia;
        ImageView imgDelete, imgUpdate;
    }
}
