package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        movieList = new ArrayList<>();
        fetchMovies();
    }

    private void fetchMovies() {

        String url = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYjMxYjVhZjU3ZDUwOWUzMjk5OGYxZDhlNzAxNTVkNSIsInN1YiI6IjY0ZWZlNmViM2E5OTM3MDBhZGE5ZjI0NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.DhbG0AVgQbqrQGorHZfSvqd-uHZs_Qfny3BpnJj5TsQ";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray resultsArray = response.getJSONArray("results");

                            for (int i = 0; i < resultsArray.length(); i++) {
                                JSONObject movieObject = resultsArray.getJSONObject(i);

                                String title = movieObject.getString("title");
                                String year = movieObject.getString("release_date");
                                String overview = movieObject.getString("overview");
                                String poster = movieObject.getString("poster_path");
                                Double rating = movieObject.getDouble("vote_average");
                                if (!poster.equals("null")) {
                                    poster = "https://image.tmdb.org/t/p/w500"+poster;
                                }

                                Movie movie = new Movie(title, year, poster, overview, rating);
                                movieList.add(movie);
                                MovieAdapter adapter = new MovieAdapter(HomeActivity.this , movieList);

                                recyclerView.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR",error.getMessage());
                        Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);
    }
}