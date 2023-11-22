package com.example.petest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petest.R;
import com.example.petest.adapter.SachListViewAdapter;
import com.example.petest.model.Sach;
import com.example.petest.model.SachRepository;
import com.example.petest.model.SachService;
import com.example.petest.model.Tacgia;
import com.example.petest.model.TacgiaPOJO;
import com.example.petest.model.TacgiaRepository;
import com.example.petest.model.TacgiaService;
import com.example.petest.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SachActivity extends AppCompatActivity {

    public enum DialogType {
        CREATE,
        UPDATE
    }

    ListView lvSach;
    ImageView imgAdd;
    Button btnNext, positiveButton1;

    SachListViewAdapter sachAdapter;
    SachService sachService;
    TacgiaService tacgiaService;
    List<Sach> sachList = new ArrayList<>();
    List<Tacgia> tacgiaList = new ArrayList<>();

    TextView txtHeading1, nagativeButton1;
    EditText etTensach, etTheloai, etNgayxb;
    Spinner spTacgia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SachActivity.this, TacgiaActivity.class);
                startActivity(intent);
            }
        });

        imgAdd = (ImageView) findViewById(R.id.imgAdd);
        lvSach = (ListView) findViewById(R.id.lvSach);
        sachService = SachRepository.getSachService();
        tacgiaService = TacgiaRepository.getTacgiaService();
        loadTacgia();
        sachAdapter =new SachListViewAdapter(SachActivity.this, sachList, tacgiaList, R.layout.row_sach);
        lvSach.setAdapter(sachAdapter);
        loadSach();

        imgAdd.setOnClickListener(view -> {
            showDialog(DialogType.CREATE, null);
        });
    }

    public void loadTacgia() {
        tacgiaService.getAllTacgias().enqueue(new Callback<List<Tacgia>>() {
            @Override
            public void onResponse(Call<List<Tacgia>> call, Response<List<Tacgia>> response) {
                if (response.body() != null && !response.body().isEmpty()) {
                    tacgiaList = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Tacgia>> call, Throwable t) {

            }
        });
    }

    public void loadSach(){
        sachService.getAllSachs().enqueue(new Callback<List<Sach>>() {
                    @Override
                    public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                        sachList = response.body();
                        if (sachList != null && sachList.size() > 0) {
                            sachAdapter.setSachList(sachList);
                            sachAdapter.setTacgiaList(tacgiaList);
                            sachAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Sach>> call, Throwable t) {
                        Log.e("error", t.toString());
                        Toast.makeText(SachActivity.this, "An error has occurred!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void deleteSach (Sach sachToDelete){
        AlertDialog.Builder builder = new AlertDialog.Builder(SachActivity.this);
        builder.setTitle("Delete Sach");
        builder.setMessage("Are you sure you want to delete " + sachToDelete.getTensach() + "?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            sachService.deleteSach(sachToDelete.getMasach()).enqueue(new Callback<Sach>() {
                @Override
                public void onResponse(Call<Sach> call, Response<Sach> response) {
                    Toast.makeText(SachActivity.this, "Sach " + sachToDelete.getTensach() + " has been deleted!", Toast.LENGTH_SHORT).show();
                    sachList.remove(sachToDelete);
                    sachAdapter.setSachList(sachList);
                    sachAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<Sach> call, Throwable t) {
                    Toast.makeText(SachActivity.this, "An error has occurred!", Toast.LENGTH_SHORT).show();
                }
            });
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }

    public void showDialog(SachActivity.DialogType dialogType, Sach sach){
        Dialog dialog = new Dialog(SachActivity.this);
        dialog.setContentView(R.layout.create_update_sach);

        txtHeading1 = dialog.findViewById(R.id.txtHeading1);
        positiveButton1 = dialog.findViewById(R.id.positiveButton1);
        nagativeButton1 = dialog.findViewById(R.id.negativeButton1);

        etNgayxb = dialog.findViewById(R.id.etNgayxb);
        etTensach = dialog.findViewById(R.id.etTensach);
        etTheloai = dialog.findViewById(R.id.etTheloai);
        spTacgia = dialog.findViewById(R.id.spTacgia);

        //set data for spinner
        ArrayList<TacgiaPOJO> tacgiaPOJOList = new ArrayList<>(tacgiaList.size());
        for (Tacgia tg: tacgiaList) {
            tacgiaPOJOList.add(new TacgiaPOJO(tg.getIdTacgia(), tg.getTenTacgia()));
        }
        ArrayAdapter<TacgiaPOJO> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, tacgiaPOJOList);
        spTacgia.setAdapter(adapter);
//        spTacgia.setSelection(adapter.getPosition());
//        spTacgia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                TacgiaPOJO tacgiaPOJO = (TacgiaPOJO) parent.getSelectedItem();
//                idTacgia = tacgiaPOJO.getId();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        dialog.show();

        //setup template
        if(dialogType == SachActivity.DialogType.CREATE){
            txtHeading1.setText("Create Sách");
            positiveButton1.setText("Create");
        } else if (dialogType == SachActivity.DialogType.UPDATE){
            txtHeading1.setText("Update Sách");
            positiveButton1.setText("Update");
            etNgayxb.setText(sach.getNgayXB());
            etTensach.setText(sach.getTensach());
            etTheloai.setText(sach.getTheloai());
            spTacgia.setSelection(sach.getIdTacgia());
        }

        //setup on click
        nagativeButton1.setOnClickListener(v -> dialog.dismiss());

        positiveButton1.setOnClickListener(v -> {
            String ngayXB = etNgayxb.getText().toString();
            String tenSach = etTensach.getText().toString();
            String theLoai = etTheloai.getText().toString();
            TacgiaPOJO tacgia = (TacgiaPOJO) spTacgia.getSelectedItem();

            if(tenSach.isEmpty()){
                etTensach.setError("Hãy điền tên sách");
                etTensach.requestFocus();
                return;
            }
            if(theLoai.isEmpty()){
                etTheloai.setError("Hãy điền thể loại");
                etTheloai.requestFocus();
                return;
            }
            if(ngayXB.isEmpty()){
                etNgayxb.setError("Hãy điền ngày xuất bản");
                etNgayxb.requestFocus();
                return;
            }
            if(!ValidationUtils.isValidDate(ngayXB)){
                etNgayxb.setError("Hãy điền ngày đúng format");
                etNgayxb.requestFocus();
                return;
            }

            int id = tacgia.getId();

            Sach newSach = new Sach(tenSach, theLoai, id, ngayXB);
            if (dialogType == SachActivity.DialogType.CREATE) {
                createSach(newSach);
            } else {
                updateSach(sach, newSach);
            }
            dialog.dismiss();
        });
        dialog.show();
    }

    public void createSach(Sach sach) {
        sachService.createSach(sach).enqueue(new Callback<Sach>() {
            @Override
            public void onResponse(Call<Sach> call, Response<Sach> response) {
                Toast.makeText(SachActivity.this, "Book has been updated!", Toast.LENGTH_SHORT).show();
                sachList.add(response.body());
                sachAdapter.setSachList(sachList);
                sachAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Sach> call, Throwable t) {

            }
        });
    }

    public void updateSach(Sach currentSach, Sach newSach) {
        sachService.updateSach(newSach.getMasach(), newSach).enqueue(new Callback<Sach>() {
            @Override
            public void onResponse(Call<Sach> call, Response<Sach> response) {
                Toast.makeText(SachActivity.this, "Book has been updated!", Toast.LENGTH_SHORT).show();
                sachList.remove(currentSach);
                sachList.add(response.body());
                sachAdapter.setSachList(sachList);
                sachAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Sach> call, Throwable t) {
                Toast.makeText(SachActivity.this, "An error has occurred!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}