# LeonImageView

Leon is an android library written to makes it easy for the programmer to display a set of images in a simple way using Picasso.

## Installation

1- Add this library as a dependency in your app's build.project file.

```groovy

allprojects {  
      repositories {  
         maven { url 'https://jitpack.io' }  
      }  
   }  
   
   ```

2- Add the dependency.

```groovy

    implementation 'com.github.am-Leon:LeonImageView:v1.0.7'

```

## Usage

single image view
set of images view in viewPager
supporting (RTL,LTR) directions.
many features for image view thanks for owner (animated zooming, zooming where it clicks,...)
reloading image in case of failure
Picasso features (place holder,error icon)
finger guster swipe to destroy view of images in full screen.
loadImages throgth 3 ways (stings,files,Media)
load thubmnal view of youtube links and open video link on youtube app. using (media object)


In Xml file.

```xml

    <am.leon.LeonImageView
        android:id="@+id/img_itemPhoto"
        android:layout_width="match_parent"
        android:layout_height="168dp"
        app:leon_default_icon="@drawable/ic_default"
        app:leon_place_holder_icon="@drawable/"
        app:leon_play_video_icon="@drawable/ic_default"
        app:leon_reload_icon="@drawable/ic_default"
        tools:src="@tools:sample/avatars" />

```

1- For Single image 
   - in your activity leon can load different types(String, File, Uri, Media).
   - when calling loadImage after, loading can click to open in full screen.

```java

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LeonImageView imageView = findViewById(R.id.img_itemPhoto);
        
        // for string
        String path = "https://via.placeholder.com/250";
        imageView.loadImage(path);
        
        // for file
        File file = new File("your image file path");
        imageView.loadImage(file);
        
        // for uri objects
        imageView.loadImage("uri object");

        // for media object supports photos url and youtube url videos only
        Media media = new Media("https://via.placeholder.com/250", Media.TYPE_PHOTO);
        imageView.loadImage(media);
        
        Media media = new Media("https://www.youtube.com/watch?v=hGLuCMrUuFk", Media.TYPE_VIDEO);
        imageView.loadImage(media);
        
    }

}

```


2- For set of images
    - in adapter viewHolder use image setOnClickListener
    - for Rtl direction set "ar" 
    - for opening images list with specific position set position

```java

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LeonImageView imageView = findViewById(R.id.img_itemPhoto);
        
        // for any type used from (String, File, Uri, Media).
        String path = "https://via.placeholder.com/150";
        imageView.loadImage(path);
     
        // after loading image use setOnClickListener  
        // to open full screen view and load images list
        imageView.loadMediaImages(getImagesList());
        
        // for opening images list with specific position set position
        imageView.loadMediaImages(items, 2);
        
        // for Rtl direction set "ar" 
        imageView.loadMediaImages(items, 2, "ar");
        
        // used for open full screen view.
        imageView.show();
        
    }
    
    
    private List<String> getImagesList() {
        List<String> mediaList = new ArrayList<>();

        mediaList.add("https://via.placeholder.com/210");
        mediaList.add("https://via.placeholder.com/220");
        mediaList.add("https://via.placeholder.com/230");
        mediaList.add("https://via.placeholder.com/240");
        mediaList.add("https://via.placeholder.com/250");
        mediaList.add("https://via.placeholder.com/260");

        return mediaList;
    }

}


```

## License

```text
MIT License

Copyright (c) 2020 am-Leon

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```