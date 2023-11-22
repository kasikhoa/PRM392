package com.example.petest.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petest.R;
import com.example.petest.adapter.SachListViewAdapter;
import com.example.petest.adapter.TacgiaListViewAdapter;
import com.example.petest.model.Sach;
import com.example.petest.model.SachRepository;
import com.example.petest.model.SachService;
import com.example.petest.model.Tacgia;
import com.example.petest.model.TacgiaRepository;
import com.example.petest.model.TacgiaService;
import com.example.petest.utils.ValidationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TacgiaActivity extends AppCompatActivity {

    ListView lvTacgia;
    ImageView imgAdd2;
    Button btnSach, positiveButton;
    TacgiaListViewAdapter tacgiaAdapter;
    SachListViewAdapter sachAdapter;
    TacgiaService tacgiaService;
    SachService sachService;
    List<Tacgia> tacgiaList = new ArrayList<>();
    List<Sach> sachList = new ArrayList<>();
    TextView txtHeading, nagativeButton;
    EditText etTenTacgia, etDienthoai, etDiachi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tacgia);

        btnSach = findViewById(R.id.btnSach);
        imgAdd2 = findViewById(R.id.imgAdd2);
        lvTacgia = findViewById(R.id.lvTacgia);
        tacgiaService = TacgiaRepository.getTacgiaService();
        tacgiaAdapter = new TacgiaListViewAdapter(TacgiaActivity.this, tacgiaList, R.layout.row_tacgia);
        lvTacgia.setAdapter(tacgiaAdapter);
        sachService = SachRepository.getSachService();
        loadTacgia();
        loadSach();

        btnSach.setOnClickListener(v -> {
            Intent intent = new Intent(TacgiaActivity.this, SachActivity.class);
            startActivity(intent);
        });

        imgAdd2.setOnClickListener(v -> showDiaLog(DialogType.CREATE, null));
    }

    public void loadTacgia() {
        tacgiaService.getAllTacgias().enqueue(new Callback<List<Tacgia>>() {
            @Override
            public void onResponse(Call<List<Tacgia>> call, Response<List<Tacgia>> response) {
                tacgiaList = response.body();
                if (tacgiaList != null && tacgiaList.size() > 0) {
                    tacgiaAdapter.setTacgiaList(tacgiaList);
                    tacgiaAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Tacgia>> call, Throwable t) {
                Toast.makeText(TacgiaActivity.this, "An error has occurred!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadSach() {
        sachService.getAllSachs().enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                if (response.body() != null && !response.body().isEmpty()) {
                    sachList = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {
                Toast.makeText(TacgiaActivity.this, "An error has occurred!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showDiaLog(DialogType dialogType, Tacgia tacgia) {
        Dialog dialog = new Dialog(TacgiaActivity.this);
        dialog.setContentView(R.layout.create_update_tacgia);

        txtHeading = dialog.findViewById(R.id.txtHeading);
        positiveButton = dialog.findViewById(R.id.positiveButton);
        nagativeButton = dialog.findViewById(R.id.negativeButton);

        etDiachi = dialog.findViewById(R.id.etDiachi);
        etDienthoai = dialog.findViewById(R.id.etDienthoai);
        etTenTacgia = dialog.findViewById(R.id.etTentacgia);

        if (dialogType == DialogType.CREATE) {
            txtHeading.setText("Create Tác giả");
            positiveButton.setText("Create");
        } else if (dialogType == DialogType.UPDATE) {
            txtHeading.setText("Update Tác giả");
            positiveButton.setText("Update");
            etTenTacgia.setText(tacgia.getTenTacgia());
            etDienthoai.setText(tacgia.getDienthoai());
            etDiachi.setText(tacgia.getDiachi());
        }

        nagativeButton.setOnClickListener(v -> dialog.dismiss());

        positiveButton.setOnClickListener(v -> {
            String name = etTenTacgia.getText().toString().trim();
            String diachi = etDiachi.getText().toString().trim();
            String phone = etDienthoai.getText().toString().trim();

            if (name.isEmpty()) {
                etTenTacgia.setError("Hãy điền tên tác giả");
                etTenTacgia.requestFocus();
                return;
            }
            if (diachi.isEmpty()) {
                etDiachi.setError("Hãy điền địa chỉ");
                etDiachi.requestFocus();
                return;
            }
            if (phone.isEmpty()) {
                etDienthoai.setError("Hãy điền SĐT");
                etDienthoai.requestFocus();
                return;
            }
            if (!ValidationUtils.isValidPhone(phone)) {
                etDienthoai.setError("SĐT không hợp lệ");
                etDienthoai.requestFocus();
                return;
            }
            Tacgia newTacgia = new Tacgia(name, phone, diachi);
            if (dialogType == DialogType.CREATE) {
                createTacgia(newTacgia);
            } else {
                updateTacgia(tacgia, newTacgia);
            }
            dialog.dismiss();
        });

        dialog.show();
    }

    public void deleteTacgia(Tacgia tacgiaToDelete) {
        List<Sach> saches = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(TacgiaActivity.this);
        builder.setTitle("Delete Tacgia");
        builder.setMessage("Are you sure you want to delete " + tacgiaToDelete.getTenTacgia() + "?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            // Kiểm tra xem tác giả có liên quan đến sách
            for (Sach sach : sachList) {
                if (sach.getIdTacgia() == tacgiaToDelete.getIdTacgia()) {
                    saches.add(sach);
                }
            }

            if (saches.size() != 0) {
                Toast.makeText(TacgiaActivity.this, "This author still has " + saches.size() + " books, you cannot delete now, try later!", Toast.LENGTH_SHORT).show();
            } else {
                deleteTacgiaNow(tacgiaToDelete);

            }
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }

    private void deleteTacgiaNow(Tacgia tacgiaToDelete) {
        // Thực hiện xóa tác giả
        tacgiaService.deleteTacgia(tacgiaToDelete.getIdTacgia()).enqueue(new Callback<Tacgia>() {
            @Override
            public void onResponse(Call<Tacgia> call, Response<Tacgia> response) {
                Toast.makeText(TacgiaActivity.this, "Tacgia " + tacgiaToDelete.getTenTacgia() + " has been deleted!", Toast.LENGTH_SHORT).show();
                tacgiaList.remove(tacgiaToDelete);
                tacgiaAdapter.setTacgiaList(tacgiaList);
                tacgiaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Tacgia> call, Throwable t) {
                Toast.makeText(TacgiaActivity.this, "An error has occurred!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createTacgia(Tacgia tacgia) {
        tacgiaService.createTacgia(tacgia).enqueue(new Callback<Tacgia>() {
            @Override
            public void onResponse(Call<Tacgia> call, Response<Tacgia> response) {
                Toast.makeText(TacgiaActivity.this, "Trainee " + tacgia.getTenTacgia() + " has been added!", Toast.LENGTH_SHORT).show();
                tacgiaList.add(response.body());
                tacgiaAdapter.setTacgiaList(tacgiaList);
                tacgiaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Tacgia> call, Throwable t) {
                Toast.makeText(TacgiaActivity.this, "An error has occurred!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateTacgia(Tacgia currentTacgia, Tacgia updateTacgia) {
        tacgiaService.updateTacgia(currentTacgia.getIdTacgia(), updateTacgia).enqueue(new Callback<Tacgia>() {
            @Override
            public void onResponse(Call<Tacgia> call, Response<Tacgia> response) {
                Toast.makeText(TacgiaActivity.this, "Tác giả has been updated!", Toast.LENGTH_SHORT).show();
                tacgiaList.remove(currentTacgia);
                tacgiaList.add(response.body());
                tacgiaAdapter.setTacgiaList(tacgiaList);
                tacgiaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Tacgia> call, Throwable t) {
                Toast.makeText(TacgiaActivity.this, "An error has occurred!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public enum DialogType {
        CREATE,
        UPDATE
    }
}