package com.example.petest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petest.R;
import com.example.petest.activity.TacgiaActivity;
import com.example.petest.model.Tacgia;

import java.util.List;

public class TacgiaListViewAdapter extends BaseAdapter {
    private final TacgiaActivity context;
    private List<Tacgia> tacgiaList;
    private final int rowTacgiaLayout;

    public TacgiaListViewAdapter(TacgiaActivity context, List<Tacgia> tacgiaList, int rowTacgiaLayout) {
        this.context = context;
        this.tacgiaList = tacgiaList;
        this.rowTacgiaLayout = rowTacgiaLayout;
    }

    @Override
    public int getCount() {
        return tacgiaList.size();
    }

    @Override
    public Object getItem(int i) {
        return tacgiaList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return tacgiaList.get(i).getIdTacgia();
    }

    public void setTacgiaList(List<Tacgia> tacgiaList) {
        this.tacgiaList = tacgiaList;
    }

    private static class ViewHolder {
        TextView txtTentacgia, txtDiachi, txtDienthoai;
        ImageView imgDelete2, imgUpdate2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(rowTacgiaLayout, null);
            holder.txtTentacgia = view.findViewById(R.id.txtTentacgia);
            holder.txtDienthoai = view.findViewById(R.id.txtDienthoai);
            holder.txtDiachi = view.findViewById(R.id.txtDiachi);
            holder.imgDelete2 = view.findViewById(R.id.imgDelete2);
            holder.imgUpdate2 = view.findViewById(R.id.imgUpdate2);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Tacgia tacgia = tacgiaList.get(i);
        holder.txtTentacgia.setText(tacgia.getTenTacgia());
        holder.txtDiachi.setText(tacgia.getDiachi());
        holder.txtDienthoai.setText(tacgia.getDienthoai());

        holder.imgDelete2.setOnClickListener(v -> context.deleteTacgia(tacgia));
        holder.imgUpdate2.setOnClickListener(v -> context.showDiaLog(TacgiaActivity.DialogType.UPDATE, tacgia));
        return view;
    }
}
