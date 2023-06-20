package com.dwiki.rsplg.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dwiki.rsplg.API.APIRequestData;
import com.dwiki.rsplg.API.RetroServer;
import com.dwiki.rsplg.Adapter.AdapterRumahSakit;
import com.dwiki.rsplg.Model.ModelResponse;
import com.dwiki.rsplg.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private Button btnSimpan;
    private EditText etNama, etAlamat, etTelepon, etFoto, etKoordinat;
    private String nama, alamat, telepon, foto, koordinat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etAlamat = findViewById(R.id.et_alamat);
        etTelepon = findViewById(R.id.et_telepon);
        etFoto = findViewById(R.id.et_foto);
        etKoordinat = findViewById(R.id.et_koordinat);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                alamat = etAlamat.getText().toString();
                telepon = etTelepon.getText().toString();
                foto = etFoto.getText().toString();
                koordinat = etKoordinat.getText().toString();

                if (nama.trim().isEmpty()){
                    etNama.setError("Nama Harus Diisi");
                } else if (alamat.trim().isEmpty()) {
                    etAlamat.setError("Alamat harus di isi");
                } else if (telepon.trim().isEmpty()) {
                    etTelepon.setError("Telepon harus di isi");
                } else if (foto.trim().isEmpty()) {
                    etFoto.setError("Link Foto harus di isi");
                } else if (koordinat.trim().isEmpty()) {
                    etKoordinat.setError("Koordinat Rumah Sakit harus di isi");
                } else {
                    tambahRumahSakit();
                }

            }
        });

    }

    private void tambahRumahSakit(){
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardCreate(nama, alamat, telepon, foto, koordinat);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode: "+kode+ "Pesan: "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal menghubungi server : " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}