package com.example.projektjson;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView Notatki;
    ArrayList<Ogloszenia> ogloszeniaArrayList = new ArrayList<>();

    ArrayAdapter<String> adapter;


    ArrayList<String> elementyListy = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Notatki = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                elementyListy
        );

        Notatki.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/mario08031999-ux/baza_danych_projekt/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolder jsonPlaceHolder = retrofit.create(PlaceHolder.class);

        Call<List<Ogloszenia>> call =jsonPlaceHolder.getOgloszenia();

        call.enqueue(
                new Callback<List<Ogloszenia>>() {
                    @Override
                    public void onResponse(Call<List<Ogloszenia>> call, Response<List<Ogloszenia>> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "błąd" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        List<Ogloszenia> odpowiedz = response.body();

                        if (odpowiedz == null){
                            Toast.makeText(MainActivity.this, "brak danych", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        ogloszeniaArrayList.clear();
                        ogloszeniaArrayList.addAll(odpowiedz);

                        elementyListy.clear();


                        for (Ogloszenia ogloszenia: odpowiedz){
                            Calendar calendar = Calendar.getInstance();

                            calendar.set(ogloszenia.getRok(), ogloszenia.getMiesiac() - 1, ogloszenia.getDzien());

                            int rok = calendar.get(java.util.Calendar.YEAR);
                            int miesiac = calendar.get(java.util.Calendar.MONTH) + 1;
                            int dzien = calendar.get(java.util.Calendar.DAY_OF_MONTH);
                            String textElementu = "Temat: "+ ogloszenia.getTytul() +
                                    "\n" + ogloszenia.getTresc() +
                                    "\n" + dzien +"." + miesiac + "." + rok;
                            elementyListy.add(textElementu);
                        }

                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<List<Ogloszenia>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Błąd sieci: " + t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
        );

    }


}