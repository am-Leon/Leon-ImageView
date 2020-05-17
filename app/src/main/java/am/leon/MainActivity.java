package am.leon;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GalleryAdapter adapter;
    private RecyclerView recycler_gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewInit();

        adapter.addAll(getImagesList());
    }


    private void viewInit() {
        recycler_gallery = findViewById(R.id.recycler_gallery);

        adapter = new GalleryAdapter(this, "ar");
        recycler_gallery.setAdapter(adapter);
        recycler_gallery.setLayoutManager(new GridLayoutManager(this, 2));
    }


    private List<Media> getImagesList() {
        List<Media> mediaList = new ArrayList<>();

        mediaList.add(new Media("https://brands.solutionplus.net/storage/photos/configs/blue-note-863135.jpeg", Media.TYPE_PHOTO));
        mediaList.add(new Media("https://brands.solutionplus.net/storage/photos/configs/blue-ocean-large-876999.jpeg", Media.TYPE_PHOTO));
        mediaList.add(new Media("https://www.youtube.com/watch?v=kRT9nSMCdZk", Media.TYPE_VIDEO));
        mediaList.add(new Media("https://test.api.alber.solutionplus.net/storage/photos/photosgalleries/YmviZuMeKGTxwARnVXwu.jpeg", Media.TYPE_PHOTO));
        mediaList.add(new Media("https://test.api.alber.solutionplus.net/storage/photos/photosgalleries/T7K0cqyhaA1zvPkGT3dF.jpeg", Media.TYPE_PHOTO));
        mediaList.add(new Media("https://test.api.alber.solutionplus.net/storage/photos/photosgalleries/Qoc7BqC16ikTERvwnne8.jpg", Media.TYPE_PHOTO));

        mediaList.add(new Media("https://brands.solutionplus.net/storage/photos/configs/blue-note-863135.jpeg", Media.TYPE_VIDEO));
        mediaList.add(new Media("https://brands.solutionplus.net/storage/photos/configs/new-product-config-969255.jpg", Media.TYPE_PHOTO));
        mediaList.add(new Media("https://api.tmooralmadina.solutionplus.net/storage/photos/configs/royal-sagai-582472.jpg", Media.TYPE_PHOTO));
        mediaList.add(new Media("https://api.tmooralmadina.solutionplus.net/storage/photos/configs/royal-mabroom-884161.jpg", Media.TYPE_PHOTO));
        mediaList.add(new Media("https://api.tmooralmadina.solutionplus.net/storage/photos/configs/anbra-934097.png", Media.TYPE_PHOTO));
        mediaList.add(new Media("https://api.tmooralmadina.solutionplus.net/storage/photos/configs/sukkari-figs-apricot-konoz-alalia-496682.png", Media.TYPE_PHOTO));

        return mediaList;
    }


    private List<Integer> getIntegerList() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(R.drawable.ic_delete_sweep_black_24dp);
        integerList.add(R.drawable.ic_directions_bike_black_24dp);

        return integerList;
    }

}